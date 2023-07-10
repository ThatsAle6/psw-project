package ecommerce.com.pswproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.com.pswproject.model.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findById(long id);
    List<Product> findByBarCode(String barCode);
    List<Product> findByName(String name);
    List<Product> findAll();

    boolean existsByBarCode(String barCode);
    
    void deleteById(long id);
}
