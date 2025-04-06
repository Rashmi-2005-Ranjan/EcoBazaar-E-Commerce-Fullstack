package com.ecobazar.main.ecommerce.repositories;

import com.ecobazar.main.ecommerce.model.Cart;
import com.ecobazar.main.ecommerce.model.CartItem;
import com.ecobazar.main.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c FROM CartItem c WHERE c.cart=:cart AND c.product=:product AND c.size = :size AND c.userId = :userId")
    CartItem isCartItemExist(
            @Param("cart") Cart cart
            , @Param("product") Product product
            , @Param("size") String size
            , @Param("userId") Long userId

    );
}
