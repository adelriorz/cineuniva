package frames;

import controllers.BillboardJpaController;
import controllers.MovieJpaController;
import controllers.RoomJpaController;
import controllers.ScheduleJpaController;
import controllers.StateJpaController;
import entities.Billboard;
import entities.Movie;
import entities.Room;
import entities.Schedule;
import entities.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tools.QueueString;
import tools.StringQuickSort;
import static tools.StringQuickSort.parseListToArr;

public final class BillboardView extends javax.swing.JFrame {
/*
**Written by: Armando Del Río Ramírez
**Date: 01/05/ 2021 - 04/10/2021
**Description: Billboard view where users can interact
*/
    protected static QueueString q;
    private final Billboard b;
    private final Movie m;
    private final Schedule s;
    private final Room r;
    private final State st;
    private final BillboardJpaController bc;
    private final MovieJpaController mc;
    private final ScheduleJpaController sc;
    private final RoomJpaController rc;
    private final StateJpaController stc;
    protected static Login log;
    private String parsedQueueString[];
    
    //BillboardView constructor
    public BillboardView(QueueString q) {
        super();
        initComponents();
        this.setLocationRelativeTo(null);
        this.parsedQueueString = Login.q.getContent();
        b = new Billboard();
        m = new Movie();
        s = new Schedule();
        r = new Room();
        st = new State();
        bc = new BillboardJpaController();
        mc = new MovieJpaController();
        sc = new ScheduleJpaController();
        rc = new RoomJpaController();
        stc = new StateJpaController();
        loadBillboardViewTable();
        tblBillboard.setShowGrid(true); //Shows grid in table
    }
    
    //Constructor with no parameter
    public BillboardView() {
        initComponents();
        b = new Billboard();
        m = new Movie();
        s = new Schedule();
        r = new Room();
        st = new State();
        bc = new BillboardJpaController();
        mc = new MovieJpaController();
        sc = new ScheduleJpaController();
        rc = new RoomJpaController();
        stc = new StateJpaController();
        tblBillboard.setShowGrid(true); //Shows grid in table
    }
    
    //ComboBox Selection for searching or sorting movies at Billboard
    public void userSelection(){
        int value;
        value = cmbUser.getSelectedIndex();
        switch(value){
            case 0:
                filterBy();
                break;
            case 1: 
                filterBy();
                break;
            case 2:  
                filterBy();
                break;
            case 3: 
                sortMovies(true);
                break;
            case 4: 
                sortMovies(false);
                break;
            default: 
                JOptionPane.showMessageDialog(this,
                        "Error found at userSelection, contact the administrator");
                break;
        }
    }
    
    public void sorti(){
        StringQuickSort qS = new StringQuickSort();
        List<String> list = new ArrayList<>();
        String[] s = parseListToArr(list);
        qS.sort(s);
    }
    
