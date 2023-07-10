package ecommerce.com.pswproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecommerce.com.pswproject.model.ShopCart;

public interface ShopCartRepository extends JpaRepository<ShopCart, Integer>{

    @Query("select c from ShopCart c where c.buyer.id = ?1")
    ShopCart findByBuyer(long id);

    void deleteById(long id);
    
}
