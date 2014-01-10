package myBeans;

import java.util.ArrayList;

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
                table+="\t<td>" + pizza.get(2) +" &#8364</td>\n\t";
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
        double prezzo=0;
        String dataconsegna = "";
        String dataordine = "";
        table+="<div><h3 class=\"sottotitolo\">Ordini consegnati</h3></div>";
        if(ordini!=null) {
            if (ordini.get(0).get(2).equals("false")){
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            else{
                table+= "<table class=\"tblBordi\" id=\"table\">";
                table+="\n\t<tr>\n<th>Pizza</th>\n<th>Quantità</th>\n<th>Prezzo</th>\n<th>Consegnato</th></tr>";
                for(ArrayList<String> pizza : ordini) {
                    if(pizza.get(2).equals("true"))
                    {
                        prezzo=Double.parseDouble(pizza.get(1))*Double.parseDouble(pizza.get(3));
                        dataconsegna = pizza.get(4).substring(0,10);
                        table+="\n\t<tr>\n\t";
                        table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                        table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                        table+="\t<td>" + prezzo +"0 &#8364</td>\n\t";
                        table+="\t<td>" + dataconsegna +"</td>\n\t";
                        table+="</tr>\n\t";
                    }
                    j++;
                }
                table+="</table>";
            }
            index=ordini.size();
            table+="<hr>";
            table+="<div><h3 class=\"sottotitolo\">Ordini non consegnati</h3></div>";
            if (index==j){
                table+="<div class=\"divMain\"><h4 class=\"error\">Non sono stati trovati ordini</h3></div>";
            }
            else{
                table+="<form id=\"Ordini\" name=\"Ordini\" action=\"Controller\" method=\"POST\">";
                table+="<table class=\"tblBordi\" id=\"conferma\">";
                table+="\n\t<tr>\n<th>Pizza</th>\n<th>Quantità</th>\n<th>Prezzo</th>\n<th>Consegnato</th><th>Data Ordine</th></tr>";
                int i=1;
                for(ArrayList<String> pizza : ordini) {
                    if(pizza.get(2).equals("false"))
                    {
                        prezzo=Double.parseDouble(pizza.get(1))*Double.parseDouble(pizza.get(3));
                        dataordine = pizza.get(4).substring(0,10);
                        table+="\n\t<tr>\n\t";
                        table+="\t<td><input type=\"textbox\" class=\"textbox\" name=\"txtPizza"+i+"\" id=\"txtPizza"+i+"\" value=\""+pizza.get(0)+"\"></td>\n\t";
                        table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                        table+="\t<td>" + prezzo +"0 &#8364</td>\n\t";
                        table+="\t<td><input type=\"checkbox\" name=\"chkOrdine"+i+"\" id=\"chkOrdine"+i+"\"></td>\n\t";
                        table+="\t<td><input type=\"textbox\" class=\"textbox\" name=\"txtOrd"+i+"\" id=\"txtOrd"+i+"\" value=\""+dataordine+"\"></td>\n\t";
                        table+="</tr>\n\t";
                        i++;
                    }               
                }
                table+="</table>";
                table+="<div class=\"divMain\">";
                table+="\t<td><input type=\"button\" id=\"btnConfOrdini\" name=\"btnConfOrdini\" value=\"Conferma lista ordini\" onclick=\"confermaOrdini()\"/></td>";
            }
        }      
       return table;
    }
    
    public String getTabellaOrdiniCompleta() {
        String table ="<div><h3 class=\"sottotitolo\">Ordini consegnati</h3></div>";
        table+= "<table class=\"tblBordi\">";
        double prezzo=0;
        String dataconsegna = "";
        table+="\n\t<tr>\n\t\t<th>Utente</th>\n\t\t<th>Pizza</th>\n\t\t<th>Quantità</th>\n\t\t<th>Prezzo</th>\n\t\t<th>Data Consegna</th></tr>";
        if(ordini!=null) {
            for(ArrayList<String> pizza : ordini) {
                if(pizza.get(3).equals("true"))
                {
                    prezzo=Double.parseDouble(pizza.get(2))*Double.parseDouble(pizza.get(4));
                    dataconsegna = pizza.get(5).substring(0,10);
                    table+="\n\t<tr>\n\t";
                    table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                    table+="\t<td>" + prezzo +"0 &#8364</td>\n\t";
                    table+="\t<td>" + dataconsegna +"</td>\n\t";
                    table+="</tr>\n\t";
                }
            }
            table+="</table>";
            table+="<form id=\"Ordini\" name=\"Ordini\" action=\"Controller\" method=\"POST\">";
            table+="<hr>";
            table+="<div><h3 class=\"sottotitolo\">Ordini non consegnati</h3></div>";
            table+="<table class=\"tblBordi\" id=\"conferma\">";
            table+="\n\t<tr>\n\t\t<th>Utente</th>\n\t\t<th>Pizza</th>\n\t\t<th>Quantità</th>\n\t\t<th>Prezzo</th>\n\t\t</tr>";
            int i=1;
            for(ArrayList<String> pizza : ordini) {
                if(pizza.get(3).equals("false"))
                {
                    prezzo=Double.parseDouble(pizza.get(2))*Double.parseDouble(pizza.get(4));
                    table+="\n\t<tr>\n\t";
                    table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                    table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                    table+="\t<td>" + prezzo +"0 &#8364</td>\n\t";
                    table+="</tr>\n\t";
                }
                i++;
            }
        }
       table+="</table>";
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
