package Controller;

import Classes.User;
import db.DbConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
    
    protected User userManager;
    
    public UserController(){
    }
    
    public User login(String username, String password){      
        PreparedStatement st;
        ResultSet rs;
        int idUser = -1;
        boolean typeUser = false, statusUser = false;
        String nameUser = null;
        String passwordUser = null;
        
        String query;
        query = "SELECT `idUser`, `nameUser`, `passwordUser`, `typeUser`, "
                + "`statusUser`  FROM `user` WHERE `nameUser`= ? AND "
                + "`passwordUser` = ? AND `statusUser` = 1";
        
        try {
            st = DbConnect.getConnection().prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            rs = st.executeQuery();
            
            if(rs.next()){
                // Los de la derecha son variables de SQL.
                idUser = rs.getInt("idUser");
                nameUser = rs.getString("nameUser");
                passwordUser = rs.getString("passwordUser");
                typeUser = rs.getBoolean("typeUser");
                statusUser = rs.getBoolean("statusUser");
                System.out.println("statusUser = " + statusUser);
                userManager = new User(idUser, nameUser, passwordUser, typeUser,
                        statusUser);
            } else {
                System.out.print("\n\n\tInvalid Username/ Password.\n\n");
            }
            st.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userManager;
    }
}
