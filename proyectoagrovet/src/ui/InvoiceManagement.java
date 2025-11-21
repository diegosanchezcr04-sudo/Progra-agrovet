package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import logic.Cart;

/**
 * Invoice Management System for AgroVet POS
 * 
 * Provides a comprehensive interface for managing invoices with touch screen 
 * optimizationfor 15-inch displays. Features include invoice viewing, 
 * searching, filtering by date range,
 * and status tracking.
 * 
 * @author Leo Aguilar
 * @version 1.0
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Configuration for 15-inch touch screen
        setupTouchFriendlyUI();

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        // Create interface
        mainPanel.add(createSidebar(), BorderLayout.WEST);
        mainPanel.add(createMainContent(), BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Configures global UI settings for touch screen optimization
     * Sets larger fonts, appropriate spacing, and touch-friendly defaults
     */
    private void setupTouchFriendlyUI() {
        // Configure larger fonts for touch
        setupLookAndFeel();

        // Specific configurations for components
        setupUIManagerDefaults();
    }

    /**
     * Attempts to set the best available look and feel for the system
     * Tries multiple look and feels in order of preference
     */
    private static void setupLookAndFeel() {
        System.out.println("Configuring Look and Feel...");

        // List of look and feels to try, in order of preference
        String[] lookAndFeels = {
            "Nimbus",
            "Windows",
            "Metal",
            "Motif",
            "GTK+"
        };

        for (String lafName : lookAndFeels) {
            if (setLookAndFeelByName(lafName)) {
                System.out.println("Look and Feel configured: " + lafName);
                return;
            }
        }

        // If all else fails, try the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Using System Look and Feel");
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Using default Java Look and Feel");
            // Do nothing - use Java default
        }
    }

    /**
     * Attempts to set a specific look and feel by name
     * 
     * @param lafName The name of the look and feel to set
     * @return true if successful, false otherwise
     */
    private static boolean setLookAndFeelByName(String lafName) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.
                    getInstalledLookAndFeels()) {
                if (lafName.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return true;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Could not load " + lafName + ": " + 
                    e.getMessage());
        }
        return false;
    }

    /**
     * Sets up default UI manager properties for touch-friendly interface
     * Configures fonts, margins, and other visual properties
     */
    private static void setupUIManagerDefaults() {
        // Configure larger fonts for touch
        try {
            UIManager.put("Button.font", new Font("Dialog", Font.BOLD, 16));
            UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 16));
            UIManager.put("TextField.font", new Font("Dialog", Font.PLAIN, 18));
            UIManager.put("Table.font", new Font("Dialog", Font.PLAIN, 16));
            UIManager.put("TableHeader.font", new Font("Dialog", Font.BOLD, 16));
            UIManager.put("Button.margin", new Insets(15, 20, 15, 20));
        } catch (Exception e) {
            System.out.println("Some UI configurations could not be applied");
        }
    }

    /**
     * Creates the left sidebar with navigation menu and application info
     * 
     * @return JPanel configured as the application sidebar
     */
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Top panel with logo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setBackground(Color.WHITE);

        JLabel logo = new JLabel("🐾");
        logo.setFont(new Font("Dialog", Font.PLAIN, 36));
        logo.setPreferredSize(new Dimension(60, 60));

        JLabel title = new JLabel("AgroVet POS");
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setForeground(textPrimaryColor);

        JLabel subtitle = new JLabel("Main Menu");
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 16));
        subtitle.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.add(title);
        textPanel.add(subtitle);

        logoPanel.add(logo);
        logoPanel.add(Box.createHorizontalStrut(15));
        logoPanel.add(textPanel);

        topPanel.add(logoPanel);

        // Navigation menu
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        String[] menuItems = {
            "📊 Cart", "📦 Employee", "📈 ", " Invoices", "👥 Costumer"
        };

        for (int i = 0; i < menuItems.length; i++) {
            JButton menuItem = createNavButton(menuItems[i], i == 3);
            navPanel.add(menuItem);
            navPanel.add(Box.createVerticalStrut(8));
        }

        // Logout button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton logoutBtn = createNavButton("🚪 Logout", false);
        bottomPanel.add(logoutBtn);

        sidebar.add(topPanel, BorderLayout.NORTH);
        sidebar.add(navPanel, BorderLayout.CENTER);
        sidebar.add(bottomPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    /**
     * Creates a navigation button with appropriate styling and behavior
     * 
     * @param text The display text for the button
     * @param active Whether the button should appear in active state
     * @return JButton configured for navigation
     */
    private JButton createNavButton(String text, boolean active) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(280, 60));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setFont(new Font("Dialog", Font.BOLD, 18));

        if (active) {
            button.setBackground(new Color(168, 208, 141, 76));
            button.setForeground(textPrimaryColor);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(primaryColor, 2),
                    BorderFactory.createEmptyBorder(13, 18, 13, 18)
            ));
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.DARK_GRAY);
        }

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!active) {
                    button.setBackground(new Color(240, 240, 240));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!active) {
                    button.setBackground(Color.WHITE);
                }
            }
        });

