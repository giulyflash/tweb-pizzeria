package myBeans;

public class UtentiBean {
    private String username;
    private String ruolo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    
    public String getUserInterface() {
        String userInterface = "";
        
        if(username==null) {
            userInterface+="<form id=\"frmLogin\" action=\"Controller\" method=\"POST\">\n" +
"                        <input type=\"hidden\" id=\"action\" name=\"action\" value=\"login\" />\n" +
"                        <h4 class=\"sottotitolo\">Login</h4>\n" +
"                        <table class=\"tblLogin\" >\n" +
"                            <tr>\n" +
"                                <td>Username:</td>\n" +
"                                <td><input class=\"inputLogin\" type=\"text\" id=\"txtUsername\" name=\"txtUsername\" required/></td>\n" +
"                            </tr>\n" +
"                            <tr>\n" +
"                                <td>Password:</td>\n" +
"                                <td><input class=\"inputLogin\" type=\"password\" id=\"txtPassword\" name=\"txtPassword\" required/></td>\n" +
"                            </tr>\n" +
"                        </table>\n" +
"                        <input id=\"btnOk\" name=\"btnOk\" type=\"submit\" value=\"Conferma\" />\n\n" +
"                    </form>" +
                    
"                    <form id=\"frmReg\" action=\"Controller\" method=\"POST\">\n" +
"                       <input type=\"hidden\" id=\"action\" name=\"action\" value=\"registrati\" />\n" +
"                       <input id=\"btnReg\" name=\"btnReg\" type=\"submit\" value=\"oppure Registrati\" />\n" +
"                    </form>";
        } else {
            if (ruolo!=null){
               if (ruolo.compareTo("Amministratore")==0){
                userInterface+="<form id=\"frmLogin\" action=\"Controller\" method=\"POST\">\n" +
"                        <input type=\"hidden\" id=\"action\" name=\"action\" value=\"logout\" />\n" +
"                        <p> Benvenuto, " + username + " </p>\n" +
"                        <img src=\"images/admin.jpg\" class=\"image\"/>"+
"                        <p>" +
"                        <input class=\"centered\" id=\"btnLogout\" name=\"btnLogout\" type=\"submit\" value=\"Logout\" />\n" +
"                        </p>" +
"                    </form>";
                }
                else if (ruolo.compareTo("Cliente")==0){
                    userInterface+="<form id=\"frmLogin\" action=\"Controller\" method=\"POST\">\n" +
"                        <input type=\"hidden\" id=\"action\" name=\"action\" value=\"logout\" />\n" +
"                        <p> Benvenuto, " + username + " </p>\n" +
"                        <img src=\"images/customer.jpg\" class=\"image\"/>"+
"                        <p>" +
"                        <input class=\"centered\" id=\"btnLogout\" name=\"btnLogout\" type=\"submit\" value=\"Logout\" />\n" +
"                        </p>" +
"                    </form>";
                } 
            } 
        }
        return userInterface;
    }  
    
    public String getMenu(){
        String menu="";
        if (ruolo!=null)
        {
            if (ruolo.compareTo("Cliente")==0){
                menu="<nav>";
                menu=menu+"<h3 class=\"intestazione\"> Menu </h3>";
                menu=menu+"<p>";
                menu=menu+"<ul>";
                menu=menu+"<li> <a href=\"Controller?action=ordina\">Fai un nuovo ordine </a> </li>";
                menu=menu+"<li> <a href=\"Controller?action=prenotazioni\">Visualizza i tuoi ordini </a> </li>";
                menu=menu+"</ul>";
                menu=menu+"</p>";
                menu=menu+"</nav>";
                menu=menu+"<article>";  
            }
            else if (ruolo.compareTo("Amministratore")==0){
                menu="<nav>";
                menu=menu+"<h3 class=\"intestazione\"> Menu </h3>";
                menu=menu+"<p>";
                menu=menu+"<ul>";
                menu=menu+"<li> <a href=\"Controller?action=ordina\">Fai un nuovo ordine </a> </li>";
                menu=menu+"<li> <a href=\"Controller?action=prenotazioni\">Visualizza i tuoi ordini </a> </li>";
                menu=menu+"<li> <a href=\"Controller?action=storia\">Visualizza lo storico degli ordini </a> </li>";
                menu=menu+"<li> <a href=\"Controller?action=modifica\">Modifica elenco pizze </a> </li>";
                menu=menu+"</ul>";
                menu=menu+"</p>";
                menu=menu+"</nav>";
                menu=menu+"<article>";
            }
        }
        else menu= "<article class=\"articleCenter\">";
        return menu;
    }
}
