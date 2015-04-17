import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PrimeObserverProxy implements ObserverProxy{

	private StateHandler subj;
	private ObjectOutputStream out;
	private Socket socket;
	private int printCount;
	private PrintingStrategyFactory fac;
	private PrintingStrategy ps;
	
	public PrimeObserverProxy(StateHandler subj, PrintingStrategyFactory fac) {
		this.subj = subj;
		printCount = 0;
		this.fac = fac;
	}
	
	@Override
	public void update() {
		int num = subj.getState();
		System.out.println();
		System.out.println("Prime observer proxy info:");
		
		try {
			if(isPrime(num)) {
				if(printCount % 3 == 0) {
					ps = fac.getStrategy();
				}
				printCount++;
				
				socket = new Socket("localhost", PrimeObserver.PORT);
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(ps);
				out.writeObject(new Integer(num));
				System.out.println(num + " is a prime number. Prime observer proxy sent update to observer.");
			}
			else {
				System.out.println(num + " is not a prime number. Prime observer proxy didn't send update to observer.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	private boolean isPrime(int num) {
		if (num == 1) {
			return false;
		}

		if (num == 2) {
			return true;
		}

		int sqrt = (int) Math.sqrt(num);
		for (int i = 2; i <= sqrt; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
