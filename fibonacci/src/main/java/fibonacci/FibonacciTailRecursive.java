package fibonacci;

public class FibonacciTailRecursive implements Fibonacci {
    public int get(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return getR(n, 0, 1);
    }

    private int getR(int n, int a, int b) {
        if (n == 2) {
            return a + b;
        }
        return getR(n - 1, b, a + b);
    }
}
