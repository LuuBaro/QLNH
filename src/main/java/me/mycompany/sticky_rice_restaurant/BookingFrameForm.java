/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author WINDOWS
 */
public class BookingFrameForm extends javax.swing.JFrame {



    /**
     * Creates new form BillFrameForm
     */
    public BookingFrameForm() {
        initComponents();
        setLocationRelativeTo(null);
        loadTable();
     
    }


   private void loadTable() {
        try {
            Connection connection = me.mycompany.sticky_rice_restaurant.DatabaseUtil.getConnection();
            String query = "SELECT * FROM KHACHHANG";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Tạo một mảng chứa tên cột của bảng
            String[] columns = {
                "ID Table",
                "ID Customer",
                "Name",
                "Number Phone",
                "Date",
                "Note"
            };

            // Tạo một DefaultTableModel với các cột đã chỉ định và 0 hàng
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (resultSet.next()) {
                // Lấy dữ liệu từ các cột trong ResultSet
                String maKH = resultSet.getString("MaKH");
                String tenKH = resultSet.getString("TenKH");
                String soDT = resultSet.getString("SoDT");

                String maBan = resultSet.getString("MaBan");
                String ngayDat = resultSet.getString("NgayDat");
                String ghiChu = resultSet.getString("GhiChu");

                // Thêm dữ liệu vào một hàng mới của model
                Object[] row = {maBan, maKH, tenKH, soDT, ngayDat, ghiChu};
                model.addRow(row);
            }

            // Đặt model cho bảng tblTable
            tblBooking.setModel(model);
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            e.printStackTrace();
        }
    }
   
