package fibonacci;

public class FibonacciMain {
    private static final int NTH = 30;
    public static void main(String[] argv) {
//        Fibonacci fib = new FibonacciSimple();
//        Fibonacci fib = new FibonacciNoLoop();
        Fibonacci fib = new FibonacciTailRecursive();

        int nth = fib.get(NTH);
        System.out.println("fib(" + NTH + ")=" + nth);
    }
}
