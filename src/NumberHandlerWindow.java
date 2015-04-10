




import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NumberHandlerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 650, HEIGHT = 650;
	private NumHandler handler;

	public NumberHandlerWindow(NumHandler handler) {
		setResizable(true);
		this.handler = handler;
	}

	public void addComponentsToPane(Container pane) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(15, 15));

		ButtonListener listener = new ButtonListener();
		// Add buttons 0-224 in for loop
		for (int i = 0; i < 225; i++) {
			JButton button = new JButton("" + i);
			button.addActionListener(listener);
			panel.add(button);
		}
		pane.add(panel);
	}

	class ButtonListener implements ActionListener {
		String label = null;

		public void actionPerformed(ActionEvent e) {
			label = e.getActionCommand();
			System.out.println(label + " pressed");

			// Send to Processor chain
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					handler.handleNum(Integer.parseInt(label));
				}
			});
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method is invoked
	 * from the event dispatch thread.
	 * 
	 * @param handler
	 */
	public static void createAndShowGUI(NumHandler handler) {
		// Create and set up the window.
		NumberHandlerWindow frame = new NumberHandlerWindow(handler);
		frame.setName("Number Handler Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);

		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}
