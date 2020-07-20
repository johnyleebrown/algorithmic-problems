package yandex.contest.MinCostToPowerShuttleStations;

import yandex.contest.FileReader;
import yandex.contest.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Reader r = new FileReader(new FileInputStream("problems/interviews/src/yandex/contest/MinCostToPowerShuttleStations/test.txt"));
        List<String> inputs = r.readLines();

        Reader rExpectations = new FileReader(new FileInputStream("problems/interviews/src/yandex/contest/MinCostToPowerShuttleStations/res.txt"));
        List<String> expectations = rExpectations.readLines();

        for (int i = 0; i < inputs.size(); i += 5) {
            //
            System.out.println("==");
            //
            System.out.println("*****");
        }
    }

    public void solve(int numStations, int[] costsGenerators, int numCables, List<int[]> cableCosts) {
        return;
    }
}
