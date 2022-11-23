package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(int id);
    void remove(int id);

    ProductCategory find(UUID id);

    void remove(UUID id);

    List<ProductCategory> getAll();

}
