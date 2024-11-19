/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package domain;

/**
 *
 * @author User
 */
public class Carts {

    int cartid;
    int cusid;

    public Carts() {
    }

    public Carts(int cartid) {
        this.cartid = cartid;
    }

    public Carts(int cartid, int cusid) {
        this.cartid = cartid;
        this.cusid = cusid;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }
    
}