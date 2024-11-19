<% try {

%>

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
        box-sizing: border-box;
        padding: 0;
        margin: 0;
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu" !important;
    }

    .main-menu{
        color: black;
        font-size: 20px;
        font-weight: 900;
    }
    
    .page-section {
        position: unset;
        z-index: -1;    
    }
    
    a.link-menu{
        text-decoration: none;
        color: #c0aa83;
    }

    .rounded {
        border-radius: 0.25rem !important;
    }

    .copyright{
        color:#c0aa83;
        font-size: 14px;
        font-weight: 300;
    }

    .boxed{
        background-color: #c0aa83;
        height: 150px;
        margin-left: 80px;
        margin-right: 80px;
        padding-top: 15px;
        padding-left: 20px;
    }

    .contact-heading {
        color: #c0aa83;
        font-size: 30px;
        font-weight: 300;
    }

    .contact-info{
        color: #fff;
        font-size: 18px;
    }

    a.explore-link{
        color: #fff;
        font-size: 15px;
        text-decoration: none;
    }

    .logo-text-white{
        color: #fff;
        font-size: 16px;
    }

    /*PRODUCT PAGE STYLING*/

    .blog-sidebar-title{
        color: #242424;
        font-size: 25px;
        font-weight: 900;
    }

    .blog-sidebar-list{
        color: #242424;
        font-size: 16px;
        font-weight: 900;
    }

    .blog-sidebar-text{
        color: #242424;
        font-size: 16px;
        font-weight: 200;
    }

    .blog-form{
        background-color: white;
        padding: 50px;
        height: 1000px;
    }

    .list-icon{
        color: #c0aa83;
    }

    .tags{
        color: #242424;
        font-size: 20px;
        font-weight: 200;
    }

    .tags{
        color: #242424;
        font-size: 20px;
        font-weight: 200;
    }

    .card-title{
        color: black;
        font-size: 18px;
        font-weight: 900;
        padding: 10px;
    }

    .card-text{
        font-size: 13px;
    }

    .price-tag{
        color: #c0aa83;
        font-size: 25px;
        font-weight: 900;
    }

    .button-text{
        font-size: 14px;
        font-weight: 900;
    }

    .product-image{
        width: 200px;
        height: 200px;
    }

    #collar,#hoodie,#jacket,#tshirt,#oversized {
        border: none;
        background: transparent;
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
    
    .category-button {
        display: grid;
        columns: 1;
        transition: 1s all;
    }
    
    .category-button button {
        border: none;
        background: none;
        margin: 10px 0;
        transition: 1s all;
        font-size: 17px;
    }
    .category-button button:hover {
        color: gray;
        transition: 0.5s all;
    }
    
    .btn-success {
        background: black !important;
        color: white !important;
        border: none !important;
    }
</style>

<jsp:include page="SearchResult" />
<%@page import="java.util.ArrayList"%>
<% ArrayList<domain.Product> productList = (ArrayList<domain.Product>) session.getAttribute("productList");%>
<% ArrayList<String> base = (ArrayList<String>) session.getAttribute("base");%>
<%@page import="domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, intial-scale=1, shrink-to-fit=no">
        <title>OK Clothing | Products</title>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css2?family=Kanit&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <form action="SearchResult" method="POST" class="search-form">
            <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
            <input class="searchBox" name="searchResult" id="searchField" type="text" placeholder="Search Product Name">
        </form>
        <header class="page-section masthead2">
            <div class="container h-50">

            </div>
        </header>
        <section class="page-section">
            <div class="container" style="max-width:1430px;">
                <div class="row">
                    <div class="col-lg-2 blog-form">
                        <h2 class="blog-sidebar-title"><b>Categories</b></h2>
                        <hr/>
                        <form action="SearchResult" method="POST" class="category-button">
                            <button type="submit" name="category" value="Hoodie">Hoodie</button>
                            <button type="submit" name="category" value="T-Shirt">T-Shirt</button>
                            <button type="submit" name="category" value="Collared">Collared</button>
                            <button type="submit" name="category" value="Jacket">Jacket</button>
                            <button type="submit" name="category" value="Oversized">Oversized</button>
                        </form>
                        <div>&nbsp;</div>
                        <div>&nbsp;</div>
                    </div>
                    <div class="col-lg-10" style="padding-left: 30px;">
                        <div class="row">
                            <div class="col">
                                Showing all <%=productList.size()%> results
                            </div>
                        </div>
                        <div>&nbsp;</div>
                        <div>&nbsp;</div>
                        <div class="row">
                            <%
                                if (productList == null || base == null) {
                            %>
                            <h1>No result</h1>
                            <%} else {
                                for (int i = 0; i < productList.size(); i++) {
                                    Product product = productList.get(i);
                                    String result = String.format("%.2f", product.getProductprice());
                            %> 
                            <div class="col-sm-3 col-md-6 col-lg-4">
                                <div class="card">
                                    <div class="card-body text-center">
                                        <img src ="<%= base.get(i)%>"width="150" height="150"/>
                                        <h5 class="card-title"><b><%=product.getProductname()%></b></h5>
                                        <p class="tags">RM <%=result%></p>
                                        <a href="productDetails.jsp?productid=<%=product.getProductid()%>" class="btn btn-success button-text"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Buy Now </a>
                                    </div>
                                </div>
                                <br>
                            </div>
                            <% }
                                }%>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div>&nbsp;</div>
        <div>&nbsp;</div>
        <div>&nbsp;</div>
        <%@include file ="footer.jsp" %>

    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>