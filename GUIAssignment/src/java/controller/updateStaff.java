package controller;

import Entity.Customer;
import Entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class updateStaff extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try {
            String uname = request.getParameter("suname");
            String fname = request.getParameter("sfname");
            String lname = request.getParameter("slname");
            String email = request.getParameter("semail");
            String phone = request.getParameter("sphone");
            String password = request.getParameter("spassword");

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
                out.println("location='editStaff.jsp';");
                out.println("</script>");

            } else if (matcherPassword.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid password. Please try again.');");
                out.println("location='editStaff.jsp';");
                out.println("</script>");

            } else if (matcherPhone.matches() == false) {

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Invalid phone. Please try again.');");
                out.println("location='editStaff.jsp';");
                out.println("</script>");

            } else {
                HttpSession session = request.getSession();
                Staff staff = new Staff();
                Date date = (Date) session.getAttribute("createDate");

                staff.setStaffid((Integer) session.getAttribute("id"));
                staff.setStaffusername(uname);
                staff.setStafffirstname(fname);
                staff.setStafflastname(lname);
                staff.setStaffemail(email);
                staff.setStaffphoneno(phone);
                staff.setStaffpassword(password);
                staff.setStaffcreateddate(date);
                int found = 0;
                Query query = em.createNamedQuery("Staff.findByStaffusername");
                query.setParameter("staffusername", uname);
                List<Staff> customerList = query.getResultList();
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getStaffusername().equals(uname)) {
                        found = 1;
                    }
                }
                if (found == 1) {
                    request.setAttribute("error", "Username has been taken, enter another one");
                    request.getRequestDispatcher("editStaff.jsp").include(request, response);
                } else {
                    utx.begin();
                    em.merge(staff);
                    utx.commit();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update successfully!');");
                    out.println("location='staff.jsp';");
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
