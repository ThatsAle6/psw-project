package ecommerce.com.pswproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.model.ShopCart;
import ecommerce.com.pswproject.model.User;
import ecommerce.com.pswproject.repository.ProductRepository;
import ecommerce.com.pswproject.repository.ShopCartRepository;
import ecommerce.com.pswproject.repository.UserRepository;

public class ShopCartService {
    
    @Autowired 
    private ShopCartRepository shopCartRepository;
    
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    */

    //Creazione ShopCart
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ShopCart createCart(User user){
        ShopCart sc = new ShopCart();
        sc.setBuyer(user);
        user.setShopCart(sc);
        return shopCartRepository.save(sc);
        
    }

    //Eliminazione ShopCart
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteCart(long id){
        shopCartRepository.deleteById(id);
    }    
}
