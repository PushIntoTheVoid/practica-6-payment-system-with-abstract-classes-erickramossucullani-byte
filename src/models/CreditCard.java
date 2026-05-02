package models;
 
import core.PaymentMethod;
 
public class CreditCard extends PaymentMethod {
    private String cardNumber;
 
    public CreditCard(String holderName, String cardNumber) {
        super(holderName);
        this.cardNumber = cardNumber;
    }
 
    @Override
    public boolean processPayment(double amount) {
        
        System.out.println("Processing payment with Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
        generateReceipt(amount);
        return true;
    }
}
 