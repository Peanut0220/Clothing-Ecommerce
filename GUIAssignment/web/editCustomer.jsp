<%-- 
    Document   : editCustomer
    Created on : Apr 22, 2023, 2:12:41 PM
    Author     : Tang Qiao Ling
--%>

<% try{
    if (session == null) {
        response.sendRedirect("staffLogin.jsp");
    }
    if (!(session.getAttribute("staffusername").equals("Manager"))) {
        
        response.sendRedirect("Restricted.jsp");
    }
    
%>
<jsp:useBean id="customer" scope="session" class="Entity.Customer" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        body {
            font-family: "Ubuntu";
            animation: 2.5s fadeIn;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Customer</title>
    </head>
    <body>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
        <div class="container">

            <h1>Edit Customer</h1>
            <hr>

            <div class="row">

                <!-- edit form column -->
                <div class="col-md-7 personal-info">

                    <h3>Personal info</h3>
                    <form action="updateCustomer" method="post">
                        <!-- Change this to a <form> to reproduce your original issue -->
                        <div class="form-horizontal" role="form">

                            <div class="form-group">
                                <label for="cuname" class="col-lg-3 control-label">Username:</label>
                                <span style="color:red">${error}</span>
                                <div class="col-lg-8">
                                    <input class="form-control" name="cuname" id="cuname" type="text" value="${customer.custusername}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cfname" class="col-lg-3 control-label">First name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="cfname" id="cfname" type="text" value="${customer.custfirstname}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="clname" class="col-lg-3 control-label">Last name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="clname" id="clname" type="text" value="${customer.custlastname}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cemail" class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="cemail" id="cemail" type="text" value="${customer.custemail}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cpassword" class="col-lg-3 control-label">Password:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="cpassword" id="cpassword" type="password" value="${customer.custpassword}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cphone" class="col-lg-3 control-label">Phone:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="cphone" id="cphone" type="tel" value="${customer.custphonenum}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-8">
                                    <input type="submit" class="btn btn-primary" value="Save Changes">                                    
                                    <a href="customer.jsp"> <input type="button" class="btn btn-default" value="Cancel"></a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <hr>

        </div>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>
