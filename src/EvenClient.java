




public class EvenClient {
	EvenServerProxy proxy;
	
	public EvenClient(EvenServerProxy proxy) {
		this.proxy = proxy; 
	}

	public int getResult(int num) {
		return proxy.handle(num);
	}

}
