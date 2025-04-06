package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.Review;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.ProductRepository;
import com.ecobazar.main.ecommerce.repositories.ReviewRepository;
import com.ecobazar.main.ecommerce.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Review createReview(ReviewRequest req , User user) throws ProductException {
        Product product = productService.getProductById ( req.getProductId ( ) );
        Review review = new Review ( );
        review.setUser ( user );
        review.setProduct ( product );
        review.setReview ( req.getReview ( ) );
        review.setCreatedAt ( LocalDateTime.now ( ) );
        return reviewRepository.save ( review );
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview ( productId );
    }
}
