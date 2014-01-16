package collaboration;

import database.DBUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;

public class Model {
    private String url = "jdbc:derby://localhost:1527/pizzeria2";
    private String user = "test";
    private String pwd = "test";
    
    public Model() throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());        
    }
    
    public ArrayList<ArrayList<String>> getCatalogoPizze() {
        
        ArrayList<ArrayList<String>> catalogo = null;
        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            
            String query = "SELECT * FROM pizze WHERE datafine is null";
            
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            catalogo = new ArrayList<ArrayList<String>>();
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                pizza.add(rs.getString("nome"));
                pizza.add(rs.getString("ingredienti"));
                pizza.add(rs.getString("prezzo"));
                
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
            String query = "SELECT * FROM pizze WHERE datafine is null";
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
    
    public int insertPrenotazione (String user, String nome, String quantita, Timestamp time){
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement st = conn.createStatement();
            String inizio = getDataValidita (nome);
            st.execute("INSERT INTO Prenotazioni VALUES ('"+user+"','"+nome+"',"+quantita+",'I',null,current timestamp,'"+time+"','"+inizio+"')");
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
            String query="SELECT * FROM Prenotazioni WHERE Prenotazioni.utente='"+user+"' AND status <> 'C' ORDER BY dataconsegna";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                pizza.add(rs.getString("pizza"));
                pizza.add(rs.getString("quantita"));
                pizza.add(rs.getString("status"));
                pizza.add(getPrezzo(rs.getString("pizza"), rs.getString("datavalidita")));
                pizza.add(rs.getString("dataordine"));
                pizza.add(rs.getString("dataconsegna"));
                pizza.add(rs.getString("datarichiesta"));
                ordini.add(pizza);
            }    
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ordini = null;
        }
        return ordini;
    }
    
    public ArrayList<ArrayList<String>> getOrdiniTutti(){
        ArrayList<ArrayList<String>> ordini= new ArrayList<ArrayList<String>>();
        
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            String query="SELECT * FROM Prenotazioni ORDER BY status";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                ArrayList<String> pizza = new ArrayList<String>();
                pizza.add(rs.getString("utente"));
                pizza.add(rs.getString("pizza"));
                pizza.add(rs.getString("quantita"));
                pizza.add(rs.getString("status"));
                pizza.add(getPrezzo(rs.getString("pizza"), rs.getString("datavalidita")));
                pizza.add(rs.getString("dataconsegna"));
                pizza.add(rs.getString("datarichiesta"));
                pizza.add(rs.getString("dataordine"));
                ordini.add(pizza);
            }    
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ordini = null;
        }
        return ordini;
    }
    
    public boolean insertPizza (String nome, String ingredienti, String prezzo){
        boolean ret=false;
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            String query="SELECT * FROM Pizze WHERE Nome='"+nome+"'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()) stm.execute("UPDATE Pizze SET datafine=current timestamp WHERE nome='"+nome+"'");
            stm.execute("INSERT INTO Pizze VALUES ('"+nome+"','"+ingredienti+"',"+prezzo+",current timestamp,null)");
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ret = true;
        }
        return ret;
    }
    
    public boolean updatePizza (String nome, String newnome, String ingredienti, String prezzo){
        boolean ret=false;
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            stm.execute("UPDATE Pizze SET datafine=current timestamp WHERE nome='"+nome+"'");
            stm.execute("INSERT INTO Pizze VALUES ('"+newnome+"','"+ingredienti+"',"+prezzo+",current timestamp,null)");
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ret = true;
        }
        return ret;
    }
    
    public boolean deletePizza (String nome){
        boolean ret=false;
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            stm.execute("UPDATE Pizze SET datafine=current timestamp WHERE nome='"+nome+"'");
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ret = true;
        }
        return ret;
    }
    
    public ArrayList<String> getPizza (String nome){
        ArrayList<String> ret= new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Pizze WHERE nome='"+nome+"' and datafine is not null");
            while(rs.next()){
                ret.add(rs.getString("Ingredienti"));
                ret.add(rs.getString("Prezzo"));
            }
            conn.close();
        }catch (SQLException ex) {
            ex.printStackTrace();            
            ret = null;
        }
        return ret;
    }
    
    public String getPrezzo (String pizza, String datavalidita){
        String prezzo=null;
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT prezzo FROM Pizze WHERE nome='"+pizza+"' and datainizio='"+datavalidita+"'");
            while(rs.next()){
                prezzo=rs.getString("prezzo");
            }
            conn.close();  
        }catch (SQLException ex) {
            ex.printStackTrace();            
            prezzo = null;
        }
        return prezzo;
    }
    
    public boolean updateOrder(String utente, String pizza, String data){
        
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs=stm.executeQuery ("SELECT dataordine FROM prenotazioni");
            while (rs.next()){
                DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Timestamp ts = rs.getTimestamp("dataordine");
                String datedb = date.format(ts);
                if (datedb.compareTo(data)==0){
                    stm.execute("UPDATE Prenotazioni SET Status='A',DataConsegna=current timestamp WHERE Utente='"+utente+"' AND Pizza='"+pizza+"'");
                }          
            }
            conn.close();  
        }catch (SQLException ex) {
            ex.printStackTrace();            
            return false;
        }
        return true;
    }
    
    public int insertUser(String nome, String password){
        int ret=1;
        try{
            Connection conn = DBUtil.createConnection(url, user, pwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM utenti WHERE nome='" + nome + "'");
            if (rs.next())ret=0;
            else{
                st.execute("INSERT INTO utenti VALUES ('" + nome + "','" + password + "','Cliente')");
            }
            rs.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();            
            ret = 0;
        }
        return ret;
    }
    
    public String[] getAttributi (String nome){
        String[]argo = new String[3];
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Pizze WHERE nome='"+nome+"' and datafine is null");
            while(rs.next()){
                argo[0]=rs.getString("ingredienti");
                argo[1]=rs.getString("prezzo");
                argo[2]=rs.getString("nome");
            }
            conn.close();  
        }catch (SQLException ex) {
            ex.printStackTrace();            
            argo = null;
        }
        return argo;
    }
    
    public boolean deleteOrder(String utente, String pizza, String data){    
        try{
            Connection conn = DriverManager.getConnection(url, this.user, pwd);
            Statement stm = conn.createStatement();
            Statement stm2=conn.createStatement();
            ResultSet rs=stm.executeQuery ("SELECT dataordine FROM prenotazioni WHERE status='I'");
            while (rs.next()){
                DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Timestamp ts = rs.getTimestamp("dataordine");
                String datedb = date.format(ts);
                if (datedb.compareTo(data)==0){
                    stm2.execute("UPDATE Prenotazioni SET status='C', dataconsegna=current timestamp WHERE Utente='"+utente+"' AND Pizza='"+pizza+"' AND dataordine='"+ts+"'");
                }          
            }
            conn.close();  
        }catch (SQLException ex) {
            ex.printStackTrace();            
            return true;
        }
        return false;
    }
    
    public String getDataValidita (String nome){
        String data=null;
        try{
            Connection conn = DriverManager.getConnection(url,user,pwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select datainizio from pizze where nome='"+nome+"' and datafine is null");
            while (rs.next()){
                data = rs.getString("datainizio");
            }
        }catch(SQLException e){
            e.printStackTrace();            
            return null;
        }
        return data;
    }
}
