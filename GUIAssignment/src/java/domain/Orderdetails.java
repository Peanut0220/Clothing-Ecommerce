/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;


public class Orderdetails {
    private int orderdetailsid;
    private int quantity;
    private int prodid;
    private int ordersid;

    public Orderdetails() {
        
    }
    
    public Orderdetails(int orderdetailsid, int quantity, int ordersid, int prodid ) {
        this.orderdetailsid = orderdetailsid;
        this.quantity = quantity;
        this.prodid = prodid;
        this.ordersid = ordersid;
    }

    public int getOrderdetailsid() {
        return orderdetailsid;
    }

    public void setOrderdetailsid(int orderdetailsid) {
        this.orderdetailsid = orderdetailsid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public int getOrdersid() {
        return ordersid;
    }

    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }
    
    
}
