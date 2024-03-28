/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class ThuDonFame extends javax.swing.JFrame {

    DefaultTableModel modeProducts;
    private List<ThuDonFame> list = new ArrayList<>();

    /**
     * Creates new form ThuDonFame
     */
    public ThuDonFame() {
        initComponents();
        setLocationRelativeTo(null);

        String[] headers = {"Mã món ă", "Tên món ăn", "Số lượng", "Giá tiền"};
        String[][] data = {
            {"MA001", "Phở bò", "10", "50000"},
            {"MA002", "Bánh mì gà", "15", "30000"},
            {"MA003", "Cơm gà", "12", "45000"},
            {"MA004", "Bún chả", "8", "60000"},
            {"MA005", "Bánh xèo", "20", "35000"},
            {"MA006", "Bún riêu", "18", "40000"},
            {"MA007", "Bún bò Huế", "25", "55000"},
            {"MA008", "Cơm tấm", "30", "40000"},
            {"MA009", "Gỏi cuốn", "22", "25000"},
            {"MA010", "Bò bía", "17", "28000"}
        };
        modeProducts = new DefaultTableModel(data, headers);
        tblTable.setModel(modeProducts);
    }

    public void xoaform() {
        txtMaMA.setText("");
        txtTenMA.setText("");
        txtSL.setText("");
        txtGT.setText("");
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaMA = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenMA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        lblHinh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", ""},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã món ăn", "Tên món ăn", "Số Lượng", "Giá tiền"
            }
        ));
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Menu Management");

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(0, 153, 153));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search1.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 255));
        jLabel7.setText("Food Information");

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("ID food:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        jPanel2.add(jLabel2, gridBagConstraints);

        txtMaMA.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(txtMaMA, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Name food:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(txtTenMA, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Quantity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(txtSL, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(txtGT, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(0, 153, 153));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add New1.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel5.add(btnAdd);

        btnEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 153, 153));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit1.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel5.add(btnEdit);

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 153, 153));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete1.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete);

        btnReturn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(0, 153, 153));
        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/U Turn to Left1.png"))); // NOI18N
        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        jPanel5.add(btnReturn);

        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ramen (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSearch)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowOpened

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        // TODO add your handling code here:
        int r = tblTable.getSelectedRow();
        if (r < 0) {
            return;
        }
        txtMaMA.setText(tblTable.getValueAt(r, 0).toString());
        txtTenMA.setText(tblTable.getValueAt(r, 1).toString());
        txtSL.setText(tblTable.getValueAt(r, 2).toString());
        txtGT.setText(tblTable.getValueAt(r, 3).toString());
    }//GEN-LAST:event_tblTableMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Vector dataRow = new Vector();
        dataRow.add(txtMaMA.getText().trim());
        dataRow.add(txtTenMA.getText().trim());
        dataRow.add(txtSL.getText().trim());
        dataRow.add(txtGT.getText().trim());
        modeProducts.addRow(dataRow);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = tblTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào trong bảng để xóa!");
            return;
        }
        modeProducts.removeRow(row);
        xoaform();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int row = tblTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào trong bảng để thay đổi!");
            return;
        }
        modeProducts.setValueAt(txtTenMA.getText().trim(), row, 0);
        modeProducts.setValueAt(txtSL.getText().trim(), row, 1);
        modeProducts.setValueAt(txtGT.getText().trim(), row, 2);

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:

        String maSPCanTim = txtTim.getText().trim();
        boolean timThay = false;
        for (int i = 0; i < tblTable.getRowCount(); i++) {
            String maSPTrongBang = (String) tblTable.getValueAt(i, 0); // Giả sử cột đầu tiên là cột chứa mã sản phẩm
            if (maSPTrongBang.equals(maSPCanTim)) {
                tblTable.setRowSelectionInterval(i, i); // Chọn dòng có mã sản phẩm cần tìm
                String MaMA = (String) tblTable.getValueAt(i, 0);
                String tenMA = (String) tblTable.getValueAt(i, 1);
                String slMA = (String) tblTable.getValueAt(i, 2);
                String giatien = (String) tblTable.getValueAt(i, 3);
                txtMaMA.setText(MaMA);
                txtTenMA.setText(tenMA);
                txtSL.setText(slMA);
                txtGT.setText(giatien);
                timThay = true;
                break;
            }
        }

        if (!timThay) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy món ăn có mã " + maSPCanTim);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(ThuDonFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuDonFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuDonFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuDonFame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThuDonFame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextField txtGT;
    private javax.swing.JTextField txtMaMA;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenMA;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
