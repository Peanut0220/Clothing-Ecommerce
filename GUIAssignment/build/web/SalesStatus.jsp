<%-- 
    Document   : staffSalesRecord
    Created on : Apr 30, 2023, 11:11:15 PM
    Author     : Ng Chong Jian
--%>

<% try {

%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="Entity.*"%>
<%@page import="java.util.ArrayList"%>
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
    .product-container{
        margin-left: 8%;
        margin-right: 8%;
        display:block;
    }

    .product-title h1{
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
</style>
<jsp:include page="/GetOrderStatus" />
<% List<domain.Orders> ordersList = (List<domain.Orders>) session.getAttribute("orderStatusList");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>Manage Product | OK Clothing</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <div class="product-container">
            <div class="product-title">
                <h1>Sales Status</h1>    
            </div>
            <div class="table-container">
                <p>Total : <%= ordersList.size()%></p>

                <table>
                    <thead>
                        <tr>
                            <td>No.</td>
                            <td>Order ID</td>
                            <td>Tracking Number</td>
                            <td>Order Status</td>
                            <td>Edit Status</td>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < ordersList.size(); i++) {
                                domain.Orders orders = ordersList.get(i);
                        %>
                        <tr>
                            <td><%=(i + 1)%></td>
                            <td><%=orders.getOrderid()%></td>
                            <td><%=orders.getTrackingnum()%></td>
                            <td><%=orders.getOrderstatus()%></td>
                            <td> <a href="staffEditStatus.jsp?id=<%= orders.getOrderid()%>"><i class="fas fa-edit" style="font-size:20px;"></i></a>
                            </td>
                            
                        </tr>
                        <% }%>
                                

                </table>

            </div>
        </div>
        <br><br><br><br><br>

        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>
