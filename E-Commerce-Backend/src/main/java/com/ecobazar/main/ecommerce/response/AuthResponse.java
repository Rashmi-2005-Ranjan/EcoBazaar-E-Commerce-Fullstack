package com.ecobazar.main.ecommerce.response;

public class AuthResponse {
    private String jwt;
    private String message;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthResponse(String jwt , String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public AuthResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
