package com.friedcoke.webserver.service;

import com.friedcoke.rmi.FriedCokeMetadataClient;
import com.friedcoke.webserver.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final FriedCokeMetadataClient friedCokeMetadataClient = FriedCokeMetadataClient.getInstance();

    public int addCategory(Category category) {
        UUID categoryId = UUID.randomUUID();
        category.setCategory_id(categoryId);
        String categoryName = category.getCategory_description();
        return friedCokeMetadataClient.addCategory(categoryId, categoryName);
    }

    public int deleteCategory(UUID categoryId) {
        return friedCokeMetadataClient.deleteCategory(categoryId);
    }

    public int updateCategory(UUID categoryId, Category category) {
        String categoryName = category.getCategory_description();
        return friedCokeMetadataClient.updateCategory(categoryId, categoryName);
    }

    public List<Category> getAllCategories() {
        List<String> categoryJsonList = friedCokeMetadataClient.getAllCategory();
        List<Category> categoryList = categoryJsonList.stream()
                .map(categoryJson -> Category.fromJson(categoryJson))
                .collect(Collectors.toList());
        return categoryList;
    }
}
