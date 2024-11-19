/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Ng Chong Jian
 */
@MultipartConfig(maxFileSize = 16177216)
public class UpdateProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int productid = Integer.parseInt(request.getParameter("productid"));
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
                ResultSet rs = null;
                String host = "jdbc:derby://localhost:1527/OKClothingDB";
                String user = "nbuser";
                String password = "nbuser";
                conn = DriverManager.getConnection(host, user, password); //connect to database
                stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE PRODNAME = ?");
                stmt.setString(1, productName);
                rs = stmt.executeQuery();

                int found = 0;

                while (rs.next()) {
                    if (rs.getString(2).equals(productName)) {
                        found = 1;

                    }
                }

                if (found == 1) {
                    request.setAttribute("error", "Product Name has been taken.");
                    request.getRequestDispatcher("staffEditProduct.jsp").include(request, response);
                } else {

                    String updateStr = "UPDATE PRODUCT SET prodName = ?, prodPrice= ?, prodDesc=?,categoryName=?,prodImage=?,quantity=? WHERE PRODID=?";
                    stmt = conn.prepareStatement(updateStr);
                    stmt.setString(1, productName);
                    stmt.setDouble(2, productPrice);
                    stmt.setString(3, productDescription);
                    stmt.setString(4, productCategory);
                    stmt.setBlob(5, blob);
                    stmt.setInt(6, quantity);
                    stmt.setInt(7, productid);
                    stmt.executeUpdate();
                }

            } catch (SQLException ex) {
                System.out.print(ex.getMessage());
            }
            response.sendRedirect("staffProduct.jsp");
        } catch (Exception ex) {
            out.print(ex);
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
