package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tang Qiao Ling
 */
public class deleteCartProd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        int prodid = Integer.parseInt(request.getParameter("id"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int cartid = (int) session.getAttribute("cartid");
        int prodQty = 0;
        int oriProdQty = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";
            conn = DriverManager.getConnection(host, user, password);      //connect to database
            
            stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODID = ?");
            stmt.setInt(1, prodid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                oriProdQty = rs.getInt(7);
            }

//            prodQty = oriProdQty + qty;
//
//            stmt = conn.prepareStatement("UPDATE PRODUCT " + "SET QUANTITY = ? " + "WHERE PRODID = ?");
//            stmt.setInt(1, prodQty);
//            stmt.setInt(2, prodid);
//            stmt.executeUpdate();

            stmt = conn.prepareStatement("DELETE FROM CARTDETAILS " + "WHERE PRODID = ? AND CARTID = ?");
            stmt.setInt(1, prodid);
            stmt.setInt(2, cartid);
            stmt.executeUpdate();

            response.sendRedirect("getCartLists");
        } catch (Exception e) {
            out.println(e);
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (Exception ignore) {
            }
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
