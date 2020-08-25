package util.utils;

public class Timer {
    private long startTime, endTime;

    public Timer start() {
        startTime = System.nanoTime();
        return this;
    }

    public void printEnd() {
        System.out.println(String.format("%.2f", this.end().getTotal()));
    }

    public Timer end() {
        endTime = System.nanoTime();
        return this;
    }

    public double getTotal() {
        return (endTime - startTime) / 1e6;
    }

    public void printResult() {
        System.out.println("Total execution time is: " + TimeUtils.NanoSeconds.toMilli((endTime - startTime)) + " ms");
    }
}
