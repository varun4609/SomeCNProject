package p2p;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class RemPeerInfo {
	public String peerId;
	public String peerAddress;
	public String peerPort;
	
	public RemPeerInfo(String pId, String pAddress, String pPort) {
		peerId = pId;
		peerAddress = pAddress;
		peerPort = pPort;
	
	
	 try {
			@SuppressWarnings("resource")
			FileInputStream fis = new FileInputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\test.txt");
			byte b[] = new byte[3000];
			fis.read(b, 0, b.length);

			int chunkSize = (int) Math.ceil(b.length / 100);
			int currPtr = 0;
			while (currPtr < b.length) {
				// receive the message sent from the client
				// message = (String)in.readObject();

				// show the message to the user
				System.out.println("Sending message: " + (currPtr + chunkSize));
				// Capitalize all letters in the message
				// MESSAGE = Integer.toString(i);
				// send MESSAGE back to the client
				sendMessage(b, currPtr, chunkSize);
				ObjectInputStream bufferedReader = null;
				@SuppressWarnings({ "deprecation", "null" })
				Object message = bufferedReader.readLine();
				currPtr += chunkSize;
				int i = 0;
				i++;
			}
		} catch (Exception e) {
			System.err.println("Data received in unknown format");
		}
	}

	private void sendMessage(byte[] b, int currPtr, int chunkSize) {
		// TODO Auto-generated method stub
		
	}
}
