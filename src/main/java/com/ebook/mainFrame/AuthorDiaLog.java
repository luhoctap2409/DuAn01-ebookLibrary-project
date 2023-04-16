/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.TacGiaDAO;
import com.ebooks.dao.TacGia_SachDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class AuthorDiaLog extends javax.swing.JDialog {

    private boolean congTac = false;
    private TacGiaDAO DAOTG = new TacGiaDAO();
    private List<TacGia> ListTG = new ArrayList<TacGia>();
    private String nameImg = "avata_null.png";
    public AuthorDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        setBackground(new Color(0, 0, 0, 0));
        clearForm();
        statusButton(false);
    }

    private void fillTableAuthor() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblTacGia.getModel();
        tblTacGia.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            ListTG = DAOTG.selectByKeyword(txtTimTacGia.getText());
            for (TacGia tacGia : ListTG) {
                Object[] row = {tacGia.getMaTacGia(), tacGia.getHoTen(), ShareHelper.formats.format(tacGia.getNgaySinh()), tacGia.isGioiTinh(), tacGia.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private boolean checkForm() {
        if (!UtilityHelper.checkNullText(lblNgaySinh, txtNgaySinhTacGia)) {
            return false;
        } else if (!UtilityHelper.checkNullText(lblHoTenTacGia, txtHoTenTacGia)) {
            return false;
        }
        return true;
    }

    private void statusButton(boolean status) {
        btnXoaTG.setEnabled(status);
        btnCapNhat.setEnabled(status);
        btnThemMoi.setEnabled(!status);
        return;
    }

    private void setForm(int indexTable) {
        String maTacGia = tblTacGia.getValueAt(indexTable, 0).toString();
        TacGia tg = DAOTG.findById(maTacGia);
        txtMaTacGia.setText(tg.getMaTacGia());
        txtHoTenTacGia.setText(tg.getHoTen());
        txtNgaySinhTacGia.setText(String.valueOf(tg.getNgaySinh()));
        txtMoTaTacGia.setText(tg.getMoTa());
        rdoNam.setSelected(tg.isGioiTinh());
        rdoNu.setSelected(!tg.isGioiTinh());
        lblTacGiaImg.setIcon(ShowImg(tg.getHinh()));
    }

    private TacGia getForm() {
        TacGia tacGia = new TacGia();
        tacGia.setHoTen(txtHoTenTacGia.getText());
        tacGia.setGioiTinh(rdoNam.isSelected() ? true : false);
        tacGia.setNgaySinh(Calendar.getDate());
        tacGia.setHinh(nameImg);
        tacGia.setMoTa(txtMoTaTacGia.getText());
        tacGia.setTenDangNhap(ShareHelper.USER.getTenDangNhap());
        return tacGia;
    }

    private ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon("..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\imgAthor\\" + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    private void clearForm() {
        TacGia tg = new TacGia();
        tg.setHinh("avata_null.png");
        txtMaTacGia.setText(tg.getMaTacGia());
        txtHoTenTacGia.setText(tg.getHoTen());
        txtNgaySinhTacGia.setText(String.valueOf(ShareHelper.formats.format(new Date())));
        txtMoTaTacGia.setText(tg.getMoTa());
        rdoNam.setSelected(tg.isGioiTinh());
        rdoNu.setSelected(!tg.isGioiTinh());
        lblTacGiaImg.setIcon(ShowImg(tg.getHinh()));
    }

    private boolean CheckField() {
        if (!UtilityHelper.checkNullText(lblHoTenTacGia, txtHoTenTacGia)) {
            return false;
        } else if (!UtilityHelper.checkNullText(lblNgaySinh, txtNgaySinhTacGia)) {
            return false;
        }
        return true;
    }

    public String SetImg() {
        JFileChooser fileChooser = new JFileChooser();
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream in = new FileInputStream(fileChooser.getSelectedFile().getPath());
                FileOutputStream ou = new FileOutputStream("..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\imgAthor\\" + fileChooser.getSelectedFile().getName());
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
                lblTacGiaImg.setIcon(ShowImg(nameImg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileChooser.getSelectedFile().getName();
    }

    private void createTacGia() {
        if (CheckField()) {
            try {
                DAOTG.insert(getForm());
                DialogHelper.alert(this, "Thêm mới thành công");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Thêm mới thất bại");
            }
        }
    }

    private void updateTacGia() {
        if (CheckField()) {
            try {
                TacGia tg = getForm();
                tg.setMaTacGia(txtMaTacGia.getText());
                statusButton(false);
                clearForm();
                DAOTG.update(tg);
                DialogHelper.alert(this, "Luu thông tin thành công");
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Luu thông tin thất bại");
            }
        }
    }

    private void deleteTacGia() {
        if (CheckField()) {
            try {
                TacGia_SachDAO DAOTG_S = new TacGia_SachDAO();
                boolean result = DialogHelper.confirm(this, "Bạn muốn xóa ? những cuốn sách sẽ không còn tác giả này");
                if (result ) {
                    DAOTG_S.deleteTG(txtMaTacGia.getText());
                    DAOTG.delete(txtMaTacGia.getText());
                    statusButton(false);
                    clearForm();
                    DialogHelper.alert(this, "xóa thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "xóa thất bại");
            }
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
        panelRound1 = new com.ebooks.Compoment.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        tabTacGia = new javax.swing.JTabbedPane();
        panelRound2 = new com.ebooks.Compoment.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTacGia = new com.ebooks.Compoment.Table();
        panelRound4 = new com.ebooks.Compoment.PanelRound();
        txtTimTacGia = new com.ebooks.Compoment.SearchText();
        btnTimTacGia = new com.ebooks.Compoment.MyButton();
        btnLast = new com.ebooks.Compoment.MyButton();
        btnNext = new com.ebooks.Compoment.MyButton();
        btnPrev = new com.ebooks.Compoment.MyButton();
        btnFirst = new com.ebooks.Compoment.MyButton();
        panelRound3 = new com.ebooks.Compoment.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTacGiaImg = new com.ebooks.Compoment.ImageBoder();
        txtMaTacGia = new javax.swing.JTextField();
        lblMaTacGia = new javax.swing.JLabel();
        txtNgaySinhTacGia = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        btnIconCld1 = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTaTacGia = new javax.swing.JTextArea();
        lblMoTa1 = new javax.swing.JLabel();
        txtHoTenTacGia = new javax.swing.JTextField();
        lblHoTenTacGia = new javax.swing.JLabel();
        btnXoaTG = new com.ebooks.Compoment.MyButton();
        btnReset = new com.ebooks.Compoment.MyButton();
        btnCapNhat = new com.ebooks.Compoment.MyButton();
        btnThemMoi = new com.ebooks.Compoment.MyButton();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(254, 254, 254));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabTacGia.setBackground(new java.awt.Color(255, 255, 255));
        tabTacGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabTacGia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabTacGiaStateChanged(evt);
            }
        });

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã tác giả", "Họ tên", "Ngày sinh", "Giới tính", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTacGia.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblTacGia.getTableHeader().setReorderingAllowed(false);
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTacGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTacGia);

        panelRound2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 300));

        panelRound4.setBackground(new java.awt.Color(232, 244, 234));
        panelRound4.setRoundBottomLeft(10);
        panelRound4.setRoundBottomRight(10);
        panelRound4.setRoundTopLeft(10);
        panelRound4.setRoundTopRight(10);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimTacGia.setBackground(new java.awt.Color(232, 244, 234));
        txtTimTacGia.setForeground(new java.awt.Color(51, 51, 51));
        txtTimTacGia.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        txtTimTacGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimTacGiaKeyPressed(evt);
            }
        });
        panelRound4.add(txtTimTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 40));

        btnTimTacGia.setBackground(new java.awt.Color(232, 244, 234));
        btnTimTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        btnTimTacGia.setBoderColor(new java.awt.Color(232, 244, 234));
        btnTimTacGia.setRadius(10);
        btnTimTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTacGiaActionPerformed(evt);
            }
        });
        panelRound4.add(btnTimTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

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

        tabTacGia.addTab("Danh sách tác giả", panelRound2);

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTacGiaImg.setRadius(20);
        lblTacGiaImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTacGiaImgMouseClicked(evt);
            }
        });
        jPanel2.add(lblTacGiaImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 170, 170));

        txtMaTacGia.setBackground(new java.awt.Color(222, 247, 227));
        jPanel2.add(txtMaTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 250, 40));

        lblMaTacGia.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaTacGia.setText("Mã tác giả");
        jPanel2.add(lblMaTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        txtNgaySinhTacGia.setBackground(new java.awt.Color(222, 247, 227));
        jPanel2.add(txtNgaySinhTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 220, 40));

        lblNgaySinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày Sinh");
        jPanel2.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        btnIconCld1.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld1.setAutoscrolls(true);
        btnIconCld1.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIconCld1MouseClicked(evt);
            }
        });
        jPanel2.add(btnIconCld1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 50, 40));

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        jPanel2.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, -1));

        lblGioiTinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới Tính");
        jPanel2.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, -1));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        jPanel2.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, -1, -1));

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        jPanel2.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, -1));

        txtMoTaTacGia.setColumns(20);
        txtMoTaTacGia.setLineWrap(true);
        txtMoTaTacGia.setRows(5);
        txtMoTaTacGia.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtMoTaTacGia);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 800, 160));

        lblMoTa1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMoTa1.setText("Mô Tả");
        jPanel2.add(lblMoTa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 50, -1));

        txtHoTenTacGia.setBackground(new java.awt.Color(222, 247, 227));
        jPanel2.add(txtHoTenTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 250, 40));

        lblHoTenTacGia.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblHoTenTacGia.setText("Họ tên tác giả");
        jPanel2.add(lblHoTenTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        btnXoaTG.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaTG.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaTG.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaTG.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaTG.setRadius(10);
        btnXoaTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTGActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoaTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 460, 40, 40));

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
        jPanel2.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 40, 40));

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
        jPanel2.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, 120, 40));

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
        jPanel2.add(btnThemMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 120, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 530));

        jScrollPane3.setViewportView(jPanel1);

        panelRound3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 420));

        tabTacGia.addTab("Thông tin tác giả", panelRound3);

        panelRound1.add(tabTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 870, 460));

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

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panelRound1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTacGiaActionPerformed
        fillTableAuthor();
    }//GEN-LAST:event_btnTimTacGiaActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.last(index, tblTacGia, ListTG);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.next(index, tblTacGia, ListTG);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.previous(index, tblTacGia, ListTG);
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.first(index, tblTacGia);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void tabTacGiaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabTacGiaStateChanged
        fillTableAuthor();
    }//GEN-LAST:event_tabTacGiaStateChanged

    private void tblTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblTacGia.getSelectedRow();
            setForm(index);
            statusButton(true);
            tabTacGia.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblTacGiaMouseClicked

    private void lblTacGiaImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTacGiaImgMouseClicked
       if(evt.getClickCount() == 2){
           SetImg();
           fillTableAuthor();
       }
    }//GEN-LAST:event_lblTacGiaImgMouseClicked

    private void btnIconCld1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIconCld1MouseClicked
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCld1MouseClicked

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        txtNgaySinhTacGia.setText(String.valueOf(ShareHelper.formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void txtTimTacGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTacGiaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTableAuthor();
        }
    }//GEN-LAST:event_txtTimTacGiaKeyPressed

    private void btnXoaTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTGActionPerformed
        if (checkForm()) {
            deleteTacGia();
        }
    }//GEN-LAST:event_btnXoaTGActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        statusButton(false);
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (checkForm()) {
            updateTacGia();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        if (checkForm()) {
            createTacGia();
        }
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AuthorDiaLog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AuthorDiaLog dialog = new AuthorDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnIconCld1;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnReset;
    private com.ebooks.Compoment.MyButton btnThemMoi;
    private com.ebooks.Compoment.MyButton btnTimTacGia;
    private com.ebooks.Compoment.MyButton btnXoaTG;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTenTacGia;
    private javax.swing.JLabel lblMaTacGia;
    private javax.swing.JLabel lblMoTa1;
    private javax.swing.JLabel lblNgaySinh;
    private com.ebooks.Compoment.ImageBoder lblTacGiaImg;
    private com.ebooks.Compoment.PanelRound panelRound1;
    private com.ebooks.Compoment.PanelRound panelRound2;
    private com.ebooks.Compoment.PanelRound panelRound3;
    private com.ebooks.Compoment.PanelRound panelRound4;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabTacGia;
    private com.ebooks.Compoment.Table tblTacGia;
    private javax.swing.JTextField txtHoTenTacGia;
    private javax.swing.JTextField txtMaTacGia;
    private javax.swing.JTextArea txtMoTaTacGia;
    private javax.swing.JTextField txtNgaySinhTacGia;
    private com.ebooks.Compoment.SearchText txtTimTacGia;
    // End of variables declaration//GEN-END:variables
}
