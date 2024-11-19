/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "CARTDETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cartdetails.findAll", query = "SELECT c FROM Cartdetails c"),
    @NamedQuery(name = "Cartdetails.findByCartdetailsid", query = "SELECT c FROM Cartdetails c WHERE c.cartdetailsid = :cartdetailsid"),
    @NamedQuery(name = "Cartdetails.findByProdid", query = "SELECT c FROM Cartdetails c WHERE c.prodid = :prodid"),
    @NamedQuery(name = "Cartdetails.findByQuantity", query = "SELECT c FROM Cartdetails c WHERE c.quantity = :quantity")})
public class Cartdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CARTDETAILSID")
    private Integer cartdetailsid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODID")
    private int prodid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
    @JoinColumn(name = "CARTID", referencedColumnName = "CARTID")
    @ManyToOne(optional = false)
    private Cart cartid;

    public Cartdetails() {
    }

    public Cartdetails(Integer cartdetailsid) {
        this.cartdetailsid = cartdetailsid;
    }

    public Cartdetails(Cart cartid, int prodid, int quantity) {
        this.prodid = prodid;
        this.quantity = quantity;
        this.cartid = cartid;
    }

    public Cartdetails(int prodid, int quantity) {
        this.prodid = prodid;
        this.quantity = quantity;
    }
    
    public Integer getCartdetailsid() {
        return cartdetailsid;
    }

    public void setCartdetailsid(Integer cartdetailsid) {
        this.cartdetailsid = cartdetailsid;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCartid() {
        return cartid;
    }

    public void setCartid(Cart cartid) {
        this.cartid = cartid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartdetailsid != null ? cartdetailsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartdetails)) {
            return false;
        }
        Cartdetails other = (Cartdetails) object;
        if ((this.cartdetailsid == null && other.cartdetailsid != null) || (this.cartdetailsid != null && !this.cartdetailsid.equals(other.cartdetailsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Cartdetails[ cartdetailsid=" + cartdetailsid + " ]";
    }
    
}
