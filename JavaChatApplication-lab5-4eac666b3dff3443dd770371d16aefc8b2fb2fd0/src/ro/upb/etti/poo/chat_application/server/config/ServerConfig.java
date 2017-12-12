/**
 * Acest fisier face parte din aplicatia de Chat dezvoltata
 * in sprijinul studentilor de anul 2 de la facultatea de
 * Electronica, Telecomunicatii si Tehnologia Informatiei din
 * cadrul Universitatii Politehnica Bucuresti.
 */
package ro.upb.etti.poo.chat_application.server.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ro.upb.etti.poo.chat_application.server.exceptions.InvalidFormatException;
import ro.upb.etti.poo.chat_application.server.exceptions.MissingKeyException;
import ro.upb.etti.poo.chat_application.server.exceptions.UnknownKeyException;

/**
 * Clasa ServerConfig este utilizata pentru a citi dintr-un fisier
 * parametrii relevanti pentru configurarea serverului. La momentul
 * actual, singurii parametri valizi sunt:
 * - MAX_CLIENTS - numarul maxim de clienti suportat de server.
 * - TCP_PORT - valoarea portului TCP pe care asculta serverul.
 *
 * @author Radu Hobincu
 */
public class ServerConfig {

    /**
     * Lista de proprietați cunoscute de catre server.
     */
    private static String[] sKnownProperties = {"TCP_PORT", "MAX_CLIENTS"};

    /**
     * Map de la numele proprietatii la valoarea acesteia, citita din fisier.
     */
    private Map<String, String> mProperties;

    /**
     * Constructorul clasei.
     *
     * @param filename Numele fisierului din care se vor citi proprietatile de configurare pentru server.
     *
     * @throws IOException            Daca fisierul nu exista sau nu poate fi citit.
     * @throws InvalidFormatException Daca una din liniile din fisier nu corespunde formatului asteptat.
     * @throws UnknownKeyException    Daca se citeste o proprietate necunoscuta din fisier.
     * @throws MissingKeyException    Daca una din proprietatile de configurare lipseste din fisier.
     */
    public ServerConfig(String filename) throws IOException, InvalidFormatException, UnknownKeyException,
            MissingKeyException {

        mProperties = new HashMap<>();

        Scanner scanner = new Scanner(new FileInputStream(filename));

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("#") || line.isEmpty()) {
                continue;
            }

            if (!line.matches("[a-zA-Z_][a-zA-Z0-9_]*\\s*=\\s*[0-9]+")) {
                throw new InvalidFormatException("Linia " + line + " nu se potriveste cu formatul asteptat!");
            }

            processLine(line);
        }

        for (String property : sKnownProperties) {
            if (!mProperties.containsKey(property)) {
                throw new MissingKeyException("Cheia " + property + " nu exista in fisier.");
            }
        }
    }

    /**
     * Constructor implicit care citeste configurarea din fisierul "server.conf".
     *
     * @throws IOException            Daca fisierul nu exista sau nu poate fi citit.
     * @throws InvalidFormatException Daca una din liniile din fisier nu corespunde formatului asteptat.
     * @throws UnknownKeyException    Daca se citeste o proprietate necunoscuta din fisier.
     * @throws MissingKeyException    Daca una din proprietatile de configurare lipseste din fisier.
     */
    public ServerConfig() throws IOException, InvalidFormatException, UnknownKeyException, MissingKeyException {
        this("server.conf");
    }

    /**
     * Metoda ce analizeaza o linie din fisier (care corespunde formatului asteptat) si populeaza map-ul
     * mProperties cu valorile citite sau arunca exceptie daca acestea nu sunt cunoscute.
     *
     * @param line Linia din fisier ce trebuie procesata.
     *
     * @throws UnknownKeyException Daca numele proprietatii citita de pe linie nu este cunoscuta.
     */
    private void processLine(String line) throws UnknownKeyException {
        String[] words = line.split("=");
        String keyName = words[0].trim();
        checkKey(keyName);
        mProperties.put(keyName, words[1].trim());
    }

    /**
     * Verifica daca numele proprietatii primit ca argument face parte din lista cunoscuta si daca nu, arunca exceptie.
     *
     * @param keyName numele proprietatii ce trebuie verificata.
     *
     * @throws UnknownKeyException Daca numele proprietatii citita de pe linie nu este cunoscuta.
     */
    private void checkKey(String keyName) throws UnknownKeyException {
        for (String knownKey : sKnownProperties) {
            if (keyName.equals(knownKey)) {
                return;
            }
        }

        throw new UnknownKeyException("Cheia " + keyName + " este necunoscuta.");
    }

    /**
     * Metoda ce intoarce valoarea portului TCP citit din fisier.
     *
     * @return valoarea portului TCP citit din fisier.
     */
    public int getTcpPort() {
        return Integer.parseInt(mProperties.get("TCP_PORT"));
    }

    /**
     * Metoda ce intoarce numarul maxim de clienti suportati de server, valoare citita din fisier.
     *
     * @return numarul maxim de clienti suportati de server, valoare citita din fisier.
     */
    public int getMaxClients() {
        return Integer.parseInt(mProperties.get("MAX_CLIENTS"));
    }
}
