package ecommerce.com.pswproject.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ecommerce.com.pswproject.models.Prodotto;
import ecommerce.com.pswproject.repositories.ProdottoRepo;
import ecommerce.com.pswproject.utils.Carrello;
import ecommerce.com.pswproject.utils.DTOProdCart;
import ecommerce.com.pswproject.utils.ProdottoCart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/carrelloAPI")
public class CarrelloC {

    @Autowired
    private Carrello cart;
    @Autowired
    private ProdottoRepo prodottoRepo;

    private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @PostMapping(value="/addCart") //endpoint aggiunta al carrello
    public RedirectView aggiungiAlCarrello(@RequestBody DTOProdCart prod) {
        Optional<Prodotto> op = prodottoRepo.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long,Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.aggiungiProdotto(e);
        }
        return new RedirectView("homepage/shop");
    }

    @PostMapping("/deleteFromCart") //Endpoint per la rimozione del carrello
    public RedirectView eliminaDalCarrello(@RequestBody DTOProdCart prod){
        Optional<Prodotto> op = prodottoRepo.findByNome(prod.getNome());
        if(!op.isEmpty()){
            Prodotto p = op.get();
            Map.Entry<Long, Integer> e = Map.entry(p.getId(), prod.getQnt());
            cart.eliminaProdotto(e);
        }
        return new RedirectView("/carrelloAPI/mostraCarrello");
    }

    //Visualizzare i prodotti del carrello
    @GetMapping("/mostraCarrello")
    public ModelAndView prodottiCarrello(){
        HashMap<Long,Integer> c = cart.getCarrello();
        LinkedList<ProdottoCart> prodotti = new LinkedList<>();
        double costoOrdine = 0;
        ModelAndView modelAndView = new ModelAndView("Carrello.html");

        for(Entry<Long,Integer> e : c.entrySet()){
            Optional<Prodotto> op = prodottoRepo.findById(e.getKey());
            if(!op.isEmpty()){
                Prodotto p = op.get();
                ProdottoCart pCart = new ProdottoCart();
                pCart.setNome(p.getNome());
                pCart.setQnt(e.getValue());
                pCart.setPrezzo_parziale(p.getPrezzoBase()*e.getValue());
                prodotti.add(pCart);

                costoOrdine += p.getPrezzoBase()*e.getValue();
            }
        }
        modelAndView.addObject("prodottiCart", prodotti);
        modelAndView.addObject("totale", costoOrdine);

        return modelAndView;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth){
        this.logoutHandler.logout(request, response, auth);
    }
}
