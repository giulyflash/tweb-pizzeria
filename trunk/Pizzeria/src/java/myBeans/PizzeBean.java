package myBeans;

import java.util.ArrayList;

/**
 *
 * @author st116680
 */
public class PizzeBean {
    private ArrayList<ArrayList<String>> pizze;

    public ArrayList<ArrayList<String>> getPizze() {
        return pizze;
    }

    public void setPizze(ArrayList<ArrayList<String>> pizze) {
        this.pizze = pizze;
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
}
