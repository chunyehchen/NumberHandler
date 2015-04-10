




import java.io.IOException;

import javax.swing.JOptionPane;

public class PrimeHandler extends NumHandler {

	@Override
	public void handleNum(int num) {
		if (isPrime(num)) {
			Client client = new Client(HandlerType.PRIME);
			String resFromServer = null;
			try {
				client.sendNum(num);
				resFromServer = client.getResult();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("From Prime server : " + resFromServer);
			System.out.println();
			
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "From Prime server :  "
					+ resFromServer, "Notification", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} else {
			next.handleNum(num);
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
