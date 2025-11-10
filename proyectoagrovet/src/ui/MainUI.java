
package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class MainUI {

public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new InvoiceManagement().setVisible(true);
            } catch (Exception e) {
                System.err.println("Error al crear la aplicación: " 
                        + e.getMessage());
                
                JFrame frame = new JFrame("AgroVet POS");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);
                JLabel errorLabel = new JLabel
        ("La aplicación se inició con configuraciones básicas", JLabel.CENTER);
                frame.add(errorLabel);
                frame.setVisible(true);
            }
        });
    }
}

class InvoiceTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Invoice ID", "Client Name", "Date", 
        "Amount", "Status", "Actions"};
    private final Object[][] data = {
        {"#INV-00123", "Green Valley Farms", "2024-08-15", "$1,250.00", 
            "Paid", "View"},
        {"#INV-00124", "Sunrise Stables", "2024-08-12", "$875.50", 
            "Pending", "View"},
        {"#INV-00125", "Oakwood Cattle Co.", "2024-07-20", "$2,400.00", 
            "Overdue", "View"},
        {"#INV-00126", "Feather & Fur Pet Supplies", "2024-08-10", "$450.75", 
            "Paid", "View"},
        {"#INV-00127", "Riverbend Poultry", "2024-08-05", "$1,120.00", 
            "Pending", "View"}
    };
    
    @Override
    public int getRowCount() {
        return data.length;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5;
    }
}

class StatusRenderer extends DefaultTableCellRenderer {
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

class ActionsRenderer extends DefaultTableCellRenderer {
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