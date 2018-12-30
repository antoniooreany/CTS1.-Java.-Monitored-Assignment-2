package MonitoredAssignment2;

public class Main {
	public static void main(String[] args) {
//		Terminal.put("Let's calculate ax2 + bx + c");

		String aStr = Terminal.getString("Please enter a: ");
		String bStr = Terminal.getString("Please enter b: ");
		String cStr = Terminal.getString("Please enter c: ");
		String xStr = Terminal.getString("Please enter x: ");

		UnlimitedInteger a = new UnlimitedInteger(aStr);
		UnlimitedInteger b = new UnlimitedInteger(bStr);
		UnlimitedInteger c = new UnlimitedInteger(cStr);
		UnlimitedInteger x = new UnlimitedInteger(xStr);

		UnlimitedInteger result = a.times(x.times(x)).plus(b.times(x)).plus(c);

		System.out.println("ax2 + bx + c = " + result);

		
		// Automatic checking if the times() works correctly
		String checking = "";

		int aInt = 0;
		int bInt = 0;
		int cInt = 0;
		int xInt = 0;
		long resultLong = 0L;

		try {
			aInt = Integer.parseInt(aStr);
			bInt = Integer.parseInt(bStr);
			cInt = Integer.parseInt(cStr);
			xInt = Integer.parseInt(xStr);
			resultLong = Long.parseLong(result.getValue());
			checking = isCorrect(aInt, bInt, cInt, xInt, resultLong) ? "true" : "false";
		} catch (NumberFormatException e) {
			checking = "unknown";
		} 
		
		System.out.println("The times() method works correctly: " + checking);
	}

	private static boolean isCorrect(int a, int b, int c, int x, long result) {
		if (a * x * x + b * x + c == (int) result) {
			return true;
		} else
			return false;
	}
}
