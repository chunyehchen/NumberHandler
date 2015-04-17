public class LocalObserverProxy implements ObserverProxy {

	private StateHandler subj;
	private LocalObserver observer;
	private int printCount;
	private PrintingStrategyFactory fac;
	private PrintingStrategy ps;
	
	public LocalObserverProxy(StateHandler subj, PrintingStrategyFactory fac) {
		this.subj = subj;
		observer = new LocalObserver();
		this.fac = fac;
		printCount = 0; 
	}
	
	@Override
	public void update() {
		System.out.println();
		System.out.println("Local observer proxy info:");
		if(printCount % 3 == 0) {
			ps = fac.getStrategy();
		}
		printCount++;
		observer.update(subj.getState(),ps);
	}	
}
