package amazon.oa;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class B {
    public static class Solution {
        List<Integer> lengthEachScene(List<Character> inputList) {
            List<Integer> lengths = new ArrayList<>();

            int[] counts = new int[26];
            // count each char in string
            for (char c : inputList) {
                counts[c - 'a']++;
            }

            int leftPointer = 0;
            int rightPointer = 0;
            int counter = 0;
            Set<Character> charSet = new HashSet<>();
            while (rightPointer < inputList.size()) {
                char c = inputList.get(rightPointer);

                // found new char in current window, so increase counter
                if (!charSet.contains(c)) {
                    charSet.add(c);
                    counter++;
                }
                counts[c - 'a']--;
                rightPointer++;

                // exhausted the c char and remove char c from set
                if (counts[c - 'a'] == 0) {
                    counter--;
                    charSet.remove(c);
                }

                // current window is a partition
                if (counter == 0) {
                    lengths.add(rightPointer - leftPointer);
                    // reset i for next window
                    leftPointer = rightPointer;
                }
            }

            return lengths;
        }


    }
}