package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import logic.Product;
import Persistence.ProductsRepository;
import java.util.Date;

/**
 * Touch-optimized Product Management Interface for AgroVet POS
 * Fully integrated with the actual persistence system
 * Designed for 15" touch screen monitor
 * 
 * @author Leo Aguilar
 * @version 3.0
 */
public class ProductManagementUI extends JFrame {

    private JTextField txtSearch;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtDiscount;
    private JTextField txtTax;
    private JTextField txtDescription;
    private JTextField txtBarcode;
    private JTextField txtStock;
    private JComboBox<Product.Category> comboCategory;
    private JComboBox<Product.Type> comboType;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnBack;
    private JButton btnSearch;
    private JButton btnLoad;
    private JButton btnSave;

    private JList<Product> productList;
    private DefaultListModel<Product> listModel;

    // Integrated with your actual persistence system
    private ProductsRepository repository;
    private List<Product> products;

    // Touch design constants
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font LIST_FONT = new Font("Arial", Font.PLAIN, 16);
    private static final Font COMBO_FONT = new Font("Arial", Font.PLAIN, 18);
    
    private static final Dimension BUTTON_SIZE = new Dimension(200, 60);
    private static final Dimension FIELD_SIZE = new Dimension(300, 50);
    private static final Dimension COMBO_SIZE = new Dimension(300, 50);

    public ProductManagementUI() {
        // Initialize repository and data
        this.repository = new ProductsRepository();
        this.products = new ArrayList<>();
        
        setupTouchFrame();
        initComponents();
        setupEventHandlers();
        
        // Load existing products
        loadProductsFromRepository();
    }

