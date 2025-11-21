package logic;

import java.util.Date;

/**
 * Represente the payment process
 *
 * @author Leo Aguilar
 */
public class Payment {

    private final float amount;
    private final String id;
    private final Date date;
    private final Method method;
    private final String reference;
    private Status status;

    /**
     * Represent all diferent payment methods
     */
    public enum Method {
        CASH, CARD, TRANSFER, CHECK
    }

    /**
     * Represent the payment status
     */
    public enum Status {
        PENDING, COMPLETED, FAILED, REFUNDED
    }

    /**
     * Initialize the Payment's atributes
     *
     * @param amount
     * @param method
     * @param reference
     */
    public Payment(float amount, Method method, String reference) {
        this.id = "Pay-" + System.currentTimeMillis();
        this.date = new Date();
        this.amount = amount;
        this.method = method;
        this.reference = reference;
        this.status = Status.PENDING;

    }

    public String getPaymentId() {
        return id;
    }

    public Date getPaymentDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public Method getMethod() {
        return method;
    }

    public String getReference() {
        return reference;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Simulate the payment process Validate if the paymend is completed or
     * failed
     *
     * @return paymend status
     */
    public boolean ProcessPayment() {

        if (amount > 0) {
            this.status = Status.COMPLETED;
            return true;
        }

        this.status = Status.FAILED;
        return false;
    }

    /**
     * Validate if paymend was successfully
     *
     * @return paymend completed
     */
    public boolean isSuccessful() {
        return status == Status.COMPLETED;
    }

    /**
     * Gets the paymend information for register it on invoice
     *
     * @return paymend information
     */
    public String getPaymentInfo() {
        return "Payment: " + id + " ; " + amount + " ; " + method + " ; "
                + status;
    }
}
