package frames;

import java.text.SimpleDateFormat;
import java.util.Date;
import controllers.BillboardJpaController;
import controllers.MovieJpaController;
import controllers.RoomJpaController;
import controllers.ScheduleJpaController;
import controllers.UserJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.Billboard;
import entities.Movie;
import entities.Room;
import entities.Schedule;
import entities.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Administrator Frame/Panel for CRUD operations over 
** Rooms, Schedules, Users and Movies
*/
public class Administrator extends javax.swing.JFrame {
    private Movie m;
    private MovieJpaController mc;
    private User u;
    private UserJpaController uc;
    private Room r;
    private RoomJpaController rc;
    private Schedule s;
    private ScheduleJpaController sc;
    private Billboard b;
    private BillboardJpaController bc;

    //Administrator Constructor    
    public Administrator() {
        initComponents();
        this.setLocationRelativeTo(null);
        m = new Movie();
        mc = new MovieJpaController();
        u = new User();
        uc = new UserJpaController();
        r = new Room();
        rc = new RoomJpaController();
        s = new Schedule();
        sc = new ScheduleJpaController();
        b = new Billboard();
        bc = new BillboardJpaController();
        loadMovieTable();
        loadUserTable();
        loadRoomTable();
        loadScheduleTable();
        loadBillboardTable();
        tblMovie.setShowGrid(true); //Shows grid in table
        tblUser.setShowGrid(true);
        tblRoom.setShowGrid(true);
        tblScheule.setShowGrid(true);
        tblBillboard.setShowGrid(true);
    }
    
    //Set Movie values as default
    public void clearMovieInfo(){
        txtSearchMovie.setText("");
        txtMovieName.setText("");
        txtDirector.setText("");
        txtId.setText("");
        txtProducer.setText("");
        txtDuration.setText("");
        cmbClassification.setSelectedIndex(0);
        cmbGenre.setSelectedIndex(0);
        btnDeleteMovie.setEnabled(false);
        btnUpdateMovie.setEnabled(false);
        btnAddMovie.setEnabled(true);
        txtMovieName.requestFocus();
        txtSearchMovie.setText("");
    }
    
    //Loads movie table from DB
    public void loadMovieTable(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("id");
        dtm.addColumn("Name");
        dtm.addColumn("Director");
        dtm.addColumn("Duration");
        dtm.addColumn("Genre");
        dtm.addColumn("Classification");
        dtm.addColumn("Producer");
        dtm.addColumn("Status");
        
        List<Movie> movieList = new ArrayList<>();
        MovieJpaController jm = new MovieJpaController();
        movieList = jm.findMovieEntities();
        
        for(Movie m : movieList){
            Object row[] = new Object[7];
            row[0] = m.getMovieId();
            row[1] = m.getMovieName();
            row[2] = m.getMovieDirector();
            row[3] = m.getMovieDuration();
            row[4] = m.getMovieGenre();
            row[5] = m.getMovieClassification();
            row[6] = m.getMovieProducer();
            row[7] = m.getMovieStatus();
            dtm.addRow(row);
        }
        tblMovie.setModel(dtm);
    }
    
    //Loads user table from db
    public void loadUserTable(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("id");
        dtm.addColumn("Name");
        dtm.addColumn("Password");
        dtm.addColumn("Type");
        dtm.addColumn("Status");
        
        List<User> userList = new ArrayList<>();
        UserJpaController ujc = new UserJpaController();
        userList = ujc.findUserEntities();
        
        for(User u : userList){
            Object row[] = new Object[5];
            row[0] = u.getUserId();
            row[1] = u.getUserName();
            row[2] = u.getUserPassword();
            row[3] = u.getUserType();
            row[4] = u.getUserStatus();
            dtm.addRow(row);
        }
        tblUser.setModel(dtm);
    }
    
    //Loads room table from db
    public void loadRoomTable(){//Pending to fix
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("id");
        dtm.addColumn("Number");
        dtm.addColumn("Status");
        
        List<Room> roomList = new ArrayList<>();
        RoomJpaController rjc = new RoomJpaController();
        roomList = rjc.findRoomEntities();
        
        for(Room r : roomList){
            Object row[] = new Object[3];
            row[0] = r.getRoomId();
            row[1] = r.getRoomNumber();
            row[2] = r.getRoomStatus();
            dtm.addRow(row);
        }
        tblRoom.setModel(dtm);
    }
    
    //Loads schedule table from db
    public void loadScheduleTable(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("id");
        dtm.addColumn("Start");
        dtm.addColumn("End");
        dtm.addColumn("Status");
        
        List<Schedule> scheduleList = new ArrayList<>();
        ScheduleJpaController sjc = new ScheduleJpaController();
        scheduleList = sjc.findScheduleEntities();
        
        for(Schedule s : scheduleList){
            Object row[] = new Object[4];
            row[0] = s.getScheduleId();
            row[1] = s.getScheduleStart();
            row[2] = s.getScheduleEnd();
            row[3] = s.getScheduleStatus();
            dtm.addRow(row);
        }
        tblScheule.setModel(dtm);
    }
    
