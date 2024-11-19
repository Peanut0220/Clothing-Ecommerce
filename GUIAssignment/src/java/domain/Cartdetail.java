package domain;

/**
 *
 * @author Tang Qiao Ling
 */
public class Cartdetail {

    private int cartdetailsid;
    private int cartid;
    private int prodid;
    private int quantity;

    public Cartdetail() {
    }

    public Cartdetail(int cartdetailsid, int quantity) {
        this.cartdetailsid = cartdetailsid;
        this.quantity = quantity;
    }  
       
    public Cartdetail(int cartdetailsid, int cartid, int prodid, int quantity) {
        this.cartdetailsid = cartdetailsid;
        this.cartid = cartid;
        this.prodid = prodid;
        this.quantity = quantity;
    }

    public int getCartdetailsid() {
        return cartdetailsid;
    }

    public void setCartdetailsid(int cartdetailsid) {
        this.cartdetailsid = cartdetailsid;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
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
    
}
