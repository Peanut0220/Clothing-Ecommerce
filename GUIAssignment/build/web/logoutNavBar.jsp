<%-- 
    Document   : logoutNavBar
    Created on : Mar 20, 2023, 5:10:12 PM
    Author     : LAW GUAN WEN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<style>
    /* menuNavbar*/
    .menu-nav {
        padding: 0 35px;
    }

    nav {
        position: sticky;
        top: 0;
        background: white;
    }

    nav.navBar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
    }

    .menu-nav .logo {
        width: 20%;
    }

    .menu-nav .logo img {
        width: 80%;
    }

    .menu-list {
        text-align: center;
    }

    .menu-list ul{
        display: flex;
    }

    .menu-list ul li {
        list-style: none;
        padding: 0 25px;
        font-size: 21px;
        letter-spacing: 4px;
    }

    .menu-list ul li a {
        text-decoration: none;
        color: black;
    }

    .menu-list ul li a:after {
        content: '';
        display: block;
        width: 0%;
        height: 2px;
        background: black;
        transition: 0.3s ease-out;
    }

    .menu-list ul li a:hover:after {
        width: 100%;
        transition: 0.2s ease-in;
    }

    .account-cart ul{
        display: flex;
    }

    .account-cart a{
        padding: 0 20px;
        font-size: 1.3em;
        color: black;
    }

    .account-cart a:hover {
        opacity: 0.7;
    }

    .logout-btn {
        position: absolute;
        right: 9.6%;
        top: 25%;
        border: 2px solid;
        background: white;
        padding: 10px;
        font-size: 15px;
        cursor: pointer;
        letter-spacing: 1px;
        border-radius: 11px;
    }

    .logout-btn:hover {
        opacity: 0.7;
    }
    
</style>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <nav>
        <div class="navbar-container" id="navbarOverlay">
            <div class="menu-nav">
                <nav class="navBar">
                    <div class="logo">
                        <a href="index.jsp"><img src="material/image/logo-black(1).png"></a>
                    </div>
                    <div class="menu-list">
                        <ul class="categories">
                            <li><a href="index.jsp">Home</a></li>
                            <li><a href="product.jsp">Product</a></li>
                            <li><a href="aboutUs.jsp">About Us</a></li>
                            <li><a href="FAQ.jsp">FAQ</a></li>
                            <li><a href="ViewOrderStatus.jsp">Order Status</a></li>
                        </ul>
                    </div>
                    <div class="account-cart" >
                        <a href="customerProfile.jsp"><i class="fa-solid fa-user"></i></a>
                        <a href="cart.jsp"><i class="fa-solid fa-cart-shopping"></i></a>
                        <a href="Logout.jsp"><i class="fa-solid fa-sign-out"></i></a>
                    </div>
                </nav>
            </div>
        </div>
    </nav>
</html>

