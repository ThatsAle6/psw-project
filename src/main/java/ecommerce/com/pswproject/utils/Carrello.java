package ecommerce.com.pswproject.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrello {
    private Long id_utente;
    private HashMap<Long, Integer> dettaglioProdotti; //<id, quantità> Prodotto

    public Carrello(){
        dettaglioProdotti = new HashMap<>();
    }

    //Aggiungere un prodotto al Carrello
    public void aggiungiProdotto(Entry<Long,Integer> entry){
        if(!dettaglioProdotti.containsKey(entry.getKey())){
            dettaglioProdotti.put(entry.getKey(), entry.getValue());
        }else{
            int tmp = dettaglioProdotti.get(entry.getKey());
            dettaglioProdotti.put(entry.getKey(), tmp+entry.getValue());
        }
    }

    //Rimuovere un prodotto dal Carrello
    public void eliminaProdotto(Entry<Long, Integer> entry){
        if(dettaglioProdotti.containsKey(entry.getKey())){
            dettaglioProdotti.remove(entry.getKey(), entry.getValue());
        }
    }

    //Svuotare il Carrello
    public void svuotaCarrello(){
        dettaglioProdotti.clear();
    }

    public Long getId_utente() {
        return id_utente;
    }

    public void setId_utente(Long id_utente) {
        this.id_utente = id_utente;
    }

    public HashMap<Long, Integer> getCarrello(){
        return dettaglioProdotti;
    }

    
}
