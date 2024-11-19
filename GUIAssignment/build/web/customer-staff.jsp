<%-- 
    Document   : customer-staff
    Created on : May 3, 2023, 3:00:50 PM
    Author     : LAW GUAN WEN
--%>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        background: url(material/image/background.jpg) rgba(0,0,0,0.7);
        background-size: cover;
        background-repeat: no-repeat;
        background-blend-mode: darken;
    }

    .select-container {
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        height: 100%;
    }

    .select-container a {
        font-size: 30px;
        background: black;
        color: white;
        padding: 45px 30px;
        text-decoration: none;
        border-radius: 25px;
        border: 2px white solid;
        transition: 0.35s all ease-in-out;
    }

    .select-container a:hover {
        transform: scale(1.2);
        transition: 0.35s all ease-in-out;
    }
</style>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select login method</title>
    </head>
    <body>
        <div class="select-container">
            <a class="customer-login" href="login-register.jsp">Customer Login</a>
            <a class="staff-login" href="staffLogin.jsp">Staff Login</a>
        </div>
    </body>
</html>
