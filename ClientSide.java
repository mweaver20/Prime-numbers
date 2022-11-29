package primeNumbersSrc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

public class ClientSide {

	private static void constructGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyFrame frame = new MyFrame();
		frame.setVisible(true);

	}// end of GUI construction method

	public static void main(String[] args) {
		args = null;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});
	}// end of main method
}// end of client class

//start of JFrame class
@SuppressWarnings("serial")
class MyFrame extends JFrame {

	// to be accessed by event listeners
	JLabel first;
	JTextField NumberTextField;
	JButton check;
	JLabel results;

	public MyFrame() {
		super();
		init();
	}

	private void init() {
		// basic frame setup
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("prime number calculator");
		this.setLayout(new GridLayout(2, 2));

		// initializing component variables
		first = new JLabel("enter a number to check prime: ");
		NumberTextField = new JTextField();
		check = new JButton("Check");
		results = new JLabel("Results: ");

		// adding frame components
		this.add(first);
		this.add(NumberTextField);

		// second row
		this.add(check);
		this.add(results);

		// adding event listener to calculate button
		check.addActionListener(new MyActionListener(this));
		this.pack();
	}// end if init()

}// end of Jframe class

class MyActionListener implements ActionListener {
	MyFrame fr;

	public MyActionListener(MyFrame frame) {
		fr = frame;
	}

	public void actionPerformed(ActionEvent e) {
		String GUIinput = fr.NumberTextField.getText();
		String GUIString = GUIinput + "\n";
			
			try {
				Socket connection = new Socket("127.0.0.1", 1236);
				//System.out.println("connected to server");
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				OutputStream output = connection.getOutputStream();
				output.write(GUIString.getBytes());					
				String primeResult = input.readLine();
				
				fr.results.setText("Results: " + primeResult);
				while(!connection.isClosed()) {
					connection.close();
					//System.out.println("connection closed");					
				}

			} catch (IOException c) {
				c.printStackTrace();
				System.out.println("Perminitly closing connections and shutting client down");
				System.exit(0);
			}
	}
}
// end of action listener class
