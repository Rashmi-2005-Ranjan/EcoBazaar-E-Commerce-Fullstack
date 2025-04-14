package com.ecobazar.main.ecommerce.response;

public class PaymentLinkResponse {
    private String paymentLinkId;
    private String paymentLinkUrl;

    public PaymentLinkResponse(String paymentLinkId, String paymentLinkUrl) {
        this.paymentLinkId = paymentLinkId;
        this.paymentLinkUrl = paymentLinkUrl;
    }

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public PaymentLinkResponse() {
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }

    public String getPaymentLinkUrl() {
        return paymentLinkUrl;
    }

    public void setPaymentLinkUrl(String paymentLinkUrl) {
        this.paymentLinkUrl = paymentLinkUrl;
    }
}
