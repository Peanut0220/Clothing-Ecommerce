<% try {

%>

<%-- 
    Document   : Home Page
    Created on : Mar 16, 2023, 3:27:23 PM
    Author     : LAW GUAN WEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/LoadingInitServlet" />
<% if (session.getAttribute("username") == null) {
%>
<%@include file = "navbar.jsp"%>
<%} else if (session.getAttribute("username") != null) {
%>
<%@include file = "logoutNavBar.jsp"%>
<%}%>

<!DOCTYPE html>
<style>
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
    }

    /*content*/
    .slide-container {
        width: 100%;
        height: auto;
        overflow: hidden;
    }

    .auto-slide-wrapper {
        width: 500%;
        height: auto;
        display: flex;
        overflow: hidden;
    }

    .auto-slide-wrapper input {
        display: none;
    }

    .slide {
        width: 20%;
        transition: 1.5s;
    }

    .slide img {
        width: 100%;
        height: 630px;
    }

    .manual-slide-wrapper {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .manual-btn {
        border: 2px solid black;
        padding: 8px;
        border-radius: 10px;
        cursor: pointer;
        transition: 0.4s;
        margin-top: 20px;
    }

    .manual-btn:not(:last-child) {
        margin-right: 40px;
    }

    .manual-btn:hover {
        background: black;
    }

    #radio1:checked ~ .first {
        margin-left: 0;
    }

    #radio2:checked ~ .first {
        margin-left: -20%;
    }

    #radio3:checked ~ .first {
        margin-left: -40%;
    }

    #radio4:checked ~ .first {
        margin-left: -60%;
    }

    .auto-slides-btn {
        position: absolute;
        display: flex;
        width: 100%;
        justify-content: center;
        margin-top: 654px;
        z-index: -1;
    }

    .auto-slides-btn div{
        border: 2px solid black;
        padding: 8px;
        border-radius: 10px;
        cursor: pointer;
        transition: 0.4s;
    }

    .auto-slides-btn div:not(:last-child) {
        margin-right: 40px;
    }

    #radio1:checked ~ .auto-slides-btn .auto-btn1 {
        background: black;
    }

    #radio2:checked ~ .auto-slides-btn .auto-btn2 {
        background: black;
    }

    #radio3:checked ~ .auto-slides-btn .auto-btn3 {
        background: black;
    }

    #radio4:checked ~ .auto-slides-btn .auto-btn4 {
        background: black;
    }

    /* Best Seller Product */
    .best-seller-product {
        margin: 0 10%;
        margin-top: 3%;
        margin-bottom: 2%;
        text-align: center;
    }

    .topic-wrapper{
        margin-top: 50px;
        text-align: center;
    }

    .topic-wrapper h1 {
        font-size: 35px;
        letter-spacing: 4px;
        text-transform: uppercase;
    }

    .product-wrapper {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        column-gap: 30px;
        row-gap: 50px;
    }

    .product-container {
        text-align: center;
        border: 2px solid lightgray;
        border-radius: 20px;
    }

    .product-container:hover {
        box-shadow: 0 2px 15px 10px lightgray;
        transition: 0.3s;
    }

    .product-container img{
        width: 50%;
    }

    .product-container img.hover-image-left {
        position: absolute;
        width: 295px;
        left: 19.8%;
        z-index: -1;
        opacity: 0;
    }

    .product-container img.hover-image-right {
        position: absolute;
        width: 295px;
        right: 19.8%;
        z-index: -1;
        opacity: 0;
    }

    .product-container img.no-hover{
        opacity: 1;
    }

    .product-container img:hover ~ .hover-image-left {
        opacity: 1;
    }

    .product-container img:hover ~ .hover-image-right {
        opacity: 1;
    }

    .product-detail {
        border-bottom: 2px lightgray solid;
        padding: 20px 0;
    }

    .product-detail span.product-name {
        font-size: 15px;
        letter-spacing: 3px;
        margin: 2px 0;
    }

    .add-to-cart-button {
        background: black;
        border-radius: 0 0 19px 19px;
        padding: 10px;
        cursor: pointer;
    }

    .add-to-cart-button:hover{
        opacity: 0.8;
        transition: 0.3s;
    }

    .add-to-cart-button a {
        text-decoration: none;
        font-size: 20px;
        color: white;
    }

    .product-container i {
        margin-right: 10px;
    }

    .search-form {
        text-align: center;
        margin: 30px 0;
    }

    .searchButton{
        background: transparent;
        padding: 0 20px;
        border-radius: 10px;
        font-size: 20px;
        border: 3px solid;
    }

    .searchBox {
        font-size: 20px;
        padding-left: 2px;
        border: none;
        border-bottom: 2px solid;
        width: 400px;
        margin-left: 10px;
    }

    .searchBox::placeholder {
        font-size: 15px;
    }

    .searchBox:focus {
        outline: none;
    }
