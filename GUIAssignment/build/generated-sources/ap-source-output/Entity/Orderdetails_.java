package Entity;

import Entity.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-07T22:01:16")
@StaticMetamodel(Orderdetails.class)
public class Orderdetails_ { 

    public static volatile SingularAttribute<Orderdetails, Integer> orderdetailsid;
    public static volatile SingularAttribute<Orderdetails, Integer> quantity;
    public static volatile SingularAttribute<Orderdetails, Orders> orderid;
    public static volatile SingularAttribute<Orderdetails, Integer> prodid;

}