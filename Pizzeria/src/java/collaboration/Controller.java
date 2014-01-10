/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaboration;

import java.io.IOException;
import java.io.PrintWriter;
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
               request.setAttribute("error", " ");
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
                   request.setAttribute("messaggio", "Registrazione fallita, riprovare");
                   dsp.forward(request, response);
               }
               else {
                   request.setAttribute("messaggio", "Registrazione avvenuta correttamente");
                   request.setAttribute("pizze",model.getCatalogoPizze());
                   dsp2.forward(request, response);
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
           
           //visualizzaazione utente
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
               request.setAttribute("ordini",model.getOrdiniTutti());
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/storico.jsp");
               dsp.forward(request, response);
           }
           
           //gestione prenotazioni
           else if(action.equals("ordina")){
               request.setAttribute("lista",model.getLista());
               request.setAttribute("messaggio", "");
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/newOrdina.jsp");
               dsp.forward(request, response);
           }
           
           else if (action.equals("validate")){
               HttpSession session = request.getSession();
               String utente= (String)session.getAttribute("username");
               int rowCount = Integer.parseInt(request.getParameter("rowCount"));
               RequestDispatcher dsp=null;
               String nome = null;
               String quantita = null;
               String status;
               boolean error=false;
               for (int i=0; i<rowCount; i++){
                   status=request.getParameter("chkStatus"+(i+1));
                   if (status!=null){
                       nome=request.getParameter("txtNome"+(i+1));
                       quantita=request.getParameter("txtNum"+(i+1));
                       int ret = model.insertPrenotazione(utente, nome, quantita);
                       if (ret==0){
                           request.setAttribute("messaggio", "Prenotazione fallita, riprovare");
                           dsp= getServletContext().getRequestDispatcher("/newOrdina.jsp");
                           error=true;
                       }
                   }
               }
               if (!error){
                   request.setAttribute("messaggio", "Prenotazione salvata correttamente");
                   dsp= getServletContext().getRequestDispatcher("/index.jsp");
               }
               request.setAttribute("pizze",model.getCatalogoPizze());
               dsp.forward(request, response);
           }
           
           // gestione catalogo pizze
           else if (action.equals("modifica")){
               request.setAttribute("lista",model.getLista());
               request.setAttribute("messaggio", "");
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/modificaPizze.jsp");
               dsp.forward(request, response);
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
               for (int i=0; i<rowCount; i++){
                   String status=request.getParameter("chkOrdine"+(i+1));
                   if (status!=null){
                       String pizza= request.getParameter("txtPizza"+(i+1));
                       String dataord= request.getParameter("txtOrd"+(i+1));
                       error=model.updateOrder(utente,pizza,dataord);
                   }
               }
               if (!error){
                   request.setAttribute("messaggio", "Conferma eseguita correttamente");
                   dsp= getServletContext().getRequestDispatcher("/index.jsp");
                   request.setAttribute("pizze",model.getCatalogoPizze());
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
