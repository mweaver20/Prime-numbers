package primeNumbersSrc;

public class Test {

	public static void main(String[] args) {
		
		Thread serverThread = new Thread() {
			public void run() {
				ServerSide.main(args);
			}
		};
		
		Thread clientThread = new Thread() {
			public void run() {
				ClientSide.main(args);
			}
		};
		
		serverThread.start();
		
		try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		clientThread.start();
	}

}
