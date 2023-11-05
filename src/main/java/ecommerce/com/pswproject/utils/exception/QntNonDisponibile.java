package ecommerce.com.pswproject.utils.exception;

public class QntNonDisponibile extends RuntimeException {
    public QntNonDisponibile(){
        super("Hai superato il numero di scorte disponibili!");
    }
}
