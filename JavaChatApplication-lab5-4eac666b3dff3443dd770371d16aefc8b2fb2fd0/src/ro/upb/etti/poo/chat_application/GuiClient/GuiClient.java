
package ro.upb.etti.poo.chat_application.GuiClient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import ro.upb.etti.poo.chat_application.client.ClientPeer;

/**
 *
 * @author Radu
 */
class GuiClient extends JFrame{
    private JTextField mSenderTextField;
    private JTextArea mMessageDisplayTextArea;
    private JTextField mMessageTextField;
    private JButton mChangeSenderButton;
    private JButton mSendMessageButton;
    private Socket mSocket;
    private String mNume;
    private ClientPeer cp;
    public String mesaj;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9000;
    
    public GuiClient(String nume, Socket socket) throws IOException{
        mSocket = socket;
        mNume = nume;
                    
        this.setTitle("Chat Client");
        
        JLabel mSenderNameLabel = new JLabel("Name:");
        mSenderTextField = new JTextField(40);
        mSenderTextField.setText(nume);
        
        mChangeSenderButton = new JButton("Change Name");
        mChangeSenderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                cp.setUsername(mSenderTextField.getText());
                mMessageDisplayTextArea.append("Clientul si-a schimbat numele in: " + mSenderTextField.getText()+"\n");
            }
        });
        
        JPanel jp1 = new JPanel();
        jp1.add(mSenderNameLabel);
        jp1.add(mSenderTextField);
        jp1.add(mChangeSenderButton);
        
        mMessageDisplayTextArea = new JTextArea(10, 55);
        JScrollPane scrollPane = new JScrollPane(mMessageDisplayTextArea);      
        JPanel jp2 = new JPanel();
        jp2.add(scrollPane);
         
        mMessageTextField = new JTextField(48);
        mMessageTextField.setEditable(false);
        
        mSendMessageButton = new JButton("Send");
        mSendMessageButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try{
                    String mesaj = "";
                    mesaj = mMessageTextField.getText();
                    if(mesaj.equals("/q")){
                        mMessageDisplayTextArea.append("SERVER: Ati parasit chat-ul!");
                        mSocket.close();
                    }else if(mesaj.matches("/w\\s+\\w+\\s+.+")){
                        String[] messageParts = mesaj.split("\\s+", 3);
                        cp.sendMessage(messageParts[1], messageParts[2]);
                } else {
                        cp.sendMessage(mesaj);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GuiClient.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        
        JPanel jp3 = new JPanel();
        jp3.add(mMessageTextField);
        jp3.add(mSendMessageButton);
        
       Container c = getContentPane();
       c.add(jp1,"North");
       c.add(jp2,"Center"); 
       c.add(jp3,"South");
       
       cp = new ClientPeer(nume, socket);
       cp.setOutputPane(mMessageDisplayTextArea);
       
    }
    
    public void display(){
        //cp.start();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
    
    public static void main(String[] args) throws IOException{
        Socket socket = new Socket(HOST, PORT);
        GuiClient gui = new GuiClient(socket, "batman");
        gui.display();
    
}    
}   
