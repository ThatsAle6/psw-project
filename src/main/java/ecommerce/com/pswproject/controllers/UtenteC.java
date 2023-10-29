package ecommerce.com.pswproject.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import ecommerce.com.pswproject.models.Utente;
import ecommerce.com.pswproject.models.secutity.ERole;
import ecommerce.com.pswproject.models.secutity.Role;
import ecommerce.com.pswproject.repositories.RoleRepo;
import ecommerce.com.pswproject.services.UtenteS;
import ecommerce.com.pswproject.utils.DTOUtente;

@RestController
@RequestMapping("/customerAPI")
public class UtenteC {
    
    @Autowired 
    private UtenteS utenteS;

    @Autowired 
    private RoleRepo roleRepo;

    //password encoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/createAdmin")
    public RedirectView createNewUtente(@RequestBody DTOUtente utente){
        Set<Role> ruoli = new HashSet<>();
        Utente u = new Utente();
        u.setNome(utente.getNome());
        u.setCognome(utente.getCognome());
        u.setEmail(utente.getEmail());
        u.setUsername(utente.getUsername());
        System.out.println(utente.getPassword());
        u.setPassword(passwordEncoder.encode(utente.getPassword()));

        if(utente.getRuolo().equals("ADMIN")){
            ruoli.add(roleRepo.findByNome(ERole.ADMIN).get());
        }
    
        //ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        u.setRuoli(ruoli);

        utenteS.creaUtente(u);
        return new RedirectView("/homepage");
    }

    
    @PostMapping("/createCustomer")
    public RedirectView createNewUtente(@RequestParam("nome") String nome, @RequestParam("cognome") String cognome, @RequestParam("email") String email,
                                            @RequestParam("username") String username, @RequestParam("password") String password){
        Set<Role> ruoli = new HashSet<>();
        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        ruoli.add(roleRepo.findByNome(ERole.GUEST).get());
        u.setRuoli(ruoli);

        utenteS.creaUtente(u);
        return new RedirectView("/homepage");
    }
     
    @GetMapping("/createView")
    public ModelAndView creatView(){
        return new ModelAndView("SignUp", "null", null);
    }


}
