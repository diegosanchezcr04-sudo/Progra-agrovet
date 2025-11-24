package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TouchScreen extends JFrame {

    public TouchScreen() {
        initComponents();
        setupFrame();
    }

    private void initComponents() {
        JPanel mainPanel = createMainMenu();
        add(mainPanel);
    }

    private JPanel createMainMenu() {
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(new Color(240, 245, 240));
        
        // Header
        JLabel headerLabel = new JLabel("SISTEMA AGROVET", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerLabel.setForeground(new Color(0, 100, 0));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 40, 0));
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 25, 25));
        buttonPanel.setBackground(new Color(240, 245, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        
        // Crear botones del menú
        JButton salesBtn = createMenuButton("VENTAS", new Color(46, 125, 50));
        JButton productsBtn = createMenuButton("PRODUCTOS", new Color(25, 118, 210));
        JButton customersBtn = createMenuButton("CLIENTES", new Color(245, 124, 0));
        JButton invoicesBtn = createMenuButton("FACTURAS", new Color(123, 31, 162));
        JButton employeesBtn = createMenuButton("EMPLEADOS", new Color(194, 24, 91));
        JButton exitBtn = createMenuButton("SALIR", new Color(97, 97, 97));
        
        // Agregar action listeners
        salesBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Ventas"));
        productsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Productos"));
        customersBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Clientes"));
        invoicesBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Facturas"));
        employeesBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Módulo de Empleados"));
        
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    TouchScreen.this, 
                    "¿Está seguro que desea salir del sistema?", 
                    "Confirmar Salida", 
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        // Agregar botones al panel
        buttonPanel.add(salesBtn);
        buttonPanel.add(productsBtn);
        buttonPanel.add(customersBtn);
        buttonPanel.add(invoicesBtn);
        buttonPanel.add(employeesBtn);
        buttonPanel.add(exitBtn);
        
        // Footer
        JLabel footerLabel = new JLabel("AgroVet System v1.0", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        footerLabel.setForeground(Color.GRAY);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        // Agregar componentes al panel principal
        menuPanel.add(headerLabel, BorderLayout.NORTH);
        menuPanel.add(buttonPanel, BorderLayout.CENTER);
        menuPanel.add(footerLabel, BorderLayout.SOUTH);
        
        return menuPanel;
    }

    private JButton createMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 120));
        
        return button;
    }

    private void setupFrame() {
        setTitle("Sistema AgroVet - Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new TouchScreen().setVisible(true);
    }
}