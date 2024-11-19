<%
    try {
        
    
    if (!(session.getAttribute("staffusername").equals("Manager"))) {

        response.sendRedirect("Restricted.jsp");
    }
%>

<%-- 
    Document   : staffEditProduct
    Created on : Apr 26, 2023, 7:29:33 PM
    Author     : Ng Chong Jian
--%>

<%@page import="java.sql.Blob"%>
<%@page import="java.util.ArrayList"%>
<style>
    * {
        padding: 0;
        box-sizing: border-box;
        
    }

    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    }

    @font-face {
        font-family: "Ubuntu";
        src: url(material/Ubuntu-Regular.ttf);
    }

    body {
        font-family: "Ubuntu";
        animation: 2.5s fadeIn;
    }

    .product-container {
        margin-bottom: 7%;
        margin-left: 20%;
        margin-right: 20%;
        padding-top: 3%;
    }

    .product-container fieldset {
        border: none;
    }

    .product-container fieldset h1 {
        font-size: 40px;
        border-bottom: 3px black solid;
        display: inline-block;
        margin-bottom: 5%;
        font-weight: lighter;
        
    }

    .add-product fieldset {
        border: none;
    }

    .add-product h1 {
        font-size: 40px;


    }

    .add-product {
        background-color: white;
        text-align: center;
        border-radius: 30px;
        border:4px black solid;
        padding-top: 5%;
        padding-bottom: 5%;
    }

    .add-product .add label {
        display:block;
        font-size: 30px;
        width: 30%;
        margin: 0 auto;
        border-bottom: 3.5px black solid;

    }

    .add-product .add input {
        margin-top: 2%;
        padding: 0.7%;
        background: white;

        border: 2px gray solid;
        border-radius: 6px;
        font-size: 20px;
        transition: all 0.25s;
    }

    .add-product .add input:hover {
         border: 2px black solid;
         
    }
    
    #productCategory{
        margin-top: 2%;
        padding: 0.7%;
        background: white;

        border: 2px gray solid;
        border-radius: 6px;
        font-size: 20px;
        transition: all 0.25s;    }
  

    .add-product .add-desc label {
        display: block;
        font-size: 30px;
        width: 25%;
        margin: 0 auto;
        margin-bottom:20px;
        border-bottom: 3.5px black solid;

    }

    .add-product .add-desc textarea {
        padding: 1%;
        background: white;
        font-size: 18px;
        border: 2px gray solid;
        border-radius: 10px;
        transition: all 0.25s;
    }

    .add-product .add-desc textarea:hover {
        border: 2px black solid;
    }

    .submit-cancel {
        text-align: center;
        margin-top: 4%;
        
    }

    .submit-cancel button{
        color: black;
        background-color: transparent;
        border: 3px black solid;
        border-radius: 20px;
        padding: 10px 20px;
        text-decoration: none;
        cursor:pointer;
        transition: all 0.5s;
        font-family: "Ubuntu";
    }


    .submit-cancel button:hover {
        background-color: black;
        color: white;
        transition: all 0.5s;
    }
    input[type=file]{
        color:black;
    }

    input::file-selector-button {
        color: white;

        padding: 5px 10px 5px 10px;
        background-color: black;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        transition: all 0.25s;
    }
    input::file-selector-button:hover {
        color:gray;
        transition: all 0.5s;
    }

    .invalid-feedback{
        color:rgb(220, 20, 60);
        font-size: 12px;
        margin:0;

    }
    .inputt{
        width:70%;
    }

</style>
<%int productid = Integer.parseInt(request.getParameter("productid"));%>
<jsp:include page="/GetProduct?productid=<%=productid%>" />
<% ArrayList<domain.Product> product = (ArrayList<domain.Product>) session.getAttribute("product");%>
<% ArrayList<Blob> base1 = (ArrayList<Blob>) session.getAttribute("img");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/bfb46dc9da.js" crossorigin="anonymous"></script>
        <link rel="icon" type="image/x-icon" href="material/image/small-logo.ico">
        <title>Add Product | OK Clothing</title>
    </head>
    <body>
        <%@include file = "staffNavbar.html"%>
        <form action="UpdateProduct?productid=<%=productid%>" method="POST" enctype="multipart/form-data">
            <div class="product-container">
                <fieldset>
                    <h1>Edit Product</h1>
                    <div class="add-product">
                        <div class="add">
                            <label>Name</label>
                            <input type="text" id="productName" name="productName" class="product-field" value="<%=product.get(0).getProductname()%>"required>
                            <br/>
                            <span style="color:red">${error}</span>
                        </div>
                        <div class="add">
                            <label>Price</label>
                            <input type="number" id="productPrice" name="productPrice" class="product-field" min="1" step="any" title="Numbers only" value="<%=product.get(0).getProductprice()%>"required>
                        </div>
                        <div class="add-desc">
                            <label>Description</label>
                            <textarea rows="5" cols="60" id="productDesciption" name="productDesciption" class="product-field" ><%=product.get(0).getProductdesc()%></textarea>
                        </div>
                        <div class="add">
                            <label>Image</label>
                            <input type="file" id="productImage" name="productImage" class="product-field" value="<%=base1.get(0)%>" onchange="validateFileType()" required>
                        </div>
                        <div class="add">
                            <label>Category</label>
                            <select name="productCategory" id="productCategory">
                                <option value="<%=product.get(0).getCategoryname()%>"><%=product.get(0).getCategoryname()%></option>
                                <option value="T-shirt">T-Shirt</option>
                                <option value="Collared">Collared</option>
                                <option value="Hoodie">Hoodie</option>
                                <option value="Oversized">OverSized</option>
                                <option value="Jacket">Jacket</option>
                            </select>
                        </div>
                        <div class="add">
                            <label>Quantity</label>
                            <input type="text" id="quantity" name="quantity" class="productQuantity" value="<%=product.get(0).getProductQuantity()%>" required>
                        </div>

                           
                    </div>
                    <div class="submit-cancel">
                        <button type="submit" >Submit</button>
                    </div>
                </fieldset>
            </div>
        </form>
        <%@include file = "staffFooter.jsp"%>
        <script type="text/javascript">
            function validateFileType() {
                var fileName = document.getElementById("productImage").value;
                var idxDot = fileName.lastIndexOf(".") + 1;
                var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
                if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {
                    //TO DO
                } else {
                    alert("Only jpg/jpeg and png files are allowed!");
                    document.getElementById("productImage").value="";
                }
            }
        </script>
    </body>
</html>
<%}catch(Exception ex) {
        out.print("Something went wrong");
        out.println("<a href='staffLogin.jsp'>Click me back to login</a>");
    }%>