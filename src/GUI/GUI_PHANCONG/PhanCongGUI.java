package GUI.GUI_PHANCONG;

import BUS.PhanCongBUS;
import DAO.PhanCongDAO;
import DTO.PersonDTO;
import DTO.PhanCongDTO;
import DTO.courseDTO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class PhanCongGUI extends JPanel {
    Color primaryColor = Color.decode("#dee9fc");
    private JTable table;
    JButton showDetailButton;
    JButton addButton;
    JButton editButton;
    JButton deleteButton;
    JTextField searchField;
    String[] courseValue;
    String[] personValue;
    JComboBox<String> personComboBox;
    JComboBox<String> courseComboBox;
    String selectedOption;
    JScrollPane scrollPane;
    PhanCongBUS phanCongBUS = new PhanCongBUS();

    //    tạo để dùng edit
    PhanCongDTO oldPc;
    JPanel allContentPn;

    Font buttonFont = new Font("Segoe UI", Font.BOLD, 15);

    private TableRowSorter<TableModel> rowSorter = null;

    public PhanCongGUI() {
        setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        // Tạo headerPanel
        JLabel header = new JLabel("Phân công giảng dạy");
        header.setForeground(Color.BLACK);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = new Font("Segoe UI", Font.BOLD, 25);
        header.setFont(font);
        header.setPreferredSize(new Dimension(300, 40));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(primaryColor);
        headerPanel.add(header);

        // Tạo createContentPanel
        JPanel contentPanel = createContentPanel();

        // Thêm headerPanel và contentPanel vào PhanCongGUI
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(contentPanel, BoxLayout.X_AXIS);
        contentPanel.setLayout(boxlayout);
        contentPanel.setBackground(Color.WHITE);

        createAllContent();
        contentPanel.add(allContentPn);

        return contentPanel;
    }

    private void createAllContent() {
        allContentPn = new JPanel(new BorderLayout());
        allContentPn.setBackground(Color.WHITE);
        allContentPn.setBorder(new EmptyBorder(15, 15, 15, 10)); // Thiết lập padding 10px cho mỗi phía

        ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCong();
        loadDataToTable(pcLst);

        JPanel panel = new JPanel();
        String[] sortOption = {"Mặc định", "Tên giảng viên", "ID giảng viên", "Tên môn học", "ID môn học"};
        // Tạo JComboBox với danh sách dữ liệu
        JComboBox<String> sortComboBox = new JComboBox<>(sortOption);
        createStyleCombobox(sortComboBox);
        // Click vào sẽ dựa theo lựa chọn để sắp xếp
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                if (selectedOption.equals("Tên giảng viên")) {
                    ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCongAfterSortingByPersonName();
                    loadDataToTable(pcLst);
                } else if (selectedOption.equals("ID giảng viên")) {
                    ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCongAfterSortingByPersonID();
                    loadDataToTable(pcLst);
                } else if (selectedOption.equals("Tên môn học")) {
                    ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCongAfterSortingByCourseTitle();
                    loadDataToTable(pcLst);
                } else if (selectedOption.equals("ID môn học")) {
                    ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCongAfterSortingByCourseID();
                    loadDataToTable(pcLst);
                } else {
                    ArrayList<PhanCongDTO> pcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCong();
                    loadDataToTable(pcLst);
                }
            }
        });

        panel.add(new JLabel(" Sắp xếp theo : "));
        panel.add(sortComboBox);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(0,0,10,0));

        // Tạo phần tìm kiếm
        JPanel searchPn = createSearchPanel();
        searchPn.setBorder(new EmptyBorder(0, 50, 0, 0));
        panel.add(searchPn);

        addButton = new JButton("Thêm");
        addButton.setFont(buttonFont);
        ImageIcon addButtonIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/save.jpg")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        addButton.setIcon(addButtonIcon);
        addButton.setBackground(Color.decode("#ebf2fc"));
        addButton.setForeground(new Color(50,205,50));
        addButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        addButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.addActionListener(new AddButtonListener());

        editButton = new JButton("Sửa");
        editButton.setFont(buttonFont);
        ImageIcon editButtonIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/sua.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        editButton.setIcon(editButtonIcon);
        editButton.setForeground(new Color(0,191,255));
        editButton.setBackground(Color.decode("#ebf2fc"));
        editButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        editButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editButton.addActionListener(new EditButtonListener());

        deleteButton = new JButton("Xóa");
        deleteButton.setFont(buttonFont);
        ImageIcon deleteButtonIcon = new ImageIcon(new ImageIcon(getClass().getResource("/GUI/assets/xoa.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        deleteButton.setIcon(deleteButtonIcon);
        deleteButton.setForeground(Color.red);
        deleteButton.setBackground(Color.decode("#ebf2fc"));
        deleteButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        deleteButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new DeleteButtonListener());

        showDetailButton = new JButton("Xem chi tiết");
        showDetailButton.setFont(buttonFont);
        showDetailButton.setBackground(Color.decode("#ebf2fc"));
        showDetailButton.setForeground(Color.BLACK);
        showDetailButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        showDetailButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        showDetailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showDetailButton.addActionListener(new ShowDetailButtonListener());

        JPanel buttonPn = new JPanel();
        buttonPn.setBackground(Color.WHITE);
        buttonPn.add(showDetailButton);
        buttonPn.add(addButton);
        buttonPn.add(editButton);
        buttonPn.add(deleteButton);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.add(panel);
        wrapperPanel.add(buttonPn);
        wrapperPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        wrapperPanel.setBackground(Color.WHITE);

        allContentPn.add(wrapperPanel, BorderLayout.NORTH);
    }

    private JPanel createSearchPanel() {
        JPanel searchPn = new JPanel();
        searchPn.setBackground(Color.WHITE);

        // Danh sách dữ liệu cho JComboBox
        ArrayList<courseDTO> courseLst = (ArrayList<courseDTO>) PhanCongDAO.getAllCourse();
        ArrayList<PersonDTO> personLst = (ArrayList<PersonDTO>) PhanCongDAO.getAllPerson();
        courseValue = Stream.concat(
                Stream.of(""),
                courseLst.stream()
                        .map(courseDTO -> courseDTO.getId() + "-" + courseDTO.getTittle())
        ).toArray(size -> new String[size]);

        personValue = Stream.concat(
                Stream.of(""),
                personLst.stream()
                        .map(personDTO -> personDTO.getId() + "-" + personDTO.getFirstName() + " " + personDTO.getLastName())
        ).toArray(size -> new String[size]);

        // Tạo JComboBox với danh sách dữ liệu
        personComboBox = new JComboBox<>(personValue);
        createStyleCombobox(personComboBox);
        personComboBox.setBackground(Color.WHITE);

        courseComboBox = new JComboBox<>(courseValue);
        createStyleCombobox(courseComboBox);
        courseComboBox.setBackground(Color.WHITE);

        String[] searchOption = {"Tìm kiếm theo tên GV", "Tìm kiếm theo ID GV", "Tìm kiếm theo tên MH", "Tìm kiếm theo ID MH"};
        // Tạo JComboBox với danh sách dữ liệu
        JComboBox<String> searchComboBox = new JComboBox<>(searchOption);
        createStyleCombobox(searchComboBox);
        searchComboBox.setPreferredSize(new Dimension(160, 30));
        // Click vào sẽ dựa theo lựa chọn để sắp xếp
        selectedOption = (String) searchComboBox.getSelectedItem();
        searchComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = (String) searchComboBox.getSelectedItem();
            }
        });

        Font textFieldFont = new Font("Segoe UI", Font.BOLD, 13);

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        setBorderForJTextField(searchField);
        searchField.setFont(textFieldFont);

        // tìm kiếm theo tên giảng viên,...
        // tạo TableRowSorter để có thể sắp xếp và lọc các hàng trong bảng
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // JOptionPane.showMessageDialog(PhanCongGUI.this, selectedOption);
                String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    // số 1 là tìm kiếm theo cột thứ 2 (vì bắt đầu từ 0,1,...)
                    // "(?i)" + text tạo ra một biểu thức chính quy trong đó việc tìm kiếm sẽ không phân biệt chữ hoa chữ thường và sẽ tìm kiếm dựa trên chuỗi text.
                    if (selectedOption.equals("Tìm kiếm theo ID GV")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (selectedOption.equals("Tìm kiếm theo tên GV")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    }else if (selectedOption.equals("Tìm kiếm theo ID MH")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 3));
                    }
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
//                JOptionPane.showMessageDialog(PhanCongGUI.this, selectedOption);
                String text = searchField.getText().trim();
                if (text.isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    // số 1 là tìm kiếm theo cột thứ 2 (vì bắt đầu từ 0,1,...)
                    // "(?i)" + text tạo ra một biểu thức chính quy trong đó việc tìm kiếm sẽ không phân biệt chữ hoa chữ thường và sẽ tìm kiếm dựa trên chuỗi text.
                    if (selectedOption.equals("Tìm kiếm theo ID GV")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (selectedOption.equals("Tìm kiếm theo tên GV")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    }else if (selectedOption.equals("Tìm kiếm theo ID MH")) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 3));
                    }
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        searchPn.add(searchComboBox);
        searchPn.add(searchField);

        return searchPn;
    }
    // Xử lý sự kiện khi JButton được nhấn
    private class ShowDetailButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            String personID = "";
            String courseID = "";

            if (selectedRow != -1) { // Kiểm tra dòng được chọn
                personID = table.getValueAt(selectedRow, 0).toString();
                courseID = table.getValueAt(selectedRow, 2).toString();

                oldPc = new PhanCongDTO(Integer.parseInt(personID), Integer.parseInt(courseID));
            }

            if (personID.equals("") || courseID.equals("")) {
                JOptionPane.showMessageDialog(PhanCongGUI.this,"Vui lòng chọn thông tin cần hiển thị chi tiết");
                return;
            }

            showDetailDialog();
        }
    }
    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           showAddDialog();
        }
    }
    private class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            String personID = "";
            String courseID = "";

            if (selectedRow != -1) { // Kiểm tra dòng được chọn
                personID = table.getValueAt(selectedRow, 0).toString();
                courseID = table.getValueAt(selectedRow, 2).toString();

                oldPc = new PhanCongDTO(Integer.parseInt(personID), Integer.parseInt(courseID));
            }

            if (personID.equals("") || courseID.equals("")) {
                JOptionPane.showMessageDialog(PhanCongGUI.this,"Vui lòng chọn thông tin cần sửa");
                return;
            }

            showEditDialog();
        }
    }
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                int selectedRow = table.getSelectedRow();
                String person = "";
                String course = "";

                if (selectedRow != -1) { // Kiểm tra dòng được chọn
                    String personID = table.getValueAt(selectedRow, 0).toString();
                    String pernameName = table.getValueAt(selectedRow, 1).toString();
                    String courseID = table.getValueAt(selectedRow, 2).toString();
                    String courseTitle = table.getValueAt(selectedRow, 3).toString();

                    person = personID + "-" + pernameName;
                    course = courseID + "-" + courseTitle;
                }

                if (person.equals("") || course.trim().equals(""))
                    JOptionPane.showMessageDialog(PhanCongGUI.this,"Vui lòng chọn thông tin cần xóa");
                else {
                    String[] personParts = person.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                    int personID = Integer.parseInt(personParts[0]); // Lấy phần tử đầu tiên là ID

                    String[] courseParts = course.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                    int courseID = Integer.parseInt(courseParts[0]); // Lấy phần tử đầu tiên là ID

                    int option = JOptionPane.showConfirmDialog(PhanCongGUI.this, "Bạn có muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(PhanCongGUI.this, phanCongBUS.deletePhanCong(personID, courseID));
                    }
                    // JOptionPane.showMessageDialog(PhanCongGUI.this, personID + "---" + courseID);

                    ArrayList<PhanCongDTO> newPcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCong();
                    loadDataToTable(newPcLst);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PhanCongGUI.this, "Thông tin không hợp lệ");
            }
        }
    }
    private void showDetailDialog() {
        JDialog editDialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(PhanCongGUI.this), "Sửa Phân Công");
        editDialog.setSize(400, 220);
        editDialog.setLocationRelativeTo(null); // Đặt JDialog ở giữa màn hình

        Font textFieldFont = new Font("Segoe UI", Font.BOLD, 13);
        JTextField personInput = new JTextField("");
        personInput.setPreferredSize(new Dimension(200, 30));
        setBorderForJTextField(personInput);
        personInput.setFont(textFieldFont);
        personInput.setEditable(false);

        JTextField courseInput = new JTextField("");
        courseInput.setPreferredSize(new Dimension(200, 30));
        setBorderForJTextField(courseInput);
        courseInput.setFont(textFieldFont);
        courseInput.setEditable(false);

        // lấy dữ liệu từ dòng cần hiển thị ra combobox
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Kiểm tra dòng được chọn
            String personID = table.getValueAt(selectedRow, 0).toString();
            String pernameName = table.getValueAt(selectedRow, 1).toString();
            String courseID = table.getValueAt(selectedRow, 2).toString();
            String courseTitle = table.getValueAt(selectedRow, 3).toString();

            // để edit
            oldPc = new PhanCongDTO(Integer.parseInt(courseID), Integer.parseInt(personID));

            personInput.setText(personID + "-" + pernameName);
            courseInput.setText(courseID + "-" + courseTitle);
        }

        JPanel teacherPn = new JPanel();
        JPanel subjectPn = new JPanel();
        teacherPn.setBackground(Color.WHITE);
        subjectPn.setBackground(Color.WHITE);
        teacherPn.add(ceateStyleJLabel(new JLabel("Giảng viên")));
        teacherPn.add(personInput);
        subjectPn.add(ceateStyleJLabel(new JLabel(" Môn học  ")));
        subjectPn.add(courseInput);

        JButton cancelButton = new JButton("Đóng");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            }
        });

        cancelButton.setFont(buttonFont);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBackground(Color.decode("#ebf2fc"));
        cancelButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        cancelButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel format = new JLabel("*Theo định dạng \"ID-Tên\"");
        formPanel.add(format);
        formPanel.add(teacherPn);
        formPanel.add(subjectPn);
        formPanel.setBorder(new EmptyBorder(25,25,25,25));

        JPanel buttonPn = new JPanel();
        buttonPn.setBackground(Color.WHITE);
        buttonPn.add(cancelButton);
        formPanel.add(buttonPn);

        editDialog.add(formPanel);

        editDialog.setVisible(true);
    }
    private void showAddDialog() {
        JDialog addDialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(PhanCongGUI.this), "Thêm Phân Công");
        addDialog.setSize(400, 240);
        addDialog.setLocationRelativeTo(null); // Đặt JDialog ở giữa màn hình

        ArrayList<courseDTO> courseLst = (ArrayList<courseDTO>) PhanCongDAO.getAllCourse();
        ArrayList<PersonDTO> personLst = (ArrayList<PersonDTO>) PhanCongDAO.getAllPerson();
        courseValue = Stream.concat(
                Stream.of(""),
                courseLst.stream()
                        .map(courseDTO -> courseDTO.getId() + "-" + courseDTO.getTittle())
        ).toArray(size -> new String[size]);

        personValue = Stream.concat(
                Stream.of(""),
                personLst.stream()
                        .map(personDTO -> personDTO.getId() + "-" + personDTO.getFirstName() + " " + personDTO.getLastName())
        ).toArray(size -> new String[size]);

        // Tạo JComboBox với danh sách dữ liệu
        personComboBox = new JComboBox<>(personValue);
        createStyleCombobox(personComboBox);
        personComboBox.setBackground(Color.WHITE);

        courseComboBox = new JComboBox<>(courseValue);
        createStyleCombobox(courseComboBox);
        courseComboBox.setBackground(Color.WHITE);

        JPanel teacherPn = new JPanel();
        JPanel subjectPn = new JPanel();
        teacherPn.setBackground(Color.WHITE);
        subjectPn.setBackground(Color.WHITE);
        teacherPn.add(ceateStyleJLabel(new JLabel("Giảng viên")));
        teacherPn.add(personComboBox);
        subjectPn.add(ceateStyleJLabel(new JLabel(" Môn học  ")));
        subjectPn.add(courseComboBox);

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String person = Objects.requireNonNull(personComboBox.getSelectedItem()).toString();
                    String course = Objects.requireNonNull(courseComboBox.getSelectedItem()).toString();
                    if (person.equals("") || course.trim().equals(""))
                        JOptionPane.showMessageDialog(PhanCongGUI.this,"Vui lòng nhập đủ thông tin");
                    else {
                        String[] personParts = person.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                        String personID = personParts[0]; // Lấy phần tử đầu tiên là ID

                        String[] courseParts = course.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                        String courseID = courseParts[0]; // Lấy phần tử đầu tiên là ID

                        PhanCongDTO pc = new PhanCongDTO();
                        pc.setPersonId(Integer.parseInt(personID));
                        pc.setCourseId(Integer.parseInt(courseID));

                        String result = phanCongBUS.addPhanCong(pc);
                        JOptionPane.showMessageDialog(PhanCongGUI.this, result);

                        ArrayList<PhanCongDTO> newPcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCong();
                        loadDataToTable(newPcLst);

                        if(result == "Thêm thành công") {
                            addDialog.dispose();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PhanCongGUI.this, "Thông tin không hợp lệ");
                }
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog.dispose();
            }
        });

        confirmButton.setFont(buttonFont);
        confirmButton.setForeground(new Color(50,205,50));
        confirmButton.setBackground(Color.decode("#ebf2fc"));
        confirmButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        confirmButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        cancelButton.setFont(buttonFont);
        cancelButton.setForeground(Color.red);
        cancelButton.setBackground(Color.decode("#ebf2fc"));
        cancelButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        cancelButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel format = new JLabel("*Theo định dạng \"ID-Tên\"");
        JPanel formatPN = new JPanel();
        formatPN.setBackground(Color.WHITE);
        formatPN.add(format);
        formPanel.add(formatPN);
        formPanel.add(teacherPn);
        formPanel.add(subjectPn);
        formPanel.setBorder(new EmptyBorder(25,25,25,25));

        JPanel buttonPn = new JPanel();
        buttonPn.setBackground(Color.WHITE);
        buttonPn.add(confirmButton);
        buttonPn.add(cancelButton);
        formPanel.add(buttonPn);

        addDialog.add(formPanel);

        addDialog.setVisible(true);
    }
    private void showEditDialog() {
        JDialog editDialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(PhanCongGUI.this), "Sửa Phân Công");
        editDialog.setSize(400, 240);
        editDialog.setLocationRelativeTo(null); // Đặt JDialog ở giữa màn hình

        ArrayList<courseDTO> courseLst = (ArrayList<courseDTO>) PhanCongDAO.getAllCourse();
        ArrayList<PersonDTO> personLst = (ArrayList<PersonDTO>) PhanCongDAO.getAllPerson();
        courseValue = Stream.concat(
                Stream.of(""),
                courseLst.stream()
                        .map(courseDTO -> courseDTO.getId() + "-" + courseDTO.getTittle())
        ).toArray(size -> new String[size]);

        personValue = Stream.concat(
                Stream.of(""),
                personLst.stream()
                        .map(personDTO -> personDTO.getId() + "-" + personDTO.getFirstName() + " " + personDTO.getLastName())
        ).toArray(size -> new String[size]);

        // Tạo JComboBox với danh sách dữ liệu
        personComboBox = new JComboBox<>(personValue);
        createStyleCombobox(personComboBox);
        personComboBox.setBackground(Color.WHITE);

        courseComboBox = new JComboBox<>(courseValue);
        createStyleCombobox(courseComboBox);
        courseComboBox.setBackground(Color.WHITE);

        JPanel teacherPn = new JPanel();
        JPanel subjectPn = new JPanel();
        teacherPn.setBackground(Color.WHITE);
        subjectPn.setBackground(Color.WHITE);
        teacherPn.add(ceateStyleJLabel(new JLabel("Giảng viên")));
        teacherPn.add(personComboBox);
        subjectPn.add(ceateStyleJLabel(new JLabel(" Môn học  ")));
        subjectPn.add(courseComboBox);

        // lấy dữ liệu từ dòng cần sửa hiển thị ra combobox
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Kiểm tra dòng được chọn
            String personID = table.getValueAt(selectedRow, 0).toString();
            String pernameName = table.getValueAt(selectedRow, 1).toString();
            String courseID = table.getValueAt(selectedRow, 2).toString();
            String courseTitle = table.getValueAt(selectedRow, 3).toString();

            // để edit
            oldPc = new PhanCongDTO(Integer.parseInt(courseID), Integer.parseInt(personID));

            personComboBox.setSelectedItem(personID + "-" + pernameName);
            courseComboBox.setSelectedItem(courseID + "-" + courseTitle);
        }

        JButton confirmButton = new JButton("Xác nhận");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String person = Objects.requireNonNull(personComboBox.getSelectedItem()).toString();
                    String course = Objects.requireNonNull(courseComboBox.getSelectedItem()).toString();

                    String[] personParts = person.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                    if(personParts.length < 2) {
                        JOptionPane.showMessageDialog(PhanCongGUI.this,"Chọn lại thông tin giảng viên");
                        return;
                    }
                    String personID = personParts[0]; // Lấy phần tử đầu tiên là ID

                    String[] courseParts = course.split("-"); // Tách chuỗi dựa trên dấu gạch ngang "-"
                    if(courseParts.length < 2) {
                        JOptionPane.showMessageDialog(PhanCongGUI.this,"Chọn lại thông tin môn học");
                        return;
                    }
                    String courseID = courseParts[0]; // Lấy phần tử đầu tiên là ID

                    PhanCongDTO newPC = new PhanCongDTO();
                    newPC.setPersonId(Integer.parseInt(personID));
                    newPC.setCourseId(Integer.parseInt(courseID));

                    if(newPC.equals(oldPc)) {
                        JOptionPane.showMessageDialog(PhanCongGUI.this,"Vui lòng thay đổi thông tin cần sửa");
                        return;
                    }
                    if(phanCongBUS.hasPhanCong(newPC)) {
                        JOptionPane.showMessageDialog(PhanCongGUI.this,"Phân công đã tồn tai");
                        return;
                    }
                    JOptionPane.showMessageDialog(PhanCongGUI.this, phanCongBUS.editPhanCong(newPC, oldPc));

                    ArrayList<PhanCongDTO> newPcLst = (ArrayList<PhanCongDTO>) phanCongBUS.getAllPhanCong();
                    loadDataToTable(newPcLst);

                    oldPc = null;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PhanCongGUI.this, "Thông tin không hợp lệ");
                }
                editDialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            }
        });

        confirmButton.setFont(buttonFont);
        confirmButton.setForeground(new Color(50,205,50));
        confirmButton.setBackground(Color.decode("#ebf2fc"));
        confirmButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        confirmButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        cancelButton.setFont(buttonFont);
        cancelButton.setForeground(Color.red);
        cancelButton.setBackground(Color.decode("#ebf2fc"));
        cancelButton.setHorizontalTextPosition(SwingConstants.LEADING); // Chuyển icon ra sau text
        cancelButton.setBorder(new EmptyBorder(8, 12, 8, 12));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JLabel format = new JLabel("*Theo định dạng \"ID-Tên\"");
        JPanel formatPN = new JPanel();
        formatPN.setBackground(Color.WHITE);
        formatPN.add(format);
        formPanel.add(formatPN);
        formPanel.add(teacherPn);
        formPanel.add(subjectPn);
        formPanel.setBorder(new EmptyBorder(25,25,25,25));

        JPanel buttonPn = new JPanel();
        buttonPn.setBackground(Color.WHITE);
        buttonPn.add(confirmButton);
        buttonPn.add(cancelButton);
        formPanel.add(buttonPn);

        editDialog.add(formPanel);

        editDialog.setVisible(true);
    }
    public void createStyleCombobox(JComboBox<String> cbb) {
        cbb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cbb.setBackground(Color.WHITE);
        cbb.setBorder(new MatteBorder(2, 2, 2, 0, Color.decode("#EFEFEF")));
        cbb.setBackground(Color.white);
        cbb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbb.setPreferredSize(new Dimension(200, 30));
        cbb.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
                basicComboPopup.setBorder(new LineBorder(Color.white));
                return basicComboPopup;
            }
        });
    }
    public void loadDataToTable(ArrayList<PhanCongDTO> pcLst) {
        if(pcLst.isEmpty()) {
            return;
        }
        // xóa bảng cũ nếu đã tạo rồi
        if(allContentPn.isAncestorOf(table)) {
            allContentPn.remove(scrollPane);
            allContentPn.revalidate();
            allContentPn.repaint();
        }

        table = new JTable();

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID GV");
        defaultTableModel.addColumn("Tên giảng viên");
        defaultTableModel.addColumn("ID MH");
        defaultTableModel.addColumn("Tên môn học");
        table.setModel(defaultTableModel);

        // Đặt font cho tiêu đề cột
        Font headerFont = new Font("Segoe UI", Font.BOLD, 15);
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(headerFont);
        tableHeader.setBackground(Color.decode("#dee9fc")); // Màu nền
        tableHeader.setPreferredSize(new Dimension(1, 30));
        tableHeader.setBorder(null);

        table.setBackground(Color.WHITE);
        table.setBorder(null);
        table.setRowHeight(35); // Thiết lập chiều cao cho các dòng trong bảng là 40
        table.setFont(new Font("Segoe UI", Font.BOLD, 13)); // Thiết lập font cho các dòng trong bảng
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        // bỏ viền các dòng trong jtable
        table.setShowGrid(false);

        // Truy cập vào model của bảng để điều chỉnh kích thước cột và font chữ
        TableColumnModel columnModel = table.getColumnModel();
        // Đặt chiều rộng cho các cột
        int[] columnWidths = {80, 200, 80, 200}; // Chiều rộng của mỗi cột
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        TableCellRenderer renderer = new CustomCourseInstructorTableRenderCell();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        Object rowsData = null;
        for (PhanCongDTO pc : pcLst) {
            PersonDTO giangvien = PhanCongDAO.getPersonByID(pc.getPersonId());
            courseDTO monhoc = PhanCongDAO.getCourseByID(pc.getCourseId());

            String tenGV = giangvien.getFirstName() + " " + giangvien.getLastName();
            rowsData = new Object[]{giangvien.getId(), tenGV, monhoc.getId(), monhoc.getTittle()};
            defaultTableModel.addRow((Object[]) rowsData);
        }

        table.setModel(defaultTableModel);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        allContentPn.add(scrollPane, BorderLayout.CENTER);
    }
    public JPanel ceateStyleJLabel(JLabel newLp) {
        JLabel lp = newLp;
        lp.setForeground(Color.BLACK);
        lp.setHorizontalAlignment(SwingConstants.CENTER);
        lp.setVerticalAlignment(SwingConstants.CENTER);
        Font font = new Font("Segoe UI", Font.BOLD, 15);
        lp.setFont(font);

        JPanel panel = new JPanel(new BorderLayout()); // Sử dụng BorderLayout
        panel.setBackground(primaryColor);
        panel.add(lp, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 8, 5, 8)); // tạo padding

        return panel;
    };
    public void setBorderForJTextField(JTextField textField) {
        // Tạo CompoundBorder để kết hợp EmptyBorder và LineBorder
        Border paddingBorder = new EmptyBorder(5, 10, 5, 10); // Padding 5px ở trên/dưới và 10px trái/phải
        Border border = BorderFactory.createLineBorder(Color.BLACK); // Viền đen
        Border compoundBorder = BorderFactory.createCompoundBorder(border, paddingBorder);

        // Đặt CompoundBorder cho JTextField
        textField.setBorder(compoundBorder);
    }
}
