package p2p;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Client {
	Socket requestSocket; // socket connect to the server
	ObjectOutputStream out; // stream write to the socket
	ObjectInputStream in; // stream read from the socket
	String message; // message send to the server
	String MESSAGE; // capitalized message read from the server
	FileOutputStream fr;

	public Client() {
	}

	void run() {
		try {
			// create a socket to connect to the server
			requestSocket = new Socket("localhost", 8000);
			System.out.println("Connected to localhost in port 8000");
			// initialize inputStream and outputStream
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			// get Input from standard input
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			byte[] b = new byte[3000];
			fr = new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
			while (true) {
				// System.out.print("Hello, please input a sentence: ");
				// read a sentence from the standard input
				// message = bufferedReader.readLine();
				// Send the sentence to the server
				// sendMessage(message);
				// Receive the upperCase sentence from the server
				in.read(b, 0, b.length);

				fr.write(b, 0, b.length);
				message = bufferedReader.readLine();
				// show the message to the user
				// System.out.println("Receive message: " + MESSAGE);
			}
		} catch (ConnectException e) {
			System.err.println("Connection refused. You need to initiate a server first.");
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// Close connections
			try {
				fr.close();
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	// send a message to the output stream
	void sendMessage(String msg) {
		try {
			// stream write the message
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// main method
	public static void main(String args[]) {
		Client client = new Client();
		client.run();
	}

	void sendM() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendS() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendN() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendI() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendE() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendK() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	void sendR() throws IOException {
		int size = 300;
		//if (server.getSize()) size = server.getSize(x);
		byte []b=new byte[size];
		Socket sr=new Socket("127.0.0.1", 5111);
		InputStream is=sr.getInputStream();
		FileOutputStream fr=new FileOutputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\t.txt");
		is.read(b,0,b.length);
		fr.write(b, 0, b.length);
		fr.close();
		sr.close();
	}
	
	
}
