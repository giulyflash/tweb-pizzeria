/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlets;

import database.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

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
        String url = (String)ctx.getAttribute("url");
        String user = (String)ctx.getAttribute("user");
        String password = (String)ctx.getAttribute("userPassword");
        
        
        try {
            Connection conn = DBUtil.createConnection(url, user, password);
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM utenti where username=?");
            stm.setString(1, request.getParameter("txtUsername"));
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()) {
                String pwd = request.getParameter("txtPassword");
                if(pwd.equals(rs.getString("password")))
                    isValid=true;
                else error="Username o password errati!";
            }
            else error="Username o password errati!";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        if(isValid==true) {
            HttpSession session = request.getSession();
            session.setAttribute("user", request.getParameter("user"));            
        } else {
            if(error.equals("")) error="Si è verificato un errore!";
            request.setAttribute("error", error);
        }
        
        RequestDispatcher dsp = ctx.getRequestDispatcher("/registrazione.jsp");
        dsp.forward(request, response);
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
