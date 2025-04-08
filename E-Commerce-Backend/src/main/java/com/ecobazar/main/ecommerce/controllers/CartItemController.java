package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.CartItemException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.CartItem;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.response.ApiResponse;
import com.ecobazar.main.ecommerce.service.CartItemService;
import com.ecobazar.main.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserService userService;
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user=userService.findUserProfileByJwt ( jwt );
        cartItemService.removeCartItem ( user.getId (),cartItemId );
        ApiResponse apiResponse=new ApiResponse (  );
        apiResponse.setMessage ( "Cart Item Deleted Successfully" );
        apiResponse.setStatus ( true );
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{cartItemId}/")
    public ResponseEntity<ApiResponse>updateCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user=userService.findUserProfileByJwt ( jwt );
        CartItem cartItem=cartItemService.findCartItemById ( cartItemId );
        cartItemService.updateCartItem ( user.getId (),cartItemId,cartItem );
        ApiResponse apiResponse=new ApiResponse (  );
        apiResponse.setMessage ( "Cart Item Updated Successfully" );
        apiResponse.setStatus ( true );
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
