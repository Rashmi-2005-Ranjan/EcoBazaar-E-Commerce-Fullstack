package com.ecobazar.main.ecommerce.model;

public class Size {
    private String name;
    private int quantity;

    public String getName() {
        return name;
    }

    public Size() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