   private boolean isMaBanValid(Connection connection, String maBan) throws SQLException {
    String query = "SELECT COUNT(*) FROM BAN WHERE MaBan = ?";
    PreparedStatement statement = connection.prepareStatement(query);
    statement.setString(1, maBan);
    ResultSet resultSet = statement.executeQuery();
    resultSet.next();
    int count = resultSet.getInt(1);
    return count > 0;
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

        grpTratruoc = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtMaBan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Booking");
        getContentPane().setLayout(new java.awt.BorderLayout(2, 5));

        jPanel1.setLayout(new java.awt.BorderLayout());

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
        tblBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookingMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBooking);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 153, 153));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel4.add(btnAdd);

        btnSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 153, 153));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save1.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel4.add(btnSave);

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 153, 153));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel4.add(btnEdit);

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 153, 153));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete1.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete);

        btnReturn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(0, 153, 153));
        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/U Turn to Left1.png"))); // NOI18N
        btnReturn.setText("Return");
        btnReturn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReturn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        jPanel4.add(btnReturn);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nhahang (2).jpeg"))); // NOI18N
        jPanel5.add(jLabel5, new java.awt.GridBagConstraints());

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ID Customer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(txtMaKH, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Name Customer:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(txtName, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Phone number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(txtSoDT, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ID table");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel4, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel6, gridBagConstraints);

        txtDate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(txtDate, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Note:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 20);
        jPanel3.add(jLabel8, gridBagConstraints);

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        jPanel3.add(txtMaBan, gridBagConstraints);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
      

try {
       
        Connection connection = me.mycompany.sticky_rice_restaurant.DatabaseUtil.getConnection();
       
        String maKH = txtMaKH.getText().trim();
        String tenKH = txtName.getText().trim();
        String soDT = txtSoDT.getText().trim();
        String maBan = txtMaBan.getText().trim();
        String ngayDat = txtDate.getText().trim();
        String ghiChu = txtGhiChu.getText().trim();

     
        if (isMaBanValid(connection, maBan)) {
          
            String query = "INSERT INTO KHACHHANG(MaKH, TenKH, SoDT, MaBan, NgayDat, GhiChu) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, maKH);
            statement.setString(2, tenKH);
            statement.setString(3, soDT);
            statement.setString(4, maBan);
            statement.setString(5, ngayDat);
            statement.setString(6, ghiChu);

         
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
              
                loadTable();
                JOptionPane.showMessageDialog(null, "Customer added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add customer. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid MaBan. Please enter a valid MaBan.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
    
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception ex) {
        
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }

//try {
//        // Get a database connection
//        Connection connection = me.mycompany.sticky_rice_restaurant.DatabaseUtil.getConnection();
//        
//        // Get data from text fields
//        String maKH = txtMaKH.getText().trim();
//        String tenKH = txtName.getText().trim();
//        String soDT = txtSoDT.getText().trim();
//        String maBan = txtMaBan.getText().trim();
//        // Parse date using DateUtil
//        String ngayDat = DateUtil.format(DateUtil.parse(txtDate.getText().trim()));
//        String ghiChu = txtGhiChu.getText().trim();
//
//        // Prepare SQL query
//        String query = "INSERT INTO KHACHHANG(MaKH, TenKH, SoDT, MaBan, NgayDat, GhiChu) VALUES (?,?,?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(query);
//
//        // Set parameters for the query
//        statement.setString(1, maKH);
//        statement.setString(2, tenKH);
//        statement.setString(3, soDT);
//        statement.setString(4, maBan);
//        statement.setString(5, ngayDat);
//        statement.setString(6, ghiChu);
//
//        // Execute the query
//        int rowsInserted = statement.executeUpdate();
//
//        if (rowsInserted > 0) {
//            // If the query is successful, reload the table
//            loadTable();
//            JOptionPane.showMessageDialog(null, "Customer added successfully.");
//        } else {
//            JOptionPane.showMessageDialog(null, "Failed to add customer. Please try again.");
//        }
//
//    } catch (SQLException e) {
//        // Handle SQL exception
//      System.out.println(e);
//        e.printStackTrace();
//    } catch (ParseException ex) {
//        // Handle parse exception
//       System.out.println(ex);
//        ex.printStackTrace();
//    } catch (Exception ex) {
//        // Handle other exceptions
//      System.out.println(ex);
//        ex.printStackTrace();
//    }

    }//GEN-LAST:event_btnAddActionPerformed

    private void tblBookingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookingMouseClicked
        // TODO add your handling code here:
         int selectedRow = tblBooking.getSelectedRow();

    // Ensure a row is selected and the event is a double-click
    if (selectedRow != -1 && evt.getClickCount() == 2) {
        // Get data from the selected row
        String maBan = tblBooking.getValueAt(selectedRow, 0).toString();
        String maKH = tblBooking.getValueAt(selectedRow, 1).toString();
        String tenKH = tblBooking.getValueAt(selectedRow, 2).toString();
        String soDT = tblBooking.getValueAt(selectedRow, 3).toString();
        String ngayDat = tblBooking.getValueAt(selectedRow, 4).toString();
        String ghiChu = tblBooking.getValueAt(selectedRow, 5).toString();

        // Display the data in appropriate fields for editing
        txtMaBan.setText(maBan);
        txtMaKH.setText(maKH);
        txtName.setText(tenKH);
        txtSoDT.setText(soDT);
        txtDate.setText(ngayDat);
        txtGhiChu.setText(ghiChu);
    }
       
    }//GEN-LAST:event_tblBookingMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
      
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
         try {
        Connection connection = me.mycompany.sticky_rice_restaurant.DatabaseUtil.getConnection();

        // Retrieve data from text fields
        String maKH = txtMaKH.getText().trim();
        String tenKH = txtName.getText().trim();
        String soDT = txtSoDT.getText().trim();
        String maBan = txtMaBan.getText().trim();
        String ngayDat = txtDate.getText().trim();
        String ghiChu = txtGhiChu.getText().trim();

        // Validate MaBan
        if (isMaBanValid(connection, maBan)) {
            // Construct update query
            String query = "UPDATE KHACHHANG SET TenKH = ?, SoDT = ?, NgayDat = ?, GhiChu = ? WHERE MaKH = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, tenKH);
            statement.setString(2, soDT);
            statement.setString(3, ngayDat);
            statement.setString(4, ghiChu);
            statement.setString(5, maKH);

            // Execute update query
            int rowsUpdated = statement.executeUpdate();

            // Handle result
            if (rowsUpdated > 0) {
                loadTable(); // Optional: reload table data
                JOptionPane.showMessageDialog(null, "Customer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update customer. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid MaBan. Please enter a valid MaBan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
      

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       try {
        // Get the selected row index
        int selectedRow = tblBooking.getSelectedRow();
        
        // Ensure a row is selected
        if (selectedRow != -1) {
            // Get the MaKH of the selected row
            String maKH = tblBooking.getValueAt(selectedRow, 1).toString();
            
            // Confirm deletion with a dialog box
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            // If user confirms deletion
            if (dialogResult == JOptionPane.YES_OPTION) {
                // Create connection
                Connection connection = me.mycompany.sticky_rice_restaurant.DatabaseUtil.getConnection();
                
                // Construct delete query
                String query = "DELETE FROM KHACHHANG WHERE MaKH=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, maKH);
                
                // Execute the delete query
                int rowsDeleted = statement.executeUpdate();
                
                // Check if deletion was successful
                if (rowsDeleted > 0) {
                    loadTable(); // Reload table data
                    JOptionPane.showMessageDialog(null, "Customer deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete customer. Please try again.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a customer to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        MainForm thuDonForm = new MainForm();
        thuDonForm.setVisible(true);

        // Đóng JFrame "LoginUser" nếu bạn muốn
        dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

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
            java.util.logging.Logger.getLogger(BookingFrameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingFrameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingFrameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingFrameForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new BookingFrameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup grpTratruoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBooking;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSoDT;
    // End of variables declaration//GEN-END:variables
}
