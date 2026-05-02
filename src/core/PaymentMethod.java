package core;
 
public abstract class PaymentMethod {
    private String holderName;
 
    public PaymentMethod(String holderName) {
        this.holderName = holderName;
    }
 
    public String getHolderName() {
        return holderName;
    }
 
    
    public void generateReceipt(double amount) {
        System.out.println("----- PAYMENT RECEIPT -----");
        System.out.println("Client: " + holderName);
        System.out.println("Amount charged: $" + amount);
        System.out.println("--------------------------");
    }
 
    
    public abstract boolean processPayment(double amount);
}