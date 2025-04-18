package com.ecobazar.main.ecommerce.repositories;

import com.ecobazar.main.ecommerce.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    // Custom query methods can be defined here if needed
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating>getAllProductsRating(@Param ( "productId" )Long productId);
}
