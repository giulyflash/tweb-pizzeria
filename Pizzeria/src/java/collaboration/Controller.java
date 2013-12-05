/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaboration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myBeans.UtentiBean;

/**
 *
 * @author st116680
 */
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
           
           if(action==null) {
               UtentiBean utenti = new UtentiBean();
               if (utenti.getUsername()==null) request.setAttribute("messaggio", "Fai il login per accedere ai servizi oppure registrati");
               else request.setAttribute ("messaggio", "Sei loggato come '<%= session.getAttribute(\"username\")%>', con il ruolo di '<%= session.getAttribute(\"ruolo\")%>'");
               request.setAttribute("pizze",model.getCatalogoPizze());
               RequestDispatcher dsp = getServletContext().getRequestDispatcher("/index.jsp");
               dsp.forward(request, response);
            } 
           
           else if(action.equals("login")) {   
               String username = request.getParameter("txtUsername");
               String password = request.getParameter("txtPassword");
               
               String role = null;
               if(username!=null && password!=null) role = model.getUserRole(username, password);
               
               if(role==null) {
                   request.setAttribute("error", "Errore nell'autenticazione");
                   RequestDispatcher dsp = getServletContext().getRequestDispatcher("/error.jsp");
                   dsp.forward(request, response);
                   return;
               }
               else if (role.equals("")) {
                   request.setAttribute("error", "Username o password errati!");
               }
               else {
                   HttpSession session = request.getSession();
                   session.setAttribute("username", username);
                   session.setAttribute("ruolo", role);
                   request.setAttribute("messaggio", "Benvenuto, " + username);
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
           
           else if(action.equals("ordina")){
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/ordinaPizza.jsp");
               dsp.forward(request, response);
           }
           
           else if(action.equals("prenotazioni")){
               RequestDispatcher dsp= getServletContext().getRequestDispatcher("/prenota.jsp");
               dsp.forward(request, response); 
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
