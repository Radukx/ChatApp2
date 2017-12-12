/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.structs;

/**
 * Clasa Message este utilizata pentru transferul de mesaje intre
 * server si clienti.
 *
 * @author Radu Hobincu
 */
public class Message {

    /**
     * Expeditorul mesajului.
     */
    private final String mSender;

    /**
     * Continutul mesajului.
     */
    private final String mContent;

    /**
     * Constructorul clasei Message.
     *
     * @param sender  expeditorul mesajului.
     * @param content continutul mesajului.
     */
    public Message(String sender, String content) {
        mSender = sender;
        mContent = content;
    }

    /**
     * Produce si intoarce un obiect de tip String ce va fi afisat
     * unui utilizator in fereastra de chat.
     *
     * @return mesajul formatat sub forma "expeditor: continut".
     */
    @Override
    public String toString() {
        return mSender + ": " + mContent;
    }

    public String getSender() {
        return mSender;
    }
}
