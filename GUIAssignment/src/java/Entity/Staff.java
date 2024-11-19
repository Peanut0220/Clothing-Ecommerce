/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "STAFF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findByStaffid", query = "SELECT s FROM Staff s WHERE s.staffid = :staffid"),
    @NamedQuery(name = "Staff.findByStaffusername", query = "SELECT s FROM Staff s WHERE s.staffusername = :staffusername"),
    @NamedQuery(name = "Staff.findByStafffirstname", query = "SELECT s FROM Staff s WHERE s.stafffirstname = :stafffirstname"),
    @NamedQuery(name = "Staff.findByStafflastname", query = "SELECT s FROM Staff s WHERE s.stafflastname = :stafflastname"),
    @NamedQuery(name = "Staff.findByStaffemail", query = "SELECT s FROM Staff s WHERE s.staffemail = :staffemail"),
    @NamedQuery(name = "Staff.findByStaffpassword", query = "SELECT s FROM Staff s WHERE s.staffpassword = :staffpassword"),
    @NamedQuery(name = "Staff.findByStaffphoneno", query = "SELECT s FROM Staff s WHERE s.staffphoneno = :staffphoneno"),
    @NamedQuery(name = "Staff.findByStaffcreateddate", query = "SELECT s FROM Staff s WHERE s.staffcreateddate = :staffcreateddate")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STAFFID")
    private Integer staffid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STAFFUSERNAME")
    private String staffusername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STAFFFIRSTNAME")
    private String stafffirstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STAFFLASTNAME")
    private String stafflastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STAFFEMAIL")
    private String staffemail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "STAFFPASSWORD")
    private String staffpassword;
    @Size(max = 12)
    @Column(name = "STAFFPHONENO")
    private String staffphoneno;
    @Column(name = "STAFFCREATEDDATE")
    @Temporal(TemporalType.DATE)
    private Date staffcreateddate;

    public Staff() {
    }

    public Staff(Integer staffid) {
        this.staffid = staffid;
    }

    public Staff(String staffusername, String stafffirstname, String stafflastname, String staffemail, String staffpassword, String staffphoneno, Date staffcreateddate) {
        this.staffusername = staffusername;
        this.stafffirstname = stafffirstname;
        this.stafflastname = stafflastname;
        this.staffemail = staffemail;
        this.staffpassword = staffpassword;
        this.staffphoneno = staffphoneno;
        this.staffcreateddate = staffcreateddate;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public String getStaffusername() {
        return staffusername;
    }

    public void setStaffusername(String staffusername) {
        this.staffusername = staffusername;
    }

    public String getStafffirstname() {
        return stafffirstname;
    }

    public void setStafffirstname(String stafffirstname) {
        this.stafffirstname = stafffirstname;
    }

    public String getStafflastname() {
        return stafflastname;
    }

    public void setStafflastname(String stafflastname) {
        this.stafflastname = stafflastname;
    }

    public String getStaffemail() {
        return staffemail;
    }

    public void setStaffemail(String staffemail) {
        this.staffemail = staffemail;
    }

    public String getStaffpassword() {
        return staffpassword;
    }

    public void setStaffpassword(String staffpassword) {
        this.staffpassword = staffpassword;
    }

    public String getStaffphoneno() {
        return staffphoneno;
    }

    public void setStaffphoneno(String staffphoneno) {
        this.staffphoneno = staffphoneno;
    }

    public Date getStaffcreateddate() {
        return staffcreateddate;
    }

    public void setStaffcreateddate(Date staffcreateddate) {
        this.staffcreateddate = staffcreateddate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffid != null ? staffid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffid == null && other.staffid != null) || (this.staffid != null && !this.staffid.equals(other.staffid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Staff[ staffid=" + staffid + " ]";
    }
    
}
