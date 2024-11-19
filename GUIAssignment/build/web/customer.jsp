
<%
    try {

        if (!(session.getAttribute("staffusername").equals("Manager"))) {

            response.sendRedirect("Restricted.jsp");
        }
%>

<%-- 
    Document   : customer
    Created on : Apr 22, 2023, 10:52:31 PM
    Author     : Tang Qiao Ling
--%>

<%@page import="Entity.*, java.util.*" %>
<jsp:include page="/getCustomerLists" />
<% List<Customer> customerList = (List<Customer>) session.getAttribute("customerList");%>
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
        .customer-container{
            margin-left: 8%;
            margin-right: 8%;
            display:block;
        }

        .customer-title h1{
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

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>Manage Customer | OK Clothing</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <div class="customer-container">
            <div class="customer-title">
                <h1>Customer</h1>    
            </div>
            <div class="table-container">
                <p>Total : <%= customerList.size()%> </p>
                <table>
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Username</td>
                            <td>Email</td>
                            <td>Phone</td>
                            <td></td>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (int i = 0; i < customerList.size(); ++i) {
                                Customer customer = customerList.get(i);%>
                        <tr>
                            <td><%=customer.getCustid()%></td>
                            <td><%=customer.getCustusername()%></td>
                            <td><%=customer.getCustemail()%></td>
                            <td><%=customer.getCustphonenum()%></td>
                            <td> 
                                <form action="getCustomer">
                                    <input type="hidden" name="id" value="<%=customer.getCustid()%>">
                                    <button type="submit" id="edit">
                                        <i class="fas fa-edit" style="font-size:20px;"></i>
                                    </button>                                    
                                </form>
                                <form action="deleteCustomer">
                                    <input type="hidden" name="id" value="<%=customer.getCustid()%>">
                                    <button type="submit" id="delete">
                                        <a onClick="return confirm('Please confirm deletion');" href="deleteCustomer";><i class="fas fa-trash" style="font-size:20px;"></i></a>
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <% }%> 
                    </tbody>
                </table>

            </div>
            <br><br><br><br><br>
            <div class="add-cancel">
                <button class="perform" type="button" onclick="location.href = 'createCustomer.html'">Add</button>

            </div>
        </div>
        <%@include file = "staffFooter.jsp"%>
    </body>

</html>
<%} catch (Exception ex) {
        out.println("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>
