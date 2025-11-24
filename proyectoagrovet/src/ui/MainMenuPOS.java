package ui;

import javax.swing.*;
import java.awt.*;
import logic.Cart;

public class MainMenuPOS extends JFrame {

    public MainMenuPOS() {

        setTitle("AGROVET POS - Touch Menu");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout tipo POS (botones grandes)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        // Crear botones estilo POS
        JButton btnCustomers = createPOSButton("👤 Customers");
        JButton btnProducts  = createPOSButton("📦 Products");
        JButton btnCart      = createPOSButton("🛒 Cart");
        JButton btnInvoices  = createPOSButton("🧾 Invoices");
        JButton btnMng       = createPOSButton("⚙ Invoice Management");
        JButton btnExit      = createPOSButton("❌ Exit");

        // Agregar botones al panel
        panel.add(btnCustomers);
        panel.add(btnProducts);
        panel.add(btnCart);
        panel.add(btnInvoices);
        panel.add(btnMng);
        panel.add(btnExit);

        add(panel);

        // Acciones
        btnCustomers.addActionListener(e -> new CustomerUI().setVisible(true));

        btnProducts.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Product UI aún no implementado")
        );

        btnCart.addActionListener(e -> new CartUI(new Cart(null)).setVisible(true));

        btnInvoices.addActionListener(e -> new InvoiceManagement().setVisible(true));

        btnMng.addActionListener(e -> new InvoiceManagement().setVisible(true));

        btnExit.addActionListener(e -> System.exit(0));
    }

    // Método para crear botones estilo POS
    private JButton createPOSButton(String text) {

        JButton btn = new JButton(text);

        btn.setFont(new Font("Arial", Font.BOLD, 26));
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // efecto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(30, 136, 229));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(33, 150, 243));
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        new MainMenuPOS().setVisible(true);
    }
}
