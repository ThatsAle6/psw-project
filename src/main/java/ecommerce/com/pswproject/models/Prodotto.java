package ecommerce.com.pswproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Prodotto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodotto_generator")
    @SequenceGenerator(name = "prodotto_generator", allocationSize = 1)
    private Long id;

    @Column(name = "scorte", nullable = false)
    private int max_scorte;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "prezzo", nullable = false)
    private double prezzoBase;

    
    public Prodotto(){
        
    }

    public Prodotto(Long id, int max_scorte, String nome, double prezzoBase) {
        this.id = id;
        this.max_scorte = max_scorte;
        this.nome = nome;
        this.prezzoBase = prezzoBase;
    }

    /*  Getter & Setter */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMax_scorte() {
        return max_scorte;
    }

    public void setMax_scorte(int max_scorte) {
        this.max_scorte = max_scorte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(double prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    
}
