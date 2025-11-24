package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Invoice Management System for AgroVet POS
 * 
 * Provides a simplified interface for managing invoices without touch screen 
 * optimization. Features include invoice viewing, searching, filtering by date range,
 * and status tracking.
 * 
 * @author Leo Aguilar
 * @version 2.0
 */
public class InvoiceManagement extends JFrame {

    /**
     * Primary brand color for buttons and highlights
     */
    private final Color primaryColor = new Color(168, 208, 141);

    /**
     * Background color for main application area
     */
    private final Color backgroundColor = new Color(246, 248, 246);

    /**
     * Primary text color for headings and important text
     */
    private final Color textPrimaryColor = new Color(14, 27, 18);

    /**
     * Table component for displaying invoice data
     */
    private JTable invoiceTable;

    /**
     * Data model for the invoice table
     */
    private InvoiceTableModel tableModel;

    /**
     * Search field for filtering invoices
     */
    private JTextField searchField;

    /**
     * Start date field for date range filtering
     */
    private JTextField startDateField;

    /**
     * End date field for date range filtering
     */
    private JTextField endDateField;

    /**
     * Main constructor for Invoice Management application
     * Initializes the main window and all UI components
     */
    public InvoiceManagement() {
        setTitle("AgroVet POS - Invoice Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create interface
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createFiltersPanel(), BorderLayout.CENTER);
        mainPanel.add(createInvoiceTable(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates the header panel with title and action buttons
     * 
     * @return JPanel configured as the header area
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(backgroundColor);

        JLabel title = new JLabel("Invoice Management");
        title.setFont(new Font("Dialog", Font.BOLD, 24));
        title.setForeground(textPrimaryColor);

        JLabel subtitle = new JLabel("View, search, and manage all your invoices.");
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(backgroundColor);
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(subtitle);

        titlePanel.add(textPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(backgroundColor);

        JButton exportBtn = createActionButton("Export Report", false);
        JButton newInvoiceBtn = createActionButton("Create New Invoice", true);
        JButton closeBtn = createActionButton("Close", false);

        closeBtn.addActionListener(e -> dispose());

        buttonPanel.add(exportBtn);
        buttonPanel.add(newInvoiceBtn);
        buttonPanel.add(closeBtn);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates an action button with primary or secondary styling
     * 
     * @param text The display text for the button
     * @param primary Whether the button should use primary styling
     * @return JButton configured for actions
     */
    private JButton createActionButton(String text, boolean primary) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(160, 35));
        button.setFont(new Font("Dialog", Font.BOLD, 12));
        button.setFocusPainted(false);

        if (primary) {
            button.setBackground(primaryColor);
            button.setForeground(Color.BLACK);
        } else {
            button.setBackground(new Color(220, 220, 220));
            button.setForeground(Color.BLACK);
        }

        button.addActionListener(e -> {
            System.out.println("Button clicked: " + text);
            if (text.contains("Create New Invoice")) {
                JOptionPane.showMessageDialog(this, 
                        "Functionality: Create new invoice");
            } else if (text.contains("Export Report")) {
                JOptionPane.showMessageDialog(this, 
                        "Functionality: Export report");
            }
        });

        return button;
    }

    /**
     * Creates the filters panel with search and date range controls
     * 
     * @return JPanel configured as the filters area
     */
    private JPanel createFiltersPanel() {
        JPanel filtersPanel = new JPanel(new BorderLayout());
        filtersPanel.setBackground(backgroundColor);
        filtersPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        searchPanel.setBackground(backgroundColor);

        searchField = new JTextField(20);
        searchField.setFont(new Font("Dialog", Font.PLAIN, 12));
        searchField.setText("Search by Invoice ID or Client Name...");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search by Invoice ID or Client Name...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search by Invoice ID or Client Name...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        JButton searchBtn = new JButton("Search");
        searchBtn.setPreferredSize(new Dimension(80, 25));
        
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(searchBtn);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        datePanel.setBackground(backgroundColor);

        startDateField = new JTextField(10);
        startDateField.setFont(new Font("Dialog", Font.PLAIN, 12));
        startDateField.setText("Start Date");
        startDateField.setForeground(Color.GRAY);

        endDateField = new JTextField(10);
        endDateField.setFont(new Font("Dialog", Font.PLAIN, 12));
        endDateField.setText("End Date");
        endDateField.setForeground(Color.GRAY);

        setupDateField(startDateField, "Start Date");
        setupDateField(endDateField, "End Date");

        JButton filterBtn = new JButton("Filter");
        filterBtn.setPreferredSize(new Dimension(80, 25));

        datePanel.add(new JLabel("From:"));
        datePanel.add(startDateField);
        datePanel.add(new JLabel("To:"));
        datePanel.add(endDateField);
        datePanel.add(Box.createHorizontalStrut(10));
        datePanel.add(filterBtn);

        filtersPanel.add(searchPanel, BorderLayout.WEST);
        filtersPanel.add(datePanel, BorderLayout.EAST);

        return filtersPanel;
    }

    /**
     * Sets up a date field with placeholder text behavior
     * 
     * @param dateField The text field to configure
     * @param placeholder The placeholder text to use
     */
    private void setupDateField(JTextField dateField, String placeholder) {
        dateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateField.getText().equals(placeholder)) {
                    dateField.setText("");
                    dateField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dateField.getText().isEmpty()) {
                    dateField.setText(placeholder);
                    dateField.setForeground(Color.GRAY);
                }
            }
        });
    }

    /**
     * Creates the invoice table with scroll pane
     * 
     * @return JPanel configured as the invoice table area
     */
    private JPanel createInvoiceTable() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(backgroundColor);

        tableModel = new InvoiceTableModel();
        invoiceTable = new JTable(tableModel);

        setupTable();

        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        scrollPane.setPreferredSize(new Dimension(0, 400));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    /**
     * Configures the invoice table for normal interaction
     */
    private void setupTable() {
        invoiceTable.setRowHeight(30);
        invoiceTable.setFont(new Font("Dialog", Font.PLAIN, 12));
        invoiceTable.setSelectionBackground(primaryColor.brighter());

        JTableHeader header = invoiceTable.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 12));
        header.setBackground(new Color(240, 240, 240));

        // Configure custom renderers
        invoiceTable.getColumnModel().getColumn(4).setCellRenderer(new StatusRenderer());
        invoiceTable.getColumnModel().getColumn(5).setCellRenderer(new ActionsRenderer());

        TableColumnModel columnModel = invoiceTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(70);
        columnModel.getColumn(5).setPreferredWidth(60);

        // Add listener for action buttons
        invoiceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = invoiceTable.columnAtPoint(e.getPoint());
                int row = invoiceTable.rowAtPoint(e.getPoint());

                if (column == 5 && row >= 0) { // Actions column
                    String invoiceId = (String) invoiceTable.getValueAt(row, 0);
                    JOptionPane.showMessageDialog(invoiceTable,
                            "Viewing details for: " + invoiceId);
                }
            }
        });
    }

    /**
     * Main method - application entry point
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new InvoiceManagement().setVisible(true);
            } catch (Exception e) {
                System.err.println("Error creating application: " + e.getMessage());
                
                // Basic fallback
                JFrame frame = new JFrame("AgroVet POS - Invoice Management");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                JLabel errorLabel = new JLabel("Error starting Invoice Management", JLabel.CENTER);
                frame.add(errorLabel);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Table model for invoice data management
 * Provides the data structure and behavior for the invoice table
 */
