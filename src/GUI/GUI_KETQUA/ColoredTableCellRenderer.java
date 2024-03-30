/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_KETQUA;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ColoredTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JLabel lb = (JLabel) cellComponent;
        lb.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode("#EEEEEE")));
        if(column == table.getColumnModel().getColumnIndex("Điểm Số"))
    		lb.setHorizontalAlignment(JLabel.CENTER);
        try {
        	if(Float.parseFloat(value.toString())<1) {
    			cellComponent.setForeground(Color.RED);
    		}
    		else {
    			cellComponent.setForeground(Color.green);
    		}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			if(value.toString().equalsIgnoreCase("Không Đạt")) {
				cellComponent.setForeground(Color.RED);
			}
			else if(value.toString().equalsIgnoreCase("Chưa Nhập Điểm")) {
				cellComponent.setForeground(Color.orange);
			}
			else {
				cellComponent.setForeground(Color.green);
			}
		}
        

        return cellComponent;
    }
}
