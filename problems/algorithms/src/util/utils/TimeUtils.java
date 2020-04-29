package util.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static class NanoSeconds {
        public static double toMilli(long x) {
            return TimeUnit.MILLISECONDS.convert(x, TimeUnit.NANOSECONDS);
        }
    }
}
