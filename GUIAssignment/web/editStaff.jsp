<%-- 
    Document   : editStaff
    Created on : Apr 22, 2023, 2:12:41 PM
    Author     : Tang Qiao Ling
--%>
<%
    try {
        
    
    if (!(session.getAttribute("staffusername").equals("Manager"))) {

        response.sendRedirect("Restricted.jsp");
    }
%>
<jsp:useBean id="staff" scope="session" class="Entity.Staff" />
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
        <title>Edit Staff</title>
    </head>

    <body>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>

        <div class="container">

            <h1>Edit Staff</h1>
            <hr>

            <div class="row">

                <!-- edit form column -->
                <div class="col-md-7 personal-info">

                    <h3>Personal info</h3>

                    <!-- Change this to a <form> to reproduce your original issue -->
                    <div class="form-horizontal" role="form">
                        <form action="updateStaff" method="post">
                            <div class="form-group">
                                <label for="suname" class="col-lg-3 control-label">Username:</label>
                                <span style="color:red">${error}</span>
                                <div class="col-lg-8">
                                    <input class="form-control" name="suname" id="uname" type="text" value="${staff.staffusername}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sfname" class="col-lg-3 control-label">First name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="sfname" id="fname" type="text" value="${staff.stafffirstname}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="slname" class="col-lg-3 control-label">Last name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="slname" id="lname" type="text" value="${staff.stafflastname}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="semail" class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="semail" id="email" type="text" value="${staff.staffemail}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="spassword" class="col-lg-3 control-label">Password:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="spassword" id="password" type="password" value="${staff.staffpassword}" required>                                     
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sphone" class="col-lg-3 control-label">Phone:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" name="sphone" id="phone" type="tel" value="${staff.staffphoneno}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-8">
                                    <input type="submit" class="btn btn-primary" value="Save Changes">                                    
                                    <a href="staff.jsp"> <input type="button" class="btn btn-default" value="Cancel"></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <hr>

        </div>
    </body>
</html>
<% }catch(Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
}%>
