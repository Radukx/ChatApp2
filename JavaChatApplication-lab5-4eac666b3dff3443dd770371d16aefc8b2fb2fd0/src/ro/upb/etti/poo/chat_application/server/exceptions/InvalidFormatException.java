/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.server.exceptions;

/**
 * Acest tip de exceptie este aruncat atunci cand o linie din fisierul
 * de configurare nu corespunde formatului asteptat. Formatul valid
 * este descris de REGEX-ul [a-zA-Z_][a-zA-Z0-9_]*\\s*=\\s*[0-9]+
 *
 * @author Radu Hobincu
 */
public class InvalidFormatException extends Exception {

    /**
     * Constructorul clasei.
     * @param message Mesajul de eroare.
     */
    public InvalidFormatException(String message) {
        super(message);
    }

}
