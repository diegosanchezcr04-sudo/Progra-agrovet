
package ui;

/**
 * Product class representing items in the inventory and shopping cart Contains
 * product information and quantity management functionality
 */
class ProductUI {

    private final String emoji;
    private final String name;
    private final String price;
    private int quantity;

    /**
     * Constructor for creating new products
     *
     * @param emoji The emoji representation of the product
     * @param name The display name of the product
     * @param price The formatted price string
     */
    public ProductUI(String emoji, String name, String price) {
        this.emoji = emoji;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    /**
     * Copy constructor for creating product instances from existing products
     *
     * @param other The product to copy
     */
    public ProductUI(ProductUI other) {
        this.emoji = other.emoji;
        this.name = other.name;
        this.price = other.price;
        this.quantity = 1;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity = Math.max(0, quantity - 1);
    }

    /**
     * Converts the price string to a numeric value for calculations
     *
     * @return double representation of the product price
     */
    public double getNumericPrice() {
        return Double.parseDouble(price.replace("$", ""));
    }
}

