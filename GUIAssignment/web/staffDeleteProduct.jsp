<%
    try {
        
    
    if (!(session.getAttribute("staffusername").equals("Manager"))) {

        response.sendRedirect("Restricted.jsp");
    }
%>

<%-- 
    Document   : staffDeleteProduct.jsp
    Created on : Apr 28, 2023, 12:21:45 AM
    Author     : Ng Chong Jian
--%>

<%int productid = Integer.parseInt(request.getParameter("productid"));%>
<jsp:include page="/DeleteProduct?productid=<%=productid%>" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    
    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        display: grid;
        height: 100vh;
        place-items: center;
        background: url(material/image/background.jpg) rgba(0,0,0,0.7);
        background-size: cover;
        background-repeat: no-repeat;
        background-blend-mode: darken;
    }
    
    .simple-container {
        position: relative;
        width: auto;
        background-color: white;
        padding: 30px;
        text-align: center;
    }
    
    .simple-container h1 {
        margin-bottom: 20px;
    }
    
    .simple-container h1:after {
        content: '';
        background-color: black;
        height: 2px;
        width: 100%;
        display: block;
    }
    
    .simple-container a {
        text-decoration: none;
        font-size: 17px;
    }
    
    .simple-container a:hover {
        text-decoration: underline;
    }
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Successful</title>
    </head>
    <body>
        <div class="simple-container">
            <h1>Product Has Been Successfully Deleted!</h1>
            <a href="staffProduct.jsp">Back to product page</a>
        </div>
    </body>
</html>
<%}catch(Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>