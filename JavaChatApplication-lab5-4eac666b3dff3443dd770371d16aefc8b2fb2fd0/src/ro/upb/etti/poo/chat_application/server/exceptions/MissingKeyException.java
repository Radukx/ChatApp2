/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.server.exceptions;

/**
 * Acest tip de exceptie este aruncat atunci cand o proprietate
 * de configurare nu este prezenta in fisierul de configurare.
 *
 * @author Radu Hobincu
 */
public class MissingKeyException extends Exception {

    /**
     * Constructorul clasei.
     *
     * @param message Mesajul de eroare.
     */
    public MissingKeyException(String message) {
        super(message);
    }

}
