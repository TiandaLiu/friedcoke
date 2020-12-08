package com.friedcoke.rmi.client;

import com.friedcoke.rmi.UserRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;

public class UserRMIClient {

    private static UserRMIClient userRMIClient = null;
    private UserRMI userRMIServer;
    private Registry registry;

    // TODO inject address and port
    private final String friedCokeMetadataServerAddress = "172.17.0.2";
    private final String friedCokeMetadataServerPort = "12344";

    private UserRMIClient() {
        System.out.println("connecting to "+ friedCokeMetadataServerAddress +":"+ friedCokeMetadataServerPort);
        try{
            registry= LocateRegistry.getRegistry(
                    friedCokeMetadataServerAddress,
                    Integer.parseInt(friedCokeMetadataServerPort)
            );
            userRMIServer = (UserRMI) (registry.lookup("rmiServer"));
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
    }

    public static UserRMIClient getInstance() {
        if (userRMIClient == null)
            userRMIClient = new UserRMIClient();
        return userRMIClient;
    }


    // CRUD
    public int addUser(String userJson) {
        try {
            return userRMIServer.addUser(userJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int deleteUser(String userId) {
        try {
            return userRMIServer.deleteUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int updateUser(String userId, String newUserJson) {
        try {
            return userRMIServer.updateUser(userId, newUserJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String getUserById(String userId) {
        try {
            return userRMIServer.getUserById(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Watch List
    public int addAuctionToWatchlist(String userId, UUID auctionId) {
        try {
            return userRMIServer.addAuctionToWatchlist(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int removeAuctionFromWatchlist(String userId, UUID auctionId) {
        try {
            return userRMIServer.removeAuctionFromWatchlist(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<UUID> getWatchlist(String userId) {
        try {
            return userRMIServer.getWatchlist(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Shopping Cart
    public int addAuctionToCart(String userId, UUID auctionId) {
        try {
            return userRMIServer.addAuctionToCart(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int removeAuctionFromCart(String userId, UUID auctionId) {
        try {
            return userRMIServer.removeAuctionFromCart(userId, auctionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<UUID> getCart(String userId) {
        try {
            return userRMIServer.getCart(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
