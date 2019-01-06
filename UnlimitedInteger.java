package MonitoredAssignment2;

public class UnlimitedInteger {

	private final String value;

	public UnlimitedInteger(String value) {
		// Checking parameter value for validity
		if (value.matches("[+-]?\\d+")) {
			this.value = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return "UnlimitedInteger: " + value;
	}

	// times() in OOP-style
	public UnlimitedInteger times(UnlimitedInteger unlimInteger) {
		return new UnlimitedInteger(times(value, unlimInteger.value));
	}

	// times() in procedure-style
	private static String times(String op1, String op2) {
		String sign = "";
		String result = "";
		String zeros = "";
		// If signs of operands not equal, result sign must be "-";
		if (getSign(op1) != getSign(op2)) {
			sign = "-";
		}
		op1 = getUnsigned(op1);
		op2 = getUnsigned(op2);
		// The core code of the method
		for (int i = op2.length() - 1; i >= 0; i--) {
			char char2 = op2.charAt(i);
			result = plus(result, times(op1, char2) + zeros);
			zeros += "0";
		}
		result = subZeros(result);
		return sign + result;
	}

	// Multiplication n-digit number by 1-digit number
	private static String times(String op1, char char2) {
		String result = "";
		int carry = 0;
		if (op1.length() == 0) {
			return "";
		}
		int int2 = char2 - '0';
		// The core code of the method
		for (int i = op1.length() - 1; i >= 0; --i) {
			int int1 = op1.charAt(i) - '0';
			int intResult = (int1 * int2 + carry) % 10;
			carry = (int1 * int2 + carry) / 10;
			result = intResult + result;
		}
		if (carry != 0) {
			return carry + result;
		}
		return result;
	}

	// plus() in OOP-style
	public UnlimitedInteger plus(UnlimitedInteger unlimInteger) {
		return new UnlimitedInteger(plus(value, unlimInteger.getValue()));
	}

	// plus() in procedure-style
	private static String plus(String op1, String op2) {
		String result = "";
		char sign = 0;
		// Extracting signs from operands
		char sign1 = getSign(op1);
		char sign2 = getSign(op2);
		// Extracting numbers from operands
		op1 = getUnsigned(op1);
		op2 = getUnsigned(op2);
		// Getting strings with the same length from operands
		op1 = addZeros(op1, op2);
		op2 = addZeros(op2, op1);
		// Checking 4 cases op1, op2
		// Both operands have the same sign
		if (sign1 == sign2) {
			result = addition(op1, op2);
			sign = sign1;
		}
		// Operands have different signs
		if (sign1 != sign2) {
			if (isFirstBigger(op1, op2)) {
				result = subtraction(op1, op2);
				sign = sign1;
			} else {
				result = subtraction(op2, op1);
				sign = sign2;
			}
		}
		// Returns result with its sign (if needed)
		if (sign == '-') {
			result = sign + result;
		}
		result = subZeros(result);
		return result;
	}

	// Makes sum operation with 2 positive ops with the same length.
	private static String addition(String op1, String op2) {
		String result = "";
		int carry = 0;
		int sum = 0;
		// The core code of the method
		for (int index = op1.length() - 1; index >= 0; index--) {
			int c1 = op1.charAt(index) - '0';
			int c2 = op2.charAt(index) - '0';
			sum = (c1 + c2 + carry) % 10;	// TODO addition() has more general algorithm compared with subtraction(). 
			carry = (c1 + c2 + carry) / 10;	// TODO Try to make them similar.
			result = sum + result;
		}
		if (carry == 1) {
			result = carry + result;
		}
		return result;
	}

	// Makes subtraction operation, getting positive op1, op2 with the same length,
	// where op1 > op2
	private static String subtraction(String op1, String op2) {
		String result = "";
		int carry = 0;
		int subtr = 0;
		// The core code of the method
		for (int index = op1.length() - 1; index >= 0; index--) {
			int c1 = op1.charAt(index) - '0';
			int c2 = op2.charAt(index) - '0';
			if (c1 - c2 + carry < 0) {
				subtr = 10 + c1 - c2 + carry;	// TODO addition() has more general algorithm compared with subtraction(). 
				carry = -1;						// TODO Try to make them similar.
			} else {
				subtr = c1 - c2 + carry;
				carry = 0;
			}
			result = subtr + result;
		}
		return result;
	}

	// Returns sign of an argument.
	private static char getSign(String op) {
		// If op is empty String, returns "+"
		if (op.length() == 0) {
			return '+';
		}
		// If a first character of op is '+' or '-', returns this character.
		if (op.charAt(0) == '+' || op.charAt(0) == '-') {
			return op.charAt(0);
		} else
			return '+';
	}

	// Returns the String number without its sign
	private static String getUnsigned(String op) {
		// If op is empty String, returns op
		if (op.length() == 0) {
			return op;
		}
		// If a first character of op is '+' or '-', returns op without first character.
		if (op.charAt(0) == '+' || op.charAt(0) == '-') {
			return op.substring(1);
		}
		return op;
	}

	// Returns true if op1 > op2, otherwise returns false.
	private static boolean isFirstBigger(String op1, String op2) {
		for (int index = 0; index < op1.length(); index++) {
			if (op1.charAt(index) > op2.charAt(index)) {
				return true;
			} else if (op1.charAt(index) < op2.charAt(index)) {
				return false;
			}
		}
		return false;
	}

	// Deletes all redundant leading zeros.
	private static String subZeros(String result) {
		while (result.charAt(0) == '0' && result.length() > 1) {
			result = result.substring(1);
		}
		return result;
	}

	// Returns filled with 0's op1 if op1.length() < op2.length().
	private static String addZeros(String op1, String op2) {
		int lengthDifference = op2.length() - op1.length();
		if (lengthDifference > 0) {
			for (int index = 0; index < lengthDifference; index++) {
				op1 = '0' + op1;
			}
		}
		return op1;
	}
}