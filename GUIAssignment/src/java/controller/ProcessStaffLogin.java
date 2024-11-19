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
import java.util.List;

/**
 *
 * @author LAW GUAN WEN
 */
public class ProcessStaffLogin extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String staffusername = request.getParameter("staffusername");
            String staffpassword = request.getParameter("staffpassword");

            Query query = em.createNamedQuery("Staff.findAll");
            List<Staff> staffList = query.getResultList();
            int j = 0;
            for (int i = 0; i < staffList.size(); i++) {
                if (staffList.get(i).getStaffusername().equals(staffusername) && staffList.get(i).getStaffpassword().equals(staffpassword)) {
                    j = 1;
                } else {
                    j = 0;
                }
                if (j == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("staffusername", staffusername);
                    response.sendRedirect("staffHome.jsp");
                }
            }
            request.setAttribute("errorMessage", "The username or password is incorrect");
            request.getRequestDispatcher("staffLogin.jsp").forward(request, response);

        } catch (Exception ex) {
            
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
