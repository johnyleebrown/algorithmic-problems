package string.other;

import java.util.AbstractList;
import java.util.List;

/**
 * 412
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

    class Solution {
        private int size = 0;

        private final List<String> fizzbuzzList = new AbstractList<String>() {
            @Override
            public String get(int index) {
                final int i = index + 1;
                return i % 15 == 0 ? "FizzBuzz"
                        : i % 5 == 0 ? "Buzz"
                        : i % 3 == 0 ? "Fizz"
                        : String.valueOf(i);
            }

            @Override
            public int size() {
                return Solution.this.size;
            }
        };

        public List<String> fizzBuzz(int n){
            this.size = n;
            return fizzbuzzList;
        }
    }
}
