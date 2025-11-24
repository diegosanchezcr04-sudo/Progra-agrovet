package ui;

import javax.swing.*;
import java.awt.*;
import logic.*;
import java.util.Date;

/**
 * Touch-optimized Cart Interface for AgroVet POS
 * Designed for 15" touch screen monitor
 * 
 * @author [Tu nombre]
 * @version 2.1
 */
public class CartUI extends JFrame {

    private Cart cart;

    // Touch-optimized components
    private JTextField txtProductId;
    private JTextField txtQuantity;
    private JButton btnAdd;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnCheckout;

    private JTextArea txtItems;
    private JLabel lblSummary;

    // Touch design constants
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
    
    private static final Dimension BUTTON_SIZE = new Dimension(200, 60);
    private static final Dimension FIELD_SIZE = new Dimension(300, 50);

    public CartUI(Cart cart) {
        this.cart = cart;
        setupTouchFrame();
        initComponents();
        setupEventHandlers();
    }

    private void setupTouchFrame() {
        setTitle("AgroVet - Carrito de Compras");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header with back button
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Center area with input and items
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        
        // Footer with summary and actions
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Back button
        btnBack = createTouchButton("← VOLVER AL MENÚ", new Color(97, 97, 97));
        headerPanel.add(btnBack, BorderLayout.WEST);

        // Title
        JLabel titleLabel = new JLabel("🛒 CARRITO DE COMPRAS", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        // Left panel - Input form
        centerPanel.add(createInputPanel());
        
        // Right panel - Cart items
        centerPanel.add(createItemsPanel());

        return centerPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "AGREGAR PRODUCTO"
        ));

        // Product ID
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(createLabel("ID del Producto:"));
        txtProductId = createTouchTextField();
        idPanel.add(txtProductId);

        // Quantity
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        qtyPanel.add(createLabel("Cantidad:"));
        txtQuantity = createTouchTextField();
        txtQuantity.setText("1");
        qtyPanel.add(txtQuantity);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnAdd = createTouchButton("➕ AGREGAR", new Color(46, 125, 50));
        btnClear = createTouchButton("🗑️ LIMPIAR", new Color(194, 24, 91));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);

        // Add components with spacing
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(idPanel);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(qtyPanel);
        inputPanel.add(Box.createVerticalStrut(30));
        inputPanel.add(buttonPanel);
        inputPanel.add(Box.createVerticalGlue());

        return inputPanel;
    }

    private JPanel createItemsPanel() {
        JPanel itemsPanel = new JPanel(new BorderLayout());
        itemsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "PRODUCTOS EN CARRITO"
        ));

        txtItems = new JTextArea();
        txtItems.setFont(TEXT_FONT);
        txtItems.setEditable(false);
        txtItems.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(txtItems);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        
        itemsPanel.add(scrollPane, BorderLayout.CENTER);

        return itemsPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout(20, 0));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Summary label
        lblSummary = new JLabel("Carrito vacío");
        lblSummary.setFont(LABEL_FONT);
        lblSummary.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footerPanel.add(lblSummary, BorderLayout.CENTER);

        // Checkout button
        btnCheckout = createTouchButton("💳 FINALIZAR COMPRA", new Color(123, 31, 162));
        footerPanel.add(btnCheckout, BorderLayout.EAST);

        return footerPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setPreferredSize(new Dimension(180, 40));
        return label;
    }

    private JTextField createTouchTextField() {
        JTextField field = new JTextField();
        field.setFont(TEXT_FONT);
        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);
        return field;
    }

    private JButton createTouchButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private void setupEventHandlers() {
        btnAdd.addActionListener(e -> addProduct());
        btnClear.addActionListener(e -> clearCart());
        btnBack.addActionListener(e -> returnToMainMenu());
        btnCheckout.addActionListener(e -> checkout());
    }

    private void addProduct() {
        try {
            String id = txtProductId.getText().trim();
            int qty = Integer.parseInt(txtQuantity.getText().trim());

            // CORRECCIÓN: Usar length() == 0 en lugar de isEmpty()
            if (id.length() == 0 || qty <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese un ID válido y cantidad mayor a 0", 
                    "Datos inválidos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Temporary product creation - REPLACE with real product lookup
            Product p = new Product(
                0f, 10f, 0f, 0, (short) 1, new Date(),
                "Producto temporal", id, "Producto " + id, 
                Product.Category.PETS, Product.Type.PET_TOY
            );

            cart.addProduct(p, qty);
            updateUIData();
            
            // Clear input fields
            txtProductId.setText("");
            txtQuantity.setText("1");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "La cantidad debe ser un número válido", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar producto: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearCart() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro de que desea vaciar el carrito?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            cart.clear();
            updateUIData();
        }
    }

    private void checkout() {
        // CORRECCIÓN: Usar size() == 0 en lugar de isEmpty()
        if (cart.getItems().size() == 0) {
            JOptionPane.showMessageDialog(this, 
                "El carrito está vacío", 
                "Carrito vacío", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, 
            "Funcionalidad: Finalizar compra\nTotal: " + cart.getCartSummary(), 
            "Procesar compra", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void returnToMainMenu() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Volver al menú principal? Los items del carrito se perderán.", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            // CORRECCIÓN: Usar MainMenuPOS en lugar de MainMenu
            new MainMenuPOS().setVisible(true);
            this.dispose();
        }
    }

    private void updateUIData() {
        StringBuilder sb = new StringBuilder();
        
        // CORRECCIÓN: Usar size() == 0 en lugar de isEmpty()
        if (cart.getItems().size() == 0) {
            sb.append("Carrito vacío\n");
        } else {
            for (CartItem item : cart.getItems()) {
                sb.append(String.format("• %s\n", item.getProduct().getId()))
                  .append(String.format("  Cantidad: %d\n", item.getQuantity()))
                  .append(String.format("  Total: $%.2f\n\n", item.getItemTotal()));
            }
        }

        txtItems.setText(sb.toString());
        lblSummary.setText("Resumen: " + cart.getCartSummary());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cart c = new Cart(null);
            new CartUI(c).setVisible(true);
        });
    }
}