/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import java.awt.Image;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class TTNVFame extends javax.swing.JFrame {
    private String currentFilePath = "";

    /**
     * Creates new form TTNVFame
     */
    public TTNVFame() {
        initComponents();
        setLocationRelativeTo(null);
        loadTable();
    }

    private void loadTable() {
        try {
            Connection connection = DatabaseUtil.getConnection();

            String query = "SELECT * FROM NHANVIEN ";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            loadTable(resultSet, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean loadTable(ResultSet resultSet, boolean force) {
        try {
            String[] colums = {"ID Staff", "User Name", "Birthday", "Phone Number", "Address", "Gender", "Roles", "Image"};

            DefaultTableModel model = new DefaultTableModel(colums, 0);

            boolean hasRow = false;
            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getNString("MaNV"),
                    resultSet.getNString("TenNV"),
                    resultSet.getDate("Ngaysinh"),
                    resultSet.getNString("SDT"),
                    resultSet.getNString("DiaChi"),
                    resultSet.getBoolean("GioiTinh") ? rdoMale.getText() : rdoFemale.getText(),
                    resultSet.getNString("ChucVu"),
                    resultSet.getNString("Hinh"),
                };
                model.addRow(row);
                hasRow = true;
            }
            if (hasRow || force) {
                tblNhanVien.setModel(model);
            }
            return hasRow;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void clearForm() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtNgSinh.setText("");
        txtSoDT.setText("");
        txtCV.setText("");
        txtDC.setText("");
        grpGT.clearSelection();
        tblNhanVien.clearSelection();
        currentFilePath = "";
    }
    
    private void setImageLabel(String imagePath) {
        if (!imagePath.isEmpty()) { // Kiểm tra đường dẫn không trống
            // Tạo ImageIcon từ đường dẫn hình ảnh
            ImageIcon icon = new ImageIcon(imagePath);
            // Kiểm tra xem có thể tạo được hình ảnh từ đường dẫn không
            if (icon.getImage() != null) {
                // Cố định kích cỡ của hình ảnh thành 128x130
                Image image = icon.getImage().getScaledInstance(128, 130, Image.SCALE_SMOOTH);
                // Cập nhật hình ảnh vào ImageIcon
                icon.setImage(image);
                // Đặt ImageIcon vào label
                tblHinh.setIcon(icon);
            } else {
                System.err.println("Không thể tạo hình ảnh từ đường dẫn: " + imagePath);
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        grpGT = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgSinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDC = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCV = new javax.swing.JTextField();
        btnReturn = new javax.swing.JButton();
        tblHinh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Staff", "User name", "Birthday", "Phone number", "Address", "Gender", "Roles", "Image"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Staff Information");

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("ID Staff:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtMaNV, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("User Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtTenNV, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Birthday:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtNgSinh, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Phone number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtSoDT, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel6, gridBagConstraints);

        txtDC.setColumns(20);
        txtDC.setRows(5);
        jScrollPane2.setViewportView(txtDC);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Gender:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel7, gridBagConstraints);

        grpGT.add(rdoMale);
        rdoMale.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoMale.setForeground(new java.awt.Color(0, 153, 153));
        rdoMale.setText("Male");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        jPanel2.add(rdoMale, gridBagConstraints);

        grpGT.add(rdoFemale);
        rdoFemale.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdoFemale.setForeground(new java.awt.Color(0, 153, 153));
        rdoFemale.setText("Female");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        jPanel2.add(rdoFemale, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 153, 153));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAdd.setText("New");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdd);

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 153, 153));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save1.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave);

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 153, 153));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit);

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 153, 153));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete1.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(btnDelete);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 5, 0));
        jPanel4.add(txtTim);

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 153, 153));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search1.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel4.add(btnSearch);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Roles:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(txtCV, gridBagConstraints);

        btnReturn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(0, 153, 153));
        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/U Turn to Left1.png"))); // NOI18N
        btnReturn.setText("Back");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        tblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/z5279923910173_01e831fbf2dcc284657745aa27d93503.jpg"))); // NOI18N
        tblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(tblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReturn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnReturn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        txtMaNV.setText(String.valueOf(model.getValueAt(selectedRow, 0)));
        txtTenNV.setText(String.valueOf(model.getValueAt(selectedRow, 1)));
        txtNgSinh.setText(String.valueOf(model.getValueAt(selectedRow, 2)));
        txtSoDT.setText(String.valueOf(model.getValueAt(selectedRow, 3)));
        txtDC.setText(String.valueOf(model.getValueAt(selectedRow, 4)));
        String gender = String.valueOf(model.getValueAt(selectedRow, 5));
        if (gender.equalsIgnoreCase(rdoMale.getText())) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }
        txtCV.setText(String.valueOf(model.getValueAt(selectedRow, 6)));
        
        String ImagePath = String.valueOf(tblNhanVien.getValueAt(selectedRow, 7));

        setImageLabel(ImagePath); // gọi hàm chỉnh hình ảnh label

        currentFilePath = ImagePath; //chỉnh giá trị đường dẫn hiện tại
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:

        try {
            Connection connection = DatabaseUtil.getConnection();
            String manv = txtMaNV.getText();
            String tennv = txtTenNV.getText();
            String ngsinh = txtNgSinh.getText();
            String sdt = txtSoDT.getText();
            String dc = txtDC.getText();
            String cv = txtCV.getText();
            boolean gender = rdoMale.isSelected();

            String query = "UPDATE NHANVIEN SET TenNV = ?, Ngaysinh = ?, SDT = ?, DiaChi = ?, GioiTinh = ?, ChucVu = ?,Hinh = ? WHERE MANV = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, tennv);
            statement.setNString(2, ngsinh);
            statement.setNString(3, sdt);
            statement.setNString(4, dc);
            statement.setBoolean(5, gender);
            statement.setNString(6, cv);
            statement.setNString(7, currentFilePath); //ảnh
            statement.setNString(8, manv);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dữ liệu đã được cập nhật thành công.");
                loadTable(); // Gọi hàm loadTable() để làm mới bảng
                clearForm(); // Gọi hàm clearForm() để xóa trắng các trường nhập liệu
            } else {
                JOptionPane.showMessageDialog(this, "No records have been updated.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên nếu cần thiết
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here
        try {
            Connection connection = DatabaseUtil.getConnection();
            String manv = txtMaNV.getText();

            String query = "DELETE FROM NHANVIEN WHERE MANV = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, manv);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "The data has been deleted successfully.");
                loadTable(); // Gọi hàm loadTable() để làm mới bảng
                clearForm(); // Gọi hàm clearForm() để xóa trắng các trường nhập liệu
            } else {
                JOptionPane.showMessageDialog(this, "Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng các tài nguyên nếu cần thiết
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            Connection connection = DatabaseUtil.getConnection();
            String manv = txtMaNV.getText();
            String tennv = txtTenNV.getText();
            String ngsinh = txtNgSinh.getText();
            String sdt = txtSoDT.getText();
            String dc = txtDC.getText();
            String cv = txtCV.getText();
            boolean gender = rdoMale.isSelected();

            String query = "INSERT INTO NHANVIEN (MaNV,TenNV,Ngaysinh,SDT,DiaChi,GioiTinh,ChucVu,Hinh) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setNString(1, manv);
            statement.setNString(2, tennv);
            statement.setNString(3, ngsinh);
            statement.setNString(4, sdt);
            statement.setNString(5, dc);
            statement.setBoolean(6, gender);
            statement.setNString(7, cv);
            statement.setNString(8, currentFilePath);

            statement.executeUpdate();
            loadTable();
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:

        String input = txtTim.getText();

        try {
            Connection connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM NHANVIEN WHERE MANV LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + input + "%");

            ResultSet resultSet = statement.executeQuery();
            if (!loadTable(resultSet, true)) {
                JOptionPane.showMessageDialog(this, "Not Found!");
            }

            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
         MainForm thuDonForm = new MainForm();
        thuDonForm.setVisible(true);

        // Đóng JFrame "LoginUser" nếu bạn muốn
        dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void tblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        // Đặt đường dẫn mặc định cho JFileChooser
        File currentDir = new File("C:\\");
        fileChooser.setCurrentDirectory(currentDir);

        // Mở form chọn file và lấy kết quả
        int result = fileChooser.showOpenDialog(this);
        // Nếu kết quả trả về là đã chọn file
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy file đã chọn
            File file = fileChooser.getSelectedFile();
            // Chỉnh đường dẫn hiện tại thành đường dẫn của file đã chọn
            currentFilePath = file.getAbsolutePath();
            // Gọi hàm hiện hình ảnh dựa trên đường dẫn
            setImageLabel(currentFilePath);
        }
    }//GEN-LAST:event_tblHinhMouseClicked

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
            java.util.logging.Logger.getLogger(TTNVFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTNVFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTNVFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTNVFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTNVFame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup grpGT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JLabel tblHinh;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCV;
    private javax.swing.JTextArea txtDC;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
