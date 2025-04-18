package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.CartItem;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.CartRepository;
import com.ecobazar.main.ecommerce.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart ( );
        cart.setUser ( user );
        return cartRepository.save ( cart );
    }

    @Override
    public String addCartItem(Long userId , AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId ( userId );
        Product product = productService.getProductById ( req.getProductId ( ) );
        CartItem isPresent = cartItemService.isCartItemExist ( cart , product , req.getSize ( ) , userId );
        if (isPresent == null) {
            CartItem cartItem = new CartItem ( );
            cartItem.setProduct ( product );
            cartItem.setCart ( cart );
            cartItem.setQuantity ( req.getQuantity ( ) );
            cartItem.setUserId ( userId );

            int price = req.getQuantity ( ) * product.getDiscountedPrice ( );
            cartItem.setPrice ( price );
            cartItem.setSize ( req.getSize ( ) );

            CartItem createdCartItem = cartItemService.createCartItem ( cartItem );
            cart.getCartItems ( ).add ( createdCartItem );
        }
        return "Item added to cart successfully";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId ( userId );
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totlalItem = 0;
        for (CartItem cartItem : cart.getCartItems ( )) {
            totalPrice = totalPrice + cartItem.getPrice ( );
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getProduct ( ).getDiscountedPrice ( ) * cartItem.getQuantity ( );
            totlalItem = totlalItem + cartItem.getQuantity ( );
        }
        cart.setTotalPrice ( totalPrice );
        cart.setTotalDiscountedPrice ( totalDiscountedPrice );
        cart.setTotalItem ( totlalItem );
        cart.setDiscount ( totalPrice - totalDiscountedPrice );
        return cartRepository.save ( cart );
    }
}
