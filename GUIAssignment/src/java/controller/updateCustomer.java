package controller;

import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 * @author Tang Qiao Ling
 */
public class updateCustomer extends HttpServlet {

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
            String phone = request.getParameter("cphone");
            String password = request.getParameter("cpassword");

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
                out.println("location='editCustomer.jsp';");
                out.println("</script>");

            } else if (matcherPassword.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid password. Please try again.');");
                out.println("location='editCustomer.jsp';");
                out.println("</script>");

            } else if (matcherPhone.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid phone. Please try again.');");
                out.println("location='editCustomer.jsp';");
                out.println("</script>");

            } else {
                HttpSession session = request.getSession();
                Customer customer = new Customer();

                customer.setCustid((Integer) session.getAttribute("id"));
                customer.setCustusername(uname);
                customer.setCustfirstname(fname);
                customer.setCustlastname(lname);
                customer.setCustemail(email);
                customer.setCustphonenum(phone);
                customer.setCustpassword(password);
                int found = 0;

                Query query = em.createNamedQuery("Customer.findByCustusername");
                query.setParameter("custusername", uname);
                List<Customer> customerList = query.getResultList();
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getCustusername().equals(uname)) {
                        found = 1;
                    }
                }
                if (found == 1) {
                    request.setAttribute("error", "Username has been taken, enter another one");
                    request.getRequestDispatcher("editCustomer.jsp").include(request, response);
                } else {
                    utx.begin();
                    em.merge(customer);
                    utx.commit();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update successfully!');");
                    out.println("location='customer.jsp';");
                    out.println("</script>");
                }
            }
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
