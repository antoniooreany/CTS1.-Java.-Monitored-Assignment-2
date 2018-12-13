package MonitoredAssignment2;

public class UnlimitedInteger {
	
	private String value;

	public UnlimitedInteger(String value) {

		// Checking parameter value for validity
		String regex = "[+-]?\\d+";
		if (value.matches(regex)) {
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
	
	public UnlimitedInteger times(UnlimitedInteger op) {
		
		// For the for loop we use int i, because of this I use this Integer.parseInt();
		int length = Integer.parseInt(op.getValue()); 
		
		UnlimitedInteger result = plus(this);
		
		for (int i = 0; i < length - 2; i++) {
			result = plus(result);
		}
		return result;
	}
	
//	public UnlimitedInteger times(UnlimitedInteger op) {
//		return result;
//	}
	
	public UnlimitedInteger plus(UnlimitedInteger op) {
		
		return new UnlimitedInteger(plus(value, op.getValue()));		
	}
	
	private static String plus(String op1, String op2) {
		String result = "";
		char sign = 0;

		// Extracting signs from operands
		char sign1 = getSign(op1);
		char sign2 = getSign(op2);

		// Extracting numbers from operands
		op1 = getNumber(op1);
		op2 = getNumber(op2);

		// Getting standartized strings from operands
		op1 = getStandartizedNumber(op1, op2);
		op2 = getStandartizedNumber(op2, op1);

		// Checking 4 cases op1, op2
		// Both operands have the same sign
		if (sign1 == sign2) {
			result = sum(op1, op2);
			sign = sign1;
		}
		// Operands have different signs
		if (sign1 != sign2) {
			if (isFirstLargerOrEquals(op1, op2)) {
				result = subtruct(op1, op2);
				sign = sign1;
			} else {
				result = subtruct(op2, op1);
				sign = sign2;
			}
		}
		// Return result with its sign (if needed)
		if (sign == '-') {
			result = sign + result;
		}
		return result;
	}
	
	// Returns the String number without its sign
	private static String getNumber(String op) {

		if (op.charAt(0) == '+' || op.charAt(0) == '-') {
			return op.substring(1, op.length());
		}
		return op;
	}

	// Makes substruction operation, getting op1 > op2
	private static String subtruct(String op1, String op2) {

		String result = "";
		char carryIn = '0';
		char carryOut = '0';
		char charSubstr = '0';

		for (int index = op1.length() - 1; index >= 0; index--) {
			carryIn = carryOut;
			char c1 = op1.charAt(index);
			char c2 = op2.charAt(index);
			charSubstr = (char) (c1 - c2 - carryIn + '0' + '0');
			if (charSubstr < '0') {
				charSubstr = (char) (charSubstr + 10);
				carryOut = '1';
			} else {
				carryOut = '0';
			}
			result = charSubstr + result;
		}
		if (carryOut == '1') {
			result = carryOut + result;
		}
		return result;
	}

	// Makes sum operation
	private static String sum(String op1, String op2) {

		String result = "";
		char carryIn = '0';
		char carryOut = '0';
		char charSum = '0';
		for (int index = op1.length() - 1; index >= 0; index--) {
			carryIn = carryOut;
			char c1 = op1.charAt(index);
			char c2 = op2.charAt(index);
			if (c1 + c2 + carryIn > '9' + '0' + '0') {
				charSum = (char) (c1 + c2 + carryIn - '0' - '9' - 1);
				carryOut = '1';
			} else {
				charSum = (char) (c1 + c2 + carryIn - '0' - '0');
				carryOut = '0';
			}
			result = charSum + result;
		}
		if (carryOut == '1') {
			result = carryOut + result;
		}
		return result;
	}

	// Returns true if op1 > op2, otherwise returns false
	private static boolean isFirstLargerOrEquals(String op1, String op2) {

		for (int index = 0; index < op1.length(); index++) {
			if (op1.charAt(index) > op2.charAt(index)) {
				return true;
			} else if (op1.charAt(index) < op2.charAt(index)) {
				return false;
			}
		}
		return true;
	}

	// Returns sign of op
	private static char getSign(String op) {

		char result = '+';
		if (op.charAt(0) == '+' || op.charAt(0) == '-') {
			result = op.charAt(0);
		}
		return result;
	}

	// Returns filled with 0's op1 if op1 < op2
	private static String getStandartizedNumber(String op1, String op2) {

		int lengthDifference = op2.length() - op1.length();

		if (lengthDifference > 0) {
			for (int index = 0; index < lengthDifference; index++) {
				op1 = '0' + op1;
			}
		}
		return op1;
	}
		
	
	private String addZeros(int deltaLength, String opValue) {
		for (int i = 0; i < deltaLength; i++) {
			opValue = "0" + opValue;
		}
		return opValue;
	}

}
