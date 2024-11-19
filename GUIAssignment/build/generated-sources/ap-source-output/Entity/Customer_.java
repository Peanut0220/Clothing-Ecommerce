package Entity;

import Entity.Cart;
import Entity.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-07T22:01:16")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> custfirstname;
    public static volatile SingularAttribute<Customer, String> custlastname;
    public static volatile SingularAttribute<Customer, String> custphonenum;
    public static volatile SingularAttribute<Customer, String> custemail;
    public static volatile SingularAttribute<Customer, Integer> custid;
    public static volatile SingularAttribute<Customer, String> custusername;
    public static volatile SingularAttribute<Customer, String> custpassword;
    public static volatile ListAttribute<Customer, Orders> ordersList;
    public static volatile ListAttribute<Customer, Cart> cartList;

}