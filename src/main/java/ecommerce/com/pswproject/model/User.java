package ecommerce.com.pswproject.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user", schema = "orders")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    
    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = true, length = 70)
    private String lastName;

    @Basic
    @Column(name = "e-mail", nullable = true, length = 70)
    private String email;

    @Basic
    @Column(name = "address", nullable = true, length = 50)
    private String address;

    @Basic
    @Column(name = "telephone", nullable = true, length = 50)
    private String telephone;

    public User(){}

    /*
     * Relazioni: purchase e cart
     *  1 user N purchase
     *  1 user 1 cart
    */
    
    @OneToMany(targetEntity = Purchase.class, mappedBy = "buyer", cascade = CascadeType.MERGE)
    private List<Purchase> purchases;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "ShopCart_id")
    private ShopCart shopCart;

}
