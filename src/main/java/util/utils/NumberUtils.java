package util.utils;

public class NumberUtils {
    public static String formatDouble(double d, int n) {
        return String.format("%." + n + "f", d);
    }
}
