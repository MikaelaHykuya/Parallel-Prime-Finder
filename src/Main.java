/**
 * Main.java
 * 
 * Entry point for Parallel Prime Number Finder.
 * Splits the range 1-100000 into 4 threads using ExecutorService,
 * measures total execution time, and displays per-thread results.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final int MAX_NUMBER  = 100000;
    private static final int THREAD_COUNT = 4;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("============================================");
        System.out.println("  Parallel Prime Number Finder");
        System.out.println("  Range       : 1 to " + MAX_NUMBER);
        System.out.println("  Threads     : " + THREAD_COUNT);
        System.out.println("============================================\n");

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        int rangeSize = MAX_NUMBER / THREAD_COUNT;

        List<Future<?>> futures = new ArrayList<>();
        List<PrimeFinder> tasks = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            int threadId   = i + 1;
            int rangeStart = (i * rangeSize) + 1;
            int rangeEnd   = (i == THREAD_COUNT - 1) ? MAX_NUMBER : (i + 1) * rangeSize;

            PrimeFinder task = new PrimeFinder(threadId, rangeStart, rangeEnd);
            tasks.add(task);
            futures.add(executor.submit(task));
        }

        for (Future<?> f : futures) {
            f.get();
        }

        executor.shutdown();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("\n============================================");
        System.out.println("  RESULTS");
        System.out.println("============================================\n");

        int totalPrimes = 0;
        for (PrimeFinder t : tasks) {
            System.out.println("Thread ID      : " + t.getThreadId());
            System.out.println("Range          : " + t.getStart() + " - " + t.getEnd());
            System.out.println("Primes Found   : " + t.getPrimeCount());
            System.out.println("-----------------------------");
            totalPrimes += t.getPrimeCount();
        }

        System.out.println("\nTotal Primes Found : " + totalPrimes);
        System.out.println("Total Execution    : " + totalTime + " ms (" + (totalTime / 1000.0) + " s)");

        System.out.println("\n============================================");
        System.out.println("  PARALLEL COMPUTING DEMONSTRATION COMPLETE");
        System.out.println("============================================");
    }
}
