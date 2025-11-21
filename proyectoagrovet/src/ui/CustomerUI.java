package ui;

import javax.swing.*;
import java.awt.*;
import logic.Customer;

public class CustomerUI extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;

    private JButton btnCreate;
    private JButton btnClear;

    private JTextArea txtOutput;

    public CustomerUI() {
        setTitle("Customer Manager");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Register Customer"));

        topPanel.add(new JLabel("Customer ID:"));
        txtId = new JTextField();
        topPanel.add(txtId);

        topPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        topPanel.add(txtName);

        topPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        topPanel.add(txtEmail);

        topPanel.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        topPanel.add(txtPhone);

        topPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        topPanel.add(txtAddress);

        btnCreate = new JButton("Create Customer");
        topPanel.add(btnCreate);

        btnClear = new JButton("Clear Fields");
        topPanel.add(btnClear);

        add(topPanel, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        btnCreate.addActionListener(e -> createCustomer());
        btnClear.addActionListener(e -> clearFields());
    }
    

    private void createCustomer() {
        try {
            Customer c = new Customer(
                txtAddress.getText().trim(),
                txtEmail.getText().trim(),
                txtId.getText().trim(),
                txtName.getText().trim(),
                txtPhone.getText().trim()
            );

            txtOutput.setText(
                "Customer Created Successfully:\n" +
                "ID: " + c.getId() + "\n" +
                "Name: " + c.getName() + "\n" +
                "Email: " + c.getEmail() + "\n" +
                "Phone: " + c.getPhone() + "\n" +
                "Address: " + c.getAddress()
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtOutput.setText("");
    }

    public static void main(String[] args) {
        new CustomerUI().setVisible(true);
    }
}