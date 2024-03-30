package GUI.GUI_KETQUA;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

class QuoteCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JTextField textField = new JTextField();

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField.setText(value != null ? value.toString() : "");
        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        // Allow selecting the cell
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        // Stop editing the cell
        return super.stopCellEditing();
    }
}