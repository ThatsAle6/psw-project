package ecommerce.com.pswproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.models.ordine.Ordine;

public interface OrdineRepo extends JpaRepository<Ordine, Long> {
    
}
