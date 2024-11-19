/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProdid", query = "SELECT p FROM Product p WHERE p.prodid = :prodid"),
    @NamedQuery(name = "Product.findByProdname", query = "SELECT p FROM Product p WHERE p.prodname = :prodname"),
    @NamedQuery(name = "Product.findByProdprice", query = "SELECT p FROM Product p WHERE p.prodprice = :prodprice"),
    @NamedQuery(name = "Product.findByProddesc", query = "SELECT p FROM Product p WHERE p.proddesc = :proddesc"),
    @NamedQuery(name = "Product.findByCategoryname", query = "SELECT p FROM Product p WHERE p.categoryname = :categoryname"),
    @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Product.findById", query = "SELECT p.prodid, p.prodname FROM Product p")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODID")
    private Integer prodid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRODNAME")
    private String prodname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODPRICE")
    private double prodprice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PRODDESC")
    private String proddesc;
    @Size(max = 60)
    @Column(name = "CATEGORYNAME")
    private String categoryname;
    @Lob
    @Column(name = "PRODIMAGE")
    private Serializable prodimage;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Product() {
    }

    public Product(Integer prodid) {
        this.prodid = prodid;
    }

    public Product(Integer prodid, String prodname, double prodprice, String proddesc) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodprice = prodprice;
        this.proddesc = proddesc;
    }

    public Product(Integer prodid, String prodname, double prodprice, String proddesc, String categoryname) {
        this.prodid = prodid;
        this.prodname = prodname;
        this.prodprice = prodprice;
        this.proddesc = proddesc;
        this.categoryname = categoryname;
    }
    
    public Integer getProdid() {
        return prodid;
    }

    public void setProdid(Integer prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public double getProdprice() {
        return prodprice;
    }

    public void setProdprice(double prodprice) {
        this.prodprice = prodprice;
    }

    public String getProddesc() {
        return proddesc;
    }

    public void setProddesc(String proddesc) {
        this.proddesc = proddesc;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Serializable getProdimage() {
        return prodimage;
    }

    public void setProdimage(Serializable prodimage) {
        this.prodimage = prodimage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodid != null ? prodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.prodid == null && other.prodid != null) || (this.prodid != null && !this.prodid.equals(other.prodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Product[ prodid=" + prodid + " ]";
    }
    
}
