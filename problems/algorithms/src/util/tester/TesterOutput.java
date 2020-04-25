package util.tester;

import java.util.Arrays;
import java.util.List;

public class TesterOutput {
    private static final String TEXT_ACC = "[ACCEPTED]";
    private static String TESTER_S;
    private static String TESTER_SEP;
    private static int DEFAULT_LINE_LEN = 30;
    private static List<String> outputQueue;
    private final Class solutionClass;

    public TesterOutput(final Class solutionClass, List<String> outputQueue) {
        this.outputQueue = outputQueue;
        this.solutionClass = solutionClass;
        TESTER_S = createDefaultLongString('=');
        TESTER_SEP = createDefaultLongString('-');
    }

    private String createDefaultLongString(char c) {
        return createLongString(c, getLineLen());
    }

    private String createLongString(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private int getLineLen() {
        int DOT_JAVA_TEXT_LENGTH = 5;
        int classNameLen = calculateLineLenFromClassName(solutionClass) + DOT_JAVA_TEXT_LENGTH;
        return classNameLen > TEXT_ACC.length() ? classNameLen : DEFAULT_LINE_LEN;
    }

    private int calculateLineLenFromClassName(Class c) {
        String enclosingClass = c.getEnclosingClass() != null
                ? c.getEnclosingClass().getSimpleName()
                : c.getSimpleName();
        int extra = 5; // depends on the java run alias
        return extra + enclosingClass.length();
    }

    // todo - record to list ,then if all ok, don't print ok's
    public void printIntermediateOk(int i) {
        String NUM = String.valueOf(i + 1);
        String OK = "OK";
        String E = createLongString('.', getLineLen() - NUM.length() - OK.length());
        System.out.println(NUM + E + OK);
        System.out.println(TESTER_SEP);
    }

    public void printIntermediateNok(int i, final List<Object> results, final List<Object> expectations, final List<Object> orExpectations) {
        String NUM = String.valueOf(i + 1);
        String NOK = " NOK";
        System.out.println(NUM + '.' + NOK);
        System.out.print("got:      ");
        print(results.get(i));
        System.out.print("expected: ");
        print(expectations.get(i));
        if (!orExpectations.isEmpty()) {
            System.out.print("or: ");
            print(orExpectations.get(i));
        }
        System.out.println();
        System.out.println(TESTER_SEP);
    }

    private void print(Object o) {
        if (isArr(o)) {
            if (o.getClass().getSimpleName().equals("int[]")) {
                System.out.println(Arrays.toString((int[]) o));
            }
        } else {
            System.out.println(o);
        }
    }

    private boolean isArr(Object o) {
        return o.getClass().getSimpleName().contains("[]");
    }

    public void printOk() {
        String emptiness = createLongString('.', getLineLen() - TEXT_ACC.length());
        System.out.println(emptiness + TEXT_ACC);
    }

    public void printTime(final List<Double> execTimes) {
        String avgTimes = "[" + String.format("%.2f", getAvgExecTimes(execTimes)) + "]";
        String e2 = createLongString('.', getLineLen() - avgTimes.length());
        System.out.println(e2 + avgTimes);
    }

    private Double getAvgExecTimes(final List<Double> execTimes) {
        return execTimes.stream().mapToDouble(Double::valueOf).average().orElse(Double.NaN);
    }

    public void printMainSeparator() {
        System.out.println(TESTER_S);
    }
}
