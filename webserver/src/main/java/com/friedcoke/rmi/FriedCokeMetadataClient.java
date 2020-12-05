package com.friedcoke.rmi;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.List;
import java.util.UUID;

public class FriedCokeMetadataClient {

    private static FriedCokeMetadataClient friedCokeMetadataClient = null;
    private FriedCokeMetadataInterface friedCokeMetadataServer;
    private Registry registry;

    // TODO inject address and port
    private final String friedCokeMetadataServerAddress = "127.0.0.1";
    private final String friedCokeMetadataServerPort = "12345";

    private FriedCokeMetadataClient() {
        System.out.println("connecting to "+ friedCokeMetadataServerAddress +":"+ friedCokeMetadataServerPort);
        try{
            registry=LocateRegistry.getRegistry(
                    friedCokeMetadataServerAddress,
                    Integer.parseInt(friedCokeMetadataServerPort)
            );
            friedCokeMetadataServer = (FriedCokeMetadataInterface) (registry.lookup("rmiServer"));
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
    }

    public static FriedCokeMetadataClient getInstance() {
        if (friedCokeMetadataClient == null)
            friedCokeMetadataClient = new FriedCokeMetadataClient();
        return friedCokeMetadataClient;
    }

    public int addAuction(String auctionJson){
        try {
            return friedCokeMetadataServer.addAuction(auctionJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateAuction(UUID auctionId, String newAuctionJson) {
        try {
            return friedCokeMetadataServer.updateAuction(auctionId, newAuctionJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<String> getAllAuctions() {
        try {
            return friedCokeMetadataServer.getAllAuctions();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getAuctionById(UUID acutionId) {
        try {
            return friedCokeMetadataServer.getAuctionById(acutionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // category CRUD
    public int addCategory(UUID categoryId, String category) {
        try {
            return friedCokeMetadataServer.addCategory(categoryId, category);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteCategory(UUID categoryId) {
        try {
            return friedCokeMetadataServer.deleteCategory(categoryId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateCategory(UUID categoryId, String category) {
        try {
            return friedCokeMetadataServer.updateCategory(categoryId, category);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getAllCategory() {
        try {
            return friedCokeMetadataServer.getAllCategory();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // CRUD
    public int addUser(String userJson) {
        try {
            return friedCokeMetadataServer.addUser(userJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int deleteUser(String userId) {
        try {
            return friedCokeMetadataServer.deleteUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int updateUser(String userId, String newUserJson) {
        try {
            return friedCokeMetadataServer.updateUser(userId, newUserJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String getUserById(String userId) {
        try {
            return friedCokeMetadataServer.getUserById(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Watch List
    public int addAuctionToWatchlist(String userId, UUID auctionId) {
        try {
            return friedCokeMetadataServer.addAuctionToWatchlist(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int removeAuctionFromWatchlist(String userId, UUID auctionId) {
        try {
            return friedCokeMetadataServer.removeAuctionFromWatchlist(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<UUID> getWatchlist(String userId) {
        try {
            return friedCokeMetadataServer.getWatchlist(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Shopping Cart
    public int addAuctionToCart(String userId, UUID auctionId) {
        try {
            return friedCokeMetadataServer.addAuctionToCart(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int removeAuctionFromCart(String userId, UUID auctionId) {
        try {
            return friedCokeMetadataServer.removeAuctionFromCart(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<UUID> getCart(String userId) {
        try {
            return friedCokeMetadataServer.getCart(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}

