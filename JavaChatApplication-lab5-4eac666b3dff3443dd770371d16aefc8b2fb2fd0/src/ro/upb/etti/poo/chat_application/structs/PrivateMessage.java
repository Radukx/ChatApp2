/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.structs;

/**
 * Clasa PrivateMessage este utilizata pentru transferul de mesaje intre
 * server si clienti, cu observatia ca un singur client poate primi
 * un mesaj de acest timp. Adica, un PrivateMessage va fi trimis 
 * de catre un expeditor pentru un singur destinatar.
 *
 * @author Radu Hobincu
 */
public class PrivateMessage extends Message {

    /**
     * Numele destinatarului acestui mesaj.
     */
    private final String mRecipient;

    /**
     * Constructorul clasei.
     * 
     * @param recipient Destinatarul mesajului.
     * @param sender Expeditorul mesajului.
     * @param content Continutul mesajului.
     */
    public PrivateMessage(String recipient, String sender, String content) {
        super(sender, content);
        mRecipient = recipient;
    }

    /**
     * Aceasta metoda ce suprascrie metoda din clasa de baza (java.lang.Object)
     * este utilizata pentru a obtine un String ce va fi afisat pe ecran,
     * String care contine informatiile relevante pentru mesaj.
     * 
     * Mesajul String nu contine numele destinatarului ci este marcat cu 
     * prefixul "(priv)" pentru a marca faptul ca acesta este vizibil
     * doar pentru clientul care il citeste.
     * 
     * @return o reprezentare String a acestui mesaj privat.
     */
    @Override
    public String toString() {
        return "(priv) " + super.toString();
    }

    /**
     * Metoda getter pentru campul mRecipient (destinatarul mesajului).
     * @return Destinatarul acestui mesaj.
     */
    public String getRecipient() {
        return mRecipient;
    }
}
