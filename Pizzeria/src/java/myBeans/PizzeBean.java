package myBeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PizzeBean {
    private ArrayList<ArrayList<String>> pizze;
    private ArrayList<String> lista;

    private ArrayList<ArrayList<String>> ordini;

    private String tabella="";

    
    public ArrayList<ArrayList<String>> getPizze() {
        return pizze;
    }

    public void setPizze(ArrayList<ArrayList<String>> pizze) {
        this.pizze = pizze;
    }
    
    public void setLista(ArrayList<String> lista){
        this.lista=lista;
    } 
    public ArrayList<ArrayList<String>> getOrdini() {
        return ordini;
    }

    public void setOrdini(ArrayList<ArrayList<String>> ordini) {
        this.ordini = ordini;
    }
    
    public String getTabellaPizze() {
        String table = "<table class=\"tblBordi\">";
        table+="\n\t<tr>\n\t\t<th>Pizza</th>\n\t\t<th>Ingredienti</th>\n\t\t<th>Prezzo</th>\n\t</tr>";
        if(pizze!=null) {
            for(ArrayList<String> pizza : pizze) {
                table+="\n\t<tr>\n\t";
                table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                table+="\t<td>" + pizza.get(2) +"0 &#8364</td>\n\t";
                table+="</tr>\n\t";
            }
        }
        table+="</table>";
        return table;
    }
    
    public String getListaPizze(){
        String ret="";
        ret+="<option value=\"Scegli la pizza\">Scegli la pizza </option>";
        if(lista!=null) {
            for(String elenco : lista) {
                ret+="<option value=\""+elenco+"\">"+elenco+"</option>";
            }
        }
        return ret;
    }

    public String getTabellaOrdini() {
        String table ="";
        int index=0, j=0;
        String dataconsegna = "";
        String dataordine = "";
        String datarichiesta ="";
        table+="<div><h3 class=\"sottotitolo\">Ordini consegnati</h3></div>";
        if(ordini.size()!=0){
            if (ordini.get(0).get(5)==null){
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            else{
                table+= "<table class=\"tblBordi\" id=\"table\">";
                table+="\n\t<tr>\n<th>Pizza</th>\n<th>Quantità</th>\n<th>Prezzo</th>\n<th>Ordinato</th>\n<th>Consegnato</th></tr>";
                for(ArrayList<String> pizza : ordini) {
                    if(pizza.get(2).equals("A"))
                    {
                        dataconsegna = pizza.get(5).substring(0,10);
                        dataordine = pizza.get(4).substring(0,10);
                        table+="\n\t<tr>\n\t";
                        table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                        table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                        table+="\t<td>" + pizza.get(6) +"0 &#8364</td>\n\t";
                        table+="\t<td>" + dataordine +"</td>\n\t";
                        table+="\t<td>" + dataconsegna +"</td>\n\t";
                        table+="</tr>\n\t";
                        j++;
                    }                 
                }
                table+="</table>";
            }
            index=ordini.size();
            table+="<hr>";
            table+="<div><h3 class=\"sottotitolo\">Ordini non consegnati</h3></div>";
            if (index==j){
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
                table+="</table>";
            }
            else{
                table+="<form id=\"Ordini\" name=\"Ordini\" action=\"Controller\" method=\"POST\">";
                table+="<table class=\"tblOrdini\" id=\"conferma\">";
                table+="\n\t<tr>\n<th>Pizza</th>\n<th>Quantità</th>\n<th>Prezzo</th><th>Ordinato</th>\n<th>Rischiesto</th>\n<th>Operazione</th></tr>";
                int i=1;
                for(ArrayList<String> pizza : ordini) {
                    if(pizza.get(2).equals("I"))
                    {
                        dataordine = pizza.get(4).substring(0,16);
                        datarichiesta = pizza.get(7).substring(0,16);
                        Timestamp time = new Timestamp(new Date().getTime());
                        table+="\n\t<tr>\n\t";
                        table+="\t<td><input type=\"textbox\" class=\"textbox\" name=\"txtPizza"+i+"\" id=\"txtPizza"+i+"\" value=\""+pizza.get(0)+"\" readonly></td>\n\t";
                        table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                        table+="\t<td>" + pizza.get(6) +"0 &#8364</td>\n\t";
                        table+="\t<td><input type=\"textbox\" class=\"textbox\" name=\"txtOrd"+i+"\" id=\"txtOrd"+i+"\" value=\""+dataordine+"\" readonly></td>\n\t";
                        table+="\t<td>" + datarichiesta +"</td>\n\t";
                        table+="\t<td><select name=\"cmbOp"+i+"\" id=\"cmbOp"+i+"\">\n\t";
                        table+="<option value=\"No\">No operazione</option>";
                        table+="<option value=\"Consegna\">Consegnato</option>";
                        table+="<option value=\"Cancella\">Annulla</option>";
                        table+="</select>";
                        table+="</td>";
                        table+="</tr>\n\t";
                        i++;
                    }               
                }
                table+="</table>";
                table+="<div class=\"divMain\">";
                table+="\t<td><input type=\"button\" id=\"btnConfOrdini\" name=\"btnConfOrdini\" value=\"Conferma lista ordini\" onclick=\"confermaOrdini()\"/></td>";
            }
        }      
        else {
              table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
              table+="</table>";
              table+="<hr>";
              table+="<div><h3 class=\"sottotitolo\">Ordini non consegnati</h3></div>";
              table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
              table+="</table>";
        }
       return table;
    }
    
    public String getTabellaOrdiniCompleta() {
        String table ="<div id=\"arrivate\" hidden=\"true\"><h3 class=\"sottotitolo\">Ordini consegnati</h3>";       
        String dataop = "";
        String dataordine="";
        int i=0;        
        if(ordini.size()!=0){
            if(ordini.get(0).get(3).equals("A")){
                table+= "<table class=\"tblBordi\">";
                table+="\n\t<tr>\n\t\t<th>Utente</th>\n\t\t<th>Pizza</th>\n\t\t<th>Quantità</th>\n\t\t<th>Prezzo</th>\n\t\t<th>Ordinato</th>\n\t\t<th>Consegnato</th></tr>";
            for(ArrayList<String> pizza : ordini) {
                if (pizza.get(3).equals("A")){
                    dataop = pizza.get(5).substring(0,10);
                    dataordine=pizza.get(8).substring(0,10);
                    table+="\n\t<tr>\n\t";
                    table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(7) +"0 &#8364</td>\n\t";
                    table+="\t<td>" + dataordine +"</td>\n\t";
                    table+="\t<td>" + dataop +"</td>\n\t";
                    table+="</tr>\n\t";
                    i++;
                }
                }            
            table+="</table>";
            }
            else{
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            table+="<hr>";
            table+="</div>";
            table+="<div id=\"cancellate\" hidden=\"true\"><h3 class=\"sottotitolo\">Ordini annullati</h3>";           
            if(ordini.get(i).get(3).equals("C")){
                table+="<table class=\"tblBordi\" id=\"conferma\">";
            table+="\n\t<tr>\n\t\t<th>Utente</th>\n\t\t<th>Pizza</th>\n\t\t<th>Quantità</th>\n\t\t<th>Prezzo</th>\n\t\t<th>Ordinato</th>\n\t\t<th>Cancellato</th>\n\t\t</tr>";
            for(ArrayList<String> pizza : ordini) {
                if (pizza.get(3).equals("C")){
                    dataop = pizza.get(5).substring(0,10);
                    dataordine=pizza.get(8).substring(0,10);
                    table+="\n\t<tr>\n\t";
                    table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(7) +"0 &#8364</td>\n\t";
                    table+="\t<td>" + dataordine +"</td>\n\t";
                    table+="\t<td>" + dataop +"</td>\n\t";
                    table+="</tr>\n\t";
                    i++;
                }
            }
            table+="</table>";
            }
            else{
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            table+="<hr>";
            table+="</div>";
            table+="<div id=\"inordine\" hidden=\"true\"><h3 class=\"sottotitolo\">Ordini non consegnati</h3>";
            if(ordini.get(i).get(3).equals("I")){
                table+="<table class=\"tblBordi\" id=\"conferma\">";
            table+="\n\t<tr>\n\t\t<th>Utente</th>\n\t\t<th>Pizza</th>\n\t\t<th>Quantità</th>\n\t\t<th>Prezzo</th>\n\t\t<th>Ordinato</th>\n\t\t<th>Richiesto</th>\n\t\t</tr>";
            for(ArrayList<String> pizza : ordini) {  
                if (pizza.get(3).equals("I")){
                    dataop = pizza.get(6).substring(0,10);
                    dataordine=pizza.get(8).substring(0,10);
                    table+="\n\t<tr>\n\t";
                    table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(7) +"0 &#8364</td>\n\t";
                    table+="\t<td>" + dataordine +"</td>\n\t";
                    table+="\t<td>" + dataop +"</td>\n\t";
                    table+="</tr>\n\t";
                }
                }
            table+="</table>";
            }
            else{
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            table+="</div>";
        }     
       return table;
    }
    
    public void setTabella(String nome, String quantita){
        if (nome.compareTo("")==0) tabella="";
        else{
            tabella=tabella+"<tr>";
            tabella=tabella+"<td>" + nome + "</td>";
            tabella=tabella+"<td>" + quantita + "</td>";
            tabella=tabella+"</tr>";
        }
    }

    public String getTabella(){
        return tabella;
    }

}
