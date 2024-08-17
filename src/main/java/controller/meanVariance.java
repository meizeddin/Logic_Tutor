package controller;

import java.util.Arrays;
import java.util.List;

public class meanVariance {
    private static double mean = 0;
    private static double variance = 0;
    private static double squareSum = 0;

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(27, 35, 42, 43, 47, 49, 50, 58, 59, 59, 59, 97, 70, 72, 73, 75, 79, 82, 84, 89);
        double m = mean(list);
        double variance = variance(list);
        System.out.println(m);

        System.out.println(Math.sqrt(variance));


    }

    public static double mean(List<Integer> num) {
        int sum = 0;
        // Check if the list is not empty to avoid division by zero
        if (!num.isEmpty()) {
            for (Integer i : num) {
                sum += (i);
            }
            mean = (double) sum / num.size(); // Convert sum to double for accurate division
        }

        return mean; // Return the calculated mean value
    }

    public static double variance(List<Integer> num) {
        double squaredDiffSum = 0;
        // Check if the list is not empty to avoid division by zero
        if (!num.isEmpty()) {
            for (int i : num) {
                squaredDiffSum += Math.pow(i - mean, 2);
            }
            variance = squaredDiffSum/(num.size() -1) ; // Convert sum to double for accurate division
        }

        return variance; // Return the calculated mean value
    }
}
