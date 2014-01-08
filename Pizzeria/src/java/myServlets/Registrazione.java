/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import collaboration.Model;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import database.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

public class Registrazione extends HttpServlet {

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
        Boolean isValid = false;
        String error = "";
        ServletContext ctx = getServletContext();
        String url = "jdbc:derby://localhost:1527/pizzeria";
        String user = "test";
        String password = "test";
        
        String username = request.getParameter("txtUsername");
        String pwd = request.getParameter("txtPassword");
        String ruolo = request.getParameter("cmbRuolo");
        
        if(username==null || pwd==null || ruolo==null)
            error="Parametri non validi!";
        else {
            try {
                Model model=new Model();
                Connection conn = DBUtil.createConnection(url, user, password);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM utenti WHERE nome='" + username + "'");
                if (rs.next()){
                    error="Username già presente!";
                }
                else{
                    st.execute("INSERT INTO utenti VALUES ('" + username + "','" + pwd + "','" + ruolo + "')");
                    isValid=true;
                }
                rs.close();
                conn.close();
                request.setAttribute("pizze", model.getCatalogoPizze());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if(isValid==true) {
            HttpSession session = request.getSession();
            session.setAttribute("user", request.getParameter("user"));
            request.setAttribute ("messaggio", "Registrazione avvenuta!");
            RequestDispatcher dsp = ctx.getRequestDispatcher("/index.jsp");
            dsp.forward(request, response);
        } else {
            request.setAttribute("error", error);
            RequestDispatcher dsp = ctx.getRequestDispatcher("/registrazione.jsp");
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
