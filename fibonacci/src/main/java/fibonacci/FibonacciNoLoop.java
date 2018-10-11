package fibonacci;

public class FibonacciNoLoop implements Fibonacci {
    public int get(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else return get(n - 1) + get(n - 2);
    }
}
