package com.friedcoke.webserver.controller;

import com.friedcoke.webserver.model.User;
import com.friedcoke.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "signup")
    public int signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping(path = "login")
    public int login(@RequestBody User user) {
        return userService.login(user);
    }

    @PutMapping(path = "{id}/suspend")
    public int suspendUser(@PathVariable("id") String userId) {
        return userService.suspendUser(userId);
    }

    @PutMapping(path = "{id}/block")
    public int blockUser(@PathVariable("id") String userId) {
        return userService.blockUser(userId);
    }

    @PutMapping(path = "{id}/update")
    public int changePassword(@RequestBody User user) {
        return userService.changePassword(user);
    }

    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(path = "{id}")
    public int deleteUser(@PathVariable("id") String userId) {
        return userService.deleteUser(userId);
    }

    @PutMapping(path = "{id}")
    public int updateUser(@PathVariable("id") String userId, @RequestBody User newUser) {
        return userService.updateUser(userId, newUser);
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") String userId) {
        return userService.getUserById(userId);
    }


    @PostMapping(path = "{userId}/watchlist/{auctionId}")
    public int addAuctionToWatchlist(@PathVariable("userId") String userId, @PathVariable("auctionId") UUID auctionId) {
        return userService.addAuctionToWatchlist(userId, auctionId);
    }

    @DeleteMapping(path = "{userId}/watchlist/{auctionId}")
    public int removeAuctionFromWatchlist(@PathVariable("userId") String userId, @PathVariable("auctionId") UUID auctionId) {
        return userService.removeAuctionFromWatchlist(userId, auctionId);
    }

    @GetMapping(path = "{userId}/watchlist")
    public List<UUID> getWatchlist(@PathVariable("userId") String userId) {
        return userService.getWatchlist(userId);
    }


    @PostMapping(path = "{userId}/cart/{auctionId}")
    public int addAuctionToCart(@PathVariable("userId") String userId, @PathVariable("auctionId") UUID auctionId) {
        return userService.addAuctionToCart(userId, auctionId);
    }

    @DeleteMapping(path = "{userId}/cart/{auctionId}")
    public int removeAuctionFromCart(@PathVariable("userId") String userId, @PathVariable("auctionId") UUID auctionId) {
        return userService.removeAuctionFromCart(userId, auctionId);
    }

    @GetMapping(path = "{userId}/cart")
    public List<UUID> getCart(@PathVariable("userId") String userId) {
        return userService.getCart(userId);
    }

}
