public class MainApp {
	
	public static void main(String[] args) {
		
		PrintingStrategyFactory fac = PrintingStrategyFactory.getInstance();
		
		final StateHandler handler = setUpChain(fac);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NumberHandlerWindow.createAndShowGUI(handler);
			}
		});
	}
	
	public static StateHandler setUpChain(PrintingStrategyFactory fac) {
		StateHandler handler = new StateHandler();
		
		PrimeObserverProxy pObs = new PrimeObserverProxy(handler, fac);
		LocalObserverProxy lObs = new LocalObserverProxy(handler, fac);
		FourthStateChangeObserverProxy fObs = new FourthStateChangeObserverProxy(handler, fac);
		
		handler.attach(pObs);
		handler.attach(lObs);
		handler.attach(fObs);
		
		return handler;
	}
}
