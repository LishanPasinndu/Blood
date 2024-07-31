/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MSI
 */
public class home extends javax.swing.JFrame {

    String id;

    /**
     * Creates new form home
     */
    public home() {
        initComponents();

        adddonatershow();

        ImageIcon icon = new ImageIcon(getClass().getResource("/img/logoblood.png"));
        setIconImage(icon.getImage());

        addbtn.setBackground(new Color(243, 206, 169));
        updatebtn.setBackground(new Color(204, 102, 0));
        searchbtn.setBackground(new Color(204, 102, 0));
        delbtn.setBackground(new Color(204, 102, 0));

        addbtn.setForeground(Color.BLACK);
        updatebtn.setForeground(Color.WHITE);
        searchbtn.setForeground(Color.WHITE);
        delbtn.setForeground(Color.WHITE);

        adddonate.setVisible(true);
        searchdonaters.setVisible(false);
        updatedonaters.setVisible(false);
        deletedonaters.setVisible(false);

        name.setText("Add Donaters");

    }

    public void loaddonaters() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `donaters` WHERE `status_id`='1' ORDER BY `id` ASC ; ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("age"));
                v.add(rs.getString("adress"));
                v.add(rs.getString("email"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void adddonatershow() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `donaters` WHERE `status_id`='1' ORDER BY `id` ASC ; ");

            DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("age"));
                v.add(rs.getString("adress"));
                v.add(rs.getString("email"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loaddonatersprint() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `donaters` WHERE `status_id`='1' ORDER BY `id` ASC ; ");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("age"));
                v.add(rs.getString("adress"));
                v.add(rs.getString("email"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void searchdonaters() {

        try {

            String sfname = this.sfname.getText();
            String snic = this.snic.getText();
            String scontact = this.scontact.getText();
            String sdob = this.sdob.getText();
            String sage = this.sage.getText();
            String saddress = this.saddress.getText();

            Vector queryVector = new Vector();

            //Sno
            if (sfname.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add("`fname` LIKE '%" + sfname + "%'  ");
            }
            //Sno

            //name
            if (snic.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add(" `nic` LIKE '%" + snic + "%'  ");
            }
            //name

            //barcode
            if (scontact.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add(" `contact` LIKE '%" + scontact + "%' ");
            }
            //barcode

            //barcode
            if (sdob.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add(" `dob` LIKE '%" + sdob + "%' ");
            }
            //barcode

            //barcode
            if (sage.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add(" `age` LIKE '%" + sage + "%' ");
            }
            //barcode

            //barcode
            if (saddress.isEmpty()) {
                loaddonaters();
            } else {
                queryVector.add(" `adress` LIKE '%" + saddress + "%' ");
            }
            //barcode

            String wherequery = "WHERE";

            for (int i = 0; i < queryVector.size(); i++) {
                wherequery += " ";
                wherequery += queryVector.get(i);
                wherequery += " ";
                if (i != queryVector.size() - 1) {
                    wherequery += "AND";
                }
            }

            ResultSet rs = MySQL.search("SELECT * FROM `donaters` " + wherequery + " ; ");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("fname") + " " + rs.getString("lname"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("age"));
                v.add(rs.getString("adress"));
                v.add(rs.getString("email"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addbtn = new javax.swing.JButton();
        searchbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        delbtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        adddonate = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        nic = new javax.swing.JTextField();
        moble = new javax.swing.JTextField();
        dob = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        searchdonaters = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        sfname = new javax.swing.JTextField();
        sdob = new javax.swing.JTextField();
        snic = new javax.swing.JTextField();
        sage = new javax.swing.JTextField();
        scontact = new javax.swing.JTextField();
        saddress = new javax.swing.JTextField();
        updatedonaters = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ufname = new javax.swing.JTextField();
        ulname = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        unic = new javax.swing.JTextField();
        umobile = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        udob = new javax.swing.JTextField();
        uage = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        uaddress = new javax.swing.JTextField();
        uemail = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        deletedonaters = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ymba2.png"))); // NOI18N

        addbtn.setBackground(new java.awt.Color(204, 102, 0));
        addbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addbtn.setForeground(new java.awt.Color(255, 255, 255));
        addbtn.setText("Add Donaters");
        addbtn.setBorderPainted(false);
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        searchbtn.setBackground(new java.awt.Color(204, 102, 0));
        searchbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Search Donaters");
        searchbtn.setBorderPainted(false);
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });

        updatebtn.setBackground(new java.awt.Color(204, 102, 0));
        updatebtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        updatebtn.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn.setText("Update Donaters");
        updatebtn.setBorderPainted(false);
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        delbtn.setBackground(new java.awt.Color(204, 102, 0));
        delbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        delbtn.setForeground(new java.awt.Color(255, 255, 255));
        delbtn.setText("Print Report");
        delbtn.setBorderPainted(false);
        delbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(112, 112, 112)
                .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(delbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        name.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        name.setText("Add Donaters");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("2023/09/09");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Date |");

        jLabel6.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ශ්‍රී සුදර්මාරාමය - අම්බලන්ගොඩ ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        adddonate.setBackground(new java.awt.Color(248, 234, 214));
        adddonate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 102, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Register Donaters ");

        jLabel7.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 102, 0));
        jLabel7.setText("| ලියාපදිංචි කිරීම ");

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("First Name :-");

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("( පලමු නම )");

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Last Name :-");

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("( අවසන් නම )");

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("National ID :-");

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("( ජා.හැ අංකය )");

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Contact Number :-");

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("( දුරකතන අංකය )");

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Date of Birth :-");

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("( උපන්දිනය )");

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Age :-");

        jLabel19.setBackground(new java.awt.Color(102, 102, 102));
        jLabel19.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("( වයස )");

        jLabel20.setBackground(new java.awt.Color(102, 102, 102));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Address :-");

        jLabel21.setBackground(new java.awt.Color(102, 102, 102));
        jLabel21.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("( ලිපිනය )");

        jLabel22.setBackground(new java.awt.Color(102, 102, 102));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Email :-");

        jLabel23.setBackground(new java.awt.Color(102, 102, 102));
        jLabel23.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("( විද්යුත් තැපෑල )");

        fname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        lname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        nic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        moble.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moble.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mobleKeyTyped(evt);
            }
        });

        dob.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dobKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dobKeyTyped(evt);
            }
        });

        age.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        address.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        email.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Register");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "NIC", "Contact", "DOB", "Age", "Adress", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout adddonateLayout = new javax.swing.GroupLayout(adddonate);
        adddonate.setLayout(adddonateLayout);
        adddonateLayout.setHorizontalGroup(
            adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adddonateLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addGap(204, 204, 204))
            .addGroup(adddonateLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(adddonateLayout.createSequentialGroup()
                                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(82, 82, 82)))
                        .addGap(0, 0, 0)
                        .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(nic, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(adddonateLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(224, 224, 224))
                            .addGroup(adddonateLayout.createSequentialGroup()
                                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lname)
                            .addComponent(moble)
                            .addComponent(age)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(27, 27, 27))
        );
        adddonateLayout.setVerticalGroup(
            adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adddonateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nic, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(moble, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addGroup(adddonateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(adddonateLayout.createSequentialGroup()
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        adddonateLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {address, age, dob, email, fname, lname, moble, nic});

        searchdonaters.setBackground(new java.awt.Color(248, 234, 214));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 102, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Advanced Search of Donaters");

        jLabel25.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 102, 0));
        jLabel25.setText("| පරිත්‍යාග කරන්නන් සෙවීම ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "NIC", "Contact", "DOB", "Age", "Adress", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Fname :-");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("NIC :-");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Contact :-");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("DOB :-");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("Age :-");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Address :-");

        sfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sfnameKeyReleased(evt);
            }
        });

        sdob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sdobKeyReleased(evt);
            }
        });

        snic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                snicKeyReleased(evt);
            }
        });

        sage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sageKeyReleased(evt);
            }
        });

        scontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                scontactKeyReleased(evt);
            }
        });

        saddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                saddressKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout searchdonatersLayout = new javax.swing.GroupLayout(searchdonaters);
        searchdonaters.setLayout(searchdonatersLayout);
        searchdonatersLayout.setHorizontalGroup(
            searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchdonatersLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(164, 164, 164))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchdonatersLayout.createSequentialGroup()
                .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchdonatersLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(searchdonatersLayout.createSequentialGroup()
                                .addComponent(sdob, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                            .addGroup(searchdonatersLayout.createSequentialGroup()
                                .addComponent(sfname, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(snic)
                            .addComponent(sage, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saddress, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(scontact)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchdonatersLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1)))
                .addGap(22, 22, 22))
        );
        searchdonatersLayout.setVerticalGroup(
            searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchdonatersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scontact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(snic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(searchdonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        searchdonatersLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel26, jLabel27, jLabel28, scontact, sfname, snic});

        searchdonatersLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel29, jLabel30, jLabel31, saddress, sage, sdob});

        updatedonaters.setBackground(new java.awt.Color(248, 234, 214));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(204, 102, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Update Donaters Detailes");

        jLabel33.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 102, 0));
        jLabel33.setText("| තොරතුරු යාවත්කාලීන කිරීම  ");

        jLabel34.setBackground(new java.awt.Color(102, 102, 102));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("First Name :-");

        jLabel35.setBackground(new java.awt.Color(102, 102, 102));
        jLabel35.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("( පලමු නම )");

        ufname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        ulname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel36.setBackground(new java.awt.Color(102, 102, 102));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setText("Last Name :-");

        jLabel37.setBackground(new java.awt.Color(102, 102, 102));
        jLabel37.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("( අවසන් නම )");

        jLabel38.setBackground(new java.awt.Color(102, 102, 102));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setText("National ID :-");

        jLabel39.setBackground(new java.awt.Color(102, 102, 102));
        jLabel39.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("( ජා.හැ අංකය )");

        unic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        umobile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        umobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                umobileKeyTyped(evt);
            }
        });

        jLabel40.setBackground(new java.awt.Color(102, 102, 102));
        jLabel40.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("( දුරකතන අංකය )");

        jLabel41.setBackground(new java.awt.Color(102, 102, 102));
        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setText("Contact Number :-");

        jLabel42.setBackground(new java.awt.Color(102, 102, 102));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 102, 102));
        jLabel42.setText("Date of Birth :-");

        jLabel43.setBackground(new java.awt.Color(102, 102, 102));
        jLabel43.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 102, 102));
        jLabel43.setText("( උපන්දිනය )");

        udob.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        uage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel44.setBackground(new java.awt.Color(102, 102, 102));
        jLabel44.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setText("( වයස )");

        jLabel45.setBackground(new java.awt.Color(102, 102, 102));
        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setText("Age :-");

        jLabel46.setBackground(new java.awt.Color(102, 102, 102));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 102, 102));
        jLabel46.setText("Address :-");

        jLabel47.setBackground(new java.awt.Color(102, 102, 102));
        jLabel47.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("( ලිපිනය )");

        jLabel48.setBackground(new java.awt.Color(102, 102, 102));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("Email :-");

        jLabel49.setBackground(new java.awt.Color(102, 102, 102));
        jLabel49.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setText("( විද්යුත් තැපෑල )");

        uaddress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        uemail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        uemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uemailActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 102, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Select Donaters");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatedonatersLayout = new javax.swing.GroupLayout(updatedonaters);
        updatedonaters.setLayout(updatedonatersLayout);
        updatedonatersLayout.setHorizontalGroup(
            updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatedonatersLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(120, 120, 120))
            .addGroup(updatedonatersLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatedonatersLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54))
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(255, 255, 255))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatedonatersLayout.createSequentialGroup()
                        .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(3, 3, 3)
                        .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ulname, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                            .addComponent(unic, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ufname)
                            .addComponent(umobile))
                        .addGap(54, 54, 54))
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                            .addComponent(uaddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(uemail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(udob))
                        .addGap(52, 52, 52))))
        );
        updatedonatersLayout.setVerticalGroup(
            updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatedonatersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ufname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ulname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(unic, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(umobile, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(udob, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(uage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(updatedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(uemail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2))
                    .addGroup(updatedonatersLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        deletedonaters.setBackground(new java.awt.Color(248, 234, 214));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 102, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setText("Print all donaters report");

        jLabel51.setFont(new java.awt.Font("Iskoola Pota", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(204, 102, 0));
        jLabel51.setText("| සියලු ලේ දෙන්නන්ගේ වාර්තාව ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "NIC", "Contact", "DOB", "Age", "Adress", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton4.setBackground(new java.awt.Color(0, 153, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Print");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deletedonatersLayout = new javax.swing.GroupLayout(deletedonaters);
        deletedonaters.setLayout(deletedonatersLayout);
        deletedonatersLayout.setHorizontalGroup(
            deletedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deletedonatersLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deletedonatersLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(deletedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(22, 22, 22))
        );
        deletedonatersLayout.setVerticalGroup(
            deletedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deletedonatersLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(deletedonatersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adddonate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(259, 259, 259)
                    .addComponent(searchdonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(258, 258, 258)
                    .addComponent(updatedonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(258, 258, 258)
                    .addComponent(deletedonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adddonate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(searchdonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(updatedonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(deletedonaters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        // TODO add your handling code here:

        addbtn.setBackground(new Color(243, 206, 169));
        updatebtn.setBackground(new Color(204, 102, 0));
        searchbtn.setBackground(new Color(204, 102, 0));
        delbtn.setBackground(new Color(204, 102, 0));

        addbtn.setForeground(Color.BLACK);
        updatebtn.setForeground(Color.WHITE);
        searchbtn.setForeground(Color.WHITE);
        delbtn.setForeground(Color.WHITE);

        adddonate.setVisible(true);
        searchdonaters.setVisible(false);
        updatedonaters.setVisible(false);
        deletedonaters.setVisible(false);

        name.setText("Add Donaters");

    }//GEN-LAST:event_addbtnActionPerformed

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        // TODO add your handling code here:

        searchbtn.setBackground(new Color(243, 206, 169));
        updatebtn.setBackground(new Color(204, 102, 0));
        addbtn.setBackground(new Color(204, 102, 0));
        delbtn.setBackground(new Color(204, 102, 0));

        searchbtn.setForeground(Color.BLACK);
        updatebtn.setForeground(Color.WHITE);
        addbtn.setForeground(Color.WHITE);
        delbtn.setForeground(Color.WHITE);

        searchdonaters.setVisible(true);
        adddonate.setVisible(false);
        updatedonaters.setVisible(false);
        deletedonaters.setVisible(false);

        name.setText("Search Donaters");

        loaddonaters();

    }//GEN-LAST:event_searchbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        // TODO add your handling code here:

        updatebtn.setBackground(new Color(243, 206, 169));
        addbtn.setBackground(new Color(204, 102, 0));
        searchbtn.setBackground(new Color(204, 102, 0));
        delbtn.setBackground(new Color(204, 102, 0));

        updatebtn.setForeground(Color.BLACK);
        addbtn.setForeground(Color.WHITE);
        searchbtn.setForeground(Color.WHITE);
        delbtn.setForeground(Color.WHITE);

        updatedonaters.setVisible(true);
        searchdonaters.setVisible(false);
        adddonate.setVisible(false);
        deletedonaters.setVisible(false);

        name.setText("Update Donaters");

    }//GEN-LAST:event_updatebtnActionPerformed

    private void delbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delbtnActionPerformed
        // TODO add your handling code here:

        delbtn.setBackground(new Color(243, 206, 169));
        updatebtn.setBackground(new Color(204, 102, 0));
        searchbtn.setBackground(new Color(204, 102, 0));
        addbtn.setBackground(new Color(204, 102, 0));

        delbtn.setForeground(Color.BLACK);
        updatebtn.setForeground(Color.WHITE);
        searchbtn.setForeground(Color.WHITE);
        addbtn.setForeground(Color.WHITE);

        deletedonaters.setVisible(true);
        searchdonaters.setVisible(false);
        updatedonaters.setVisible(false);
        adddonate.setVisible(false);

        name.setText("Print All Donaters Report");

        loaddonatersprint();

    }//GEN-LAST:event_delbtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String fnamee = fname.getText();
        String lnamee = lname.getText();
        String nicc = nic.getText();
        String contact = moble.getText();
        String dobb = dob.getText();
        String agee = age.getText();
        String addresss = address.getText();
        String emaill = email.getText();

        if (fnamee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet rs = MySQL.search("SELECT * FROM `donaters` WHERE `fname`='" + fnamee + "' AND `lname`='" + lnamee + "' AND `nic`='" + nicc + "' AND `dob`='" + dobb + "' ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This Donaters Already Registerd", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.iud("INSERT INTO `donaters`(`fname`,`lname`,`nic`,`contact`,`dob`,`Age`,`adress`,`email`,`status_id`) "
                            + "VALUES('" + fnamee + "','" + lnamee + "','" + nicc + "','" + contact + "','" + dobb + "','" + agee + "','" + addresss + "','" + emaill + "','1') ");

                    JOptionPane.showMessageDialog(this, "Registerd Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                    adddonatershow();

                    fname.setText("");
                    lname.setText("");
                    nic.setText("");
                    moble.setText("");
                    dob.setText("");
                    age.setText("");
                    address.setText("");
                    email.setText("");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void sfnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sfnameKeyReleased
        // TODO add your handling code here:

        searchdonaters();
    }//GEN-LAST:event_sfnameKeyReleased

    private void sdobKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sdobKeyReleased
        // TODO add your handling code here:
        searchdonaters();
    }//GEN-LAST:event_sdobKeyReleased

    private void snicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_snicKeyReleased
        // TODO add your handling code here:
        searchdonaters();
    }//GEN-LAST:event_snicKeyReleased

    private void scontactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scontactKeyReleased
        // TODO add your handling code here:
        searchdonaters();
    }//GEN-LAST:event_scontactKeyReleased

    private void sageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sageKeyReleased
        // TODO add your handling code here:
        searchdonaters();
    }//GEN-LAST:event_sageKeyReleased

    private void saddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saddressKeyReleased
        // TODO add your handling code here:
        searchdonaters();
    }//GEN-LAST:event_saddressKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        viewdonaters vd = new viewdonaters(this);
        vd.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String fname = ufname.getText();
        String lname = ulname.getText();
        String nic = unic.getText();
        String contact = umobile.getText();
        String dob = udob.getText();
        String age = uage.getText();
        String address = uaddress.getText();
        String email = uemail.getText();

        if (ufname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a donaters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            MySQL.iud("UPDATE `donaters` SET `fname`='" + fname + "',`lname`='" + lname + "',`nic`='" + nic + "',`contact`='" + contact + "',`dob`='" + dob + "',`age`='" + age + "',`adress`='" + address + "',`email`='" + email + "' WHERE `id`='" + id + "' ");

            JOptionPane.showMessageDialog(this, "Update Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

            ufname.setText("");
            ulname.setText("");
            unic.setText("");
            umobile.setText("");
            udob.setText("");
            uage.setText("");
            uaddress.setText("");
            uemail.setText("");

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void uemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uemailActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        try {

            // InputStream filepath = getClass().getResourceAsStream("../reports/student.jrxml");
            //getClass().getResourceAsStream("/reports/student.jrxml")
            JasperReport jr = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/bloodd.jrxml"));

            HashMap<String, Object> data = new HashMap<String, Object>();

            Connection dataSource = MySQL.getConnection();
            JasperPrint jp = JasperFillManager.fillReport(jr, data, dataSource);

            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void umobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umobileKeyTyped
        // TODO add your handling code here:

        String mobile = umobile.getText();
        String text = mobile + evt.getKeyChar();

        if (text.length() == 1) {
            if (!text.equals("0")) {
                evt.consume();
            }
        } else if (text.length() == 2) {
            if (!text.equals("07")) {
                evt.consume();
            }
        } else if (text.length() == 3) {
            if (!Pattern.compile("07[01245678]").matcher(text).matches()) {
                evt.consume();
            }
        } else if (text.length() <= 10) {
            if (!Pattern.compile("07[01245678][0-9]+").matcher(text).matches()) {
                evt.consume();
            }

        } else {
            evt.consume();
        }

    }//GEN-LAST:event_umobileKeyTyped

    private void mobleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobleKeyTyped
        // TODO add your handling code here:

        String mobile = moble.getText();
        String text = mobile + evt.getKeyChar();

        if (text.length() == 1) {
            if (!text.equals("0")) {
                evt.consume();
            }
        } else if (text.length() == 2) {
            if (!text.equals("07")) {
                evt.consume();
            }
        } else if (text.length() == 3) {
            if (!Pattern.compile("07[01245678]").matcher(text).matches()) {
                evt.consume();
            }
        } else if (text.length() <= 10) {
            if (!Pattern.compile("07[01245678][0-9]+").matcher(text).matches()) {
                evt.consume();
            }

        } else {
            evt.consume();
        }

    }//GEN-LAST:event_mobleKeyTyped

    private void dobKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobKeyReleased
        // TODO add your handling code here:

        int s = 2023;

        String date = dob.getText();
        String year = " ";

        if (date.length() > 4) {

            year = date.substring(0, 4);

        } else if (date.isEmpty()) {

            age.setText("  ");

        }

        int i = Integer.parseInt(year);
        int a = s - i;

        String newage = String.valueOf(a);

        age.setText(newage);

    }//GEN-LAST:event_dobKeyReleased

    private void dobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dobKeyTyped

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JPanel adddonate;
    private javax.swing.JTextField address;
    private javax.swing.JTextField age;
    private javax.swing.JButton delbtn;
    private javax.swing.JPanel deletedonaters;
    private javax.swing.JTextField dob;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField moble;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nic;
    private javax.swing.JTextField saddress;
    private javax.swing.JTextField sage;
    private javax.swing.JTextField scontact;
    private javax.swing.JTextField sdob;
    private javax.swing.JButton searchbtn;
    private javax.swing.JPanel searchdonaters;
    private javax.swing.JTextField sfname;
    private javax.swing.JTextField snic;
    public javax.swing.JTextField uaddress;
    public javax.swing.JTextField uage;
    public javax.swing.JTextField udob;
    public javax.swing.JTextField uemail;
    public javax.swing.JTextField ufname;
    public javax.swing.JTextField ulname;
    public javax.swing.JTextField umobile;
    public javax.swing.JTextField unic;
    private javax.swing.JButton updatebtn;
    private javax.swing.JPanel updatedonaters;
    // End of variables declaration//GEN-END:variables
}
