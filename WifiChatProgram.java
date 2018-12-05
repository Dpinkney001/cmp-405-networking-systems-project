/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wifi.chat.program;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javafx.embed.swing.JFXPanel;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author DUVALL NOTEBOOK
 */
public class WifiChatProgram extends JApplet implements ActionListener
{
    
    private static final int JFXPANEL_WIDTH_INT = 500;
    private static final int JFXPANEL_HEIGHT_INT = 500;
    private static JFXPanel fxContainer;
    private DatagramSocket socket;
    private static InetAddress myAddress;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            }
            
            JFrame frame = new JFrame("Duvall Wifi Chat Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JApplet applet = new WifiChatProgram();
            applet.init();
            
            frame.setContentPane(applet.getContentPane());
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            applet.start();
            
            //setup for jtextare and jtextfield
        });
    }
    private DatagramSocket outSocket;
    private DatagramSocket inSocket;
    private Object textArea;
    private Object textField;
    private InetAddress serverAddress;
    
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        
         JTextField inputTextField;
        inputTextField = new JTextField();
         JTextArea displayTextArea;
        displayTextArea = new JTextArea( 4,30 );
         add(displayTextArea, BorderLayout.NORTH);
         add(inputTextField, BorderLayout.SOUTH);
         add(new JScrollPane( displayTextArea ), BorderLayout.CENTER );
         displayTextArea.addComponentListener(null);
         //textField.addActionListener();
         setSize(100,100);
         setVisible(true);
         
         // 2nd jtextarea and field
         /*
         JTextField textField2 = new JTextField();
         JTextArea textArea2 = new JTextArea( 4,30 );
         add(new JScrollPane( textArea2 ), BorderLayout.CENTER );
         setSize(100,100);
         setVisible(true);
         */
         // Beginning of chat application back end
                try
        {
        	inSocket = new DatagramSocket(64000, myAddress);
                
        }// end try
        catch ( SocketException socketException )
        {
        	System.exit( 1 );
        }
                try{
                    
                    outSocket = new DatagramSocket(63000, serverAddress);
                }catch ( SocketException socketException ){
                    
                    System.exit( 1 );
                    
                }
        
        try
		{
                    for (int i = 0; i < 5; i++) {
			String message = displayTextArea.getText();
			displayTextArea.append( message );
			
			byte[] data = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket( data, data.length, InetAddress.getLocalHost(), 63000);
			
			outSocket.send( sendPacket );
			displayTextArea.append( "\n" );
			displayTextArea.setCaretPosition( displayTextArea.getText().length() );
                    }
		} catch ( IOException ioException )
		{
		}

        try{
            for (int i = 0; i < 5; i++) 
            {
            //textArea.setText();
            //displayTextArea.append( receiveMessage );
            String receiveMessage = "";
            byte[] data = receiveMessage.getBytes(receiveMessage);
            DatagramPacket receivePacket = new DatagramPacket( data, data.length, InetAddress.getByAddress(receiveMessage, data), 64000);
			
			inSocket.receive( receivePacket );
			displayTextArea.append( "\n" );
			displayTextArea.setCaretPosition( displayTextArea.getText().length() );
            }
		} catch ( IOException ioException )
		{
		}
            
    
        
    /*
           
    

        // create JavaFX scene
      // WifiChatProgram();
}
   /*
    private void createScene() {
        setSize( 500, 500 );
        setVisible( true );
       
        
        Button btn = new Button();
        btn.setText("Send");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        textArea.setText(textField.getText());
        });
        
        textArea.setOnAction(new EventHandler<ActionEvent>() {

});
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        fxContainer.setScene(new Scene(root));
    }
    */
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //textField.equals(textArea)actionEvent(e.getModifiers());
        /*
        public void sendMessage(ActionEvent e){
        if (textField.getModifiers( "enter" ) == e.getActionCommand()){
            outSocket.send( packet );
        }
*/
    }
}//wifichatProgram class
