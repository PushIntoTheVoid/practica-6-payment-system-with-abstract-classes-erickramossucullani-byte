import java.util.List;
import core.PaymentMethod;
import models.CreditCard;
import models.PayPal;
import models.CryptoPayment;
import models.CashPayment;
public class Store {
    public static void main(String[] args) throws Exception {
        CreditCard creditCardPayment = new CreditCard("Alice Smith" , "1234-5678-9012-3456");
        PayPal payPalPayment = new PayPal("Bob Johnson" , "bob.johnson@example.com");
        CryptoPayment cryptoValid = new CryptoPayment("Charlie Brown", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        CryptoPayment cryptoInvalid = new CryptoPayment("Charlie Brown", "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        CashPayment cashValid = new CashPayment("Erick Doe");
        CashPayment cashInvalid = new CashPayment("Erick Doe");

        List<PaymentMethod> payments = List.of(creditCardPayment, payPalPayment, cryptoValid, cryptoInvalid, cashValid, cashInvalid);
   
        System.out.println("=== Pago de membresía ===");
        makePayments(List.of(creditCardPayment, payPalPayment), 49.99);

        // Prueba de CryptoPayment
        System.out.println("\n=== Prueba Crypto ===");
        cryptoValid.processPayment(15.0);
        cryptoInvalid.processPayment(5.0);

        // Prueba de CashPayment
        System.out.println("\n=== Prueba Efectivo ===");
        cashValid.processPayment(500.0);
        cashInvalid.processPayment(1500.0);

        // Polimorfismo: lista con todos
        System.out.println("\n=== Procesamiento múltiple ===");
        makePayments(payments, 49.99);  
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
