
public class LogStrategy implements PrintingStrategy {

	@Override
	public void print(int num) {
		int multiplier = 1;
		int count = 1;
		int i = 1;
		
		System.out.print(i);
		while (i < num) {
			i += multiplier;
			
			if(i >= num) {
				break;
			}
			
			System.out.print("," + i );
			count++;
			
			if(count % 10 == 0) {
				multiplier *= 10;
				count = 1;
			}
		}
		
		System.out.println(": " + num);
	}
}
