package ecommerce.com.pswproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.models.Prodotto;
import ecommerce.com.pswproject.repositories.ProdottoRepo;
import ecommerce.com.pswproject.utils.exception.ProdottoNonPresente;

@Service
public class ProdottoS {
    
    @Autowired
    private ProdottoRepo prodottoRepo;
    
    @Transactional(readOnly = false)
    public void creaProdotto( Prodotto p){
        if(!prodottoRepo.existsByBarCode(p.getBarcode())){
            prodottoRepo.save(p);
        }
    }

    @Transactional(readOnly = false)
    public void deleteProdotto( Long id )throws ProdottoNonPresente{
        if(prodottoRepo.existsById(id)){
            prodottoRepo.deleteById(id);
        }else{
            throw new ProdottoNonPresente("Il prodotto con id: "+id+" non è presente nel sistema");
        }
    }
}
