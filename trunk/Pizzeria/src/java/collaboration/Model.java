package collaboration;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;



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
                pizza.add(Double.toString(rs.getDouble("prezzo"))+"0");
                
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
    
    public ArrayList<String> getLista(){
        ArrayList<String>lista = new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            String query = "SELECT * FROM pizze";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                lista.add(rs.getString("nome"));
            }    
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();            
            lista = null;
        }
        return lista;
    }
    
    public int insertPrenotazione (String user, String nome, int quantita){
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement st = conn.createStatement();
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
            nome.replaceAll("\r","");
            st.execute("INSERT INTO Prenotazioni VALUES ('"+user+"','"+nome+"',"+quantita+",current date,false,null)");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return 1;
    }
    public ArrayList<ArrayList<String>> getOrdini(String user){
        ArrayList<ArrayList<String>> ordini= new ArrayList<ArrayList<String>>();
        
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            String query="SELECT * FROM Prenotazioni WHERE Prenotazioni.utente='"+user+"'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                pizza.add(rs.getString("pizza"));
                pizza.add(rs.getString("quantita"));
                ordini.add(pizza);
            }    
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ordini = null;
        }
        return ordini;
    }
}
