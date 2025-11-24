package ui;

import javax.swing.*;
import java.awt.*;
import logic.Customer;

/**
 * Touch-optimized Customer Management Interface for AgroVet POS
 * Designed for 15" touch screen monitor
 * 
 * @author [Tu nombre]
 * @version 2.0
 */
public class CustomerUI extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;

    private JButton btnCreate;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnSearch;

    private JTextArea txtOutput;

    // Touch design constants
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
    
    private static final Dimension BUTTON_SIZE = new Dimension(220, 60);
    private static final Dimension FIELD_SIZE = new Dimension(350, 50);

    public CustomerUI() {
        setupTouchFrame();
        initComponents();
        setupEventHandlers();
    }

    private void setupTouchFrame() {
        setTitle("AgroVet - Gestión de Clientes");
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
        
        // Center area with form and output
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        
        // Footer with actions
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
        JLabel titleLabel = new JLabel("👥 GESTIÓN DE CLIENTES", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        // Left panel - Customer form
        centerPanel.add(createFormPanel());
        
        // Right panel - Output area
        centerPanel.add(createOutputPanel());

        return centerPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "REGISTRAR CLIENTE"
        ));

        // Customer ID
        JPanel idPanel = createFormField("ID del Cliente:", txtId = createTouchTextField());

        // Name
        JPanel namePanel = createFormField("Nombre:", txtName = createTouchTextField());

        // Email
        JPanel emailPanel = createFormField("Email:", txtEmail = createTouchTextField());

        // Phone
        JPanel phonePanel = createFormField("Teléfono:", txtPhone = createTouchTextField());

        // Address
        JPanel addressPanel = createFormField("Dirección:", txtAddress = createTouchTextField());

        // Add components with spacing
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(idPanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(namePanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(emailPanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(phonePanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(addressPanel);
        formPanel.add(Box.createVerticalStrut(30));

        return formPanel;
    }

    private JPanel createFormField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = createLabel(labelText);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private JPanel createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "INFORMACIÓN DEL CLIENTE"
        ));

        txtOutput = new JTextArea();
        txtOutput.setFont(TEXT_FONT);
        txtOutput.setEditable(false);
        txtOutput.setMargin(new Insets(10, 10, 10, 10));
        txtOutput.setText("Aquí se mostrará la información del cliente...");
        
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        return outputPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Action buttons
        btnCreate = createTouchButton("➕ CREAR CLIENTE", new Color(46, 125, 50));
        btnClear = createTouchButton("🗑️ LIMPIAR CAMPOS", new Color(194, 24, 91));
        btnSearch = createTouchButton("🔍 BUSCAR CLIENTE", new Color(25, 118, 210));

        footerPanel.add(btnCreate);
        footerPanel.add(btnClear);
        footerPanel.add(btnSearch);

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
        btnCreate.addActionListener(e -> createCustomer());
        btnClear.addActionListener(e -> clearFields());
        btnBack.addActionListener(e -> returnToMainMenu());
        btnSearch.addActionListener(e -> searchCustomer());
    }

    private void createCustomer() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();

            // Validación básica
            if (id.length() == 0 || name.length() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "ID y Nombre son campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Customer customer = new Customer(address, email, id, name, phone);

            txtOutput.setText(
                "✅ CLIENTE CREADO EXITOSAMENTE:\n\n" +
                "🔹 ID: " + customer.getId() + "\n" +
                "🔹 Nombre: " + customer.getName() + "\n" +
                "🔹 Email: " + customer.getEmail() + "\n" +
                "🔹 Teléfono: " + customer.getPhone() + "\n" +
                "🔹 Dirección: " + customer.getAddress() + "\n\n" +
                "📅 Cliente registrado en el sistema."
            );

            JOptionPane.showMessageDialog(this, 
                "Cliente creado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear cliente: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchCustomer() {
        String searchId = JOptionPane.showInputDialog(this, 
            "Ingrese el ID del cliente a buscar:", 
            "Buscar Cliente", 
            JOptionPane.QUESTION_MESSAGE);

        if (searchId != null && searchId.length() > 0) {
            // Simulación de búsqueda - en una implementación real aquí buscarías en la base de datos
            txtOutput.setText(
                "🔍 RESULTADO DE BÚSQUEDA:\n\n" +
                "Buscando cliente con ID: " + searchId + "\n\n" +
                "⚠️ Funcionalidad de búsqueda en desarrollo.\n" +
                "En una implementación real, aquí se mostrarían\n" +
                "los datos del cliente encontrado."
            );
        }
    }

    private void clearFields() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro de que desea limpiar todos los campos?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtAddress.setText("");
            txtOutput.setText("Aquí se mostrará la información del cliente...");
        }
    }

    private void returnToMainMenu() {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Volver al menú principal?", 
            "Confirmar", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            new MainMenuPOS().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerUI().setVisible(true);
        });
    }
}