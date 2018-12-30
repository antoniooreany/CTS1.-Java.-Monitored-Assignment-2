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

		System.out.println("ax2 + bx + c = " + result);

		int aInt = Integer.parseInt(aStr);
		int bInt = Integer.parseInt(bStr);
		int cInt = Integer.parseInt(cStr);
		int xInt = Integer.parseInt(xStr);
		long resultLong = Long.parseLong(result.getValue());

		System.out.println(resultLong);
		
		System.out.println(isCorrect(aInt, bInt, cInt, xInt, resultLong));

//		String op1 = "00001111111";
//		char charOp2 = '7';
//
//		System.out.println(UnlimitedInteger.manyByOneDigitMult(op1, charOp2));

//		String op1 = "12345";
//		String op2 = "12345";
//		System.out.println(UnlimitedInteger.sum(op1, op2));

//		System.out.println(-3/10);

//		String op1 = "34";
//		String op2 = "18";
//
//		System.out.println(UnlimitedInteger.subtract(op1, op2));

	}

	private static boolean isCorrect(int a, int b, int c, int x, long result) {

		if (a * x * x + b * x + c == (int) result) {
			return true;
		} else
			return false;
	}
}
