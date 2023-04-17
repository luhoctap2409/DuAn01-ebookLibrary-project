/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.MuonSachChiTietDAO;
import com.ebooks.dao.NhaXuatBanDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.dao.SachYeuThichDAO;
import com.ebooks.dao.TacGia_SachDAO;
import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.dao.TheLoai_SachDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.NhaXuatBan;
import com.ebooks.model.Sach;
import com.ebooks.model.TacGia;
import com.ebooks.model.TheLoai;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class BookDiaLog extends javax.swing.JDialog {

    private boolean congTac = false;
    private List<Sach> ListS = new ArrayList<Sach>();
    private SachDAO DAOS = new SachDAO();
    private String nameImg = "stock-photo-74810889.jpg";

    public BookDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        Calendar.setVisible(congTac);
        congTac = !congTac;
        fillComBoBoxNhaXuatBan();
        statusButton(false);
    }

    //---------------------------------LẤY DỮ LIỆU VÀ HIỂN THỊ---------------------------------//
    private void fillTableBook() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblSach.getModel();
        tblSach.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            ListS = DAOS.selectByKeyword(txtTimSach.getText());
            for (Sach sach : ListS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), sach.getNamXuatBan(), sach.getMoTa()};
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private void fillComBoBoxNhaXuatBan() {
        NhaXuatBanDAO DAONXB = new NhaXuatBanDAO();
        List<NhaXuatBan> ListNXB = new ArrayList<NhaXuatBan>();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaXuatBan.getModel();
        model.removeAllElements();
        ListNXB = DAONXB.selectAll();
        for (NhaXuatBan nhaXuatBan : ListNXB) {
            model.addElement(nhaXuatBan.getMaNXB());
        }
    }

    //---------------------------------THAO TÁC FORM---------------------------------//
    private void seFormBook(int indexTable) {
        String maSach = tblSach.getValueAt(indexTable, 0).toString();
        Sach sach = DAOS.findById(maSach);
        if (sach != null) {
            txtMaSach.setText(sach.getMaSach());
            txtDuongDan.setText(sach.getDuongDan());
            txtMoTaSach.setText(sach.getMoTa());
            txtNgayDang.setText(ShareHelper.formats.format(sach.getNamXuatBan()));
            txtTenSach.setText(sach.getTenSach());
            lblSachImg.setIcon(ShowImg(sach.getBiaTruoc()));
        }

    }

    private Sach getFormBook() {
        Sach sach = new Sach();
        sach.setBiaTruoc(nameImg);
        sach.setDuongDan(txtDuongDan.getText());
        sach.setMaNXB(cboNhaXuatBan.getSelectedItem().toString());
        sach.setMoTa(txtMoTaSach.getText());
        sach.setNamXuatBan(Calendar.getDate());
        sach.setTenDangNhap(ShareHelper.USER.getTenDangNhap());
        sach.setTenSach(txtTenSach.getText());
        return sach;
    }

    private boolean checkForm() {
        if (!UtilityHelper.checkNullText(lblTenSach, txtTenSach)) {
            return false;
        } else if (!UtilityHelper.checkNullText(lblNgayDang, txtNgayDang)) {
            return false;
        } else if (!UtilityHelper.checkNullText(lblDuongDan, txtDuongDan)) {
            return false;
        } else if (lblSachImg.getIcon().equals(null)) {
            return false;
        }
        return true;
    }

    private void statusButton(boolean status) {
        btnXoaS.setEnabled(status);
        btnCapNhat.setEnabled(status);
        btnThemMoi.setEnabled(!status);
        return;
    }

    private void clearForm() {
        Sach sach = new Sach();
        sach.setBiaTruoc("stock-photo-74810889.jpg");
        txtMaSach.setText(sach.getMaSach());
        txtDuongDan.setText(sach.getDuongDan());
        txtMoTaSach.setText(sach.getMoTa());
        txtTenSach.setText(null);
        txtNgayDang.setText(String.valueOf(ShareHelper.formats.format(new Date())));
        lblSachImg.setIcon(ShowImg(sach.getBiaTruoc()));
    }

    private ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon("..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\imgEbooks\\" + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    private void SetImg() {
        JFileChooser fileChooser = new JFileChooser();
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION && !fileChooser.getSelectedFile().equals(null)) {
            try {
                FileInputStream in = new FileInputStream(fileChooser.getSelectedFile().getPath());
                FileOutputStream ou = new FileOutputStream("..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\imgEbooks\\" + fileChooser.getSelectedFile().getName());
                nameImg = fileChooser.getSelectedFile().getName();
                BufferedInputStream bin = new BufferedInputStream(in);
                BufferedOutputStream bou = new BufferedOutputStream(ou);
                int b = 0;
                while (b != -1) {
                    b = bin.read();
                    bou.write(b);
                }
                bin.close();
                bou.close();
                lblSachImg.setIcon(ShowImg(nameImg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }
    
    
    private void SetFilePDF() {
        JFileChooser fileChooser = new JFileChooser();
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION && !fileChooser.getSelectedFile().equals(null)) {
            try {
                FileInputStream in = new FileInputStream(fileChooser.getSelectedFile().getPath());
                FileOutputStream ou = new FileOutputStream("..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\pdf\\" + fileChooser.getSelectedFile().getName());
                BufferedInputStream bin = new BufferedInputStream(in);
                BufferedOutputStream bou = new BufferedOutputStream(ou);
                int b = 0;
                while (b != -1) {
                    b = bin.read();
                    bou.write(b);
                }
                bin.close();
                bou.close();
                txtDuongDan.setText(fileChooser.getSelectedFile().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ;
    }

//-------------------------THAO TAC DU LIEU---------------------------//    
    private void createSach() {
        try {
            DAOS.insert(getFormBook());
            DialogHelper.alert(this, "Đăng nhập thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Đăng nhập thất bại");
            e.printStackTrace();
        }
    }

    private void updateSach() {
        try {
            Sach sach = getFormBook();
            sach.setMaSach(txtMaSach.getText());
            DAOS.update(sach);
            clearForm();
            statusButton(false);
            DialogHelper.alert(this, "Lưu thông tin thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lưu thông tin thất bại");
            e.printStackTrace();
        }
    }

    private void deleteSach() {
        try {
            boolean result = DialogHelper.confirm(this, "Bạn muốn xóa ? Những thông tin liên cuốn sách này sẽ bị xóa!");
            if (result) {
                TacGia_SachDAO DAOTG_S = new TacGia_SachDAO();
                SachYeuThichDAO DAOSYT = new SachYeuThichDAO();
                TheLoai_SachDAO DAOTL_S = new TheLoai_SachDAO();
                MuonSachChiTietDAO DAOMSCT = new MuonSachChiTietDAO();
                DAOTG_S.deleteSach(txtMaSach.getText());
                DAOSYT.deleteSach(txtMaSach.getText());
                DAOTL_S.deleteSach(txtMaSach.getText());
                DAOMSCT.deleteSach(txtMaSach.getText());
                DAOS.delete(txtMaSach.getText());
                clearForm();
                statusButton(false);
                DialogHelper.alert(this, "xóa thành công");
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "xóa thất bại");
            e.printStackTrace();
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

        panelRound1 = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        tabBook = new javax.swing.JTabbedPane();
        panelRound2 = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSach = new com.ebooks.Compoment.Table();
        panelRound4 = new com.ebooks.Compoment.PanelRound();
        txtTimSach = new com.ebooks.Compoment.SearchText();
        btnTimSach = new com.ebooks.Compoment.MyButton();
        btnLast = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        panelRound3 = new com.ebooks.Compoment.PanelRound();
        tabThongTinSach = new javax.swing.JScrollPane();
        panelRound5 = new com.ebooks.Compoment.PanelRound();
        txtTenSach = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblSachImg = new com.ebooks.Compoment.ImageBoder();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTaSach = new javax.swing.JTextArea();
        lblMaSach = new javax.swing.JLabel();
        btnChonBiaSach = new com.ebooks.Compoment.MyButton();
        cboNhaXuatBan = new javax.swing.JComboBox<>();
        maNhaXuatBan = new javax.swing.JLabel();
        txtNgayDang = new javax.swing.JTextField();
        lblNgayDang = new javax.swing.JLabel();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        lblTenSach = new javax.swing.JLabel();
        lblDuongDan = new javax.swing.JLabel();
        txtDuongDan = new javax.swing.JTextField();
        btnChonFile = new com.ebooks.Compoment.MyButton();
        jPanel1 = new javax.swing.JPanel();
        btnReset = new com.ebooks.Compoment.MyButton();
        btnCapNhat = new com.ebooks.Compoment.MyButton();
        btnXoaS = new com.ebooks.Compoment.MyButton();
        btnThemMoi = new com.ebooks.Compoment.MyButton();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setBackground(new java.awt.Color(254, 254, 254));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabBook.setBackground(new java.awt.Color(255, 255, 255));
        tabBook.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabBookMouseClicked(evt);
            }
        });
        tabBook.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabBookPropertyChange(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Năm xuất bản", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblSach.getTableHeader().setReorderingAllowed(false);
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        tblSach.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblSachPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tblSach);

        panelRound2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 300));

        panelRound4.setBackground(new java.awt.Color(232, 244, 234));
        panelRound4.setRoundBottomLeft(10);
        panelRound4.setRoundBottomRight(10);
        panelRound4.setRoundTopLeft(10);
        panelRound4.setRoundTopRight(10);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimSach.setBackground(new java.awt.Color(232, 244, 234));
        txtTimSach.setForeground(new java.awt.Color(51, 51, 51));
        txtTimSach.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        txtTimSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimSachKeyPressed(evt);
            }
        });
        panelRound4.add(txtTimSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 40));

        btnTimSach.setBackground(new java.awt.Color(232, 244, 234));
        btnTimSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        btnTimSach.setBoderColor(new java.awt.Color(232, 244, 234));
        btnTimSach.setRadius(10);
        btnTimSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSachActionPerformed(evt);
            }
        });
        panelRound4.add(btnTimSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

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

        tabBook.addTab("Danh sách cuốn sách", panelRound2);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabThongTinSach.setBackground(new java.awt.Color(255, 255, 255));
        tabThongTinSach.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelRound5.setBackground(new java.awt.Color(254, 254, 254));
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenSach.setBackground(new java.awt.Color(222, 247, 227));
        panelRound5.add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 260, 40));

        txtMaSach.setEditable(false);
        txtMaSach.setBackground(new java.awt.Color(222, 247, 227));
        panelRound5.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 260, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        panelRound5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        lblSachImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/stock-photo-74810889.jpg"))); // NOI18N
        lblSachImg.setRadius(20);
        panelRound5.add(lblSachImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 170, 170));

        txtMoTaSach.setColumns(10);
        txtMoTaSach.setLineWrap(true);
        txtMoTaSach.setRows(5);
        txtMoTaSach.setWrapStyleWord(true);
        txtMoTaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMoTaSachKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtMoTaSach);

        panelRound5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 800, 120));

        lblMaSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaSach.setText("Mã Sách");
        panelRound5.add(lblMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        btnChonBiaSach.setBackground(new java.awt.Color(87, 190, 110));
        btnChonBiaSach.setForeground(new java.awt.Color(255, 255, 255));
        btnChonBiaSach.setText("Chọn file ảnh");
        btnChonBiaSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnChonBiaSach.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnChonBiaSach.setRadius(10);
        btnChonBiaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonBiaSachActionPerformed(evt);
            }
        });
        panelRound5.add(btnChonBiaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 170, 40));

        cboNhaXuatBan.setBackground(new java.awt.Color(222, 247, 227));
        cboNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboNhaXuatBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        panelRound5.add(cboNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 260, 40));

        maNhaXuatBan.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        maNhaXuatBan.setText("Nhà Xuẩt Bản");
        panelRound5.add(maNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        txtNgayDang.setBackground(new java.awt.Color(222, 247, 227));
        panelRound5.add(txtNgayDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 210, 40));

        lblNgayDang.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgayDang.setText("Ngày đăng");
        panelRound5.add(lblNgayDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, -1, -1));

        btnIconCld.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld.setAutoscrolls(true);
        btnIconCld.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCldActionPerformed(evt);
            }
        });
        panelRound5.add(btnIconCld, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, 50, 40));

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        panelRound5.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, -1, -1));

        lblTenSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenSach.setText("Tên Sách");
        panelRound5.add(lblTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        lblDuongDan.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblDuongDan.setText("File PDF");
        panelRound5.add(lblDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        txtDuongDan.setBackground(new java.awt.Color(222, 247, 227));
        panelRound5.add(txtDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 420, 40));

        btnChonFile.setBackground(new java.awt.Color(87, 190, 110));
        btnChonFile.setForeground(new java.awt.Color(255, 255, 255));
        btnChonFile.setText("Chọn File");
        btnChonFile.setBoderColor(new java.awt.Color(87, 190, 110));
        btnChonFile.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnChonFile.setRadius(10);
        btnChonFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonFileActionPerformed(evt);
            }
        });
        panelRound5.add(btnChonFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 110, 40));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        panelRound5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 530, -1, 20));

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
        panelRound5.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, 40, 40));

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
        panelRound5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 120, 40));

        btnXoaS.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaS.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaS.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaS.setRadius(10);
        btnXoaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSActionPerformed(evt);
            }
        });
        panelRound5.add(btnXoaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, 40, 40));

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
        panelRound5.add(btnThemMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 480, 120, 40));

        tabThongTinSach.setViewportView(panelRound5);

        panelRound3.add(tabThongTinSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 420));

        tabBook.addTab("Thông tin cuốn sách", panelRound3);

        panelRound1.add(tabBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

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

        panelRound1.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSachActionPerformed
        fillTableBook();
    }//GEN-LAST:event_btnTimSachActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.last(index, tblSach, ListS);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.next(index, tblSach, ListS);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.previous(index, tblSach, ListS);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.first(index, tblSach);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void txtMoTaSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoTaSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtMoTaSachKeyPressed

    private void btnChonBiaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonBiaSachActionPerformed
        SetImg();
        this.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_btnChonBiaSachActionPerformed

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayDang.setText(String.valueOf(formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblSach.getSelectedRow();
            txtMaSach.setEditable(false);
            statusButton(true);
            seFormBook(index);
            tabBook.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblSachMouseClicked

    private void btnChonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFileActionPerformed
        SetFilePDF();
    }//GEN-LAST:event_btnChonFileActionPerformed

    private void tabBookPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabBookPropertyChange


    }//GEN-LAST:event_tabBookPropertyChange

    private void tabBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabBookMouseClicked
        fillTableBook();
    }//GEN-LAST:event_tabBookMouseClicked

    private void txtTimSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTableBook();
        }
    }//GEN-LAST:event_txtTimSachKeyPressed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        statusButton(false);
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkForm()) {
            updateSach();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSActionPerformed
        if (checkForm()) {
            deleteSach();
        }
    }//GEN-LAST:event_btnXoaSActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (checkForm()) {
            createSach();
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        pnlExit1.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        pnlExit1.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_pnlExit1MouseExited

    private void tblSachPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblSachPropertyChange
        fillTableBook();
    }//GEN-LAST:event_tblSachPropertyChange

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
            java.util.logging.Logger.getLogger(BookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BookDiaLog dialog = new BookDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnCapNhat;
    private com.ebooks.Compoment.MyButton btnChonBiaSach;
    private com.ebooks.Compoment.MyButton btnChonFile;
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnReset;
    private com.ebooks.Compoment.MyButton btnThemMoi;
    private com.ebooks.Compoment.MyButton btnTimSach;
    private com.ebooks.Compoment.MyButton btnXoaS;
    private javax.swing.JComboBox<String> cboNhaXuatBan;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDuongDan;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblNgayDang;
    private com.ebooks.Compoment.ImageBoder lblSachImg;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JLabel maNhaXuatBan;
    private com.ebooks.Compoment.PanelRound panelRound1;
    private com.ebooks.Compoment.PanelRound panelRound2;
    private com.ebooks.Compoment.PanelRound panelRound3;
    private com.ebooks.Compoment.PanelRound panelRound4;
    private com.ebooks.Compoment.PanelRound panelRound5;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private javax.swing.JTabbedPane tabBook;
    private javax.swing.JScrollPane tabThongTinSach;
    private com.ebooks.Compoment.Table tblSach;
    private javax.swing.JTextField txtDuongDan;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextArea txtMoTaSach;
    private javax.swing.JTextField txtNgayDang;
    private javax.swing.JTextField txtTenSach;
    private com.ebooks.Compoment.SearchText txtTimSach;
    // End of variables declaration//GEN-END:variables
}
