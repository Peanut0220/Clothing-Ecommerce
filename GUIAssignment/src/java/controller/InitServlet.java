/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class InitServlet extends HttpServlet {

    private String companyName = "OK CLOTHING";
    private String copyright = "COPYRIGHT © OK CLOTHING ALL RIGHTS RESERVED.";
    private String companyEmail = "okclothing@gmail.com";

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        if (config.getInitParameter("companyName") != null) {
            companyName = config.getInitParameter("companyName");
        }
        if (config.getInitParameter("copyright") != null) {
            copyright = config.getInitParameter("copyright");
        }
        if (config.getInitParameter("companyEmail") != null) {
            companyEmail = config.getInitParameter("companyEmail");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("companyName", companyName);
        session.setAttribute("copyright", copyright);
        session.setAttribute("companyEmail", companyEmail);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
