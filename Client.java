import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public  class Client extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextArea textArea;
	private JButton newSessionButton, sendButton, exitButton;
    //private JLabel newSessionLabel, sendLabel, exitLabel;
    private DatagramSocket socket;
    private static InetAddress myAddress;
    
    
	@SuppressWarnings("resource")
	public Client(){
		
		super( "Duvall Chat App");
		
	    new WindowDestroyer();
		//addWindowListener((WindowListener) this);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		
		textField = new JTextField( "Enter message here" );
		textField.addActionListener(
new ActionListener()
{
	public void actionPerformed( ActionEvent event)
	{
		try
		{
			String message = event.getActionCommand();
			textArea.append( message );
			
			byte[] data = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket( data, data.length, InetAddress.getLocalHost(), 64000);
			
			socket.send( sendPacket );
			textArea.append( "\n" );
			textArea.setCaretPosition( textArea.getText().length() );
			
		} catch ( IOException ioException )
		{
			ioException.printStackTrace();
		}
		
		
	}
});
        add( textField, BorderLayout.NORTH );
		
		textArea = new JTextArea( 4, 30 );
        add(new JScrollPane( textArea ), BorderLayout.CENTER );
        setSize( 400, 300 );
        setVisible( true );
       
        /*
        newSessionButton = new JButton("New Session");
        contentPane.add(newSessionButton);

        sendButton = new JButton("SEND");
        contentPane.add(sendButton);

        exitButton = new JButton("EXIT");
        contentPane.add(exitButton);
        */
     //------------------------------------------------------   
       /*
        try {
			myAddress = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
        
        System.out.println("My Address = " + myAddress.getHostAddress());

		DatagramSocket inSocket = null;
		byte[] inBuffer = new byte[1000];
		DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);

		*/
		//-----------------------------------------------------------
        try
        {
        	socket = new DatagramSocket();
        }// end try
        catch ( SocketException socketException )
        {
        	socketException.printStackTrace();
        	System.exit( 1 );
        }
        
        //----------------------------------------------------------
        /*
        do {
			for ( int i = 0 ; i < inBuffer.length ; i++ ) {
				inBuffer[i] = ' ';
			}


			try {
				// this thread will block in the receive call
				// until a message is received
				System.out.println("Waiting for input...");
				inSocket.receive(inPacket);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}

			String message = new String(inPacket.getData());
			System.out.println("Received message = " + message);
			textField.setText( message );

			byte[] data = message.getBytes();

			DatagramPacket sendpacket = null;
			try {
				sendpacket = new DatagramPacket( data, data.length, InetAddress.getLocalHost(), 64000 );
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				socket.send(sendpacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while(true);
        */
        //-------------------------------------------------------------
        // receive socket
        //------------------------------------------------

        
/*
        		try {
        			myAddress = InetAddress.getLocalHost();
        		} catch (Exception e) {
        			e.printStackTrace();
        			System.exit(-1);
        		}

        		System.out.println("My Address = " + myAddress.getHostAddress());

        		DatagramSocket inSocket = null;
        		byte[] inBuffer = new byte[1000];
        		DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);

        		try {
        			inSocket = new DatagramSocket(64000, myAddress);
        		} catch (Exception e) {
        			e.printStackTrace();
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
        			} catch (Exception e) {
        				e.printStackTrace();
        				System.exit(-1);
        			}

        			String message = new String(inPacket.getData());
        			System.out.println("Received message = " + message);
    				textField.setText( message );

    				byte[] data = message.getBytes();

    				DatagramPacket sendpacket = null;
    				try {
    					sendpacket = new DatagramPacket( data, data.length, InetAddress.getLocalHost(), 64000 );
    				} catch (UnknownHostException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}

    				try {
    					socket.send(sendpacket);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        		} while(true);
        	
        
        
        
        //------------------------------------------------------------
        //end receive socket
        //-----------------------------------------------------------
	*/
        
        
	}// end client constructor
	

	 public static void main(String[] args) {
         //Schedule a job for the event-dispatching thread:
         //creating and showing this application's GUI.
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
            	 
                  Client app = null;
				try {
					app = new Client();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  app.setVisible(true);
             } 
        });
         
         
	 }


	@Override
	public void actionPerformed(ActionEvent event) {
		if( event.getActionCommand().equals(newSessionButton)) {
	            try {
	            	
	            	socket = new DatagramSocket();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            }
	        else if( event.getActionCommand().equals(sendButton)){
				String message = event.getActionCommand();
				System.out.println("Received message = " + message);
				textField.setText( message );
				
				byte[] data = message.getBytes();
				
				DatagramPacket sendpacket = null;
				try {
					sendpacket = new DatagramPacket( data, data.length, InetAddress.getLocalHost(), 64000 );
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					socket.send(sendpacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        }else if( event.getActionCommand().equals(exitButton) ){
	          	
	      	  System.exit(-1);
	      	  
	        }
		/*
	        else if ( event.getActionCommand().equals( "New Session" ) )
	        {
	        	String ipAddr = " ";
	        	System.out.println(" enter the person's IP address: " );
	        	Scanner scnr = new Scanner(System.in);
	        	ipAddr = scnr.next();
	        	
	        	Client newWindow = null;
				try {
					newWindow = new Client();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	newWindow.setTitle( ipAddr );
	        	
	        	scnr.close();
	        	
	        }
	        */
	}
}
