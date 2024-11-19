/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import domain.Product;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class SearchResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String result = request.getParameter("searchResult"); 
        String category = request.getParameter("category");
        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";
            ArrayList<Blob> img = new ArrayList<Blob>();
            ArrayList<String> base = new ArrayList<String>();
            ArrayList<BufferedImage> buffer = new ArrayList<BufferedImage>();
            ArrayList<domain.Product> productList = new ArrayList<domain.Product>();
            conn = DriverManager.getConnection(host, user, password);      //connect to database
            if (category != null) {
                stmt = conn.prepareStatement("SELECT * FROM PRODUCT WHERE CATEGORYNAME = ?");
                stmt.setString(1, category);
                rs = stmt.executeQuery();
                int i = 0;
                while (rs.next()) {
                    productList.add(new Product(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getString(5),
                            null,
                            rs.getInt(7)));
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
                    i++;
                }
            } else {
                if (result != null) {
                    stmt = conn.prepareStatement("SELECT * FROM PRODUCT");
                    rs = stmt.executeQuery();
                    int i = 0;  
                    while (rs.next()) {
                        
                        int found = 0;
                        
                        if (rs.getString(2).toUpperCase().contains(result.toUpperCase())) {
                            found = 1;
                        } 
                        if (found == 1) {
                            productList.add(new Product(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getDouble(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    null,
                                    rs.getInt(7)));
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
                            i++;
                        }
                    }
                } else {
                    stmt = conn.prepareStatement("SELECT * FROM PRODUCT");
                    rs = stmt.executeQuery();
                    int i = 0;
                    while (rs.next()) {
                        productList.add(new Product(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4),
                                rs.getString(5),
                                null,
                                rs.getInt(7)));
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
                        i++;
                    }
                }
            }
            session.setAttribute("base", base);
            session.setAttribute("productList", productList);
            response.sendRedirect("SearchResult.jsp");

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
