
public class PrintingStrategyFactory {
	
	private static PrintingStrategyFactory fac = null;
	private int count = 0;

	private PrintingStrategyFactory() {
		
	}
	
	public static PrintingStrategyFactory getInstance() {
		if(fac == null) {
			fac = new PrintingStrategyFactory();
		}
		else {
			System.out.println("PrintingStrategyFactory instance already exists. Failed to generate a seconed one.");
		}
		return fac;
	}
	
	public PrintingStrategy getStrategy() {
		count++;
		if (count % 2 == 0)
			return new BarChartStrategy(); 
		else
			return new LogStrategy();
	}
}

