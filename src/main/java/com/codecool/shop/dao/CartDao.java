package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.Map;
import java.util.UUID;

public interface CartDao {

    public void addToCart(Product product, UUID userId);
    public void removeFromCart(Product product, UUID userId);
    public Map<Product, Integer> getCart(UUID userId);
    void emptyCart();

}
