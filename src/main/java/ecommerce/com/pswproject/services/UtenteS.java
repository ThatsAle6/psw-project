package ecommerce.com.pswproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.models.Utente;
import ecommerce.com.pswproject.repositories.UtenteRepo;
import ecommerce.com.pswproject.utils.exception.UtenteNonPresente;

@Service
public class UtenteS {
    
    @Autowired
    private UtenteRepo utenteRepo;

    @Transactional(readOnly = false)
    public void creaUtente( Utente utente){
        if(!utenteRepo.exexistsByUsername(utente.getUsername())){
            utenteRepo.save(utente);
        }
    }

    @Transactional(readOnly = false)
    public void deleteUtente(Long id) throws UtenteNonPresente{
        if(utenteRepo.existsById(id)){
            utenteRepo.deleteById(id);
        }else{
            throw new UtenteNonPresente("L'utente con id"+id+"non è presente nel sistema.");
        }
    }

    @Transactional(readOnly = true)
    public List<Utente> listaUtenti(){
        return utenteRepo.findAll();
    }
}
