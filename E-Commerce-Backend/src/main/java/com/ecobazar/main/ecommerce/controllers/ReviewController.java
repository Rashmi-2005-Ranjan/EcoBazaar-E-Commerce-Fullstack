package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Review;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.ReviewRequest;
import com.ecobazar.main.ecommerce.service.ReviewService;
import com.ecobazar.main.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req , @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt ( jwt );
        Review review = reviewService.createReview ( req , user );
        return new ResponseEntity<> ( review , HttpStatus.CREATED );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>>getProductsReview(@PathVariable Long productId)throws UserException,ProductException{
        List<Review> reviews=reviewService.getAllReview ( productId );
        return new ResponseEntity<> ( reviews , HttpStatus.ACCEPTED );
    }
}
