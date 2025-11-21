package ui;

import javax.swing.*;
import java.awt.*;
import logic.Employee;

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

    public EmployeeUI() {
        setTitle("Employee Manager");
        setSize(450, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        form.add(new JLabel("ID:"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Name:"));
        txtName = new JTextField();
        form.add(txtName);

        form.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        form.add(txtEmail);

        form.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        form.add(txtPhone);

        form.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        form.add(txtAddress);

        form.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        form.add(txtPassword);

        form.add(new JLabel("Role:"));
        comboRole = new JComboBox<>(Employee.EmployeeRole.values());
        form.add(comboRole);

        form.add(new JLabel("Active:"));
        chkActive = new JCheckBox();
        chkActive.setSelected(true);
        form.add(chkActive);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        btnSave = new JButton("Save Employee");
        btnClear = new JButton("Clear");

        buttons.add(btnSave);
        buttons.add(btnClear);
        add(buttons, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveEmployee());
        btnClear.addActionListener(e -> clearForm());
    }

    private void saveEmployee() {
        try {
            Employee emp = new Employee(
                    chkActive.isSelected(),
                    (Employee.EmployeeRole) comboRole.getSelectedItem(),
                    new String(txtPassword.getPassword()),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    txtId.getText(),
                    txtName.getText(),
                    txtPhone.getText()
            );

            saveToFile(emp);
            JOptionPane.showMessageDialog(this, "Employee saved successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(Employee emp) {
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("employees.txt", true))) {
            pw.println(emp.toFileLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtPassword.setText("");
        comboRole.setSelectedIndex(0);
        chkActive.setSelected(true);
    }

    public static void main(String[] args) {
        new EmployeeUI().setVisible(true);
    }
}
