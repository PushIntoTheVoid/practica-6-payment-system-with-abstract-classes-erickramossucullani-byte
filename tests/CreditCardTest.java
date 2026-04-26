package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import java.lang.reflect.Modifier;
import models.CreditCard;
import org.junit.jupiter.api.Test;

public class CreditCardTest {

    @Test
    public void creditCardMustExtendPaymentMethod() {
        assertTrue(PaymentMethod.class.isAssignableFrom(CreditCard.class),
                "CreditCard must inherit from PaymentMethod");
    }

    @Test
    public void cardNumberMustBePrivate() throws Exception {
        assertTrue(Modifier.isPrivate(CreditCard.class.getDeclaredField("cardNumber").getModifiers()),
                "cardNumber should be private");
    }

    @Test
    public void constructorMustInitializeInheritedHolderName() {
        CreditCard creditCard = new CreditCard("Juan Perez", "1234567890123456");
        assertEquals("Juan Perez", creditCard.getHolderName());
    }

    @Test
    public void processPaymentMustSucceedAndGenerateReceipt() {
        CreditCard creditCard = new CreditCard("Juan Perez", "1234567890123456");

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> assertTrue(creditCard.processPayment(50.0),
                "CreditCard should accept a normal payment"));

        assertTrue(output.stdout.contains("ending in 3456"),
                "CreditCard should report only the last four digits");
        assertTrue(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Successful credit card payments should generate a receipt");
        assertTrue(output.stdout.contains("Client: Juan Perez"),
                "The receipt should include the holder name");
    }

    @Test
    public void sourceCodeShouldUseOverride() throws Exception {
        assertTrue(TestSupport.readSource("src/models/CreditCard.java").contains("@Override"),
                "CreditCard should use @Override on processPayment");
    }
}
