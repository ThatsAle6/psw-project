package ecommerce.com.pswproject.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "purchase", schema = "orders")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "puchase_time", nullable = true)
    private Date purchaseTime;


    /*
     * Relazioni: User - ProductInPurchase
     *  N Purchase 1 User
     *  1 Purchase N list of ProductInPurchase
    */

    @ManyToOne
    @JoinColumn(name = "buyer")
    @JsonIgnore
    private User buyer;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
    private List<ProductInPurchase> productInPurchases;
    
}
