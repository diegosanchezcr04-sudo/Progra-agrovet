package logic;

/**
 * Represent the cart item complemented with cart for manege his funcionalities
 *
 * @author Leo Aguilar
 */
public class CartItem {

    private final Product product;
    private int quantity;
    private final double unitPrice;
    private double itemTotal;

    /**
     * Initialize the cartItem atributies for complement with Cart
     *
     * @param product
     * @param quantity
     * @param unitPrice
     */
    public CartItem(Product product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.itemTotal = unitPrice * quantity;
    }

    /**
     * Set the items quantity
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.itemTotal = unitPrice * quantity;
    }

    /**
     * Gets the items information
     *
     * @return
     */
    public String getItemInfo() {
        return product.getName() + " x " + quantity + " - " + unitPrice
                + " c/u - " + itemTotal + " total";
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getItemTotal() {
        return itemTotal;
    }

}
