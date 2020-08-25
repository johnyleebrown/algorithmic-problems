package math.numberTheory.fib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciNumberTest {
    Set<Integer> set;
    int n;
    int numberOfTests;
    Random r;

    @BeforeEach
    void setUp() {
        r = new Random();
        n = r.nextInt(1_000_000);
        set = FibNumbers.generate(n);
        numberOfTests = Math.min(100, n);
    }

    @Test
    void test1() {
        System.out.println("[Test 1]");
        System.out.println("Count of tests: " + numberOfTests);
        System.out.println("Count of numbers: " + n);
        FibonacciNumber.Solution s1 = new FibonacciNumber.Solution();
        for (int i = 0; i < numberOfTests; i++) {
            int num = r.nextInt(n);
            assertEquals(set.contains(num), s1.solve(num));
        }
    }

    @Test
    void test2() {
        System.out.println("[Test 2]");
        System.out.println("Count of tests: " + numberOfTests);
        System.out.println("Count of numbers: " + n);
        FibonacciNumber.Solution2 s2 = new FibonacciNumber.Solution2();
        for (int i = 0; i < numberOfTests; i++) {
            int num = r.nextInt(n);
            assertEquals(set.contains(num), s2.solve(num));
        }
    }
}