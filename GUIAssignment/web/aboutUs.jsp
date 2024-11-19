<%-- 
    Document   : aboutUs
    Created on : Mar 28, 2023, 9:53:19 AM
    Author     : LAW GUAN WEN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (session.getAttribute("username") == null) {
%>
<%@include file = "navbar.jsp"%>
<%}
else if (session.getAttribute("username") != null) {
%>
<%@include file = "logoutNavBar.jsp"%>
<%}%>

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
    
    .container {
        margin: 6%;
    }
    
    .heading {
        margin-bottom: 25px;
    }
    
    .heading h1 {
        font-size: 40px;
        text-transform: uppercase;
    }
    
    .heading h1:after {
        content: '';
        background: gray;
        height: 4px;
        width: 8%;
        display: block;
    }
    
    .content p{
        font-size: 20px;    
        margin: 30px 0;
        font-weight: 100;
        line-height: 37px;
    }
</style>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
    </head>
    <body>
        <div class="container">
            <div class="heading">
                <h1>About Us</h1>
            </div>
            <div class="content">
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam suscipit facilisis dui. Nulla nec sapien metus. In rutrum tellus at 
                    interdum blandit. Phasellus eu consectetur nisi, at ultrices ipsum. Aliquam ut leo diam. Maecenas maximus vel ligula quis ultricies. 
                    Aliquam venenatis faucibus lectus ac aliquet. Aliquam at nunc sed ipsum pellentesque mattis. Integer placerat dui lacus, et sodales ante scelerisque eget. 
                    Integer id tempus ante, quis tristique nunc. Praesent consequat sollicitudin leo at scelerisque.
                </p>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam suscipit facilisis dui. Nulla nec sapien metus. In rutrum tellus at 
                    interdum blandit. Phasellus eu consectetur nisi, at ultrices ipsum. Aliquam ut leo diam. Maecenas maximus vel ligula quis ultricies. 
                    Aliquam venenatis faucibus lectus ac aliquet. Aliquam at nunc sed ipsum pellentesque mattis. Integer placerat dui lacus, et sodales ante scelerisque eget. 
                    Integer id tempus ante, quis tristique nunc. Praesent consequat sollicitudin leo at scelerisque.
                </p>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam suscipit facilisis dui. Nulla nec sapien metus. In rutrum tellus at 
                    interdum blandit. Phasellus eu consectetur nisi, at ultrices ipsum. Aliquam ut leo diam. Maecenas maximus vel ligula quis ultricies. 
                    Aliquam venenatis faucibus lectus ac aliquet. Aliquam at nunc sed ipsum pellentesque mattis. Integer placerat dui lacus, et sodales ante scelerisque eget. 
                    Integer id tempus ante, quis tristique nunc. Praesent consequat sollicitudin leo at scelerisque.
                </p>
            </div>
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>
