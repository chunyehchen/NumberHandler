




import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private HandlerType type;

	public Client(HandlerType type) {
		this.type = type;
		try {
			if (type.equals(HandlerType.PRIME)) {
				socket = new Socket("localhost", PrimeServer.PORT);
			} else {
				socket = new Socket("localhost", OddServer.PORT);
			}

			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendNum(int num) throws UnknownHostException, IOException {
		out.println(num);
		System.out.println(type + " client starting...");
	}

	public String getResult() {

		String result = null;
		try {
			result = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
