package com.friedcoke.rmi;

import java.rmi.*;
import java.util.List;
import java.util.UUID;

public interface FriedCokeMetadataInterface extends Remote
{

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

    // CRUD
    int addUser(String userJson) throws RemoteException;
    int deleteUser(String userId) throws RemoteException;
    int updateUser(String userId, String newUserJson) throws RemoteException;
    String getUserById(String userId) throws RemoteException;

    // Watch List
    int addAuctionToWatchlist(String userId, UUID auctionId) throws RemoteException;
    int removeAuctionFromWatchlist(String userId, UUID auctionId) throws RemoteException;
    List<UUID> getWatchlist(String userId) throws RemoteException; // return a list of auctionId

    // Shopping Cart
    int addAuctionToCart(String userId, UUID auctionId) throws RemoteException;
    int removeAuctionFromCart(String userId, UUID auctionId) throws RemoteException;
    List<UUID> getCart(String userId) throws RemoteException; // return a list of auctionId

    // Notification
    int addNotification(String notificationJson) throws RemoteException;
    List<String> getAllNotifications() throws RemoteException;

    // Call by Auction Server
    int auctionCompleted(String highest_bidder, UUID auction_id) throws RemoteException;
    int auctionEndNotification(UUID auction_id, long interval) throws RemoteException;

}
