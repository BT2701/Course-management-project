/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_HOME;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageSliderPanel extends JPanel implements ActionListener{
    private static final int SLIDE_DELAY = 3000; // Thời gian chuyển đổi ảnh
    private ImageIcon[] images;
    private int currentImageIndex;
    private Timer timer;
    private JLabel label;

    public ImageSliderPanel(String[] imagePaths, int width, int height) {
        images = new ImageIcon[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource(imagePaths[i])).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
            images[i] = iconMenu;
        }
        currentImageIndex = 0;
        label = new JLabel(images[0]);
        setLayout(new BorderLayout());
        add(label,BorderLayout.CENTER);
        timer = new Timer(SLIDE_DELAY, this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        currentImageIndex++;
        if(currentImageIndex == images.length) {
            currentImageIndex = 0;
        }
        label.setIcon(images[currentImageIndex]);
    }
}
