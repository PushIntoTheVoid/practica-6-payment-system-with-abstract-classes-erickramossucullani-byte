package models;


import core.PaymentMethod;

public class CashPayment extends PaymentMethod {
    public CashPayment(String holderName) {
        super(holderName);
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount > 1000) {
            System.out.println("Cash payment failed: Amount exceeds the limit of $1000.");
            return false;
        }
        System.out.println("Processing cash payment for: " + getHolderName());
        generateReceipt(amount);
        return true;
    }
   
}

