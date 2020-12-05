package com.friedcoke.rmi;

import java.util.*;

public class Category {
    private String category_id, category_description;
    public Category(String _category_id, String _category_description) {
        this.category_id = _category_id;
        this.category_description = _category_description;
    }

    public Category(List<String> keys){

    }
}
