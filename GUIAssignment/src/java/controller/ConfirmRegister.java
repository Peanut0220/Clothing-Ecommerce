/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Entity.*;
import java.io.*;
import java.util.List;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ConfirmRegister extends HttpServlet {
    
    @PersistenceContext EntityManager em;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String phoneNo = request.getParameter("phoneNo");
            String password = request.getParameter("password");
            Customer customer = new Customer(email, username, fname, lname, phoneNo, password);
            
            Query query = em.createNamedQuery("Customer.findByCustusername");
            query.setParameter("custusername", username);
            List<Customer> customerList = query.getResultList();
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getCustusername().equals(username)) {
                    request.setAttribute("error", "Username has been taken, enter another one");
                    request.getRequestDispatcher("login-register.jsp").include(request, response);
                }
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("ConfirmRegistriation.jsp");
            
        } catch(Exception ex) {
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
