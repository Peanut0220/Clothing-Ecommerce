/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.persistence.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Entity.*;
import java.io.PrintWriter;
import java.util.List;

public class ProcessLogin extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("login-username");
            String password = request.getParameter("login-password");

            Query query = em.createNamedQuery("Customer.findAll");
            List<Customer> customerList = query.getResultList();
            int j = 0;
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getCustusername().equals(username) && customerList.get(i).getCustpassword().equals(password)) {
                    j = 1;
                } else {
                    j = 0;
                }
                if (j == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    response.sendRedirect("index.jsp");
                }
            }
            request.setAttribute("errorMessage", "The username or password is incorrect");
            request.getRequestDispatcher("login-register.jsp").forward(request, response);

        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Something went wrong.....");
            out.println("<button type='button' onclick='history.back()'>Back to previous page</button>");
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
