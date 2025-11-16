package logic;

import java.util.Date;

public class Payment {

    private String paymentId;

    private float amount;

    private String method;

    private Date paymenDate;

    public boolean applyPayment(Payment payment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getRemainingBalance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
