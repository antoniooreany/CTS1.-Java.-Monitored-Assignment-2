package MonitoredAssignment2;

public class Main {

	public static void main(String[] args) {

		Terminal.put("Let's calculate ax2 + bx + c");

		String aStr = Terminal.getString("Please enter a: ");
		String bStr = Terminal.getString("Please enter b: ");
		String cStr = Terminal.getString("Please enter c: ");
		String xStr = Terminal.getString("Please enter x: ");

		UnlimitedInteger a = new UnlimitedInteger(aStr);
		UnlimitedInteger b = new UnlimitedInteger(bStr);
		UnlimitedInteger c = new UnlimitedInteger(cStr);
		UnlimitedInteger x = new UnlimitedInteger(xStr);

		UnlimitedInteger result = a.times(x.times(x)).plus(b.times(x)).plus(c);
		
//		UnlimitedInteger result = plus(times(a, times(x, x)), times(b, x));

		System.out.println("ax2 + bx + c = " + result);

	}

}
