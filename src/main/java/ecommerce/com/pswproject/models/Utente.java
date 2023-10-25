package ecommerce.com.pswproject.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ecommerce.com.pswproject.models.ordine.Ordine;
import ecommerce.com.pswproject.models.secutity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
    @SequenceGenerator(name = "utente_generator", allocationSize = 1)
    private Long id;
    
    @Column(nullable = false, length = 15)
    private String nome;

    @Column(nullable = false, length = 20)
    private String cognome;

    @Column(nullable = false, length = 25)
    private String email;

    @Column(nullable = false, length = 15, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ordine> ordini;

    public List<Ordine> getOrdini(){
        return ordini;
    }

    private Set<Role> ruoli = new HashSet<>();

    public Utente(){

    }    

    /*  Getter & Setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<Role> ruoli) {
        this.ruoli = ruoli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }
    
    
}
