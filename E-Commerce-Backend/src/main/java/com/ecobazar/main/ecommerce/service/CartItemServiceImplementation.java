package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.CartItemException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.CartItem;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.CartItemRepository;
import com.ecobazar.main.ecommerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity ( 1 );
        cartItem.setPrice ( cartItem.getProduct ( ).getPrice ( ) * cartItem.getQuantity ( ) );
        cartItem.setDiscountedPrice ( cartItem.getProduct ( ).getDiscountedPrice ( ) * cartItem.getQuantity ( ) );

        CartItem ceratedCartItem = cartItemRepository.save ( cartItem );
        return ceratedCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId , Long id , CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById ( id );
        User user = userService.findUserById ( item.getUserId ( ) );
        if (user.getId ( ).equals ( userId )) {
            item.setQuantity ( cartItem.getQuantity ( ) );
            item.setPrice ( item.getQuantity ( ) * item.getProduct ( ).getPrice ( ) );
            item.setDiscountedPrice ( item.getQuantity ( ) * item.getProduct ( ).getDiscountedPrice ( ) );
            CartItem updatedCartItem = cartItemRepository.save ( item );
            return updatedCartItem;
        } else {
            throw new UserException ( "You Can't Update This Cart" );
        }
    }

    @Override
    public CartItem isCartItemExist(Cart cart , Product product , String size , Long userId) {
        CartItem cartItem = cartItemRepository.isCartItemExist ( cart , product , size , userId );
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId , Long cartItemId) throws CartItemException, UserException {
        CartItem item = findCartItemById ( cartItemId );
        User user = userService.findUserById ( item.getUserId ( ) );
        User loggedInUser = userService.findUserById ( userId );
        if (user.getId ( ).equals ( loggedInUser.getId ( ) )) {
            cartItemRepository.delete ( item );
        } else {
            throw new UserException ( "You Can't Remove This Cart" );
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById ( cartItemId );
        if (opt.isPresent ( )) {
            return opt.get ( );
        } else {
            throw new CartItemException ( "Cart Item Not Found" );
        }
    }
}
