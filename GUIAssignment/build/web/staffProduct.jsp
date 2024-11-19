<%
    try {

%>

<%-- 
    Document   : staffProduct
    Created on : Apr 22, 2023, 5:42:30 PM
    Author     : Ng Chong Jian
--%>


<%@page import="domain.Product"%>

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
        margin-bottom: 10px;
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
<jsp:include page="/GetProductList" />
<%@page import="java.util.ArrayList"%>
<% ArrayList<domain.Product> productList = (ArrayList<domain.Product>) session.getAttribute("productList");%>
<% ArrayList<String> base = (ArrayList<String>) session.getAttribute("base");%>

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
                <h1>Product</h1>    
            </div>
            <div class="table-container">
                <p>Total :<%=productList.size()%><p>
                <table>
                    <thead>
                        <tr>
                            <td>No.</td>
                            <td>Product ID</td>
                            <td>Name</td>
                            <td>Price</td>
                            <td>Description</td>
                            <td>Quantity</td>
                            <td>Image</td>
                            <td>Category</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int i = 0; i < productList.size(); i++) {
                                Product product = productList.get(i);
                                String result = String.format("%.2f", product.getProductprice());
                        %>
                        <tr>
                            <td><%=i + 1%></td>
                            <td><%=product.getProductid()%></td>
                            <td><%=product.getProductname()%></td>
                            <td>RM <%=result%></td>
                            <td><%=product.getProductdesc()%></td>
                            <td><%=product.getProductQuantity()%></td>
                            <td><img src="<%= base.get(i)%>"width="200" height="200"/></td>
                            <td><%=product.getCategoryname()%></td>
                            <td> <a href="staffEditProduct.jsp?productid=<%=product.getProductid()%>"><i class="fas fa-edit" style="font-size:20px;"></i></a>
                                <a onClick="return confirm('Please confirm deletion');" href="staffDeleteProduct.jsp?productid=<%=product.getProductid()%>";><i class="fas fa-trash" style="font-size:20px;"></i></a>
                            </td>
                        </tr>
                        <%  }%>
                </table>
            </div>
        </div>
        <br><br><br>
        <div class="add-cancel">
            <form action="staffAddProduct.jsp" method="post">  
                <input class="perform" type="submit" value="Add">
            </form>
        </div>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<% } catch (Exception ex) {
        out.println("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>