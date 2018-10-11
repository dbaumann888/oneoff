package fibonacci;

public class FibonacciSimple implements Fibonacci {
    public int get(int n) {
        int prev = 0, current = 1;
        if (n == 0) {
            return prev;
        }
        for (int i = 1; i < n; ++i) {
            int newCurrent = prev + current;
            prev = current;
            current = newCurrent;
        }
        return current;
    }
}
