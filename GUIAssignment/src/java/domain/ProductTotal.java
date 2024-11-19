/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

public class ProductTotal {
    private int prodtotalid;
    private int prodtotal;
    private int prodid;

    public ProductTotal() {
    }

    public ProductTotal(int prodtotalid, int prodtotal, int prodid) {
        this.prodtotalid = prodtotalid;
        this.prodtotal = prodtotal;
        this.prodid = prodid;
    }

    public int getProdtotalid() {
        return prodtotalid;
    }

    public void setProdtotalid(int prodtotalid) {
        this.prodtotalid = prodtotalid;
    }

    public int getProdtotal() {
        return prodtotal;
    }

    public void setProdtotal(int prodtotal) {
        this.prodtotal = prodtotal;
    }

    public int getProdid() {
        return prodid;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }
    
}
