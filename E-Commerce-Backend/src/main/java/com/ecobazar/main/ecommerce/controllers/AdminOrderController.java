package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.model.Order;
import com.ecobazar.main.ecommerce.response.ApiResponse;
import com.ecobazar.main.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    // Add methods to handle admin order operations here
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrdersHandler() {
        List<Order> orders = orderService.getAllOrders ( );
        return new ResponseEntity<> ( orders , HttpStatus.ACCEPTED );
    }

    //Used For Updating Order Status
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> ConfirmedOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.confirmOrder ( orderId );
        return new ResponseEntity<> ( order , HttpStatus.OK );
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.shippedOrder ( orderId );
        return new ResponseEntity<> ( order , HttpStatus.OK );
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliverOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.deliveredOrder ( orderId );
        return new ResponseEntity<> ( order , HttpStatus.OK );
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        Order order = orderService.cancelOrder ( orderId );
        return new ResponseEntity<> ( order , HttpStatus.OK );
    }

    @DeleteMapping("{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId , @RequestHeader("Authorization") String jwt) throws OrderException {
        orderService.deleteOrder ( orderId );
        ApiResponse apiResponse = new ApiResponse ( );
        apiResponse.setMessage ( "Order Deleted Successfully" );
        apiResponse.setStatus ( true );
        return new ResponseEntity<> ( apiResponse , HttpStatus.OK );
    }
}
