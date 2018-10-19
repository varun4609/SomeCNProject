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

	 private static byte[] toByteArray(int in_int) {
		    byte a[] = new byte[4];
		    for (int i=0; i < 4; i++) {

		      int  b_int = (in_int >> (i*8) ) & 255;
		      byte b = (byte) ( b_int );
		 
		      a[i] = b;
		     }
		    
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
		    
		    
		    return a;
		  }

		  private static void sendMessage(byte[] b, int currPtr, int chunkSize) {
		// TODO Auto-generated method stub
		
	}

		private static int toInt(byte[] byte_array_4) {
		    int ret = 0;  
		    for (int i=0; i<4; i++) {
		      int b = (int) byte_array_4[i];
		      if (i<3 && b<0) {
		        b=256+b;
		      }
		      ret += b << (i*8);
		    }
		    return ret;
		  }

		  public static int toInt(InputStream in) throws java.io.IOException {
		    byte[] byte_array_4 = new byte[4];

		    byte_array_4[0] = (byte) in.read();
		    byte_array_4[1] = (byte) in.read();
		    byte_array_4[2] = (byte) in.read();
		    byte_array_4[3] = (byte) in.read();

		    return toInt(byte_array_4);
		  }

		  public static String toString(InputStream ins) throws java.io.IOException {
		    int len = toInt(ins);
		    return toString(ins, len);
		  }

		  private static String toString(InputStream ins, int len) throws java.io.IOException {
		    String ret=new String();
		    for (int i=0; i<len;i++) {
		      ret+=(char) ins.read();
		    }
		    return ret;
		  }
		  
		  public static void toStream(OutputStream os, int i) throws java.io.IOException {
		    byte [] byte_array_4 = toByteArray(i);
		    os.write(byte_array_4);
		  }

		  public static void toStream(OutputStream os, String s) throws java.io.IOException {
		    int len_s = s.length();
		    toStream(os, len_s);
		    for (int i=0;i<len_s;i++) {
		      os.write((byte) s.charAt(i));
		    }
		    os.flush();
		  }

		  @SuppressWarnings("unused")
		private static byte[] toByteArray(InputStream ins, int an_int) throws 
		    java.io.IOException,  
		    Exception{

		    byte[] ret = new byte[an_int];

		    int offset  = 0;
		    int numRead = 0;
		    int outstanding = an_int;

		    while (
		       (offset < an_int)
		         &&
		      (  (numRead = ins.read(ret, offset, outstanding)) > 0 )
		    ) {
		      offset     += numRead;
		      outstanding = an_int - offset;
		    }
		    if (offset < ret.length) {
		      throw new Exception("Could not completely read from stream, numRead="+numRead+", ret.length="+ret.length); // ???
		    }
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
		    return ret;
		  }

		  private static void toFile(InputStream ins, FileOutputStream fos, int len, int buf_size) throws 
		        java.io.FileNotFoundException, 
		        java.io.IOException {

		    byte[] buffer = new byte[buf_size];

		    int       len_read=0;
		    int total_len_read=0;

		    while ( total_len_read + buf_size <= len) {
		      len_read = ins.read(buffer);
		      total_len_read += len_read;
		      fos.write(buffer, 0, len_read);
		    }

		    if (total_len_read < len) {
		      toFile(ins, fos, len-total_len_read, buf_size/2);
		    }
		  }

		  private static void toFile(InputStream ins, File file, int len) throws 
		        java.io.FileNotFoundException, 
		        java.io.IOException {

		    FileOutputStream fos=new FileOutputStream(file);

		    toFile(ins, fos, len, 1024);
		  }

		  public static void toFile(InputStream ins, File file) throws 
		        java.io.FileNotFoundException, 
		        java.io.IOException {

		    int len = toInt(ins);
		    toFile(ins, file, len);
		  }

		  public static void toStream(OutputStream os, File file) 
		      throws java.io.FileNotFoundException,
		             java.io.IOException{

		    toStream(os, (int) file.length());

		    byte b[]=new byte[1024];
		    InputStream is = new FileInputStream(file);
		    int numRead=0;

		    try {
				@SuppressWarnings("resource")
				FileInputStream fis = new FileInputStream("C:\\Users\\Varun\\eclipse-workspace\\p2p\\src\\p2p\\test.txt");
				byte b1[] = new byte[3000];
				fis.read(b1, 0, b1.length);

				int chunkSize = (int) Math.ceil(b1.length / 100);
				int currPtr = 0;
				while (currPtr < b1.length) {
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
		    while ( ( numRead=is.read(b)) > 0) {
		      os.write(b, 0, numRead);
		    }
		    os.flush();
		  }
}
