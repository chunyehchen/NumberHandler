
public class BarChartStrategy implements PrintingStrategy {

	@Override
	public void print(int num) {
		int starNum = num / 10;
		
		for(int i=0; i<starNum; i++) {
			System.out.print("*");
		}
		System.out.println(num);
	}

}
