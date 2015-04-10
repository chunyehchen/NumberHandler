




import javax.swing.JOptionPane;

public class EvenHandler extends NumHandler {

	@Override
	public void handleNum(int num) {
		if (num % 2 == 0) {
			EvenServerProxy proxy = new EvenServerProxy();
			EvenClient demo	 = new EvenClient(proxy);
			
			String resFromServer =   String.valueOf(demo.getResult(num));
			System.out.println("From Even server : " + resFromServer);
			System.out.println();
			
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(null,
					"From Even server :  " + resFromServer, "Notification", 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					options, 
					options[0]);
		} else {
			next.handleNum(num);
		}

	}
}
