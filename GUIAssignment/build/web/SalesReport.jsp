<%-- 
    Document   : SalesReport
    Created on : May 1, 2023, 10:31:28 PM
    Author     : <null>
--%>
<%
    try {

        if (!(session.getAttribute("staffusername").equals("Manager"))) {

            response.sendRedirect("Restricted.jsp");
        }
%>
<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }
    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        animation: 2.5s fadeIn;
    }

    .report-container {
        text-align: center;
    }

    .report-container .highest-result {
        margin: 20px 0;
        padding: 30px;
        font-size: 20px;
        border: 1px gray solid;
    }

    .highest-result h1 {
        font-size: 40px;
        margin-bottom: 20px;
        text-decoration: underline;
        text-underline-offset: 11px;
    }


    .lowest-result {
        margin: 20px 0;
        font-size: 20px;
        border: 1px gray solid;
        padding: 30px;
    }

    .lowest-result h1 {
        font-size: 40px;
        margin-bottom: 20px;
        text-decoration: underline;
        text-underline-offset: 11px;
    }
    
    .show-grid {
        display: grid;
        columns: 1;
    }
    
    .show-grid .spacing {
        margin-bottom: 15px;
    }
</style>

<%@page import="domain.ProductTotal"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/GetSalesReport" />
<% ArrayList<domain.ProductTotal> prodtotalList = (ArrayList<domain.ProductTotal>) session.getAttribute("prodtotalList");%>
<% ArrayList<domain.Product> prodList = (ArrayList<domain.Product>) session.getAttribute("prodList");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <title>Sales Report</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <div class="report-container">
            <div class="highest-result">
                <h1>Top 5 Sold Products</h1>
                <% for (int i = 0; i < 5; i++) {
                        if (prodtotalList.get(i) != null) {
                            
                        ProductTotal prodtotal = prodtotalList.get(i);

                        for (int j = 0; j < prodList.size(); j++) {
                            if (prodtotal.getProdid() == prodList.get(j).getProductid()) {
                %>
                <div class="show-grid">
                    
                    <span>Product ID : <%= prodList.get(j).getProductid()%></span>
                    <span class="spacing">Total Sold   : <%= prodtotal.getProdtotal()%></span>
                </div>
                <%}
                        }
                    } }%>
            </div>
            <div class="lowest-result">
                <h1>Least 5 Sold Products</h1>
                <% int size = prodtotalList.size();
                    for (int z = prodtotalList.size() - 1; z + 1 > size - 5; z--) {
                    if (prodtotalList.get(z)!=null) {
                            
                        
                        ProductTotal prodtotal = prodtotalList.get(z);

                        for (int y = 0; y < prodList.size(); y++) {
                            if (prodtotal.getProdid() == prodList.get(y).getProductid()) {
                %>
                <div class="show-grid">
                    <span>Product ID : <%= prodList.get(y).getProductid()%></span>
                    <span class="spacing">Total Sold   : <%= prodtotal.getProdtotal()%></span>
                </div>
                <%}
                        }
                   } }%>
            </div>
        </div>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>
