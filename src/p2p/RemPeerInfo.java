package p2p;

public class RemPeerInfo {
	public String peerId;
	public String peerAddress;
	public String peerPort;
	
	public RemPeerInfo(String pId, String pAddress, String pPort) {
		peerId = pId;
		peerAddress = pAddress;
		peerPort = pPort;
	}
}
