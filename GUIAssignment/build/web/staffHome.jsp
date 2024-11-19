<%
    try {
        
    
%>

<%-- 
    Document   : staffHome
    Created on : Apr 22, 2023, 5:13:58 PM
    Author     : Ng Chong Jian
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<jsp:include page="/LoadingInitServlet" />  
<% session.getAttribute("staffusername"); %>
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
    
    .content1 {

        padding-top: 10%;
        margin-left: 8%;
        margin-right: 8%;
    }

    .content1 .content-title {
        display: flex;
        justify-content: space-between;
        background: url(image/gaming3.jpg) no-repeat;
        background-position: right;
        background-size: 50% 100%;
        height: 100vh;
    }

    .content-title .main-title h1{
        font-size: 95px;
        width: 60%;
        overflow-wrap: break-word;
        line-height: 95%;
    }

    .content-title .main-title p {
        font-size: 21px;
        width: 100%;
        overflow-wrap: break-word;
        color: rgb(104, 104, 104);
        margin-top: 4.5%;
    }

</style>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>OK Clothing</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
       <div class="content">
            <div class="content-background">
                <div class="content1">
                    <div class="content-title">
                        <div class="main-title">
                            <h1>Welcome back,</h1>
                            <h1>${staffusername}</h1>
                            <p>
                                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Omnis assumenda quas suscipit eaque excepturi 
                                incidunt voluptates exercitationem aliquid architecto odio, officia, perferendis ipsum earum eveniet iure 
                                accusantium ad fugiat porro.Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        <%@include file = "staffFooter.jsp"%>
    </body>
</html>
<%}catch(Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>