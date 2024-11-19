<%
    try {

        if (!(session.getAttribute("staffusername").equals("Manager"))) {

            response.sendRedirect("Restricted.jsp");
        }
%>

<%-- 
    Document   : staffSalesRecord
    Created on : Apr 30, 2023, 11:11:15 PM
    Author     : Ng Chong Jian
--%>
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

    .search-day-month-year input{
        width: 100px;
        padding: 7px 0 7px 3px;
        margin-top: 10px;
        font-size: 15px;
    }

    .search-day-month-year input:focus {
        outline: none;
    }

    .search-day-month-year button {
        padding: 7px 7px 7px 7px;
        background-color: black;
        font-size: 15px;
        color: white;
        cursor: pointer;
    }

    .search-day-month-year button:hover {
        opacity: 0.7;
    }
    
    .editstatus {
        position: absolute;
        right: 10%;
        top: 30%;
        padding: 10px 20px;
        background: black;
        color: white;
        text-decoration: none;
        border-radius: 10px;
        font-size: 17px;
    }

    .editstatus:hover {
        opacity: 0.7;
    }
</style>

<% List<Orders> ordersList = (List<Orders>) session.getAttribute("ordersList");%>
<% ArrayList<domain.Orderdetails> orderDList = (ArrayList<domain.Orderdetails>) session.getAttribute("orderDList");%>
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
                <h1>Sales Record</h1>    
            </div>
            <div class="table-container">
                <p>Total : <%= ordersList.size()%></p>
                <form action="GetOrderList" method="POST" class="search-day-month-year">
                    <input type="number" min="1" max="31"name="day" value=""placeholder="Day">
                    <button type="submit">Submit</button>
                </form>
                <form action="GetOrderList" method="POST" class="search-day-month-year">
                    <input type="number"  min="1" max="12"name="month" value=""placeholder="Month">
                    <button type="submit">Submit</button>
                </form>
                <form action="GetOrderList" method="POST" class="search-day-month-year">
                    <input type="number" name="year" value=""placeholder="Year">
                    <button type="submit">Submit</button>
                </form>
                <a class="editstatus" href="SalesStatus.jsp">Show Status</a>
                <table>
                    <thead>
                        <tr>
                            <td>No.</td>
                            <td>Order ID</td>
                            <td>Customer ID</td>
                            <td>Order Date</td>
                            <td>Address</td>
                            <td>Shipping Fees</td>
                            <td>SubTotal</td>
                            <td>Total</td>
                        </tr>
                    </thead>
                     <tbody>
                        <%
                            for (int i = 0; i < ordersList.size(); i++) {
                                Orders orders = ordersList.get(i);
                                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = orders.getOrderdate();
                                String result1 = String.format("%.2f", orders.getShippingfees());
                                String result2 = String.format("%.2f", orders.getSubtotal());
                                String result3 = String.format("%.2f", orders.getTotal());
                        %>
                        <tr>
                            <td><%=(i + 1)%></td>
                            <td><%=orders.getOrderid()%></td>
                            <td><%=orders.getCustid().getCustid()%></td>
                            <td><%= dt.format(date)%></td>
                            <td><%=orders.getOrderaddress()%></td>
                            <td>RM <%=result1%></td>
                            <td>RM <%=result2%></td>
                            <td>RM <%=result3%></td>
                        </tr>
                        <% for (int j = 0; j < orderDList.size(); j++) {
                                domain.Orderdetails orderdetails = orderDList.get(j);
                                if (orderdetails.getOrdersid() == orders.getOrderid()) {
                        %>
                        <tr>
                            <td></td>
                            <td>Product ID : <%=orderdetails.getProdid()%></td>
                            <td>Quantity : <%=orderdetails.getQuantity()%></td>
                        </tr>
                        <% }
                                }
                            }%>

                </table>

            </div>
        </div>
        <br><br><br><br><br>
        <div class="add-cancel">
            <form action="GetSalesReport" method="post">  
                <input class="perform" type="submit" value="Generate Report"></button>
            </form>
        </div>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>

