
package ro.upb.etti.poo.chat_application.client;

import java.awt.TextArea;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;
import ro.upb.etti.poo.chat_application.structs.Message;
import ro.upb.etti.poo.chat_application.structs.PrivateMessage;

/**
 *
 * @author professor
 */
public class ClientPeer extends Thread {

    private final ObjectOutputStream mObjectStream;
    private String mSender;
    private final Socket mSocket;
    private JTextArea mTextArea;

    public ClientPeer(String sender, Socket communicationSocket) throws IOException {
        mSender = sender;
        mObjectStream = new ObjectOutputStream(communicationSocket.getOutputStream());
        mSocket = communicationSocket;
    }

    @Override
    public void run () {
         try {
            ObjectInputStream stream = new ObjectInputStream(mSocket.getInputStream());

            while (true) {
                Message message = (Message) stream.readObject();
                System.out.println(message.toString());
                if(mTextArea != null) {
                    mTextArea.append(message.toString() + "\n");
                }
            }
        } catch (EOFException ex) {
            // client disconnected gracefully so do nothing
        } catch (IOException ex) {
            System.err.println("Client connection reset: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Unknown object received.");
        } finally {
            System.exit(0);
        }
    }

    public void sendMessage(String message) throws IOException {
        mObjectStream.writeObject(new Message(mSender, message));
    }

    public void sendMessage(String recipient, String message) throws IOException {
        mObjectStream.writeObject(new PrivateMessage(recipient, mSender, message));
    }

    public void setOutputPane(JTextArea jTextArea1) {
        mTextArea = jTextArea1;
    }

    public void setUsername(String nume) {
        mSender = nume;
    }
}
