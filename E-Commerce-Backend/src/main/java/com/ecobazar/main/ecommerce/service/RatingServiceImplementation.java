package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Product;
import com.ecobazar.main.ecommerce.model.Rating;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.repositories.RatingRepository;
import com.ecobazar.main.ecommerce.request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Rating createrating(RatingRequest req , User user) throws ProductException {
        Product product = productService.getProductById ( req.getProductId ( ) );
        Rating rating = new Rating ( );
        rating.setProduct ( product );
        rating.setUser ( user );
        rating.setRating ( req.getRating ( ) );
        rating.setCreatedAt ( LocalDateTime.now ( ) );
        return ratingRepository.save ( rating );
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating ( productId );
    }
}
