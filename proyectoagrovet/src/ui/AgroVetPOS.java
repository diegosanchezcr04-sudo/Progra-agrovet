/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AgroVetPOS extends JFrame {
    private Color primaryColor = new Color(168, 216, 185);
    private Color backgroundColor = new Color(245, 245, 245);
    private Color surfaceColor = new Color(253, 251, 246);
    private Color textPrimaryColor = new Color(74, 85, 104);
    private Color textSecondaryColor = new Color(160, 174, 192);
    
    private List<CartItem> cartItems = new ArrayList<>();
    private JPanel cartPanel;
    private JLabel subtotalLabel, taxLabel, totalLabel;
    
    public AgroVetPOS() {
        setTitle("Agro-Vet POS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        
        // Panel principal con layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        
        // Crear los tres paneles principales
        mainPanel.add(createSidebar(), BorderLayout.WEST);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createReceiptPanel(), BorderLayout.EAST);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(256, 0));
        sidebar.setBackground(surfaceColor);
        sidebar.setBorder(new EmptyBorder(16, 16, 16, 16));
        
        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(surfaceColor);
        
        // Logo y título
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(surfaceColor);
        logoPanel.setMaximumSize(new Dimension(300, 60));
        
        // Placeholder para logo
        JLabel logo = new JLabel("🐾");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        logo.setPreferredSize(new Dimension(40, 40));
        
        JLabel title = new JLabel("Agro-Vet POS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(textPrimaryColor);
        
        JLabel store = new JLabel("Store #1");
        store.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        store.setForeground(primaryColor);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(surfaceColor);
        textPanel.add(title);
        textPanel.add(store);
        
        logoPanel.add(logo);
        logoPanel.add(Box.createHorizontalStrut(10));
        logoPanel.add(textPanel);
        
        // Menú de navegación
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(surfaceColor);
        navPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        String[] menuItems = {
            "📊 Dashboard", "💊 Veterinary Medicines", "🌿 Animal Feed", 
            "🔧 Farming Tools", "🐕 Pet Accessories", "🏥 Consultation Services"
        };
        
        for (int i = 0; i < menuItems.length; i++) {
            JButton menuItem = createMenuItem(menuItems[i], i == 2); // Animal Feed activo
            navPanel.add(menuItem);
            navPanel.add(Box.createVerticalStrut(5));
        }
        
        topPanel.add(logoPanel);
        topPanel.add(navPanel);
        
        // Panel inferior
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(surfaceColor);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        String[] bottomItems = {"⚙️ Settings", "❓ Help"};
        for (String item : bottomItems) {
            JButton menuItem = createMenuItem(item, false);
            bottomPanel.add(menuItem);
            bottomPanel.add(Box.createVerticalStrut(5));
        }
        
        sidebar.add(topPanel, BorderLayout.NORTH);
        sidebar.add(bottomPanel, BorderLayout.SOUTH);
        
        return sidebar;
    }
    
    private JButton createMenuItem(String text, boolean active) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(10, 15, 10, 15));
        button.setFocusPainted(false);
        
        if (active) {
            button.setBackground(new Color(primaryColor.getRed(), primaryColor.getGreen(), primaryColor.getBlue(), 76));
            button.setForeground(textPrimaryColor);
        } else {
            button.setBackground(surfaceColor);
            button.setForeground(textSecondaryColor);
        }
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!active) {
                    button.setBackground(new Color(primaryColor.getRed(), primaryColor.getGreen(), primaryColor.getBlue(), 51));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!active) {
                    button.setBackground(surfaceColor);
                }
            }
        });
        
        return button;
    }
    
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(backgroundColor);
        centerPanel.setBorder(new EmptyBorder(24, 0, 0, 0));
        
        // Barra de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(backgroundColor);
        searchPanel.setBorder(new EmptyBorder(0, 16, 0, 16));
        
        JTextField searchField = new JTextField("Search for products...", 30);
        searchField.setPreferredSize(new Dimension(400, 48));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        searchPanel.add(searchField);
        
        // Título
        JLabel title = new JLabel("Animal Feed");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(textPrimaryColor);
        title.setBorder(new EmptyBorder(20, 32, 12, 0));
        
        // Grid de productos
        JPanel productsPanel = new JPanel(new GridLayout(0, 4, 24, 24));
        productsPanel.setBackground(backgroundColor);
        productsPanel.setBorder(new EmptyBorder(16, 32, 32, 32));
        
        // Productos de ejemplo
        Product[] products = {
            new Product("🐕 Premium Dog Food", "$45.99"),
            new Product("🐔 Chicken Feed Pellets", "$22.50"),
            new Product("🐎 Horse Feed Mix", "$38.00"),
            new Product("🐄 Cattle Feed Supplement", "$55.00"),
            new Product("🐟 Fish Food Flakes", "$9.99"),
            new Product("🐇 Organic Rabbit Pellets", "$19.50")
        };
        
        for (Product product : products) {
            productsPanel.add(createProductCard(product));
        }
        
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(title, BorderLayout.CENTER);
        centerPanel.add(productsPanel, BorderLayout.SOUTH);
        
        return centerPanel;
    }
    
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        card.setPreferredSize(new Dimension(180, 220));
        
        // Imagen del producto (placeholder con emoji)
        JLabel imageLabel = new JLabel(product.getEmoji(), SwingConstants.CENTER);
        imageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(240, 240, 240));
        imageLabel.setPreferredSize(new Dimension(180, 180));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        
        // Info del producto
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(textPrimaryColor);
        
        JLabel priceLabel = new JLabel(product.getPrice());
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        priceLabel.setForeground(primaryColor);
        
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);
        
        // Evento para agregar al carrito
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addToCart(product);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(240, 240, 240));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });
        
        return card;
    }
    
    private JPanel createReceiptPanel() {
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BorderLayout());
        receiptPanel.setPreferredSize(new Dimension(384, 0));
        receiptPanel.setBackground(surfaceColor);
        receiptPanel.setBorder(new EmptyBorder(24, 24, 24, 24));
        
        // Título
        JLabel title = new JLabel("Current Sale");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(textPrimaryColor);
        title.setBorder(new EmptyBorder(0, 0, 24, 0));
        
        // Panel del carrito
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(surfaceColor);
        
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Panel de totales
        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
        totalsPanel.setBackground(surfaceColor);
        totalsPanel.setBorder(new EmptyBorder(24, 0, 0, 0));
        
        subtotalLabel = new JLabel("Subtotal: $0.00");
        taxLabel = new JLabel("Tax (5%): $0.00");
        totalLabel = new JLabel("Total: $0.00");
        
        subtotalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        subtotalLabel.setForeground(textPrimaryColor);
        taxLabel.setForeground(textPrimaryColor);
        totalLabel.setForeground(textPrimaryColor);
        
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(taxLabel);
        totalsPanel.add(Box.createVerticalStrut(10));
        totalsPanel.add(totalLabel);
        
        // Botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        buttonPanel.setBackground(surfaceColor);
        buttonPanel.setBorder(new EmptyBorder(16, 0, 0, 0));
        
        JButton cancelButton = new JButton("Cancel");
        JButton checkoutButton = new JButton("Checkout");
        
        cancelButton.setBackground(new Color(220, 220, 220));
        checkoutButton.setBackground(primaryColor);
        checkoutButton.setForeground(Color.WHITE);
        
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(checkoutButton);
        
        receiptPanel.add(title, BorderLayout.NORTH);
        receiptPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(surfaceColor);
        bottomPanel.add(totalsPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        receiptPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        return receiptPanel;
    }
    
    private void addToCart(Product product) {
        // Buscar si el producto ya está en el carrito
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.incrementQuantity();
                updateCartDisplay();
                return;
            }
        }
        
        // Si no está, agregarlo
        cartItems.add(new CartItem(product));
        updateCartDisplay();
    }
    
    private void updateCartDisplay() {
        cartPanel.removeAll();
        
        double subtotal = 0;
        
        for (CartItem item : cartItems) {
            JPanel itemPanel = createCartItemPanel(item);
            cartPanel.add(itemPanel);
            cartPanel.add(Box.createVerticalStrut(8));
            
            subtotal += item.getTotalPrice();
        }
        
        double tax = subtotal * 0.05;
        double total = subtotal + tax;
        
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        taxLabel.setText(String.format("Tax (5%%): $%.2f", tax));
        totalLabel.setText(String.format("Total: $%.2f", total));
        
        cartPanel.revalidate();
        cartPanel.repaint();
    }
    
    private JPanel createCartItemPanel(CartItem cartItem) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBackground(surfaceColor);
        itemPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        
        Product product = cartItem.getProduct();
        
        // Información del producto
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(surfaceColor);
        
        JLabel emojiLabel = new JLabel(product.getEmoji());
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(surfaceColor);
        
        JLabel nameLabel = new JLabel(product.getName());
        JLabel priceLabel = new JLabel(product.getPrice());
        
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        nameLabel.setForeground(textPrimaryColor);
        priceLabel.setForeground(primaryColor);
        
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);
        
        infoPanel.add(emojiLabel, BorderLayout.WEST);
        infoPanel.add(Box.createHorizontalStrut(10));
        infoPanel.add(textPanel, BorderLayout.CENTER);
        
        // Controles de cantidad
        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.setBackground(surfaceColor);
        
        JButton minusButton = new JButton("-");
        JLabel quantityLabel = new JLabel(String.valueOf(cartItem.getQuantity()));
        JButton plusButton = new JButton("+");
        JButton deleteButton = new JButton("🗑️");
        
        minusButton.addActionListener(e -> {
            cartItem.decrementQuantity();
            if (cartItem.getQuantity() == 0) {
                cartItems.remove(cartItem);
            }
            updateCartDisplay();
        });
        
        plusButton.addActionListener(e -> {
            cartItem.incrementQuantity();
            updateCartDisplay();
        });
        
        deleteButton.addActionListener(e -> {
            cartItems.remove(cartItem);
            updateCartDisplay();
        });
        
        controlsPanel.add(minusButton);
        controlsPanel.add(quantityLabel);
        controlsPanel.add(plusButton);
        controlsPanel.add(deleteButton);
        
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(controlsPanel, BorderLayout.EAST);
        
        return itemPanel;
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new AgroVetPOS().setVisible(true);
        });
    }
}

class Product {
    private String name;
    private String price;
    private String emoji;
    
    public Product(String name, String price) {
        this.name = name;
        this.price = price;
        this.emoji = name.substring(0, 2); // Extraer emoji del nombre
    }
    
    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getEmoji() { return emoji; }
    
    public double getPriceValue() {
        return Double.parseDouble(price.substring(1));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return name.equals(product.name);
    }
}

class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }
    
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    public void incrementQuantity() { quantity++; }
    public void decrementQuantity() { quantity = Math.max(0, quantity - 1); }
    
    public double getTotalPrice() {
        return product.getPriceValue() * quantity;
    }
}