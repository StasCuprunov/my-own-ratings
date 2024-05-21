package en.ratings.own.my.utility.math;

import static java.lang.Math.pow;

public class MathUtility {
    public static boolean isLastIndex(int index, int size) {
        return index == (size - 1);
    }

    public static double tenPow(int number) {
        return pow(10, number);
    }

    public static double twoPow(int number) {
        return pow(2, number);
    }
}
