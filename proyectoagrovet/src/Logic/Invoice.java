package logic;

import java.util.Date;

/**
 * Represent the invoice sell in agrovet POS
 *
 * @author Leo Aguilar
 */
public class Invoice {

    public static Invoice fromFileLine(String line) {

        return null;

    }
    private final float subtotal;
    private final float total;
    private final short itemCount;

    private final String number;
    private final Date issueDate;
    private final Customer customer;
    private final Employee cashier;
    private final CartItem[] items;
    private Status status;

    /**
     * Initialize the invoice atributes
     *
     * @param customer
     * @param cashier
     * @param cart
     */
    public Invoice(Customer customer, Employee cashier, Cart cart) {
        this.number = generateInvoiceNumber();
        this.issueDate = new Date();
        this.customer = customer;
        this.cashier = cashier;
        this.status = Status.PENDING;

        this.items = new CartItem[cart.getItemCount()];
        CartItem[] cartItems = cart.getItems();
        for (short i = 0; i < cartItems.length; i++) {
            this.items[i] = cartItems[i];
        }
        this.itemCount = (short) cart.getItemCount();
        this.subtotal = (float) cart.getSubtotal();
        this.total = (float) cart.getTotal();
    }

    public float getSubtotal() {
        return subtotal;
    }

    public float getTotal() {
        return total;
    }

    public short getItemCount() {
        return itemCount;
    }

    public String getNumber() {
        return number;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getCashier() {
        return cashier;
    }

    public CartItem[] getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Autogenerate the invoice´s number
     *
     * @return
     */
    private String generateInvoiceNumber() {
        return "Invoice-" + System.currentTimeMillis();
    }

    /**
     * Gets Invoice details
     *
     * @return details
     */
    public String getInvoiceDetails() {
        String details = "";
        details += "INVOICE #" + number + "\n";
        details += "Customer: " + customer.getName() + "\n";
        details += "Cashier: " + cashier.getName() + "\n";
        details += "Date: " + issueDate + "\n";
        details += "Items: " + itemCount + "\n\n";

        for (int i = 0; i < itemCount; i++) {
            details += items[i].getItemInfo() + "\n";
        }
        details += "\nSUBTOTAL: $" + subtotal;
        details += "\nTOTAL: $" + total;
        details += "\nSTATUS: " + status;

        return details;
    }

    /**
     * Mark the invoice as paid
     */
    public void paid() {
        this.status = Status.PAID;
    }

    /**
     * Turns the invoice´s detains in file.txt format
     *
     * @return
     */
    public String toFileLine() {
        return number + ";" + issueDate.getTime() + ";" + customer.getId() + ";"
                + cashier.getId() + ";" + subtotal + ";" + total + ";" + status;
    }

    /**
     * Represent invoice status
     */
    public enum Status {
        PENDING, PAID, CANCELLED
    }
}
