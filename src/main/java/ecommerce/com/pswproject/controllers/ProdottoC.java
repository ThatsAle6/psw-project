package ecommerce.com.pswproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.com.pswproject.models.Prodotto;
import ecommerce.com.pswproject.services.ProdottoS;
import ecommerce.com.pswproject.utils.DTOProdotto;

@RestController
@RequestMapping("/productAPI")
public class ProdottoC {
    
    @Autowired
    private ProdottoS prodottoS;

    @PostMapping("/createProd")
    public void aggiungiProdotto(@RequestBody DTOProdotto p){
        prodottoS.creaProdotto(new Prodotto(p));
    }

    @GetMapping("/isAuth")
    public boolean isUserAuthenticated() {
	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return auth.isAuthenticated();
    }

}
