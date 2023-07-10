package ecommerce.com.pswproject.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product", schema = "orders")
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;
    
    @Basic
    @Column(name = "bar_code", nullable = true, length = 90)
    private String barCode;

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String despricption;

    @Basic
    @Column(name = "price", nullable = true)
    private float price;

    @Basic
    @Column(name = "quantity", nullable = true)
    private long quantity;

    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;

    public Product(){}
    
    /*
     * Relazioni: ProductInPurchase
     *  1 product N purchase
    */
    
    @OneToMany(targetEntity = ProductInPurchase.class, mappedBy = "product", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<ProductInPurchase> productInPurchase;
}
