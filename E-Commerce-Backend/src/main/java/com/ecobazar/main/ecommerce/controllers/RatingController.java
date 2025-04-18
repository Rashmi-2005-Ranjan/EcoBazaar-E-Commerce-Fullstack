package com.ecobazar.main.ecommerce.controllers;

import com.ecobazar.main.ecommerce.exception.ProductException;
import com.ecobazar.main.ecommerce.exception.UserException;
import com.ecobazar.main.ecommerce.model.Rating;
import com.ecobazar.main.ecommerce.model.User;
import com.ecobazar.main.ecommerce.request.RatingRequest;
import com.ecobazar.main.ecommerce.service.RatingService;
import com.ecobazar.main.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req , @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt ( jwt );
        Rating rating = ratingService.createrating ( req , user );
        return new ResponseEntity<> ( rating , HttpStatus.CREATED );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId , @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt ( jwt );
        List<Rating> ratings = ratingService.getProductsRating ( productId );
        return new ResponseEntity<> ( ratings , HttpStatus.CREATED );
    }
}
