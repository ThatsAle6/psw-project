package ecommerce.com.pswproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/statoAccesso")
    public ResponseEntity<String> statoAccesso(Authentication auth){

        if(auth != null && auth.isAuthenticated()){
            return new ResponseEntity<>("Utente autenticato", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Utente non autenticato", HttpStatus.UNAUTHORIZED);
        }
    }

}
