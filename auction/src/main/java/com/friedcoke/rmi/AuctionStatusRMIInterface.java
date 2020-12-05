package com.friedcoke.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface AuctionStatusRMIInterface extends Remote {
    // auction CRUD
    int auctionCompleted(String highest_bidder, UUID auction_id) throws RemoteException;
    int auctionEndNotification(UUID auction_id, long interval) throws RemoteException;
}
