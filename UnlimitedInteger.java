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
	
	
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	public static String times(String a, String b) {

		String sign = "";
		String result = "";
		String zeros = "";

		if (a.length() == 0 || b.length() == 0) {
			throw new NumberFormatException();
		}

		if (sign(a) != sign(b)) {
			sign = "-";
		}

		String aWithoutSign = deleteSign(a);
		String bWithoutSign = deleteSign(b);

		if (!isValidNumber(aWithoutSign) || !isValidNumber(bWithoutSign)) {
			throw new NumberFormatException();
		}

		for (int j = bWithoutSign.length() - 1; j >= 0; j--) {
			String stringB = Character.toString(bWithoutSign.charAt(j));
			result = plus(result, manyByOneDigitMult(aWithoutSign, stringB) + zeros);
			zeros += "0";
		}

		return sign + result;
	}

	private static String sign(String number) {

		if (number.charAt(0) == '-') {
			return "-";
		}
		return "+";
	}
	
	// Method checking for errors in the input of the user
	public static boolean isValidNumber(String a) {

		// Checks for strings a and b if the Unicode value is between values of '0' and
		// '9'
		// If it were outside these parameters the characters could be symbols or
		// letters which are irrelevant for mathematical operations
		for (int i = a.length() - 1; i >= 0; i--) {
			if (a.charAt(i) < '0' || a.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	private static String manyByOneDigitMult(String a, String b) {

		String result = "";
		int carry = 0;
		int i = a.length() - 1;
		int j = b.length() - 1;

		while (j >= 0 && i >= 0) {

			int value1 = a.charAt(i) - '0';
			int value2 = b.charAt(j) - '0';
			int value3 = (value1 * value2 + carry) % 10;
			carry = (value1 * value2 + carry) / 10;
			i--;
			result = value3 + result;
		}
		if (carry != 0) {
			return carry + result;
		}
		while (result.charAt(0) == '0' && result.length() > 1) {
			result = result.substring(1);
		}
		return result;
	}

	private static String deleteSign(String number) {
		if (number.charAt(0) == '+' || number.charAt(0) == '-') {
			number = number.substring(1);
		}
		return number;
	}
	
	
	
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	public UnlimitedInteger plus(UnlimitedInteger op) {
		
		return new UnlimitedInteger(plus(value, op.getValue()));		
	}
	
	public static String plus(String op1, String op2) {
		String result = "";
		char sign = 0;

		if (op1.length() == 0) {
			return op2;
		}
		
		if (op2.length() == 0) {
			return op1;
		}
		
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
