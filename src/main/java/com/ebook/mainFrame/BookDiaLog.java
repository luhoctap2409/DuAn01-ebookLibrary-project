/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebook.mainFrame;

import com.ebooks.dao.NhaXuatBanDAO;
import com.ebooks.dao.SachDAO;
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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    boolean congTac = false;
    List<Sach> ListS = new ArrayList<Sach>();
    List<NhaXuatBan> ListNXB = new ArrayList<NhaXuatBan>();
    SachDAO DAOS = new SachDAO();
    NhaXuatBanDAO DAONXB = new NhaXuatBanDAO();
    private String UrlImg = "..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\imgEbooks\\";
    private String UrlEbook = "..\\DuAn01-ebookLibrary-project\\src\\main\\java\\com\\ebooks\\pdf\\";
    private String NameImg = "stock-photo-74810889.jpg";
    private JFileChooser fileChooser = new JFileChooser();

    public BookDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        Calendar.setVisible(congTac);
        congTac = !congTac;
        fillTableBook();
        fillComBoBoxNhaXuatBan();
    }

    public void fillTableBook() {
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

    public void fillComBoBoxNhaXuatBan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNhaXuatBan.getModel();
        model.removeAllElements();
        ListNXB = DAONXB.selectAll();
        for (NhaXuatBan nhaXuatBan : ListNXB) {
            model.addElement(nhaXuatBan.getMaNXB());
        }
    }

    public void seFormBook(int indexTable) {
        String maSach = tblSach.getValueAt(indexTable, 0).toString();
        Sach sach = DAOS.findById(maSach);
        if (sach != null) {
            txtMaSach.setText(sach.getMaSach());
            txtDuongDan.setText(sach.getDuongDan());
            txtMoTaSach.setText(sach.getMoTa());
            txtNgayDang.setText(sach.getNamXuatBan().toString());
            txtTenSach.setText(sach.getTenSach());
            lblSachImg.setIcon(ShowImg(sach.getBiaTruoc()));
        }

    }

    public void clearForm() {
        Sach sach = new Sach();
        sach.setBiaTruoc(NameImg);
        txtMaSach.setText(sach.getMaSach());
        txtDuongDan.setText(sach.getDuongDan());
        txtMoTaSach.setText(sach.getMoTa());
        txtTenSach.setText(null);
        lblSachImg.setIcon(ShowImg(sach.getBiaTruoc()));
    }

    Sach getFormBook() {
        Sach sach = new Sach();
        sach.setBiaTruoc(NameImg);
        sach.setDuongDan(txtDuongDan.getText());
        sach.setMaNXB(cboNhaXuatBan.getSelectedItem().toString());
        sach.setMoTa(txtMoTaSach.getText());
        sach.setNamXuatBan(Calendar.getDate());
        sach.setTenDangNhap(ShareHelper.USER.getTenDangNhap());
        sach.setTenSach(txtTenSach.getText());
        return sach;
    }

    public String MovingFile() {
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                File afile = new File(fileChooser.getSelectedFile().getPath());
                if (afile.renameTo(new File(UrlEbook + fileChooser.getSelectedFile().getName()))) {
                    System.out.println("File is moved successful!");
                    txtDuongDan.setText(fileChooser.getSelectedFile().getName());
                } else {
                    System.out.println("File is failed to move!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileChooser.getSelectedFile().getName();
    }

    public ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon(UrlImg + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    public String SetImg() {
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                File afile = new File(fileChooser.getSelectedFile().getPath());
                if (afile.renameTo(new File(UrlImg + fileChooser.getSelectedFile().getName()))) {
                    System.out.println("File is moved successful!");
                    NameImg = fileChooser.getSelectedFile().getName();
                    lblSachImg.setIcon(ShowImg(NameImg));
                    System.out.println(fileChooser.getSelectedFile().getName());
                } else {
                    System.out.println("File is failed to move!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
        return fileChooser.getSelectedFile().getName();
    }

    public void createSach() {
        try {
            DAOS.insert(getFormBook());
            DialogHelper.alert(this, "Đăng nhập thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Đăng nhập thất bại");
            e.printStackTrace();
        }
    }

    public void updateSach() {
        try {
            Sach sach = getFormBook();
            sach.setMaSach(txtMaSach.getText());
            DAOS.update(sach);
            DialogHelper.alert(this, "Lưu thông tin thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lưu thông tin thất bại");
            e.printStackTrace();
        }
    }

    public void deleteSach() {
        try {
            boolean result = DialogHelper.confirm(this, "Bạn muốn xóa ?");
            Sach sach = DAOS.findById(txtMaSach.getText().toString());
            if (result && sach != null) {
                DAOS.delete(sach.getMaSach());
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
        jLabel3 = new javax.swing.JLabel();
        txtNgayDang = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        lblTenSach1 = new javax.swing.JLabel();
        lblTenSach2 = new javax.swing.JLabel();
        btnXoaSach = new com.ebooks.Compoment.MyButton();
        btnClearForm = new com.ebooks.Compoment.MyButton();
        btnLuuThongTin = new com.ebooks.Compoment.MyButton();
        txtDuongDan = new javax.swing.JTextField();
        btnChonFile = new com.ebooks.Compoment.MyButton();
        jPanel1 = new javax.swing.JPanel();
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
                false, false, true, true
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
        txtTenSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenSachKeyPressed(evt);
            }
        });
        panelRound5.add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 260, 40));

        txtMaSach.setBackground(new java.awt.Color(222, 247, 227));
        txtMaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaSachKeyPressed(evt);
            }
        });
        panelRound5.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 260, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        panelRound5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

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
        panelRound5.add(lblSachImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, 170));

        txtMoTaSach.setColumns(10);
        txtMoTaSach.setRows(5);
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
        panelRound5.add(btnChonBiaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 170, 40));

        cboNhaXuatBan.setBackground(new java.awt.Color(222, 247, 227));
        cboNhaXuatBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboNhaXuatBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboNhaXuatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhaXuatBanActionPerformed(evt);
            }
        });
        panelRound5.add(cboNhaXuatBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 260, 40));

        jLabel3.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel3.setText("Mã Nhà Xuẩt Bản");
        panelRound5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        txtNgayDang.setBackground(new java.awt.Color(222, 247, 227));
        panelRound5.add(txtNgayDang, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 210, 40));

        lblNgaySinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày đăng");
        panelRound5.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, -1, -1));

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

        lblTenSach1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenSach1.setText("Tên Sách");
        panelRound5.add(lblTenSach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, -1));

        lblTenSach2.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenSach2.setText("File PDF");
        panelRound5.add(lblTenSach2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        btnXoaSach.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaSach.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaSach.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnXoaSach.setRadius(10);
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });
        panelRound5.add(btnXoaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, 40, 40));

        btnClearForm.setBackground(new java.awt.Color(87, 190, 110));
        btnClearForm.setForeground(new java.awt.Color(255, 255, 255));
        btnClearForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/add-document.png"))); // NOI18N
        btnClearForm.setBoderColor(new java.awt.Color(87, 190, 110));
        btnClearForm.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        btnClearForm.setRadius(10);
        btnClearForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearFormActionPerformed(evt);
            }
        });
        panelRound5.add(btnClearForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 40, 40));

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
        panelRound5.add(btnLuuThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 480, 200, 40));

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

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExit1MouseClicked

    private void lblExit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MousePressed
        // TODO add your handling code here:
          this.dispose();
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
          this.dispose();
    }//GEN-LAST:event_pnlExit1MousePressed

    private void txtTenSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtTenSachKeyPressed

    private void txtMaSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtMaSachKeyPressed

    private void lblSachImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseEntered
        lblSachImg.setBorder(new LineBorder(new Color(249, 249, 249)));
    }//GEN-LAST:event_lblSachImgMouseEntered

    private void lblSachImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseExited
        lblSachImg.setBorder(new LineBorder(new Color(0, 0, 0)));
    }//GEN-LAST:event_lblSachImgMouseExited

    private void lblSachImgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMousePressed
        if (evt.getClickCount() == 2) {
            try {

            } catch (Exception e) {
                //               DialogHelper.alert(this,"Lỗi Chọn Hình");
            }
        }
    }//GEN-LAST:event_lblSachImgMousePressed

    private void txtMoTaSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoTaSachKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_txtMoTaSachKeyPressed

    private void btnChonBiaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonBiaSachActionPerformed
        try {
            SetImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnChonBiaSachActionPerformed

    private void cboNhaXuatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhaXuatBanActionPerformed

    }//GEN-LAST:event_cboNhaXuatBanActionPerformed

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        deleteSach();
        btnXoaSach.setEnabled(false);
        clearForm();

    }//GEN-LAST:event_btnXoaSachActionPerformed

    private void btnClearFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearFormActionPerformed

    private void btnLuuThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongTinActionPerformed
        if (txtMaSach.getText().equals("")) {
            createSach();
        } else {
            updateSach();
        }
    }//GEN-LAST:event_btnLuuThongTinActionPerformed

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayDang.setText(String.valueOf(formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblSach.getSelectedRow();
            txtMaSach.setEditable(false);
            btnXoaSach.setEnabled(true);
            seFormBook(index);
            tabBook.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblSachMouseClicked

    private void btnChonFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFileActionPerformed
        try {
            MovingFile();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi Chuyển Dữ Liệu");
        }
    }//GEN-LAST:event_btnChonFileActionPerformed

    private void tabBookPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabBookPropertyChange


    }//GEN-LAST:event_tabBookPropertyChange

    private void tabBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabBookMouseClicked
        fillTableBook();
    }//GEN-LAST:event_tabBookMouseClicked

    private void txtTimSachKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSachKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
           fillTableBook();
        }
    }//GEN-LAST:event_txtTimSachKeyPressed

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
    private com.ebooks.Compoment.MyButton btnChonBiaSach;
    private com.ebooks.Compoment.MyButton btnChonFile;
    private com.ebooks.Compoment.MyButton btnClearForm;
    private com.ebooks.Compoment.MyButton btnFirst;
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnLast;
    private com.ebooks.Compoment.MyButton btnLuuThongTin;
    private com.ebooks.Compoment.MyButton btnNext;
    private com.ebooks.Compoment.MyButton btnPrev;
    private com.ebooks.Compoment.MyButton btnTimSach;
    private com.ebooks.Compoment.MyButton btnXoaSach;
    private javax.swing.JComboBox<String> cboNhaXuatBan;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblNgaySinh;
    private com.ebooks.Compoment.ImageBoder lblSachImg;
    private javax.swing.JLabel lblTenSach1;
    private javax.swing.JLabel lblTenSach2;
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