button.addActionListener(e -> {
    System.out.println("Navigating to: " + text);

     if (text.contains("Cart")) {
        new CartUI(new Cart(null)).setVisible(true);
        this.dispose();
        return;
    }
     
    if (text.contains("Employee")) {
        new EmployeeUI().setVisible(true);
        this.dispose();
        return;
    }

    if (text.contains("Customer")) {
        new CustomerUI().setVisible(true);
        this.dispose();
        return;
    }

    System.out.println("No action for: " + text);
});


        return button;
    }

    /**
     * Creates the main content area with header, filters, and invoice table
     * 
     * @return JPanel configured as the main content area
     */
    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(backgroundColor);
        mainContent.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
        mainContent.add(createFiltersPanel(), BorderLayout.CENTER);
        mainContent.add(createInvoiceTable(), BorderLayout.SOUTH);

        return mainContent;
    }

    /**
     * Creates the header panel with title and action buttons
     * 
     * @return JPanel configured as the header area
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(backgroundColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.setBackground(backgroundColor);

        JLabel title = new JLabel("Invoice Management");
        title.setFont(new Font("Dialog", Font.BOLD, 36));
        title.setForeground(textPrimaryColor);

        JLabel subtitle = new JLabel
        ("View, search, and manage all your invoices.");
        subtitle.setFont(new Font("Dialog", Font.PLAIN, 20));
        subtitle.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(backgroundColor);
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(subtitle);

        titlePanel.add(textPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(
                FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(backgroundColor);

        JButton exportBtn = createActionButton("📊 Export Report", false);
        JButton newInvoiceBtn = createActionButton
        ("➕ Create New Invoice", true);

        buttonPanel.add(exportBtn);
        buttonPanel.add(newInvoiceBtn);

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
        button.setPreferredSize(new Dimension(220, 60));
        button.setFont(new Font("Dialog", Font.BOLD, 18));
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

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(600, 60));
        searchField.setFont(new Font("Dialog", Font.PLAIN, 18));
        searchField.setText("Search by Invoice ID or Client Name...");
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().
                        equals("Search by Invoice ID or Client Name...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search by Invoice "
                            + "ID or Client Name...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        searchPanel.add(searchField);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        datePanel.setBackground(backgroundColor);

        startDateField = new JTextField();
        startDateField.setPreferredSize(new Dimension(180, 60));
        startDateField.setFont(new Font("Dialog", Font.PLAIN, 18));
        startDateField.setText("Start Date");
        startDateField.setForeground(Color.GRAY);

        endDateField = new JTextField();
        endDateField.setPreferredSize(new Dimension(180, 60));
        endDateField.setFont(new Font("Dialog", Font.PLAIN, 18));
        endDateField.setText("End Date");
        endDateField.setForeground(Color.GRAY);

        setupDateField(startDateField, "Start Date");
        setupDateField(endDateField, "End Date");

        datePanel.add(startDateField);
        datePanel.add(new JLabel(" - "));
        datePanel.add(endDateField);

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
     * Creates the invoice table with scroll pane and pagination
     * 
     * @return JPanel configured as the invoice table area
     */
    private JPanel createInvoiceTable() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(backgroundColor);

        tableModel = new InvoiceTableModel();
        invoiceTable = new JTable(tableModel);

        setupTouchFriendlyTable();

        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(createPaginationPanel(), BorderLayout.SOUTH);

        return tablePanel;
    }

    /**
     * Configures the invoice table for touch-friendly interaction
     * Sets row height, fonts, selection colors, and custom renderers
     */
    private void setupTouchFriendlyTable() {
        invoiceTable.setRowHeight(60);
        invoiceTable.setFont(new Font("Dialog", Font.PLAIN, 16));
        invoiceTable.setSelectionBackground(primaryColor.brighter());

        JTableHeader header = invoiceTable.getTableHeader();
        header.setFont(new Font("Dialog", Font.BOLD, 18));
        header.setPreferredSize(new Dimension(header.getWidth(), 50));
        header.setBackground(new Color(240, 240, 240));

        // Configure custom renderers
        invoiceTable.getColumnModel().getColumn(4).
                setCellRenderer(new StatusRenderer());
        invoiceTable.getColumnModel().getColumn(5).
                setCellRenderer(new ActionsRenderer());

        TableColumnModel columnModel = invoiceTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);

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
     * Creates the pagination controls for the invoice table
     * 
     * @return JPanel configured with pagination buttons
     */
    private JPanel createPaginationPanel() {
        JPanel paginationPanel = new JPanel(new BorderLayout());
        paginationPanel.setBackground(backgroundColor);
        paginationPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel pageInfo = new JLabel("Showing 1-5 of 100");
        pageInfo.setFont(new Font("Dialog", Font.PLAIN, 16));

        JPanel controlsPanel = new JPanel(new FlowLayout
        (FlowLayout.RIGHT, 5, 0));
        controlsPanel.setBackground(backgroundColor);

        JButton prevBtn = createPaginationButton("Previous");
        JButton page1Btn = createPaginationButton("1");
        JButton page2Btn = createPaginationButton("2");
        JButton page3Btn = createPaginationButton("3");
        JButton nextBtn = createPaginationButton("Next");

        // Mark page 2 as active
        page2Btn.setBackground(new Color(168, 208, 141, 76));
        page2Btn.setForeground(primaryColor.darker());
        page2Btn.setBorder(BorderFactory.createLineBorder(primaryColor, 1));

        controlsPanel.add(prevBtn);
        controlsPanel.add(page1Btn);
        controlsPanel.add(page2Btn);
        controlsPanel.add(page3Btn);
        controlsPanel.add(nextBtn);

        paginationPanel.add(pageInfo, BorderLayout.WEST);
        paginationPanel.add(controlsPanel, BorderLayout.EAST);

        return paginationPanel;
    }

    /**
     * Creates a pagination button with consistent styling
     * 
     * @param text The button text
     * @return JButton configured for pagination
     */
    private JButton createPaginationButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        button.setFont(new Font("Dialog", Font.PLAIN, 14));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        button.addActionListener(e -> {
            System.out.println("Page: " + text);
        });

        return button;
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
                System.err.println("Error creating application: " 
                        + e.getMessage());

                // Basic fallback
                JFrame frame = new JFrame("AgroVet POS");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);
                JLabel errorLabel = new JLabel
        ("Application started with basic configurations", JLabel.CENTER);
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
    private final String[] columnNames = {"Invoice ID", "Client Name", "Date", 
        "Amount", "Status", "Actions"};

    /**
     * Sample invoice data for demonstration
     */
    private final Object[][] data = {
        {"#INV-00123", "Green Valley Farms", "2024-08-15", "$1,250.00", 
            "Paid", "View"},
        {"#INV-00124", "Sunrise Stables", "2024-08-12", "$875.50", "Pending",
            "View"},
        {"#INV-00125", "Oakwood Cattle Co.", "2024-07-20", "$2,400.00", 
            "Overdue", "View"},
        {"#INV-00126", "Feather & Fur Pet Supplies", "2024-08-10", "$450.75", 
            "Paid", "View"},
        {"#INV-00127", "Riverbend Poultry", "2024-08-05", "$1,120.00",
            "Pending", "View"}
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
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        return button;
    }
}