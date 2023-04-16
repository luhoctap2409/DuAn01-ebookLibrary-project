/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.NhaXuatBanDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.MovingForm;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.NhaXuatBan;
import com.ebooks.model.TaiKhoan;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class PublishingCompanyDiaLog extends javax.swing.JDialog {

    private NhaXuatBanDAO DAONXB = new NhaXuatBanDAO();
    private List<NhaXuatBan> ListNXB = new ArrayList<NhaXuatBan>();
    private SachDAO DAOS = new SachDAO();
    public PublishingCompanyDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        MovingForm.initMoving(this, pnlPubCompany);
        fillTablePublishingCompany();
        setBackground(new Color(0, 0, 0, 0));
        txtMaNhaXuatBan.setEditable(false);
        statusButton(false);
    }

    private void fillTablePublishingCompany() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblNhaXuatBan.getModel();
        tblNhaXuatBan.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            ListNXB = DAONXB.selectByKeyword(txtTimNhaXuatBan.getText(), txtTimNhaXuatBan.getText());
            for (NhaXuatBan nhaXuatBan : ListNXB) {
                Object[] row = {nhaXuatBan.getMaNXB(), nhaXuatBan.getTenNXB(), nhaXuatBan.getDiaChiNXB(), nhaXuatBan.getMota()};
                model.addRow(row);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
    private void statusButton(boolean status){
        btnXoaNXB.setEnabled(status);
        btnCapNhat.setEnabled(status);
        btnThemMoi.setEnabled(!status);
        return;
    }

    private boolean ChecForm() {
        if (!UtilityHelper.checkNullText(lblTenNXB, txtTenNhaXuatBan)) {
            return false;
        } else if (!UtilityHelper.checkNullText(lblDiaChiNXB, txtDiaChi)) {
            return false;
        }
        return true;
    }

    private void clearFormNXB() {
        txtMaNhaXuatBan.setText(null);
        txtTenNhaXuatBan.setText(null);
        txtDiaChi.setText(null);
        txtMoTaNXB.setText(null);
        return;
    }

    private void setFormNXB(int indexTable) {
        String maNXB = tblNhaXuatBan.getValueAt(indexTable, 0).toString();
        NhaXuatBan nhaXuatBan = DAONXB.findById(maNXB);
        if (nhaXuatBan != null) {
            txtMaNhaXuatBan.setText(nhaXuatBan.getMaNXB());
            txtTenNhaXuatBan.setText(nhaXuatBan.getTenNXB());
            txtDiaChi.setText(nhaXuatBan.getDiaChiNXB());
            txtMoTaNXB.setText(nhaXuatBan.getMota());
            return;
        }

    }

    private NhaXuatBan getFormNXB() {
        NhaXuatBan nhaXuatBan = new NhaXuatBan();
        nhaXuatBan.setTenNXB(txtTenNhaXuatBan.getText());
        nhaXuatBan.setDiaChiNXB(txtDiaChi.getText());
        nhaXuatBan.setMota(txtMoTaNXB.getText());
        return nhaXuatBan;
    }
    
    //-------------------------------------------THAO TAC DU LIEU-------------------------------------------//

    private void CreateNXB() {
        try {
            DAONXB.insert(getFormNXB());
            DialogHelper.alert(this, "Thêm mới thành công");
            return;
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại");
            e.printStackTrace();
        }
    }

    private void UpadateNXB() {
        try {
            NhaXuatBan nhaXuatBan = getFormNXB();
            nhaXuatBan.setMaNXB(txtMaNhaXuatBan.getText());
            DAONXB.update(nhaXuatBan);
            DialogHelper.alert(this, "Lưu thông tin thành công");
            statusButton(false);
            clearFormNXB();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lưu thông tin thất bại");
        }
    }

    private void DeleteNXB() {
        try {
            boolean result = DialogHelper.confirm(this, "bạn muốn xóa ?");
            if (result) {
                DAOS.deleteNXB(txtMaNhaXuatBan.getText());
                DAONXB.delete(txtMaNhaXuatBan.getText());
                DialogHelper.alert(this, "Xóa thành công");
                statusButton(false);
                clearFormNXB();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Xóa thất bại");
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

        pnlPubCompany = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        tabNhaXuatBan = new javax.swing.JTabbedPane();
        panelRound2 = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhaXuatBan = new com.ebooks.Compoment.Table();
        panelRound4 = new com.ebooks.Compoment.PanelRound();
        txtTimNhaXuatBan = new com.ebooks.Compoment.SearchText();
        btnTimNhaXuatBan = new com.ebooks.Compoment.MyButton();
        btnLast = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        panelRound3 = new com.ebooks.Compoment.PanelRound();
        txtTenNhaXuatBan = new javax.swing.JTextField();
        lblTenNXB = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTaNXB = new javax.swing.JTextArea();
        lblMoTa = new javax.swing.JLabel();
        btnThemMoi = new com.ebooks.Compoment.MyButton();
        btnXoaNXB = new com.ebooks.Compoment.MyButton();
        txtMaNhaXuatBan = new javax.swing.JTextField();
        lblMaNXB = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblDiaChiNXB = new javax.swing.JLabel();
        btnReset = new com.ebooks.Compoment.MyButton();
        btnCapNhat = new com.ebooks.Compoment.MyButton();
        pnlExit = new com.ebooks.Compoment.PanelRound();
        lblExit2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnlPubCompany.setBackground(new java.awt.Color(254, 254, 254));
        pnlPubCompany.setRoundBottomLeft(20);
        pnlPubCompany.setRoundBottomRight(20);
        pnlPubCompany.setRoundTopLeft(20);
        pnlPubCompany.setRoundTopRight(20);
        pnlPubCompany.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlPubCompany.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabNhaXuatBan.setBackground(new java.awt.Color(255, 255, 255));
        tabNhaXuatBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabNhaXuatBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabNhaXuatBanMouseClicked(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhaXuatBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhà xuất bản", "Tên nhà xuất bản", "Địa chỉ nhà xuất bản", "Mô tả về nhà xuất bản"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhaXuatBan.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblNhaXuatBan.getTableHeader().setReorderingAllowed(false);
        tblNhaXuatBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhaXuatBanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhaXuatBan);

        panelRound2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 300));

        panelRound4.setBackground(new java.awt.Color(232, 244, 234));
        panelRound4.setRoundBottomLeft(10);
        panelRound4.setRoundBottomRight(10);
        panelRound4.setRoundTopLeft(10);
        panelRound4.setRoundTopRight(10);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimNhaXuatBan.setBackground(new java.awt.Color(232, 244, 234));
        txtTimNhaXuatBan.setForeground(new java.awt.Color(51, 51, 51));
        txtTimNhaXuatBan.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        txtTimNhaXuatBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimNhaXuatBanKeyPressed(evt);
            }
        });
        panelRound4.add(txtTimNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 40));

        btnTimNhaXuatBan.setBackground(new java.awt.Color(232, 244, 234));
        btnTimNhaXuatBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        btnTimNhaXuatBan.setBoderColor(new java.awt.Color(232, 244, 234));
        btnTimNhaXuatBan.setRadius(10);
        btnTimNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimNhaXuatBanActionPerformed(evt);
            }
        });
        panelRound4.add(btnTimNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

        panelRound2.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 40));

        btnLast.setBackground(new java.awt.Color(145, 227, 168));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLast.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLast.setRadius(10);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        panelRound2.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, 60, 40));

        btnNext.setBackground(new java.awt.Color(145, 227, 168));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNext.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNext.setRadius(10);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        panelRound2.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 60, 40));

        btnPrev.setBackground(new java.awt.Color(145, 227, 168));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrev.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrev.setRadius(10);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        panelRound2.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 60, 40));

        btnFirst.setBackground(new java.awt.Color(145, 227, 168));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirst.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirst.setRadius(10);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        panelRound2.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 60, 40));

        tabNhaXuatBan.addTab("Danh sách nhà xuất bản", panelRound2);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenNhaXuatBan.setBackground(new java.awt.Color(222, 247, 227));
        panelRound3.add(txtTenNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 380, 40));

        lblTenNXB.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenNXB.setText("Tên Nhà xuất bản");
        panelRound3.add(lblTenNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        txtMoTaNXB.setColumns(5);
        txtMoTaNXB.setLineWrap(true);
        txtMoTaNXB.setRows(20);
        txtMoTaNXB.setTabSize(10);
        txtMoTaNXB.setToolTipText("");
        txtMoTaNXB.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtMoTaNXB);

        panelRound3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 810, 120));

        lblMoTa.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMoTa.setText("Mô Tả");
        panelRound3.add(lblMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 120, -1));

        btnThemMoi.setBackground(new java.awt.Color(87, 190, 110));
        btnThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemMoi.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnThemMoi.setRadius(10);
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });
        panelRound3.add(btnThemMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 120, 40));

        btnXoaNXB.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaNXB.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNXB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaNXB.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaNXB.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaNXB.setRadius(10);
        btnXoaNXB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNXBActionPerformed(evt);
            }
        });
        panelRound3.add(btnXoaNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 40, 40));

        txtMaNhaXuatBan.setBackground(new java.awt.Color(222, 247, 227));
        txtMaNhaXuatBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaNhaXuatBanMouseClicked(evt);
            }
        });
        panelRound3.add(txtMaNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 380, 40));

        lblMaNXB.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaNXB.setText("Mã Nhà Xuất bản");
        panelRound3.add(lblMaNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtDiaChi.setBackground(new java.awt.Color(222, 247, 227));
        panelRound3.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 810, 40));

        lblDiaChiNXB.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblDiaChiNXB.setText("Địa chỉ trụ sở");
        panelRound3.add(lblDiaChiNXB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        btnReset.setBackground(new java.awt.Color(87, 190, 110));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnReset.setBoderColor(new java.awt.Color(87, 190, 110));
        btnReset.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnReset.setRadius(10);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        panelRound3.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 40, 40));

        btnCapNhat.setBackground(new java.awt.Color(87, 190, 110));
        btnCapNhat.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBoderColor(new java.awt.Color(87, 190, 110));
        btnCapNhat.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnCapNhat.setRadius(10);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        panelRound3.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, 120, 40));

        tabNhaXuatBan.addTab("Thông tin nhà xuất bản", panelRound3);

        pnlPubCompany.add(tabNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

        pnlExit.setBackground(new java.awt.Color(253, 127, 127));
        pnlExit.setRoundBottomLeft(20);
        pnlExit.setRoundTopRight(20);
        pnlExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlExitMouseExited(evt);
            }
        });
        pnlExit.setLayout(new java.awt.GridBagLayout());

        lblExit2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        lblExit2.setForeground(new java.awt.Color(255, 255, 255));
        lblExit2.setText("X");
        lblExit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit2MouseClicked(evt);
            }
        });
        pnlExit.add(lblExit2, new java.awt.GridBagConstraints());

        pnlPubCompany.add(pnlExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPubCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPubCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimNhaXuatBanActionPerformed
        fillTablePublishingCompany();
    }//GEN-LAST:event_btnTimNhaXuatBanActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int index = tblNhaXuatBan.getSelectedRow();
        UtilityHelper.last(index, tblNhaXuatBan, ListNXB);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int index = tblNhaXuatBan.getSelectedRow();
        UtilityHelper.next(index, tblNhaXuatBan, ListNXB);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int index = tblNhaXuatBan.getSelectedRow();
        UtilityHelper.previous(index, tblNhaXuatBan, ListNXB);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int index = tblNhaXuatBan.getSelectedRow();
        UtilityHelper.first(index, tblNhaXuatBan);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (ChecForm()) {
            CreateNXB();
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnXoaNXBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNXBActionPerformed
        if(ChecForm()) {
            DeleteNXB();
        }
    }//GEN-LAST:event_btnXoaNXBActionPerformed

    private void tblNhaXuatBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhaXuatBanMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblNhaXuatBan.getSelectedRow();
            setFormNXB(index);
            statusButton(true);
            tabNhaXuatBan.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblNhaXuatBanMouseClicked

    private void tabNhaXuatBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabNhaXuatBanMouseClicked
        fillTablePublishingCompany();
    }//GEN-LAST:event_tabNhaXuatBanMouseClicked

    private void txtTimNhaXuatBanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimNhaXuatBanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTablePublishingCompany();
        }
    }//GEN-LAST:event_txtTimNhaXuatBanKeyPressed

    private void lblExit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit2MouseClicked
        this.dispose();
    }//GEN-LAST:event_lblExit2MouseClicked

    private void pnlExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExitMouseClicked

    private void pnlExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseEntered
        pnlExit.setBackground(new Color(255, 205, 205));
    }//GEN-LAST:event_pnlExitMouseEntered

    private void pnlExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseExited
        pnlExit.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_pnlExitMouseExited

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        statusButton(false);
        clearFormNXB();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
      if(ChecForm()){
          UpadateNXB();
      }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtMaNhaXuatBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaNhaXuatBanMouseClicked
        if(evt.getClickCount()== 2){
            DialogHelper.alert(this,"Mã nhà xuất bản sẽ tự động cấp phát không cần nhập !");
        }
    }//GEN-LAST:event_txtMaNhaXuatBanMouseClicked

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
            java.util.logging.Logger.getLogger(PublishingCompanyDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PublishingCompanyDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PublishingCompanyDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PublishingCompanyDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PublishingCompanyDiaLog dialog = new PublishingCompanyDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnCapNhat;
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnReset;
    private com.ebooks.Compoment.MyButton btnThemMoi;
    private com.ebooks.Compoment.MyButton btnTimNhaXuatBan;
    private com.ebooks.Compoment.MyButton btnXoaNXB;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDiaChiNXB;
    private javax.swing.JLabel lblExit2;
    private javax.swing.JLabel lblMaNXB;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblTenNXB;
    private com.ebooks.Compoment.PanelRound panelRound2;
    private com.ebooks.Compoment.PanelRound panelRound3;
    private com.ebooks.Compoment.PanelRound panelRound4;
    private com.ebooks.Compoment.PanelRound pnlExit;
    private com.ebooks.Compoment.PanelRound pnlPubCompany;
    private javax.swing.JTabbedPane tabNhaXuatBan;
    private com.ebooks.Compoment.Table tblNhaXuatBan;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNhaXuatBan;
    private javax.swing.JTextArea txtMoTaNXB;
    private javax.swing.JTextField txtTenNhaXuatBan;
    private com.ebooks.Compoment.SearchText txtTimNhaXuatBan;
    // End of variables declaration//GEN-END:variables
}
