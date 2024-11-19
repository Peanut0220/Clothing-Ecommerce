package Entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-07T22:01:16")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, String> prodname;
    public static volatile SingularAttribute<Product, String> categoryname;
    public static volatile SingularAttribute<Product, Integer> prodid;
    public static volatile SingularAttribute<Product, Double> prodprice;
    public static volatile SingularAttribute<Product, String> proddesc;
    public static volatile SingularAttribute<Product, Serializable> prodimage;

}