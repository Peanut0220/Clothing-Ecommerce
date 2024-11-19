/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import domain.Product;
import Entity.Customer;
import Entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author snowc
 */
public class addOrders extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        PreparedStatement stmt4 = null;
        PreparedStatement stmt5 = null;
        PreparedStatement stmt6 = null;
        PreparedStatement stmt7 = null;

        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        ResultSet rs4 = null;

        try {
            String customerusername = (String) session.getAttribute("username");
            Customer customer = (Customer) em.createNamedQuery("Customer.findByCustusername").setParameter("custusername", customerusername).getSingleResult();
            System.out.print(customer);
            int cartid = (int) session.getAttribute("cartid");

            String address = request.getParameter("address");
            String paymentM = request.getParameter("payment");
            session.setAttribute("paymentM", paymentM);
            System.out.print(paymentM);
            if ("CARD".equals(paymentM)) {
                String cardName = request.getParameter("cardName");
                String cardNum = request.getParameter("cardNum");
                String expDate = request.getParameter("expDate");
                String cvv = request.getParameter("cvv");
                session.setAttribute("cardName", cardName);
                session.setAttribute("cardNum", cardNum);
                session.setAttribute("expDate", expDate);
                session.setAttribute("cvv", cvv);
                System.out.print(cardName);
                System.out.print(cardNum);
                System.out.print(expDate);
                System.out.print(cvv);
            }

            ArrayList<domain.Cartdetail> cartList = new ArrayList<domain.Cartdetail>();
            ArrayList<Integer> prodid = new ArrayList<Integer>();
            ArrayList<Integer> cartProdQty = new ArrayList<Integer>();
            ArrayList<domain.Product> cartProdList = new ArrayList<domain.Product>();

            double subtotal = (double) session.getAttribute("subtotal");
            double shippingFee = (double) session.getAttribute("shippingFee");
            double discount = (double) session.getAttribute("discount");
            session.setAttribute("discount", discount);
            double total = (double) session.getAttribute("total");
            double subt = subtotal;
            Date date = new Date();
            String orderStatus = request.getParameter("orderStatus");

            session.setAttribute("paymentM", paymentM);

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
            stmt.close();
            rs.close();
            for (int i = 0; i < prodid.size(); i++) {

                stmt2 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODID = ?");
                stmt2.setInt(1, prodid.get(i));
                rs2 = stmt2.executeQuery();

                if (rs2.next()) {
                    cartProdList.add(new Product(
                            prodid.get(i),
                            rs2.getString(2),
                            rs2.getDouble(3),
                            rs2.getString(4),
                            rs2.getString(5),
                            null,
                            cartProdQty.get(i)));

                }

            }

            LocalDate currentDate = LocalDate.now();
            System.out.print(currentDate);
            LocalDate oneWeekLater = currentDate.plusWeeks(2);
            System.out.print(oneWeekLater);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date date1 = Date.from(oneWeekLater.atStartOfDay(defaultZoneId).toInstant());

            request.setAttribute("date1", date1);
            System.out.println(request.getAttribute("date1"));

            Random rand = new Random();
            int trackingNum = rand.nextInt(1000000000) + 1000000000;
            System.out.println(trackingNum);

            utx.begin();
            Orders order = new Orders(date, address, orderStatus, shippingFee, subt, total, date1, trackingNum, customer);
            em.persist(order);
            utx.commit();

            session.setAttribute("order", order);

            for (int i = 0; i < prodid.size(); i++) {
                System.out.println(cartProdQty.get(i));
                stmt3 = conn.prepareStatement("INSERT INTO ORDERDETAILS (QUANTITY,ORDERID,PRODID) VALUES(?,?,?)");

                stmt3.setInt(1, cartProdQty.get(i));

                stmt3.setInt(2, order.getOrderid());
                stmt3.setInt(3, prodid.get(i));
                stmt3.executeUpdate();

                stmt7 = conn.prepareStatement("SELECT * FROM PRODUCTTOTAL WHERE PRODID = ?");
                stmt7.setInt(1, prodid.get(i));
                rs3 = stmt7.executeQuery();
                int quantity1 = 0;
                while (rs3.next()) {
                    quantity1 = rs3.getInt(1);
                    System.out.println(quantity1 + "ok");
                }
                quantity1 = quantity1 + cartProdQty.get(i);
                System.out.println(quantity1 + "ok1");
                stmt7 = conn.prepareStatement("UPDATE PRODUCTTOTAL SET PRODTOTAL =? WHERE PRODID = ?");
                stmt7.setInt(1, quantity1);
                stmt7.setInt(2, prodid.get(i));

                stmt7.executeUpdate();
            }

            for (int i = 0; i < prodid.size(); i++) {

                stmt4 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODID = ?");
                stmt4.setInt(1, prodid.get(i));
                rs4 = stmt4.executeQuery();

                if (rs4.next()) {
                    cartProdList.add(new Product(
                            prodid.get(i),
                            rs4.getString(2),
                            rs4.getDouble(3),
                            rs4.getString(4),
                            rs4.getString(5),
                            null,
                            cartProdQty.get(i)));

                }
                int currentQuantity = rs4.getInt("quantity");
                System.out.print(currentQuantity);
                int newQuantity = currentQuantity - cartProdQty.get(i);
                System.out.print(newQuantity);

                stmt4.close();
                rs4.close();

                String sql = "UPDATE PRODUCT " + "SET QUANTITY = ? " + "WHERE PRODID = ?";
                stmt5 = conn.prepareStatement(sql);
                stmt5.setInt(1, newQuantity);
                stmt5.setInt(2, prodid.get(i));
                stmt5.executeUpdate();

                String deletesql = "DELETE FROM CARTDETAILS WHERE CARTID = ?";
                stmt5 = conn.prepareStatement(deletesql);
                stmt5.setInt(1, cartid);
                stmt5.executeUpdate();
                stmt5.close();
            }
            response.sendRedirect("orderComplete.jsp");
        } catch (Exception ex) {
            out.println(ex.getMessage());
        } finally {
            try {
                stmt.close();
                stmt2.close();
                stmt3.close();
                stmt4.close();
                stmt5.close();
                stmt6.close();
                rs.close();
                rs2.close();
                rs4.close();
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
