package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Main Menu optimized for 15" touch screen monitor
 * Single entry point for the entire system with touch-friendly interface
 */
public class MainMenuPOS extends JFrame {
    
    // Touch-optimized constants
    private static final Color BACKGROUND_COLOR = new Color(240, 245, 240);
    private static final Color PRIMARY_GREEN = new Color(46, 125, 50);
    private static final Color SALES_COLOR = new Color(46, 125, 50);
    private static final Color CUSTOMERS_COLOR = new Color(25, 118, 210);
    private static final Color PRODUCTS_COLOR = new Color(245, 124, 0);
    private static final Color INVOICES_COLOR = new Color(123, 31, 162);
    private static final Color EMPLOYEES_COLOR = new Color(194, 24, 91);
    private static final Color EXIT_COLOR = new Color(97, 97, 97);
    
    // TOUCH-OPTIMIZED DIMENSIONS
    private static final Dimension BUTTON_SIZE = new Dimension(350, 120);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 36);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font FOOTER_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final int BUTTON_MARGIN = 20;

    public MainMenuPOS() {
        setupTouchOptimizedFrame();
        initComponents();
    }

    private void setupTouchOptimizedFrame() {
        setTitle("AgroVet POS - Sistema Principal");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen for touch
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configure global touch settings
        setupGlobalTouchSettings();
    }

    private void setupGlobalTouchSettings() {
        // Larger fonts and components for touch
        UIManager.put("Button.font", BUTTON_FONT);
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 20));
        UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 18));
        UIManager.put("Button.margin", new Insets(15, 20, 15, 20));
    }

    private void initComponents() {
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        panel.add(createHeaderPanel(), BorderLayout.NORTH);
        panel.add(createButtonGridPanel(), BorderLayout.CENTER);
        panel.add(createFooterPanel(), BorderLayout.SOUTH);
        
        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

        JLabel titleLabel = new JLabel("SISTEMA AGROVET POS", JLabel.CENTER);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.DARK_GRAY);

        JLabel subtitleLabel = new JLabel("Interfaz optimizada para pantalla táctil 15\"", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(BACKGROUND_COLOR);
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(subtitleLabel);

        headerPanel.add(textPanel, BorderLayout.CENTER);
        return headerPanel;
    }

    private JPanel createButtonGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(2, 3, BUTTON_MARGIN, BUTTON_MARGIN));
        gridPanel.setBackground(BACKGROUND_COLOR);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // Touch-optimized menu buttons
        gridPanel.add(createTouchMenuButton("🛒 VENTAS", SALES_COLOR));
        gridPanel.add(createTouchMenuButton("👥 CLIENTES", CUSTOMERS_COLOR));
        gridPanel.add(createTouchMenuButton("📦 PRODUCTOS", PRODUCTS_COLOR));
        gridPanel.add(createTouchMenuButton("🧾 FACTURAS", INVOICES_COLOR));
        gridPanel.add(createTouchMenuButton("👨‍💼 EMPLEADOS", EMPLOYEES_COLOR));
        gridPanel.add(createTouchMenuButton("🚪 SALIR", EXIT_COLOR));

        return gridPanel;
    }

    private JButton createTouchMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(BUTTON_SIZE);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Touch optimization
        button.setMargin(new Insets(20, 20, 20, 20));
        
        // Add navigation
        button.addActionListener(e -> handleMenuNavigation(text));
        
        return button;
    }

    private void handleMenuNavigation(String option) {
        switch(option) {
            case "🛒 VENTAS":
                openCartModule();
                break;
            case "👥 CLIENTES":
                openCustomerModule();
                break;
            case "📦 PRODUCTOS":
                openProductsModule();
                break;
            case "🧾 FACTURAS":
                openInvoicesModule();
                break;
            case "👨‍💼 EMPLEADOS":
                openEmployeesModule();
                break;
            case "🚪 SALIR":
                confirmExit();
                break;
        }
    }

    private void openCartModule() {
        // Asegúrate que CartUI también esté optimizado para touch
        new CartUI(new logic.Cart(null)).setVisible(true);
        this.dispose();
    }

    private void openCustomerModule() {
        new CustomerUI().setVisible(true);
        this.dispose();
    }

    private void openProductsModule() {
        // ✅ AHORA IMPLEMENTADO: Abre el sistema real de gestión de productos
        new ProductManagementUI().setVisible(true);
        this.dispose();
    }

    private void openInvoicesModule() {
        new InvoiceManagement().setVisible(true);
        this.dispose();
    }

    private void openEmployeesModule() {
        new EmployeeUI().setVisible(true);
        this.dispose();
    }

    private void confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro que desea salir del sistema?", 
            "Confirmar Salida", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(BACKGROUND_COLOR);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel footerLabel = new JLabel("AgroVet System v1.0 - Monitor Touch 15\"", JLabel.CENTER);
        footerLabel.setFont(FOOTER_FONT);
        footerLabel.setForeground(Color.GRAY);

        footerPanel.add(footerLabel, BorderLayout.CENTER);
        return footerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuPOS().setVisible(true);
        });
    }
}