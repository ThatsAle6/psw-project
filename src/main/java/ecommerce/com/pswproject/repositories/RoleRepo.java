package ecommerce.com.pswproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.models.secutity.ERole;
import ecommerce.com.pswproject.models.secutity.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByNome(ERole nome);
    
}
