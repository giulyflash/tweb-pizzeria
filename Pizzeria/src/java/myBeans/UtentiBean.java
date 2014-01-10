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
"                        <p class=\"sottotitolo\"> Benvenuto, " + username + " </p>\n" +
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
                menu=menu+"<h2 class=\"intestazione\"> Menu </h2>";
                menu=menu+"<p>";
                menu=menu+"<ul>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=home\" onmouseover=\"rilievo\">Home</a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=ordina\">Fai un nuovo ordine </a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=prenotazioni\">I tuoi ordini </a> </li>";
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
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=home\">Home</a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=ordina\">Nuovo ordine </a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=prenotazioni\">I tuoi ordini </a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=storia\">Storico degli ordini </a> </li>";
                menu=menu+"<li> <a class=\"liColored\" href=\"Controller?action=modifica\">Modifica elenco pizze </a> </li>";
                menu=menu+"</ul>";
                menu=menu+"</p>";
                menu=menu+"</nav>";
                menu=menu+"<article>";
            }
        }
        else menu= "<article class=\"articleCenter\">";
        return menu;
    }
    
    public String getFooter(){
        String ret="";
        ret+="<div class=\"divFooterSX\">\n" +
"                <h4 class=\"sottotitoloF\">Realizzato da:</h4>\n" +
"                <ul>\n" +
"                    <li>Pavan Francesco</li>\n" +
"                    <li>Mannu Giorgio</li>\n" +
"                    <li>Marchisone Yari</li>\n" +
"                </ul>\n" +
"            </div>\n" + 
"            <div class=\"divFooterDX\">\n" +
"                <h4 class=\"sottotitoloF\">Contacts:</h4>\n" +
"                <ul>\n" +
"                    <li>Mail: <a href=\"mailto:info@pizzeriaonline.unito.it\">info@pizzeriaonline.unito.it</a></li>\n" +
"                    <li>Telefono: +39 345 34 56 789</li>\n" +
"                    <li>Indirizzo: Via Pessinetto 18, 10100 (TO)</li>\n" +
"                </ul>\n" +
"            </div>";
        return ret;
    }
}
