package com.friedcoke.rmi.server;

import com.friedcoke.rmi.FriedCokeMetadataInterface;
import com.friedcoke.rmi.client.AuctionRMIClient;
import com.friedcoke.rmi.client.UserRMIClient;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;

public class FriedCokeMetadataServer extends UnicastRemoteObject implements FriedCokeMetadataInterface {

    private final AuctionRMIClient auctionRMIClient = AuctionRMIClient.getInstance();
    private final UserRMIClient userRMIClient = UserRMIClient.getInstance();
    int      port;
    String   address;
    Registry registry;

    @Override
    public int addAuction(String auctionJson) throws RemoteException {
        return auctionRMIClient.addAuction(auctionJson);
    }

    @Override
    public int updateAuction(UUID auctionId, String newAuctionJson) throws RemoteException {
        return auctionRMIClient.updateAuction(auctionId, newAuctionJson);
    }

    @Override
    public List<String> getAllAuctions() throws RemoteException {
        return auctionRMIClient.getAllAuctions();
    }

    @Override
    public String getAuctionById(UUID acutionId) throws RemoteException {
        return auctionRMIClient.getAuctionById(acutionId);
    }

    @Override
    public int addCategory(UUID categoryId, String category) throws RemoteException {
        return auctionRMIClient.addCategory(categoryId, category);
    }

    @Override
    public int deleteCategory(UUID categoryId) throws RemoteException {
        return auctionRMIClient.deleteCategory(categoryId);
    }

    @Override
    public int updateCategory(UUID categoryId, String category) throws RemoteException {
        return auctionRMIClient.updateCategory(categoryId, category);
    }

    @Override
    public List<String> getAllCategory() throws RemoteException {
        return auctionRMIClient.getAllCategory();
    }

    @Override
    public int addUser(String userJson) throws RemoteException {
        return userRMIClient.addUser(userJson);
    }

    @Override
    public int deleteUser(String userId) throws RemoteException {
        return userRMIClient.deleteUser(userId);
    }

    @Override
    public int updateUser(String userId, String newUserJson) throws RemoteException {
        return userRMIClient.updateUser(userId, newUserJson);
    }

    @Override
    public String getUserById(String userId) throws RemoteException {
        return userRMIClient.getUserById(userId);
    }

    @Override
    public int addAuctionToWatchlist(String userId, UUID auctionId) throws RemoteException {
        return userRMIClient.addAuctionToWatchlist(userId, auctionId);
    }

    @Override
    public int removeAuctionFromWatchlist(String userId, UUID auctionId) throws RemoteException {
        return userRMIClient.removeAuctionFromWatchlist(userId, auctionId);
    }

    @Override
    public List<UUID> getWatchlist(String userId) throws RemoteException {
        return userRMIClient.getWatchlist(userId);
    }

    @Override
    public int addAuctionToCart(String userId, UUID auctionId) throws RemoteException {
        return userRMIClient.addAuctionToCart(userId, auctionId);
    }

    @Override
    public int removeAuctionFromCart(String userId, UUID auctionId) throws RemoteException {
        return userRMIClient.removeAuctionFromCart(userId, auctionId);
    }

    @Override
    public List<UUID> getCart(String userId) throws RemoteException {
        return userRMIClient.getCart(userId);
    }

    @Override
    public int auctionCompleted(String highest_bidder, UUID auction_id) throws RemoteException {
        return 0;
    }

    @Override
    public int auctionEndNotification(UUID auction_id, long interval) throws RemoteException {
        return 0;
    }


    public FriedCokeMetadataServer() throws RemoteException
    {
        try{
            // get the address of this host.
            address= (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
        port=12345;  // our port
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
            FakeFriedCokeMetadataServer s=new FakeFriedCokeMetadataServer();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

