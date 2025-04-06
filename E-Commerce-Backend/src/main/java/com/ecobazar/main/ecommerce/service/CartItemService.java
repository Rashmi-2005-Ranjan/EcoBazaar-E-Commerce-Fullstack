package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.CartItemException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.CartItem;
import com.ecobazar.main.ecommerce.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId , Long id , CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product,String size,Long userId);

    public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
