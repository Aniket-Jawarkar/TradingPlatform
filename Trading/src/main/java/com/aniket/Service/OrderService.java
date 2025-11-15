package com.aniket.service;

import com.aniket.domain.OrderType;
import com.aniket.model.Coin;
import com.aniket.model.Order;
import com.aniket.model.OrderItem;
import com.aniket.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder (User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;
    List<Order> getAllOrdersOfUser (Long userId, OrderType orderType, String assetSymbol);
    Order processOrder (Coin coin, double quantity, OrderType orderType, User user) throws Exception;
}
