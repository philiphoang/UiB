import java.util.Random;
import java.util.Arrays;


public class Forensic {

    /**
     * Compute the time in hours required for a body to cool down to temperature
     * degrees. Gaussian noise is added to simulate parameter uncertainty.
     * @param temperature The temperature of the body when found.
     * @return the time in hours required for the body to cool down to
     * temperature degrees.
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

    public static double[] cooldownSamples(int temperature, int numSamples) {
        double[] samples = new double[numSamples];

        for(int i = 0; i < samples.length; i++)
          samples[i] = cooldown(temperature);

        return samples;
    }

    public static double minFromArray(double[] array) {
      double min = array[0];

      for (int i = 0; i < array.length; i++){
        if (array[i] < min)
          min = array[i];
      }

      return min;
    }

    public static double maxFromArray(double[] array) {
      double max = array[0];

      for (int i = 0; i < array.length; i++){
        if (array[i] > max)
          max = array[i];
      }

      return max;
    }

    public static double[] countsFromArray(double[] array, int numRanges) {
      double[] counts = new double[numRanges];
      double min = minFromArray(array);
      double max = maxFromArray(array);
      double rangeSize = (max+min)/numRanges;


      System.out.println("Rangesize: " + rangeSize);

      for(int i = 0; i < array.length; i++) {
        double value = array[i];
        if((rangeSize * i <= (value - min)) && (value - min) < rangeSize * (i + 1))
          
      }
      // for(double value : array) {
      //   int j = 0;
      //   System.out.println("Value: " + value);
      //   System.out.println("Min: " + min);
      //   for(int i = (int) ((value - min)/rangeSize); (rangeSize * i <= (value - min)) && (value - min) < rangeSize * (i + 1); i++) {
      //     System.out.println("i value: " + i);
      //     counts[j] = ;
      //   }
      // }

      return counts;
    }

    public static void main(String args[]) {
      double[] cdSamples = cooldownSamples(27, 10);

      System.out.println(Arrays.toString(cdSamples));

      System.out.println("Min : " + minFromArray(cdSamples));
      System.out.println("Max : " + maxFromArray(cdSamples));

      System.out.println(Arrays.toString(countsFromArray(cdSamples, 5)));
    }

}