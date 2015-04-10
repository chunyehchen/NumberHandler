import java.io.IOException;
import javax.swing.JOptionPane;

public class OddHandler extends NumHandler {

	@Override
	public void handleNum(int num) {
		if (num % 2 == 1) {
			Client demo = new Client(HandlerType.ODD);
			String resFromServer = null;
			try {
				demo.sendNum(num);
				resFromServer = demo.getResult();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("From Odd server : " + resFromServer);
			System.out.println();

			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "From Odd server :  "
					+ resFromServer, "Notification", JOptionPane.PLAIN_MESSAGE,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		} else {
			next.handleNum(num);
		}
	}

}
