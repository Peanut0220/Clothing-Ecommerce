/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Date;


public class Orders {
    private int orderid;
    private Date orderdate;
    private String orderaddress;
    private String orderstatus;
    private double shippingfee;
    private double subtotal;
    private double total;
    private int custid;
    private Date estimdate;
    private int trackingnum;

    public Orders() {
    }

    public Orders(int orderid, Date orderdate, String orderaddress, String orderstatus, double shippingfee, double subtotal, double total, int custid) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfee = shippingfee;
        this.subtotal = subtotal;
        this.total = total;
        this.custid = custid;
    }
    public Orders(int orderid, Date orderdate, String orderaddress, String orderstatus, double shippingfee, double subtotal, double total, int custid,Date estimdate, int trackingnum) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfee = shippingfee;
        this.subtotal = subtotal;
        this.total = total;
        this.custid = custid;
        this.estimdate = estimdate;
        this.trackingnum = trackingnum;
    }

    public Orders(Date orderdate, String orderaddress, String orderstatus, double shippingfees, double subtotal, double total, int custid, Date estimdate, int trackingnum) {
        this.orderdate = orderdate;
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfee = shippingfees;
        this.subtotal = subtotal;
        this.total = total;
        this.custid = custid;
        this.estimdate = estimdate;
        this.trackingnum = trackingnum;
    }
    
    public Orders(String orderaddress, String orderstatus, double shippingfee, double subtotal, double total, int custid) {
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfee = shippingfee;
        this.subtotal = subtotal;
        this.total = total;
        this.custid = custid;
    }
    
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public double getShippingfee() {
        return shippingfee;
    }

    public void setShippingfee(double shippingfee) {
        this.shippingfee = shippingfee;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public Date getEstimdate() {
        return estimdate;
    }

    public void setEstimdate(Date estimdate) {
        this.estimdate = estimdate;
    }

    public int getTrackingnum() {
        return trackingnum;
    }

    public void setTrackingnum(int trackingnum) {
        this.trackingnum = trackingnum;
    }
    
}
