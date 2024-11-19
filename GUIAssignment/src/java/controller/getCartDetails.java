/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import domain.Product;
import Entity.Cart;
import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

public class getCartDetails extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double discount;
        try {

            String customerusername = (String) session.getAttribute("username");
            Customer customer = (Customer) em.createNamedQuery("Customer.findByCustusername").setParameter("custusername", customerusername).getSingleResult();
            System.out.print(customer);
            int cid = customer.getCustid();
            Cart cart = (Cart) em.createNamedQuery("Cart.findByCusid").setParameter("cusid", cid).getSingleResult();
            System.out.print(cart);
            double subtotal = (double) session.getAttribute("subtotal");
            System.out.print(subtotal);
            int cartid = (int) session.getAttribute("cartid");

            ArrayList<domain.Cartdetail> cartList = new ArrayList<domain.Cartdetail>();
            ArrayList<Integer> prodid = new ArrayList<Integer>();
            ArrayList<Double> cartProdPrice = new ArrayList<Double>();

            ArrayList<Integer> cartProdQty = new ArrayList<Integer>();
            ArrayList<domain.Product> cartProdList = new ArrayList<domain.Product>();

            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";

            conn = DriverManager.getConnection(host, user, password);      //connect to database
            stmt = conn.prepareStatement("SELECT * FROM CARTDETAILS WHERE CARTID =?");
            stmt.setInt(1, cartid);
            rs = stmt.executeQuery();

            while (rs.next()) {
                prodid.add(rs.getInt(3));
                cartProdQty.add(rs.getInt(4));
            }

            for (int i = 0; i < prodid.size(); i++) {

                stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODID = ?");
                stmt.setInt(1, prodid.get(i));
                rs = stmt.executeQuery();

                if (rs.next()) {
                    cartProdList.add(new Product(
                            prodid.get(i),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getString(5),
                            null,
                            cartProdQty.get(i)));
                    double tempPrice = cartProdQty.get(i) * rs.getDouble(3);
                    cartProdPrice.add(tempPrice);

                }

            }

            session.setAttribute("Custusername", customerusername);
            session.setAttribute("customer", customer);
            session.setAttribute("cid", cid);
            session.setAttribute("cart", cart);
            session.setAttribute("cartProdPrice", cartProdPrice);
            session.setAttribute("cartid", cartid);

            double shippingFee;
            double total;
            if (subtotal >= 200) {
                shippingFee = 0;
                session.setAttribute("shippingFee", shippingFee);
            } else {
                shippingFee = 25;
                session.setAttribute("shippingFee", shippingFee);
            }
            if(subtotal >= 350){
                discount = subtotal * 0.10;

            }
            else{
                discount = subtotal * 0;
            }

            total = subtotal + shippingFee - discount;

            session.setAttribute("total", total);
            session.setAttribute("discount", discount);
            session.setAttribute("shippingFee", shippingFee);

            response.sendRedirect("shippingPage.jsp");
        } catch (Exception ex) {
            out.println(ex.getMessage());
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
