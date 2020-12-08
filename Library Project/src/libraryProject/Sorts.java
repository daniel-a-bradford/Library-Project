package libraryProject;

import java.math.BigInteger;

public class Sorts {
	/** Method bubbleSortBigInt sorts an array of BigIntegers depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
		If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private BigInteger[] bubbleSortInt(BigInteger[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1].compareTo(inputArray[in]) == 1 ) || 
						(!leastToGreatest && inputArray[in-1].compareTo(inputArray[in]) == -1)) {
					BigInteger temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
				}
			}
		}
		return inputArray;
	}
	/** Method bubbleSortInt sorts an array of integers depending on leastToGreatest.
	 * 	If leastToGreatest is true: Bubble sort from least to greatest.
		If leastToGreatest is false: Bubble sort from greatest to least.
	 */
	private int[] bubbleSortInt(int[] inputArray, boolean leastToGreatest) {

		for (int out = 0; out < inputArray.length; out++) {
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputArray.length - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ( (leastToGreatest && inputArray[in-1] > inputArray[in]) || 
						(!leastToGreatest && inputArray[in-1] < inputArray[in])) {
					int temp = inputArray[in-1];
					inputArray[in-1] = inputArray[in];
					inputArray[in] = temp;
				}
			}
		}
		return inputArray;
	}

}
