package com.friedcoke.friedcokemetadata.model;

import com.google.gson.Gson;

import java.util.*;

public class Category {
    private UUID category_id;
    private String category_description;

    public Category(
            UUID category_id,
            String category_description) {
        this.category_id = category_id;
        this.category_description = category_description;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Category fromJson(String categoryJson) {
        Gson gson = new Gson();
        return gson.fromJson(categoryJson, Category.class);
    }
}
