<%-- 
    Document   : staff
    Created on : Apr 11, 2023, 2:42:33 PM
    Author     : Tang Qiao Ling
--%>
<%
    try {

        if (!(session.getAttribute("staffusername").equals("Manager"))) {

            response.sendRedirect("Restricted.jsp");
        }
%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entity.*, java.util.*" %>
<jsp:include page="/getStaffLists" />
<% List<Staff> staffList = (List<Staff>) session.getAttribute("staffList");%>
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
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        @font-face {
            font-family: "Ubuntu";
            src: url(material/Ubuntu-Regular.ttf);
        }

        body {
            font-family: "Ubuntu";
            animation: 2.5s fadeIn;
        }
        .staff-container{
            margin-left: 8%;
            margin-right: 8%;
            display:block;
        }

        .staff-title h1{
            font-weight:lighter;
            display:inline-block;
            border-bottom:2px solid black;
            margin-bottom:10px;
        }

        table{
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 0.9em;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            width: 100%;
        }
        table th,td{
            padding: 12px 15px;
        }
        table thead tr {
            background-color:black;
            color: #ffffff;
            text-align: left;
        }

        .imgs{
            width:250px;
            height:150px;
        }

        i{

            transition:0.5s;
        }
        fas{
            font-size: 20px;
        }

        #edit{
            border: none;
            border-color: transparent;
        }

        #delete{
            border: none;
            border-color: transparent;
        }

        i:hover{
            color:black;
            transition:0.5s;
        }

        .fa-edit,.fa-trash{
            color:gray;
        }

        .add-cancel {
            text-align: center;
            margin-top: 4%;

        }

        .add-cancel .perform{
            color: black;
            background-color: white;
            border: 3px black solid;
            border-radius: 30px;
            padding: 7px 20px;
            text-decoration: none;
            cursor:pointer;
            transition: all 0.5s;
            font-size:20px;
            margin-bottom:20px;
        }

        .add-cancel .perform:hover {
            background-color: black;
            color:white;
            transition: all 0.5s;
        }
        
        button {
            
            
        }
        
        button.edit-button {
            cursor: pointer;
            background: none;
        }
        
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>Manage Staff | OK Clothing</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <div class="staff-container">
            <div class="staff-title">
                <h1>Staff</h1>    
            </div>
            <div class="table-container">
                <p>Total : <%= staffList.size()%></p>
                <table>
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Username</td>
                            <td>Email</td>
                            <td>Phone</td>
                            <td>Created Date</td>
                            <td></td>
                        </tr>
                    </thead>

                    <tbody>
                        <%! SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");%>
                        <% for (int i = 0; i < staffList.size(); ++i) {
                                Staff staff = staffList.get(i);%>
                        <tr>
                            <td><%=staff.getStaffid()%></td>                
                            <td><%=staff.getStaffusername()%></td>
                            <td><%=staff.getStaffemail()%></td>
                            <td><%=staff.getStaffphoneno()%></td>
                            <td><%=formatter.format(staff.getStaffcreateddate())%></td>
                            <td> 
                                <form action="getStaff">
                                    <input type="hidden" name="id" value="<%=staff.getStaffid()%>">
                                    <button type="submit" id="edit" class="edit-button">
                                        <i class="fas fa-edit" style="font-size:20px;"></i>
                                    </button>
                                </form>
                                <form action="deleteStaff" method="post">
                                    <input type="hidden" name="id" value="<%=staff.getStaffid()%>">
                                    <button type="submit" id="delete">
                                        <a onClick="return confirm('Please confirm deletion');" href="deleteStaff";><i class="fas fa-trash" style="font-size:20px;"></i></a>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <% }%> 
                    </tbody>                    
                </table>
            </div>
        </div>
        <br><br><br><br><br>
        <div class="add-cancel">
            <button class="perform" type="button" onclick="location.href = 'createStaff.html'">Add</button>
        </div>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>