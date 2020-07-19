package yandex.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CommandLineReader implements Reader {
    public List<String> readLines() throws IOException {
        List<String> res = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String curLine = reader.readLine();
        while (curLine != null) {
            res.add(curLine);
            curLine = reader.readLine();
        }
        return res;
    }
}
