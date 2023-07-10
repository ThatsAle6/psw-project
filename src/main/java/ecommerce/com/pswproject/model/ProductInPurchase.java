package ecommerce.com.pswproject.model;

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
@Table(name = "product_in_purchase", schema = "orders")
public class ProductInPurchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    /*
     * Relazioni: purchase, product
     * N product
     * N purchase
    */

    @ManyToOne
    @JoinColumn(name = "related_purchase")
    @JsonIgnore
    @ToString.Exclude
    private Purchase purchase;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Product")
    private Product product;
}

/*
 * Table: | Purchase | Product |
 * mi serve per tenere traccia dei prodotti in acquisto e del relativo acquisto. 
*/