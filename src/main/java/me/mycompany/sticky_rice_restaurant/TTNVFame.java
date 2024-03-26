/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import java.io.File;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class TTNVFame extends javax.swing.JFrame {

    DefaultTableModel modeNhanVien;

    /**
     * Creates new form TTNVFame
     */
    public TTNVFame() {
        initComponents();
        setLocationRelativeTo(null);

        String[] headers = {"Mã NV", "Họ và tên", "Ngày sinh", "Sđt", "Địa chỉ", "Giới tính", "Tài khoản", "Mật khẩu", "Chức vụ", "Hình"};
        String[][] data = {
            {"NV001", "Nguyễn Văn A", "01/01/1990", "123456789", "Hà Nội", "Nam", "nvA", "passwordA", "Nhân viên", "path/to/image1.jpg"},
            {"NV002", "Trần Thị B", "02/02/1991", "987654321", "Hải Phòng", "Nữ", "nvB", "passwordB", "Nhân Viên", "path/to/image2.jpg"},
            {"NV003", "Lê Văn C", "03/03/1992", "456123789", "Đà Nẵng", "Nam", "nvC", "passwordC", "Nhân viên", "path/to/image3.jpg"},
            {"NV004", "Phạm Thị D", "04/04/1993", "789456123", "TP.HCM", "Nữ", "nvD", "passwordD", "Nhân viên", "path/to/image4.jpg"}
        };

        modeNhanVien = new DefaultTableModel(data, headers);
        tblNhanVien.setModel(modeNhanVien);
    }

    public void xoaform() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtNgSinh.setText("");
        txtSoDT.setText("");
        txtDC.setText("");
        grpGT.clearSelection();
        txtTK.setText("");
        txtMK.setText("");
        txtCV.setText("");
    }

    private boolean isMaNVExist(String maNV) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String existingMaNV = model.getValueAt(i, 0).toString();
            if (existingMaNV.equals(maNV)) {
                return true; // Mã nhân viên đã tồn tại
            }
        }
        return false; // Mã nhân viên chưa tồn tại
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
        btnThem = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnXua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCV = new javax.swing.JTextField();
        txtMK = new javax.swing.JTextField();
        btnquaylai = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MaNV", "Họ và Tên", "Ngày Sinh", "Số ĐT", "Địa chỉ", "Giới Tính", "Tài Khoản", "Mật Khẩu", "Chức vụ"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Thông Tin Nhân Viên");

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("MaNV");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtMaNV, gridBagConstraints);

        jLabel3.setText("Họ Và Tên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtTenNV, gridBagConstraints);

        jLabel4.setText("Ngày Sinh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtNgSinh, gridBagConstraints);

        jLabel5.setText("Số ĐT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(txtSoDT, gridBagConstraints);

        jLabel6.setText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
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

        jLabel7.setText("Giới tính");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(jLabel7, gridBagConstraints);

        grpGT.add(rdoMale);
        rdoMale.setText("Nam");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        jPanel2.add(rdoMale, gridBagConstraints);

        grpGT.add(rdoFemale);
        rdoFemale.setText("Nữ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        jPanel2.add(rdoFemale, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 153, 153));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add (3).png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem);

        btnluu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnluu.setForeground(new java.awt.Color(0, 153, 153));
        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save1 (2).png"))); // NOI18N
        btnluu.setText("Lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });
        jPanel3.add(btnluu);

        btnXua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXua.setForeground(new java.awt.Color(0, 153, 153));
        btnXua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pen (1).png"))); // NOI18N
        btnXua.setText("Sữa");
        btnXua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXua);

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 153, 153));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bin (2).png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 5, 0));
        jPanel4.add(txtTim);

        btnTim.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTim.setForeground(new java.awt.Color(0, 153, 153));
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search (1).png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });
        jPanel4.add(btnTim);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("Tài Khoản");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(txtTK, gridBagConstraints);

        jLabel9.setText("Mật khẩu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jLabel9, gridBagConstraints);

        jLabel10.setText("Chức Vụ");
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(txtMK, gridBagConstraints);

        btnquaylai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnquaylai.setForeground(new java.awt.Color(0, 153, 153));
        btnquaylai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return.png"))); // NOI18N
        btnquaylai.setText("Quay lại");
        btnquaylai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquaylaiActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/z5279923910173_01e831fbf2dcc284657745aa27d93503.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnquaylai))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnquaylai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String maNV = txtMaNV.getText().trim();
        // Kiểm tra xem mã nhân viên đã tồn tại trong bảng chưa
        if (isMaNVExist(maNV)) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại trong bảng!");
            return; // Không thêm nếu mã nhân viên đã tồn tại
        }

        // Tiếp tục thêm nhân viên vào bảng nếu không trùng lặp mã nhân viên
        Vector dataRow = new Vector();
        dataRow.add(maNV);
        // Thêm các thông tin khác vào dataRow ở đây
        // ...

        dataRow.add(txtTenNV.getText().trim());
        dataRow.add(txtNgSinh.getText().trim());
        dataRow.add(txtSoDT.getText().trim());
        dataRow.add(txtDC.getText().trim());
        String gioiTinh;
        if (rdoMale.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdoFemale.isSelected()) {
            gioiTinh = "Nữ";
        } else {
            gioiTinh = ""; // Xử lý trường hợp không chọn giới tính
        }
        dataRow.add(gioiTinh);
        dataRow.add(txtTK.getText().trim());
        dataRow.add(txtMK.getText().trim());
        dataRow.add(txtCV.getText().trim());
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.addRow(dataRow);
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int r = tblNhanVien.getSelectedRow();
        if (r < 0) {
            return;
        }
        txtMaNV.setText(tblNhanVien.getValueAt(r, 0).toString());
        txtTenNV.setText(tblNhanVien.getValueAt(r, 1).toString());
        txtNgSinh.setText(tblNhanVien.getValueAt(r, 2).toString());
        txtSoDT.setText(tblNhanVien.getValueAt(r, 3).toString());
        txtDC.setText(tblNhanVien.getValueAt(r, 4).toString());

        String gioiTinh = (String) tblNhanVien.getValueAt(r, 5);
        if (gioiTinh.equals("Nam")) {
            rdoMale.setSelected(true);
        } else {
            rdoFemale.setSelected(true);
        }

        txtTK.setText(tblNhanVien.getValueAt(r, 6).toString());
        txtMK.setText(tblNhanVien.getValueAt(r, 7).toString());
        txtCV.setText(tblNhanVien.getValueAt(r, 8).toString());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuaActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào trong bảng để thay đổi!");
            return;
        }

