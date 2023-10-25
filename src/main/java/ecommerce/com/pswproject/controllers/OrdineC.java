package ecommerce.com.pswproject.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ecommerce.com.pswproject.models.Prodotto;
import ecommerce.com.pswproject.models.Utente;
import ecommerce.com.pswproject.models.ordine.DettaglioOrdine;
import ecommerce.com.pswproject.repositories.ProdottoRepo;
import ecommerce.com.pswproject.repositories.UtenteRepo;
import ecommerce.com.pswproject.services.OrdineS;
import ecommerce.com.pswproject.utils.Carrello;
import ecommerce.com.pswproject.utils.DTOordine;

@RestController
public class OrdineC {

    @Autowired
    private OrdineS ordineS;

    @Autowired
    private Carrello cart;

    @Autowired
    private ProdottoRepo prodottoRepo;

    @Autowired
    private UtenteRepo utenteRepo;
    

    @GetMapping("/ordineCarrello")
    public RedirectView ordineCart(){

        //sicurezza controllo utente
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        HashMap<Long, Integer> prodQnt = cart.getCarrello();
        LinkedList<DettaglioOrdine> prodotti = new LinkedList<>();

        DTOordine dtOrdine = new DTOordine();


        for( Entry<Long, Integer> e : prodQnt.entrySet()){
            Optional<Prodotto> op = prodottoRepo.findById(e.getKey());
            if(!op.isEmpty()){
                Prodotto p = op.get();

                DettaglioOrdine dto = new DettaglioOrdine();
                dto.setProdotto(p);

                if(e.getValue() <= p.getMax_scorte()){
                    dto.setQnt(e.getValue());
                }else{
                    cart.svuotaCarrello();
                    return new RedirectView("/errore/scorte");
                }
                prodotti.add(dto);

            } 
        }
        dtOrdine.setDettagliProd(prodotti);
        if(auth.isAuthenticated()){
            String username = auth.getName();
            Optional<Utente> op = utenteRepo.findByUsername(username);
            if(op.isPresent()){
                Utente u = op.get();
                dtOrdine.setUtente(u.getId());
            }
        }
        ordineS.createOrdine(dtOrdine);
        cart.svuotaCarrello();
        return  new RedirectView("/carrelloAPI/viewCarrello");
    }
    
}
