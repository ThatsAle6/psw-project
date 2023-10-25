package ecommerce.com.pswproject.models;

import ecommerce.com.pswproject.utils.DTOProdotto;
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

    @Column(nullable = false, length = 15, unique = true)
    private String barCode;

    
    public Prodotto(){
        
    }

    public Prodotto(DTOProdotto dto) {
        this.nome = dto.getNome();
        this.prezzoBase = dto.getPrezzo();
        this.max_scorte = dto.getScorte();
        this.barCode = dto.getBarcode();
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

    public String getBarcode() {
        return barCode;
    }

    public void setBarcode(String barCode) {
        this.barCode = barCode;
    }

    
}
