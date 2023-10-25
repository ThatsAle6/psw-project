package ecommerce.com.pswproject.utils.exception;

public class ProdottoNonPresente extends RuntimeException{
    public ProdottoNonPresente(String messaggio){
        super(messaggio);
    }
}
