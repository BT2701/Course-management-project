/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_HOME;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class PanelHome extends JPanel {

    public PanelHome(int width, int height) {
        init(width, height);
    }

    public void init(int width, int height) {
        setLayout(new BorderLayout());
        add(new ImageSliderPanel(new String[]{"/GUI/assets/bn1.png", "/GUI/assets/bn2.png","/GUI/assets/bn3.png"}, width, height),BorderLayout.CENTER);
    }
}
