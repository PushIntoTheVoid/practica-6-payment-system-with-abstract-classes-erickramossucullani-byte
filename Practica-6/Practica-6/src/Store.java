import java.util.List;
import core.PaymentMethod;
import models.CreditCard;
import models.PayPal;
import models.CashPayment;
import models.CryptoPayment;

public class Store {
    public static void main(String[] args) throws Exception {
        CreditCard creditCardPayment = new CreditCard("Alice Smith" , "1234-5678-9012-3456");
        PayPal payPalPayment = new PayPal("Bob Johnson" , "bob.johnson@example.com");
        CryptoPayment cryptoPayment = new CryptoPayment("Charlie Brown", "1A2b2uyg4yf3847B3984f757");
        CashPayment cashPayment = new CashPayment("Erick Doe");
        List<PaymentMethod> payments = List.of(creditCardPayment, payPalPayment, cryptoPayment, cashPayment);

        double membershipAmount  = 49.99;
        makePayments(payments, membershipAmount);     
    }

    public static void makePayments(List<PaymentMethod> payments, double membershipAmount) {
        for (PaymentMethod payment : payments) {
            boolean success = payment.processPayment(membershipAmount);
            if (!success) {
                System.err.println("Payment failed for: " + payment.getHolderName());
            }
        }
    }

}
