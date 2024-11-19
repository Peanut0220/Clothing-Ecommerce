/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import domain.Cartdetail;
import Entity.Cart;
import Entity.Cartdetails;
import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tang Qiao Ling, Wong Bao Yi
 */
public class AddtoCart extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String host = "jdbc:derby://localhost:1527/OKClothingDB";
        String user = "nbuser";
        String password = "nbuser";
        try {
            HttpSession session = request.getSession();
            int productid = Integer.parseInt(request.getParameter("productid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String customerusername = (String) session.getAttribute("username");
            Customer customer = (Customer) em.createNamedQuery("Customer.findByCustusername").setParameter("custusername", customerusername).getSingleResult();
            int cid = customer.getCustid();
            int prodQty = 0;

            conn = DriverManager.getConnection(host, user, password);      //connect to database
            stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODID =?");
            stmt.setInt(1, productid);
            rs = stmt.executeQuery();

            //int i = 0;
            while (rs.next()) {

                String prodName = rs.getString(2);
                double prodprice = rs.getDouble(3);
                String prodDesc = rs.getString(4);
                String categoryName = rs.getString(5);
                prodQty = rs.getInt(7);

                Entity.Product products = new Entity.Product(productid, prodName, prodprice, prodDesc, categoryName);

                session.setAttribute("products", products);
                session.setAttribute("prodQty", prodQty);
            }

            //if db product is empty, cannot add to cart
            if (prodQty > 0) {
                Query query = em.createNamedQuery("Cart.findByCusid").setParameter("cusid", cid);
                List<Cart> cartList = query.getResultList();
                List<List<Cart>> listOfLists = Arrays.asList(cartList);
                boolean isAnyEmpty = isAnyEmpty(listOfLists);

                //if the db doesn't have this customer's cart record, it will create a cart and cart details                
                if (isAnyEmpty) {

                    //create cart, create cart details
                    utx.begin();
                    Cart cart = new Cart(cid);
                    em.persist(cart);
                    utx.commit();

                    Cartdetails cartdetailsList = new Cartdetails(cart, productid, quantity);
                    utx.begin();
                    em.persist(cartdetailsList);
                    utx.commit();

                    session.setAttribute("prodCartid", cart.getCartid());

                } else {
                    int comparedQty = 0;
                    int cartdetailsQty = 0;
                    Cart cart = (Cart) em.createNamedQuery("Cart.findByCusid").setParameter("cusid", cid).getSingleResult();
                    stmt = conn.prepareStatement("SELECT * FROM CARTDETAILS WHERE CARTID = ? AND PRODID = ?");
                    stmt.setInt(1, cart.getCartid());
                    stmt.setInt(2, productid);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        cartdetailsQty = rs.getInt(4);
                    }

                    comparedQty = cartdetailsQty + quantity;

                    //if input product qty or quantity add with cart prod qty larger than db product, cannot add to cart
                    if (quantity <= prodQty && comparedQty <= prodQty) {

                        //create cart details                    
                        Cartdetails cart2 = new Cartdetails(cart, productid, quantity);
                        Cartdetail checkCartProduct = null;

                        stmt = conn.prepareStatement("SELECT * FROM CARTDETAILS WHERE PRODID = ? AND CARTID = ?");
                        stmt.setInt(1, productid);
                        stmt.setInt(2, cart.getCartid());
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            checkCartProduct = new Cartdetail(rs.getInt(1), rs.getInt(4));
                        }

                        //if cart consists the same product, then will only update
                        if (checkCartProduct == null) {
                            utx.begin();
                            em.persist(cart2);
                            utx.commit();

                        } else {
                            int cartdetailsid = checkCartProduct.getCartdetailsid();
                            int tempQTY = checkCartProduct.getQuantity();
                            int newQuantity = tempQTY + quantity;

                            stmt = conn.prepareStatement("UPDATE CARTDETAILS " + "SET QUANTITY = ? " + "WHERE CARTDETAILSID = ?");
                            stmt.setInt(1, newQuantity);
                            stmt.setInt(2, cartdetailsid);
                            stmt.executeUpdate();
                        }

                        //update product (use domain)
//                comparedQty -= quantity;
//
//                String sql = "UPDATE PRODUCT " + "SET QUANTITY = ? " + "WHERE PRODID = ?";
//                stmt = conn.prepareStatement(sql);
//                stmt.setInt(1, comparedQty);
//                stmt.setInt(2, productid);
//                stmt.executeUpdate();
                    } else {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Quantity unavailable! Please contact the staff.');");
                        out.println("location='product.jsp';");
                        out.println("</script>");
                    }
                }
                
                //add succesful message
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Cart added successfully');");
                out.println("location='getCartLists';");
                out.println("</script>");

            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('OUT OF STOCK!');");
                out.println("location='product.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            out.println(e);
            //e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (Exception ignore) {
            }
        }
    }
    //check whether the cart consists of the login customer

    public static boolean isAnyEmpty(List<List<Cart>> listOfLists) {
        return listOfLists.stream()
                .anyMatch(x -> x == null || x.isEmpty());
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
