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

		Terminal.put("ax2 + bx + c = " + result.getValue());

		
		// Automatic testing if the times() works correctly
		String checking = "";

		long aInt = 0;
		long bInt = 0;
		long cInt = 0;
		long xInt = 0;
		long resultLong = 0L;

		try {
			aInt = Long.parseLong(aStr);
			bInt = Long.parseLong(bStr);
			cInt = Long.parseLong(cStr);
			xInt = Long.parseLong(xStr);
			resultLong = Long.parseLong(result.getValue());
			checking = isCorrect(aInt, bInt, cInt, xInt, resultLong) ? "YES" : "NO";
		} catch (NumberFormatException e) {
			checking = "UNKNOWN";
		} 
		
		Terminal.put("\n\nThe times() method works correctly: " + checking);
	}

	private static boolean isCorrect(long a, long b, long c, long x, long result) {
		if (a * x * x + b * x + c == result) {
			return true;
		} else
			return false;
	}
}
