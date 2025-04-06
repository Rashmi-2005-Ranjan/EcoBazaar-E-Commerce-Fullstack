package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Review;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user)throws ProductException;
    public List<Review> getAllReview(Long productId);
}
