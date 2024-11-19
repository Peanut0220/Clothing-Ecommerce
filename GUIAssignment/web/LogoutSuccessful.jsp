<%-- 
    Document   : LogoutSuccessful
    Created on : Apr 27, 2023, 11:05:41 PM
    Author     : LAW GUAN WEN
--%>

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
    
    .logout-container {
        position: relative;
        width: auto;
        background-color: white;
        padding: 30px;
        text-align: center;
    }
    
    .logout-container h1 {
        margin-bottom: 20px;
    }

    .logout-container h1:after {
        content: '';
        background-color: black;
        height: 2px;
        width: 100%;
        display: block;
    }
    
    .logout-container a {
        text-decoration: none;
        font-size: 17px;
    }
    
    .logout-container a:hover {
        text-decoration: underline;
    }
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Successful!</title>
    </head>
    <body>
        <div class="logout-container">
            <h1>Logout Successfully</h1>
            <a href="index.jsp">Click me back to home page</a>
        </div>
    </body>
</html>
