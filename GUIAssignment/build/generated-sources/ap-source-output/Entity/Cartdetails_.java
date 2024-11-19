package Entity;

import Entity.Cart;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-07T22:01:16")
@StaticMetamodel(Cartdetails.class)
public class Cartdetails_ { 

    public static volatile SingularAttribute<Cartdetails, Integer> cartdetailsid;
    public static volatile SingularAttribute<Cartdetails, Integer> quantity;
    public static volatile SingularAttribute<Cartdetails, Cart> cartid;
    public static volatile SingularAttribute<Cartdetails, Integer> prodid;

}