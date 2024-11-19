<% try {

%>

<%-- 
    Document   : Testing
    Created on : Apr 23, 2023, 2:12:57 PM
    Author     : Guanwen
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
            <h1>Edit</h1>
            <div class="confirm-container">
                <form action="ConfirmRegister" method="POST">
                    <table>
                        <tr><td>Email</td><td><input type="email" name="email" title="Must contain @ and .com" pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" value="${customer.custemail}" required></td></tr>
                        <tr><td>Username</td><td><input type="text" name="username" value="${customer.custusername}" required></td></tr>
                        <tr><td>First Name</td><td><input type="text" name="fname" title="Name only contain alphabets and spaces" pattern="[A-Za-z ]{1,30}" value="${customer.custfirstname}" required></td></tr>
                        <tr><td>Last Name</td><td><input type="text" name="lname" title="Name only contain alphabets and spaces" pattern="[A-Za-z ]{1,30}" value="${customer.custlastname}" required></td>
                        <tr><td>Phone Number</td><td><input type="text" name="phoneNo" title="Phone Number only start with 010/011/012/013/014/016/017/018/019" value="${customer.custphonenum}" pattern="(01)[0-46-9][-][0-9]{7,8}" required></td>
                        <tr><td>Password</td><td><input type="password" name="password" title="Password must contain at least 1 capital, 1 small letter, and longer than 8" onchange="validatePassword()" value="${customer.custpassword}" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}" required></td>
                        <tr><td>Confirm Password</td><td><input type="password" name="confirm-password" onchange="validatePassword()" value="${customer.custpassword}" required></td>
                    </table>
                    <div class="button-container">
                        <button type="submit">Confirm</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script>
    function validatePassword() {
        const password = document.querySelector('input[name=password]');
        const confirm = document.querySelector('input[name=confirm-password]');
        if (password.value !== confirm.value) {
            confirm.setCustomValidity('Password does not match');
        }else {
            confirm.setCustomValidity('');
        }
    }
</script>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>
