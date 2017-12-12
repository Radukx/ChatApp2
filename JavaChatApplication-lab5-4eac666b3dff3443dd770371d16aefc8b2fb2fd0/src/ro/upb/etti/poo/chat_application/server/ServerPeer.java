/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.upb.etti.poo.chat_application.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.upb.etti.poo.chat_application.structs.Message;

/**
 *
 * @author professor
 */
public class ServerPeer extends Thread {

    private final Socket mSocket;
    private String mClientName;
    private final Server mServer;
    private final ObjectOutputStream mObjectOutputStream;

    public ServerPeer(Socket communicationSocket, Server server) throws IOException {
        mSocket = communicationSocket;
        mServer = server;
        mObjectOutputStream = new ObjectOutputStream(communicationSocket.getOutputStream());
    }

    public String getClientName() {
        return mClientName;
    }

    public void run() {
        try {
            ObjectInputStream stream = new ObjectInputStream(mSocket.getInputStream());
            while (true) {
                Message message = (Message) stream.readObject();
                mClientName = message.getSender();
                mServer.dispatch(message);
            }
        } catch (EOFException ex) {
            // client disconnected gracefully so do nothing
        } catch (IOException ex) {
            System.err.println("Client connection reset: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Unknown object received.");
        } finally {
            mServer.cleanup(this);
        }
    }

    public void sendMessage(Message message) {
        try {
            mObjectOutputStream.writeObject(message);
        } catch (IOException ex) {
            // Unable to send message. Do nothing and let the recieving end cleanup resources.
        }
    }
}
