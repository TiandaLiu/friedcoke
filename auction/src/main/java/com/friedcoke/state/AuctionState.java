package com.friedcoke.state;

import com.friedcoke.rmi.Auction;
import com.friedcoke.rmi.AuctionStatusRMIInterface;
import com.google.gson.Gson;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Instant;
import java.util.List;

import static com.friedcoke.utils.CommonStrings.auction_table;
import static com.friedcoke.utils.tableManipulation.tableModification;
import static com.friedcoke.utils.tableManipulation.tableScan;

public class AuctionState {
    public static void scanAuction(AuctionStatusRMIInterface rmiServer) throws RemoteException {
        String query = String.format("SELECT * from %s", auction_table);
        List<String> rs = tableScan(auction_table, query);
        for(String auc: rs){
            Gson gson = new Gson();
            Auction auction = gson.fromJson(auc, Auction.class);
            if(checkStatus(auction) == 1){
                rmiServer.auctionCompleted(auction.highest_bidder, auction.id);
            }
            if(checkInterval(auction, 360l) == 1){
                rmiServer.auctionEndNotification(auction.id, 360l);
            }
            if(checkInterval(auction, 86400l) ==1){
                rmiServer.auctionEndNotification(auction.id, 86400l);
            }
        }
    }

    /**
     *
     * @param auction
     * @param interval
     * @return 1: send email, 0: not send email
     */
    static int checkInterval(Auction auction, long interval) {
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        long endTime = Long.parseLong(auction.endTime),notificationStatus = Long.parseLong(auction.notification) ;
        if(endTime-timeStampSeconds<=interval && notificationStatus>interval){
            String query = String.format("UPDATE %s SET notification = \"%s\" WHERE id = '%s'",auction_table, interval,auction.id);
            tableModification(query);
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param auction
     * @return int representing the status of the auction
     *  0: ready
     *  1: active
     *  2: finished
     */
    static int checkStatus(Auction auction){
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();
        long startTime = Long.parseLong(auction.startTime), endTime = Long.parseLong(auction.endTime);
        if(startTime >= timeStampSeconds && auction.status.equals("ready")) {
            String query = String.format("UPDATE %s SET status = \"active\" WHERE id = '%s'",auction_table, auction.id);
            tableModification(query);
            return 1;
        }

        if(endTime >= timeStampSeconds && auction.status.equals("active")) {
            String query = String.format("UPDATE %s SET status = \"completed\" WHERE id = '%s'",auction_table, auction.id);
            tableModification(query);
            return 2;
        }
        return 0;
    }

    static public void main(String args[])
    {
        try {
            Registry registry;
            String serverAddress=args[0];
            String serverPort=args[1];
            registry = LocateRegistry.getRegistry(
                    serverAddress,
                    (new Integer(serverPort)).intValue()
            );
            AuctionStatusRMIInterface rmiServer= (AuctionStatusRMIInterface) (registry.lookup("rmiServer"));
            while(true){
                scanAuction(rmiServer);
            }
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }


    }
}
