package math;

/**
 * 400
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
 */
public class NthDigit {

    public int findNthDigit(int n) {
        if (n < 10) return n;
        long low = 1;
        int k = 0;
        long q = 0;
        while (n > q){
            low = q + 1;
            q += ++k * (Math.pow(10, k-1)) * 9;
        }
        long num = (long)Math.pow(10, k-1) + (n - low) / k;
        return Character.getNumericValue(String.valueOf(num).charAt((int)(n - low) % k));
    }
}
