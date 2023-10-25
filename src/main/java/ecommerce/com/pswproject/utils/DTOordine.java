package ecommerce.com.pswproject.utils;

import java.time.LocalDate;
import java.util.LinkedList;

import ecommerce.com.pswproject.models.ordine.DettaglioOrdine;

public class DTOordine {
    private Long id;
    private LocalDate data;
    private LinkedList<DettaglioOrdine> dettagliProd;
    private Long utente;

    public DTOordine(){

    }

    public LinkedList<DettaglioOrdine> getDettagliProd() {
        return dettagliProd;
    }

    public void setDettagliProd(LinkedList<DettaglioOrdine> dettagliProd) {
        this.dettagliProd = dettagliProd;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
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

    
}
