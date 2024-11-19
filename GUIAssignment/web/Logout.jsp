<%-- 
    Document   : LogoutStaff
    Created on : May 3, 2023, 3:30:46 PM
    Author     : LAW GUAN WEN
--%>

<% if (session != null) {
        session.invalidate();
        request.getSession(false);
        response.sendRedirect("LogoutSuccessful.jsp");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
