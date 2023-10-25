package ecommerce.com.pswproject.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;

import ecommerce.com.pswproject.models.Utente;
import ecommerce.com.pswproject.models.secutity.ERole;
import ecommerce.com.pswproject.models.secutity.Role;
import ecommerce.com.pswproject.repositories.RoleRepo;
import ecommerce.com.pswproject.services.UtenteS;

@RestController
public class UtenteC {
    
    @Autowired 
    private UtenteS utenteS;

    @Autowired 
    private RoleRepo roleRepo;

    //password encoder

    @PostMapping("/signUp")
    public RedirectView createNewUtente(@RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("email") String email,
                                            @RequestParam("username") String username, @RequestParam("password") String password){
        Set<Role> ruoli = new HashSet<>();
        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(password); //crittografare dopo aver implementato la sicurezza
        ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        u.setRuoli(ruoli);

        utenteS.creaUtente(u);
        return new RedirectView("/homepage");
    }


}
