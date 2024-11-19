package controller;

import domain.Product;
import Entity.Cart;
import Entity.Customer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
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
 * @author Tang Qiao Ling
 */
public class getCartLists extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String customerusername = (String) session.getAttribute("username");

        try {
            Customer customer = (Customer) em.createNamedQuery("Customer.findByCustusername").setParameter("custusername", customerusername).getSingleResult();
            int cid = customer.getCustid();         
            ArrayList<Blob> img = new ArrayList<Blob>();
            ArrayList<String> base = new ArrayList<String>();
            ArrayList<Integer> prodid = new ArrayList<Integer>();
            ArrayList<Integer> cartProdQty = new ArrayList<Integer>();
            ArrayList<domain.Product> cartProdList = new ArrayList<domain.Product>();
            double subtotal = 0;

            //check if there is cart for this customer
            Query query = em.createNamedQuery("Cart.findByCusid").setParameter("cusid", cid);
            List<Cart> checkCartList = query.getResultList();
            List<List<Cart>> listOfLists = Arrays.asList(checkCartList);
            boolean isAnyEmpty = isAnyEmpty(listOfLists);

            if (isAnyEmpty) {
                
                //create cart, create cart details
                utx.begin();
                Cart newCart = new Cart(cid);
                em.persist(newCart);
                utx.commit();

            }
            
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";
            
            Cart cart = (Cart) em.createNamedQuery("Cart.findByCusid").setParameter("cusid", cid).getSingleResult();
            int cartid = cart.getCartid();
            System.out.println(cartid);
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

                    img.add(rs.getBlob(6));
                    Blob blob = img.get(i);
                    InputStream in = blob.getBinaryStream();
                    BufferedImage image = ImageIO.read(in);
                    in.close();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", baos);
                    baos.flush();
                    byte[] imageData = baos.toByteArray();
                    baos.close();                   
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    String imageSrc = "data:image/png;base64," + base64Image;
                    base.add(imageSrc);

                    subtotal += rs.getDouble(3) * cartProdQty.get(i);
                }
            }

            session.setAttribute("base", base);
            session.setAttribute("subtotal", subtotal);
            session.setAttribute("cartProdList", cartProdList);
            session.setAttribute("cartid", cartid);
            //session.setAttribute("prodid", prodid);
            response.sendRedirect("cart.jsp");

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
