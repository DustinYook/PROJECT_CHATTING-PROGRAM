package server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class Manager extends Vector<Socket>
{
	private static final long serialVersionUID = 1L;

	public boolean add(Socket socket) { return super.add(socket); } // add socket
	public void remove(Socket socket) { super.remove(socket); } // remove socket
	
	// send messages to all the clients in the vector (synchronized)
	public synchronized void sendToAll(String message)
	{
		PrintWriter writer = null;
		Socket socket;
		
		for(int i = 0; i < size(); i++)
		{
			socket = (Socket) elementAt(i);
			
			try 
			{ 
				writer = new PrintWriter(socket.getOutputStream(), true); // initialize PrintWriter object to write message
			} 
			catch (Exception e) { e.printStackTrace(); }
			
			if(writer != null)
				writer.println(message); // actually send message
		}
	}
	
	public synchronized void sendClientInfo()
	{
		String info = String.format("Current Chatting Members : %d", size());
		System.out.println(info);
		sendToAll(info);
	}
}