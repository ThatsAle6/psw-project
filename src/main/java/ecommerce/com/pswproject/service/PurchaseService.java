package ecommerce.com.pswproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import ecommerce.com.pswproject.model.Product;
import ecommerce.com.pswproject.model.ProductInPurchase;
import ecommerce.com.pswproject.model.Purchase;
import ecommerce.com.pswproject.model.User;
import ecommerce.com.pswproject.repository.ProductInPurchaseRepository;
import ecommerce.com.pswproject.repository.PurchaseRepository;
import ecommerce.com.pswproject.repository.UserRepository;
import ecommerce.com.pswproject.support.Exception.QuantityUnavailableException;
import ecommerce.com.pswproject.support.Exception.UserNotFoundException;
import jakarta.persistence.EntityManager;

@Service
public class PurchaseService 
{
    @Autowired 
    private PurchaseRepository purchaseRepository;
    @Autowired 
    private ProductInPurchaseRepository productInPurchaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private EntityManager entityManager;

    //Creazione di un nuovo Acquisto
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Purchase newPurchase(Purchase purchase) throws QuantityUnavailableException{
        Purchase result = purchaseRepository.save(purchase);

        for ( ProductInPurchase p : result.getProductInPurchases() ){
            p.setPurchase(result);
            ProductInPurchase add = productInPurchaseRepository.save(p);
            entityManager.refresh(add);

            Product product = add.getProduct();
            long newQuantity = product.getQuantity() - p.getQuantity();
            if(newQuantity < 0){
                throw new QuantityUnavailableException();
            }

            product.setQuantity(newQuantity);
            entityManager.refresh(p);
        }
        entityManager.refresh(result);
        return result;
    }

    //Visualizzare Acquisto per acquirente
    @Transactional(readOnly = true)
    public List<Purchase> getByUser(User u) throws UserNotFoundException{
        
        if( !userRepository.existsById(u.getId()) ){
            throw new UserNotFoundException();
        }

        return purchaseRepository.findByBuyer(u);
    }

    //Visualizzazzione di tutti gli Acquisti
    @Transactional(readOnly = true)
    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    //Rimozione di un Acquisto
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePurchase(long id){
        purchaseRepository.deleteById(id);
    }
}
