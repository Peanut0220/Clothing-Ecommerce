package controller;

import Entity.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ng Chong Jian
 */
public class GetOrderList extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int day = 100;
        int month = 100;
        int year = 100;
        if (request.getParameter("day") != null) {
            day = Integer.parseInt(request.getParameter("day"));
        }
        if (request.getParameter("month") != null) {
            month = Integer.parseInt(request.getParameter("month"));
        }
        if (request.getParameter("year") != null) {
            year = Integer.parseInt(request.getParameter("year"));
        }

        Query query = em.createNamedQuery("Orders.findAll");

        List<Orders> ordersList = query.getResultList();
        List<Orders> ordersSearchList = new ArrayList<Orders>();
        String pattern = "yyyy-MM-dd";

        if (day != 100 || month != 100 || year != 100) {

            for (int i = 0; i < ordersList.size(); i++) {
                DateFormat df = new SimpleDateFormat(pattern);
                Date today = ordersList.get(i).getOrderdate();
                String todayAsString = df.format(today);
                LocalDate currentDate = LocalDate.parse(todayAsString);
                int dayfound = currentDate.getDayOfMonth();
                int monthfound = currentDate.getMonthValue();
                int yearfound = currentDate.getYear();
                if (dayfound == day) {
                    ordersSearchList.add(ordersList.get(i));
                } else if (monthfound == month) {
                    ordersSearchList.add(ordersList.get(i));
                } else if (yearfound == year) {
                    ordersSearchList.add(ordersList.get(i));
                }
            }
        }
        try {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String host = "jdbc:derby://localhost:1527/OKClothingDB";
            String user = "nbuser";
            String password = "nbuser";

            ArrayList<domain.Orderdetails> orderDList = new ArrayList<domain.Orderdetails>();
            conn = DriverManager.getConnection(host, user, password);      //connect to database
            stmt = conn.prepareStatement("SELECT * FROM ORDERDETAILS");
            rs = stmt.executeQuery();

            while (rs.next()) {
                orderDList.add(new domain.Orderdetails(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }

            HttpSession session = request.getSession();
            if (day == 100 && month == 100 && year == 100) {
                session.setAttribute("ordersList", ordersList);
            } else {
                session.setAttribute("ordersList", ordersSearchList);
            }
            session.setAttribute("orderDList", orderDList);
            response.sendRedirect("staffSalesRecordFilter.jsp");
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
