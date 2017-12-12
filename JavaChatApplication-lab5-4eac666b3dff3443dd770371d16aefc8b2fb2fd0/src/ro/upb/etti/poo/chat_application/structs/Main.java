/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.structs;

/**
 * Clasa Main este utilizata pentru testarea claselor Message si
 * PrivateMessage din acest pachet.
 *
 * @author Radu Hobincu
 */
public class Main {

    public static void main(String[] args) {
        Message m1 = new Message("Ion", "Am plecat la coasa.");
        Message m2 = new Message("Vasile", "Eu raman sa dorm.");
        Message m3 = new Message("Ion", "Da, e o idee mai buna.");

        System.out.println(m1.toString());
        System.out.println(m2.toString());
        System.out.println(m3.toString());

        Message m4 = new PrivateMessage("Vasile", "Ion", "Am plecat la coasa.");
        Message m5 = new PrivateMessage("Ion", "Vasile", "Eu raman sa dorm.");
        Message m6 = new PrivateMessage("Vasile", "Ion", "Da, e o idee mai buna.");

        System.out.println(m4.toString());
        System.out.println(m5.toString());
        System.out.println(m6.toString());
    }
}
