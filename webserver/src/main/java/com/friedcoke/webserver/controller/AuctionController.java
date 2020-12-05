package com.friedcoke.webserver.controller;

import com.friedcoke.webserver.model.Auction;
import com.friedcoke.webserver.model.Bidding;
import com.friedcoke.webserver.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public int addAuction(@RequestBody Auction auction) {
        return auctionService.addAuction(auction);
    }

    @PutMapping(path = "{id}")
    public int updateAuction(@PathVariable("id") UUID auctionId, @RequestBody Auction newAuction) {
        return auctionService.updateAuction(auctionId, newAuction);
    }



    @GetMapping
    public List<Auction> getAuctions(@RequestParam(value = "status", required = false) String status,
                                     @RequestParam(value = "flag", required = false) String flag,
                                     @RequestParam(value = "keyword", required = false) String keyword) {
        if (status != null) {
            return auctionService.getAuctionsByStatus(status);
        } else if (flag != null && flag.equals("true")) {
            return auctionService.getFlaggedAuctions();
        } else if (keyword != null) {
            return auctionService.getAuctionsByKeyword(keyword);
        }
        return auctionService.getAllAuctions();
    }

    @GetMapping(path = "{id}")
    public Auction getAuctionById(@PathVariable("id") UUID id) {
        return auctionService.getAuctionById(id);
    }

    @PostMapping(path = "biding")
    public int bidAuction(@RequestBody Bidding bidding) {
        return auctionService.bidOnAuction(bidding);
    }

    @PostMapping(path = "{auctionId}/terminate")
    public int terminateAuction(@PathVariable("auctionid") UUID auctionId) {
        return auctionService.terminateAuction(auctionId);
    }

    @PostMapping(path = "{auctionId}/buynow/{userId}")
    public int buynow(@PathVariable("auctionId") UUID auctionId, @PathVariable("userId") String userId) {
        return auctionService.buyNow(auctionId, userId);
    }

    @PostMapping(path = "{auctionId}/purchase/{userId}")
    public int purchaseAuction(@PathVariable("auctionId") UUID auctionId, @PathVariable("userId") String userId) {
        return auctionService.purchaseAuction(auctionId, userId);
    }

}
