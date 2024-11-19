<% try {

%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*, Entity.*"%>
<jsp:useBean id="order" scope="session" class="Entity.Orders" />
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Product"%>
<jsp:include page="/getCartLists" />
<% ArrayList<domain.Product> cartProdList = (ArrayList<domain.Product>) session.getAttribute("cartProdList");%>
<% ArrayList<String> base = (ArrayList<String>) session.getAttribute("base");%>
<%ArrayList<Double> cartProdPrice = (ArrayList<Double>) session.getAttribute("cartProdPrice");%>
<!DOCTYPE html>
<html>
    <head>
        <title>Order Complete</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>

        <style type="text/css">

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
                font-family: "Ubuntu";
            }

            body, table, td, a {
                -webkit-text-size-adjust: 100%;
                -ms-text-size-adjust: 100%;
            }
            table, td {
                mso-table-lspace: 0pt;
                mso-table-rspace: 0pt;
            }
            img {
                -ms-interpolation-mode: bicubic;
            }

            img {
                border: 0;
                height: auto;
                line-height: 100%;
                outline: none;
                text-decoration: none;
            }
            table {
                border-collapse: collapse !important;
            }
            body {
                height: 100% !important;
                margin: 0 !important;
                padding: 0 !important;
                width: 100% !important;
            }


            a[x-apple-data-detectors] {
                color: inherit !important;
                text-decoration: none !important;
                font-size: inherit !important;
                font-family: inherit !important;
                font-weight: inherit !important;
                line-height: inherit !important;
            }

            @media screen and (max-width: 480px) {
                .mobile-hide {
                    display: none !important;
                }
                .mobile-center {
                    text-align: center !important;
                }
            }
            div[style*="margin: 16px 0;"] {
                margin: 0 !important;
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

        </style>
        <%
            Date Estimdate = order.getEstimdate();
            Date orderDate = order.getOrderdate();
            String EsdateFormat = new String("");
            String OrderdateFormat = new String("");
            SimpleDateFormat format = new SimpleDateFormat("dd - MM - YYYY");
            OrderdateFormat = format.format(orderDate);
            EsdateFormat = format.format(Estimdate);
        %>

    <body bgcolor="#eeeeee">


        <div style="display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;">
            For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them. 
        </div>

        <%@ include file="logoutNavBar.jsp" %>
        <div id="title"><span>Shopping Bag &nbsp;</span><span> > &nbsp;</span> 
            <span>Place Order &nbsp; </span> <span> > &nbsp;</span> 
            <span id="orderComplete"><b>Order Complete </b></span>
        </div>


        <table border="0" cellpadding="0" cellspacing="0" width="100%">

            <tr>
                <td align="center" style="padding: 35px 35px 20px 35px; background-color: #ffffff;" bgcolor="#ffffff">
                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                        <tr>
                            <td align="center" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;">
                                <img src="material/image/tickGreen.png" width="125" height="120" style="display: block; border: 0px;" /><br>
                                <h2 style="font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;">
                                    Thank You For Your Order!
                                </h2>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;">
                                <p style="font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;">
                                    Your order has been received and is now being processed. Your order details are shown below for you reference:
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" style="padding-top: 30px;">
                                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                    <tr>
                                        <td width="75%" align="left" bgcolor="#eeeeee" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                            Order Number #
                                        </td>
                                        <td width="25%" align="left" bgcolor="#eeeeee" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                            <%= order.getOrderid()%>
                                        </td>
                                    </tr>
                                    <%
                                        for (int i = 0; i < cartProdList.size(); i++) {

                                            Product product = cartProdList.get(i);

                                    %> 
                                    <tr>

                                        <td width="75%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 10px;">
                                            <%=product.getProductname()%> (<%=product.getProductQuantity()%>)
                                        </td>
                                        <td width="25%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 10px;">
                                            RM <%= String.format("%.2f", cartProdPrice.get(i))%>
                                        </td>

                                    </tr>
                                    <% }%>
                                    <tr>
                                        <td width="75%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 10px;">
                                            Shipping
                                        </td>
                                        <td width="25%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 10px;">
                                            RM <%= String.format("%.2f", order.getShippingfees())%>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td width="75%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 10px;">
                                        Discount
                                    </td>
                                    <td width="25%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 10px 0px;">
                                        - RM <%= String.format("%.2f", session.getAttribute("discount"))%>
                                    </td>
                                </tr>

                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" style="padding-top: 10px;">
                                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                    <tr>
                                        <td width="75%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                            TOTAL
                                        </td>
                                        <td width="25%" align="left" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                            RM <%= String.format("%.2f", order.getTotal())%>
                                        </td>
                                    </tr>
                                </table>

                            </td>

                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td align="center" style="padding: 10px 35px 20px 35px; background-color: #ffffff;" bgcolor="#ffffff">
                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">


                        <tr>
                            <td align="left" style="padding-top: 20px;">
                                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                    <tr>
                                        <td width="75%" align="left" bgcolor="#eeeeee" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                            Tracking Number #
                                        </td>
                                        <td width="25%" align="left" bgcolor="#eeeeee" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                            <%= order.getTrackingnum()%>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" style="padding-top: 10px;">
                                <table cellspacing="0" cellpadding="0" border="0" width="100%">

                                </table>

                            </td>

                        </tr>
                    </table>

                </td>
            </tr>
            <tr>
                <td align="center" height="100%" valign="top" width="100%" style="padding: 0 35px 50px 35px; background-color: #ffffff;" bgcolor="#ffffff">
                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:660px;">
                        <tr>
                            <td align="center" valign="top" style="font-size:0;">
                                <div style="display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;padding-left:39px;">
                                    <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                        <tr>

                                            <td align="left" valign="top" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;">
                                                <p style="font-weight: 800;">Order Date</p>
                                                <p><%=OrderdateFormat%></p>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div style="display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;padding-left:80px;">
                                    <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                        <tr>


                                            <td align="left" valign="top" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;">
                                                <p style="font-weight: 800;">Payment Method</p>
                                                <p><%= session.getAttribute("paymentM")%></p>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div style="display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;padding-left:39px;padding-top: 20px;">

                                    <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                        <tr>
                                            <td align="left" valign="top" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;">
                                                <p style="font-weight: 800;">Delivery Address</p>
                                                <p><%= order.getOrderaddress()%></p>

                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div style="display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;padding-left:80px;padding-top: 20px;">
                                    <table align="left" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:300px;">
                                        <tr>

                                            <td align="left" valign="top" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;">
                                                <p style="font-weight: 800;">Estimated Delivery Date</p>
                                                <p><%=EsdateFormat%></p>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td align="center" style="padding: 35px 35px 50px 35px; background-color: #ffffff;" bgcolor="#ffffff">
                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">


                        <tr>
                            <td align="center" style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;">
                                <form action = "ViewOrderStatus.jsp">
                                    <button style="padding:20px;font-size:17px;background-color: black;color:white;font-weight: bold;cursor: pointer;">
                                        Go to Order Status
                                    </button>
                                </form>
                            </td>
                        </tr>

                    </table>

                </td>

            </tr>
        </table>

    </td>
</tr>
</table>
<%@ include file="footer.jsp" %>
</body>
</html>
<%} catch (Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='login-register.jsp'>Click me back to login</a>");
    }%>
