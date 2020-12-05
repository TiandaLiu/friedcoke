package com.friedcoke.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface UserRemoteInterface extends Remote {
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
}
