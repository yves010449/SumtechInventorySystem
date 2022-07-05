/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yves0
 */
public class InventoryTable extends AbstractTableModel {

    private String[] columnNames = {
        "ID", "NAME", "PRICE", "QUANTITY", "TOTAL INVENTORY", "QUALITY"
    };

    // TableModel's data
    private Object[][] data = {
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {0, "Laptop", 9000, 2, 2, 1},
        {1, "Radio", 6000, 1, 1, 0},
        {2, "SSD", 1000, 2, 2, 1}
    };

    /**
     * Returns the number of rows in the table model.
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Returns the number of columns in the table model.
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the column name for the column index.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns data type of the column specified by its index.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /**
     * Returns the value of a table model at the specified row index and column
     * index.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 3:
                return true;
            default:
                return false;
        }
    }
}
