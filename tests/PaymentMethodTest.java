package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.PaymentMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;

public class PaymentMethodTest {

    @Test
    public void paymentMethodMustBeAbstract() {
        assertTrue(Modifier.isAbstract(PaymentMethod.class.getModifiers()),
                "PaymentMethod should be declared as abstract");
    }

    @Test
    public void processPaymentMustBeAbstract() throws Exception {
        Method processPayment = PaymentMethod.class.getDeclaredMethod("processPayment", double.class);
        assertTrue(Modifier.isAbstract(processPayment.getModifiers()),
                "processPayment(double) must remain abstract in PaymentMethod");
    }

    @Test
    public void holderNameMustBePrivate() throws Exception {
        Field holderName = PaymentMethod.class.getDeclaredField("holderName");
        assertTrue(Modifier.isPrivate(holderName.getModifiers()),
                "holderName should be private to preserve encapsulation");
    }

    @Test
    public void constructorMustInitializeHolderNameAndGetterMustExposeIt() {
        PaymentMethod paymentMethod = new PaymentMethod("Laura Diaz") {
            @Override
            public boolean processPayment(double amount) {
                return true;
            }
        };

        assertEquals("Laura Diaz", paymentMethod.getHolderName(),
                "The inherited getter should return the holder name initialized by the constructor");
    }

    @Test
    public void generateReceiptMustPrintSharedReceiptFormat() {
        PaymentMethod paymentMethod = new PaymentMethod("Laura Diaz") {
            @Override
            public boolean processPayment(double amount) {
                return true;
            }
        };

        TestSupport.CapturedResult output = TestSupport.captureSystemOutput(() -> paymentMethod.generateReceipt(42.5));

        assertTrue(output.stdout.contains("----- PAYMENT RECEIPT -----"),
                "Receipt header should be printed");
        assertTrue(output.stdout.contains("Client: Laura Diaz"),
                "Receipt should contain the holder name");
        assertTrue(output.stdout.contains("Amount charged: $42.5"),
                "Receipt should contain the amount");
    }
}
