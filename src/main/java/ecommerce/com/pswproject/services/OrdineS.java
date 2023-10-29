package ecommerce.com.pswproject.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecommerce.com.pswproject.models.Prodotto;
import ecommerce.com.pswproject.models.ordine.Ordine;
import ecommerce.com.pswproject.repositories.DettOrdineRepo;
import ecommerce.com.pswproject.repositories.OrdineRepo;
import ecommerce.com.pswproject.repositories.ProdottoRepo;
import ecommerce.com.pswproject.repositories.UtenteRepo;
import ecommerce.com.pswproject.utils.Carrello;
import ecommerce.com.pswproject.utils.DTOordine;

@Service
public class OrdineS {
    
    @Autowired
    private OrdineRepo ordineRepo;

    @Autowired
    private ProdottoRepo prodottoRepo;

    @Autowired
    private Carrello cart;

    @Autowired
    private DettOrdineRepo dettOrdineRepo;

    @Autowired
    private UtenteRepo utenteRepo;

    @Transactional(readOnly = true)
    public List<Ordine> listaOrdini(){
        return ordineRepo.findAll();
    }

    @Transactional(readOnly = false)
    public void createOrdine(DTOordine ordine){
        HashMap<Long, Integer> prodQnt = cart.getCarrello();
        int i=0;

        Ordine o = new Ordine();

        for(Entry<Long,Integer> e : prodQnt.entrySet()){

            Optional<Prodotto> p = prodottoRepo.findById(e.getKey());
            Prodotto prod = p.get();
            int scorte_nuove = prod.getMax_scorte() - e.getValue();
            prod.setMax_scorte(scorte_nuove);
            prodottoRepo.save(prod);
            dettOrdineRepo.save(ordine.getDettagliProd().get(i));
            i++;
        }
        o.setData(LocalDate.now());
        o.setUtente(utenteRepo.findById(ordine.getUtente()).get());
        o.setDettagli(ordine.getDettagliProd());

        ordineRepo.save(o);

    }




    /*
    //Creazione di un nuovo ordine
    @Transactional(readOnly = false)
    public void creaOrdine(DTOordine ordine){
        Ordine o = new Ordine();

        Optional<Utente> op = utenteRepo.findById(ordine.getUtente());
        if(!op.isEmpty()){
            Utente u = op.get();
            o.setUtente(u);
        }

        o.setData(LocalDate.now());
        o.setDettagli(ordine.getDettagliProd());
    }
    */

}
