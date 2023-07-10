package ecommerce.com.pswproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.com.pswproject.model.Purchase;
import ecommerce.com.pswproject.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
    List<Purchase> findByBuyer(User buyer);
    List<Purchase> findAll();
}
