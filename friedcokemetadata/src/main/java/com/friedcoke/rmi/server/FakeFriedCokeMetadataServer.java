package com.friedcoke.rmi.server;

import com.friedcoke.friedcokemetadata.model.Auction;
import com.friedcoke.friedcokemetadata.model.Category;
import com.friedcoke.friedcokemetadata.model.User;
import com.friedcoke.rmi.FriedCokeMetadataInterface;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FakeFriedCokeMetadataServer extends UnicastRemoteObject implements FriedCokeMetadataInterface {
    int      port;
    String   address;
    Registry registry;

    Map<UUID, String> auctionJsonMap = new HashMap<>();
    Map<UUID, String> categoryMap = new HashMap<>();
    Map<String, String> userMap = new HashMap<>();


    @Override
    public int addAuction(String auctionJson) throws RemoteException {
        System.out.println("Adding: " + auctionJson);
        Auction auction = Auction.fromJson(auctionJson);
        UUID auctionId = auction.getId();
        auctionJsonMap.put(auctionId, auctionJson);
        return 1;
    }

    @Override
    public int updateAuction(UUID auctionId, String newAuctionJson) throws RemoteException {
        auctionJsonMap.put(auctionId, newAuctionJson);
        return 1;
    }

    @Override
    public List<String> getAllAuctions() throws RemoteException {
        List<String> auctionJsonList = new ArrayList<>();
        for (String auctionJson : auctionJsonMap.values()) {
            auctionJsonList.add(auctionJson);
        }
        return auctionJsonList;
    }

    @Override
    public String getAuctionById(UUID acutionId) throws RemoteException {
        return auctionJsonMap.getOrDefault(acutionId, null);
    }

    @Override
    public int addCategory(UUID categoryId, String category) throws RemoteException {
        System.out.println("Adding: " + category);
        Category categoryObj = new Category(categoryId, category);
        String categoryJson = categoryObj.toJson();
        categoryMap.put(categoryId, categoryJson);
        return 1;
    }

    @Override
    public int deleteCategory(UUID categoryId) throws RemoteException {
        categoryMap.remove(categoryId);
        return 1;
    }

    @Override
    public int updateCategory(UUID categoryId, String category) throws RemoteException {
        Category categoryObj = new Category(categoryId, category);
        String categoryJson = categoryObj.toJson();
        categoryMap.put(categoryId, categoryJson);
        return 1;
    }

    @Override
    public List<String> getAllCategory() throws RemoteException {
        List<String> categoryList = new ArrayList<>();
        for (String category: categoryMap.values())
            categoryList.add(category);
        return categoryList;
    }

    @Override
    public int addUser(String userJson) throws RemoteException {
        System.out.println("Adding : " + userJson);
        User user = User.fromJson(userJson);
        String username = user.getUsername();
        userMap.put(username, userJson);
        return 1;
    }

    @Override
    public int deleteUser(String userId) throws RemoteException {
        userMap.remove(userId);
        return 1;
    }

    @Override
    public int updateUser(String userId, String newUserJson) throws RemoteException {
        userMap.put(userId, newUserJson);
        return 1;
    }

    @Override
    public String getUserById(String userId) throws RemoteException {
        return userMap.getOrDefault(userId, null);
    }

    @Override
    public int addAuctionToWatchlist(String userId, UUID auctionId) throws RemoteException {
        return 0;
    }

    @Override
    public int removeAuctionFromWatchlist(String userId, UUID auctionId) throws RemoteException {
        return 0;
    }

    @Override
    public List<UUID> getWatchlist(String userId) throws RemoteException {
        return null;
    }

    @Override
    public int addAuctionToCart(String userId, UUID auctionId) throws RemoteException {
        return 0;
    }

    @Override
    public int removeAuctionFromCart(String userId, UUID auctionId) throws RemoteException {
        return 0;
    }

    @Override
    public List<UUID> getCart(String userId) throws RemoteException {
        return null;
    }

    @Override
    public int auctionCompleted(String highest_bidder, UUID auction_id) throws RemoteException {
        return 0;
    }

    @Override
    public int auctionEndNotification(UUID auction_id, long interval) throws RemoteException {
        return 0;
    }

    public FakeFriedCokeMetadataServer() throws RemoteException
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

