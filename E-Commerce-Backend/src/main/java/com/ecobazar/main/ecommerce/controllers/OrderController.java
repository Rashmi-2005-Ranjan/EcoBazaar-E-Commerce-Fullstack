package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Address;
import com.ecobazar.main.ecommerce.model.Order;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.service.OrderService;
import com.ecobazar.main.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress , @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt ( jwt );
        Order order = orderService.createOrder ( user , shippingAddress );
        return new ResponseEntity<> ( order , HttpStatus.CREATED );
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt ( jwt );
        List<Order> orders = orderService.usersOrderHistory ( user.getId ( ) );
        return new ResponseEntity<> ( orders , HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(
            @PathVariable("id") Long orderId ,
            @RequestHeader("Authorization") String jwt
    ) throws UserException, OrderException {
        User user = userService.findUserProfileByJwt ( jwt );
        Order order = orderService.findOrderById ( orderId );
        return new ResponseEntity<> ( order , HttpStatus.ACCEPTED );
    }
}
