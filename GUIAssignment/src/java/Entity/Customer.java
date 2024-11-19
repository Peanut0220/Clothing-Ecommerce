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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustid", query = "SELECT c FROM Customer c WHERE c.custid = :custid"),
    @NamedQuery(name = "Customer.findByCustusername", query = "SELECT c FROM Customer c WHERE c.custusername = :custusername"),
    @NamedQuery(name = "Customer.findByCustfirstname", query = "SELECT c FROM Customer c WHERE c.custfirstname = :custfirstname"),
    @NamedQuery(name = "Customer.findByCustlastname", query = "SELECT c FROM Customer c WHERE c.custlastname = :custlastname"),
    @NamedQuery(name = "Customer.findByCustemail", query = "SELECT c FROM Customer c WHERE c.custemail = :custemail"),
    @NamedQuery(name = "Customer.findByCustpassword", query = "SELECT c FROM Customer c WHERE c.custpassword = :custpassword"),
    @NamedQuery(name = "Customer.findByCustphonenum", query = "SELECT c FROM Customer c WHERE c.custphonenum = :custphonenum")})
public class Customer implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CUSTUSERNAME")
    private String custusername;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 50)
    @Column(name = "CUSTFIRSTNAME")
    private String custfirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CUSTLASTNAME")
    private String custlastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CUSTEMAIL")
    private String custemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CUSTPASSWORD")
    private String custpassword;
    @Size(max = 12)
    @Column(name = "CUSTPHONENUM")
    private String custphonenum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custid")
    @JoinColumn(name = "CUSTID")
    private List<Orders> ordersList = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTID")
    private Integer custid;

    public Customer() {
    }

    public Customer(Integer custid) {
        this.custid = custid;
    }

    public Customer(String custemail, String custusername, String custfirstname, String custlastname, String custphonenum, String custpassword) {
        this.custusername = custusername;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custemail = custemail;
        this.custpassword = custpassword;
        this.custphonenum = custphonenum;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custid != null ? custid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custid == null && other.custid != null) || (this.custid != null && !this.custid.equals(other.custid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Customer[ custid=" + custid + " ]";
    }
    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }
    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getCustusername() {
        return custusername;
    }

    public void setCustusername(String custusername) {
        this.custusername = custusername;
    }

    public String getCustfirstname() {
        return custfirstname;
    }

    public void setCustfirstname(String custfirstname) {
        this.custfirstname = custfirstname;
    }

    public String getCustlastname() {
        return custlastname;
    }

    public void setCustlastname(String custlastname) {
        this.custlastname = custlastname;
    }

    public String getCustemail() {
        return custemail;
    }

    public void setCustemail(String custemail) {
        this.custemail = custemail;
    }

    public String getCustpassword() {
        return custpassword;
    }

    public void setCustpassword(String custpassword) {
        this.custpassword = custpassword;
    }

    public String getCustphonenum() {
        return custphonenum;
    }

    public void setCustphonenum(String custphonenum) {
        this.custphonenum = custphonenum;
    }
    
}
