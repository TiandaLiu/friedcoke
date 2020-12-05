package com.friedcoke.rmi;

import java.util.*;

public class Auction {
    public UUID id;
    public String item_name, description, category, startTime, endTime;
    public String highest_bidder, seller;
    public String start_price, curr_price, buynow_price;
    public String flag, notification, status;
    public Auction(HashMap<String, String> record) {
        this.id = UUID.fromString(record.get("id"));
        this.item_name = record.get("item_name");
        this.description = record.get("description");
        this.category = record.get("category");
        this.startTime = record.get("startTime");
        this.endTime = record.get("endTime");
        this.seller = record.get("seller");
        this.start_price = record.get("start_price");
        this.curr_price = record.get("curr_price");
        this.buynow_price = record.get("buynow_price");
        this.flag = record.get("flag");
        this.notification = record.get("notification");
        this.status = record.get("status");
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHighest_bidder() {
        return highest_bidder;
    }

    public void setHighest_bidder(String highest_bidder) {
        this.highest_bidder = highest_bidder;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getStart_price() {
        return start_price;
    }

    public void setStart_price(String start_price) {
        this.start_price = start_price;
    }

    public String getCurr_price() {
        return curr_price;
    }

    public void setCurr_price(String curr_price) {
        this.curr_price = curr_price;
    }

    public String getBuynow_price() {
        return buynow_price;
    }

    public void setBuynow_price(String buynow_price) {
        this.buynow_price = buynow_price;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
