/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.LichSuMuonSachDAO;
import com.ebooks.dao.MuonSachDAO;
import com.ebooks.dao.SachYeuThichDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.ThongTinNguoiDungDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.MovingForm;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.LichSuMuonSach;
import com.ebooks.model.MuonSach;
import com.ebooks.model.SachYeuThich;
import com.ebooks.model.TaiKhoan;
import com.ebooks.model.ThongTinNguoiDung;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class AccoutAdmainDiaLog extends javax.swing.JDialog {

    private List<TaiKhoan> ListTK = new ArrayList<TaiKhoan>();
    private TaiKhoanDAO DAOTK = new TaiKhoanDAO();
    private ThongTinNguoiDungDAO DAOTTND = new ThongTinNguoiDungDAO();
    private SachYeuThichDAO DAOSYT = new SachYeuThichDAO();
    private MuonSachDAO DAOMS = new MuonSachDAO();
    private LichSuMuonSachDAO DAOLSMS = new LichSuMuonSachDAO();

    public AccoutAdmainDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        MovingForm.initMoving(this, pnlAccAdmin);
        txtTenDangNhap.setEditable(false);
        fillTableTaiKhoan();
    }
    
    public AccoutAdmainDiaLog() {
        
    }

    private void fillTableTaiKhoan() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblTaiKhoan.getModel();
        tblTaiKhoan.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            ListTK = DAOTK.selectByKeyword(txtTimTaiKhoan.getText());
            for (TaiKhoan taiKhoan : ListTK) {
                Object[] row = {taiKhoan.getTenDangNhap(), anMatKhau(taiKhoan.getMatKhau()), taiKhoan.isTrangThai(), taiKhoan.isVaiTro()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private String anMatKhau(String matkhau) {
        String str = "";
        for (int i = 0; i < matkhau.length(); i++) {
            str += "*";
        }
        return str;
    }

    private void setFormTaiKhoan(int indexTable) {
        try {
            if (indexTable != -1) {
                String tenDangNhap = tblTaiKhoan.getValueAt(indexTable, 0).toString();
                TaiKhoan taiKhoan = DAOTK.findById(tenDangNhap);
                if (taiKhoan != null) {
                    txtTenDangNhap.setText(taiKhoan.getTenDangNhap());
                    txtMatKhau.setText(taiKhoan.getMatKhau());
                    rdoHoatDong.setSelected(taiKhoan.isTrangThai());
                    rdoDungHoatDong.setSelected(!taiKhoan.isTrangThai());
                    rdoQuanTriVien.setSelected(taiKhoan.isVaiTro());
                    rdoNguoiDungThuong.setSelected(!taiKhoan.isVaiTro());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearFormTaiKhoan() {
        txtTenDangNhap.setText(null);
        txtMatKhau.setText(null);
        rdoHoatDong.setSelected(false);
        rdoDungHoatDong.setSelected(false);
        rdoQuanTriVien.setSelected(false);
        rdoNguoiDungThuong.setSelected(false);
    }

    private boolean checkForm() {
        if (!UtilityHelper.checkNullText(lblTenDangNhap, txtTenDangNhap)) {
            return false;
        } else if (!UtilityHelper.checkNullPass(lblMatKhau, txtMatKhau)) {
            return false;
        } else if (!String.valueOf(txtMatKhau.getPassword()).matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})")) {
            DialogHelper.alert(this, "Mật khẩu độ tài từ 6 đến 12 ký tự và phải chứa ít nhất 1 ký tự số từ, ký tự chữ hoa, tự chữ thường, ký tự đặc biệt");
            return false;
        }
        return true;
    }

    private TaiKhoan getFormTaiKhoan() {
        TaiKhoan taiKhoanNew = new TaiKhoan();
        taiKhoanNew.setTenDangNhap(txtTenDangNhap.getText());
        taiKhoanNew.setMatKhau(String.valueOf(txtMatKhau.getPassword()));
        taiKhoanNew.setTrangThai(rdoHoatDong.isSelected() ? true : false);
        taiKhoanNew.setVaiTro(rdoQuanTriVien.isSelected() ? true : false);
        return taiKhoanNew;
    }

    private void UptateTaiKhoan() {
        try {
            DAOTK.update(getFormTaiKhoan());
            DialogHelper.alert(this, "Lưu thông tin thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lưu thông tin thất bại");
        }

    }
    
    //test Chỉnh sửa tài khoản
    public String testUptateTaiKhoan(TaiKhoan taiKhoan) {
    	String notification = "";
        try {
            DAOTK.update(taiKhoan);
            notification = "Lưu thông tin thành công";
            return notification;
        } catch (Exception e) {
           notification = "Lưu thông tin thất bại";
            return notification;
        }

    }

    private void DeleteTaiKhoan() {
        if (!rdoQuanTriVien.isSelected()) {
            try {
                boolean result = DialogHelper.confirm(this, "Bạn có chắc muốn xóa !");
                if (result) {
                    DAOMS.deleteND(txtTenDangNhap.getText());
                    DAOTTND.deleteND(txtTenDangNhap.getText());
                    DAOTK.delete(txtTenDangNhap.getText());
                    DialogHelper.alert(this, "Xóa thành công");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            DialogHelper.alert(this, "Tài khoản muốn xóa đang là quản trị viên !");
            return;
        }
    }
    
    //test xóa account
    public String testDeleteTaiKhoan(String username) {
    	String nofitication = "";
    	TaiKhoan taiKhoan = DAOTK.findById(username);
    	boolean role = taiKhoan.isVaiTro();
        if (!role) {
            try {
                boolean result = DialogHelper.confirm(this, "Bạn có chắc muốn xóa !");
                if (result) {
                    DAOMS.deleteND(username);
                    DAOTTND.deleteND(username);
                    DAOTK.delete(username);
                    nofitication = "Xóa thành công";
                    return nofitication;
                }
                else {
                	return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return nofitication;
            }
        } else {
        	nofitication = "Tài khoản muốn xóa đang là quản trị viên !";
            return nofitication;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlAccAdmin = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        tabTaiKhoanAdmin = new javax.swing.JTabbedPane();
        pnlDanhSachTaiKhoan = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTaiKhoan = new com.ebooks.Compoment.Table();
        panelRound4 = new com.ebooks.Compoment.PanelRound();
        txtTimTaiKhoan = new com.ebooks.Compoment.SearchText();
        btnTimTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnLast = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        pnlChiTietTaiKhoan = new com.ebooks.Compoment.PanelRound();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNguoiDungThuong = new javax.swing.JRadioButton();
        rdoQuanTriVien = new javax.swing.JRadioButton();
        lblMatKhau = new javax.swing.JLabel();
        btnLuuThongTin = new com.ebooks.Compoment.MyButton();
        btnXoaTaiKhoan = new com.ebooks.Compoment.MyButton();
        txtTenDangNhap = new javax.swing.JTextField();
        lblTenDangNhap = new javax.swing.JLabel();
        txtMatKhau = new com.ebooks.Compoment.txtFieldPassBoder();
        rdoDungHoatDong = new javax.swing.JRadioButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        lblGioiTinh1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlAccAdmin.setBackground(new java.awt.Color(254, 254, 254));
        pnlAccAdmin.setRoundBottomLeft(20);
        pnlAccAdmin.setRoundBottomRight(20);
        pnlAccAdmin.setRoundTopLeft(20);
        pnlAccAdmin.setRoundTopRight(20);
        pnlAccAdmin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlAccAdmin.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabTaiKhoanAdmin.setBackground(new java.awt.Color(255, 255, 255));
        tabTaiKhoanAdmin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabTaiKhoanAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTaiKhoanAdminMouseClicked(evt);
            }
        });

        pnlDanhSachTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlDanhSachTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên đăng nhập", "Mật khẩu", "Trạng thái", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblTaiKhoan.getTableHeader().setReorderingAllowed(false);
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTaiKhoan);

        pnlDanhSachTaiKhoan.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 300));

        panelRound4.setBackground(new java.awt.Color(232, 244, 234));
        panelRound4.setRoundBottomLeft(10);
        panelRound4.setRoundBottomRight(10);
        panelRound4.setRoundTopLeft(10);
        panelRound4.setRoundTopRight(10);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimTaiKhoan.setBackground(new java.awt.Color(232, 244, 234));
        txtTimTaiKhoan.setForeground(new java.awt.Color(51, 51, 51));
        txtTimTaiKhoan.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        txtTimTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimTaiKhoanKeyPressed(evt);
            }
        });
        panelRound4.add(txtTimTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 40));

        btnTimTaiKhoan.setBackground(new java.awt.Color(232, 244, 234));
        btnTimTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        btnTimTaiKhoan.setBoderColor(new java.awt.Color(232, 244, 234));
        btnTimTaiKhoan.setRadius(10);
        btnTimTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTaiKhoanActionPerformed(evt);
            }
        });
        panelRound4.add(btnTimTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

        pnlDanhSachTaiKhoan.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 360, 40));

        btnLast.setBackground(new java.awt.Color(145, 227, 168));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLast.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLast.setRadius(10);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlDanhSachTaiKhoan.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 380, 60, 40));

        btnNext.setBackground(new java.awt.Color(145, 227, 168));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNext.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNext.setRadius(10);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlDanhSachTaiKhoan.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 60, 40));

        btnPrev.setBackground(new java.awt.Color(145, 227, 168));
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrev.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrev.setRadius(10);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnlDanhSachTaiKhoan.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 380, 60, 40));

        btnFirst.setBackground(new java.awt.Color(145, 227, 168));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirst.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirst.setRadius(10);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlDanhSachTaiKhoan.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 60, 40));

        tabTaiKhoanAdmin.addTab("Danh sách tài khoản", pnlDanhSachTaiKhoan);

        pnlChiTietTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlChiTietTaiKhoan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGioiTinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblGioiTinh.setText("Vai trò");
        pnlChiTietTaiKhoan.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        rdoNguoiDungThuong.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNguoiDungThuong);
        rdoNguoiDungThuong.setSelected(true);
        rdoNguoiDungThuong.setText("Người dùng thường");
        pnlChiTietTaiKhoan.add(rdoNguoiDungThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, -1));

        rdoQuanTriVien.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoQuanTriVien);
        rdoQuanTriVien.setText("Quản trị viên");
        pnlChiTietTaiKhoan.add(rdoQuanTriVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        lblMatKhau.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMatKhau.setText("Mật khẩu");
        pnlChiTietTaiKhoan.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        btnLuuThongTin.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuThongTin.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuThongTin.setText("Lưu thông tin ");
        btnLuuThongTin.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuThongTin.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnLuuThongTin.setRadius(10);
        btnLuuThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThongTinActionPerformed(evt);
            }
        });
        pnlChiTietTaiKhoan.add(btnLuuThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 200, 40));

        btnXoaTaiKhoan.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaTaiKhoan.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaTaiKhoan.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaTaiKhoan.setRadius(10);
        btnXoaTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTaiKhoanActionPerformed(evt);
            }
        });
        pnlChiTietTaiKhoan.add(btnXoaTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 40, 40));

        txtTenDangNhap.setBackground(new java.awt.Color(222, 247, 227));
        txtTenDangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenDangNhapKeyPressed(evt);
            }
        });
        pnlChiTietTaiKhoan.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 370, 40));

        lblTenDangNhap.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenDangNhap.setText("Tên đăng nhập");
        pnlChiTietTaiKhoan.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtMatKhau.setBackground(new java.awt.Color(222, 247, 227));
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyPressed(evt);
            }
        });
        pnlChiTietTaiKhoan.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 370, 40));

        rdoDungHoatDong.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoDungHoatDong);
        rdoDungHoatDong.setSelected(true);
        rdoDungHoatDong.setText("Dừng hoạt động");
        pnlChiTietTaiKhoan.add(rdoDungHoatDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        rdoHoatDong.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setText("Hoạt động");
        pnlChiTietTaiKhoan.add(rdoHoatDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        lblGioiTinh1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblGioiTinh1.setText("Trạng thái");
        pnlChiTietTaiKhoan.add(lblGioiTinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        pnlChiTietTaiKhoan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 810, 10));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        pnlChiTietTaiKhoan.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 10, 70));

        tabTaiKhoanAdmin.addTab("Chi tiết tài khoản", pnlChiTietTaiKhoan);

        pnlAccAdmin.add(tabTaiKhoanAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

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

        pnlAccAdmin.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        getContentPane().add(pnlAccAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTaiKhoanActionPerformed
        fillTableTaiKhoan();
    }//GEN-LAST:event_btnTimTaiKhoanActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.last(index, tblTaiKhoan, ListTK);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.next(index, tblTaiKhoan, ListTK);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.previous(index, tblTaiKhoan, ListTK);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.first(index, tblTaiKhoan);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLuuThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongTinActionPerformed
        if (checkForm()) {
            UptateTaiKhoan();
        }
    }//GEN-LAST:event_btnLuuThongTinActionPerformed

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        pnlExit1.setBackground(new Color(255, 205, 205));
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        pnlExit1.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_pnlExit1MouseExited

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblTaiKhoan.getSelectedRow();
            setFormTaiKhoan(index);
            System.out.println(index + "trong doulick");
            tabTaiKhoanAdmin.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void tabTaiKhoanAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTaiKhoanAdminMouseClicked
        fillTableTaiKhoan();
    }//GEN-LAST:event_tabTaiKhoanAdminMouseClicked

    private void btnXoaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanActionPerformed
        DeleteTaiKhoan();
        clearFormTaiKhoan();
    }//GEN-LAST:event_btnXoaTaiKhoanActionPerformed

    private void txtTimTaiKhoanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTaiKhoanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTableTaiKhoan();
        }
    }//GEN-LAST:event_txtTimTaiKhoanKeyPressed

    private void txtTenDangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenDangNhapKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                UptateTaiKhoan();
            }
        }
    }//GEN-LAST:event_txtTenDangNhapKeyPressed

    private void txtMatKhauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (checkForm()) {
                UptateTaiKhoan();
            }
        }
    }//GEN-LAST:event_txtMatKhauKeyPressed

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
            java.util.logging.Logger.getLogger(AccoutAdmainDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccoutAdmainDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccoutAdmainDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccoutAdmainDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccoutAdmainDiaLog dialog = new AccoutAdmainDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnLuuThongTin;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnTimTaiKhoan;
    private com.ebooks.Compoment.MyButton btnXoaTaiKhoan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblGioiTinh1;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private com.ebooks.Compoment.PanelRound panelRound4;
    private com.ebooks.Compoment.PanelRound pnlAccAdmin;
    private com.ebooks.Compoment.PanelRound pnlChiTietTaiKhoan;
    private com.ebooks.Compoment.PanelRound pnlDanhSachTaiKhoan;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private javax.swing.JRadioButton rdoDungHoatDong;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoNguoiDungThuong;
    private javax.swing.JRadioButton rdoQuanTriVien;
    private javax.swing.JTabbedPane tabTaiKhoanAdmin;
    private com.ebooks.Compoment.Table tblTaiKhoan;
    private com.ebooks.Compoment.txtFieldPassBoder txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    private com.ebooks.Compoment.SearchText txtTimTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
