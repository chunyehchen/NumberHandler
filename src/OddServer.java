import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class OddServer {
	public static final int PORT = 888;
	
	public static void main(String[] args) throws IOException {
		OddServerProxy OP = new OddServerProxy();
		OP.getResult();
	}
}

class OddNetServer implements ServerInterface {
	BufferedReader in;
	PrintWriter out;
	Socket socket;
	ServerSocket server;
	int input;

	public OddNetServer() {
		try {
			server = new ServerSocket(OddServer.PORT);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void sendResult(int result) {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(result + "(cache)");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getResult() {
		int result = 0;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			result = (int) (input * Math.random());
			out.println(result + "(remote)");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getInput() {
		try {
			socket = server.accept();
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String str = in.readLine();
			input = Integer.parseInt(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return input;

	}

	public void dispose() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class OddServerProxy implements ServerInterface {
	ServerInterface srv;
	Map<Integer, ResultAndTime> map;

	public OddServerProxy() {
		srv = new OddNetServer();
		map = new HashMap<Integer, ResultAndTime>();
	}

	public int getResult() {
		while (true) {

			// Store in cache
			int input = ((OddNetServer) srv).getInput();
			long currTime = System.currentTimeMillis();

			if (map.containsKey(input)
					&& (currTime - map.get(input).getTime()) < 30000) {
				ResultAndTime rt = map.get(input);
				((OddNetServer) srv).sendResult(rt.getResult());
			} else {
				int res = srv.getResult();
				ResultAndTime rt = new ResultAndTime(res, currTime);
				map.put(input, rt);
			}
		}

	}

	public void dispose() {
		srv.dispose();
	}

}

class ResultAndTime {
	int result;
	long time;

	public ResultAndTime(int result, long time) {
		this.result = result;
		this.time = time;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
