package ui;
import javax.swing.*;
import java.awt.*;
import logic.*;


import java.util.Date;

public class CartUI extends JFrame {

    private Cart cart;

    private JTextField txtProductId;
    private JTextField txtQuantity;
    private JButton btnAdd;
    private JButton btnClear;

    private JTextArea txtItems;
    private JLabel lblSummary;

    public CartUI(Cart cart) {
        this.cart = cart;

        setTitle("Cart Manager");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Add Product"));

        topPanel.add(new JLabel("Product ID:"));
        txtProductId = new JTextField();
        topPanel.add(txtProductId);

        topPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        topPanel.add(txtQuantity);

        btnAdd = new JButton("Add to Cart");
        topPanel.add(btnAdd);

        btnClear = new JButton("Clear Cart");
        topPanel.add(btnClear);

        add(topPanel, BorderLayout.NORTH);

        txtItems = new JTextArea();
        txtItems.setEditable(false);
        add(new JScrollPane(txtItems), BorderLayout.CENTER);

        lblSummary = new JLabel("Summary: ");
        add(lblSummary, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addProduct());
        btnClear.addActionListener(e -> clearCart());

        updateUIData();
    }

    private void addProduct() {
        try {
            String id = txtProductId.getText().trim();
            int qty = Integer.parseInt(txtQuantity.getText().trim());

            // TODO: replace with real lookup once you provide Product constructor
// Temporary product creation using full constructor (replace with real lookup)
Product p = new Product(
        0f,        // discount
        10f,       // price
        0f,        // tax
        0,         // barCode
        (short) 1, // quantity
        new Date(),// expiration
        "Temp description", // description
        id,        // id
        "Temp Product", // name
        Product.Category.PETS, // default category
        Product.Type.PET_TOY   // default type
); // REPLACE with real lookup

            cart.addProduct(p, qty);
            updateUIData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearCart() {
        cart.clear();
        updateUIData();
    }

    private void updateUIData() {
        StringBuilder sb = new StringBuilder();

        for (CartItem item : cart.getItems()) {
            sb.append(item.getProduct().getId())
              .append(" | Qty: ")
              .append(item.getQuantity())
              .append(" | Total: ")
              .append(item.getItemTotal())
              .append("\n");
        }

        txtItems.setText(sb.toString());
        lblSummary.setText(cart.getCartSummary());
    }

    public static void main(String[] args) {
        Cart c = new Cart(null);
        new CartUI(c).setVisible(true);
    }
}
