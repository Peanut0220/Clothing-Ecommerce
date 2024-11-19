package Entity;

import Entity.Customer;
import Entity.Orderdetails;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-07T22:01:16")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Double> total;
    public static volatile SingularAttribute<Orders, Double> shippingfees;
    public static volatile SingularAttribute<Orders, String> orderstatus;
    public static volatile SingularAttribute<Orders, Integer> orderid;
    public static volatile SingularAttribute<Orders, Double> subtotal;
    public static volatile SingularAttribute<Orders, Customer> custid;
    public static volatile ListAttribute<Orders, Orderdetails> orderdetailsList;
    public static volatile SingularAttribute<Orders, Date> orderdate;
    public static volatile SingularAttribute<Orders, String> orderaddress;

}