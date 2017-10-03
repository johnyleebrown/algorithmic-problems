package Basics;

public class Factorial {
    public int f(int x) {
        if (x == 1) return 1;
        return x * f(x - 1);
    }
}
