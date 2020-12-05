package com.friedcoke.rmi;

import com.google.gson.Gson;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;

import static com.friedcoke.utils.CommonStrings.auction_table;
import static com.friedcoke.utils.CommonStrings.category_table;
import static com.friedcoke.utils.tableManipulation.tableModification;
import static com.friedcoke.utils.tableManipulation.tableScan;


public class AuctionRMIServer extends java.rmi.server.UnicastRemoteObject
        implements AuctionRMIInterface
{
    int      port;
    String   address;
    Registry registry;    // rmi registry for lookup the remote objects.
    // auction CRUD
    public int addAuction(String auctionJson) throws RemoteException {
        Gson gson = new Gson();
        Auction auc = gson.fromJson(auctionJson, Auction.class);
        String query = String.format("INSERT INTO %s VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                auction_table,auc.id,auc.item_name, auc.description, auc.category, auc.start_price, auc.curr_price,
                auc.curr_price, auc.buynow_price, auc.startTime, auc.endTime, auc.highest_bidder, auc.seller,
                auc.flag, auc.status);
        return tableModification(query);
    }

    public int updateAuction(UUID auctionId, String newAuctionJson) throws RemoteException {
        String query_delete = String.format("DELETE FROM %s WHERE id = %s", auction_table,auctionId);
        if (tableModification(query_delete) == 0){
            return 0;
        }
        return addAuction(newAuctionJson);
    }

    public List<String> getAllAuctions() throws RemoteException {
        String query = String.format("SELECT * from %s", auction_table);
        List<String> auctions = tableScan(auction_table, query);

        return auctions;
    }

    public String getAuctionById(UUID acutionId) throws RemoteException {
        String query = String.format("SELECT * from %s where id = %s", auction_table, acutionId);
        List<String> auctions = tableScan(auction_table, query);
        return auctions.get(0);
    }

    // category CRUD
    public int addCategory(UUID categoryId, String category) throws RemoteException {
        String query = String.format("INSERT INTO %s VALUES('%s', '%s')", category_table,categoryId,category);
        System.out.println(query);
        return tableModification(query);
    }

    public int deleteCategory(UUID categoryId) throws RemoteException {
        String query = String.format("DELETE FROM %s WHERE category_id = %s",category_table,categoryId);
        return tableModification(query);
    }

    public int updateCategory(UUID categoryId, String category) throws RemoteException {
        String query = String.format("UPDATE %s SET category_desciption = %s WHERE category_id = %s",category_table, category, categoryId);
        return tableModification(query);
    }

    //return a list of categoryJson string
    public List<String> getAllCategory() throws RemoteException{
        String query = String.format("SELECT * from %s", category_table);
        return tableScan(category_table, query);
    }

    public AuctionRMIServer() throws RemoteException
    {
        try{
//            address= (InetAddress.getLocalHost()).toString();
            address = "0.0.0.0";
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
        port=12346;  // our port
        System.out.println("using address="+address+",port="+port);
        try{
            // create the registry and bind the name and object.
            registry = LocateRegistry.createRegistry( port );
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e){
            throw e;
        }
    }

    static public void main(String args[])
    {
        try{
            AuctionRMIServer s = new AuctionRMIServer();
            System.out.println(System.getProperty("java.class.path"));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
