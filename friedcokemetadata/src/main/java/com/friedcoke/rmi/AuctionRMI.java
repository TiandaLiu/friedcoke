package com.friedcoke.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface AuctionRMI extends Remote {

    // auction CRUD
    int addAuction(String auctionJson) throws RemoteException;
    int updateAuction(UUID auctionId, String newAuctionJson) throws RemoteException;
    List<String> getAllAuctions() throws RemoteException;
    String getAuctionById(UUID acutionId) throws RemoteException;

    // category CRUD
    int addCategory(UUID categoryId, String category) throws RemoteException;
    int deleteCategory(UUID categoryId) throws RemoteException;
    int updateCategory(UUID categoryId, String category) throws RemoteException;
    List<String> getAllCategory() throws RemoteException; //return a list of categoryJson string

}
