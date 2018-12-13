package MonitoredAssignment2;

public class Main {

	public static void main(String[] args) {

//		UnlimitedInteger unlimitedInteger1 = new UnlimitedInteger("12");
//		UnlimitedInteger unlimitedInteger2 = new UnlimitedInteger("10");
//		
//		UnlimitedInteger unlimitedInteger3 = unlimitedInteger1.plus(unlimitedInteger2);
		
//		System.out.println(unlimitedInteger3);
		
//		UnlimitedInteger unlimitedInteger4 = unlimitedInteger1.times(unlimitedInteger2);
//		System.out.println(unlimitedInteger4);
		
		UnlimitedInteger a = new UnlimitedInteger("12");
		UnlimitedInteger b = new UnlimitedInteger("12");
		UnlimitedInteger c = new UnlimitedInteger("12");
		UnlimitedInteger x = new UnlimitedInteger("12");
		
		UnlimitedInteger result = a.times(x.times(x)).plus(b.times(x)).plus(c);
		
		
		System.out.println(result);
		
	}

}
