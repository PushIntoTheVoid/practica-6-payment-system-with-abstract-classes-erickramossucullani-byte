package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import java.lang.reflect.Modifier;
import models.CryptoPayment;
import org.junit.jupiter.api.Test;

public class CryptoPaymentTest {

    @Test
    public void cryptoPaymentMustExtendPaymentMethod() {
        assertTrue(PaymentMethod.class.isAssignableFrom(CryptoPayment.class),
                "CryptoPayment must inherit from PaymentMethod");
    }

    @Test
    public void walletAddressMustBePrivate() throws Exception {
        assertTrue(Modifier.isPrivate(CryptoPayment.class.getDeclaredField("walletAddress").getModifiers()),
                "walletAddress should be private");
    }

    @Test
    public void constructorMustInitializeInheritedHolderName() {
        CryptoPayment crypto = new CryptoPayment("Satoshi", "wallet-001");
        assertEquals("Satoshi", crypto.getHolderName());
    }

    @Test
    public void processPaymentMustRejectAmountsBelowTen() {
        CryptoPayment crypto = new CryptoPayment("Satoshi", "wallet-001");

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> assertFalse(crypto.processPayment(9.99),
                "Crypto payment must fail if amount is below 10.0"));

        assertTrue(output.stdout.contains("Crypto payment failed: Amount must be at least $10.0"),
                "Crypto failure message should explain the minimum amount rule");
        assertFalse(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Crypto failure should not generate a receipt");
    }

    @Test
    public void processPaymentMustAcceptTenOrMoreAndGenerateReceipt() {
        CryptoPayment crypto = new CryptoPayment("Satoshi", "wallet-001");

        TestSupport.CapturedResult exactLimitOutput = TestSupport.captureSystemOutput(() -> assertTrue(crypto.processPayment(10.0),
                "Crypto payment must succeed if amount is exactly 10.0"));
        assertTrue(exactLimitOutput.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Successful crypto payment should generate a receipt at the exact limit");

        TestSupport.CapturedResult higherAmountOutput = TestSupport.captureSystemOutput(() -> assertTrue(crypto.processPayment(15.0),
                "Crypto payment must succeed if amount is above 10.0"));
        assertTrue(higherAmountOutput.stdout.contains("wallet-001"),
                "Successful crypto payment should include the wallet address");
    }

    @Test
    public void sourceCodeShouldUseOverride() throws Exception {
        assertTrue(TestSupport.readSource("src/models/CryptoPayment.java").contains("@Override"),
                "CryptoPayment should use @Override on processPayment");
    }
}
