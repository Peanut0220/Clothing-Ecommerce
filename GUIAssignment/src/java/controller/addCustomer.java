package controller;

import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tang Qiao Ling
 */
public class addCustomer extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {
            String uname = request.getParameter("cuname");
            String fname = request.getParameter("cfname");
            String lname = request.getParameter("clname");
            String email = request.getParameter("cemail");
            String password = request.getParameter("cpassword");
            String phone = request.getParameter("cphone");
            //Regular Expression   
            String regexEmail = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
            String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
            String regexPhone = "^(01)[0-46-9]-*[0-9]{7,8}$";

            //Compile regular expression to get the pattern  
            Pattern patternEmail = Pattern.compile(regexEmail);
            Pattern patternPassword = Pattern.compile(regexPassword);
            Pattern patternPhone = Pattern.compile(regexPhone);
            Matcher matcherEmail = patternEmail.matcher(email);
            Matcher matcherPassword = patternPassword.matcher(password);
            Matcher matcherPhone = patternPhone.matcher(phone);

            if (matcherEmail.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid email. Please try again.');");
                out.println("location='createCustomer.html';");
                out.println("</script>");

            } else if (matcherPassword.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid password. Please try again.');");
                out.println("location='createCustomer.html';");
                out.println("</script>");

            } else if (matcherPhone.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid phone. Please try again.');");
                out.println("location='createCustomer.html';");
                out.println("</script>");

            } else {
                utx.begin();
                Customer customer = new Customer(email, uname, fname, lname, phone, password);
                em.persist(customer);
                utx.commit();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add successfully!');");
                out.println("location='customer.jsp';");
                out.println("</script>");
            }
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
