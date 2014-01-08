package database;

import java.sql.*;

public class DBUtil {
    public static Connection createConnection(String url,String user,String password) {
        Connection conn = null;
        
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(url, user, password);
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
}
