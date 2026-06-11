/**
 * PrimeFinder.java
 * 
 * A Runnable task that finds all prime numbers within a given range.
 * Each instance runs on a separate thread for parallel execution.
 */

import java.util.ArrayList;
import java.util.List;

public class PrimeFinder implements Runnable {

    private final int start;
    private final int end;
    private final List<Integer> primes;
    private final int threadId;

    public PrimeFinder(int threadId, int start, int end) {
        this.threadId = threadId;
        this.start = start;
        this.end = end;
        this.primes = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadId + " processing range: " + start + " to " + end);

        for (int num = start; num <= end; num++) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }

        System.out.println("Thread " + threadId + " found " + primes.size() + " primes.");
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int getThreadId()       { return threadId; }
    public int getStart()          { return start; }
    public int getEnd()            { return end; }
    public int getPrimeCount()     { return primes.size(); }
    public List<Integer> getPrimes() { return primes; }
}
