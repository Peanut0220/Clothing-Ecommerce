/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import domain.Product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSalesReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";
            PreparedStatement stmt2 = null;
            ResultSet rs2 = null;
            
            ArrayList<domain.ProductTotal> prodtotalList = new ArrayList<domain.ProductTotal>();
            ArrayList<domain.Product> prodList = new ArrayList<domain.Product>();
            
            
            conn = DriverManager.getConnection(host, user, password);      //connect to database
            stmt = conn.prepareStatement("SELECT * FROM PRODUCTTOTAL ORDER BY PRODTOTAL DESC");
            stmt2 = conn.prepareStatement("SELECT * FROM PRODUCT");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                prodtotalList.add(new domain.ProductTotal(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)));
            }
            
            rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                prodList.add(new Product(
                        rs2.getInt(1),
                        rs2.getString(2),
                        rs2.getDouble(3),
                        rs2.getString(4),
                        rs2.getString(5),
                        null,
                        rs2.getInt(7)));
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("prodtotalList", prodtotalList);
            session.setAttribute("prodList", prodList);
            response.sendRedirect("SalesReport.jsp");
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
