package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.model.Address;
import com.ecobazar.main.ecommerce.model.Order;
import com.ecobazar.main.ecommerce.model.User;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user , Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

}
