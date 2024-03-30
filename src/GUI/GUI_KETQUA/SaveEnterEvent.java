package GUI.GUI_KETQUA;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JViewport;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import BUS.KetQuaBUS;
import GUI.GUI_BASIC.ThongBaoDialog;

public class SaveEnterEvent implements KeyListener {
	private AddOrUpdateForm form;
	private Rectangle initialRect;

	public SaveEnterEvent(AddOrUpdateForm form) {
		this.form = form;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < form.pnItems.size(); i++) {
			if (e.getSource() == form.tfGrades.get(i)) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (form.tfGrades.get(i).getText().equalsIgnoreCase("")) {
						new ThongBaoDialog("Không để trống", ThongBaoDialog.INFO_DIALOG);
						return;
					}

					if (Float.parseFloat(form.tfGrades.get(i).getText()) > 4) {
						new ThongBaoDialog("Tối đa 4", ThongBaoDialog.INFO_DIALOG);
						return;
					}
					if (i == form.pnItems.size() - 1) {
//						lưu và hiện thông báo
						// khu vực xử lý lưu
						int grade= Integer.parseInt(form.tfGrades.get(i).getText());
						String fullname=form.lbStudents.get(i).getText();
						String course=form.lbCourses.get(i).getText();
						int courseid=Integer.parseInt(course.split(" - ")[0]);
						KetQuaBUS.getInstance().updateGrade(grade, courseid, fullname);
						//end

						new ThongBaoDialog("Hoàn Tất", ThongBaoDialog.SUCCESS_DIALOG);
					} else {
						// khu vực xử lý lưu
						int grade= Integer.parseInt(form.tfGrades.get(i).getText());
						String fullname=form.lbStudents.get(i).getText();
						String course=form.lbCourses.get(i).getText();
						int courseid=Integer.parseInt(course.split(" - ")[0]);
						KetQuaBUS.getInstance().updateGrade(grade, courseid, fullname);
						//end
						
						int bonus = 1;
						form.tfGrades.get(i + 1).requestFocus();
						JViewport viewport = form.scrContainer.getViewport();
						Dimension size = form.pnScroll.getPreferredSize();
						if (form.location >= (size.height - 1)) {
							viewport.scrollRectToVisible(new Rectangle(0, size.height - 1, 1, 1));
						}
						Rectangle rect = new Rectangle(0, form.location += bonus, 1, 1);
						viewport.scrollRectToVisible(rect);

					}

				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
