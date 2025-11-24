package ui;

import javax.swing.*;
import java.awt.*;
import logic.Employee;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Touch-optimized Employee Management Interface for AgroVet POS
 * Designed for 15" touch screen monitor
 * 
 * @author [Tu nombre]
 * @version 2.0
 */
public class EmployeeUI extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JPasswordField txtPassword;
    private JComboBox<Employee.EmployeeRole> comboRole;
    private JCheckBox chkActive;

    private JButton btnSave;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnViewEmployees;

    // Touch design constants
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font COMBO_FONT = new Font("Arial", Font.PLAIN, 18);
    
    private static final Dimension BUTTON_SIZE = new Dimension(220, 60);
    private static final Dimension FIELD_SIZE = new Dimension(350, 50);
    private static final Dimension COMBO_SIZE = new Dimension(350, 50);

    public EmployeeUI() {
        setupTouchFrame();
        initComponents();
        setupEventHandlers();
    }

    private void setupTouchFrame() {
        setTitle("AgroVet - Gestión de Empleados");
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
        
        // Center area with form
        mainPanel.add(createFormPanel(), BorderLayout.CENTER);
        
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
        JLabel titleLabel = new JLabel("👨‍💼 GESTIÓN DE EMPLEADOS", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "REGISTRAR EMPLEADO"
        ));

        // Employee ID
        JPanel idPanel = createFormField("ID del Empleado:", txtId = createTouchTextField());

        // Name
        JPanel namePanel = createFormField("Nombre Completo:", txtName = createTouchTextField());

        // Email
        JPanel emailPanel = createFormField("Email:", txtEmail = createTouchTextField());

        // Phone
        JPanel phonePanel = createFormField("Teléfono:", txtPhone = createTouchTextField());

        // Address
        JPanel addressPanel = createFormField("Dirección:", txtAddress = createTouchTextField());

        // Password
        JPanel passwordPanel = createFormField("Contraseña:", txtPassword = createTouchPasswordField());

        // Role
        JPanel rolePanel = createComboField("Rol:", comboRole = createRoleComboBox());

        // Active Status
        JPanel activePanel = createCheckboxField("Empleado Activo:", chkActive = createTouchCheckbox());

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
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(passwordPanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(rolePanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(activePanel);
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

    private JPanel createComboField(String labelText, JComboBox<?> comboBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = createLabel(labelText);
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }

    private JPanel createCheckboxField(String labelText, JCheckBox checkBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = createLabel(labelText);
        panel.add(label);
        panel.add(checkBox);
        return panel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Action buttons
        btnSave = createTouchButton("💾 GUARDAR EMPLEADO", new Color(46, 125, 50));
        btnClear = createTouchButton("🗑️ LIMPIAR CAMPOS", new Color(194, 24, 91));
        btnViewEmployees = createTouchButton("👁️ VER EMPLEADOS", new Color(25, 118, 210));

        footerPanel.add(btnSave);
        footerPanel.add(btnClear);
        footerPanel.add(btnViewEmployees);

        return footerPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setPreferredSize(new Dimension(200, 40));
        return label;
    }

    private JTextField createTouchTextField() {
        JTextField field = new JTextField();
        field.setFont(TEXT_FONT);
        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);
        return field;
    }

    private JPasswordField createTouchPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(TEXT_FONT);
        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);
        return field;
    }

    private JComboBox<Employee.EmployeeRole> createRoleComboBox() {
        JComboBox<Employee.EmployeeRole> combo = new JComboBox<>(Employee.EmployeeRole.values());
        combo.setFont(COMBO_FONT);
        combo.setPreferredSize(COMBO_SIZE);
        combo.setMaximumSize(COMBO_SIZE);
        return combo;
    }

    private JCheckBox createTouchCheckbox() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(true);
        checkBox.setPreferredSize(new Dimension(30, 30));
        return checkBox;
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
        btnSave.addActionListener(e -> saveEmployee());
        btnClear.addActionListener(e -> clearForm());
        btnBack.addActionListener(e -> returnToMainMenu());
        btnViewEmployees.addActionListener(e -> viewEmployees());
    }

    private void saveEmployee() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();
            String password = new String(txtPassword.getPassword());
            Employee.EmployeeRole role = (Employee.EmployeeRole) comboRole.getSelectedItem();
            boolean active = chkActive.isSelected();

            // Validación básica
            if (id.length() == 0 || name.length() == 0 || password.length() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "ID, Nombre y Contraseña son campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Employee employee = new Employee(active, role, password, address, email, id, name, phone);

            saveToFile(employee);
            
            JOptionPane.showMessageDialog(this, 
                "✅ Empleado guardado exitosamente\n\n" +
                "🔹 ID: " + employee.getId() + "\n" +
                "🔹 Nombre: " + employee.getName() + "\n" +
                "🔹 Rol: " + employee.getRole() + "\n" +
                "🔹 Estado: " + (employee.isActive() ? "Activo" : "Inactivo"),
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            clearForm();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error al guardar empleado: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(Employee employee) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("employees.txt", true))) {
            pw.println(employee.toFileLine());
        } catch (Exception ex) {
            throw new RuntimeException("Error al guardar en archivo: " + ex.getMessage());
        }
    }

    private void viewEmployees() {
        JOptionPane.showMessageDialog(this,
            "👁️ FUNCIONALIDAD: VER EMPLEADOS\n\n" +
            "En una implementación completa, aquí se mostraría\n" +
            "una lista de todos los empleados registrados\n" +
            "con opciones para editar y eliminar.\n\n" +
            "📊 Empleados guardados en: employees.txt",
            "Ver Empleados",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
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
            txtPassword.setText("");
            comboRole.setSelectedIndex(0);
            chkActive.setSelected(true);
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
            new EmployeeUI().setVisible(true);
        });
    }
}