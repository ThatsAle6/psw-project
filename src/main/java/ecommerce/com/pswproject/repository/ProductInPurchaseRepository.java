package ecommerce.com.pswproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.model.ProductInPurchase;

public interface ProductInPurchaseRepository extends JpaRepository<ProductInPurchase, Long> {
    
    
}
