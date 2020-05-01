package util.utils;

import util.utils.reader.InputReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {
	public static String replaceWith(String source, final String[][] strs) {
		for (String[] str : strs) source = source.replaceAll(str[0], str[1]);
		return source;
	}

	public static void main(String[] args) throws IOException {

	}

	private void replaceWordsInFile() throws IOException {
		String source = new String(Files.readAllBytes(Paths.get("test.txt")));
		System.out.println((replaceWith(source, new String[][]{{Pattern.quote("["), "{"}, {Pattern.quote("]"), "}"}})));
	}

	private void readWords() {
		int n = 50;
		InputReader inputReader = new InputReader(System.in);
		String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = inputReader.nextLine();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("\"" + lines[i] + "\"");
            if (i != n - 1) System.out.print(",");
        }
        System.out.println();
    }

    public static String getMaxLen3Strings(String a, String b, String c) {
        return getMaxLen2Strings(a, getMaxLen2Strings(b, c));
    }

    public static String getMaxLen2Strings(String a, String b) {
        if (a.length() > b.length())
            return a;
        else
            return b;
    }

    public static boolean containsSubsequence(String src, String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String s : a) sb.append("(.*)").append(s);
        return src.matches(sb.toString());
    }

    /**
     * [97,100],[51,65]
     */
    public static int[][] stringToAr(String s) {
        String[] nums = s.split("[^0-9]");
        int i = 0;
        List<int[]> res = new ArrayList<>();
        int[] local = new int[2];
        for (String num : nums) {
            if (!num.trim().isEmpty()) {
                local[i++] = Integer.parseInt(num);
                if (i == 2) {
                    res.add(local);
                    i = 0;
                    local = new int[2];
                }
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int j = 0; j < res.size(); j++) {
            ans[j] = res.get(j);
        }
        return ans;
    }
}
