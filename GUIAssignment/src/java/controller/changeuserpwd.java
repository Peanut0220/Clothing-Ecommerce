package controller;

import Entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class changeuserpwd extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            Customer customer = (Customer) em.createNamedQuery("Customer.findByCustusername").setParameter("custusername", user).getSingleResult();
            int pid = customer.getCustid();
            String ppw = customer.getCustpassword();
            session.setAttribute("pid", pid);

            String uname = customer.getCustusername();
            String fname = customer.getCustfirstname();
            String lname = customer.getCustlastname();
            String email = customer.getCustemail();
            String phone = customer.getCustphonenum();

            String oldpw = request.getParameter("oldpw");
            String newpw = request.getParameter("newpw");
            String conpw = request.getParameter("conpw");

            String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
            Pattern patternPassword = Pattern.compile(regexPassword);
            Matcher matcherNewPassword = patternPassword.matcher(newpw);

            if (oldpw.equals("")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please fill in password.');");
                out.println("location='customerProfile.jsp';");
                out.println("</script>");
            } else if (!(oldpw.equals(ppw))) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Wrong old password. Please try again.');");
                out.println("location='customerProfile.jsp';");
                out.println("</script>");

            } else if (matcherNewPassword.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid password. Please try again.');");
                out.println("location='customerProfile.jsp';");
                out.println("</script>");

            } else if (!(newpw.equals(conpw))) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password mismatch. Please try again.');");
                out.println("location='customerProfile.jsp';");
                out.println("</script>");

            } else {
                customer.setCustid(pid);
                customer.setCustusername(uname);
                customer.setCustfirstname(fname);
                customer.setCustlastname(lname);
                customer.setCustemail(email);
                customer.setCustphonenum(phone);
                customer.setCustpassword(conpw);

                utx.begin();
                em.merge(customer);
                utx.commit();
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password changed!');");    
                out.println("location='index.jsp';");
                out.println("</script>");                
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
