package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	private ServerSocket server;
	private Manager manager = new Manager();
	
	public void start()
	{
		try 
		{
			server = new ServerSocket(12345); // create socket in server with specified number
			System.out.println("Now server is activated!");
			
			while(true)
			{
				Socket socket = server.accept(); // waiting 
				new Chat(socket).start(); // create object for client who just connected to server
				manager.add(socket); // add client to vector
				manager.sendClientInfo();
			}
		} 
		catch (Exception e) { e.printStackTrace(); }
	}

	public class Chat extends Thread
	{
		private Socket socket;
		private BufferedReader reader;
		
		public Chat(Socket socket) { this.socket = socket; }
		
		public void run()
		{
			try 
			{
				// create BufferedReader object for reading messages from one socket
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String message;
				while((message = reader.readLine()) != null)
				{
					System.out.println(message);
					manager.sendToAll(message);
				}
			} 
			catch (Exception e) { e.printStackTrace(); }
			finally
			{
				try
				{
					manager.remove(socket); // if client leaves from the chat, remove client from vector
					if(reader != null)
						reader.close();
					if(socket != null)
						socket.close();
					
					System.out.println("Client is disconnected");
					manager.sendClientInfo();
				}
				catch(Exception e) { e.printStackTrace(); }
			}
		}
	}

}