</style>
<%@page import="java.util.ArrayList"%>
<jsp:include page="SearchResult" />
<% ArrayList<domain.Product> productList = (ArrayList<domain.Product>) session.getAttribute("productList");%>
<% ArrayList<String> base = (ArrayList<String>) session.getAttribute("base");%>
<%@page import="domain.Product"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>OK Clothing</title>
    </head>
    <body>
        <div class="slide-container">
            <div class="auto-slide-wrapper">
                <input type="radio" name="radio-btn" id="radio1">
                <input type="radio" name="radio-btn" id="radio2">
                <input type="radio" name="radio-btn" id="radio3">
                <input type="radio" name="radio-btn" id="radio4">
                <div class="slide first">
                    <a href="product.jsp"><img src="material/image/slide-image-1.png;" alt="slide-image-1"/></a>
                </div>
                <div class="slide">
                    <a href="product.jsp"><img src="material/image/slide-image-2.jpg;" alt="slide-image-2"/></a>
                </div>
                <div class="slide">
                    <a href="product.jsp"><img src="material/image/slide-image-3.jpg;" alt="slide-image-3"/></a>
                </div>
                <div class="slide">
                    <a href="aboutUs.jsp"><img src="material/image/slide-image-4.jpg;" alt="slide-image-4"/></a>
                </div>
                <div class="auto-slides-btn">
                    <div class="auto-btn1"></div>
                    <div class="auto-btn2"></div>
                    <div class="auto-btn3"></div>
                    <div class="auto-btn4"></div>
                </div>
            </div>
            <div class="manual-slide-wrapper">
                <label for="radio1" class="manual-btn"></label>
                <label for="radio2" class="manual-btn"></label>
                <label for="radio3" class="manual-btn"></label>
                <label for="radio4" class="manual-btn"></label>
            </div>
        </div>
        <form action="SearchResult" method="POST" class="search-form">
            <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
            <input class="searchBox" name="searchResult" id="searchField" type="text" placeholder="Search Product Name">
        </form>
        <div class="topic-wrapper">
            <h1>Best Seller</h1>
        </div>
        <div class="best-seller-product">
            <div class="product-wrapper">

                <%
                    if (productList == null || base == null) {
                %>
                <span>No Product Found</span>
                <%} else {
                    for (int i = 0; i < 4 && i < productList.size(); i++) {
                            Product product = productList.get(i);
                            String result = String.format("%.2f", product.getProductprice());
                %>
                <div class="product-container">
                    <img src="<%=base.get(i) %>" />
                    <div class="product-detail">
                        <span class="product-name"><%=product.getProductname()%></span>
                        <br>
                        <span class="price">RM <%=result%></span>
                    </div>
                    <div class="add-to-cart-button">
                        <a href="productDetails.jsp?productid=<%=product.getProductid()%>"><i class="fa-solid fa-cart-plus"></i>Buy Now</a>
                    </div>
                </div>
                <% }
                        
                    }%>
            </div>
        </div>
        <%@include file = "footer.jsp"%>
    </body>
</html>
<script>
    var counter = 1;
    setInterval(function () {
        document.getElementById('radio' + counter).checked = true;
        counter++;
        if (counter > 4) {
            counter = 1;
        }
    }, 5000);
</script>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>

