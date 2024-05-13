package en.ratings.own.my.utility;

import static java.lang.Math.pow;

public class MathUtility {
    public static boolean isLastIndex(int index, int size) {
        return index == (size - 1);
    }

    public static double tenPow(int number) {
        return pow(10, number);
    }
}
