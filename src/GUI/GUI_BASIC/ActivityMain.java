/*
 * Author: Dương Thành Trưởng
 */

package GUI.GUI_BASIC;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.*;

import GUI.GUI_HOME.PanelHome;
import GUI.GUI_KETQUA.KetQuaGUI;
import GUI.GUI_ONLINECOURSE.OnlineCourseGUI;
import GUI.GUI_ONSITECOURSE.OnsiteCourseGUI;
import GUI.GUI_ONSITECOURSE.jpnOnsite;
import GUI.GUI_PHANCONG.PhanCongGUI;

public class ActivityMain extends JFrame {

    private JPanel pnHeader = new JPanel();
    private JPanel pnMenu = new JPanel();
    private JPanel pnContent = new JPanel();
    private JPanel pnOnlineCourse,pnOnsiteCourse,pnPhanCong,pnKetQua;
    private ArrayList<JButton> btnMenu = new ArrayList<>();

    //font 
    private Font sgUI15 = new Font("Segoe UI", Font.BOLD, 15);
    private Font sgUI13 = new Font("Segoe UI", Font.PLAIN, 13);
    private Font sgUI18b = new Font("Segoe UI", Font.BOLD, 18);
    private Font tNR18b = new Font("Times New Roman", Font.BOLD, 18);

//    JButton btnMode = new JButton("Buổi tối");
//    JButton btnLogOut = new JButton("Đăng xuất");
    JButton btnZoomMenu = new JButton();

    //mode
    int LightDark = 0;
    //check button now
    JButton pos;
    //check zoom
    int zoom;
    int menu;

    public ActivityMain() {
        pnOnlineCourse = new OnlineCourseGUI();
        pnOnsiteCourse = new jpnOnsite();
        pnPhanCong = new PhanCongGUI();
        pnKetQua = new KetQuaGUI();
        initComponents();
    }

