package p2p;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Server {

	private static final int sPort = 8000; // The server will be listening on this port number

	public static void main(String[] args) throws Exception {
		System.out.println("The server is running.");
		ServerSocket listener = new ServerSocket(sPort);
		int clientNum = 1;
		try {
			while (true) {
				new Handler(listener.accept(), clientNum).start();
				System.out.println("Client " + clientNum + " is connected!");
				clientNum++;
			}
		} finally {
			listener.close();
		}

	}

	/**
	 * A handler thread class. Handlers are spawned from the listening loop and are
	 * responsible for dealing with a single client's requests.
	 */
	private static class Handler extends Thread {
		private String message; // message received from the client
		private String MESSAGE; // uppercase message send to the client
		private Socket connection;
		private ObjectInputStream in; // stream read from the socket
		private ObjectOutputStream out; // stream write to the socket
		FileInputStream fis;
		private int no; // The index number of the client

		public Handler(Socket connection, int no) {
			this.connection = connection;
			this.no = no;
		}

		public void run() {
			try {
				// initialize Input and Output streams
				out = new ObjectOutputStream(connection.getOutputStream());
				out.flush();
				int i = 1;
				in = new ObjectInputStream(connection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				try {
					fis = new FileInputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\test.txt");
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
						message = bufferedReader.readLine();
						currPtr += chunkSize;
						i++;
					}
				} catch (Exception e) {
					System.err.println("Data received in unknown format");
				}
			} catch (IOException ioException) {
				System.out.println("Disconnect with Client " + no);
			} finally {
				// Close connections
				try {
					fis.close();
					in.close();
					out.close();
					connection.close();
				} catch (IOException ioException) {
					System.out.println("Disconnect with Client " + no);
				}
			}
		}

		// send a message to the output stream
		public void sendMessage(byte[] msg, int start, int end) {
			try {
				out.write(msg, start, end);
				out.flush();
				System.out.println("Send message: " + start + " to " + end);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

	}

}
