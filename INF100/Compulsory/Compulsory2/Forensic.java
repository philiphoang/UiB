import java.util.Random;
import java.util.Arrays;

/**
 * This program produce a histogram based on body temperature of a dead body.
 * The program makes use of Newton's law of cooling to compute time that has
 * passed since the death based on the current temperature of the body.
 *
 * @author Philip Hoang
 */
public class Forensic {

	/**
	* Compute the time in hours required for a body to cool down to temperature
	* degrees. Gaussian noise is added to simulate parameter uncertainty.
	*
	* @param temperature
	*            The temperature of the body when found.
	* @return the time in hours required for the body to cool down to
	*         temperature degrees.
	*/
	public static double cooldown(double temperature) {
		// we need this object to generate Gaussian random variables
		// (remember to import java.util.Random)
		Random random = new Random();

		// the average body temperature of a (living) human
		double bodyTemperature = 37;

		// add noise to simulate that the body temperature of the victim at the
		// time of death is uncertain
		bodyTemperature += random.nextGaussian();

		// compute the time required for the body to cool down from
		// bodyTemperature to temperature using Newton's law of cooling.
		double cooldownTime = Math.log(bodyTemperature / temperature);
		cooldownTime *= 1 / bodyTemperature;

		// normalize this value such that cooling down from 37 to 32 degrees
		// takes 1 hour. we assume that we have measured this for the
		// environment that the body is found in. we add Gaussian noise to
		// simulate measurement uncertainty.
		cooldownTime *= 255 + random.nextGaussian();

		return cooldownTime;
	}

	/**
	 * Create and return an array (samples) which contains result from a call to
	 * cooldown() with a given temperature;
	 *
	 * @param temperature
	 *            The temperature of the body when found.
	 * @param int numSamples
	 *            The number of samples, the size of the array
	 *
	 * @return the array that contains the result from cooldown() with the size
	 *             of numSamples
	 */
	public static double[] cooldownSamples(int temperature, int numSamples) {
		double[] samples = new double[numSamples];

		for (int i = 0; i < samples.length; i++)
			samples[i] = cooldown(temperature);

		return samples;
	}

	/**
	 * Find the minimun value from an array
	 * @param  double[] array
	 *            The array with samples
	 * @return the minimum value of the array
	 */
	public static double minFromArray(double[] array) {
		double min = array[0];

		for (int i = 0; i < array.length; i++)
			if (array[i] < min)
				min = array[i];

		return min;
	}

	/**
	 * Find the maximum value from an array
	 * @param  double[] array
	 *            The array with samples
	 * @return the maximum value of the array
	 */
	public static double maxFromArray(double[] array) {
		double max = array[0];

		for (int i = 0; i < array.length; i++)
			if (array[i] > max)
				max = array[i];
		
		return max;
	}

	/**
	 * Splits the range of values in an array into equally sized ranges and counts
	 * the number of samples that fall within each range.
	 *
	 * @param  double[] array
	 *                  The array with samples
	 * @param  int      numRanges
	 *                  Range of size
	 * @return the array that counts the number of samples that fall into each of
	 * ranges.
	 */
	public static double[] countsFromArray(double[] array, int numRanges) {
		// The array counts[] counts the samples that fall within the specific range
		double[] counts = new double[numRanges];
		double min = minFromArray(array);
		double max = maxFromArray(array);
		// The equally sized range
		double rangeSize = (max - min) / (numRanges - 1);

		// Iterate over the array and count the number of samples that fall
		// withing range.
		for(double value : array) {
			for(int i = (int) ((value - min) / rangeSize); i < array.length; i++)
				if((rangeSize * i <= (value - min)) && (value - min) < rangeSize * (i + 1))
					counts[i]++;
		}

		return counts;
	}

	/**
	 * Prints each element of the 2d input array
	 *
	 * @param String[][] array2d
	 *                   The array that are going to get printed out
	 */
	public static void printArray2d(String[][] array2d) {
		for(String[] a : array2d) {
			for(String s : a) {
				System.out.print(s);
			}
			System.out.println(" ");
		}
	}

	/**
	 * Converts the array from countsFromArray() into an array that can get
	 * printed out.
	 *
	 * @param  double[] counts
	 *                  The array that are going to converted 2d-array
	 * @return the converted array (2d-array)
	 */
	public static String[][] array2dFromCounts(double[] counts) {
		final int PRINT_WIDTH = 50;
		String[][] array2d = new String[counts.length][PRINT_WIDTH];
		double max = maxFromArray(counts);

		// Iterate over the 2d-array and put in "#" or " " based on the value
		// of counts[].
		for(int i = 0; i < counts.length; i++) {
			for(int k = 0; k < PRINT_WIDTH; k++) {
				if(k < ((int) (counts[i] * PRINT_WIDTH / max)))
					array2d[i][k] = "#";
				else
					array2d[i][k] = " ";
			}
		}
		return array2d;
	}

	/**
	 * Uses the other implemented methods to create a histogram.
	 * @param String[][] array2d
	 *                   The 2d-array that are going to get printed out
	 * @param double     arrayMin
	 *                   The minimum value of the array
	 * @param double     arrayMax
	 *                   The maximum value of the array
	 */
	public static void printReport(String[][] array2d, double arrayMin, double arrayMax) {
		double rangeSize = (arrayMax - arrayMin) / ((array2d.length) - 1);
		System.out.println("Time since death probability distribution");
		System.out.printf("- Each line corresponds to %.2f hours.\n", rangeSize);
		System.out.println("====================================================");
		System.out.printf("%.2f hours\n", arrayMin);
		// Here we print out the actual array
		printArray2d(array2d);
		System.out.printf("%.2f hours\n", arrayMax);
		System.out.println("====================================================");
	}

	public static void main(String args[]) {
		double[] array = cooldownSamples(27, 100);
		double[] counts = countsFromArray(array, 20);
		String[][] array2d = array2dFromCounts(counts);
		printReport(array2d, minFromArray(array), maxFromArray(array));
	}

}
