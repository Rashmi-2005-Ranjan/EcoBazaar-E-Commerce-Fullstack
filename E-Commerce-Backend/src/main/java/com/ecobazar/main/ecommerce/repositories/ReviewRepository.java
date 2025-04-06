package com.ecobazar.main.ecommerce.repositories;

import com.ecobazar.main.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> getAllProductsReview(@Param ( "productId" )Long productId);
}
