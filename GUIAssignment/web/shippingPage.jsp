<% try {

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Product"%>
<jsp:include page="/getCartLists" />
<% ArrayList<domain.Product> cartProdList = (ArrayList<domain.Product>) session.getAttribute("cartProdList");%>
<%ArrayList<Double> cartProdPrice = (ArrayList<Double>) session.getAttribute("cartProdPrice");%>
<!DOCTYPE html>
<html>
    <head>
        <style>

            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
            }

            @font-face {
                font-family: "Ubuntu";
                src: url(material/Ubuntu-Regular.ttf);
            }

            body{
                background-color: rgba(242, 242, 242,0.5);
                font-family: "Ubuntu";
            }

            #title{
                text-align: center;
                padding-top: 18px;
                padding-bottom: 30px;
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
                display: flex;
                justify-content: space-between;
                padding:10px 30px;
            }

            main .checkout-page .billing-detail{
                width: 60%;
            }
            main .checkout-page .billing-detail h2{
                display: block;
                color: var(--darkStaleGray);
                background-color: var(--khaki);
                padding: 5px;
                margin-bottom: 10px;
            }
            main form.checkout-form {
                margin-bottom: 30px;
            }


            main form.checkout-form .form-group{
                margin: 10px 5px;
                width: calc(100% - 10px);
            }
            main form.checkout-form label{
                color: var(--darkStaleGray);
                display: block;
                margin-bottom: 6px;
            }
            main form.checkout-form .form-group input,
            main form.checkout-form .form-group textarea{
                width: 100%;
                padding: 8px 5px;
            }
            main form.checkout-form input:focus,
            main form.checkout-form textarea:focus{
                outline: 1px solid var(--darkStaleGray);
            }
            main form.checkout-form .form-inline{
                display: flex;
                justify-content: space-between;
            }
            main form.checkout-form .form-inline .form-group{
                width: 48%;
            }


            /* Content Section === Checkout Page === Order Summary */
            main .order-summary{
                width: 30%;
                height: 320px;
                padding: 5px;
                border: 1px solid rgba(236, 240, 241,0.3);
            }
            main .order-summary .checkout-total span{
                float: right;
            }

            main .order-summary .checkout-total h3{
                background-color: var(--darkStaleGray);
                color: var(--khaki);
                text-transform: uppercase;
                padding: 10px;
            }
            main .order-summary .checkout-total ul{
                list-style: none;
                padding-top:10px;
            }
            main .order-summary .checkout-total ul li{
                margin-bottom: 10px;
            }



            main .order-summary .checkout-total ul li input[type="submit"]{
                background-color: var(--khaki);
                color: var(--darkStaleGray);
                text-transform: uppercase;
                text-decoration: none;
                text-align: center;
                padding: 5px 8px;
                margin-top: 20px;
                display: block;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            #shippingbtn{
                padding:15px 80px;
                font-size:17px;
                background-color: black;
                font-weight: bold;
                color:white;
                cursor: pointer;
            }
            #button{
                padding:10px 250px;

            }
            #btn{
                padding:20px;
                font-size:17px;
                background-color: black;
                color:white;
                font-weight: bold;
            }
            #btnDesign{
                padding:20px 5px;
            }
            #shipFee,
            #price,
            #item{
                font-size:16px;
            }
            #total{
                font-size:27px;
                font-weight: bold;
            }
            main .order-summary{
                width: 32%;
                padding: 15px;
            }
            main .order-summary .checkout-total span{
                float: right;
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
                margin-bottom: 15px;
            }
            #nextline{
                padding-top:35px;
            }


            #cardpart{
                background-color: white;
            }

            #cardDetails{
                margin:0 30px;
                padding-top:10px;
                display:none;
            }
            main form.checkout-form .paymentMeth{
                background-color: white;
                margin-top:15px;
                padding:20px;
                width:100%;
            }
            main form.checkout-form .paymentMeth .text{
                margin-left:10px;
            }

            main form.checkout-form .card_item{
                background-color: white;
                margin-top: 15px;
                padding:20px;
                width:100%;
                font-size: 14px;
                display: flex;
                position: relative;
            }
            main form.checkout-form .card_item .product_img img {
                height: 80px;
                margin-right: 20px;
                width: 70px;
                border-radius: 5px;
            }

            main form.checkout-form .card_item .product_info h1 {
                font-size: 20px;
                color: #1e212d;
                margin: 5px 0;
            }

        </style>
        <title>CHECKOUT PAGE</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>

    </head>
    <body>
        <%@ include file="logoutNavBar.jsp" %>
        <div id="title"><span>Shopping Bag &nbsp;</span><span> > &nbsp;</span> 
            <span id="placeorder"><b>Place Order &nbsp; </b></span> <span> > &nbsp;</span> 
            <span>Order Complete </span>
        </div>
        <div class="container">
            <main>
                <div class="checkout-page">
                    <div class="billing-detail">					
                        <form class="checkout-form"  action="addOrders" method="POST">
                            <h2>Shipping Address</h2>
                            <input type="hidden" name="orderStatus" value="Packaging">
                            <div class="form-inline">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input type="text" id="fname" value="${customer.custfirstname}" readonly> 
                                </div>
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input type="text" id="lname"  value="${customer.custlastname}" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Phone Number</label>
                                <input type="text" id="phoneNum" value="${customer.custphonenum}" readonly>
                            </div>

                            <div class="form-group">
                                <label>Address</label>
                                <textarea style="resize:none" id="address" name="address" rows="5" required></textarea>
                            </div>

                            <h2 id="nextline">Payment Method</h2>
                            <div class="paymentMeth">
                                <input type="radio" name="payment" value="COD" onclick="myFunction2()" required="required">
                                <span class="text">Cash on delivery</span>
                            </div>
                            <div class="paymentMeth">
                                <input type="radio" name="payment" value="CARD" onclick="myFunction()">
                                <span class="text">Credit / Debit Card</span>
                            </div>
                            <div id="cardpart">
                                <div id="cardDetails">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input type="text" id="name" name="cardName" placeholder="TAN LI LI" >
                                    </div>

                                    <div class="form-group">
                                        <label>Card Number</label>
                                        <input type="text" id="cnum" name="cardNum" placeholder="0000 0000 0000 0000" pattern="[0-9]{4} [0-9]{4} [0-9]{4} [0-9]{4}" title="Example : 1234 1234 5678 5678">
                                    </div>

                                    <div class="form-inline">
                                        <div class="form-group">
                                            <label>Expiration Date(MM/YY)</label>
                                            <input type="text" id="expDate" name="expDate" placeholder="00/00" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" title="Example : 12/02">
                                        </div>
                                        <div class="form-group">
                                            <label>CVV</label>
                                            <input type="text" id="cvv" name="cvv" placeholder="000" pattern="[0-9]{3}" title="Example : 311">
                                        </div>
                                    </div>  
                                </div>
                            </div>
                    </div>

                    <div class="order-summary">
                        <div class="checkout-total">
                            <h2>Order Summary</h2>
                            <ul>
                                <%
                                    for (int i = 0; i < cartProdList.size(); i++) {

                                        Product product = cartProdList.get(i);

                                %> 
                                <li><%=product.getProductname()%><span id="item">RM <%= String.format("%.2f", cartProdPrice.get(i))%></span></li>
                                    <% }%>
                                <hr><br>
                                <li>Subtotal : <span id="price">RM <%= String.format("%.2f", session.getAttribute("subtotal"))%></span></li>
                                <li>Shipping Fee : <span id="shipFee">RM <%= String.format("%.2f", session.getAttribute("shippingFee"))%></span></li>
                                <li>Discount : <span id="discount">- RM <%= String.format("%.2f", session.getAttribute("discount"))%></span></li>
                                <li>Total : <span id="total">RM <%= String.format("%.2f", session.getAttribute("total"))%></span></li>

                                <li id="btnDesign"><input id="btn" type="submit" name="order" value="Pay Now"></li>

                            </ul>
                        </div>

                        </form><!-- End of Checkout Form -->

                    </div>
                </div>
            </main> <!-- Main Area -->
        </div>	
        <%@ include file="footer.jsp" %>

        <script>
            function myFunction() {
                document.getElementById("cardDetails").style.display = "block";
            }
            function myFunction2() {
                document.getElementById("cardDetails").style.display = "none";
            }
            
        </script>
    </body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>

