package util.utils;

public class MemoryUtils {
    public double getMemory(Runtime r) {
        return bytesToMb(r.totalMemory() - r.freeMemory());
    }

    public double bytesToMb(long x) {
        return ((double) x) / 1_000_000;
    }
}
