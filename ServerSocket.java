/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wifi.chat.program;



import java.io.IOException;
  import java.net.*;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DUVALL NOTEBOOK
 */
public class ServerSocket extends WifiChatProgram {
    
    static private InetAddress myAddress = null;
    static private InetAddress serverAddress = null;
    
    
           public static void ServerMethod(){

		try {
                    
                    DatagramSocket inSocket;
                    inSocket = null;
                    byte[] inBuffer = new byte[256];
                    DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                    
                    serverAddress = InetAddress.getLocalHost();
                    try {
                        inSocket = new DatagramSocket(64000, serverAddress);
                    } catch (SocketException e) {
                        System.exit(-1);
                    }
                    
                    do {
                        for ( int i = 0 ; i < inBuffer.length ; i++ ) {
                            inBuffer[i] = ' ';
                        }
                        
                        try {
                            // this thread will block in the receive call
                            // until a message is received
                            System.out.println("Waiting for input...");
                            inSocket.receive(inPacket);
                        } catch (IOException e) {
                            System.exit(-1);
                        }
                        
                        String message = new String(inPacket.getData());
                        System.out.println("Received message = " + message);
                        
                    } while(true);
                } catch (UnknownHostException ex) {
			Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
    /*
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        
       
		try {
			myAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.exit(-1);
		}

		System.out.println("My Address = " + myAddress.getHostAddress());
        
                
		DatagramSocket outSocket;
        outSocket = null;

		try {
			outSocket = new DatagramSocket(63000, myAddress);
		} catch (SocketException e) {
			System.exit(-1);
		}
                        
                Scanner input = new Scanner(System.in);

                String prefix = "Message number ";
		byte[] outBuffer = new byte[256];

		for ( int i = 1 ; i <= 1000 ; i++ ) {
                        System.out.println("enter message here");
			String message = prefix + i;
                        String data = message + input.nextLine();
			outBuffer = data.getBytes();
				DatagramPacket packet = new DatagramPacket(outBuffer, 
						data.length(),
						myAddress,
						64000);

				System.out.println("Sending message = " + data);
                              
                    try {
                        outSocket.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                    }
			}
		
                
		Thread receiveThread = new Thread(ServerSocket::ServerMethod);
		receiveThread.setName("Receive Thread");
		receiveThread.start();

        }
	
}





    
    

