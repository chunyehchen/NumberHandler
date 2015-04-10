




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeServer {
	public static final int PORT = 777;
	
	public static void main(String[] args) throws IOException {
		PrimeServerProxy PP = new PrimeServerProxy();
		PP.getResult();
	}
}

class PrimeNetServer implements ServerInterface {
	BufferedReader in;
	PrintWriter out;
	Socket socket;
	ServerSocket server;
	
	public PrimeNetServer() {
		
		try {
			server = new ServerSocket(PrimeServer.PORT);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public int getResult() {
		
		while(true) {
			try {
				socket = server.accept();
				
				in = new BufferedReader( new InputStreamReader(socket.getInputStream()));               
				out = new PrintWriter( socket.getOutputStream(), true );
				
				String str = in.readLine();
				int input = Integer.parseInt(str);
				int result = 2*input;
				
				out.println(result);
				
			} catch (IOException e) {
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

class PrimeServerProxy implements ServerInterface {
	ServerInterface srv;

	public PrimeServerProxy() {
		srv = new PrimeNetServer(); 
	}

	public int getResult() {
		return srv.getResult();
	}
	
	public void dispose() {
		srv.dispose();
	}
	
}


