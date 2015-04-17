import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class FourthStateChangeObserverProxy implements ObserverProxy{

	private StateHandler subj;
	private ObjectOutputStream out;
	private Socket socket;
	private int count;
	private int printCount;
	
	private PrintingStrategyFactory fac;
	private PrintingStrategy ps;
	
	public FourthStateChangeObserverProxy(StateHandler subj, PrintingStrategyFactory fac) {
		this.subj = subj;
		count = 0;
		this.fac = fac;
		printCount = 0;
	}
	
	@Override
	public void update() {
		count++;
		int num = subj.getState();
		try {
			System.out.println();
			System.out.println("4th change state observer proxy info:");
			System.out.println("Change count: " + count);
					
			if(count % 4 == 0) {
				if(printCount % 3 == 0) {
					ps = fac.getStrategy();
				}
				printCount++;
				
				socket = new Socket("localhost", FourthStateChangeObserver.PORT);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(ps);
				out.writeObject(new Integer(num));
				System.out.println("4th change state proxy sent update to observer.");
			}
			else {
				System.out.println("4th change state proxy didn't send update.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