    public void initComponents() {
        setSize(1400, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("Phần mềm quản lý khóa học");

        //set button menu
        JButton btn1 = new JButton(" Màn hình chính");
        JButton btn2 = new JButton(" Khóa Học Online");
        JButton btn3 = new JButton(" Khóa Học Onsite");
        JButton btn4 = new JButton(" Phân Công Giảng Dạy");
        JButton btn5 = new JButton(" Kết Quả Học Tập");

        btnMenu.add(btn1);
        btnMenu.add(btn2);
        btnMenu.add(btn3);
        btnMenu.add(btn4);
        btnMenu.add(btn5);

        for (JButton x : btnMenu) {
            x.setFocusPainted(false);
            x.setBorder(new EmptyBorder(10, 10, 10, 10));
            x.setPreferredSize(new Dimension(200, 45));
            x.setMaximumSize(new Dimension(200, 45));
            x.setFont(sgUI15);
            x.setHorizontalAlignment(SwingConstants.LEFT);
        }

        //btn logout
//        btnLogOut.setFocusPainted(false);
//        btnLogOut.setPreferredSize(new Dimension(200, 40));
//        btnLogOut.setMaximumSize(new Dimension(200, 40));
//        btnLogOut.setFont(sgUI15);
//        ImageIcon iconLogOut = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//        btnLogOut.setIcon(iconLogOut);
//
//        //btn mode
//        btnMode.setFocusPainted(false);
//        btnMode.setPreferredSize(new Dimension(200, 40));
//        btnMode.setMaximumSize(new Dimension(200, 40));
//        btnMode.setFont(sgUI15);
//        ImageIcon iconMode = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/moon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//        btnMode.setIcon(iconMode);

        btnZoomMenu.setFocusable(false);
        btnZoomMenu.setMnemonic(KeyEvent.VK_Z);

        ImageIcon icon1 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/home.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn1.setIcon(icon1);
        ImageIcon icon2 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/online.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn2.setIcon(icon2);
        ImageIcon icon3 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/onsite.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn3.setIcon(icon3);
        ImageIcon icon4 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/nhanvien.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn4.setIcon(icon4);
        ImageIcon icon5 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/result.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        btn5.setIcon(icon5);
        ImageIcon icon6 = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/result.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        
        //action hover menu
        setMouse();
        //action hover mode
//        setMouseMode();
        //set pos
        pos = btn1;
        //set action 
        setAction();
//        //set action mode
//        setActionMode();
//        //set action logout
//        actionLogOut();
//        //set action zoom
        actionZoom();

        //set layout
        setLayout(new BorderLayout(0, 0));
        add(pnHeader, BorderLayout.NORTH);
        add(pnMenu, BorderLayout.WEST);
        add(pnContent, BorderLayout.CENTER);

        //set display panel header
        setDisplayHeader(pnHeader);

        //set display panel menu
        setDisplayMenu(pnMenu);

        //lightdark
        lightDark();

        //show tooltip
//        showToolTip();
        //set display panel content
        menu = 0;
        setDisplayContent();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    
                    dispose();
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        setVisible(true);
    }

    public void setDisplayHeader(JPanel pnHeader) {
        pnHeader.removeAll();
        pnHeader.revalidate();
        pnHeader.repaint();
        pnHeader.setLayout(new BorderLayout());
        //panel logo
        JPanel pnLogo = new JPanel();
        JLabel lbImg = new JLabel();
        JLabel lbLogo = new JLabel("Course Management Software");

        pnHeader.add(pnLogo, BorderLayout.WEST);

        //set logo
        pnLogo.setLayout(new BorderLayout());
        pnLogo.add(lbImg, BorderLayout.WEST);
        pnLogo.add(lbLogo, BorderLayout.CENTER);
        pnLogo.add(btnZoomMenu, BorderLayout.EAST);

        //set label
        lbLogo.setFont(tNR18b);
        //set img logo
        ImageIcon iconLogo = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/school.png")).getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH));
        lbLogo.setIcon(iconLogo);
        setIconImage(new ImageIcon(getClass().getResource("/GUI/assets/school.png")).getImage());
        //set icon btnzoommenu
        if (zoom == 0) {
            ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/x.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            btnZoomMenu.setIcon(iconMenu);
        } else {
            ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/menu.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            btnZoomMenu.setIcon(iconMenu);
        }
        if (LightDark == 1) {
            Color black = new Color(69, 69, 69);
            pnHeader.setBackground(black);
            pnLogo.setBackground(black);
            lbImg.setBackground(black);
            lbLogo.setForeground(Color.white);
        } else {
            pnHeader.setBackground(Color.decode("#c6d9f7"));
            pnLogo.setBackground(Color.decode("#c6d9f7"));
            lbImg.setBackground(Color.decode("#c6d9f7"));
            lbLogo.setForeground(Color.black);
        }
    }

//    PanelPhong pnPhong;
//    PanelKhachHang pnKhachHang;
//    PanelNhanVien pnNhanVien;
//    PanelDichVu pnDichVu;
//    HoaDonGUI pnHoaDon;
//    ThongKeGUI pnThongKe;

    public void setDisplayContent() {
        pnContent.removeAll();
        pnContent.revalidate();
        pnContent.repaint();
        pnContent.setLayout(new BorderLayout());
        switch (menu) {
            case 0:
                JPanel a = new PanelHome(getWidth(),getHeight());
                pnContent.setLayout(new BorderLayout());
                pnContent.add(a, BorderLayout.CENTER);
                break;
            case 1:
                pnContent.add(pnOnlineCourse, BorderLayout.CENTER);
                break;
            case 4:
                pnContent.add(pnKetQua, BorderLayout.CENTER);
                break;
            case 3:
                pnContent.add(pnPhanCong, BorderLayout.CENTER);
                break;
            case 2:
                pnContent.add(pnOnsiteCourse, BorderLayout.CENTER);
                break;
            
        }
    }

    public void setDisplayMenu(JPanel pnMenu) {
        pnMenu.removeAll();
        pnMenu.revalidate();
        pnMenu.repaint();
        JPanel pnTop = new JPanel();
        JPanel pnBottom = new JPanel();

        //set layout
        pnMenu.setLayout(new BorderLayout());
        pnMenu.add(pnTop, BorderLayout.CENTER);
        pnMenu.add(pnBottom, BorderLayout.SOUTH);

        pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
        for (JButton btn : btnMenu) {
            pnTop.add(btn);
        }

        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
//        pnBottom.add(btnMode);
//        pnBottom.add(btnLogOut);
        if (LightDark == 1) {
            Color black = new Color(51, 51, 51);
            pnTop.setBackground(black);
            pnBottom.setBackground(black);
        } else {
            pnTop.setBackground(Color.decode("#dee9fc"));
            pnBottom.setBackground(Color.decode("#dee9fc"));
        }
        pnBottom.setBorder(new EmptyBorder(5, 0, 5, 0));
    }

    public void setAction() {
        for (JButton x : btnMenu) {
            x.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == btnMenu.get(0)) {
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                        menu = 0;
                        setDisplayContent();
                    } else if (e.getSource() == btnMenu.get(1)) {
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                        menu = 1;
                        setDisplayContent();
                    } else if (e.getSource() == btnMenu.get(2)) {
                        menu = 2;
                        setDisplayContent();
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                    } else if (e.getSource() == btnMenu.get(3)) {
                        menu = 3;
                        setDisplayContent();
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                    } else if (e.getSource() == btnMenu.get(4)) {
                        menu = 4;
                        setDisplayContent();
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                    } else if (e.getSource() == btnMenu.get(5)) {
                        menu = 5;
                        setDisplayContent();
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                    } else if (e.getSource() == btnMenu.get(6)) {
                        menu = 6;
                        setDisplayContent();
                        settingColor(x, "#ebf2fc", "#FFFFFF", "#98befa");
                    }
                }
            });
        }
    }

    public void settingColor(JButton x, String colorLight, String colorDark, String colorPos) {
        setBackground();
        pos = x;
        if (LightDark == 0) {
            run(x, Color.decode(colorLight));
            pos.setBackground(Color.decode(colorPos));
        } else {
            run(x, Color.decode(colorDark));
        }
        revalidate();
        repaint();
    }

    public void setBackground() {
        for (JButton x : btnMenu) {
            if (LightDark == 0) {
                x.setBackground(Color.decode("#dee9fc"));
            } else {
                x.setBackground(new Color(51, 51, 51));
            }
        }
    }

    public void setMouse() {
        for (JButton x : btnMenu) {
            x.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (LightDark == 0) {
                        x.setBackground(Color.decode("#F5F5F5"));
                    } else {
                        x.setBackground(Color.decode("#4D4D4D"));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (LightDark == 0) {
                        if (x != pos) {
                            x.setBackground(Color.decode("#dee9fc"));
                        } else {
                            x.setBackground(Color.decode("#98befa"));
                        }
                    } else {
                        if (x != pos) {
                            x.setBackground(new Color(51, 51, 51));
                        } else {
                            x.setBackground(Color.decode("#4c4d4c"));
                        }
                    }
                }
            });
        }
    }

