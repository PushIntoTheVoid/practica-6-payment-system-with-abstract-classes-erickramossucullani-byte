package models;

import core.PaymentMethod;

public class PayPal extends PaymentMethod {
    private String email;

    public PayPal(String holderName, String email){
        super(holderName);
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount){
        System.out.println("Collecting payment via PayPal to the account: " + email);
        generateReceipt(amount);
        return true;
    }
    
}

