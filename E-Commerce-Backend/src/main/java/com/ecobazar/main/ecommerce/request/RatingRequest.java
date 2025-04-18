package com.ecobazar.main.ecommerce.request;

public class RatingRequest {
    private Long productId;
    private double rating;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public RatingRequest() {
    }

    public RatingRequest(Long productId , double rating) {
        this.productId = productId;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
