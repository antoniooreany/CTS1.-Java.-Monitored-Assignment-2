package MonitoredAssignment2;

public class UnlimitedInteger {

	private String operand = "";

	//Constructor
	public UnlimitedInteger(String operand) {

		if(operand.matches("[+-]?\\d+")) {    
			this.operand = operand;
		} 
		else { 
			throw new NumberFormatException();
		} 
	}

	//Add op to operand and return the sum
	public UnlimitedInteger plus(UnlimitedInteger op) {

		String result = "";

		//Turn op into a string
		String opString = op.toString();
		if(isNegative(operand) && isNegative(opString)) {

			//Both numbers are negative
			operand = operand.substring(1, operand.length());
			opString = opString.substring(1, opString.length());
			result = "-" + addition(operand, opString);

			//If result is -0, make result = "0"
			if(result.length() == 2 && result.charAt(0) == '-' && result.charAt(1) == '0') {
				result = "0";
			} 
		} 
		else if((isNegative(operand) && !(isNegative(opString))) || (isNegative(opString) && !isNegative(operand))) { 

			//One (and only one) number is negative
			if (isNegative(operand)) {

				//op is positive, remove + sign if it exists
				if(opString.charAt(0) == '+') {
					opString = (operand.substring(1, opString.length()));
				}
				result = minus(opString, operand.substring(1, operand.length()));
			} else {

				//operand is positive, remove + sign if it exists
				if(operand.charAt(0) == '+') {
					operand = operand.substring(1, opString.length());
				}
				result = minus(operand, opString.substring(1, opString.length()));
			}
		} else {

			//Both numbers are positive    
			//Remove + sign if it exists
			if(operand.charAt(0) == '+') {
				operand = operand.substring(1, operand.length());
			}
			if(opString.charAt(0) == '+') {
				opString = opString.substring(1, opString.length());
			}
			result = addition(operand, opString);
		}

		//Turn result into an UnlimitedInteger
		UnlimitedInteger sum = new UnlimitedInteger(result);
		return sum;
	}


