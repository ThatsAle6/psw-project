package ecommerce.com.pswproject.security;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecommerce.com.pswproject.models.Utente;
import ecommerce.com.pswproject.models.secutity.Role;
import ecommerce.com.pswproject.repositories.UtenteRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private UtenteRepo utenteRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Utente> op = utenteRepo.findByUsername(username);
        if(!op.isEmpty()){
            Utente u = op.get();
            return new User(
                u.getUsername(),
                u.getPassword(),
                getPermessi(u.getRuoli())
            );
        }else{
            throw new UsernameNotFoundException("Username: "+username+" non è associato a nessun utente");
        }
    }
    
    private Collection< ? extends GrantedAuthority> getPermessi(Collection<Role> ruoli){
        return ruoli.stream().map(
            ruolo -> new SimpleGrantedAuthority(ruolo.getNome().name())
        ).collect(Collectors.toList());
    }
}
