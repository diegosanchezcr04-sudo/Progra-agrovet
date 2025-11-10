package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * AgroVet POS for Touch Screen Application
 *
 * This point-of-sale system designed specifically for veterinary and
 * agricultural stores with touch screen optimization for 15-inch displays.
 * product catalog, shopping cart, real-time calculations, and intuitive touch
 * interface.
 *
 * @author Leo Aguilar
 * @version 1.0
 */
public class TouchScreen extends JFrame {

    /**
     * Primary brand color for buttons and highlights
     */
    private final Color primaryColor = new Color(168, 216, 185);

    /**
     * Background color for main application area
     */
    private final Color backgroundColor = new Color(245, 245, 245);

    /**
     * Surface color for side panels and cards
     */
    private final Color surfaceColor = new Color(253, 251, 246);

    /**
     * Primary text color for headings and important text
     */
    private final Color textPrimaryColor = new Color(74, 85, 104);

    /**
     * Secondary text color for less important information
     */
    private final Color textSecondaryColor = new Color(160, 174, 192);

    /**
     * List to store products added to shopping cart
     */
    private final List<ProductUI> cartProducts = new ArrayList<>();

    /**
     * Panel containing the shopping cart items
     */
    private JPanel cartPanel;

    /**
     * Labels for displaying price calculations
     */
    private JLabel subtotalLabel, taxLabel, totalLabel;

    /**
     * Panel containing the product grid
     */
    private JPanel productsPanel;

    /**
     * Height for touch-friendly buttons
     */
    private final int BUTTON_HEIGHT = 70;

    /**
     * Font size for section titles
     */
    private final int FONT_SIZE_TITLE = 28;

    /**
     * Font size for normal text and buttons
     */
    private final int FONT_SIZE_NORMAL = 18;

    /**
     * Font size for secondary text and prices
     */
    private final int FONT_SIZE_SMALL = 16;

    /**
     * Margin size for touch-friendly spacing
     */
    private final int TOUCH_MARGIN = 15;

