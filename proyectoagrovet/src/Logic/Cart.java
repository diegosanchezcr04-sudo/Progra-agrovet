package logic;

import java.util.Date;

public class Cart {

    private String cartId;

    private Customer customer;

    private Date createdDate;

    private double discount;

    private String discountType;

    public double calculateTax(double taxRate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean checkout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
