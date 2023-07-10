package ecommerce.com.pswproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.model.User;
import ecommerce.com.pswproject.repository.UserRepository;
import ecommerce.com.pswproject.support.Exception.MailUserAlredyExistException;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    //Aggiunta di un nuovo User
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User registerUser(User u) throws MailUserAlredyExistException{
        
        if( userRepository.existsByEmail(u.getEmail())){
            throw new MailUserAlredyExistException();
        }
        return userRepository.save(u);
    }

    //Visualizzazione lista User
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //Rimozione di uno User esistente 
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