//    public void setMouseMode() {
//
//        btnLogOut.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                if (LightDark == 0) {
//                    btnLogOut.setBackground(Color.decode("#FFFFFF"));
//                } else {
//                    btnLogOut.setBackground(Color.decode("#A0A0A0"));
//                }
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                if (LightDark == 0) {
//                    btnLogOut.setBackground(Color.decode("#bad4ff"));
//                } else {
//                    btnLogOut.setBackground(Color.decode("#696969"));
//                }
//            }
//        });
//
//        btnMode.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                if (LightDark == 0) {
//                    btnMode.setBackground(Color.decode("#FFFFFF"));
//                } else {
//                    btnMode.setBackground(Color.decode("#A0A0A0"));
//                }
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                if (LightDark == 0) {
//                    btnMode.setBackground(Color.decode("#bad4ff"));
//                } else {
//                    btnMode.setBackground(Color.decode("#696969"));
//                }
//            }
//        });
//    }

    public synchronized void run(JButton btn, Color color) {
        new Thread(() -> {
            btn.setLayout(null);
            JPanel pn = new JPanel();
            pn.setBackground(color);
            JPanel pn1 = new JPanel();
            pn1.setBackground(color);
            btn.add(pn);
            btn.add(pn1);
            for (int i = 0; i <= btn.getWidth() / 2; i++) {
                pn.setBounds(0, 0, btn.getWidth() / 2 - i, btn.getHeight());
                pn1.setBounds(btn.getWidth() / 2 + i, 0, btn.getWidth() / 2, btn.getHeight());
                repaint();
                try {
                    if (zoom == 0) {
                        Thread.sleep(1);
                    } else {
                        Thread.sleep(5);
                    }
                } catch (InterruptedException e) {
                }
            }
            btn.remove(pn);
            btn.remove(pn1);
        }).start();
    }

    //action logout
//    public void actionLogOut() {
//        btnLogOut.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int ans = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                if (ans == JOptionPane.YES_OPTION) {
//                    new FormLogin();
//                    dispose();
//                }
//            }
//        });
//    }

    //action menu zoom 
    public void actionZoom() {
        btnZoomMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zoom == 0) {
                    ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/menu.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                    btnZoomMenu.setIcon(iconMenu);
                    zoom = 1;
//                    hideText();
                    run(btnMenu);
                    lightDark();
                    showToolTip();
//                    setDisplayContent();
                } else {
                    ImageIcon iconMenu = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/x.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
                    btnZoomMenu.setIcon(iconMenu);
                    zoom = 0;
                    showText();
                    run2(btnMenu);
                    lightDark();
                    showToolTip();
//                    setDisplayContent();
                }
            }
        });
    }

