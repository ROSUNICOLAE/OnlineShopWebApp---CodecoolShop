package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.Map;
import java.util.UUID;


public interface OrderDao {

    Order createOrder(Map<String, String> clientDetails, CartDao cart, UUID userId);

    Order getOrder(UUID orderID);

    void confirmOrder();


}