	//Positive op1 and a negative op2, return the difference
	private static String minus(String op1, String op2) {

		String result = "";

		//We'll make sure the two strings are the same length.
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, op1.length() - op2.length());
		} 
		else if (op2.length() > op1.length()) {
			op1 = addZeros(op1, op2.length() - op1.length());
		}

		if(isSmaller(op2,op1)) {

			//Negative number is smaller than positive number; subtraction
			result = subtraction(op1, op2);

			//Remove leading zeroes
			result = subZeroes(result);
		} 
		else {

			//Either the negative number is larger...
			if(isBigger(op2, op1)) {
				result = "-" + subtraction(op2, op1);

				//Remove leading zeroes
				result = subZeroes(result);
			} 
			else {

				//Or the values are equal
				result = "0";
			}
		}

		return result;
	}

	//Positive op1 and op2, return the sum
	//Code based on GeeksforGeeks DANISH_RAZA
	private static String addition(String op1, String op2) {

		String result = "";
		int carry = 0;

		//We'll make sure the two strings are the same length.
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, op1.length() - op2.length());
		} 
		else if (op2.length() > op1.length()) {
			op1 = addZeros(op1, op2.length() - op1.length());
		}

		for(int i = op1.length() - 1; i >=0; i--) {

			//Compute sum of digits and carry
			int sum = digit(op1, i) + digit(op2, i) + carry;
			result = "" + result + (sum % 10);
			carry = sum / 10;
		}

		//Add remaining carry
		if (carry > 0) {
			result = "" + result + carry;
		}

		//Reverse string
		result = reverseString(result);

		//Return string
		return result;
	}

	//Subtract op2 from op1 (op1 must be >= op2)
	//Code based on GeeksforGeeks DANISH_RAZA
	private static String subtraction(String op1, String op2) {

		String result = "";
		int carry = 0;

		op1 = reverseString(op1);
		op2 = reverseString(op2);

		for(int i = 0; i < op1.length(); i++) {

			//Compute difference of digits
			int sub = digit(op1, i) - digit(op2, i) - carry;
			if (sub < 0) { 
				sub = sub + 10; 
				carry = 1; 
			} else  {
				carry = 0; 
			}
			result = result + sub;
		} 

		//Reverse string
		result = reverseString(result);
		return result;
	}

	//Adds x 0's in front of String s
	private static String addZeros(String s, int x) {

		String result = s;

		for(int i = 0; i < x; i++) {
			result = '0' + result;
		}

		return result;
	}

	//Removes leading zeroes from Sting s (preserving - sign if it exists)
	private static String subZeroes(String s) {

		boolean isNegative = false;
		int i = 0;

		if(s.charAt(0) == '-') {
			isNegative = true;
			s = s.substring(1, s.length());
		}

		while(s.charAt(i) == '0') {
			i++;
		}

		s = s.substring(i, s.length());

		if(isNegative) {
			s = '-' + s;
		}

		return s;
	}

	//Returns true if op1 < op2
	private static boolean isSmaller(String op1, String op2) {

		if(op1.length() < op2.length()) {
			return true;
		}
		if(op2.length() < op1.length()) {
			return false;
		}

		for(int i=0; i < op1.length(); i++) {
			if(op1.charAt(i) < op2.charAt(i)) {
				return true;
			} 
			else if(op1.charAt(i) > op2.charAt(i)) {
				return false;
			}
		}

		return false;
	}

	//Returns true if op1 > op2
	private static boolean isBigger(String op1, String op2) {

		if(op1.length() > op2.length()) {
			return true;
		}
		if(op2.length() < op1.length()) {
			return false;
		}

		for(int i=0; i < op1.length(); i++) {
			if(op1.charAt(i) > op2.charAt(i)) {
				return true;
			} 
			else if(op1.charAt(i) < op2.charAt(i)) {
				return false;
			}
		}

		return false;
	}

	//Returns true if String s is negative
	private static boolean isNegative(String s) {

		if(s.charAt(0) == '-') {
			return true;
		}

		return false;
	}

	//Returns the digit value of s at index
	//Code based on Dr. Kratzer's
	private static int digit(String s, int index) {

		return (s.charAt(index) - '0');
	}

	//Reverses String s
	private static String reverseString(String s) {

		String reverse ="";

		for(int i = 0; i < s.length(); i++) {
			reverse = s.charAt(i) + reverse;
		}

		return reverse;
	}

	// In this method, the sign of the product of the strings is determined.
	//Also the method directs the implementation of the Multiplication.
	public UnlimitedInteger times(UnlimitedInteger op) {

		String result = "";
		String signOfResult;

		String op1 = op.toString();
		String op2 = operand;

		// If the strings have different signs, then the sign of the result is '-' negative.
		if ((isNegative(op1) && isNegative(op2)) || (!isNegative(op1) && !isNegative(op2))) {
			signOfResult = "+";
		}
		//Otherwise, the sign of the result is '+' positive.
		else {
			signOfResult = "-";
		}

		String op1Modified = "";
		String op2Modified = "";

		// Here we get the Strings without sign in case they have any.
		if(op1.charAt(0) == '+' || op1.charAt(0) == '-') {
			op1Modified = op1.substring(1);
		}
		else {
			op1Modified = op1;
		}
		if(op2.charAt(0) == '+' || op2.charAt(0) == '-') {
			op2Modified = op2.substring(1);
		}
		else {
			op2Modified = op2;
		}

		// This conditional statements are for the order of the operands in when calling the product method
		// Always the Shorter string is the second parameter in the product method.  
		if (op1Modified.length() < op2Modified.length()) {
			result = product(op2Modified, op1Modified);
		}
		else {
			result = product(op1Modified, op2Modified);
		}

		result = signOfResult + result;

		UnlimitedInteger product = new UnlimitedInteger(result);
		return product;
	}

	private static String product(String op1, String op2) {

		String result = "";

		//The array is used to store the intermediate results of the multiplication.
		//The size of the array is always the length of the shorter operand which is op2.
		int sizeOfArray = 0;
		sizeOfArray = op2.length();

		String sumForMult [] = new String [sizeOfArray];

		//The carryIn is used when the product of two digits is > 9
		//The countForKeepingPosition is used for adding zero(s) at the end of the intermediate results when switching from one digit to the next one.
		int carryIn = 0;
		int countForKeepingPosition = 0;
		int indexOfArray = 0;


		for (int index2 = op2.length() -1; index2 >= 0; ) {

			for (int index1 = op1.length() -1;index1 >=0; --index1) {

				int valueAtOp1 = op1.charAt(index1) - '0';
				int valueAtOp2 = op2.charAt(index2) - '0';

				int mult = (valueAtOp1 * valueAtOp2) + carryIn;

				//The temporaryStorage String to determines the value of the carry in for the next cycle of the loop,
				//and to determines which digit should be in the intermediate result.
				String temporaryStorage = "";
				temporaryStorage = mult + temporaryStorage; 

				//If the length of the temporaryStorage > 1, which means that the product of those digits > 9.
				//So the carryIn value resides at the index 0 of the temporaryStorage,
				// and the digit which will be passed to the intermediate result resides at the index 1.
				if (index1 != 0 && temporaryStorage.length() > 1) {
					carryIn = temporaryStorage.charAt(0) - '0';
					mult = temporaryStorage.charAt(1) - '0';
				}

				//If the length of the temporaryStorage == 1, which means that the product of those digits <= 9.
				//So the carryIn value is 0,
				//and the digit which will be passed to the intermediate result resides at the index 0 of the temporaryStorage. 
				if (index1 != 0 && temporaryStorage.length() == 1) {
					carryIn = 0;
					mult = temporaryStorage.charAt(0) - '0';
				}

				//When index1 == 0, then we must write the product of the specified digits as it is,
				//because there will be no other digit to continue adding the carryIn.
				//That is why in this case the carry in must be 0.
				if (index1 == 0) {
					carryIn =0;
				}
				result = mult + result;

				//Whenever index1 == 0, that means that the loop is done with multiplying the specified digit of the second operand by the all the digits of the first operand.
				//So we have to decrement the value of index2 so we can do the second cycle of the multiplication.
				//Here the method addZerosForPosiiton is called, if the intermediate results needs to be extended with zero(s) to maintain the position.
				//After each time the method addZerosForPosiiton is called, the countForKeepingPosition integer is incremented for the next intermediate result.
				//Then the result is stored in the array for future addition to get the final results.
				//After that the index of the array for future use in case there is another intermediate result.
				//The result is set to "" just to make sure that there will not be any mistakes.
				if (index1 == 0) {
					--index2;
					result = addZerosForPosiiton(result,countForKeepingPosition);
					++countForKeepingPosition;
					sumForMult[indexOfArray] = result;
					++ indexOfArray;
					result = "";
				}
			}
		}

		//After the completion of multiplying all the digits of the the first operand by all the digits of the second operand,
		//We have to sum the intermediate result(s) by using the addition method which was previously used in the first assignment.
		//Initializing the result with the value "0" to avoid any mistakes.
		result = "0";

		//If the array's size == 1, then the results resides at the index 0 of the array, and there is no need to add anything.
		if (sizeOfArray == 1) {
			return result = sumForMult[0];
		}

		//Otherwise, we sum all the values which are stored in the indexes of array.
		else {
			for(int index = 0; index < sizeOfArray; ++index) {
				result =addition(result , sumForMult[index]);
			}
		}

		return result;
	}

	//Here the method addZerosForPosiiton is called, if the intermediate results needs to be extended with zero(s) to maintain the position.
	private static String addZerosForPosiiton(String op,  int countForKeepingPosition) {

		if (countForKeepingPosition == 0) {
			return op;
		}

		for (int index = 1; index <= countForKeepingPosition; ++index) {
			op = op + 0;
		}

		return op;		
	}

	//toString() Override
	public String toString() {

		return "" + operand;
	}    
}