package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.OrderException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Order;
import com.ecobazar.main.ecommerce.repositories.OrderRepository;
import com.ecobazar.main.ecommerce.response.ApiResponse;
import com.ecobazar.main.ecommerce.response.PaymentLinkResponse;
import com.ecobazar.main.ecommerce.service.OrderService;
import com.ecobazar.main.ecommerce.service.UserService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/payments/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable Long orderId ,
            @RequestHeader("Authorization") String jwt
    ) throws RazorpayException, UserException, OrderException {
        Order order = orderService.findOrderById ( orderId );
        try {
            RazorpayClient razorPayClient = new RazorpayClient ( "rzp_test_BWqzCd2257m6X3" , "wTvqWkLHb7O3D1RtZcy6pgDL" );
            JSONObject paymentLinkRequest = new JSONObject ( );
            paymentLinkRequest.put ( "amount" , order.getTotalPrice ( ) * 100 );
            paymentLinkRequest.put ( "currency" , "INR" );

            JSONObject customerDetails = new JSONObject ( );
            customerDetails.put ( "name" , order.getUser ( ).getFirstName ( ) );
            customerDetails.put ( "email" , order.getUser ( ).getEmail ( ) );
            customerDetails.put ( "contact" , order.getUser ( ).getMobile ( ) );

            paymentLinkRequest.put ( "customer" , customerDetails );

            JSONObject notify = new JSONObject ( );
            notify.put ( "sms" , true );
            notify.put ( "email" , true );
            paymentLinkRequest.put ( "notify" , notify );

            paymentLinkRequest.put ( "callback_url" , "http://localhost:4200/payment-success?order_id=" + orderId );
            paymentLinkRequest.put ( "callback_method" , "get" );

            PaymentLink payment = razorPayClient.paymentLink.create ( paymentLinkRequest );
            String paymentLinkId = payment.get ( "id" );
            String paymentLinkUrl = payment.get ( "short_url" );

            PaymentLinkResponse response = new PaymentLinkResponse ( );
            response.setPaymentLinkId ( paymentLinkId );
            response.setPaymentLinkUrl ( paymentLinkUrl );

            return new ResponseEntity<PaymentLinkResponse> ( response , HttpStatus.CREATED );
        } catch (Exception e) {
            throw new RazorpayException ( "Error creating payment link: " + e.getMessage ( ) );
        }
    }

    public ResponseEntity<ApiResponse> updatePayment(@RequestParam(name = "payment_id") String paymentId , @RequestParam(name = "order_id") Long orderId) throws OrderException,RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient ( "rzp_test_BWqzCd2257m6X3" , "wTvqWkLHb7O3D1RtZcy6pgDL" );
        Order order=orderService.findOrderById ( orderId );
        try{
            Payment payment=razorpayClient.payments.fetch ( paymentId );
            if(payment.get ( "status" ).equals ( "captured" )){
                order.setOrderStatus ( "PLACED" );
                order.getPaymentDetails ().setPaymentId ( paymentId );
                order.getPaymentDetails ().setPaymentStatus ( "COMPLETED" );
                orderRepository.save ( order );
            }
            ApiResponse apiResponse = new ApiResponse ();
            apiResponse.setMessage ( "Payment Updated Successfully" );
            apiResponse.setStatus ( true );
            return new ResponseEntity<> ( apiResponse , HttpStatus.OK );
        }catch (Exception e){
            throw new RazorpayException ( "Error updating payment: " + e.getMessage ( ) );
        }
    }
}
