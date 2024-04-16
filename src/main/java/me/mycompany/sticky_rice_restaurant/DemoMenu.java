/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import Content.MainForm;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class DemoMenu extends javax.swing.JFrame {

    CardLayout cardlayout;
    private String currentTableId;
    private Runnable runnable;

    /**
     * Creates new form DemoMenu
     */
    public DemoMenu() {
        initComponents();
        setLocationRelativeTo(null);

        Component[] components = this.getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                ((JButton) comp).setUI(new BasicButtonUI());
                ((JButton) comp).setFocusPainted(false);
            }
        }
        cardlayout = (CardLayout) (pnlCards.getLayout());

        loadTable();
        loadTableBooking();
        loadTableBancbo();
        loadTableIDs();
        loadFoodNames();
//        loadHoaDon();
    }

    public void totalPrice(String tableId) {
        try {
            Connection connection = DatabaseUtil.getConnection();

            String query = "SELECT SUM(THUCDON.GiaTien * BAN_THUCDON.SoLuong) AS Total "
                    + "FROM THUCDON JOIN BAN_THUCDON ON THUCDON.MaMon = BAN_THUCDON.MaMon "
                    + "JOIN BAN ON BAN_THUCDON.MaBan = BAN.MaBan "
                    + "JOIN HOADON ON BAN.MaBan = HOADON.MaBan "
                    + "WHERE BAN.MaBan = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tableId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                float total = rs.getFloat("Total");
                String discountText = txtGiamGia.getText();
                if (!discountText.isEmpty()) {
                    float discountPercent = Float.parseFloat(discountText);
                    total = calculateDiscountedPrice(total, discountPercent);
                }
                txtTongTien.setText(String.valueOf(total));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public float calculateDiscountedPrice(float total, float discountPercent) {
        // Kiểm tra xem phần trăm giảm có hợp lệ không

        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("The discount percentage must be between 0 and 100.");
        }

        // Tính giá tiền sau khi đã giảm
        float discountedPrice = total * (1 - discountPercent / 100);

        return discountedPrice;
    }

    public void loadTable() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM THUCDON";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Tạo một mảng chứa tên cột của bảng
            String[] columns = {
                "ID Food",
                "Food Name",
                "Price",
                "Path"
            };

            // Tạo một DefaultTableModel với các cột đã chỉ định và 0 hàng
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (resultSet.next()) {
                // Lấy dữ liệu từ các cột trong ResultSet
                String maMon = resultSet.getString("MaMon");
                String tenMon = resultSet.getString("TenMon");
                float giaTien = resultSet.getFloat("GiaTien");
                String hinh = resultSet.getString("Hinh");
                // Thêm dữ liệu vào một hàng mới của model
                Object[] row = {maMon, tenMon, giaTien, hinh};
                model.addRow(row);
            }

            // Đặt model cho bảng tblTable
            tblTable.setModel(model);
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }

    }

    public void loadTableBooking() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT CHI_TIET_DAT_BAN.MaBan, KHACHHANG.MaKH, KHACHHANG.TenKH, KHACHHANG.SoDT, CHI_TIET_DAT_BAN.NgayDat "
                    + "FROM CHI_TIET_DAT_BAN LEFT JOIN KHACHHANG ON CHI_TIET_DAT_BAN.MaKH = KHACHHANG.MaKH";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Tạo một mảng chứa tên cột của bảng
            String[] columns = {
                "ID Table",
                "ID Customer",
                "Name",
                "Number Phone",
                "Date"
            };

            // Tạo một DefaultTableModel với các cột đã chỉ định và 0 hàng
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (resultSet.next()) {
                String idTable = resultSet.getString("MaBan");
                String idCustomer = resultSet.getString("MaKH");
                String name = resultSet.getString("TenKH");
                String phone = resultSet.getString("SoDT");
                String date = resultSet.getString("NgayDat");

                // Thêm dữ liệu vào một hàng mới của model
                Object[] row = {idTable, idCustomer, name, phone, date};
                model.addRow(row);
            }

            // Đặt model cho bảng tblBooking
            tblBooking.setModel(model);
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }

    private void loadTableBan() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM BAN";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Tạo một mảng chứa tên cột của bảng
            String[] columns = {
                "ID Table",
                "Status"
            };

            // Tạo một DefaultTableModel với các cột đã chỉ định và 0 hàng
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (resultSet.next()) {
                String idBan = resultSet.getString("MaBan");
                String trangThai = resultSet.getString("TrangThai");

                // Thêm dữ liệu vào một hàng mới của model
                Object[] row = {idBan, trangThai};
                model.addRow(row);
            }

            // Đặt model cho bảng tblBan
            tblBan.setModel(model);
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }

    private void loadTableIDs() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT MaBan FROM BAN";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            cboIdTable.removeAllItems(); // Xóa tất cả các mục hiện có trong ComboBox
            while (resultSet.next()) {
                String tableID = resultSet.getString("MaBan");
                cboIdTable.addItem(tableID); // Thêm ID bàn vào ComboBox
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading table ID.");
        }
    }

    private void loadFoodNames() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT TenMon FROM THUCDON";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            cboTenMon.removeAllItems(); // Xóa tất cả các mục hiện có trong ComboBox
            while (resultSet.next()) {
                String foodName = resultSet.getString("TenMon");
                cboTenMon.addItem(foodName); // Thêm tên món ăn vào ComboBox
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading food names.");
        }
    }

    //bắt lỗi cho Bàn
    private boolean isBanExists(String idBan) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT MaBan FROM BAN WHERE MaBan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, idBan);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Trả về true nếu ID đã tồn tại, ngược lại trả về false
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    // Phương thức load danh sách ID bàn vào combobox
    public void loadTableBancbo() {

        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT MaBan FROM BAN";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Lưu giữ ID bàn hiện tại để có thể chọn lại sau khi load dữ liệu
            String selectedItem = (cboIDTable.getSelectedItem() != null) ? cboIDTable.getSelectedItem().toString() : null;

            // Xóa mục khỏi cboIDTable
            cboIDTable.removeAllItems();

            // Biến cờ để kiểm tra xem ID bàn hiện tại có tồn tại sau khi load không
            boolean itemExists = false;

            // Đọc dữ liệu từ ResultSet và thêm vào combobox
            while (resultSet.next()) {
                String maBan = resultSet.getString("MaBan");
                cboIDTable.addItem(maBan);
                // Kiểm tra nếu mã bàn trùng với mục được chọn trước đó
                if (maBan.equals(selectedItem)) {
                    itemExists = true;
                }
            }

            // Chọn lại mục đã chọn trước đó nếu nó vẫn tồn tại sau khi tái tạo combobox
            if (itemExists) {
                cboIDTable.setSelectedItem(selectedItem);
            } else {
                if (cboIDTable.getItemCount() > 0) {
                    cboIDTable.setSelectedIndex(0); // hoặc bạn có thể thiết lập một trạng thái mặc định nào đó
                }
            }

        } catch (SQLException e) {
            // Xử lý lỗi
            e.printStackTrace();
        }
    }

    public void loadHoaDon() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
            model.setRowCount(0); // Xóa tất cả các dòng hiện có trong bảng

            String query = "SELECT * FROM HOADON"; // Thay đổi câu truy vấn tùy theo cấu trúc bảng HOADON của bạn
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String maHD = resultSet.getString("MaHD");
                String maBan = resultSet.getString("MaBan");
                double tongTien = resultSet.getDouble("TongTien");

                // Thêm dòng mới vào bảng
                model.addRow(new Object[]{maHD, maBan, tongTien});
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pnlCards = new javax.swing.JPanel();
        pnlCard1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCard2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnHome1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnAddfood = new javax.swing.JButton();
        btnDeletefood = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboIdTable = new javax.swing.JComboBox<>();
        cboTenMon = new javax.swing.JComboBox<>();
        txtMaMon = new javax.swing.JTextField();
        txtGiaMon = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHoaDon = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        pnlCard3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtMaMA = new javax.swing.JTextField();
        txtTenMA = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtGT = new javax.swing.JTextField();
        lblHinh = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        pnlCard4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnDeleteTable = new javax.swing.JButton();
        btnAddTable = new javax.swing.JButton();
        btnEditTable = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtMaban = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        txtNameKH = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnSave1 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        cboIDTable = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        Reset = new javax.swing.JButton();
        pnlCard5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangHoaDon = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIDBang = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnPay = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnPay1 = new javax.swing.JButton();
        btnPrint1 = new javax.swing.JButton();
        pnlCard6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setPreferredSize(new java.awt.Dimension(671, 200));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(139, 200));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnHome.setBackground(new java.awt.Color(51, 51, 51));
        btnHome.setForeground(new java.awt.Color(51, 51, 51));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Home Page.png"))); // NOI18N
        btnHome.setBorder(null);
        btnHome.setBorderPainted(false);
        btnHome.setFocusPainted(false);
        btnHome.setFocusable(false);
        btnHome.setPreferredSize(new java.awt.Dimension(100, 80));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bill_2.png"))); // NOI18N
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton9.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Restaurant Menu_2.png"))); // NOI18N
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton10.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(51, 51, 51));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Get Revenue.png"))); // NOI18N
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton12.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Table.png"))); // NOI18N
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton11.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User_1.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Emergency Exit.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 80));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Information.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton3.setPreferredSize(new java.awt.Dimension(100, 60));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        pnlCards.setLayout(new java.awt.CardLayout());

        pnlCard1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/menuLOGO.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlCard1Layout = new javax.swing.GroupLayout(pnlCard1);
        pnlCard1.setLayout(pnlCard1Layout);
        pnlCard1Layout.setHorizontalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCard1Layout.setVerticalGroup(
            pnlCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCards.add(pnlCard1, "pnlCard1");

        pnlCard2.setBackground(new java.awt.Color(255, 255, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnHome1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(0, 51, 153));
        btnHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Home2.png"))); // NOI18N
        btnHome1.setText("Home");
        btnHome1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome1ActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 51, 153));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh1.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnAddfood.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAddfood.setForeground(new java.awt.Color(0, 51, 153));
        btnAddfood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAddfood.setText("Add food");
        btnAddfood.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddfood.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddfood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddfoodActionPerformed(evt);
            }
        });

        btnDeletefood.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDeletefood.setForeground(new java.awt.Color(0, 51, 153));
        btnDeletefood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Close1.png"))); // NOI18N
        btnDeletefood.setText("Delete food");
        btnDeletefood.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletefood.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeletefood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletefoodActionPerformed(evt);
            }
        });

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Table", "Food Name", "ID Food", "Price", "Quantity", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblThongTin);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setText("Quantity:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Price:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setText("ID Table:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Food Name:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ID Food:");

        cboIdTable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"B001","B002","B003","B004","B005","B006","B007","B008","B009","B010" }));
        cboIdTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIdTableActionPerformed(evt);
            }
        });

        cboTenMon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        cboTenMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenMonActionPerformed(evt);
            }
        });

        txtMaMon.setEditable(false);

        txtGiaMon.setEditable(false);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.W_RESIZE_CURSOR));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 153));
        jLabel28.setText("Bill:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoluong)
                                    .addComponent(txtMaMon, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboIdTable, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTenMon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiaMon)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddfood, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeletefood))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHome1)
                            .addComponent(btnReset))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddfood)
                            .addComponent(btnDeletefood))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboIdTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(29, 29, 29)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlCard2Layout = new javax.swing.GroupLayout(pnlCard2);
        pnlCard2.setLayout(pnlCard2Layout);
        pnlCard2Layout.setHorizontalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCard2Layout.setVerticalGroup(
            pnlCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlCards.add(pnlCard2, "pnlCard2");

        pnlCard3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 255));
        jLabel16.setText("Menu Management");

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 153, 153));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search1.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 153));
        jLabel17.setText("ID food:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 153));
        jLabel18.setText("Name food:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 153));
        jLabel19.setText("Price:");

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ramen (1).png"))); // NOI18N
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 153, 153));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete1.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 153, 153));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 153, 153));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", ""},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Food", "Food Name", "Price"
            }
        ));
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTable);

        javax.swing.GroupLayout pnlCard3Layout = new javax.swing.GroupLayout(pnlCard3);
        pnlCard3.setLayout(pnlCard3Layout);
        pnlCard3Layout.setHorizontalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaMA, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMA, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        pnlCard3Layout.setVerticalGroup(
            pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(54, 54, 54)
                .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard3Layout.createSequentialGroup()
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(pnlCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCard3, "pnlCard3");

        pnlCard4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 255));
        jLabel25.setText("STICKY RICE RESTAURANT ");

        btnDeleteTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDeleteTable.setForeground(new java.awt.Color(0, 153, 153));
        btnDeleteTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnDeleteTable.setText("Delete");
        btnDeleteTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeleteTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTableActionPerformed(evt);
            }
        });

        btnAddTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAddTable.setForeground(new java.awt.Color(0, 153, 153));
        btnAddTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAddTable.setText("Add");
        btnAddTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTableActionPerformed(evt);
            }
        });

        btnEditTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEditTable.setForeground(new java.awt.Color(0, 153, 153));
        btnEditTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnEditTable.setText("Edit");
        btnEditTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTableActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 153));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("ID Table:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 153));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Status:");

        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Table", "Status"
            }
        ));
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txtMaban, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnEditTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDeleteTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtMaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddTable)
                    .addComponent(btnDeleteTable)
                    .addComponent(btnEditTable)))
        );

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 153, 153));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnSave.setText("Edit");
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblBooking);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 153));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Date");

        txtDate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 153));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Phone number:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 153));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Name Customer:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 153));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("ID Customer:");

        btnSave1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave1.setForeground(new java.awt.Color(0, 153, 153));
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnSave1.setText("Delete");
        btnSave1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        btnSave2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave2.setForeground(new java.awt.Color(0, 153, 153));
        btnSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save1.png"))); // NOI18N
        btnSave2.setText("Save");
        btnSave2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        cboIDTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboIDTableMouseClicked(evt);
            }
        });
        cboIDTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDTableActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 153));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Table:");

        Reset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Reset.setForeground(new java.awt.Color(0, 153, 153));
        Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh1.png"))); // NOI18N
        Reset.setText("Reset");
        Reset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Reset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNameKH, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMaKH))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboIDTable, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Reset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboIDTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtNameKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave1)
                            .addComponent(btnSave))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave2)
                            .addComponent(Reset))))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 178, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCard4Layout = new javax.swing.GroupLayout(pnlCard4);
        pnlCard4.setLayout(pnlCard4Layout);
        pnlCard4Layout.setHorizontalGroup(
            pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCard4Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCard4Layout.setVerticalGroup(
            pnlCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlCards.add(pnlCard4, "pnlCard4");

        pnlCard5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 255));
        jLabel6.setText("STICKY RICE RESTAURANT ");

        lblClock.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 51, 51));
        lblClock.setText("00:00:00");
        lblClock.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblClockAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 402));

        tblBangHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Food", "Food Name", "Quantity", "Price"
            }
        ));
        tblBangHoaDon.setPreferredSize(new java.awt.Dimension(250, 80));
        jScrollPane2.setViewportView(tblBangHoaDon);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Discount:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("ID Table:");

        txtIDBang.setEditable(false);
        txtIDBang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtIDBang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtGiamGia.setForeground(new java.awt.Color(255, 51, 51));
        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Total:");

        txtTongTien.setEditable(false);

        btnPay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPay.setForeground(new java.awt.Color(0, 153, 153));
        btnPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pay2.png"))); // NOI18N
        btnPay.setText("Delete");
        btnPay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPay.setPreferredSize(new java.awt.Dimension(100, 52));
        btnPay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 153, 153));
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Print1.png"))); // NOI18N
        btnPrint.setText("Save");
        btnPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint.setPreferredSize(new java.awt.Dimension(100, 52));
        btnPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 153, 153));
        jLabel12.setText("Customere ID:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 153));
        jLabel14.setText("Name:");

        btnPay1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPay1.setForeground(new java.awt.Color(0, 153, 153));
        btnPay1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pay2.png"))); // NOI18N
        btnPay1.setText("Pay");
        btnPay1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPay1.setPreferredSize(new java.awt.Dimension(100, 52));
        btnPay1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPay1ActionPerformed(evt);
            }
        });

        btnPrint1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrint1.setForeground(new java.awt.Color(0, 153, 153));
        btnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Print1.png"))); // NOI18N
        btnPrint1.setText("Print");
        btnPrint1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrint1.setPreferredSize(new java.awt.Dimension(100, 52));
        btnPrint1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlCard5Layout = new javax.swing.GroupLayout(pnlCard5);
        pnlCard5.setLayout(pnlCard5Layout);
        pnlCard5Layout.setHorizontalGroup(
            pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(52, 52, 52)
                        .addComponent(lblClock))
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel8)
                                .addGap(20, 20, 20)
                                .addComponent(txtIDBang, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(20, 20, 20)
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel11)
                                .addGap(20, 20, 20)
                                .addComponent(txtTongTien)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnPay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCard5Layout.setVerticalGroup(
            pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCard5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblClock))
                .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCard5Layout.createSequentialGroup()
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCard5Layout.createSequentialGroup()
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8))
                            .addComponent(txtIDBang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel7))
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pnlCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCard5Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11))
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        pnlCards.add(pnlCard5, "pnlCard5");

        pnlCard6.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout pnlCard6Layout = new javax.swing.GroupLayout(pnlCard6);
        pnlCard6.setLayout(pnlCard6Layout);
        pnlCard6Layout.setHorizontalGroup(
            pnlCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        pnlCard6Layout.setVerticalGroup(
            pnlCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        pnlCards.add(pnlCard6, "pnlCard6");

        jSplitPane1.setRightComponent(pnlCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard1");
    }//GEN-LAST:event_btnHomeActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardlayout.show(pnlCards, "pnlCard6");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome1ActionPerformed
        // TODO add your handling code here:
        MainForm billForm = new MainForm();
        billForm.setVisible(true);

        // Đóng JFrame "LoginUser" nếu bạn muốn
        dispose();
    }//GEN-LAST:event_btnHome1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
        model.setRowCount(0);

        // Đặt lại giá trị của các textfield và combobox về giá trị mặc định
        cboIdTable.setSelectedItem(0);
        cboTenMon.setSelectedItem(0);
        txtMaMon.setText("");
        txtGiaMon.setText("");
        txtSoluong.setText("");
        // Đặt lại combobox về giá trị đầu tiên

        // Hiển thị thông báo reset thành công
        JOptionPane.showMessageDialog(null, "Data reset successfully.");
    }//GEN-LAST:event_btnResetActionPerformed


    private void btnAddfoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddfoodActionPerformed
        // TODO add your handling code here:
        // Lấy thông tin từ các trường nhập vào
        String maHD = txtHoaDon.getText();
        String maBan = cboIDTable.getSelectedItem().toString();
        String selectedFoodId = cboTenMon.getSelectedItem().toString();
        String IdFood = txtMaMA.getText().trim();
        String giatien = txtGiaMon.getText().trim();
        String soluongText = txtSoluong.getText().trim();
        float quantity = 0; // Số lượng mặc định là 0
        double foodPrice = 0;
        double totalPrice = 0;

        if (!soluongText.isEmpty()) {
            try {
                quantity = Float.parseFloat(soluongText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Kết thúc hàm nếu có lỗi
            }
        } else {
            JOptionPane.showMessageDialog(null, "Quantity field cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc hàm nếu trường số lượng trống
        }

        // Đảm bảo giá tiền cũng là một số hợp lệ
        try {
            foodPrice = Double.parseDouble(txtGiaMon.getText().trim()); // Giá tiền từ TextField
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number for price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Kết thúc hàm nếu nhập sai định dạng giá
        }

        totalPrice = quantity * foodPrice; // Tính tổng tiền

        // Hiển thị thông tin lên bảng tblThongTin
        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
        model.addRow(new Object[]{maBan, selectedFoodId, IdFood, giatien, soluongText, totalPrice});

        // Thêm thông tin vào bảng HOADON
        try {
            Connection connection = DatabaseUtil.getConnection();

            String insertHDQuery = "INSERT INTO HOADON (MaHD, MaBan, TongTien) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertHDQuery);

            statement.setString(1, maHD);
            statement.setString(2, maBan);
            statement.setDouble(3, totalPrice);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Invoice added successfully.");
                loadHoaDon(); // Tải lại bảng sau khi thêm dữ liệu
            } else {
                JOptionPane.showMessageDialog(null, "Could not add the invoice, please try again.");
            }

        } catch (SQLException e) {
            System.out.println(e);       
            e.printStackTrace(); // Giúp debug nếu có lỗi dữ liệu
        }
    }//GEN-LAST:event_btnAddfoodActionPerformed

    private void btnDeletefoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletefoodActionPerformed
        // TODO add your handling code here:
        // Lấy chỉ mục của hàng được chọn trong bảng
        int selectedRow = tblThongTin.getSelectedRow();

        if (selectedRow == -1) {
            // Nếu không có hàng nào được chọn, hiển thị thông báo và thoát
            JOptionPane.showMessageDialog(null, "Please select a dish to delete!");
            return;
        }

        // Lấy MaBan và MaMon từ hàng được chọn
        String selectedTableId = tblThongTin.getValueAt(selectedRow, 0).toString();
        String selectedFoodId = tblThongTin.getValueAt(selectedRow, 2).toString();

        try {
            // Xóa món ăn khỏi bảng và cơ sở dữ liệu
            Connection connection = DatabaseUtil.getConnection();
            String deleteQuery = "DELETE FROM BAN_THUCDON WHERE MaBan = ? AND MaMon = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, selectedTableId);
            deleteStatement.setString(2, selectedFoodId);
            deleteStatement.executeUpdate();
            deleteStatement.close();
            connection.close();

            // Xóa hàng đã chọn khỏi bảng
            DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
            model.removeRow(selectedRow);

            // Hiển thị thông báo khi xóa thành công
            JOptionPane.showMessageDialog(null, "Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error removing dish from table!");
        }
    }//GEN-LAST:event_btnDeletefoodActionPerformed

    private void cboIdTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIdTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIdTableActionPerformed

    private void cboTenMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenMonActionPerformed
        // Lấy món ăn được chọn từ comboBox

        cboTenMon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displaySelectedFoodInfo(); // Phương thức này sẽ chứa code điền thông tin vào txtPrice và txtIDFood
            }
        });
    }//GEN-LAST:event_cboTenMonActionPerformed

    private void displaySelectedFoodInfo() {
        // Cơ sở dữ liệu và hiển thị thông tin món ăn như đã thảo luận ở trên
        try {
            // Lấy tên món ăn được chọn từ comboBox
            String selectedFoodName = (String) cboTenMon.getSelectedItem();
            if (selectedFoodName == null) {
                return; // Không làm gì cả nếu không có món ăn được chọn
            }

            // Kết nối cơ sở dữ liệu và truy vấn để lấy thông tin id và giá tiền của món ăn được chọn
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT MaMon, GiaTien FROM THUCDON WHERE TenMon = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedFoodName);
            ResultSet resultSet = statement.executeQuery();

            // Hiển thị thông tin vào các textField tương ứng
            if (resultSet.next()) {
                String selectedFoodId = resultSet.getString("MaMon");
                double selectedFoodPrice = resultSet.getDouble("GiaTien");

                txtMaMon.setText(selectedFoodId);
                txtGiaMon.setText(String.format("%.2f", selectedFoodPrice));
            } else {
                // Nếu không tìm thấy món ăn, xóa nội dung trong textField
                txtMaMon.setText("");
                txtGiaMon.setText("");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving dish information from database.");
        }
    }
    private void lblClockAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblClockAncestorAdded
        // TODO add your handling code here:
        runnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                while (true) {
                    String text = dateFormat.format(new Date());
                    lblClock.setText(text);
                }
            }
        };
        new Thread(runnable).start();
    }//GEN-LAST:event_lblClockAncestorAdded

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
        totalPrice(currentTableId);
    }//GEN-LAST:event_txtGiamGiaActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Payment successful!");

        // Xóa dữ liệu trong bảng
        DefaultTableModel model = (DefaultTableModel) tblBangHoaDon.getModel();
        model.setRowCount(0);
        // Xóa dữ liệu trong txtTongTien
        txtTongTien.setText("");
        // Xóa dữ liệu trong txtGiamGia
        txtGiamGia.setText("");
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnPay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPay1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String searchTerm = txtTim.getText();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM THUCDON WHERE TenMon LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchTerm + "%"); // Tìm kiếm món ăn có tên chứa chuỗi nhập vào

            ResultSet resultSet = statement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng trong bảng

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getString("MaMon"),
                    resultSet.getString("TenMon"),
                    resultSet.getFloat("GiaTien")
                };
                model.addRow(row);
            }

            // Nếu không có kết quả nào được tìm thấy, hiển thị thông báo
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No matching dishes found.");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = tblTable.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy dữ liệu từ hàng được chọn
                String id = tblTable.getValueAt(selectedRow, 0).toString();

                // Xóa dữ liệu từ cơ sở dữ liệu
                Connection connection = DatabaseUtil.getConnection();
                String query = "DELETE FROM THUCDON WHERE MaMon = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, id);
                statement.executeUpdate();

                // Thông báo thành công và làm mới bảng
                JOptionPane.showMessageDialog(this, "Deleted successfully.");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        String id = txtMaMA.getText();
        String foodName = txtTenMA.getText();
        String price = txtGT.getText();

        // Hiển thị hộp thoại nhập liệu để người dùng chỉnh sửa
        String newId = JOptionPane.showInputDialog(this, "Enter new ID:", id);
        String newFoodName = JOptionPane.showInputDialog(this, "Enter new item name:", foodName);
        String newPrice = JOptionPane.showInputDialog(this, "Enter new price:", price);

        try {
            Connection connection = DatabaseUtil.getConnection();

            // Thực hiện cập nhật dữ liệu vào cơ sở dữ liệu
            String query = "UPDATE THUCDON SET MaMon = ?, TenMon = ?, GiaTien = ? WHERE MaMon = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newId);
            statement.setString(2, newFoodName);
            statement.setString(3, newPrice);
            statement.setString(4, id);

            statement.executeUpdate();

            // Thông báo thành công và làm mới bảng
            JOptionPane.showMessageDialog(this, "The data has been updated successfully.");
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        try {
            Connection connection = DatabaseUtil.getConnection();
            String mamon = txtMaMA.getText();
            String tenmon = txtTenMA.getText();
            String giatien = txtGT.getText();
            String hinh = lblHinh.getText();
            String query = "INSERT INTO THUCDON(MaMon, TenMon, GiaTien, Hinh) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setNString(1, mamon);
            statement.setNString(2, tenmon);
            statement.setNString(3, giatien);
            statement.setNString(4, hinh);
            statement.executeUpdate();
            loadTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblTableMouseClicked

    private void btnDeleteTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTableActionPerformed
        try {
            Connection connection = DatabaseUtil.getConnection();

            // Kiểm tra xem ID đã tồn tại trong cơ sở dữ liệu hay không
            if (isBanExists(txtMaban.getText())) {
                String query = "DELETE FROM BAN WHERE MaBan = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, txtMaban.getText());
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Deleted successfully!");
                    // Sau khi xóa, cần load lại bảng để cập nhật dữ liệu mới
                    loadTableBan();
                } else {
                    JOptionPane.showMessageDialog(this, "Delete failed!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "ID not available!");
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteTableActionPerformed

    private void btnEditTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTableActionPerformed
        // TODO add your handling code here:
        try {
            // Kiểm tra xem có hàng nào được chọn không
            int selectedRow = tblBan.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
                return;
            }

            // Lấy dữ liệu từ hàng được chọn
            String idBan = txtMaban.getText();
            String trangThai = txtTrangThai.getText();

            // Hiển thị dữ liệu lên các trường nhập liệu hoặc các thành phần khác để người dùng có thể chỉnh sửa
            // Ở đây, bạn có thể cho phép người dùng chỉnh sửa các trường nhập liệu txtMaban và txtTrangThai
            // Sau khi người dùng chỉnh sửa, cập nhật dữ liệu trong cơ sở dữ liệu và bảng
            Connection connection = DatabaseUtil.getConnection();
            String query = "UPDATE BAN SET TrangThai = ? WHERE MaBan = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, trangThai);
            statement.setString(2, idBan);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Table updated successfully.");
                // Sau khi cập nhật, cần load lại bảng để cập nhật dữ liệu mới
                loadTableBan();
            } else {
                JOptionPane.showMessageDialog(this, "Table update failed.");
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditTableActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSaveActionPerformed
// nút thêm của bảng đặt bàn
    private void btnAddTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTableActionPerformed

        try {
            Connection connection = DatabaseUtil.getConnection();

            // Kiểm tra xem ID đã tồn tại trong cơ sở dữ liệu hay không
            if (!isBanExists(txtMaban.getText())) {
                String query = "INSERT INTO BAN (MaBan, TrangThai) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, txtMaban.getText());
                statement.setString(2, txtTrangThai.getText());
                statement.executeUpdate();

                // Cập nhật bảng tblBan
                loadTableBan(); // Hàm loadTableBan cập nhật bảng tblBan từ cơ sở dữ liệu
            } else {
                JOptionPane.showMessageDialog(this, "ID đã tồn tại!");
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddTableActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
        String maKH = txtMaKH.getText();
        String tenKH = txtNameKH.getText();
        String soDT = txtSoDT.getText();
        String ngayDat = txtDate.getText();
        String maBan = cboIDTable.getSelectedItem().toString();

        // Kiểm tra xem đã chọn mã bàn chưa
        if (maBan == null || maBan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã bàn trước khi thêm thông tin khách hàng!");
            return; // Dừng thực thi nếu không có mã bàn được chọn
        }

        try {
            // Thêm thông tin khách hàng vào bảng CHI_TIET_DAT_BAN
            Connection connection = DatabaseUtil.getConnection();
            String query = "INSERT INTO CHI_TIET_DAT_BAN (MaKH, MaBan, NgayDat) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maKH);
            statement.setString(2, maBan);
            statement.setString(3, ngayDat);

            int rowsAffected = statement.executeUpdate();
            loadTableBan();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Thông tin khách hàng đã được thêm vào bàn thành công!");
                // Sau khi thêm thông tin khách hàng thành công, có thể cập nhật giao diện hoặc thực hiện các hành động khác tùy vào yêu cầu của ứng dụng
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thông tin khách hàng vào bàn không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm thông tin khách hàng vào bàn!");
        }
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_ResetActionPerformed

    private void tblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblBan.getSelectedRow();

        // Lấy dữ liệu từ hàng được chọn
        String idBan = tblBan.getValueAt(selectedRow, 0).toString(); // Giả sử cột đầu tiên là cột chứa ID bàn
        String trangThai = tblBan.getValueAt(selectedRow, 1).toString(); // Giả sử cột thứ hai là cột chứa trạng thái bàn

        // Hiển thị dữ liệu lên các trường nhập liệu hoặc các thành phần khác trên giao diện
        txtMaban.setText(idBan);
        txtTrangThai.setText(trangThai);
    }//GEN-LAST:event_tblBanMouseClicked

    private void cboIDTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDTableActionPerformed
        // TODO add your handling code here:
        // Lấy ID bàn đã chọn từ combobox

        // Thực hiện các hành động tương ứng với việc chọn ID bàn, ví dụ: thêm thông tin khách hàng vào bàn đó
        // Load lại dữ liệu của bàn
        loadTableBancbo();
    }//GEN-LAST:event_cboIDTableActionPerformed

    private void cboIDTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboIDTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboIDTableMouseClicked
//mouseClicked hình

    private void setImageLabel(String imagePath) {
        if (imagePath.isBlank()) {
            lblHinh.setIcon(new ImageIcon(getClass().getResource("/image/user_128.png")));
        } else {
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage().getScaledInstance(161, 227, Image.SCALE_SMOOTH);

            icon.setImage(image);
            lblHinh.setIcon(icon);
        }
    }

    private String currentFilePath = "";
    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            currentFilePath = file.getAbsolutePath();

            setImageLabel(currentFilePath);
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DemoMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemoMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemoMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemoMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DemoMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reset;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddTable;
    private javax.swing.JButton btnAddfood;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteTable;
    private javax.swing.JButton btnDeletefood;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditTable;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHome1;
    private javax.swing.JButton btnPay;
    private javax.swing.JButton btnPay1;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrint1;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cboIDTable;
    private javax.swing.JComboBox<String> cboIdTable;
    private javax.swing.JComboBox<String> cboTenMon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlCard3;
    private javax.swing.JPanel pnlCard4;
    private javax.swing.JPanel pnlCard5;
    private javax.swing.JPanel pnlCard6;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JTable tblBan;
    private javax.swing.JTable tblBangHoaDon;
    private javax.swing.JTable tblBooking;
    private javax.swing.JTable tblTable;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtGT;
    private javax.swing.JTextField txtGiaMon;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtHoaDon;
    private javax.swing.JTextField txtIDBang;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaMA;
    private javax.swing.JTextField txtMaMon;
    private javax.swing.JTextField txtMaban;
    private javax.swing.JTextField txtNameKH;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenMA;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
