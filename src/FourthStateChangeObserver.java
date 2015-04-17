import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FourthStateChangeObserver {
	
	public static final int PORT = 888;
	
	public static void main(String[] args) throws IOException {
		FourthStateChangeObserver PP = new FourthStateChangeObserver();
		PP.getState();
	}
	
	ObjectInputStream ois;
	Socket socket;
	ServerSocket server;
	
	public FourthStateChangeObserver() {
		try {
			server = new ServerSocket(FourthStateChangeObserver.PORT);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public int getState() {
		while(true) {
			try {
				socket = server.accept();
				ois = new ObjectInputStream(socket.getInputStream());
				
				PrintingStrategy ps = (PrintingStrategy) ois.readObject();
				int result = (Integer) ois.readObject();
				ps.print(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void dispose() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
