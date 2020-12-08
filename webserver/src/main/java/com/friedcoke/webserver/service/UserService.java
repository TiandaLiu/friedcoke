package com.friedcoke.webserver.service;

import com.friedcoke.rmi.FriedCokeMetadataClient;
import com.friedcoke.webserver.model.Auction;
import com.friedcoke.webserver.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final FriedCokeMetadataClient friedCokeMetadataClient = FriedCokeMetadataClient.getInstance();

    public int signup(User user) {
        String userId = user.getUsername();
        String password = user.getPassword();
        User persistedUser = getUserById(userId);
        if (persistedUser != null) return -1;
        User newUser = new User(userId, password, "active");
        return addUser(newUser);
    }

    public int login(User user) {
        String userId = user.getUsername();
        String password = user.getPassword();

        User persistedUser = getUserById(userId);
        if (persistedUser == null) return -1;
        if (persistedUser.getStatus() != "active") return -1;
        String correctPassword = persistedUser.getPassword();
        if (!password.equals(correctPassword)) return -1;
        return 1;
    }

    public int suspendUser(String userId) {
        User user = getUserById(userId);
        user.setStatus("suspended");
        return updateUser(user.getUsername(), user);
    }

    public int blockUser(String userId) {
        User user = getUserById(userId);
        user.setStatus("blocked");
        return updateUser(user.getUsername(), user);
    }

    public int changePassword(User user) {
        String userId = user.getUsername();
        String newPassword = user.getPassword();
        User persistedUser = getUserById(userId);
        persistedUser.setPassword(newPassword);
        return updateUser(userId, persistedUser);
    }

    // CRUD
    public int addUser(User user) {
        String userJson = user.toJson();
        return friedCokeMetadataClient.addUser(userJson);
    }

    public int deleteUser(String userId) {
        return friedCokeMetadataClient.deleteUser(userId);
    }

    public int updateUser(String userId, User newUser) {
        String newUserJson = newUser.toJson();
        return friedCokeMetadataClient.updateUser(userId, newUserJson);
    }

    public User getUserById(String userId) {
        String userJson = friedCokeMetadataClient.getUserById(userId);
        User user = User.fromJson(userJson);
        return user;
    }


    // Watch List
    public int addAuctionToWatchlist(String userId, UUID auctionId) {
        return friedCokeMetadataClient.addAuctionToWatchlist(userId, auctionId);
    }

    public int removeAuctionFromWatchlist(String userId, UUID auctionId) {
        return removeAuctionFromWatchlist(userId, auctionId);
    }

    public List<Auction> getWatchlist(String userId) {
        List<UUID> uuidList = friedCokeMetadataClient.getWatchlist(userId);
        List<Auction> auctions = new ArrayList<>();
        for (UUID auctionId: uuidList) {
            String auctionJson =friedCokeMetadataClient.getAuctionById(auctionId);
            auctions.add(Auction.fromJson(auctionJson));
        }
        return auctions;
    }


    // Shopping Cart
    public int addAuctionToCart(String userId, UUID auctionId) {
        return friedCokeMetadataClient.addAuctionToCart(userId, auctionId);
    }

    public int removeAuctionFromCart(String userId, UUID auctionId) {
        return friedCokeMetadataClient.removeAuctionFromCart(userId, auctionId);
    }

    public List<UUID> getCart(String userId) {
        return friedCokeMetadataClient.getCart(userId);
    }
}
