package ecommerce.com.pswproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    
    long findById(long id);
    List<User> findByEmail(String email);
    List<User> findByAddress(String address);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findAll();

    boolean existsByEmail(String email);
    boolean existsById(Long id);
    void deleteById(Long id);
}
