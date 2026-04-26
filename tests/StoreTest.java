package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import java.lang.reflect.Method;
import java.util.List;
import models.CashPayment;
import models.CreditCard;
import models.CryptoPayment;
import models.PayPal;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    public void makePaymentsMustProcessMixedPaymentMethodsPolymorphically() throws Exception {
        List<PaymentMethod> payments = List.of(
                new CreditCard("Alice Smith", "1234-5678-9012-3456"),
                new PayPal("Bob Johnson", "bob@example.com"),
                new CryptoPayment("Eve Wilson", "wallet-xyz"),
                new CashPayment("Frank Davis"));

        Class<?> storeClass = Class.forName("Store");
        Method makePayments = storeClass.getDeclaredMethod("makePayments", List.class, double.class);

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(
                () -> TestSupport.invokeStore(makePayments, null, payments, 49.99));

        assertEquals(4, TestSupport.countOccurrences(output.stdout, "----- PAYMENT RECEIPT -----"),
                "Store should process all payment methods through the common PaymentMethod API");
        assertTrue(output.stderr.isEmpty(),
                "No error should be printed when all payment methods succeed");
    }

    @Test
    public void makePaymentsMustReportFailuresUsingTheHolderName() throws Exception {
        List<PaymentMethod> payments = List.of(
                new CreditCard("Alice Smith", "1234-5678-9012-3456"),
                new PayPal("Bob Johnson", "bob@example.com"),
                new CryptoPayment("Eve Wilson", "wallet-xyz"),
                new CashPayment("Frank Davis"));

        Class<?> storeClass = Class.forName("Store");
        Method makePayments = storeClass.getDeclaredMethod("makePayments", List.class, double.class);

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(
                () -> TestSupport.invokeStore(makePayments, null, payments, 1500.0));

        assertTrue(output.stderr.contains("Payment failed for: Frank Davis"),
                "Store should report failed payments through the common PaymentMethod API");
    }

    @Test
    public void sourceCodeShouldUsePaymentMethodListForPolymorphicProcessing() throws Exception {
        String source = TestSupport.readSource("src/Store.java");

        assertTrue(source.contains("List<PaymentMethod>"),
                "Store should use a PaymentMethod list to demonstrate polymorphism");
        assertTrue(source.contains("payment.processPayment"),
                "Store should invoke processPayment through the base type reference");
    }
}
