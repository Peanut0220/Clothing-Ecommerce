<%-- 
    Document   : cart
    Created on : Apr 28, 2023, 10:52:31 PM
    Author     : Tang Qiao Ling, Wong Bao Yi
--%>
<% try {

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Product"%>
<jsp:include page="/getCartLists" />
<% ArrayList<domain.Product> cartProdList = (ArrayList<domain.Product>) session.getAttribute("cartProdList");%>
<% ArrayList<String> base = (ArrayList<String>) session.getAttribute("base");%>

<!DOCTYPE html>
<html>
    <head>
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }
            #title{
                text-align: center;
                padding-top: 18px;
                padding-bottom: 30px;
            }
            
            @font-face {
                font-family: "Ubuntu";
                src: url(material/Ubuntu-Regular.ttf);
            }

            body{
                font-family: "Ubuntu";
            }
            
            #txtOrderComplete,
            #txtPayment,
            #txtPlaceOreder{
                font-size:16px;
                color:rgb(77, 77, 77);
            }
            #txtShoppingbag{
                font-size:17px;
            }

            .container{
                margin-bottom: 70px;
            }
            main .checkout-page{
                /*max-height: 680px;*/
                display: flex;
                justify-content: space-between;
                padding:10px 40px;
                
            }
           

            main .checkout-form .card_item{
                background-color: white;
                padding:20px 0px;
                width:94%;
                font-size: 14px;
                display: flex;
                position: relative;
            }

            main .checkout-form .card_item .close-btn {
                position: absolute;
                right: 10px;
                top: 5px;
            }
            main .checkout-form .card_item .product_img img {
                height: 80px;
                margin-right: 20px;
                width: 70px;
                border-radius: 5px;
            }

            main .checkout-form .card_item .product_info h1 {
                font-size: 20px;
                color: #1e212d;
                margin: 5px 0;
            }

            main .checkout-form .card_item .product_info p {
                color: #a1a1a1;
                font-size: 14px;
            }
            main .checkout-form .card_item .product_rate_info {
                display: flex;
                margin: 5px 0;
                align-items: center;
                justify-content: space-between;
            }

            /* Content Section === Checkout Page === Order Summary */
            #btn{
                padding:20px;
                font-size:17px;
                background-color: black;
                color:white;
                font-weight: bold;
            }
            #btnDesign{
                padding:50px 5px;
            }
            #price{
                font-size:27px;
                font-weight: bold;
            }
            main .order-summary{
                width: 37%;
                height: 260px;
                padding: 20px;
                background-color: white;
            }
            main .order-summary .checkout-total span{
                float: right;
            }
            main .order-summary .checkout-total textarea{
                width: 100%;
                resize: none;
                display: none;
                font-size: 12px;
                padding: 5px;
            }
            main .order-summary .checkout-total h2{
                background-color: var(--darkStaleGray);
                color: var(--khaki);
                padding: 10px;
            }
            main .order-summary .checkout-total ul{
                list-style: none;
                padding: 10px;
            }
            main .order-summary .checkout-total ul li{
                margin-bottom: 10px;
            }


            main .order-summary .checkout-total ul li input[type="submit"]{
                background-color: var(--khaki);
                color: var(--darkStaleGray);
                text-decoration: none;
                text-align: center;
                padding: 5px 8px;
                margin-top: 20px;
                display: block;
                border: none;
                cursor: pointer;
                width: 100%;
            }
            main .order-summary .checkout-total ul li input[type="submit"]:hover{
                background-color: var(--darkStaleGray);
                color: var(--khaki);
            }

            

            #emptyCart{
                width: 30%;
                height: 30%;
                display: block;
                margin-left: auto;
                margin-right: auto;
                border-radius: 50%;
            }

        </style>
        <title>CART</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>  
    <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
    <body>
        <%@ include file="logoutNavBar.jsp" %>

        <%
            if (!(cartProdList.isEmpty())) {
        %>

        <div id="title"><span id="txtShoppingbag"><b>Shopping Bag &nbsp;</b></span><span> > &nbsp;</span> 
            <span id="txtPlaceOreder">Place Order &nbsp; </span> <span> > &nbsp;</span> 
            <span id="txtOrderComplete">Order Complete </span>
        </div>

        <div class="container">
            <main>

                <div class="checkout-form">		
                    <div class="checkout-page">
                        <div class="billing-detail" style="width: 100%; display: grid; ">	
                            <div id="item">
                                <h1 style="padding-bottom: 10px;">ITEM SUMMARY</h1>
                            </div>

                            <%
                                for (int i = 0; i < cartProdList.size(); i++) {

                                    Product product = cartProdList.get(i);

                            %> 
                            <div class="card_item">    
                                <div style="padding-right:5%"><img src ="<%= base.get(i)%>" width="150" height="150"></div>
                                <div class="product_info">                                    
                                    <h1><%=product.getProductname()%></h1>
                                    <p><%=product.getProductdesc()%></p>
                                    <form class="close-btn" action="deleteCartProd">
                                        <input type="hidden" name="id" value="<%=product.getProductid()%>">
                                        <input type="hidden" name="qty" value="<%=product.getProductQuantity()%>">
                                        <button type="submit" style="border: none; background-color: transparent; cursor: pointer;"><i class="fa fa-close"></i></button>
                                    </form>
                                    <div class="product_rate_info"  style="width:200%">
                                        <h1>RM <%=String.format("%.2f", product.getProductprice())%></h1>
                                        <div style="width:50%; padding: 3%">
                                            <form class="pqt-minus" action="minusCartProdQty">
                                                <input type="hidden" name="id" value="<%=product.getProductid()%>">
                                                <input type="hidden" name="qty" value="<%=product.getProductQuantity()%>">
                                                <button type="submit" style="border: none; background-color: transparent; cursor: pointer;">-</button>
                                            </form>
                                            <span class="pqt"><%=product.getProductQuantity()%></span>
                                            <form class="pqt-plus" action="addCartProdQty">
                                                <input type="hidden" name="id" value="<%=product.getProductid()%>">
                                                <input type="hidden" name="qty" value="<%=product.getProductQuantity()%>">
                                                <button type="submit" style="border: none; background-color: transparent; cursor: pointer;">+</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <% }%>
                            <br><br>                            
                        </div>                        
                        <form class="order-summary" action="getCartDetails">
                            <div class="checkout-total">
                                <h2>Order Summary</h2>
                                <ul>
                                    <li>Subtotal : <span id="price">RM <%= String.format("%.2f", session.getAttribute("subtotal"))%></span></li>
                                    <li id="btnDesign"><input id="btn" type="submit" name="order" value="Checkout Now"></li>
                                </ul>
                            </div>                           
                        </form>

                    </div>
                </div> <!-- End of Checkout Form -->
            </main> <!-- Main Area -->
        </div>	
        <% } else {%>

        <a href="product.jsp"><img src="material/image/cartEmpty.jpg" id="emptyCart"></a>
        <h2 style="text-align: center;">Your cart is empty!</h2>
        <br><br>
        <% }%>
        <%@ include file="footer.jsp" %>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>

