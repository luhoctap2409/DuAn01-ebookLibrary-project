/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.helper.ShareHelper;
import java.awt.Color;

/**
 *
 * @author Thinh
 */
public class StartUpDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form StartUpDiaLog
     */
    public StartUpDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0,0,0,0));
        setIconImage(ShareHelper.APP_ICON);
        Loading();
        
    }
    
    
     public void Loading(){
        setLocationRelativeTo(null);
        //thread là 1 khỗi mã thực hiện nhiệm vụ
        Thread t=new Thread(){
                int i=-1;
          @Override
          public void run(){
              while(true){
                  try {
                      i++;
                      pgbLoading.setValue(i);
                      lblPhanTram.setText(i + "%");
                      if(i==20)lblStatus.setText("Đang khởi tạo cái modun...");
                      if(i==50)lblStatus.setText("Đang kết nối CSDL...");
                      if(i==90)lblStatus.setText("Chuẩn bị vào chương trình...");
                      if(i==100){
                         StartUpDiaLog.this.dispose();   //đóng ManHinhChao
                         break; 
                      }
                      Thread.sleep(20);   //thread tạm dừng hoạt động trong 20 ms
                  } catch (InterruptedException ex) {
                      break;
                  }
              }
          }  
        };
        t.start();       //thread bắt đầu hoạt động
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.ebooks.Compoment.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        imageBoder2 = new com.ebooks.Compoment.ImageBoder();
        lblStatus = new javax.swing.JLabel();
        lblPhanTram = new javax.swing.JLabel();
        imageBoder1 = new com.ebooks.Compoment.ImageBoder();
        pgbLoading = new javax.swing.JProgressBar();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 34)); // NOI18N
        jLabel1.setText("bạn yêu thích ");
        panelBorder1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 34)); // NOI18N
        jLabel2.setText("Hãy đọc những cuốn sách");
        panelBorder1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Open Sans Medium", 0, 24)); // NOI18N
        jLabel3.setText("Học tập là ánh mắt của tri thức ");
        panelBorder1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));
        panelBorder1.add(imageBoder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, -1, -1));

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatus.setText("Khởi động ứng dụng...");
        panelBorder1.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 410, -1));

        lblPhanTram.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPhanTram.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhanTram.setText("0%");
        panelBorder1.add(lblPhanTram, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, 30, -1));

        imageBoder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/stock-photo-74810889.jpg"))); // NOI18N
        imageBoder1.setRadius(25);
        panelBorder1.add(imageBoder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 350, 350));

        pgbLoading.setBackground(new java.awt.Color(204, 204, 204));
        pgbLoading.setForeground(new java.awt.Color(153, 255, 153));
        panelBorder1.add(pgbLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 440, 14));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds.png"))); // NOI18N
        panelBorder1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StartUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartUpDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StartUpDiaLog dialog = new StartUpDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.ImageBoder imageBoder1;
    private com.ebooks.Compoment.ImageBoder imageBoder2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPhanTram;
    private javax.swing.JLabel lblStatus;
    private com.ebooks.Compoment.PanelBorder panelBorder1;
    private javax.swing.JProgressBar pgbLoading;
    // End of variables declaration//GEN-END:variables
}