    /**
     * Main constructor for AgroVet POS application Initializes the main window
     * and all UI components
     */
    public TouchScreen() {

        setTitle("Agro-Vet POS - Touch Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setupTouchOptimizedUI();
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Configures global UI settings for touch screen optimization Sets larger
     * fonts, appropriate spacing, and touch-friendly defaults
     */
    private void setupTouchOptimizedUI() {

        try {

            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD,
                    FONT_SIZE_NORMAL));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN,
                    FONT_SIZE_NORMAL));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN,
                    FONT_SIZE_NORMAL));
            UIManager.put("Panel.background", backgroundColor);
            UIManager.put("Button.margin", new Insets(TOUCH_MARGIN,
                    TOUCH_MARGIN, TOUCH_MARGIN, TOUCH_MARGIN));
        } catch (Exception e) {
            System.out.println("Using default UI configurations");
        }
    }

    /**
     * Initializes and arranges all main components of the application Creates
     * the three-panel layout: sidebar, main content, and cart
     */
    private void initializeComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(createSidebar(), BorderLayout.WEST);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createReceiptPanel(), BorderLayout.EAST);

        add(mainPanel);
    }

    /**
     * Creates the left sidebar with navigation menu and application info
     *
     * @return JPanel configured as the application sidebar
     */
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout(0, 20));
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBackground(surfaceColor);
        sidebar.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 2),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(surfaceColor);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setBackground(surfaceColor);

        JLabel logo = new JLabel("🐾");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 42));
        logo.setPreferredSize(new Dimension(70, 70));

        JLabel title = new JLabel("Agro-Vet POS");
        title.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_TITLE));
        title.setForeground(textPrimaryColor);

        JLabel store = new JLabel("Store #1");
        store.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_SMALL));
        store.setForeground(primaryColor);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(surfaceColor);
        textPanel.add(title);
        textPanel.add(store);

        logoPanel.add(logo);
        logoPanel.add(Box.createHorizontalStrut(15));
        logoPanel.add(textPanel);

        topPanel.add(logoPanel);
        topPanel.add(Box.createVerticalStrut(30));

        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(surfaceColor);

        String[] menuItems = {
            "📊 Dashboard", "💊 Veterinary Medicines", "🌿 Animal Feed",
            "🔧 Farming Tools", "🐕 Pet Accessories", "🏥 Consultation Services"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton menuItem = createTouchButton(menuItems[i], i == 2);
            navPanel.add(menuItem);
            navPanel.add(Box.createVerticalStrut(8));
        }

        topPanel.add(navPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(surfaceColor);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        String[] bottomItems = {"⚙️ Settings", "❓ Help"};
        for (String item : bottomItems) {
            JButton menuItem = createTouchButton(item, false);
            bottomPanel.add(menuItem);
            bottomPanel.add(Box.createVerticalStrut(8));
        }

        sidebar.add(topPanel, BorderLayout.NORTH);
        sidebar.add(bottomPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    /**
     * Creates a touch-optimized button with appropriate styling and behavior
     *
     * @param text The display text for the button
     * @param active Whether the button should appear in active state
     * @return JButton configured for touch screen use
     */
    private JButton createTouchButton(String text, boolean active) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, BUTTON_HEIGHT));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(primaryColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (active) {
                    button.setBackground(new Color(primaryColor.getRed(),
                            primaryColor.getGreen(), primaryColor.
                            getBlue(), 76));
                } else {
                    button.setBackground(surfaceColor);
                }
            }
        });

        if (active) {
            button.setBackground(new Color(primaryColor.getRed(),
                    primaryColor.getGreen(), primaryColor.getBlue(), 76));
            button.setForeground(textPrimaryColor);
            button.setBorder(new CompoundBorder(
                    new LineBorder(primaryColor, 3),
                    new EmptyBorder(12, 17, 12, 17)
            ));
        } else {
            button.setBackground(surfaceColor);
            button.setForeground(textSecondaryColor);
        }

        button.addActionListener(e -> {
            System.out.println("Navigating to: " + text);
        });

        return button;
    }

    /**
     * Creates the center panel containing search, product grid, and category
     * title
     *
     * @return JPanel configured as the main content area
     */
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout(0, 20));
        centerPanel.setBackground(backgroundColor);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        centerPanel.add(createSearchPanel(), BorderLayout.NORTH);

        JLabel title = new JLabel("Animal Feed");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(textPrimaryColor);
        title.setBorder(new EmptyBorder(0, 20, 20, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(backgroundColor);
        titlePanel.add(title);

        centerPanel.add(titlePanel, BorderLayout.CENTER);

        productsPanel = createProductsGrid();
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        return centerPanel;
    }

    /**
     * Creates the search panel with touch-optimized search field
     *
     * @return JPanel containing the search interface
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        searchPanel.setBackground(backgroundColor);

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(600, BUTTON_HEIGHT));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, FONT_SIZE_NORMAL));
        searchField.setText("Search for products...");
        searchField.setForeground(Color.GRAY);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 2),
                new EmptyBorder(0, 15, 0, 15)
        ));

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for products...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for products...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        searchPanel.add(searchField);

        return searchPanel;
    }

    /**
     * Creates the product grid displaying all available products
     *
     * @return JPanel configured as a product catalog grid
     */
    private JPanel createProductsGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setBackground(backgroundColor);
        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        ProductUI[] products = {
            new ProductUI("🐕", "Premium Dog Food", "$45.99"),
            new ProductUI("🐔", "Chicken Feed Pellets", "$22.50"),
            new ProductUI("🐎", "Horse Feed Mix", "$38.00"),
            new ProductUI("🐄", "Cattle Feed Supplement", "$55.00"),
            new ProductUI("🐟", "Fish Food Flakes", "$9.99"),
            new ProductUI("🐇", "Organic Rabbit Pellets", "$19.50")
        };

        for (ProductUI product : products) {
            gridPanel.add(createProductCard(product));
        }

        return gridPanel;
    }

    /**
     * Creates an individual product card with image, name, price and touch
     * interactions
     *
     * @param product The product to display in the card
     * @return JPanel configured as a product card
     */
    private JPanel createProductCard(ProductUI product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 2),
                new EmptyBorder(15, 15, 15, 15)
        ));
        card.setPreferredSize(new Dimension(220, 280));

        JLabel imageLabel = new JLabel(product.getEmoji(),
                SwingConstants.CENTER);
        imageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(240, 240, 240));
        imageLabel.setPreferredSize(new Dimension(200, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL));
        nameLabel.setForeground(textPrimaryColor);

        JLabel priceLabel = new JLabel(product.getPrice());
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_SMALL));
        priceLabel.setForeground(primaryColor);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                card.setBackground(new Color(240, 240, 240));
                Timer timer = new Timer(100, evt -> {
                    card.setBackground(Color.WHITE);
                });
                timer.setRepeats(false);
                timer.start();

                addToCart(product);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(primaryColor, 3),
                        new EmptyBorder(15, 15, 15, 15)
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(220, 220, 220), 2),
                        new EmptyBorder(15, 15, 15, 15)
                ));
            }
        });

        return card;
    }

    /**
     * Creates the right-side panel for shopping cart and checkout
     *
     * @return JPanel configured as the shopping cart interface
     */
    private JPanel createReceiptPanel() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BorderLayout(0, 20));
        receiptPanel.setPreferredSize(new Dimension(350, 0));
        receiptPanel.setBackground(surfaceColor);
        receiptPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 2),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel title = new JLabel("Current Sale");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(textPrimaryColor);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(surfaceColor);

        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        JPanel totalsPanel = createTotalsPanel();
        JPanel buttonPanel = createReceiptButtons();

        receiptPanel.add(title, BorderLayout.NORTH);
        receiptPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(surfaceColor);
        southPanel.add(totalsPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        receiptPanel.add(southPanel, BorderLayout.SOUTH);

        return receiptPanel;
    }

    /**
     * Creates the totals display panel with subtotal, tax, and total
     *
     * @return JPanel configured to display price calculations
     */
    private JPanel createTotalsPanel() {
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
        totalsPanel.setBackground(surfaceColor);
        totalsPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        subtotalLabel = new JLabel("Subtotal: $0.00");
        taxLabel = new JLabel("Tax (5%): $0.00");
        totalLabel = new JLabel("Total: $0.00");

        subtotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, FONT_SIZE_NORMAL));
        taxLabel.setFont(new Font("Segoe UI", Font.PLAIN, FONT_SIZE_NORMAL));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        subtotalLabel.setForeground(textPrimaryColor);
        taxLabel.setForeground(textPrimaryColor);
        totalLabel.setForeground(textPrimaryColor);

        totalsPanel.add(subtotalLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(taxLabel);
        totalsPanel.add(Box.createVerticalStrut(15));
        totalsPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        totalsPanel.add(Box.createVerticalStrut(15));
        totalsPanel.add(totalLabel);

        return totalsPanel;
    }

    /**
     * Creates the checkout action buttons (Cancel and Checkout)
     *
     * @return JPanel containing the action buttons
     */
    private JPanel createReceiptButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setBackground(surfaceColor);

        JButton cancelBtn = new JButton("Cancel");
        JButton checkoutBtn = new JButton("Checkout");

        for (JButton btn : new JButton[]{cancelBtn, checkoutBtn}) {
            btn.setPreferredSize(new Dimension(0, BUTTON_HEIGHT));
            btn.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL));
            btn.setFocusPainted(false);
            btn.setBorder(new EmptyBorder(15, 0, 15, 0));
        }

        cancelBtn.setBackground(new Color(220, 220, 220));
        cancelBtn.setForeground(textPrimaryColor);

        checkoutBtn.setBackground(primaryColor);
        checkoutBtn.setForeground(Color.WHITE);

        cancelBtn.addActionListener(e -> clearCart());
        checkoutBtn.addActionListener(e -> processSale());

        buttonPanel.add(cancelBtn);
        buttonPanel.add(checkoutBtn);

        return buttonPanel;
    }

    /**
     * Adds a product to the shopping cart or increments quantity if already
     * present
     *
     * @param product The product to add to the cart
     */
    private void addToCart(ProductUI product) {

        for (ProductUI item : cartProducts) {
            if (item.getName().equals(product.getName())) {
                item.incrementQuantity();
                updateCart();
                return;
            }
        }

        cartProducts.add(new ProductUI(product));
        updateCart();
    }

    /**
     * Updates the cart display with current products and recalculates totals
     */
    private void updateCart() {
        cartPanel.removeAll();

        double subtotal = 0;

        for (ProductUI product : cartProducts) {
            JPanel itemPanel = createCartItem(product);
            cartPanel.add(itemPanel);
            cartPanel.add(Box.createVerticalStrut(10));

            subtotal += product.getNumericPrice() * product.getQuantity();
        }

        double tax = subtotal * 0.05;
        double total = subtotal + tax;

        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        taxLabel.setText(String.format("Tax (5%%): $%.2f", tax));
        totalLabel.setText(String.format("Total: $%.2f", total));

        cartPanel.revalidate();
        cartPanel.repaint();
    }

    /**
     * Creates a display panel for a single cart item
     *
     * @param product The product to display in the cart
     * @return JPanel configured as a cart item row
     */
    private JPanel createCartItem(ProductUI product) {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(surfaceColor);
        itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel infoPanel = new JPanel(new BorderLayout(10, 0));
        infoPanel.setBackground(surfaceColor);

        JLabel emojiLabel = new JLabel(product.getEmoji());
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(surfaceColor);

        JLabel nameLabel = new JLabel(product.getName());
        JLabel priceLabel = new JLabel(product.getPrice());

        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_SMALL));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_SMALL));

        nameLabel.setForeground(textPrimaryColor);
        priceLabel.setForeground(primaryColor);

        textPanel.add(nameLabel);
        textPanel.add(priceLabel);

        infoPanel.add(emojiLabel, BorderLayout.WEST);
        infoPanel.add(textPanel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        controlsPanel.setBackground(surfaceColor);

        JButton minusBtn = createQuantityButton("-");
        JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
        JButton plusBtn = createQuantityButton("+");
        JButton deleteBtn = createDeleteButton();

        minusBtn.addActionListener(e -> {
            product.decrementQuantity();
            if (product.getQuantity() == 0) {
                cartProducts.remove(product);
            }
            updateCart();
        });

        plusBtn.addActionListener(e -> {
            product.incrementQuantity();
            updateCart();
        });

        deleteBtn.addActionListener(e -> {
            cartProducts.remove(product);
            updateCart();
        });

        quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL));
        quantityLabel.setPreferredSize(new Dimension(30, 30));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        controlsPanel.add(minusBtn);
        controlsPanel.add(quantityLabel);
        controlsPanel.add(plusBtn);
        controlsPanel.add(Box.createHorizontalStrut(10));
        controlsPanel.add(deleteBtn);

        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(controlsPanel, BorderLayout.EAST);

        return itemPanel;
    }

    /**
     * Creates a quantity adjustment button
     *
     * @param text The button text ("+" or "-")
     * @return JButton configured for quantity adjustment
     */
    private JButton createQuantityButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(40, 40));
        button.setFont(new Font("Segoe UI", Font.BOLD, FONT_SIZE_NORMAL));
        button.setBackground(new Color(220, 220, 220));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Creates a delete button for removing items from cart
     *
     * @return JButton configured for item deletion
     */
    private JButton createDeleteButton() {
        JButton button = new JButton("🗑️");
        button.setPreferredSize(new Dimension(50, 40));
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, FONT_SIZE_NORMAL));
        button.setBackground(new Color(255, 200, 200));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Clears all items from the shopping cart
     */
    private void clearCart() {
        cartProducts.clear();
        updateCart();
        JOptionPane.showMessageDialog(this, "Cart cleared", "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Processes the current sale and completes the transaction
     */
    private void processSale() {
        if (cartProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        double total = Double.parseDouble(totalLabel.getText().
                replace("Total: $", ""));
        JOptionPane.showMessageDialog(this,
                String.format("Sale processed for: $%.2f", total),
                "Sale Successful",
                JOptionPane.INFORMATION_MESSAGE);
        clearCart();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TouchScreen().setVisible(true);
        });
    }

}
