package ecommerce.com.pswproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.models.Utente;



public interface UtenteRepo extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);

    boolean exexistsByUsername(String username);
}
