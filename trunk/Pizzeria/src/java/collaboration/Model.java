package collaboration;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Model {
    private String url = "jdbc:derby://localhost:1527/pizzeria";
    private String user = "test";
    private String pwd = "test";
    
    public Model() throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());        
    }
    
    public ArrayList<ArrayList<String>> getCatalogoPizze() {
        
        ArrayList<ArrayList<String>> catalogo = null;
        try {
            
            Connection conn = DriverManager.getConnection(url, user, pwd);
            
            String query = "SELECT * FROM pizze";
            
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            catalogo = new ArrayList<ArrayList<String>>();
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                pizza.add(rs.getString("nome"));
                pizza.add(rs.getString("ingredienti"));
                pizza.add(Double.toString(rs.getDouble("prezzo")));
                
                catalogo.add(pizza);
            }    
            
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();            
            catalogo = null;
        }
        
        return catalogo;
    }
    
    public String getUserRole(String username,String password) {
        String role = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT Ruolo FROM Utenti WHERE Nome='" + username + "' AND Password='" + password + "'");
            
            if(rs.next()) 
                role = rs.getString("ruolo");                
            else 
                role = "";
            
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            role = null;
        }
        
        return role;
    }
}
