package ecommerce.com.pswproject.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table( name = "Shop_Cart", schema = "orders")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false )
    private long id;

    @Basic
    @Column(name = "Total", nullable = false)
    private float total;


    /* Relazione: USER e ProductInCart
     *  1 Cart 1 User
     *  1 Cart N ProductInCart
    */
    @OneToOne(mappedBy = "shopCart")
    private User buyer;

    @OneToMany(targetEntity = ProductInCart.class, cascade = CascadeType.MERGE, mappedBy = "shopCart")
    private List<ProductInCart> products;
}
