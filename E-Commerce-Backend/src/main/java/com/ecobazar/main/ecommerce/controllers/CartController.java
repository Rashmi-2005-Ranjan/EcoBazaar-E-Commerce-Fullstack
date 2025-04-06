package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.AddItemRequest;
import com.ecobazar.main.ecommerce.response.ApiResponse;
import com.ecobazar.main.ecommerce.service.CartService;
import com.ecobazar.main.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart>findUserCart(@RequestHeader("Authorization") String jwt)throws UserException{
        User user=userService.findUserProfileByJwt ( jwt );
        Cart cart=cartService.findUserCart ( user.getId ( ) );
        return new ResponseEntity<> ( cart, HttpStatus.OK );
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization") String jwt)throws UserException, ProductException{
        User user=userService.findUserProfileByJwt ( jwt );
        cartService.addCartItem ( user.getId (),req );
        ApiResponse apiResponse=new ApiResponse (  );
        apiResponse.setMessage ( "Item Added To Cart Successfully" );
        apiResponse.setStatus ( true );
        return new ResponseEntity<> ( apiResponse, HttpStatus.OK );
    }
}
