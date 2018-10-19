package p2p;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import p2p.Message;

public class FileHandler {

	private static String fileName;
	private static int fileSize;
	private static int[][] fileRange;
	private static boolean[] chunkRequested;
	private String test_handshake;

	Socket requestSocket; // socket connect to the server
	ObjectOutputStream out; // stream write to the socket
	ObjectInputStream in; // stream read from the socket
	String message; // message send to the server
	// String RECIEVED; //capitalized message read from the server
	byte[] RECIEVED; // capitalized message read from the server
	String message_type;
	private final String HANDSHAKE = "HANDSHAKE";
	private final String ACTUAL_MESSAGE = "ACTUAL";
	/** Testing Variables ***/
	String test_handshake1 = "P2PFILESHARINGPROJ0000000000";
	String id = "1003";

	public FileHandler(String fileName, int fileSize) {
		FileHandler.fileName = fileName;
		FileHandler.fileSize = fileSize;

		chunkRequested = new boolean[fileSize];
	}

	public static boolean hasFile() {
		return false;
	}

	public static String getFileName() {
		return fileName;
	}

	public static int[][] getFileRange() {
		return fileRange;
	}

	public static int getFileSize() {
		return fileSize;
	}

	public static void validRequestsList(int begin, int end) {

	}

	public static void requestChunk(int numOfChunk) {
		chunkRequested[numOfChunk] = true;
	}

	String parseMessage(byte[] msg) {
		String tmp = new String(msg, StandardCharsets.UTF_8);

		String type = null;
		// if(tmp.length() == 32){ //this means that it is most likely handshake message
		// type = HANDSHAKE;
		// }
		if (tmp.length() < 6) {
			type = "not Valid";
		} else if (tmp.toLowerCase().contains(test_handshake.toLowerCase())) {
			type = HANDSHAKE;
			String tmp_id;
			tmp_id = tmp.substring(tmp.length() - 4, tmp.length());
			System.out.println(tmp_id);
			// TODO check i

			// } else if(NumberUtils.isNumber(msg.substring(0,4))){
		} else {
			type = ACTUAL_MESSAGE;
		}
		return type;
	}

	String getID(byte[] msg) {
		String handshake = new String(msg, StandardCharsets.UTF_8);
		return handshake.substring(handshake.length() - 4, handshake.length()); // return the last foud
	}

	void startActualMessageProcess(byte[] msg) {
		String message = new String(msg);
		switch (Integer.parseInt(message)) {
		case 0: // choke
			// Choke choke = new Choke();
			// choke.prefNeighborUpdate();
			break;
		case 1: // unchoke
			break;
		case 2: // interested
			// interested(message.intmessage_type, message);
			break;
		case 3: // not interested
			// notInterested(message.intmessage_type, message);
			break;
		case 4: // have
			// interest(id, message, config);
			break;
		case 5: // bitfield
			break;
		case 6: // request
			break;
		case 7: // piece
			break;
		default:
			break;
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

	void sendMessage(byte[] msg) {
		try {
			// stream write the message
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static byte[] interested(int id, String msg) {
		// Create Message
		byte[] finalmessage;
		int len = 1;
		byte[] length = ByteBuffer.allocate(4).putInt(len).array();
		// finalmessage = ArrayUtils.addAll(length, values.message_types[2]);
		// MESSAGE interested = new MESSAGE(finalmessage);
		// send Message
		return null;
		
	}

	public static byte[] notInterested(int id, String msg) {
		// Create Message
		byte[] finalmessage;
		int len = 1;
		byte[] length = ByteBuffer.allocate(4).putInt(len).array();
		// finalmessage = ArrayUtils.addAll(length, values.message_types[3]);
		// MESSAGE not_interested = new MESSAGE(finalmessage);
		// send Message
		return null;

	}

}
