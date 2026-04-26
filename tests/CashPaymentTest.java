package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import models.CashPayment;
import org.junit.jupiter.api.Test;

public class CashPaymentTest {

    @Test
    public void cashPaymentMustExtendPaymentMethod() {
        assertTrue(PaymentMethod.class.isAssignableFrom(CashPayment.class),
                "CashPayment must inherit from PaymentMethod");
    }

    @Test
    public void cashPaymentShouldNotDeclareExtraAttributes() {
        assertEquals(0, CashPayment.class.getDeclaredFields().length,
                "CashPayment should not need extra state beyond the inherited holder name");
    }

    @Test
    public void constructorMustInitializeInheritedHolderName() {
        CashPayment cash = new CashPayment("Carlos Lopez");
        assertEquals("Carlos Lopez", cash.getHolderName());
    }

    @Test
    public void processPaymentMustAcceptAmountsUpToTheLimitAndGenerateReceipt() {
        CashPayment cash = new CashPayment("Carlos Lopez");

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> assertTrue(cash.processPayment(1000.0),
                "Cash payment must accept the exact limit"));

        assertTrue(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Successful cash payment should generate a receipt");
        assertTrue(output.stdout.contains("Processing cash payment for: Carlos Lopez"),
                "Successful cash payment should identify the holder");
    }

    @Test
    public void processPaymentMustRejectAmountsOverTheLimitWithoutReceipt() {
        CashPayment cash = new CashPayment("Carlos Lopez");

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> assertFalse(cash.processPayment(1000.01),
                "Cash payment must reject amounts above the limit"));

        assertTrue(output.stdout.contains("Cash payment failed: Amount exceeds the limit of $1000."),
                "Cash failure message should explain the allowed limit");
        assertFalse(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Rejected cash payment should not generate a receipt");
    }

    @Test
    public void sourceCodeShouldUseOverride() throws Exception {
        assertTrue(TestSupport.readSource("src/models/CashPayment.java").contains("@Override"),
                "CashPayment should use @Override on processPayment");
    }
}
