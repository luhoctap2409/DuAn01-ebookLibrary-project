/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.helper.MovingForm;
import com.ebooks.model.Sach;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

/**
 *
 * @author Thinh
 */
public class BorrowedBooksDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form BorrowBooksDiaLog
     */
     boolean congTac;
    public BorrowedBooksDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0,0,0,0));
        MovingForm.initMoving(this, pnlBorrwedBooks);
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBorrwedBooks = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelRound2 = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMuonSach = new com.ebooks.Compoment.Table();
        btnLast = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        panelRound3 = new com.ebooks.Compoment.PanelRound();
        lblSachImg = new com.ebooks.Compoment.ImageBoder();
        txtMaSach = new javax.swing.JTextField();
        lblMaSach = new javax.swing.JLabel();
        lblTenSach1 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        txtNgayMuon = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        txtTaiKhoanMuon = new javax.swing.JTextField();
        lblMaSach1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnDocSach = new com.ebooks.Compoment.MyButton();
        btnLuuThongTin1 = new com.ebooks.Compoment.MyButton();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlBorrwedBooks.setBackground(new java.awt.Color(254, 254, 254));
        pnlBorrwedBooks.setRoundBottomLeft(20);
        pnlBorrwedBooks.setRoundBottomRight(20);
        pnlBorrwedBooks.setRoundTopLeft(20);
        pnlBorrwedBooks.setRoundTopRight(20);
        pnlBorrwedBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlBorrwedBooks.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMuonSach.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMuonSach.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tbMuonSach.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbMuonSach);

        panelRound2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 850, 300));

        btnLast.setBackground(new java.awt.Color(145, 227, 168));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLast.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLast.setRadius(10);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        panelRound2.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 60, 40));

        btnNext.setBackground(new java.awt.Color(145, 227, 168));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNext.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNext.setRadius(10);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        panelRound2.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 360, 60, 40));

        btnPrev.setBackground(new java.awt.Color(145, 227, 168));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrev.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrev.setRadius(10);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        panelRound2.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 60, 40));

        btnFirst.setBackground(new java.awt.Color(145, 227, 168));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirst.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirst.setRadius(10);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        panelRound2.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, 60, 40));

        jTabbedPane1.addTab("Các sách đã mượn", panelRound2);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSachImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/stock-photo-74810889.jpg"))); // NOI18N
        lblSachImg.setRadius(20);
        lblSachImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSachImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSachImgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSachImgMousePressed(evt);
            }
        });
        panelRound3.add(lblSachImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 160, 160));

        txtMaSach.setBackground(new java.awt.Color(222, 247, 227));
        txtMaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaSachKeyPressed(evt);
            }
        });
        panelRound3.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 260, 40));

        lblMaSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaSach.setText("Mã Sách");
        panelRound3.add(lblMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        lblTenSach1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenSach1.setText("Tên Sách");
        panelRound3.add(lblTenSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        txtTenSach.setBackground(new java.awt.Color(222, 247, 227));
        txtTenSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenSachKeyPressed(evt);
            }
        });
        panelRound3.add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 260, 40));

        txtNgayMuon.setBackground(new java.awt.Color(222, 247, 227));
        panelRound3.add(txtNgayMuon, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 200, 40));

        lblNgaySinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày mượn");
        panelRound3.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, -1, -1));

        btnIconCld.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld.setAutoscrolls(true);
        btnIconCld.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCldActionPerformed(evt);
            }
        });
        panelRound3.add(btnIconCld, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, 60, 40));

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        panelRound3.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, -1, -1));

        txtTaiKhoanMuon.setBackground(new java.awt.Color(222, 247, 227));
        txtTaiKhoanMuon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTaiKhoanMuonKeyPressed(evt);
            }
        });
        panelRound3.add(txtTaiKhoanMuon, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 260, 40));

        lblMaSach1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaSach1.setText("Tài khoản mượn");
        panelRound3.add(lblMaSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));
        panelRound3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 800, 10));

        btnDocSach.setBackground(new java.awt.Color(87, 190, 110));
        btnDocSach.setForeground(new java.awt.Color(255, 255, 255));
        btnDocSach.setText("Đọc sách");
        btnDocSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnDocSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnDocSach.setRadius(10);
        btnDocSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocSachActionPerformed(evt);
            }
        });
        panelRound3.add(btnDocSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 280, 110, 40));

        btnLuuThongTin1.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuThongTin1.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuThongTin1.setText("Hủy mượn sách");
        btnLuuThongTin1.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuThongTin1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnLuuThongTin1.setRadius(10);
        panelRound3.add(btnLuuThongTin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 200, 40));

        jTabbedPane1.addTab("Thông tin mượn sách", panelRound3);

        pnlBorrwedBooks.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

        pnlExit1.setBackground(new java.awt.Color(253, 127, 127));
        pnlExit1.setRoundBottomLeft(20);
        pnlExit1.setRoundTopRight(20);
        pnlExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlExit1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlExit1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlExit1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlExit1MousePressed(evt);
            }
        });
        pnlExit1.setLayout(new java.awt.GridBagLayout());

        lblExit1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        lblExit1.setForeground(new java.awt.Color(255, 255, 255));
        lblExit1.setText("X");
        lblExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblExit1MousePressed(evt);
            }
        });
        pnlExit1.add(lblExit1, new java.awt.GridBagConstraints());

        pnlBorrwedBooks.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlBorrwedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlBorrwedBooks, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        //        int index = tblTacGia.getSelectedRow();
        //        UtilityHelper.last(index, tblTacGia, listTL);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        //        int index = tblTheLoai.getSelectedRow();
        //        UtilityHelper.next(index, tblTheLoai, listTL);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        //        int index = tblTheLoai.getSelectedRow();
        //        UtilityHelper.previous(index, tblTheLoai, listTL);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        //        int index = tblTheLoai.getSelectedRow();
        //        UtilityHelper.first(index, tblTheLoai);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExit1MouseClicked

    private void lblExit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lblExit1MousePressed

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseExited

    private void pnlExit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_pnlExit1MousePressed

    private void lblSachImgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {

        }
    }//GEN-LAST:event_lblSachImgMousePressed

    private void lblSachImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseExited

    }//GEN-LAST:event_lblSachImgMouseExited

    private void lblSachImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseEntered

    }//GEN-LAST:event_lblSachImgMouseEntered

    private void txtMaSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtMaSachKeyPressed

    private void txtTenSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtTenSachKeyPressed

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
       
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayMuon.setText(String.valueOf(formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void txtTaiKhoanMuonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTaiKhoanMuonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaiKhoanMuonKeyPressed

    private void btnDocSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocSachActionPerformed
        Sach sach = new Sach();
        new readingDialog(this, true, sach).setVisible(true);
    }//GEN-LAST:event_btnDocSachActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BorrowedBooksDiaLog dialog = new BorrowedBooksDiaLog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar Calendar;
    private com.ebooks.Compoment.MyButton btnDocSach;
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnLuuThongTin1;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblMaSach1;
    private javax.swing.JLabel lblNgaySinh;
    private com.ebooks.Compoment.ImageBoder lblSachImg;
    private javax.swing.JLabel lblTenSach1;
    private com.ebooks.Compoment.PanelRound panelRound2;
    private com.ebooks.Compoment.PanelRound panelRound3;
    private com.ebooks.Compoment.PanelRound pnlBorrwedBooks;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.Table tbMuonSach;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtTaiKhoanMuon;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}