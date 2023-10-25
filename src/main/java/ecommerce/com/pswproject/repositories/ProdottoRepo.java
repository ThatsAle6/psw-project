package ecommerce.com.pswproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.models.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto, Long>{
    Optional<Prodotto> findByNome(String nome);

    boolean existsByBarCode(String barcode);
    
}
