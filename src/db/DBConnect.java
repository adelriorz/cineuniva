package DB;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class DBConnect {

    private static final String servername = "localhost";
    private static final Integer portnumber = 3306;
    private static final String URL = "jdbc:mysql://localhost:3306/cinema?zeroDateTimeBehaviour=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String dbname = "cinema";
    private static PreparedStatement st;
    private static ResultSet rs;    
    
    public DBConnect(){
    }
    
    //Method to connect to DB.
    public static Connection getConnection(){
        Connection cnx = null;
        MysqlDataSource datasource = new MysqlDataSource();
        
        datasource.setServerName(servername);
        datasource.setUser(USERNAME);
        datasource.setPassword(PASSWORD);
        datasource.setDatabaseName(dbname);
        datasource.setPortNumber(portnumber);
        
        try {
            cnx = datasource.getConnection();
        } catch (SQLException ex) {
            System.out.println("Problema en db");
            Logger.getLogger(" Get Connection -> " + DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
       
}
