/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderid", query = "SELECT o FROM Orders o WHERE o.orderid = :orderid"),
    @NamedQuery(name = "Orders.findByOrderdate", query = "SELECT o FROM Orders o WHERE o.orderdate = :orderdate"),
    @NamedQuery(name = "Orders.findByOrderaddress", query = "SELECT o FROM Orders o WHERE o.orderaddress = :orderaddress"),
    @NamedQuery(name = "Orders.findByOrderstatus", query = "SELECT o FROM Orders o WHERE o.orderstatus = :orderstatus"),
    @NamedQuery(name = "Orders.findByShippingfees", query = "SELECT o FROM Orders o WHERE o.shippingfees = :shippingfees"),
    @NamedQuery(name = "Orders.findBySubtotal", query = "SELECT o FROM Orders o WHERE o.subtotal = :subtotal"),
    @NamedQuery(name = "Orders.findByTotal", query = "SELECT o FROM Orders o WHERE o.total = :total"),
    @NamedQuery(name = "Orders.findByEstimdate", query = "SELECT o FROM Orders o WHERE o.estimdate = :estimdate"),
    @NamedQuery(name = "Orders.findByTrackingnum", query = "SELECT o FROM Orders o WHERE o.trackingnum = :trackingnum")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERID")
    private Integer orderid;
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ORDERADDRESS")
    private String orderaddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ORDERSTATUS")
    private String orderstatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPPINGFEES")
    private double shippingfees;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL")
    private double subtotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @Column(name = "ESTIMDATE")
    @Temporal(TemporalType.DATE)
    private Date estimdate;
    @Column(name = "TRACKINGNUM")
    private Integer trackingnum;
    @JoinColumn(name = "CUSTID", referencedColumnName = "CUSTID")
    @ManyToOne(optional = false)
    private Customer custid;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERID")
    private List<Orderdetails> orderdetailsList = new ArrayList<>();

        public Orders() {
    }

    public Orders(Integer orderid) {
        this.orderid = orderid;
    }

    public Orders(Date orderdate, String orderaddress, String orderstatus, double shippingfees, double subtotal, double total, Date estimdate, Integer trackingnum, Customer custid) {
        this.orderdate = orderdate;
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfees = shippingfees;
        this.subtotal = subtotal;
        this.total = total;
        this.estimdate = estimdate;
        this.trackingnum = trackingnum;
        this.custid = custid;
    }
    

    public Orders(Integer orderid, String orderaddress, String orderstatus, double shippingfees, double subtotal, double total) {
        this.orderid = orderid;
        this.orderaddress = orderaddress;
        this.orderstatus = orderstatus;
        this.shippingfees = shippingfees;
        this.subtotal = subtotal;
        this.total = total;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
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

    public double getShippingfees() {
        return shippingfees;
    }

    public void setShippingfees(double shippingfees) {
        this.shippingfees = shippingfees;
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

    public Date getEstimdate() {
        return estimdate;
    }

    public void setEstimdate(Date estimdate) {
        this.estimdate = estimdate;
    }

    public Integer getTrackingnum() {
        return trackingnum;
    }

    public void setTrackingnum(Integer trackingnum) {
        this.trackingnum = trackingnum;
    }

    public Customer getCustid() {
        return custid;
    }

    public void setCustid(Customer custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Orders[ orderid=" + orderid + " ]";
    }
    
}
