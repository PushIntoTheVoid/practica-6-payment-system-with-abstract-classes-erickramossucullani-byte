package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import java.lang.reflect.Modifier;
import models.PayPal;
import org.junit.jupiter.api.Test;

public class PayPalTest {

    @Test
    public void payPalMustExtendPaymentMethod() {
        assertTrue(PaymentMethod.class.isAssignableFrom(PayPal.class),
                "PayPal must inherit from PaymentMethod");
    }

    @Test
    public void emailMustBePrivate() throws Exception {
        assertTrue(Modifier.isPrivate(PayPal.class.getDeclaredField("email").getModifiers()),
                "email should be private");
    }

    @Test
    public void constructorMustInitializeInheritedHolderName() {
        PayPal payPal = new PayPal("Ana Gomez", "ana@usfx.bo");
        assertEquals("Ana Gomez", payPal.getHolderName());
    }

    @Test
    public void processPaymentMustSucceedAndGenerateReceipt() {
        PayPal payPal = new PayPal("Ana Gomez", "ana@usfx.bo");

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> assertTrue(payPal.processPayment(25.0),
                "PayPal should accept a normal payment"));

        assertTrue(output.stdout.contains("ana@usfx.bo"),
                "PayPal should report the account email used for payment");
        assertTrue(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Successful PayPal payments should generate a receipt");
        assertTrue(output.stdout.contains("Client: Ana Gomez"),
                "The receipt should include the holder name");
    }

    @Test
    public void sourceCodeShouldUseOverride() throws Exception {
        assertTrue(TestSupport.readSource("src/models/PayPal.java").contains("@Override"),
                "PayPal should use @Override on processPayment");
    }
}
