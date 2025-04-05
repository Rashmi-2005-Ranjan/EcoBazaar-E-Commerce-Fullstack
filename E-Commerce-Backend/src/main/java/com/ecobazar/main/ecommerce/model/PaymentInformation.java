package com.ecobazar.main.ecommerce.model;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class PaymentInformation {
    @Column
    private String cardHolderName;
    @Column
    private String cardNumber;
    @Column
    private LocalDate expirationDate;
    @Column
    private String ccv;
}
