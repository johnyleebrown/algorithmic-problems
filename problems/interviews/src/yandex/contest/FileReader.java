package yandex.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileReader implements Reader {

    private final InputStream inputStream;

    public FileReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public List<String> readLines() throws IOException {
        List<String> res = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String curLine = reader.readLine();
        while (curLine != null) {
            res.add(curLine);
            curLine = reader.readLine();
        }
        return res;
    }
}
