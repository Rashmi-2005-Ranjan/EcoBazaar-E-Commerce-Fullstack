package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.model.Address;
import com.ecobazar.main.ecommerce.model.Order;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Order createOrder(User user , Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