class InvoiceTableModel extends AbstractTableModel {

    /**
     * Column names for the invoice table
     */
    private final String[] columnNames = {"Invoice ID", "Client Name", "Date", "Amount", "Status", "Actions"};

    /**
     * Sample invoice data for demonstration
     */
    private final Object[][] data = {
        {"#INV-00123", "Green Valley Farms", "2024-08-15", "$1,250.00", "Paid", "View"},
        {"#INV-00124", "Sunrise Stables", "2024-08-12", "$875.50", "Pending", "View"},
        {"#INV-00125", "Oakwood Cattle Co.", "2024-07-20", "$2,400.00", "Overdue", "View"},
        {"#INV-00126", "Feather & Fur Pet Supplies", "2024-08-10", "$450.75", "Paid", "View"},
        {"#INV-00127", "Riverbend Poultry", "2024-08-05", "$1,120.00", "Pending", "View"}
    };

    /**
     * Returns the number of rows in the table
     * 
     * @return The number of rows
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the table
     * 
     * @return The number of columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the value at specified row and column
     * 
     * @param rowIndex The row index
     * @param columnIndex The column index
     * @return The value at the specified position
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Returns the column name for the specified column
     * 
     * @param column The column index
     * @return The column name
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns whether the cell at specified position is editable
     * 
     * @param rowIndex The row index
     * @param columnIndex The column index
     * @return true if editable, false otherwise
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5; // Only Actions column is editable
    }
}

/**
 * Custom cell renderer for status column
 * Applies color coding based on invoice status
 */
class StatusRenderer extends DefaultTableCellRenderer {

    /**
     * Returns the component for rendering status cells
     * 
     * @param table The JTable
     * @param value The value to render
     * @param isSelected Whether the cell is selected
     * @param hasFocus Whether the cell has focus
     * @param row The row index
     * @param column The column index
     * @return The configured component for rendering
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);

        if (c instanceof JLabel label) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

            String status = (String) value;
            switch (status) {
                case "Paid" -> {
                    label.setBackground(new Color(92, 184, 92, 76));
                    label.setForeground(new Color(92, 184, 92));
                }
                case "Pending" -> {
                    label.setBackground(new Color(240, 173, 78, 76));
                    label.setForeground(new Color(240, 173, 78));
                }
                case "Overdue" -> {
                    label.setBackground(new Color(217, 83, 79, 76));
                    label.setForeground(new Color(217, 83, 79));
                }
            }
        }

        return c;
    }
}

/**
 * Custom cell renderer for actions column
 * Displays buttons in the actions column
 */
class ActionsRenderer extends DefaultTableCellRenderer {

    /**
     * Returns a button component for action cells
     * 
     * @param table The JTable
     * @param value The value to render
     * @param isSelected Whether the cell is selected
     * @param hasFocus Whether the cell has focus
     * @param row The row index
     * @param column The column index
     * @return A JButton configured for actions
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        JButton button = new JButton("View");
        button.setBackground(new Color(168, 208, 141));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Dialog", Font.BOLD, 11));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return button;
    }
}