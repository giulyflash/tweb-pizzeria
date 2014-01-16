/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaboration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myBeans.PizzeBean;
import myBeans.UtentiBean;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Model model = new Model();
            
            //Il controller chiede al model l'arraylist delle pizze per poi metterle nella request
            //la vista crea la tabella delle pizze attraverso un javaBean
           String action = request.getParameter("action");
           
           //visualizzazione index
           if(action==null||action.equals("home")) {
               HttpSession session = request.getSession();
               String utente = (String)session.getAttribute("username");
               String ruolo = (String)session.getAttribute("ruolo");
               if (utente==null) request.setAttribute("messaggio", "Fai il login per accedere ai servizi oppure registrati");
               else request.setAttribute ("messaggio", "Sei loggato con il ruolo di "+ruolo);
               request.setAttribute("pizze",model.getCatalogoPizze());
               RequestDispatcher dsp = getServletContext().getRequestDispatcher("/index.jsp");
               dsp.forward(request, response);
            } 
           
           //gestione utente           
           else if(action.equals("login")) {   
               String username = request.getParameter("txtUsername");
               String password = request.getParameter("txtPassword");
               
               String role = null;
               if(username!=null && password!=null) role = model.getUserRole(username, password);
               
               if(role==null) {
                   request.setAttribute("messaggio", "Errore nell'autenticazione");
                   RequestDispatcher dsp = getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
                   return;
               }
               else if (role.equals("")) {
                   request.setAttribute("messaggio", "Username o password errati!");
               }
               else {
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   session.setAttribute("ruolo", role);
                   request.setAttribute("messaggio", "Sei loggato con il ruolo di "+role);
               }
               request.setAttribute("pizze", model.getCatalogoPizze());
               RequestDispatcher dsp = getServletContext().getRequestDispatcher("/index.jsp");
               dsp.forward(request, response);
           }
           
           else if(action.equals("registrati")){
               request.setAttribute("messaggio", " ");
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/registrazione.jsp");
               dsp.forward(request, response);
           }
           
           
           else if (action.equals("confreg")){
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/registrazione.jsp");
               RequestDispatcher dsp2= getServletContext().getRequestDispatcher("/index.jsp");
               String username = request.getParameter("txtUsername");
               String pwd = request.getParameter("txtPassword");
               int ret=0;
               
               ret=model.insertUser(username, pwd);
               if (ret==0){
                   request.setAttribute("messaggio", "Utente già esistente");
                   dsp.forward(request, response);
               }
               else {
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   session.setAttribute("ruolo", "Cliente");
                   request.setAttribute("messaggio", "Sei loggato con il ruolo di Cliente");
                   request.setAttribute("pizze", model.getCatalogoPizze());
                   dsp2.forward(request,response);
               }
           }
           
           else if (action.equals("logout")){
               UtentiBean utenti = new UtentiBean();
               utenti.setUsername(null);
               utenti.setRuolo(null);
               HttpSession session = request.getSession();
               session.invalidate();
               request.setAttribute("messaggio", "Fai il login per accedere ai servizi oppure registrati");
               request.setAttribute("pizze",model.getCatalogoPizze());
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/index.jsp");
               dsp.forward(request, response);
           }
           
           //visualizzazione utente
           else if(action.equals("prenotazioni")){
               HttpSession session = request.getSession();
               String utente= (String)session.getAttribute("username");
               request.setAttribute("messaggio", "");
               request.setAttribute("ordini",model.getOrdini(utente));
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/visualizzaOrdini.jsp");
               dsp.forward(request, response); 
           }
           
           //storico ordinazioni
           else if (action.equals("storia")){              
               HttpSession session = request.getSession();
               if (session.getAttribute("ruolo").equals("Amministratore")){
                   request.setAttribute("ordini",model.getOrdiniTutti());
                   RequestDispatcher dsp= getServletContext().getRequestDispatcher("/storico.jsp");
                   dsp.forward(request, response);
               }
               else {
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   request.setAttribute("messaggio", "Non hai i diritti per accedere a quest'area");
                   RequestDispatcher dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
               }
           }
           
           //gestione prenotazioni
           else if(action.equals("ordina")){
               request.setAttribute("lista",model.getLista());
               request.setAttribute("messaggio", "");
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/newOrdina.jsp");
               dsp.forward(request, response);
           }
           
           else if (action.equals("validate")){
               try{
                    HttpSession session = request.getSession();                   
                    String utente= (String)session.getAttribute("username");
                    int rowCount = Integer.parseInt(request.getParameter("rowCount"));
                    String date=(String) request.getParameter("date");
                    RequestDispatcher dsp=null;
                    String nome = null;
                    String quantita = null;
                    String status;
                    boolean error=false;
                    DateFormat format;
                    format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    date = date.replace ("T", " ");
                    Timestamp time=new Timestamp(format.parse(date).getTime());
                    for (int i=0; i<rowCount; i++){
                        status=request.getParameter("chkStatus"+(i+1));
                        if (status!=null){
                            nome=request.getParameter("txtNome"+(i+1));
                            quantita=request.getParameter("txtNum"+(i+1));
                            double prezzo=Double.parseDouble(model.getPrezzo(nome))*Double.parseDouble(quantita);
                            int ret = model.insertPrenotazione(utente, nome, quantita, time, prezzo);
                            if (ret==0){
                                request.setAttribute("messaggio", "Prenotazione fallita, riprovare");
                                request.setAttribute("lista",model.getLista());
                                dsp= getServletContext().getRequestDispatcher("/newOrdina.jsp");
                                error=true;
                            }
                            else{
                                request.setAttribute("messaggio", "Prenotazione salvata correttamente");
                                request.setAttribute("ordini",model.getOrdini(utente));
                                dsp= getServletContext().getRequestDispatcher("/visualizzaOrdini.jsp");
                                dsp.forward(request, response);
                             }
                        }
                    }
                    
               }catch(Exception e){
                        request.setAttribute("messaggio", "Prenotazione fallita, riprovare");
                        RequestDispatcher dsp= getServletContext().getRequestDispatcher("/newOrdina.jsp");
                       }            
           }
           // gestione catalogo pizze
           else if (action.equals("modifica")){
               HttpSession session = request.getSession();
               if (session.getAttribute("ruolo").equals("Amministratore")){
                   request.setAttribute("lista",model.getLista());
                   request.setAttribute("messaggio", "");
                   RequestDispatcher dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
                   dsp.forward(request, response);
               }
               else {
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   request.setAttribute("messaggio", "Non hai i diritti per accedere a quest'area");
                   RequestDispatcher dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
               }
           }
           
           else if (action.equals("insert")){
               String nome=request.getParameter("txtNome");
               String ingredienti=request.getParameter("txtIngr");
               String prezzo= request.getParameter("txtPrezzo");
               boolean error=false;
               RequestDispatcher dsp=null;
               error=model.insertPizza(nome, ingredienti, prezzo);
               if (!error){
                   request.setAttribute("messaggio", "Pizza inserita correttamente");
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
               }
               else{
                   request.setAttribute("messaggio", "Errore, riprovare");
                   dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
                   dsp.forward(request, response);
               }
           }
           
           else if (action.equals("update")){
               String nome=request.getParameter("cmbPizza");
               String newnome=request.getParameter("txtNomeU");
               String ingredienti=request.getParameter("txtIngrU");
               String prezzo= request.getParameter("txtPrezzoU");
               boolean error=false;
               RequestDispatcher dsp=null;
               error=model.updatePizza(nome, newnome, ingredienti, prezzo);
               if (!error){
                   request.setAttribute("messaggio", "Pizza modificata correttamente");
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
               }
               else{
                   request.setAttribute("messaggio", "Errore, riprovare");
                   dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
                   dsp.forward(request, response);
               }
           }
           
           else if (action.equals("delete")){
               String nome=request.getParameter("cmbPizzaD");
               boolean error=false;
               RequestDispatcher dsp=null;
               error=model.deletePizza(nome);
               if (!error){
                   request.setAttribute("messaggio", "Pizza eliminata correttamente");
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   dsp.forward(request, response);
               }
               else{
                   request.setAttribute("messaggio", "Errore, riprovare");
                   dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
                   dsp.forward(request, response);
               }
           }

           
           else if (action.equals("import")){
               String xml = "<pizza>";
               String[] ret= null;
               String nome=request.getParameter("nome");
               ret=model.getAttributi(nome);
               if (ret!= null){
                   xml+="<nome>"+ret[2]+"</nome>";
                   xml+="<ingredienti>"+ret[0]+"</ingredienti>";
                   xml+="<prezzo>"+ret[1]+"</prezzo>";
               }
               xml+="</pizza>";
               response.getWriter().write(xml);
           }
           
           else if (action.equals("set")){
               String nome=(String) request.getAttribute("cmbPizza");
               ArrayList<String> pizza = new ArrayList<String>();
               
               pizza=model.getPizza(nome);
               if (pizza!=null){
                   request.setAttribute("txtIngrU", pizza.get(0));
                   request.setAttribute("txtPrezzoU", pizza.get(1));
               }
               else{
                   request.setAttribute("txtIngrU", "errore");
                   request.setAttribute("txtPrezzoU", "errore");
               }
               request.setAttribute("messaggio", "");
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
               dsp.forward(request, response);
           }
           
           //conferma degli ordini
           else if (action.equals("arrivo")){
               HttpSession session = request.getSession();
               String utente= (String)session.getAttribute("username");
               int rowCount = Integer.parseInt(request.getParameter("rowCount"));
               RequestDispatcher dsp=null;
               boolean error=false;
               for (int i=0; i<rowCount-1; i++){
                   String status=(String)request.getParameter("cmbOp"+(i+1));
                   if (status.equals("Consegna")){
                       String pizza= request.getParameter("txtPizza"+(i+1));
                       String dataord= request.getParameter("txtOrd"+(i+1));
                       error=model.updateOrder(utente,pizza,dataord);
                   }
                   else if (status.equals("Cancella")){
                       String pizza= request.getParameter("txtPizza"+(i+1));
                       String dataord= request.getParameter("txtOrd"+(i+1));
                       error=model.deleteOrder(utente,pizza,dataord);
                   }
               }
               if (!error){
                   request.setAttribute("messaggio", "Conferma eseguita correttamente");
                   dsp= getServletContext().getRequestDispatcher("/visualizzaOrdini.jsp");
                   request.setAttribute("ordini",model.getOrdini(utente));
                   dsp.forward(request, response);
               }
               else{
                   request.setAttribute("ordini",model.getOrdini(utente));
                   request.setAttribute("messaggio", "Errore nel salvataggio, riprovare");
                   dsp= getServletContext().getRequestDispatcher("/visualizzaOrdini.jsp");
                   dsp.forward(request, response);
               }
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            RequestDispatcher dsp = getServletContext().getRequestDispatcher("/error.jsp");
            dsp.forward(request, response);
          }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
