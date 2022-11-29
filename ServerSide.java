package primeNumbersSrc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

	public static void main(String[] args) {
		
		args = null;
		// initializing needed variables for user input/output and server/client socket
		Boolean shutdown = false;
		Integer n;
		ServerSocket server = null;
		Socket client = null;
		BufferedReader input = null; 
		OutputStream output = null;
		
		try {
			server = new ServerSocket(1236);
			System.out.println("Port bound: waiting for client");
			
			
			while(!shutdown){	
				
				//setting up client connections
				client = server.accept();
				input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				output = client.getOutputStream();
				
				//taking client input as String
				String clientInput = input.readLine();
				
				//checking input for shutdown command
				if((clientInput).equalsIgnoreCase("shutdown")) {
					System.out.println("shutting down");
					shutdown = true;
				}
				
				try {
					// parsing client input for int n
					n = Integer.parseInt(clientInput);
					
					//Formatting and outputting fib results to client
					String result = primeCalculator(n);
					String response = result + "\n";
					output.write(response.getBytes());
						 
					//closing connection
					client.close();
				}catch(NumberFormatException e) {
					System.err.println("please only put a positive integer");
				}
				
			}
			//closing server when while loop is exited
			server.close();
			System.out.println("Server terminated");
			System.exit(-1);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}


	}//end of main method

	public static String primeCalculator(int n) {
		int i, m = 0, flag = 0;
		String answer = null;
		m = n/2;
		
		if(n == 0 || n == 1) {
			answer = n + " is not a prime number";
		} else {
			for(i = 2; i <= m; i++) {
				if(n % i == 0) {
					answer = n + " is not a prime number";
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				answer = n + " is a prime number";
			}
			
		}
		return answer;
		
	}

}
