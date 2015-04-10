




class EvenServer {
	public int handle(int num) {
		return num * num;
	}
}

class EvenServerProxy {
	EvenServer srv = new EvenServer();
	
	public int handle(int num) {
		if(num % 6 == 0) {
			return -1;
		} else {
			return srv.handle(num);
		}
	}
}
