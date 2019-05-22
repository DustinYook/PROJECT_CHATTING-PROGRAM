package client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame implements Runnable, ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JFrame frmChattingProgram;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JPanel panel;
	private JTextField textField;
	private JButton btnSend;
	
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket socket;
	private JTextField nameField;

	/** Launch the application */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					ClientGUI window = new ClientGUI();
					window.connect(); // connect client to server
					window.frmChattingProgram.setVisible(true);
				} 
				catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	/** Create the application */
	public ClientGUI() { initialize(); }

	/** Initialize the contents of the frame */
	private void initialize() 
	{
		this.frmChattingProgram = new JFrame();
		this.frmChattingProgram.setResizable(false);
		this.frmChattingProgram.setTitle("Chatting Program");
		this.frmChattingProgram.setBounds(100, 100, 550, 600);
		this.frmChattingProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.scrollPane.setPreferredSize(new Dimension(2, 450));
		this.frmChattingProgram.getContentPane().add(this.scrollPane, BorderLayout.NORTH);
		
		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(10, 100));
		this.frmChattingProgram.getContentPane().add(this.panel, BorderLayout.SOUTH);
		this.panel.setLayout(null);
		
		this.nameField = new JTextField();
		this.nameField.setBounds(12, 35, 96, 42);
		this.panel.add(this.nameField);
		this.nameField.setColumns(10);
		
		this.textField = new JTextField();
		this.textField.setBounds(120, 35, 299, 42);
		this.textField.setPreferredSize(new Dimension(300, 30));
		this.panel.add(this.textField);
		this.textField.setColumns(10);
		
		this.btnSend = new JButton("Send");
		this.btnSend.setBounds(444, 34, 71, 42);
		this.btnSend.addActionListener(this);
		this.panel.add(this.btnSend);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			writer.println(nameField.getText() + " : " + textField.getText());
			textField.setText("");
		}
		catch(Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try { textArea.append(reader.readLine() + "\n"); }
			catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public void connect()
	{
		try
		{
			socket = new Socket("127.0.0.1", 12345);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			new Thread(this).start();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}