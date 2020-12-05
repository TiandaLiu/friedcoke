package com.friedcoke.webserver.service;

import com.friedcoke.rmi.FriedCokeMetadataClient;
import com.friedcoke.webserver.model.Auction;
import com.friedcoke.webserver.model.Bidding;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    private final FriedCokeMetadataClient friedCokeMetadataClient = FriedCokeMetadataClient.getInstance();

    public int addAuction(Auction auction) {
        UUID auctionId = UUID.randomUUID();
        auction.setId(auctionId);
        return friedCokeMetadataClient.addAuction(auction.toJson());
    }

    public int updateAuction(UUID auctionId, Auction newAuction) {
        return friedCokeMetadataClient.updateAuction(auctionId, newAuction.toJson());
    }

    public List<Auction> getAllAuctions() {
        List<String> auctionJsonList = friedCokeMetadataClient.getAllAuctions();
        List<Auction> auctionList = auctionJsonList.stream()
                .map(auctionJson -> Auction.fromJson(auctionJson))
                .collect(Collectors.toList());
        return auctionList;
    }

    public Auction getAuctionById(UUID id) {
        String auctionJson =friedCokeMetadataClient.getAuctionById(id);
        return Auction.fromJson(auctionJson);
    }

//    // TBD
    public List<Auction> getAuctionsByStatus(String status) {
        List<String> auctionJsonList = friedCokeMetadataClient.getAllAuctions();
        List<Auction> activeAuctions = auctionJsonList.stream()
                .map(auctionJson -> Auction.fromJson(auctionJson))
                .filter(auction -> auction.getStatus().equals(status))
                .collect(Collectors.toList());
        return activeAuctions;
    }

    public List<Auction> getFlaggedAuctions() {
        List<String> auctionJsonList = friedCokeMetadataClient.getAllAuctions();
        List<Auction> flaggedAuctions = auctionJsonList.stream()
                .map(auctionJson -> Auction.fromJson(auctionJson))
                .filter(auction -> auction.getFlag().equals(Boolean.TRUE))
                .collect(Collectors.toList());
        return flaggedAuctions;
    }

    public List<Auction> getAuctionsByKeyword(String keyword) {
        List<String> auctionJsonList = friedCokeMetadataClient.getAllAuctions();
        List<Auction> auctionList = auctionJsonList.stream()
                .map(auctionJson -> Auction.fromJson(auctionJson))
                .filter(auction -> (auction.getItem_name().contains(keyword) || auction.getCategory().equals(keyword)))
                .collect(Collectors.toList());
        return auctionList;
    }

    public int bidOnAuction(Bidding bidding) {
        Double biddingPrice = Double.parseDouble(bidding.getBiddingPrice());
        String userId = bidding.getUserId();
        UUID auctionId = bidding.getAuctionId();

        Auction auction = getAuctionById(auctionId);
        Double currPrice = auction.getCurr_price();
        // validation
        if (biddingPrice <= currPrice) {
            return -1;
        }

        // sendEmail();
        auction.setHighest_bidder(userId);
        auction.setCurr_price(biddingPrice);
        return updateAuction(auctionId, auction);
    }

    public int terminateAuction(UUID auctionId) {
        Auction auction = getAuctionById(auctionId);
        auction.setStatus("terminated");
        return updateAuction(auctionId, auction);
    }

    public int buyNow(UUID auctionId, String userId) {
        // update auction
        Auction auction = getAuctionById(auctionId);
        auction.setStatus("finished");
        // add to cart
        if (friedCokeMetadataClient.addAuctionToCart(userId, auctionId) == -1) return -1;
        if (updateAuction(auctionId, auction) == -1) return -1;
        return 1;
    }

    public int purchaseAuction(UUID auctionId, String userId) {
        // update auction
        Auction auction = getAuctionById(auctionId);
        auction.setStatus("finished");
        // remove from cart
        if (friedCokeMetadataClient.removeAuctionFromCart(userId, auctionId) == -1) return -1;
        if (updateAuction(auctionId, auction) == -1) return -1;
        return 1;
    }


}
