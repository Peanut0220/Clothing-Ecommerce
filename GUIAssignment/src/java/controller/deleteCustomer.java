package controller;

import domain.Cartdetail;
import domain.Carts;
import domain.Customers;
import domain.Orders;
import domain.Orderdetails;
import Entity.Cart;
import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tang Qiao Ling
 */
public class deleteCustomer extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String host = "jdbc:derby://localhost:1527/OKClothingDB";
        String user = "nbuser";
        String password = "nbuser";
        ArrayList<Integer> orderid = new ArrayList<Integer>();
        ArrayList<domain.Orders> orderList = new ArrayList<domain.Orders>();
        ArrayList<domain.Orderdetails> orderDetailList = new ArrayList<domain.Orderdetails>();
        ArrayList<domain.Cartdetail> cartDetailList = new ArrayList<domain.Cartdetail>();
        Carts cart = null;

        try {
            int custid = Integer.parseInt(request.getParameter("id"));
            conn = DriverManager.getConnection(host, user, password);      //connect to database
            stmt = conn.prepareStatement("SELECT * FROM ORDERS " + "WHERE CUSTID = ?");
            stmt.setInt(1, custid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                orderid.add(rs.getInt(1));
            }

            for (int i = 0; i < orderid.size(); i++) {

                stmt = conn.prepareStatement("SELECT * FROM ORDERS WHERE ORDERID = ?");
                stmt.setInt(1, orderid.get(i));
                rs = stmt.executeQuery();

                if (rs.next()) {
                    orderList.add(new Orders(rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8)));
                }

                stmt = conn.prepareStatement("SELECT * FROM ORDERDETAILS WHERE ORDERID = ?");
                stmt.setInt(1, orderid.get(i));
                rs = stmt.executeQuery();

                while (rs.next()) {
                    orderDetailList.add(new Orderdetails(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
                }

                if (!orderDetailList.isEmpty()) {
                    stmt = conn.prepareStatement("DELETE FROM ORDERDETAILS " + "WHERE ORDERID = ?");
                    stmt.setInt(1, orderid.get(i));
                    stmt.executeUpdate();
                }

                stmt = conn.prepareStatement("DELETE FROM ORDERS " + "WHERE ORDERID = ?");
                stmt.setInt(1, orderid.get(i));
                stmt.executeUpdate();
            }

            stmt = conn.prepareStatement("SELECT * FROM CART " + "WHERE CUSID = ?");
            stmt.setInt(1, custid);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cart = new Carts(rs.getInt(1));
            }
            
            if (cart != null) {                
                stmt = conn.prepareStatement("SELECT * FROM CARTDETAILS WHERE CARTID = ?");
                stmt.setInt(1, cart.getCartid());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    cartDetailList.add(new Cartdetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
                }
               
                if (!cartDetailList.isEmpty()) {                    
                    conn = DriverManager.getConnection(host, user, password);      //connect to database
                    stmt = conn.prepareStatement("DELETE FROM CARTDETAILS " + "WHERE CARTID = ?");
                    stmt.setInt(1, cart.getCartid());
                    stmt.executeUpdate();
                }

                conn = DriverManager.getConnection(host, user, password);      //connect to database
                stmt = conn.prepareStatement("DELETE FROM CART " + "WHERE CARTID = ?");
                stmt.setInt(1, cart.getCartid());
                stmt.executeUpdate();
            }

            stmt = conn.prepareStatement("DELETE FROM CUSTOMER " + "WHERE CUSTID = ?");
            stmt.setInt(1, custid);
            stmt.executeUpdate();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Delete successfully!');");
            out.println("location='customer.jsp';");
            out.println("</script>");

        } catch (Exception ex) {
            out.println(ex);
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
