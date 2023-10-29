package ecommerce.com.pswproject.models.ordine;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ecommerce.com.pswproject.models.Utente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Ordine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordine_generator")
    @SequenceGenerator(name = "ordine_generator", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 10)
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_utente")
    @JsonIgnore
    private Utente utente;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ordine")
    private Collection<DettaglioOrdine> dettagli = new LinkedList<>();


    public Ordine(){

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getData() {
        return data;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }


    public Utente getUtente() {
        return utente;
    }


    public void setUtente(Utente utente) {
        this.utente = utente;
    }


    public Collection<DettaglioOrdine> getDettagli() {
        return dettagli;
    }


    public void setDettagli(Collection<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
    }

    
}
