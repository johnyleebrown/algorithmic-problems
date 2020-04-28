package util.utility.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
    public BufferedReader r;
    public StringTokenizer t;

    public InputReader(InputStream s) {
        r = new BufferedReader(new InputStreamReader(s), 32768);
        t = null;
    }

    public int[] nextIntAr(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = nextInt();
        return a;
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public String next() {
        while (t == null || !t.hasMoreTokens()) {
            try {
                t = new StringTokenizer(r.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return t.nextToken();
    }

    public Long[] nextLongAr(int n) {
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) a[i] = nextLong();
        return a;
    }

    public Long nextLong() {
        return Long.parseLong(next());
    }

    public Double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        try {
            return r.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}