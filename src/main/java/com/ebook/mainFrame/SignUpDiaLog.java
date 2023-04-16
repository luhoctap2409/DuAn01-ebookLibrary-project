/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.ThongTinNguoiDungDAO;

import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.MovingForm;
import com.ebooks.helper.UtilityHelper;

import com.ebooks.model.TaiKhoan;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Time;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class SignUpDiaLog extends javax.swing.JDialog {

    private TaiKhoanDAO DAOTK = new TaiKhoanDAO();

    public SignUpDiaLog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        MovingForm.initMoving(this, pnlMainDialog);
    }

    private boolean checkForm() {
        if (!UtilityHelper.checkNullText(lblTenDangNhap, txtTenDangNhap)) {
            return false;
        } else if (!UtilityHelper.checkNullPass(lblMatKhau, txtMatKhau)) {
            return false;
        } else if (!UtilityHelper.checkNullPass(lblXacNhan, txtXacNhan)) {
            return false;
        } else if (!txtTenDangNhap.getText().matches("[a-z0-9_-]{6,12}$")) {
            DialogHelper.alert(this, "Tên đăng nhập có độ tài từ 6 đến 12 ký tự, không có khoảng trắng, ký tự hoa và không dấu");
            return false;
        } else if (!txtMatKhau.getPassword().toString().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})")) {
            DialogHelper.alert(this, "Mật khẩu độ tài từ 6 đến 12 ký tự và phải chứa ít nhất 1 ký tự số từ, ký tự chữ hoa, tự chữ thường, ký tự đặc biệt");
            return false;
        }
        return true;
    }

    private void DangKy(TaiKhoan taiKhoanNew) {
        TaiKhoan taiKhoan = DAOTK.findById(taiKhoanNew.getTenDangNhap());
        if (taiKhoan == null) {
            if (!String.valueOf(txtMatKhau.getPassword()).equals(String.valueOf(txtMatKhau.getText()))) {
                DialogHelper.alert(this, "Xác nhận không khớp");
                return;
            } else {
                taiKhoanNew.setMatKhau(String.valueOf(this.txtMatKhau.getPassword()));
                DAOTK.insert(taiKhoanNew);
                DialogHelper.alert(this, "Khởi tạo thành công");
                return;
            }
        } else {
            DialogHelper.alert(this, "Tài khoản đã tồn tại !");
            return;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMainDialog = new com.ebooks.Compoment.PanelBorder();
        imageBoder2 = new com.ebooks.Compoment.ImageBoder();
        jLabel2 = new javax.swing.JLabel();
        btnTaoTaiKhoan = new com.ebooks.Compoment.MyButton();
        lblTenDangNhap = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblXacNhan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTroVe = new com.ebooks.Compoment.MyButton();
        txtTenDangNhap = new com.ebooks.Compoment.txtFieldBoder();
        txtXacNhan = new javax.swing.JPasswordField();
        txtMatKhau = new javax.swing.JPasswordField();
        lblMatKhau = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlMainDialog.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/2d23848e893747691e26.jpg"))); // NOI18N
        imageBoder2.setRadius(25);
        pnlMainDialog.add(imageBoder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 380, 380));

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 30)); // NOI18N
        jLabel2.setText("Đăng ký vào Nerds");
        pnlMainDialog.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        btnTaoTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnTaoTaiKhoan.setBorder(null);
        btnTaoTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoTaiKhoan.setText("Tạo Tài Khoản");
        btnTaoTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnTaoTaiKhoan.setColorOver(new java.awt.Color(54, 172, 63));
        btnTaoTaiKhoan.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnTaoTaiKhoan.setRadius(10);
        btnTaoTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTaoTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTaoTaiKhoanMouseExited(evt);
            }
        });
        btnTaoTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoTaiKhoanActionPerformed(evt);
            }
        });
        pnlMainDialog.add(btnTaoTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 140, 40));

        lblTenDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTenDangNhap.setText("Tên Đăng Nhập");
        pnlMainDialog.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        pnlMainDialog.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        lblXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblXacNhan.setText("Xác Nhận");
        pnlMainDialog.add(lblXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainDialog.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnTroVe.setBackground(new java.awt.Color(87, 190, 110));
        btnTroVe.setBorder(null);
        btnTroVe.setForeground(new java.awt.Color(255, 255, 255));
        btnTroVe.setText("Trở về");
        btnTroVe.setBoderColor(new java.awt.Color(87, 190, 110));
        btnTroVe.setColorOver(new java.awt.Color(54, 172, 63));
        btnTroVe.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnTroVe.setRadius(10);
        btnTroVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTroVeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTroVeMouseExited(evt);
            }
        });
        btnTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroVeActionPerformed(evt);
            }
        });
        pnlMainDialog.add(btnTroVe, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 60, 40));

        txtTenDangNhap.setBackground(new java.awt.Color(220, 250, 220));
        txtTenDangNhap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTenDangNhap.setRadius(10);
        txtTenDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenDangNhapKeyPressed(evt);
            }
        });
        pnlMainDialog.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 460, 40));

        txtXacNhan.setBackground(new java.awt.Color(220, 250, 220));
        txtXacNhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtXacNhanKeyPressed(evt);
            }
        });
        pnlMainDialog.add(txtXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 350, 220, 40));

        txtMatKhau.setBackground(new java.awt.Color(220, 250, 220));
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });
        pnlMainDialog.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 210, 40));

        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMatKhau.setText("Mật Khẩu");
        pnlMainDialog.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanActionPerformed
        if (checkForm()) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
          
            DangKy(taiKhoan);
        }

    }//GEN-LAST:event_btnTaoTaiKhoanActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        dispose();
        new LogInDiaLog(null, true).setVisible(true);
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void txtTenDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDangNhapKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
                DangKy(taiKhoan);
            }
        }
    }//GEN-LAST:event_txtTenDangNhapKeyPressed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
                DangKy(taiKhoan);
            }
        }
    }//GEN-LAST:event_txtMatKhauKeyPressed

    private void txtXacNhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtXacNhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
                DangKy(taiKhoan);
            }
        }
    }//GEN-LAST:event_txtXacNhanKeyPressed

    private void btnTaoTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanMouseEntered
        btnTaoTaiKhoan.setBackground(new Color(68, 152, 87));
    }//GEN-LAST:event_btnTaoTaiKhoanMouseEntered

    private void btnTaoTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoTaiKhoanMouseExited
        btnTaoTaiKhoan.setBackground(new Color(87, 170, 100));
    }//GEN-LAST:event_btnTaoTaiKhoanMouseExited

    private void btnTroVeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTroVeMouseEntered
        btnTroVe.setBackground(new Color(68, 152, 87));
    }//GEN-LAST:event_btnTroVeMouseEntered

    private void btnTroVeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTroVeMouseExited
        btnTroVe.setBackground(new Color(87, 170, 100));
    }//GEN-LAST:event_btnTroVeMouseExited

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
            java.util.logging.Logger.getLogger(SignUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SignUpDiaLog dialog = new SignUpDiaLog(new javax.swing.JDialog(), true);
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
    private com.ebooks.Compoment.MyButton btnTaoTaiKhoan;
    private com.ebooks.Compoment.MyButton btnTroVe;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.ebooks.Compoment.ImageBoder imageBoder2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JLabel lblXacNhan;
    private com.ebooks.Compoment.PanelBorder pnlMainDialog;
    private javax.swing.JPasswordField txtMatKhau;
    private com.ebooks.Compoment.txtFieldBoder txtTenDangNhap;
    private javax.swing.JPasswordField txtXacNhan;
    // End of variables declaration//GEN-END:variables
}