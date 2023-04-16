/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.dao.TheLoai_SachDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.MovingForm;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.NhaXuatBan;
import com.ebooks.model.TheLoai;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class TypeBookDiaLog extends javax.swing.JDialog {

    private boolean congTac = false;
    private List<TheLoai> ListTL = new ArrayList<TheLoai>();
    private TheLoaiDAO DAOTL = new TheLoaiDAO();
    private SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
    private TheLoai_SachDAO DAOTL_S = new TheLoai_SachDAO();
    public TypeBookDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0,0,0,0));
        Calendar.setVisible(congTac);
        MovingForm.initMoving(this, pnlMain);
        congTac = !congTac;
        fillTableTypeBook();
        statusButton(false); 
        txtTenDangNhap.setText(ShareHelper.USER.getTenDangNhap());
    }

    private void fillTableTypeBook() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblTheLoai.getModel();
        tblTheLoai.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            ListTL = DAOTL.selectByKeyword(txtTimTheLoai.getText());
            for (TheLoai theLoai : ListTL) {
                Object[] row = {theLoai.getMaTheLoai(), theLoai.getTenTheLoai(), formats.format(theLoai.getNgayTao()), theLoai.getTenDangNhap(), theLoai.getMoTaTheLoai()};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    
     
    private void statusButton(boolean status){
        btnXoaTL.setEnabled(status);
        btnCapNhat.setEnabled(status);
        btnThemMoi.setEnabled(!status);
        return;
    }

   

    private TheLoai getFormTypeBook() {
        TheLoai theLoaiNew = new TheLoai();
        theLoaiNew.setTenDangNhap(txtTenDangNhap.getText());
        theLoaiNew.setMoTaTheLoai(txtMoTaTheLoai.getText());
        theLoaiNew.setNgayTao(Calendar.getDate());
        theLoaiNew.setTenTheLoai(txtTenTheLoai.getText());
        return theLoaiNew;
    }

    private void setFormTypeBook(int indexTable) {
        String maTheLoai = tblTheLoai.getValueAt(indexTable, 0).toString();
        TheLoai theLoai = DAOTL.findById(maTheLoai);
        txtMaTheLoai.setText(theLoai.getMaTheLoai());
        txtTenTheLoai.setText(theLoai.getTenTheLoai());
        txtNgayĐăng.setText(String.valueOf(formats.format(theLoai.getNgayTao())));
        txtTenDangNhap.setText(theLoai.getTenDangNhap());
        txtMoTaTheLoai.setText(theLoai.getMoTaTheLoai());
    }
    //-----------------------------------THAO TAC DỮ LIỆU

    private boolean checkForm(){
        if(!UtilityHelper.checkNullText(lblNgayDang, txtNgayĐăng)){
            return false;
        }else if(!UtilityHelper.checkNullText(lblTenTheLoai, txtTenTheLoai)){
            return false;
        }else if(!UtilityHelper.checkNullText(lblTenNguoiDang, txtTenDangNhap)){
            return false;
        }
        return true;
    }
    
    private void CreateTheLoai() {
        try {
            DAOTL.insert(getFormTypeBook());
            DialogHelper.alert(this, "Tạo mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Tạo mới thất bại");
        }
    }
    
    
    private void UpdateTheLoai() {
        try {
            TheLoai theLoais = getFormTypeBook();
            theLoais.setMaTheLoai(txtMaTheLoai.getText());
            DAOTL.update(theLoais);
            statusButton(false);
            clearForm();
            DialogHelper.alert(this, "Lưu thông tin thành công");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lưu thông tin thất bại");
        }
    }

    private void clearForm() {
        txtMaTheLoai.setText(null);
        txtTenTheLoai.setText(null);
        txtMoTaTheLoai.setText(null);
        txtNgayĐăng.setText(String.valueOf(formats.format(new Date())));
    }

    private void DeleteTheLoai() {
        try {
            boolean result = DialogHelper.confirm(this, "bạn muốn xóa ? Những cuốn sách không còn thể loại này!");
            if (result) {
                DAOTL_S.deleteTL(txtMaTheLoai.getText());
                DAOTL.delete(txtMaTheLoai.getText());
                statusButton(false);
                clearForm();
                DialogHelper.alert(this, "Xóa thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Thể loại đăng được sử dụng");
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

        pnlMain = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        TabTheLoai = new javax.swing.JTabbedPane();
        tabDanhSachTheLoai = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTheLoai = new com.ebooks.Compoment.Table();
        panelRound4 = new com.ebooks.Compoment.PanelRound();
        txtTimTheLoai = new com.ebooks.Compoment.SearchText();
        btnTimTheLoai = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnLast = new com.ebooks.Compoment.MyButton();
        tabThongTinTheLoai = new com.ebooks.Compoment.PanelRound();
        txtMaTheLoai = new javax.swing.JTextField();
        lblMaTheLoai = new javax.swing.JLabel();
        txtNgayĐăng = new javax.swing.JTextField();
        lblNgayDang = new javax.swing.JLabel();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTaTheLoai = new javax.swing.JTextArea();
        lblMoTa = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        lblTenNguoiDang = new javax.swing.JLabel();
        txtTenTheLoai = new javax.swing.JTextField();
        lblTenTheLoai = new javax.swing.JLabel();
        btnXoaTL = new com.ebooks.Compoment.MyButton();
        btnReset = new com.ebooks.Compoment.MyButton();
        btnCapNhat = new com.ebooks.Compoment.MyButton();
        btnThemMoi = new com.ebooks.Compoment.MyButton();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        pnlMain.setBackground(new java.awt.Color(254, 254, 254));
        pnlMain.setRoundBottomLeft(20);
        pnlMain.setRoundBottomRight(20);
        pnlMain.setRoundTopLeft(20);
        pnlMain.setRoundTopRight(20);
        pnlMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMain.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        TabTheLoai.setBackground(new java.awt.Color(255, 255, 255));
        TabTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TabTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabTheLoaiMouseClicked(evt);
            }
        });

        tabDanhSachTheLoai.setBackground(new java.awt.Color(255, 255, 255));
        tabDanhSachTheLoai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thể loại", "Tên thể loại", "Ngày đăng", "Tài khoản đăng", "Mô tả thể loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTheLoai.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblTheLoai.getTableHeader().setReorderingAllowed(false);
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTheLoai);

        tabDanhSachTheLoai.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 300));

        panelRound4.setBackground(new java.awt.Color(232, 244, 234));
        panelRound4.setRoundBottomLeft(10);
        panelRound4.setRoundBottomRight(10);
        panelRound4.setRoundTopLeft(10);
        panelRound4.setRoundTopRight(10);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimTheLoai.setBackground(new java.awt.Color(232, 244, 234));
        txtTimTheLoai.setForeground(new java.awt.Color(51, 51, 51));
        txtTimTheLoai.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        txtTimTheLoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimTheLoaiKeyPressed(evt);
            }
        });
        panelRound4.add(txtTimTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 40));

        btnTimTheLoai.setBackground(new java.awt.Color(232, 244, 234));
        btnTimTheLoai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        btnTimTheLoai.setBoderColor(new java.awt.Color(232, 244, 234));
        btnTimTheLoai.setRadius(10);
        btnTimTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTheLoaiActionPerformed(evt);
            }
        });
        panelRound4.add(btnTimTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

        tabDanhSachTheLoai.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 40));

        btnFirst.setBackground(new java.awt.Color(145, 227, 168));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirst.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirst.setRadius(10);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        tabDanhSachTheLoai.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 60, 40));

        btnPrev.setBackground(new java.awt.Color(145, 227, 168));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrev.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrev.setRadius(10);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        tabDanhSachTheLoai.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 60, 40));

        btnNext.setBackground(new java.awt.Color(145, 227, 168));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNext.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNext.setRadius(10);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        tabDanhSachTheLoai.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 60, 40));

        btnLast.setBackground(new java.awt.Color(145, 227, 168));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLast.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLast.setRadius(10);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        tabDanhSachTheLoai.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, 60, 40));

        TabTheLoai.addTab("Danh sách thể loại", tabDanhSachTheLoai);

        tabThongTinTheLoai.setBackground(new java.awt.Color(255, 255, 255));
        tabThongTinTheLoai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaTheLoai.setEditable(false);
        txtMaTheLoai.setBackground(new java.awt.Color(222, 247, 227));
        tabThongTinTheLoai.add(txtMaTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 360, 40));

        lblMaTheLoai.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaTheLoai.setText("Mã thể loại");
        tabThongTinTheLoai.add(lblMaTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        txtNgayĐăng.setBackground(new java.awt.Color(222, 247, 227));
        tabThongTinTheLoai.add(txtNgayĐăng, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 310, 40));

        lblNgayDang.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgayDang.setText("Ngày đăng");
        tabThongTinTheLoai.add(lblNgayDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        btnIconCld.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld.setAutoscrolls(true);
        btnIconCld.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCldActionPerformed(evt);
            }
        });
        tabThongTinTheLoai.add(btnIconCld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, 50, 40));

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        tabThongTinTheLoai.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, -1, -1));

        txtMoTaTheLoai.setColumns(10);
        txtMoTaTheLoai.setLineWrap(true);
        txtMoTaTheLoai.setRows(5);
        txtMoTaTheLoai.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtMoTaTheLoai);

        tabThongTinTheLoai.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 790, 100));

        lblMoTa.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMoTa.setText("Mô tả thể loại");
        tabThongTinTheLoai.add(lblMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 120, -1));

        txtTenDangNhap.setEditable(false);
        txtTenDangNhap.setBackground(new java.awt.Color(222, 247, 227));
        tabThongTinTheLoai.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 360, 40));

        lblTenNguoiDang.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenNguoiDang.setText("Tên tài khoản đăng");
        tabThongTinTheLoai.add(lblTenNguoiDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        txtTenTheLoai.setBackground(new java.awt.Color(222, 247, 227));
        tabThongTinTheLoai.add(txtTenTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 360, 40));

        lblTenTheLoai.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenTheLoai.setText("Tên thể loại");
        tabThongTinTheLoai.add(lblTenTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        btnXoaTL.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaTL.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaTL.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaTL.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaTL.setRadius(10);
        btnXoaTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTLActionPerformed(evt);
            }
        });
        tabThongTinTheLoai.add(btnXoaTL, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 40, 40));

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
        tabThongTinTheLoai.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 40, 40));

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
        tabThongTinTheLoai.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 120, 40));

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
        tabThongTinTheLoai.add(btnThemMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 120, 40));

        TabTheLoai.addTab("Thông Tin thể loại", tabThongTinTheLoai);

        pnlMain.add(TabTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

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
        });
        pnlExit1.setLayout(new java.awt.GridBagLayout());

        lblExit1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        lblExit1.setForeground(new java.awt.Color(255, 255, 255));
        lblExit1.setText("X");
        lblExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit1MouseClicked(evt);
            }
        });
        pnlExit1.add(lblExit1, new java.awt.GridBagConstraints());

        pnlMain.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTheLoaiActionPerformed
        fillTableTypeBook();
    }//GEN-LAST:event_btnTimTheLoaiActionPerformed

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        txtNgayĐăng.setText(String.valueOf(formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
            this.dispose();
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.first(index, tblTheLoai);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.previous(index, tblTheLoai, ListTL);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.next(index, tblTheLoai, ListTL);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.last(index, tblTheLoai, ListTL);
    }//GEN-LAST:event_btnLastActionPerformed

    private void TabTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabTheLoaiMouseClicked
        fillTableTypeBook();
    }//GEN-LAST:event_TabTheLoaiMouseClicked

    private void tblTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblTheLoai.getSelectedRow();
            setFormTypeBook(index);
            statusButton(true);
            TabTheLoai.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblTheLoaiMouseClicked

    private void txtTimTheLoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTheLoaiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTableTypeBook();
        }
    }//GEN-LAST:event_txtTimTheLoaiKeyPressed

    private void btnXoaTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTLActionPerformed
        if (checkForm()) {
            DeleteTheLoai();
        }
    }//GEN-LAST:event_btnXoaTLActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        statusButton(false);
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkForm()) {
            UpdateTheLoai();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (checkForm()) {
            CreateTheLoai();
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        pnlExit1.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        pnlExit1.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_pnlExit1MouseExited

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
            java.util.logging.Logger.getLogger(TypeBookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TypeBookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TypeBookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TypeBookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TypeBookDiaLog dialog = new TypeBookDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JTabbedPane TabTheLoai;
    private com.ebooks.Compoment.MyButton btnCapNhat;
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnReset;
    private com.ebooks.Compoment.MyButton btnThemMoi;
    private com.ebooks.Compoment.MyButton btnTimTheLoai;
    private com.ebooks.Compoment.MyButton btnXoaTL;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaTheLoai;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblNgayDang;
    private javax.swing.JLabel lblTenNguoiDang;
    private javax.swing.JLabel lblTenTheLoai;
    private com.ebooks.Compoment.PanelRound panelRound4;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRound pnlMain;
    private com.ebooks.Compoment.PanelRound tabDanhSachTheLoai;
    private com.ebooks.Compoment.PanelRound tabThongTinTheLoai;
    private com.ebooks.Compoment.Table tblTheLoai;
    private javax.swing.JTextField txtMaTheLoai;
    private javax.swing.JTextArea txtMoTaTheLoai;
    private javax.swing.JTextField txtNgayĐăng;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTenTheLoai;
    private com.ebooks.Compoment.SearchText txtTimTheLoai;
    // End of variables declaration//GEN-END:variables
}
