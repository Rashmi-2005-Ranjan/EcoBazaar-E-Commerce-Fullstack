package com.ecobazar.main.ecommerce.service;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.model.Rating;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createrating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}

