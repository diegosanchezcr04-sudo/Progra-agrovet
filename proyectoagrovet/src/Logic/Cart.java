package logic;

import java.util.Date;

/**
 * Represent the cart in agrovet POS
 * @author leona
 */
public class Cart {

    private final Stringlogic id;
    private final Date createdDate;
    private final CartItem[] items;
    private int itemCount;
    private double subtotal;
    private double total;
    private boolean active;
    
    /**+
     * Initizlize the Cart atributes
     * @param customer 
     */
    public Cart(Customer customer) {
        this.items = new CartItem[50];
        this.itemCount = 0;
        this.createdDate = new Date();
        this.active = true;
        this.id = generateCartId();
    }

    
    /**
     * Adds a product to the cart or updates quantity if already exists
     *
     * @param product
     * @param quantity
     */
    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0 || itemCount >= items.length) {
            throw new IllegalArgumentException("Datos inválidos o carrito lleno");
        }

        for (int i = 0; i < itemCount; i++) {
            if (items[i].getProduct().getId().equals(product.getId())) {
                items[i].setQuantity(items[i].getQuantity() + quantity);
                calculateTotal();
                return;
            }
        }

        items[itemCount] = new CartItem(product, quantity,
                product.getFinalPrice());
        itemCount++;
        calculateTotal();
    }

    /**
     * Calculates the total amount in each sell
     */
    private void calculateTotal() {
        this.subtotal = 0;

        for (int i = 0; i < itemCount; i++) {

            this.subtotal += items[i].getItemTotal();
        }

        this.total = subtotal;
    }

    /**
     * Crear totaly cart
     */
    public void clear() {
        for (int i = 0; i < itemCount; i++) {
            items[i] = null;
        }
        itemCount = 0;
        subtotal = 0;
        total = 0;
    }

    /**
     * Verify if the cart is empty
     *
     * @return itemCount in 0
     */
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * Gets the total quantity of items in cart
     *
     * @return total items
     */
    public int getTotalItems() {
        int totalItems = 0;
        for (int i = 0; i < itemCount; i++) {
            totalItems += items[i].getQuantity();
        }
        return totalItems;
    }

    /**
     * Gets all cart items (array opy)
     *
     * @return array copy
     */
    public CartItem[] getItems() {
        CartItem[] copy = new CartItem[itemCount];
        System.arraycopy(items, 0, copy, 0, itemCount);
        return copy;
    }

    private String generateCartId() {
        return "CART_" + System.currentTimeMillis() + "_"
                + (int) (Math.random() * 1000);
    }

    /**
     * After to finish purchase, close cart
     */
    public void close() {
        this.active = false;
    }

    /**
     * Gets the cart information for show it in POS.
     *
     * @return cart info
     */
    public String getCartSummary() {
        return "Carrito: " + getTotalItems() + " items | Subtotal: $" + subtotal
                + " | Total: $" + total;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public boolean isActive() {
        return active;
    }

}
