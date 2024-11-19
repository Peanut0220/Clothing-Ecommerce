package controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UploadImage")
@MultipartConfig(maxFileSize = 16177216)

public class AddProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            String productName = request.getParameter("productName");
            Double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            String productDescription = request.getParameter("productDesciption");
            String productCategory = request.getParameter("productCategory");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Part filePart = request.getPart("productImage");

            InputStream inputStream = filePart.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(imageData);

            try {
                Connection conn = null;
                PreparedStatement stmt = null;
                PreparedStatement stmt2 = null;
                PreparedStatement stmt3 = null;
                ResultSet rs = null;
                ResultSet rs2 = null;

                String host = "jdbc:derby://localhost:1527/OKClothingDB";
                String user = "nbuser";
                String password = "nbuser";
                conn = DriverManager.getConnection(host, user, password); 
                stmt = conn.prepareStatement("SELECT * FROM PRODUCT");
                rs2 = stmt.executeQuery();
                
                while (rs2.next()) {
                   if(rs2.getString(2).equals(productName)){
                       request.setAttribute("error2", "Product Name has been taken, please try another one");
                       request.getRequestDispatcher("staffAddProduct.jsp").include(request, response);
                   }
                }
                

                     //connect to database
                stmt = conn.prepareStatement("INSERT INTO PRODUCT(PRODNAME,PRODPRICE,PRODDESC,CATEGORYNAME,PRODIMAGE,QUANTITY) VALUES(?,?,?,?,?,?)");
                stmt.setString(1, productName);
                stmt.setDouble(2, productPrice);
                stmt.setString(3, productDescription);
                stmt.setString(4, productCategory);
                stmt.setBlob(5, blob);
                stmt.setInt(6, quantity);
                stmt.executeUpdate();

                stmt.close();
                stmt3 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODNAME =?");
                stmt3.setString(1, productName);
                rs = stmt3.executeQuery();
                int prodid = 0;
                while (rs.next()) {
                    prodid = rs.getInt(1);
                }
                stmt3.close();
                
                stmt2 = conn.prepareStatement("INSERT INTO PRODUCTTOTAL(PRODTOTAL,PRODID) VALUES(?,?)");
                stmt2.setInt(1, 0);
                stmt2.setInt(2, prodid);
                stmt2.executeUpdate();
                stmt2.close();

            } catch (SQLException ex) {
                response.sendRedirect("Error.jsp");
            }

            response.sendRedirect("staffProduct.jsp");
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
