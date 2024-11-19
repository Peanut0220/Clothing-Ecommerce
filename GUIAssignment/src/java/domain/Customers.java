/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package domain;

/**
 *
 * @author User
 */
public class Customers {

    int custid;
    String custusername;
    String custfirstname;
    String custlastname;
    String custemail;
    String custPassword;
    String custPhoneNum;

    public Customers(int custid) {
        this.custid = custid;
    }

    public Customers(String custusername, String custfirstname, String custlastname, String custemail, String custPassword, String custPhoneNum) {
        this.custusername = custusername;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custemail = custemail;
        this.custPassword = custPassword;
        this.custPhoneNum = custPhoneNum;
    }

    public Customers(int custid, String custusername, String custfirstname, String custlastname, String custemail, String custPassword, String custPhoneNum) {
        this.custid = custid;
        this.custusername = custusername;
        this.custfirstname = custfirstname;
        this.custlastname = custlastname;
        this.custemail = custemail;
        this.custPassword = custPassword;
        this.custPhoneNum = custPhoneNum;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
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

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

    public String getCustPhoneNum() {
        return custPhoneNum;
    }

    public void setCustPhoneNum(String custPhoneNum) {
        this.custPhoneNum = custPhoneNum;
    }
    
    
}
