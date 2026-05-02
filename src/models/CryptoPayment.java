package models;

import core.PaymentMethod;

public class CryptoPayment extends PaymentMethod {
    private String walletAddress;
    public CryptoPayment(String holderName, String walletAddress) {
        super(holderName);
        this.walletAddress = walletAddress;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amount < 10.0) {
            System.out.println("Crypto payment failed: Amount must be at least $10.0");
            return false;
        }
        System.out.println("Processing crypto payment to wallet: " + walletAddress);
        generateReceipt(amount);
        return true;
    }

}