// Cập nhật dữ liệu cho dòng được chọn trong bảng "nhân viên"
        modeNhanVien.setValueAt(txtMaNV.getText().trim(), row, 0);
        modeNhanVien.setValueAt(txtTenNV.getText().trim(), row, 1);
        modeNhanVien.setValueAt(txtNgSinh.getText().trim(), row, 2);
        modeNhanVien.setValueAt(txtSoDT.getText().trim(), row, 3);
        modeNhanVien.setValueAt(txtDC.getText().trim(), row, 4);
        String gioiTinh = rdoMale.isSelected() ? "Nam" : "Nữ";
        modeNhanVien.setValueAt(gioiTinh, row, 5);

        modeNhanVien.setValueAt(txtTK.getText().trim(), row, 6);
        modeNhanVien.setValueAt(txtMK.getText().trim(), row, 7);
        modeNhanVien.setValueAt(txtCV.getText().trim(), row, 8);

    }//GEN-LAST:event_btnXuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào trong bảng để xóa!");
            return;
        }
        modeNhanVien.removeRow(row);
        xoaform();

    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fname = fileChooser.getSelectedFile();
            try {
                PrintWriter pw = new PrintWriter(fname);

                pw.println(tblNhanVien.isShowing());
                pw.close();;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maNV = txtTim.getText().trim();
        for (int i = 0; i < modeNhanVien.getRowCount(); i++) {
            String maNVTuBang = modeNhanVien.getValueAt(i, 0).toString();
            if (maNVTuBang.equals(maNV)) {
                // Nếu tìm thấy mã nhân viên, cập nhật thông tin vào các JTextField tương ứng
                txtMaNV.setText(maNV);
                txtTenNV.setText(modeNhanVien.getValueAt(i, 1).toString());
                txtNgSinh.setText(modeNhanVien.getValueAt(i, 2).toString());
                txtSoDT.setText(modeNhanVien.getValueAt(i, 3).toString());
                txtDC.setText(modeNhanVien.getValueAt(i, 4).toString());
                String gioiTinh = modeNhanVien.getValueAt(i, 5).toString();
                if (gioiTinh.equals("Nam")) {
                    rdoMale.setSelected(true);
                } else {
                    rdoFemale.setSelected(true);
                }
                txtTK.setText(modeNhanVien.getValueAt(i, 6).toString());
                txtMK.setText(modeNhanVien.getValueAt(i, 7).toString());
                txtCV.setText(modeNhanVien.getValueAt(i, 8).toString());
                return; // Kết thúc vòng lặp khi tìm thấy mã nhân viên

            }
            // Hiển thị thông báo nếu không tìm thấy mã nhân viên
            JOptionPane.showMessageDialog(null, "Không tìm thấy mã nhân viên: " + maNV);
            return;
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnquaylaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquaylaiActionPerformed
        // TODO add your handling code here:
        MainForm thuDonForm = new MainForm();
        thuDonForm.setVisible(true);

        // Đóng JFrame "LoginUser" nếu bạn muốn
        dispose();
    }//GEN-LAST:event_btnquaylaiActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TTNVFame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXua;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnquaylai;
    private javax.swing.ButtonGroup grpGT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtCV;
    private javax.swing.JTextArea txtDC;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgSinh;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTK;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
