package ecommerce.com.pswproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.model.Product;
import ecommerce.com.pswproject.repository.ProductRepository;
import ecommerce.com.pswproject.support.Exception.BarCodeAlreadyExistException;

@Service
public class ProductService {
    
    @Autowired ProductRepository productRepository;

    //Aggiunta di un nuovo Prodotto
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED)
    public Product addProduct(Product p) throws BarCodeAlreadyExistException{
        if( productRepository.existsByBarCode(p.getBarCode())){
            throw new BarCodeAlreadyExistException();
        }
        return productRepository.save(p);
    }

    //Visualizzazione lista Prodotti
    @Transactional( readOnly = true, propagation = Propagation.REQUIRED)
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    //Rimozione di un Prodotto esistente
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