//    public void hideText() {
//        for (JButton x : btnMenu) {
//            x.setText("");
//        }
//        btnLogOut.setText("");
//        btnMode.setText("");
//    }

    public void showText() {
        btnMenu.get(0).setText(" Màn hình chính");
        btnMenu.get(1).setText(" Khóa Học Online");
        btnMenu.get(2).setText(" Khóa Học Onsite");
        btnMenu.get(3).setText(" Phân Công Giảng Dạy");
        btnMenu.get(4).setText(" Kết Quả Học Tập");
        
//        btnLogOut.setText("Đăng xuất");
//        if (LightDark == 0) {
//            btnMode.setText("Buổi tối");
//        } else {
//            btnMode.setText("Buổi sáng");
//        }
    }

    public void showToolTip() {
        if (zoom == 1) {
            btnMenu.get(0).setToolTipText("Sơ đồ phòng");
            btnMenu.get(1).setToolTipText("Quản lý Phòng");
            btnMenu.get(2).setToolTipText("Quản lý Dịch vụ");
            btnMenu.get(3).setToolTipText("Quản lý Nhân viên");
            btnMenu.get(4).setToolTipText("Quản lý Khách hàng");
            btnMenu.get(5).setToolTipText("Quản lý Hóa đơn");
            btnMenu.get(6).setToolTipText("Quản lý Thống kê");
        } else {
            btnMenu.get(0).setToolTipText(null);
            btnMenu.get(1).setToolTipText(null);
            btnMenu.get(2).setToolTipText(null);
            btnMenu.get(3).setToolTipText(null);
            btnMenu.get(4).setToolTipText(null);
            btnMenu.get(5).setToolTipText(null);
            btnMenu.get(6).setToolTipText(null);
        }
    }

    public synchronized void run(ArrayList<JButton> listbtn) {
        new Thread(() -> {
            for (int i = 200; i >= 50; i--) {
                for (JButton x : listbtn) {
                    x.setPreferredSize(new Dimension(i, 45));
                    x.setMaximumSize(new Dimension(i, 45));
                }
                pnMenu.setPreferredSize(new Dimension(i, 100));
//                btnMode.setPreferredSize(new Dimension(i, 40));
//                btnMode.setMaximumSize(new Dimension(i, 40));
//                btnLogOut.setPreferredSize(new Dimension(i, 40));
//                btnLogOut.setMaximumSize(new Dimension(i, 40));
                pnMenu.repaint();
                pnMenu.revalidate();
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    public synchronized void run2(ArrayList<JButton> listbtn) {
        new Thread(() -> {
            for (int i = 50; i <= 200; i++) {
                for (JButton x : listbtn) {
                    x.setPreferredSize(new Dimension(i, 45));
                    x.setMaximumSize(new Dimension(i, 45));
                }
//                btnMode.setPreferredSize(new Dimension(i, 40));
//                btnMode.setMaximumSize(new Dimension(i, 40));
//                btnLogOut.setPreferredSize(new Dimension(i, 40));
//                btnLogOut.setMaximumSize(new Dimension(i, 40));
                pnMenu.setPreferredSize(new Dimension(i, 100));
                pnMenu.repaint();
                pnMenu.revalidate();
                pnContent.repaint();
                pnContent.revalidate();
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }

    //setting mode
    //set lightdark action
//    public void setActionMode() {
//        btnMode.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (LightDark == 0) {
//                    if (zoom == 0) {
//                        btnMode.setText("Buổi sáng");
//                    }
//                    ImageIcon iconMode = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/dichvuicon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//                    btnMode.setIcon(iconMode);
//                    LightDark = 1;
//                    lightDark();
//                    revalidate();
//                    repaint();
//                } else {
//                    if (zoom == 0) {
//                        btnMode.setText("Buổi tối");
//                    }
//                    ImageIcon iconMode = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/moon.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//                    btnMode.setIcon(iconMode);
//                    LightDark = 0;
//                    lightDark();
//                    revalidate();
//                    repaint();
//                }
//            }
//        });
//    }

    public void lightDark() {
        if (LightDark == 0) {
//            pnPhong.lightDark(LightDark);
//            pnDichVu.lightDark(LightDark);
//            pnHoaDon.lightDark(LightDark);
//            pnKhachHang.lightDark(LightDark);
//            pnNhanVien.lightDark(LightDark);
            pnHeader.setBackground(Color.decode("#c6d9f7"));
            pnContent.setBackground(Color.white);
            pnMenu.setBackground(Color.decode("#c6d9f7"));
            setDisplayHeader(pnHeader);
            setDisplayMenu(pnMenu);
            for (JButton x : btnMenu) {
                x.setBackground(Color.decode("#dee9fc"));
                x.setForeground(Color.black);
            }
            if (zoom == 0) {
//                btnLogOut.setBorder(new MatteBorder(2, 10, 0, 10, Color.decode("#dee9fc")));
//                btnLogOut.setBackground(Color.decode("#bad4ff"));
//                btnLogOut.setForeground(Color.black);
//                btnMode.setBorder(new MatteBorder(0, 10, 2, 10, Color.decode("#dee9fc")));
//                btnMode.setBackground(Color.decode("#bad4ff"));
//                btnMode.setForeground(Color.black);
            } else {
//                btnLogOut.setBorder(new MatteBorder(2, 2, 0, 2, Color.decode("#dee9fc")));
//                btnLogOut.setBackground(Color.decode("#bad4ff"));
//                btnLogOut.setForeground(Color.black);
//                btnMode.setBorder(new MatteBorder(0, 2, 2, 2, Color.decode("#dee9fc")));
//                btnMode.setBackground(Color.decode("#bad4ff"));
//                btnMode.setForeground(Color.black);
            }
            btnZoomMenu.setBorder(new MatteBorder(0, 10, 0, 10, Color.decode("#c6d9f7")));
            btnZoomMenu.setBackground(Color.decode("#c6d9f7"));
            pos.setBackground(Color.decode("#98befa"));
            pnContent.setBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#F6FBFF")));
        } else {
            Color black = new Color(51, 51, 51);
//            pnPhong.lightDark(LightDark);
//            pnDichVu.lightDark(LightDark);
//            pnHoaDon.lightDark(LightDark);
//            pnKhachHang.lightDark(LightDark);
//            pnNhanVien.lightDark(LightDark);
            pnHeader.setBackground(black);
            pnContent.setBackground(Color.decode("#4c4d4c"));
            pnMenu.setBackground(black);
            setDisplayHeader(pnHeader);
            setDisplayMenu(pnMenu);
            for (JButton x : btnMenu) {
                x.setBackground(black);
                x.setForeground(Color.white);
            }
//            if (zoom == 0) {
//                btnLogOut.setBorder(new MatteBorder(2, 10, 0, 10, black));
//                btnLogOut.setBackground(Color.decode("#696969"));
//                btnLogOut.setForeground(Color.white);
//                btnMode.setBorder(new MatteBorder(0, 10, 2, 10, black));
//                btnMode.setBackground(Color.decode("#696969"));
//                btnMode.setForeground(Color.white);
//            } else {
//                btnLogOut.setBorder(new MatteBorder(2, 2, 0, 2, black));
//                btnLogOut.setBackground(Color.decode("#696969"));
//                btnLogOut.setForeground(Color.white);
//                btnMode.setBorder(new MatteBorder(0, 2, 2, 2, black));
//                btnMode.setBackground(Color.decode("#696969"));
//                btnMode.setForeground(Color.white);
//            }
            btnZoomMenu.setBorder(new MatteBorder(0, 10, 0, 10, new Color(69, 69, 69)));
            btnZoomMenu.setBackground(new Color(69, 69, 69));
            pos.setBackground(Color.decode("#4c4d4c"));
            pnContent.setBorder(new MatteBorder(10, 10, 10, 10, Color.decode("#3C3C3C")));
        }
    }
}
