/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
public class LoadingInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        
        String companyName = "OK CLOTHING";
        String companyEmail = "okclothing@gmail.com";
        String copyright = "COPYRIGHT © OK CLOTHING ALL RIGHTS RESERVED.";
        ServletContext context = getServletContext();
        if (context.getInitParameter("companyName") != null) {
            companyName = context.getInitParameter("companyName");
        }
        if (context.getInitParameter("companyEmail") != null) {
            companyEmail = context.getInitParameter("companyEmail");
        }
        if (context.getInitParameter("copyright") != null) {
            copyright = context.getInitParameter("copyright");
        }
        context.setAttribute("companyName", companyName);
        context.setAttribute("companyEmail", companyEmail);
        context.setAttribute("copyright", copyright);
        
     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
    }

}
