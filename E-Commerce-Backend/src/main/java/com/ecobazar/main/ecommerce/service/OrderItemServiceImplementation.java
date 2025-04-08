package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.model.OrderItem;
import com.ecobazar.main.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImplementation implements OrderItemService{
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save ( orderItem );
    }
}
