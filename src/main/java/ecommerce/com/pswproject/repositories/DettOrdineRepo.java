package ecommerce.com.pswproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.models.ordine.DettaglioOrdine;

public interface DettOrdineRepo extends JpaRepository<DettaglioOrdine, Long>{
    
}
