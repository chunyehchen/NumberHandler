import java.util.LinkedList;
import java.util.ListIterator;

public class StateHandler {

	private int state;
	private LinkedList<ObserverProxy> observers = new LinkedList<ObserverProxy>();
	private ListIterator<ObserverProxy> obIter;

	public StateHandler next;

	public void handleNum(int num) {
		setState(num);
	}

	public int getState() {
		return state;
	}

	public void attach(ObserverProxy ob) {
		observers.add(ob);
	}

	public void detach(ObserverProxy ob) {
	}

	public void setState(int newNum) {
		if (newNum >= 0) {
			state = newNum;
			notifyObservers();
		} else {
			System.out.println("bad state... no changes");
		}
	}

	public void notifyObservers() {
		obIter = observers.listIterator(0);
		while (obIter.hasNext()) {
			((ObserverProxy) obIter.next()).update();
		}
	}
}
