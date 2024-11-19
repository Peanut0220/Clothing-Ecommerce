<%
    try {
        
    
    if (!(session.getAttribute("staffusername").equals("Manager"))) {

        response.sendRedirect("Restricted.jsp");
    }
%>

<%-- 
    Document   : staffEditStatus
    Created on : May 2, 2023, 6:16:52 PM
    Author     : Ng Chong Jian
--%>
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
    }

    .selection {
        position: relative;
        width: auto;
        text-align: center;
        margin: 150px 0;
    }

    .selection select {
        padding: 7px 7px 7px 3px;
        font-size: 15px;
    }

    .selection button {
        padding: 7px 7px;
        font-size: 15px;
        background: black;
        color: white;
        cursor: pointer;
    }
    
    .selection select:focus {
        outline: none;
    }
    
    .selection button:hover {
        opacity: 0.7;
    }
</style>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int id = Integer.parseInt(request.getParameter("id"));%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <title>Edit Status</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <form action="EditStatus?id=<%= id%>" method="POST" class="selection">
            <select name="status">
                <option value="Packaging">Packaging</option>
                <option value="Shipping">Shipping</option>
                <option value="Delivered">Delivered</option>
            </select>
            <button type="submit">Submit</button>
        </form>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%}catch(Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>