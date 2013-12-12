package myBeans;

import java.util.ArrayList;

/**
 *
 * @author st116680
 */
public class PizzeBean {
    private ArrayList<ArrayList<String>> pizze;
    private ArrayList<String> lista;
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
    
    public String getTabellaPizze() {
        String table = "<table class=\"tblBordi\">";
        table+="\n\t<tr>\n\t\t<th>Pizza</th>\n\t\t<th>Ingredienti</th>\n\t\t<th>Prezzo</th>\n\t</tr>";
        if(pizze!=null) {
            for(ArrayList<String> pizza : pizze) {
                table+="\n\t<tr>\n\t";
                table+="\t<td>" + pizza.get(0) +"</td>\n\t";
                table+="\t<td>" + pizza.get(1) +"</td>\n\t";
                table+="\t<td>" + pizza.get(2) +"</td>\n\t";
                table+="</tr>\n\t";
            }
        }
        table+="</table>";
        return table;
    }
    
    public String getListaPizze(){
        String ret="";
        ret="<option value=\"pizza\">Scegli la pizza</option>";
        if(lista!=null) {
            for(String elenco : lista) {
                ret+="<option value=\""+elenco+"\">"+elenco+"</option>";
            }
        }
        return ret;
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
