<% try {

%>

<%-- 
    Document   : Testing
    Created on : Apr 23, 2023, 2:12:57 PM
    Author     : LAW GUAN WEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" scope="session" class="Entity.Customer" />

<!DOCTYPE html>
<style>
    *{
        box-sizing: border-box;
        padding: 0;
        margin: 0;
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        display: grid;
        place-items: center;
        height: 100vh;
        background: url(material/image/background.jpg) rgba(0,0,0,0.7);
        background-size: cover;
        background-repeat: no-repeat;
        background-blend-mode: darken;
    }

    .confirm-overlay {
        background: white;
        position: relative;
        width: auto;
        padding: 40px;
    }

    .confirm-overlay h1 {
        margin-bottom: 20px;
        font-size: 40px;
        letter-spacing: 2.5px;
    }

    .confirm-overlay h1:after {
        content: '';
        display: block;
        background: black;
        width: 100%;
        height: 3px;
    }

    table {
        margin-left: auto;
        margin-right: auto;
    }

    tr, td {
        padding: 10px;
        font-size: 20px;
    }
    
    input {
        border: none;
        border-bottom: 2px solid;
        font-size: 20px;
    }
    
    input:focus {
        outline: none;
    }
    
    .button-container {
        text-align: center;
        margin-top: 20px;
    }
    
    .button-container button {
        font-size: 20px;
        margin-left: 20px;
        padding: 10px;
        background: none;
        border: 2px black solid;
        cursor: pointer;
        border-radius: 10px;
    }
    
    .button-container button:hover {
        opacity: 0.7;
    }
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Information</title>
    </head>
    <body>
        <div class="confirm-overlay">
            <h1>Confirm Your Information</h1>
            <div class="confirm-container">
                <form action="ProcessRegister" method="GET">
                    <table>
                        <tr><td>Email</td><td><input type="text" name="email" id="input" value="${customer.custemail}" readonly></td></tr>
                        <tr><td>Username</td><td><input type="text" name="username" id="input" value="${customer.custusername}" readonly></td></tr>
                        <tr><td>First Name</td><td><input type="text" name="fname" id="input" value="${customer.custfirstname}" readonly></td></tr>
                        <tr><td>Last Name</td><td><input type="text" name="lname" id="input" value="${customer.custlastname}" readonly></td></tr>
                        <tr><td>Phone Number</td><td><input type="text" name="phoneNo" id="input" value="${customer.custphonenum}" readonly></td></tr>
                        <tr><td>Password</td><td><input type="text" name="password" id="input" value="${customer.custpassword}" readonly></td></tr>
                    </table>
                    <div class="button-container">
                        <button type="button" id="editButton" onclick="location.href='Edit.jsp'">Edit</button>
                        <button type="submit">Confirm</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>
