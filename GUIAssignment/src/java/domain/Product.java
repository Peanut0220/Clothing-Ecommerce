/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Blob;

public class Product {

    private int productid;
    private String productname;
    private double productprice;
    private String productdesc;
    private String categoryname;
    private Blob prodimage;
    private int productQuantity;

    public Product(int productid, String productname, double productprice, String productdesc, String categoryname, Blob prodimage, int productQuantity) {
        this.productid = productid;
        this.productname = productname;
        this.productprice = productprice;
        this.productdesc = productdesc;
        this.categoryname = categoryname;
        this.prodimage = prodimage;
        this.productQuantity = productQuantity;
    }

    public Product(String productname, double productprice, String productdesc, String categoryname, Blob prodimage, int productQuantity) {
        this.productname = productname;
        this.productprice = productprice;
        this.productdesc = productdesc;
        this.categoryname = categoryname;
        this.prodimage = prodimage;
        this.productQuantity = productQuantity;
    }
    
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Blob getProdimage() {
        return prodimage;
    }

    public void setProdimage(Blob prodimage) {
        this.prodimage = prodimage;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
   
}