    //Detects wich option has been selected by user and returns values if any
    public void sortMovies(boolean as){
        if(true){//"Sort A-Z"
            DefaultTableModel dtm = new DefaultTableModel();
            List<Billboard> billboardList = new ArrayList<>();
            billboardList = bc.findBillboardEntities();
            String tempState = this.parsedQueueString[0];
            dtm.addColumn("Movie");
            dtm.addColumn("Duration");
            dtm.addColumn("Classification");
            dtm.addColumn("Gender");
            dtm.addColumn("Room");
            dtm.addColumn("Date/Time");
            
            for(Billboard b : billboardList){
                Object row[] = new Object[6];
                row[0] = b.getMovieId().getMovieName();
                row[1] = b.getMovieId().getMovieDuration();
                row[2] = b.getMovieId().getMovieClassification();
                row[3] = b.getMovieId().getMovieGenre();
                row[4] = b.getRoomId().getRoomNumber();
                row[5] = b.getScheduleId().getScheduleStart();
            if(tempState.equals(b.getStateId().getStateName()) 
                    && b.getMovieId().getMovieStatus()==true 
                    && b.getRoomId().getRoomStatus()==true
                    && b.getScheduleId().getScheduleStatus()==true
                ){ dtm.addRow(row); }
            }
            if(dtm.getRowCount()<=0) { 
                JOptionPane.showMessageDialog(this,
                    "No movies available");
                hideCurrent();
            }
            tblBillboard.setModel(dtm);
              txtsearchBillboard.setText("");
        } else {//"Sort Z-A"
            
        }
    }
    
    
    //Search Criteria Method to find movies by name
    public void filterBy(){
        String name = txtsearchBillboard.getText().toLowerCase();
        DefaultTableModel dtm = new DefaultTableModel();
        List<Billboard> billboardList = new ArrayList<>();
        billboardList = bc.findBillboardEntities();
        String tempState = this.parsedQueueString[0];
        dtm.addColumn("Movie");
        dtm.addColumn("Duration");
        dtm.addColumn("Classification");
        dtm.addColumn("Gender");
        dtm.addColumn("Room");
        dtm.addColumn("Date/Time");    
        for(Billboard b : billboardList){
            Object row[] = new Object[6];
            row[0] = b.getMovieId().getMovieName();
            row[1] = b.getMovieId().getMovieDuration();
            row[2] = b.getMovieId().getMovieClassification();
            row[3] = b.getMovieId().getMovieGenre();
            row[4] = b.getRoomId().getRoomNumber();
            row[5] = b.getScheduleId().getScheduleStart();
            if(tempState.equals(b.getStateId().getStateName())
                && b.getMovieId().getMovieStatus()==true 
                && b.getRoomId().getRoomStatus()==true
                && b.getScheduleId().getScheduleStatus()==true
                && name.equals(b.getMovieId().getMovieName().toLowerCase())
                || name.equals(b.getMovieId().getMovieClassification().toLowerCase())
                || name.equals(b.getMovieId().getMovieGenre().toLowerCase())
              ){
                dtm.addRow(row);
            }
        }
        if(dtm.getRowCount()<=0) { 
            JOptionPane.showMessageDialog(this,
                "No movies available");
            hideCurrent();
        }
        tblBillboard.setModel(dtm);
        txtsearchBillboard.setText("");
    }
    
    //Loads billboard table from DB filtering State option from user
    public void loadBillboardViewTable(){
        sorti();
        DefaultTableModel dtm = new DefaultTableModel();
        List<Billboard> billboardList = new ArrayList<>();
        billboardList = bc.findBillboardEntities();
        String tempState = this.parsedQueueString[0];
        dtm.addColumn("Movie");
        dtm.addColumn("Duration");
        dtm.addColumn("Classification");
        dtm.addColumn("Gender");
        dtm.addColumn("Room");
        dtm.addColumn("Date/Time");    
        for(Billboard b : billboardList){
            Object row[] = new Object[6];
            row[0] = b.getMovieId().getMovieName();
            row[1] = b.getMovieId().getMovieDuration();
            row[2] = b.getMovieId().getMovieClassification();
            row[3] = b.getMovieId().getMovieGenre();
            row[4] = b.getRoomId().getRoomNumber();
            row[5] = b.getScheduleId().getScheduleStart();
            if(tempState.equals(b.getStateId().getStateName()) 
                    && b.getMovieId().getMovieStatus()==true 
                    && b.getRoomId().getRoomStatus()==true
                    && b.getScheduleId().getScheduleStatus()==true
              ){ dtm.addRow(row); }
        }
        if(dtm.getRowCount()<=0) { 
            JOptionPane.showMessageDialog(this,
                "No movies available in your State");
            hideCurrent();
        }
        tblBillboard.setModel(dtm);
    }

    //Hide current Frame and displays Login
    public void hideCurrent(){
        Login login = new Login();
        this.dispose();
        login.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbUser = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtsearchBillboard = new javax.swing.JTextField();
        btnSearchBillboard = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillboard = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("UNIVA CINEMA");

        cmbUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Classification", "Genre", "Sort A-Z", "Sort Z-A" }));

        jLabel2.setText("Search");

        btnSearchBillboard.setText("Go");
        btnSearchBillboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchBillboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtsearchBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtsearchBillboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchBillboard))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(tblBillboard);

        btnBack.setText("< Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        hideCurrent();
    }//GEN-LAST:event_btnBackActionPerformed
    //When clicked the input will be lowered and sent to filter or sort
    private void btnSearchBillboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchBillboardActionPerformed
        userSelection();
    }//GEN-LAST:event_btnSearchBillboardActionPerformed

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
            java.util.logging.Logger.getLogger(BillboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillboardView(q).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSearchBillboard;
    private javax.swing.JComboBox<String> cmbUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBillboard;
    private javax.swing.JTextField txtsearchBillboard;
    // End of variables declaration//GEN-END:variables
}
