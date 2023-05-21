
package bankingapplication1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankConnection {
    public static Connection connect() {
        String URL = "jdbc:mysql://localhost:3306/mydb1";
        String username = "root";
        String password = "151246";
        Connection connection = null;
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //1.driver
            connection = DriverManager.getConnection(URL, username, password); //2.connection

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BankConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
}
