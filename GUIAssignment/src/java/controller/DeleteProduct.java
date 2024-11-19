/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ng Chong Jian
 */
public class DeleteProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int productid = Integer.parseInt(request.getParameter("productid"));
        String staffname = session.getAttribute("staffusername").toString();
        try {
            if (staffname.equals("Manager")) {
                try {
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    PreparedStatement stmt2 = null;
                    String host = "jdbc:derby://localhost:1527/OKClothingDB";
                    String user = "nbuser";
                    String password = "nbuser";
                    conn = DriverManager.getConnection(host, user, password);      //connect to database
                    String updateStr = "DELETE FROM PRODUCT WHERE PRODID=?";
                    stmt = conn.prepareStatement(updateStr);
                    stmt.setInt(1, productid);
                    stmt.executeUpdate();
                    String updateStr2 = "DELETE FROM PRODUCTTOTAL WHERE PRODID=?";
                    stmt2 = conn.prepareStatement(updateStr2);
                    stmt2.setInt(1, productid);
                    stmt2.executeUpdate();
                    response.sendRedirect("staffProduct.jsp");

                } catch (SQLException ex) {
                    response.sendRedirect("Error.jsp");
                }
            } else {
                response.sendRedirect("staffProduct.jsp");
            }

        } catch (Exception ex) {
            response.sendRedirect("Error.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
