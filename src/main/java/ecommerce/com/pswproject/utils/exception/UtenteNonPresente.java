package ecommerce.com.pswproject.utils.exception;

public class UtenteNonPresente extends RuntimeException{
    
    public UtenteNonPresente(String messaggio){
        super(messaggio);
    }
}
