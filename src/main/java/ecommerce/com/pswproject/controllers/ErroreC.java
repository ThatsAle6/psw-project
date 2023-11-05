package ecommerce.com.pswproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/errore")
public class ErroreC {
    
    @GetMapping("/scorte")
    public ModelAndView qntNonDisponibile(){
        return new ModelAndView("ErroreScorte.html", "null", null);
    }

    @GetMapping("/erroreNonPresente")
    public ModelAndView prodottoNonPresente(){
        return new ModelAndView("ProdottoNonPresente.html", "null", null);
    }
}
