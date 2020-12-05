package com.friedcoke.rmi.client;

import com.friedcoke.rmi.AuctionRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;

public class AuctionRMIClient {

    private static AuctionRMIClient auctionRMIClient = null;
    private AuctionRMI auctionRMIServer;
    private Registry registry;

    // TODO inject address and port
    private final String friedCokeMetadataServerAddress = "127.0.0.1";
    private final String friedCokeMetadataServerPort = "12346";

    private AuctionRMIClient() {
        System.out.println("connecting to "+ friedCokeMetadataServerAddress +":"+ friedCokeMetadataServerPort);
        try{
            registry= LocateRegistry.getRegistry(
                    friedCokeMetadataServerAddress,
                    Integer.parseInt(friedCokeMetadataServerPort)
            );
            auctionRMIServer = (AuctionRMI) (registry.lookup("rmiServer"));
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
    }

    public static AuctionRMIClient getInstance() {
        if (auctionRMIClient == null)
            auctionRMIClient = new AuctionRMIClient();
        return auctionRMIClient;
    }

    // auction CRUD
    public int addAuction(String auctionJson) {
        try {
            return auctionRMIServer.addAuction(auctionJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateAuction(UUID auctionId, String newAuctionJson) {
        try {
            return auctionRMIServer.updateAuction(auctionId, newAuctionJson);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<String> getAllAuctions() {
        try {
            return auctionRMIServer.getAllAuctions();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getAuctionById(UUID acutionId) {
        try {
            return auctionRMIServer.getAuctionById(acutionId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    // category CRUD
    public int addCategory(UUID categoryId, String category) {
        try {
            return auctionRMIServer.addCategory(categoryId, category);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int deleteCategory(UUID categoryId) {
        try {
            return auctionRMIServer.deleteCategory(categoryId);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int updateCategory(UUID categoryId, String category) {
        try {
            return auctionRMIServer.updateCategory(categoryId, category);
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public List<String> getAllCategory() {
        try {
            return auctionRMIServer.getAllCategory();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
