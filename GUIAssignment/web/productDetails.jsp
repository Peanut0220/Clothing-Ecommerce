<% try {

%>

<%@page import="java.sql.Blob"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Product"%>
<% if (session.getAttribute("username") == null) {
%>
<% response.sendRedirect("login-register.jsp"); %>
<%} else if (session.getAttribute("username") != null) {
%>
<%@include file = "logoutNavBar.jsp"%>
<%}%>
<!DOCTYPE html>
<style>
    *{
        margin:0;
        padding:0;
        box-sizing:border-box;
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu" !important;
    }
    
    .wrapper{
        display:flex;
        justify-content: center;
        height: 100vh;
        padding: 100px 0 170px 0;
    }

    .wrapper .img{
        margin: 30px;
        border: 2px solid;
        text-align: center;
    }

    .wrapper .img img{
        width: 70%;
        object-fit: cover;
        
    }

    .wrapper ,info{
        height:88%;
        color:#444;
    }

    .info h3,p{
        padding: 15px 10px;
    }
    .info h3{
        font-size: 35px;
        font-weight: 900;
    }

    .info p{
        font-size: 22px;
    }

    .button {
        color: white;
        padding: 16px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 8px;
        transition-duration: 0.4s;
        cursor: pointer;

    }
    .button1 {
        color: white;
        padding: 16px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 8px;
        transition-duration: 0.4s;
        cursor: pointer;
        width:100%;
    }

    .button4 {
        background-color: white;
        color: black;
        border: 2px solid #e7e7e7;
    }

    .button4:hover {
        background-color: #e7e7e7;
    }

    .button5{
        background-color: black;
        color: white;
        border: 2px solid #555555;

    }

    .button5:hover {
        background-color: white;
        color: black;
    }


</style>

<%int productid = Integer.parseInt(request.getParameter("productid"));%>
<jsp:include page="/GetProductDetail?productid=<%=productid%>" />
<% ArrayList<domain.Product> product = (ArrayList<domain.Product>) session.getAttribute("product");%>
<% ArrayList<Blob> base = (ArrayList<Blob>) session.getAttribute("base");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>

    </head>

    <body>

        <div class="wrapper">

            <div class ="img">
                <%String result = String.format("%.2f", product.get(0).getProductprice()); %>
                <img src="<%=base.get(0)%>">
            </div>

            <div class="info">
                <h3><%=product.get(0).getProductname()%></h3>
                <p>Price : RM<%=result%></p>
                <p> <%=product.get(0).getProductdesc()%></p>
                <hr style="border:1px solid #eeeeee">

                <form action="AddtoCart" method="post">
                    <div class="qty">
                        <p>Quantity : </p>
                        <input type ="number" name="quantity" value="1" min="1" style=" font-size: 18px; width: 80px;height: 50px;margin:4px 8px;padding:3px 5px; width:38% ">
                    </div>

                    <br>
                    <input type="hidden"  name="productid" value="<%=product.get(0).getProductid()%>">
                    <div class="cart">
                        <button class="button1 button5">ADD TO CART</button><br><br><br>           
                    </div>
                </form>
            </div>
        </div>

        <%@include file ="footer.jsp" %>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>