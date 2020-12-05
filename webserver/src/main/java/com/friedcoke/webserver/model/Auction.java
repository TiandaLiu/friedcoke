package com.friedcoke.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import java.util.UUID;

public class Auction {

    private UUID id;
    private String item_name, description, category, startTime, endTime, status;
    private String highest_bidder, seller;
    private Double start_price, curr_price, buynow_price;
    private Boolean flag;

    public Auction(
            @JsonProperty("id") UUID id,
            @JsonProperty("item_name") String item_name,
            @JsonProperty("description") String description,
            @JsonProperty("category") String category,
            @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime,
            @JsonProperty("status") String status,
            @JsonProperty("highest_bidder") String highest_bidder,
            @JsonProperty("seller") String seller,
            @JsonProperty("start_price") Double start_price,
            @JsonProperty("curr_price") Double curr_price,
            @JsonProperty("buynow_price") Double buynow_price,
            @JsonProperty("flag") Boolean flag) {
        this.id = id;
        this.item_name = item_name;
        this.description = description;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.highest_bidder = highest_bidder;
        this.seller = seller;
        this.start_price = start_price;
        this.curr_price = curr_price;
        this.buynow_price = buynow_price;
        this.flag = flag;
    }

    public UUID getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public String getHighest_bidder() {
        return highest_bidder;
    }

    public String getSeller() {
        return seller;
    }

    public Double getStart_price() {
        return start_price;
    }

    public Double getCurr_price() {
        return curr_price;
    }

    public Double getBuynow_price() {
        return buynow_price;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHighest_bidder(String highest_bidder) {
        this.highest_bidder = highest_bidder;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setStart_price(Double start_price) {
        this.start_price = start_price;
    }

    public void setCurr_price(Double curr_price) {
        this.curr_price = curr_price;
    }

    public void setBuynow_price(Double buynow_price) {
        this.buynow_price = buynow_price;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Auction fromJson(String auctionJson) {
        Gson gson = new Gson();
        return gson.fromJson(auctionJson, Auction.class);
    }

}