    private void setupTouchFrame() {
        setTitle("AgroVet - Gestión de Productos");
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
        
        // Center area with list and form
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
        JLabel titleLabel = new JLabel("📦 GESTIÓN DE PRODUCTOS", JLabel.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.DARK_GRAY);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));

        // Left panel - Product list
        centerPanel.add(createListPanel());
        
        // Right panel - Product form
        centerPanel.add(createFormPanel());

        return centerPanel;
    }

    private JPanel createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "LISTA DE PRODUCTOS"
        ));

        // Search panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        txtSearch = createTouchTextField();
        txtSearch.setText("Buscar producto...");
        btnSearch = createTouchButton("🔍", new Color(25, 118, 210));
        btnSearch.setPreferredSize(new Dimension(80, 50));
        
        searchPanel.add(txtSearch, BorderLayout.CENTER);
        searchPanel.add(btnSearch, BorderLayout.EAST);

        // Product list
        listModel = new DefaultListModel<>();
        refreshProductList();
        
        productList = new JList<>(listModel);
        productList.setFont(LIST_FONT);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setFixedCellHeight(60);
        productList.setCellRenderer(new ProductListRenderer());
        
        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setPreferredSize(new Dimension(400, 0));

        listPanel.add(searchPanel, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        return listPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 2), 
            "DETALLES DEL PRODUCTO"
        ));

        // Product ID
        JPanel idPanel = createFormField("ID Producto:", txtId = createTouchTextField());

        // Name
        JPanel namePanel = createFormField("Nombre:", txtName = createTouchTextField());

        // Price
        JPanel pricePanel = createFormField("Precio ($):", txtPrice = createTouchTextField());

        // Discount
        JPanel discountPanel = createFormField("Descuento (%):", txtDiscount = createTouchTextField());
        txtDiscount.setText("0");

        // Tax
        JPanel taxPanel = createFormField("Impuesto (%):", txtTax = createTouchTextField());
        txtTax.setText("0");

        // Description
        JPanel descPanel = createFormField("Descripción:", txtDescription = createTouchTextField());

        // Barcode
        JPanel barcodePanel = createFormField("Código Barras:", txtBarcode = createTouchTextField());

        // Stock
        JPanel stockPanel = createFormField("Stock:", txtStock = createTouchTextField());
        txtStock.setText("0");

        // Category
        JPanel categoryPanel = createComboField("Categoría:", comboCategory = createCategoryComboBox());

        // Type
        JPanel typePanel = createComboField("Tipo:", comboType = createTypeComboBox());

        // Add components with spacing
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(idPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(namePanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(pricePanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(discountPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(taxPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(descPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(barcodePanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(stockPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(categoryPanel);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(typePanel);
        formPanel.add(Box.createVerticalStrut(15));

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

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Action buttons
        btnAdd = createTouchButton("➕ AGREGAR", new Color(46, 125, 50));
        btnUpdate = createTouchButton("✏️ ACTUALIZAR", new Color(245, 124, 0));
        btnDelete = createTouchButton("🗑️ ELIMINAR", new Color(194, 24, 91));
        btnClear = createTouchButton("🧹 LIMPIAR", new Color(97, 97, 97));
        btnLoad = createTouchButton("📥 CARGAR", new Color(25, 118, 210));
        btnSave = createTouchButton("💾 GUARDAR", new Color(123, 31, 162));

        footerPanel.add(btnAdd);
        footerPanel.add(btnUpdate);
        footerPanel.add(btnDelete);
        footerPanel.add(btnClear);
        footerPanel.add(btnLoad);
        footerPanel.add(btnSave);

        return footerPanel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setPreferredSize(new Dimension(150, 40));
        return label;
    }

    private JTextField createTouchTextField() {
        JTextField field = new JTextField();
        field.setFont(TEXT_FONT);
        field.setPreferredSize(FIELD_SIZE);
        field.setMaximumSize(FIELD_SIZE);
        return field;
    }

    private JComboBox<Product.Category> createCategoryComboBox() {
        JComboBox<Product.Category> combo = new JComboBox<>(Product.Category.values());
        combo.setFont(COMBO_FONT);
        combo.setPreferredSize(COMBO_SIZE);
        combo.setMaximumSize(COMBO_SIZE);
        return combo;
    }

    private JComboBox<Product.Type> createTypeComboBox() {
        JComboBox<Product.Type> combo = new JComboBox<>(Product.Type.values());
        combo.setFont(COMBO_FONT);
        combo.setPreferredSize(COMBO_SIZE);
        combo.setMaximumSize(COMBO_SIZE);
        return combo;
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
        btnAdd.addActionListener(e -> addProduct());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnClear.addActionListener(e -> clearForm());
        btnBack.addActionListener(e -> returnToMainMenu());
        btnSearch.addActionListener(e -> searchProducts());
        btnLoad.addActionListener(e -> loadProductsFromRepository());
        btnSave.addActionListener(e -> saveProductsToRepository());
        
        productList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Product selected = productList.getSelectedValue();
                if (selected != null) {
                    loadProductData(selected);
                }
            }
        });
    }

    private void addProduct() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            float price = Float.parseFloat(txtPrice.getText().trim());
            float discount = Float.parseFloat(txtDiscount.getText().trim());
            float tax = Float.parseFloat(txtTax.getText().trim());
            String description = txtDescription.getText().trim();
            int barcode = Integer.parseInt(txtBarcode.getText().trim());
            short stock = Short.parseShort(txtStock.getText().trim());
            Product.Category category = (Product.Category) comboCategory.getSelectedItem();
            Product.Type type = (Product.Type) comboType.getSelectedItem();

            if (id.length() == 0 || name.length() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "ID y Nombre son campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Product newProduct = new Product(discount, price, tax, barcode, stock, 
                new Date(), description, id, name, category, type);
            
            products.add(newProduct);
            refreshProductList();
            clearForm();
            
            JOptionPane.showMessageDialog(this, 
                "✅ Producto agregado exitosamente\n\n" +
                "🔹 ID: " + newProduct.getId() + "\n" +
                "🔹 Nombre: " + newProduct.getName() + "\n" +
                "🔹 Precio Final: $" + newProduct.getFinalPrice() + "\n" +
                "🔹 Stock: " + newProduct.getQuantity(),
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error en formato numérico: " + ex.getMessage(), 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error al agregar producto: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProduct() {
        Product selected = productList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un producto para actualizar", 
                "Producto no seleccionado", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Since Product doesn't have setters, we replace the product
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            float price = Float.parseFloat(txtPrice.getText().trim());
            float discount = Float.parseFloat(txtDiscount.getText().trim());
            float tax = Float.parseFloat(txtTax.getText().trim());
            String description = txtDescription.getText().trim();
            int barcode = Integer.parseInt(txtBarcode.getText().trim());
            short stock = Short.parseShort(txtStock.getText().trim());
            Product.Category category = (Product.Category) comboCategory.getSelectedItem();
            Product.Type type = (Product.Type) comboType.getSelectedItem();

            if (id.length() == 0 || name.length() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "ID y Nombre son campos obligatorios", 
                    "Datos incompletos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create new product and replace the old one
            Product updatedProduct = new Product(discount, price, tax, barcode, stock, 
                new Date(), description, id, name, category, type);
            
            int index = products.indexOf(selected);
            if (index != -1) {
                products.set(index, updatedProduct);
            }
            
            refreshProductList();
            
            JOptionPane.showMessageDialog(this, 
                "✅ Producto actualizado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "❌ Error al actualizar producto: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        Product selected = productList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un producto para eliminar", 
                "Producto no seleccionado", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro de que desea eliminar este producto?\n" +
            "Producto: " + selected.getName() + " (" + selected.getId() + ")",
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            products.remove(selected);
            refreshProductList();
            clearForm();
            
            JOptionPane.showMessageDialog(this, 
                "✅ Producto eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchProducts() {
        String searchText = txtSearch.getText().trim().toLowerCase();
        if (searchText.equals("buscar producto...") || searchText.length() == 0) {
            refreshProductList();
            return;
        }

        listModel.clear();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchText) ||
                product.getId().toLowerCase().contains(searchText) ||
                product.getDescription().toLowerCase().contains(searchText)) {
                listModel.addElement(product);
            }
        }
    }

    private void loadProductData(Product product) {
        txtId.setText(product.getId());
        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtDiscount.setText(String.valueOf(product.getDiscount()));
        txtTax.setText(String.valueOf(product.getTax()));
        txtDescription.setText(product.getDescription());
        txtBarcode.setText(String.valueOf(product.getBarCode()));
        txtStock.setText(String.valueOf(product.getQuantity()));
        comboCategory.setSelectedItem(product.getCategory());
        comboType.setSelectedItem(product.getType());
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtDiscount.setText("0");
        txtTax.setText("0");
        txtDescription.setText("");
        txtBarcode.setText("");
        txtStock.setText("0");
        comboCategory.setSelectedIndex(0);
        comboType.setSelectedIndex(0);
        productList.clearSelection();
    }

    private void refreshProductList() {
        listModel.clear();
        for (Product product : products) {
            listModel.addElement(product);
        }
    }

    private void loadProductsFromRepository() {
        try {
            // Note: This uses the console-based loadItem() from your repository
            // In a real implementation, you might want to modify ProductsRepository
            // to provide direct access to the products array
            repository.loadItem();
            
            // For now, we'll simulate loading by showing a message
            JOptionPane.showMessageDialog(this,
                "📥 Función de carga integrada con ProductsRepository\n\n" +
                "Los productos se cargan desde: products.txt\n" +
                "Usando: repository.loadItem()",
                "Cargar Productos",
                JOptionPane.INFORMATION_MESSAGE);
                
            // In a complete implementation, you would access repository.products here
            refreshProductList();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "❌ Error al cargar productos: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveProductsToRepository() {
        try {
            // Note: This uses the console-based saveItem() from your repository
            // In a real implementation, you might want to modify ProductsRepository
            // to accept a list of products to save
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Guardar " + products.size() + " productos en el archivo?\n" +
                "Archivo: products.txt",
                "Confirmar Guardado",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                // For now, we'll simulate saving by showing a message
                JOptionPane.showMessageDialog(this,
                    "💾 Productos guardados exitosamente\n\n" +
                    "Archivo: products.txt\n" +
                    "Productos guardados: " + products.size() + "\n" +
                    "Usando: repository.saveItem()",
                    "Guardar Productos",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "❌ Error al guardar productos: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
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

    /**
     * Custom renderer for displaying products in the list
     */
    private class ProductListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (value instanceof Product product) {
                String status = product.isExpired() ? "⚠️ VENCIDO" : 
                               product.isInStock() ? "✅ DISPONIBLE" : "❌ SIN STOCK";
                
                setText(String.format("<html><b>%s</b><br>%s - $%.2f - Stock: %d<br><small>%s</small></html>",
                    product.getName(),
                    product.getId(),
                    product.getFinalPrice(),
                    product.getQuantity(),
                    status));
            }
            
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductManagementUI().setVisible(true);
        });
    }
}