    //Loads billboard table from DB filtering State option from user
    public void loadBillboardTable(){
        DefaultTableModel dtm = new DefaultTableModel();
        List<Billboard> billboardList = bc.findBillboardEntities();
        dtm.addColumn("Id");
        dtm.addColumn("Movie Id");
        dtm.addColumn("Room Id");
        dtm.addColumn("Schedule Id");
        dtm.addColumn("State Id");
        for(Billboard b : billboardList){
            Object row[] = new Object[5];
            row[0] = b.getBillboardId();
            row[1] = b.getMovieId().getMovieId();
            row[2] = b.getRoomId().getRoomId();
            row[3] = b.getScheduleId().getScheduleId();
            row[4] = b.getStateId().getStateId();
            dtm.addRow(row);
        }
        tblBillboard.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        txtClassification = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnAddMovie = new javax.swing.JButton();
        btnUpdateMovie = new javax.swing.JButton();
        btnDeleteMovie = new javax.swing.JButton();
        txtSearchMovie = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnClearMovie = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMovieName = new javax.swing.JTextField();
        txtDirector = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtProducer = new javax.swing.JTextField();
        txtDuration = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbClassification = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cmbGenre = new javax.swing.JComboBox<>();
        lblDurationValidation = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMovie = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        btnUpdateUser = new javax.swing.JButton();
        btnDeleteUser = new javax.swing.JButton();
        txtSearchUser = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnClearUser = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        txtIdUser = new javax.swing.JTextField();
        txtNameUser = new javax.swing.JTextField();
        txtPassUser = new javax.swing.JTextField();
        cmbUserType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbStatusUser = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        btnAddRoom = new javax.swing.JButton();
        btnUpdateRoom = new javax.swing.JButton();
        btnDeleteRoom = new javax.swing.JButton();
        txtRoomSearch = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnClearRoom = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtRoomId = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRoomNumber = new javax.swing.JTextField();
        cmbRoomStatus = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRoom = new javax.swing.JTable();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtScheduleId = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtScheduleStart = new javax.swing.JTextField();
        txtScheduleEnd = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtScheduleDay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cmbScheduleStatus = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnAddSchedule = new javax.swing.JButton();
        btnDeleteSchedule = new javax.swing.JButton();
        txtScheduleSearch = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnClearSchedule = new javax.swing.JButton();
        btnUpdateSchedule = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblScheule = new javax.swing.JTable();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        btnAddBillboard = new javax.swing.JButton();
        btnDeleteBillboard = new javax.swing.JButton();
        txtBillboardSearch = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnClearBillboard = new javax.swing.JButton();
        btnUpdateBillboard = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        txtBillboardId = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cmbBillboardStatus = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtBillboardMovie = new javax.swing.JTextField();
        txtBillboardRoomId = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtBillboardScheduleId = new javax.swing.JTextField();
        txtBillboardStateId = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBillboard = new javax.swing.JTable();
        btnBackMovie2 = new javax.swing.JButton();
        btnExitMovie1 = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddMovie.setText("Add");
        btnAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMovieActionPerformed(evt);
            }
        });

        btnUpdateMovie.setText("Update");
        btnUpdateMovie.setEnabled(false);
        btnUpdateMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMovieActionPerformed(evt);
            }
        });

        btnDeleteMovie.setText("Delete");
        btnDeleteMovie.setEnabled(false);
        btnDeleteMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMovieActionPerformed(evt);
            }
        });

        jLabel19.setText("Search");

        btnClearMovie.setText("Clear");
        btnClearMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearMovieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAddMovie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdateMovie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDeleteMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClearMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddMovie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateMovie)
                .addGap(1, 1, 1)
                .addComponent(btnDeleteMovie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtSearchMovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnClearMovie)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        txtId.setEnabled(false);

        jLabel5.setText("Id");

        jLabel6.setText("Name");

        jLabel7.setText("Director");

        txtProducer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProducerActionPerformed(evt);
            }
        });

        txtDuration.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDurationKeyPressed(evt);
            }
        });

        jLabel8.setText("Producer");

        jLabel10.setText("Duration");

        cmbClassification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "B15", "C", "D" }));

        jLabel9.setText("Classification");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel20.setText("Status");

        jLabel25.setText("Genre");

        cmbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Action", "Adventure", "Comedy", "Sci-Fy", "Terror" }));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProducer, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblDurationValidation))
                    .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMovieName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProducer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDurationValidation))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout txtClassificationLayout = new javax.swing.GroupLayout(txtClassification);
        txtClassification.setLayout(txtClassificationLayout);
        txtClassificationLayout.setHorizontalGroup(
            txtClassificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtClassificationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        txtClassificationLayout.setVerticalGroup(
            txtClassificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtClassificationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(txtClassificationLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblMovie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblMovie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMovieMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMovie);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jDesktopPane2.setLayer(txtClassification, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtClassification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addComponent(txtClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Movies", jDesktopPane2);

        btnAddUser.setText("Add");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnUpdateUser.setText("Update");
        btnUpdateUser.setEnabled(false);
        btnUpdateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUserActionPerformed(evt);
            }
        });

        btnDeleteUser.setText("Delete");
        btnDeleteUser.setEnabled(false);
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteUserActionPerformed(evt);
            }
        });

        jLabel18.setText("Search");

        btnClearUser.setText("Clear");
        btnClearUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnClearUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchUser)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeleteUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(btnAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnUpdateUser, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(btnAddUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearUser)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txtIdUser.setEnabled(false);

        cmbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Administrator" }));

        jLabel1.setText("Id");

        jLabel2.setText("Name");

        jLabel3.setText("Password");

        jLabel4.setText("Type");

        cmbStatusUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel21.setText("Status");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbUserType, 0, 94, Short.MAX_VALUE)
                            .addComponent(txtIdUser)
                            .addComponent(txtPassUser)
                            .addComponent(txtNameUser)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbStatusUser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNameUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStatusUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)))
        );

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("Users", jDesktopPane1);

        btnAddRoom.setText("Add");
        btnAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRoomActionPerformed(evt);
            }
        });

        btnUpdateRoom.setText("Update");
        btnUpdateRoom.setEnabled(false);
        btnUpdateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRoomActionPerformed(evt);
            }
        });

        btnDeleteRoom.setText("Delete");
        btnDeleteRoom.setEnabled(false);
        btnDeleteRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRoomActionPerformed(evt);
            }
        });

        txtRoomSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoomSearchActionPerformed(evt);
            }
        });

        jLabel17.setText("Search");

        btnClearRoom.setText("Clear");
        btnClearRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRoomSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnClearRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnAddRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRoomSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(170, 124));

        jLabel11.setText("Id");

        txtRoomId.setEnabled(false);

        jLabel12.setText("Number");

        cmbRoomStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel24.setText("Status");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(21, 21, 21)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbRoomStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(cmbRoomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        tblRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tblRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRoomMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblRoom);

        jDesktopPane3.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane3.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane3Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tab.addTab("Rooms", jDesktopPane3);

        txtScheduleId.setEnabled(false);

        jLabel13.setText("Id");

        txtScheduleStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScheduleStartActionPerformed(evt);
            }
        });

        jLabel14.setText("Start");

        jLabel22.setText("End");

        txtScheduleDay.setText(" ");
        txtScheduleDay.setEnabled(false);

        jLabel15.setText("Day");
        jLabel15.setEnabled(false);

        cmbScheduleStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel23.setText("Status");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtScheduleId, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtScheduleEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtScheduleStart, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbScheduleStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtScheduleDay, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScheduleId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScheduleStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(txtScheduleEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtScheduleDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbScheduleStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddSchedule.setText("Add");
        btnAddSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddScheduleActionPerformed(evt);
            }
        });

        btnDeleteSchedule.setText("Delete");
        btnDeleteSchedule.setEnabled(false);
        btnDeleteSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteScheduleActionPerformed(evt);
            }
        });

        txtScheduleSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtScheduleSearchKeyTyped(evt);
            }
        });

        jLabel16.setText("Search");

        btnClearSchedule.setText("Clear");
        btnClearSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearScheduleActionPerformed(evt);
            }
        });

        btnUpdateSchedule.setText("Update");
        btnUpdateSchedule.setEnabled(false);
        btnUpdateSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeleteSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClearSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txtScheduleSearch))))
                .addGap(32, 32, 32))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddSchedule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateSchedule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteSchedule)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtScheduleSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearSchedule)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tblScheule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblScheule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblScheuleMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblScheule);

        jDesktopPane4.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane4.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane4Layout = new javax.swing.GroupLayout(jDesktopPane4);
        jDesktopPane4.setLayout(jDesktopPane4Layout);
        jDesktopPane4Layout.setHorizontalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane4Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jDesktopPane4Layout.setVerticalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane4Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tab.addTab("Schedules", jDesktopPane4);

        btnAddBillboard.setText("Add");
        btnAddBillboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBillboardActionPerformed(evt);
            }
        });

        btnDeleteBillboard.setText("Delete");
        btnDeleteBillboard.setEnabled(false);
        btnDeleteBillboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBillboardActionPerformed(evt);
            }
        });

        txtBillboardSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBillboardSearchKeyPressed(evt);
            }
        });

        jLabel26.setText("Search");

        btnClearBillboard.setText("Clear");
        btnClearBillboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearBillboardActionPerformed(evt);
            }
        });

        btnUpdateBillboard.setText("Update");
        btnUpdateBillboard.setEnabled(false);
        btnUpdateBillboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBillboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeleteBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClearBillboard, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(txtBillboardSearch))))
                .addGap(32, 32, 32))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddBillboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdateBillboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteBillboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBillboardSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearBillboard)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txtBillboardId.setEnabled(false);

        jLabel27.setText("Id");

        cmbBillboardStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));

        jLabel31.setText("Status");

        jLabel28.setText("Movie Id");

        jLabel29.setText("Room Id");

        txtBillboardMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBillboardMovieActionPerformed(evt);
            }
        });
        txtBillboardMovie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBillboardMovieKeyPressed(evt);
            }
        });

        txtBillboardRoomId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBillboardRoomIdKeyPressed(evt);
            }
        });

        jLabel30.setText("Schedule Id");

        jLabel32.setText("State Id");

        jLabel33.setText(" ");

        jLabel34.setText(" ");

        jLabel35.setText(" ");

        jLabel36.setText(" ");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel28))
                .addGap(25, 25, 25)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbBillboardStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtBillboardStateId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBillboardScheduleId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBillboardMovie, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBillboardRoomId, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBillboardId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBillboardId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBillboardMovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtBillboardRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtBillboardScheduleId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(5, 5, 5)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtBillboardStateId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBillboardStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        tblBillboard.setModel(new javax.swing.table.DefaultTableModel());
        tblBillboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillboardMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblBillboard);

        jDesktopPane5.setLayer(jPanel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane5.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane5Layout = new javax.swing.GroupLayout(jDesktopPane5);
        jDesktopPane5.setLayout(jDesktopPane5Layout);
        jDesktopPane5Layout.setHorizontalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6)
        );
        jDesktopPane5Layout.setVerticalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane5Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tab.addTab("Billboard", jDesktopPane5);

        btnBackMovie2.setText("< Back");
        btnBackMovie2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackMovie2ActionPerformed(evt);
            }
        });

        btnExitMovie1.setText("Exit");
        btnExitMovie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitMovie1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnBackMovie2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExitMovie1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBackMovie2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnExitMovie1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    //returns int from selected status accordingly
    public int selectStatus(JTable tbl, int row, int statusPlace){
        String selected = tbl.getValueAt(row, statusPlace).toString();
        int value = 0;
        
        switch(selected){
            case "Active":
                value = 0;
                break;
            case "Inactive":
                value = 1;
                break;
        }
        return value;
    }
    
    //Returns int value from ranked movie
    public int selectMovieRank(JTable tbl, int row){
        String selected = tbl.getValueAt(row, 4).toString();
        int value = 0;
        
        switch(selected){
            case "A":
                value = 0;
                break;
            case "B":
                value = 1;
                break;
            case "B15":
                value = 2;
                break;
            case "C":
                value = 3;
                break;
            case "D":
                value = 4;
                break;
        }
        return value;
    }
    
    //Returns int value from ranked movie
    public int selectMovieGenre(JTable tbl, int row){
        String selected = tbl.getValueAt(row, 4).toString();
        int value = 0;
        
        switch(selected){
            case "Action":
                value = 0;
                break;
            case "Adventure":
                value = 1;
                break;
            case "Comedy":
                value = 2;
                break;
            case "Sci-Fy":
                value = 3;
                break;
            case "Terror":
                value = 4;
                break;
        }
        return value;
    }
    
    //Sets user info as default
    public void clearUserInfo(){
        txtIdUser.setText("");
        txtPassUser.setText("");
        txtNameUser.setText("");
        cmbStatusUser.setSelectedIndex(0);
        cmbUserType.setSelectedIndex(0);
        txtSearchUser.setText("");
    }
    
   //Sets all room UI values as default
    public void clearRoomInfo(){
        txtRoomId.setText("");
        txtRoomNumber.setText("");
        txtRoomSearch.setText("");
        cmbRoomStatus.setSelectedIndex(0);
        txtRoomSearch.setText("");
    }
    
   //Sets all UI values set as default
    public void clearScheduleInfo(){
        txtScheduleId.setText("");
        txtScheduleStart.setText("");
        txtScheduleEnd.setText("");
        txtScheduleDay.setText("");
        txtScheduleSearch.setText("");
        cmbScheduleStatus.setSelectedIndex(0);
        txtScheduleSearch.setText("");
    }
    //Sets all UI values set as default
    public void clearBillboardInfo(){
        txtBillboardRoomId.setText("");
        txtBillboardScheduleId.setText("");
        txtBillboardId.setText("");
        txtBillboardMovie.setText("");
        txtBillboardStateId.setText("");
        cmbBillboardStatus.setSelectedIndex(0);
        txtBillboardSearch.setText("");
    }
    
    //returns int from selected status accordingly
    public int selectType(JTable tbl, int row, int typePlace){
        String selected = tbl.getValueAt(row, typePlace).toString();
        int value = 0;
        
        switch(selected){
            case "User":
                value = 0;
                break;
            case "Administrator":
                value = 1;
                break;
        }
        return value;
    }
    
   //Gets you back to Login
    private void btnBackMovie2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackMovie2ActionPerformed
        hideCurrent();
    }//GEN-LAST:event_btnBackMovie2ActionPerformed
    //Exits Program
    private void btnExitMovie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitMovie1ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_btnExitMovie1ActionPerformed

    private void tblScheuleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblScheuleMouseClicked
        int row = tblScheule.getSelectedRow();
        
        txtScheduleId.setText(tblScheule.getValueAt(row, 0).toString());
        txtScheduleStart.setText(tblScheule.getValueAt(row, 1).toString());
        txtScheduleEnd.setText(tblScheule.getValueAt(row, 2).toString());
        cmbStatus.setSelectedIndex(selectStatus(tblScheule, row, 3));
        btnAddSchedule.setEnabled(false);
        btnDeleteSchedule.setEnabled(true);
        btnUpdateSchedule.setEnabled(true);
        loadScheduleTable();
    }//GEN-LAST:event_tblScheuleMouseClicked

    private void btnUpdateScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateScheduleActionPerformed
        Date date = Calendar.getInstance().getTime();
        s.setScheduleId(Integer.parseInt(txtScheduleId.getText()));
        s.setScheduleStart(date);
        s.setScheduleEnd(date); 
        String tempStatus = (String)cmbStatusUser.getSelectedItem();
        if(tempStatus.equals("Active")){
            s.setScheduleStatus(true);
        } else { s.setScheduleStatus(false); }
        try {
            sc.edit(s);
            clearScheduleInfo();
            loadScheduleTable();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateScheduleActionPerformed

    private void btnClearScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearScheduleActionPerformed
        clearScheduleInfo();
    }//GEN-LAST:event_btnClearScheduleActionPerformed

    //Deletes selected Schedule
    private void btnDeleteScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteScheduleActionPerformed
        try{
            try {
                sc.destroy(Integer.parseInt(txtScheduleId.getText()));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearScheduleInfo();
            loadScheduleTable();
        }catch(NonexistentEntityException ex){
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteScheduleActionPerformed

   //Adds new Schedule to db
    private void btnAddScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddScheduleActionPerformed
        Date date = Calendar.getInstance().getTime();
        s.setScheduleId(Integer.parseInt(txtScheduleId.getText()));
        s.setScheduleEnd(date);
        s.setScheduleEnd(date);
        String tempStatus = (String)cmbScheduleStatus.getSelectedItem();
        if(tempStatus.equals("Active")){
                s.setScheduleStatus(true);
            } else { s.setScheduleStatus(false); }
        try {
                sc.create(s);
                clearScheduleInfo();
                loadScheduleTable();
            } catch (Exception ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnAddScheduleActionPerformed

    private void txtScheduleStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScheduleStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtScheduleStartActionPerformed

    private void tblRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRoomMouseClicked
        int row = tblRoom.getSelectedRow();
        
        txtRoomId.setText(tblRoom.getValueAt(row, 0).toString());
        txtRoomNumber.setText(tblRoom.getValueAt(row, 1).toString());
        cmbRoomStatus.setSelectedIndex(selectStatus(tblRoom, row, 2));
        btnAddRoom.setEnabled(false);
        btnDeleteRoom.setEnabled(true);
        btnUpdateRoom.setEnabled(true);
        loadRoomTable();
    }//GEN-LAST:event_tblRoomMouseClicked

    private void btnClearRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRoomActionPerformed
        clearRoomInfo();
    }//GEN-LAST:event_btnClearRoomActionPerformed

    private void txtRoomSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoomSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRoomSearchActionPerformed

    //Deletes selected room
    private void btnDeleteRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRoomActionPerformed
        try{
            try {
                rc.destroy(Integer.parseInt(txtRoomId.getText()));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearRoomInfo();
            loadRoomTable();
        }catch(NonexistentEntityException ex){
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteRoomActionPerformed

    private void btnUpdateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRoomActionPerformed
        r.setRoomId(Integer.parseInt(txtRoomId.getText()));
        r.setRoomNumber(Integer.parseInt(txtRoomNumber.getText()));
        String tempStatus = (String)cmbStatusUser.getSelectedItem();
        if(tempStatus.equals("Active")){
            u.setUserStatus(true);
        } else { u.setUserStatus(false); }
        try {
            rc.edit(r);
            clearRoomInfo();
            loadRoomTable();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateRoomActionPerformed

    private void btnAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRoomActionPerformed
        r.setRoomNumber(Integer.parseInt(txtRoomNumber.getText()));
        String tempStatus = (String)cmbStatusUser.getSelectedItem();
        if(tempStatus.equals("Active")){
            r.setRoomStatus(true);
        } else { r.setRoomStatus(false); }
        try {
            rc.create(r);
            clearMovieInfo();
            loadUserTable();
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddRoomActionPerformed

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int row = tblUser.getSelectedRow();

        txtIdUser.setText(tblUser.getValueAt(row, 0).toString());
        txtNameUser.setText(tblUser.getValueAt(row, 1).toString());
        txtPassUser.setText(tblUser.getValueAt(row, 2).toString());
        cmbUserType.setSelectedIndex(selectType(tblUser, row, 3));
        cmbStatusUser.setSelectedIndex(selectStatus(tblUser, row, 4));
        btnAddUser.setEnabled(false);
        btnDeleteUser.setEnabled(true);
        btnUpdateUser.setEnabled(true);
        loadUserTable();
    }//GEN-LAST:event_tblUserMouseClicked

    //Invokes clearUserInfo method
    private void btnClearUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearUserActionPerformed
        clearUserInfo();
    }//GEN-LAST:event_btnClearUserActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteUserActionPerformed
        try{
            try {
                uc.destroy(Integer.parseInt(txtIdUser.getText()));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearUserInfo();
            loadUserTable();
        }catch(NonexistentEntityException ex){
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteUserActionPerformed

    //Adds user to db
    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        u.setUserName(txtNameUser.getText());
        u.setUserPassword(txtPassUser.getText());
        String tempUserType =  ((String) cmbUserType.getSelectedItem());
        if(tempUserType.equals("Administrator")){
            u.setUserType(true);
        } else { u.setUserType(false); }
        String tempStatus = (String)cmbStatusUser.getSelectedItem();
        if(tempStatus.equals("Active")){
            u.setUserStatus(true);
        } else { u.setUserStatus(false); }
        try {
            uc.create(u);
            clearMovieInfo();
            loadUserTable();
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddUserActionPerformed

    //Retrieves information to the slots at the selected row
    private void tblMovieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMovieMouseClicked
        int row = tblMovie.getSelectedRow();

        txtId.setText(tblMovie.getValueAt(row, 0).toString());
        txtMovieName.setText(tblMovie.getValueAt(row, 1).toString());
        txtDirector.setText(tblMovie.getValueAt(row, 2).toString());
        txtDuration.setText(tblMovie.getValueAt(row, 3).toString());
        cmbClassification.setSelectedIndex(selectMovieRank(tblMovie, row));
        cmbGenre.setSelectedIndex(selectMovieGenre(tblMovie, row));
        txtProducer.setText(tblMovie.getValueAt(row, 5).toString());
        cmbStatus.setSelectedIndex(selectStatus(tblMovie, row, 6));
        btnAddMovie.setEnabled(false);
        btnDeleteMovie.setEnabled(true);
        btnUpdateMovie.setEnabled(true);
        loadMovieTable();
    }//GEN-LAST:event_tblMovieMouseClicked

    private void txtProducerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProducerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProducerActionPerformed

    private void btnClearMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearMovieActionPerformed
        clearMovieInfo();
    }//GEN-LAST:event_btnClearMovieActionPerformed

    //Deletes movie
    private void btnDeleteMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMovieActionPerformed
        m.setMovieName(txtMovieName.getText());
        m.setMovieDirector(txtDirector.getText());
        m.setMovieClassification((String) cmbClassification.getSelectedItem());
        m.setMovieDuration(Integer.parseInt(txtDuration.getText()));
        m.setMovieProducer(txtProducer.getText());
        m.setMovieStatus(Boolean.parseBoolean((String) cmbStatus.getSelectedItem()));

        try{
            try {
                mc.destroy(Integer.parseInt(txtIdUser.getText()));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearUserInfo();
            loadUserTable();
        }catch(NonexistentEntityException ex){
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteMovieActionPerformed

   //Updates movie
    private void btnUpdateMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMovieActionPerformed
        m.setMovieId(Integer.parseInt(txtId.getText()));
        m.setMovieName(txtMovieName.getText());
        m.setMovieDirector(txtDirector.getText());
        m.setMovieClassification((String) cmbClassification.getSelectedItem());
        m.setMovieGenre((String) cmbGenre.getSelectedItem());
        m.setMovieDuration(Integer.parseInt(txtDuration.getText()));
        m.setMovieProducer(txtProducer.getText());
        String tempMovieStatus = (String)cmbStatus.getSelectedItem();
        if(tempMovieStatus.equals("Active")){
            m.setMovieStatus(true);
        } else { m.setMovieStatus(false); }
        try {
            mc.edit(m);
            clearMovieInfo();
            loadMovieTable();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateMovieActionPerformed

    private void btnAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMovieActionPerformed
        m.setMovieName(txtMovieName.getText());
        m.setMovieDirector(txtDirector.getText());
        m.setMovieClassification((String) cmbClassification.getSelectedItem());
        m.setMovieGenre((String) cmbGenre.getSelectedItem());
        m.setMovieDuration(Integer.parseInt(txtDuration.getText()));
        m.setMovieProducer(txtProducer.getText());
        String tempMovieStatus = (String)cmbStatus.getSelectedItem();
        if(tempMovieStatus.equals("Active")){
            m.setMovieStatus(true);
        } else { m.setMovieStatus(false); }
        try {
            mc.create(m);
            clearMovieInfo();
            loadMovieTable();
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddMovieActionPerformed

    private void tblBillboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillboardMouseClicked
        int row = tblBillboard.getSelectedRow();

        txtBillboardId.setText(tblBillboard.getValueAt(row, 0).toString());
        txtBillboardMovie.setText(tblBillboard.getValueAt(row, 1).toString());
        txtBillboardRoomId.setText(tblBillboard.getValueAt(row, 2).toString());
        txtBillboardScheduleId.setText(tblBillboard.getValueAt(row, 3).toString());
        txtBillboardStateId.setText(tblBillboard.getValueAt(row, 3).toString());
        cmbStatus.setSelectedIndex(selectStatus(tblBillboard, row, 6));
        btnAddBillboard.setEnabled(false);
        btnDeleteBillboard.setEnabled(true);
        btnUpdateBillboard.setEnabled(true);;
        loadBillboardTable();
    }//GEN-LAST:event_tblBillboardMouseClicked

    private void btnAddBillboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBillboardActionPerformed
        Date date = Calendar.getInstance().getTime();
        int id = Integer.parseInt(txtBillboardId.getText());
        int mo = Integer.parseInt(txtBillboardMovie.getText());;
        int ro = Integer.parseInt(txtBillboardRoomId.getText());
        int sche = Integer.parseInt(txtBillboardScheduleId.getText());
        int sta = Integer.parseInt(txtBillboardStateId.getText());        
        String tempStatus = (String)cmbBillboardStatus.getSelectedItem();
        if(tempStatus.equals("Active")){
            b.setBillboardStatus(false);
        } else { b.setBillboardStatus(false); }
        Billboard bill = new Billboard(mo, ro, sche, sta, tempStatus);
        try {
            bc.create(b);
            clearBillboardInfo();
            loadBillboardTable();
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddBillboardActionPerformed

    private void btnDeleteBillboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBillboardActionPerformed
        try{
            try {
                bc.destroy(Integer.parseInt(txtBillboardId.getText()));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
            clearBillboardInfo();
            loadBillboardTable();
        }catch(NonexistentEntityException ex){
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteBillboardActionPerformed

    private void btnClearBillboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearBillboardActionPerformed
       clearBillboardInfo();
    }//GEN-LAST:event_btnClearBillboardActionPerformed

    private void btnUpdateBillboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBillboardActionPerformed
        Date date = Calendar.getInstance().getTime();
        int id = Integer.parseInt(txtBillboardId.getText());
        int mo = Integer.parseInt(txtBillboardMovie.getText());;
        int ro = Integer.parseInt(txtBillboardRoomId.getText());
        int sche = Integer.parseInt(txtBillboardScheduleId.getText());
        int sta = Integer.parseInt(txtBillboardStateId.getText());        
        String tempStatus = (String)cmbBillboardStatus.getSelectedItem();
        if(tempStatus.equals("Active")){
            b.setBillboardStatus(false);
        } else { b.setBillboardStatus(false); }
        Billboard bill = new Billboard(mo, ro, sche, sta, tempStatus);
        try {
            bc.edit(b);
            clearBillboardInfo();
            loadBillboardTable();
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateBillboardActionPerformed

    private void btnUpdateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUserActionPerformed
        u.setUserId(Integer.parseInt(txtIdUser.getText()));
        u.setUserName(txtNameUser.getText());
        u.setUserPassword(txtPassUser.getText());
        String tempType = (String)cmbUserType.getSelectedItem();
        if(tempType.equals("Administrator")){
            u.setUserType(true);
        } else {u.setUserType(false); }
        String tempStatus = (String)cmbStatusUser.getSelectedItem();
        if(tempStatus.equals("Active")){
            u.setUserStatus(true);
        } else { u.setUserStatus(false); }
        try {
            uc.edit(u);
            clearUserInfo();
            loadUserTable();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateUserActionPerformed

    private void txtScheduleSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtScheduleSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtScheduleSearchKeyTyped

    private void txtDurationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDurationKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            //Won't be able to enter in text field if enter char is not number
            txtBillboardMovie.setEditable(false);
            jLabel33.setText("Please enter numbers only");
        } else {
            txtBillboardMovie.setEditable(true);
        }
    }//GEN-LAST:event_txtDurationKeyPressed

    private void txtBillboardMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBillboardMovieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBillboardMovieActionPerformed

    private void txtBillboardMovieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBillboardMovieKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            //Won't be able to enter in text field if enter char is not number
            txtBillboardRoomId.setEditable(false);
            jLabel34.setText("Please enter numbers only");
        } else {
            txtBillboardRoomId.setEditable(true);
        }
    }//GEN-LAST:event_txtBillboardMovieKeyPressed

    private void txtBillboardRoomIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBillboardRoomIdKeyPressed
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            //Won't be able to enter in text field if enter char is not number
            txtDuration.setEditable(false);
            lblDurationValidation.setText("Please enter numbers only");
        } else {
            txtDuration.setEditable(true);
        }
    }//GEN-LAST:event_txtBillboardRoomIdKeyPressed

    private void txtBillboardSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBillboardSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBillboardSearchKeyPressed

    //return int value when user type is selected
    public int selectUserType(JTable tbl, int row){
        String selected = tbl.getValueAt(row, 6).toString();
        int value = 0;
        switch(selected){
            case "User":
                value = 0;
                break;
            case "Administrator":
                value = 1;
                break;
        }
        return value;
    }
    
    //Hide current frameand set Login frame visible
    public void hideCurrent(){
        Login welcome = new Login();
        welcome.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }

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
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBillboard;
    private javax.swing.JButton btnAddMovie;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnAddSchedule;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnBackMovie2;
    private javax.swing.JButton btnClearBillboard;
    private javax.swing.JButton btnClearMovie;
    private javax.swing.JButton btnClearRoom;
    private javax.swing.JButton btnClearSchedule;
    private javax.swing.JButton btnClearUser;
    private javax.swing.JButton btnDeleteBillboard;
    private javax.swing.JButton btnDeleteMovie;
    private javax.swing.JButton btnDeleteRoom;
    private javax.swing.JButton btnDeleteSchedule;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnExitMovie1;
    private javax.swing.JButton btnUpdateBillboard;
    private javax.swing.JButton btnUpdateMovie;
    private javax.swing.JButton btnUpdateRoom;
    private javax.swing.JButton btnUpdateSchedule;
    private javax.swing.JButton btnUpdateUser;
    private javax.swing.JComboBox<String> cmbBillboardStatus;
    private javax.swing.JComboBox<String> cmbClassification;
    private javax.swing.JComboBox<String> cmbGenre;
    private javax.swing.JComboBox<String> cmbRoomStatus;
    private javax.swing.JComboBox<String> cmbScheduleStatus;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JComboBox<String> cmbStatusUser;
    private javax.swing.JComboBox<String> cmbUserType;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDurationValidation;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblBillboard;
    private javax.swing.JTable tblMovie;
    private javax.swing.JTable tblRoom;
    private javax.swing.JTable tblScheule;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtBillboardId;
    private javax.swing.JTextField txtBillboardMovie;
    private javax.swing.JTextField txtBillboardRoomId;
    private javax.swing.JTextField txtBillboardScheduleId;
    private javax.swing.JTextField txtBillboardSearch;
    private javax.swing.JTextField txtBillboardStateId;
    private javax.swing.JPanel txtClassification;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtDuration;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JTextField txtMovieName;
    private javax.swing.JTextField txtNameUser;
    private javax.swing.JTextField txtPassUser;
    private javax.swing.JTextField txtProducer;
    private javax.swing.JTextField txtRoomId;
    private javax.swing.JTextField txtRoomNumber;
    private javax.swing.JTextField txtRoomSearch;
    private javax.swing.JTextField txtScheduleDay;
    private javax.swing.JTextField txtScheduleEnd;
    private javax.swing.JTextField txtScheduleId;
    private javax.swing.JTextField txtScheduleSearch;
    private javax.swing.JTextField txtScheduleStart;
    private javax.swing.JTextField txtSearchMovie;
    private javax.swing.JTextField txtSearchUser;
    // End of variables declaration//GEN-END:variables
}
