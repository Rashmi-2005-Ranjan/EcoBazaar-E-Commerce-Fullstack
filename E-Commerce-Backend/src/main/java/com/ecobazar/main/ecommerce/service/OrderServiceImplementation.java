package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.model.*;
import com.ecobazar.main.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order createOrder(User user , Address shippingAddress) {
        shippingAddress.setUser ( user );
        Address address = addressRepository.save ( shippingAddress );
        user.getAddresses ( ).add ( address );
        userRepository.save ( user );

        Cart cart = cartService.findUserCart ( user.getId ( ) );
        List<OrderItem> orderItems = new ArrayList<> ( );

        for (CartItem item : cart.getCartItems ( )) {
            OrderItem orderItem = new OrderItem ( );
            orderItem.setPrice ( item.getPrice ( ) );
            orderItem.setProduct ( item.getProduct ( ) );
            orderItem.setQuantity ( item.getQuantity ( ) );
            orderItem.setSize ( item.getSize ( ) );
            orderItem.setUserId ( item.getUserId ( ) );
            orderItem.setDiscountedPrice ( item.getDiscountedPrice ( ) );

            OrderItem createdOrderItem = orderItemRepository.save ( orderItem );
            orderItems.add ( createdOrderItem );
        }

        Order createdOrder = new Order ( );
        createdOrder.setUser ( user );
        createdOrder.setOrderItems ( orderItems );
        createdOrder.setTotalPrice ( cart.getTotalPrice ( ) );
        createdOrder.setTotalDiscountedPrice ( cart.getTotalDiscountedPrice ( ) );
        createdOrder.setDiscount ( cart.getDiscount ( ) );
        createdOrder.setTotalItem ( cart.getTotalItem ( ) );
        createdOrder.setShippingAddress ( address );
        createdOrder.setOrderDate ( LocalDateTime.now ( ) );
        createdOrder.setOrderStatus ( "PENDING" );
        createdOrder.getPaymentDetails ( ).setPaymentStatus ( "PENDING" );

        Order savedOrder = orderRepository.save ( createdOrder );

        for (OrderItem item : orderItems) {
            item.setOrder ( savedOrder );
            orderItemRepository.save ( item );
        }

        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt = orderRepository.findById ( orderId );
        if (opt.isPresent ( )) {
            return opt.get ( );
        } else {
            throw new OrderException ( "Order Not Found" );
        }

    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders=orderRepository.getUsersOrders ( userId );
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById ( orderId );
        order.setOrderStatus ( "PLACED" );
        order.getPaymentDetails ( ).setPaymentStatus ( "COMPLETED" );
        return order;
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        Order order = findOrderById ( orderId );
        order.setOrderStatus ( "CONFIRMED" );
        return orderRepository.save ( order );
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById ( orderId );
        order.setOrderStatus ( "SHIPPED" );
        return orderRepository.save ( order );
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById ( orderId );
        order.setOrderStatus ( "DELIVERED" );
        return orderRepository.save ( order );
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        Order order = findOrderById ( orderId );
        order.setOrderStatus ( "CANCELLED" );
        return orderRepository.save ( order );
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll ();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order=orderRepository.findById ( orderId ).orElseThrow ( ()->new OrderException ( "Order Not Found" ) );
        orderRepository.deleteById ( orderId );
    }
}
