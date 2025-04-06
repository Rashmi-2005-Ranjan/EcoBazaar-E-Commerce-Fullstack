package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req)throws ProductException;
    public Cart findUserCart(Long userId);
}
