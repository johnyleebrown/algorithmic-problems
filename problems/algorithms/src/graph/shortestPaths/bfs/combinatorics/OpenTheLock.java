package graph.shortestPaths.bfs.combinatorics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 752
 */
public class OpenTheLock
{
    /**
     The idea is to generate all possible versions of the current string
     And each time when generating increment counter
     The time when we will reach the target we will return counter
     */
    class Solution {
        public int openLock(String[] deadends, String target) {

            String base = "0000"; // startin point that we will use to generate variants

            Set<String> deadEndsSet = fillSetWithDeadEnds(deadends);  // set to check if the processed string is a dead end

            Set<String> seen = new HashSet<>(); // to check if we have fone that way before for optimization
            seen.add(base); // adding the first possible version, that is base of course

            if (deadEndsSet.contains(base)) return -1; // edge case

            Queue<String> queueToProcess = new LinkedList<>();
            queueToProcess.add(base); // adding to the end of the queue

            int stepsNumber = 0;
            while (!queueToProcess.isEmpty())
            {
                int size = queueToProcess.size(); // remove the first one
                for (int k = 0; k < size; k++)
                {
                    String version = queueToProcess.poll();
                    if (version.equals(target)) return stepsNumber;
                    generateAndAdd(version, seen, deadEndsSet, queueToProcess);
                }
                stepsNumber++; // increment each round
            }

            return -1;
        }

        private void generateAndAdd(String str, Set<String> seen, Set<String> deadEndsSet, Queue<String> queueToProcess)
        {
            char[] charr = str.toCharArray();
            for (int i = 0; i < 4; i++)
            {
                StringBuilder sb = new StringBuilder(str);
                processStrings(sb.replace(i, i + 1, generateIncremented(charr[i])).toString(),
                        sb.replace(i, i + 1, generateDecremented(charr[i])).toString(),
                        seen, deadEndsSet, queueToProcess);
            }
        }

        private void processStrings(String s1, String s2, Set<String> seen, Set<String> deadEndsSet, Queue<String> queueToProcess)
        {
            processGeneratedString(s1, seen, deadEndsSet, queueToProcess);
            processGeneratedString(s2, seen, deadEndsSet, queueToProcess);
        }

        private void processGeneratedString(String generatedStr, Set<String> seen,
                                            Set<String> deadEndsSet, Queue<String> queueToProcess)
        {
            if (isStringEligable(generatedStr, seen, deadEndsSet))
            {
                seen.add(generatedStr);
                queueToProcess.add(generatedStr);
            }
        }

        private boolean isStringEligable(String generatedStr, Set<String> seen, Set<String> deadEndsSet)
        {
            return !deadEndsSet.contains(generatedStr) && !seen.contains(generatedStr);
        }

        private String generateIncremented(char ch)
        {
            if (ch == '9') return "0";
            return String.valueOf(Character.getNumericValue(ch) + 1);
        }

        private String generateDecremented(char ch)
        {
            if (ch == '0') return "9";
            return String.valueOf(Character.getNumericValue(ch) - 1);
        }

        private Set<String> fillSetWithDeadEnds(String[] deadends)
        {
            Set<String> deadEndsSet = new HashSet<>();
            Arrays.stream(deadends).forEach(deadEndsSet::add);
            return deadEndsSet;
        }
    }
}
