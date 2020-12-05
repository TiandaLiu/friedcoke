package com.friedcoke.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Bidding {

    private final UUID auctionId;
    private final String userId;
    private final String biddingPrice;

    public Bidding(@JsonProperty("auctionId") UUID auctionId,
                   @JsonProperty("userId") String userId,
                   @JsonProperty("biddingPrice") String biddingPrice) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.biddingPrice = biddingPrice;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBiddingPrice() {
        return biddingPrice;
    }
}
