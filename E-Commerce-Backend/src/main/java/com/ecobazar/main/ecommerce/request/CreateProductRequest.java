package com.ecobazar.main.ecommerce.request;

import com.ecobazar.main.ecommerce.model.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {
    private String title;
    private String description;
    private int price;
    private Integer discountedPrice;
    private Integer discountPercent;
    private int quantity;
    private String brand;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




    public int getQuantity() {
        return quantity;
    }

    public Integer getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Integer discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Size> getSize() {
        return sizes;
    }

    public void setSize(Set<Size> size) {
        this.sizes = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTopLavelCategory() {
        return topLavelCategory;
    }

    public void setTopLavelCategory(String topLavelCategory) {
        this.topLavelCategory = topLavelCategory;
    }

    public String getSecondLavelCategory() {
        return secondLavelCategory;
    }

    public void setSecondLavelCategory(String secondLavelCategory) {
        this.secondLavelCategory = secondLavelCategory;
    }

    public String getThirdLavelCategory() {
        return thirdLavelCategory;
    }

    public void setThirdLavelCategory(String thirdLavelCategory) {
        this.thirdLavelCategory = thirdLavelCategory;
    }

    private String color;
    private Set<Size> sizes = new HashSet<> ( );
    private String imageUrl;
    private String topLavelCategory;
    private String secondLavelCategory;
    private String thirdLavelCategory;
}
