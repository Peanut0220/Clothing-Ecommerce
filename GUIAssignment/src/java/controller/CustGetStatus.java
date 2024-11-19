/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustGetStatus extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";
            String username = session.getAttribute("username").toString();
            int userid=0;

            ArrayList<domain.Orders> orderStatusList = new ArrayList<domain.Orders>();
            conn = DriverManager.getConnection(host, user, password); 
            stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE CUSTUSERNAME = ?");
            stmt.setString(1,username);
            rs2 = stmt.executeQuery();
            while (rs2.next()) {
                userid = rs2.getInt(1);
            }

                 //connect to database
            stmt = conn.prepareStatement("SELECT * FROM ORDERS WHERE CUSTID = ?");
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                orderStatusList.add(new domain.Orders(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getInt(10)
                ));
            }

            session.setAttribute("orderStatusList", orderStatusList);
            response.sendRedirect("ViewOrderStatus.jsp");
        } catch (SQLException ex) {
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
