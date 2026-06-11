# Parallel Prime Number Finder using Multithreading

[![Run Parallel Prime Finder](https://github.com/MikaelaHykuya/Parallel-Prime-Finder/actions/workflows/run.yml/badge.svg)](https://github.com/MikaelaHykuya/Parallel-Prime-Finder/actions/workflows/run.yml)
[![GitHub Pages](https://img.shields.io/badge/GitHub-Pages-blue)](https://mikaelahykuya.github.io/Parallel-Prime-Finder/)

A Java parallel computing project that finds all prime numbers from **1 to 100,000** by splitting the workload across **4 threads** using `ExecutorService`.

🌐 **Live Demo:** https://mikaelahykuya.github.io/Parallel-Prime-Finder/

## Project Description

The program divides the range 1–100,000 into 4 equal sub-ranges, assigns each to a separate thread, and measures total execution time. Each thread reports its ID, the range it processed, and how many primes it found.

## Objectives

- Demonstrate **multithreading** in Java using `ExecutorService`.
- Practice **workload decomposition** — splitting a large task into independent chunks.
- Measure **execution time** to observe speedup from parallel execution.
- Produce a well-documented project suitable for a Parallel Computing course.

## Folder Structure

```
Parallel-Prime-Finder/
├── src/
│   ├── Main.java           # Entry point — ExecutorService, timing
│   └── PrimeFinder.java    # Runnable — prime detection logic
├── docs/
│   ├── index.html          # Interactive website (Web Workers)
│   └── documentation.md    # Full written documentation
├── .github/workflows/
│   └── run.yml             # GitHub Actions — compile & run
├── .gitignore
└── README.md
```

## How to Run

### Prerequisites
- **JDK 8+** installed

### Compile & Run
```bash
javac -d out src/PrimeFinder.java src/Main.java
java -cp out Main
```

### Expected Output
```
============================================
  Parallel Prime Number Finder
  Range       : 1 to 100000
  Threads     : 4
============================================

Thread 1 processing range: 1 to 25000
Thread 2 processing range: 25001 to 50000
Thread 3 processing range: 50001 to 75000
Thread 4 processing range: 75001 to 100000
Thread 1 found 2762 primes.
Thread 3 found 2260 primes.
Thread 2 found 2371 primes.
Thread 4 found 2199 primes.

============================================
  RESULTS
============================================

Thread ID      : 1
Range          : 1 - 25000
Primes Found   : 2762
-----------------------------
Thread ID      : 2
Range          : 25001 - 50000
Primes Found   : 2371
-----------------------------
Thread ID      : 3
Range          : 50001 - 75000
Primes Found   : 2260
-----------------------------
Thread ID      : 4
Range          : 75001 - 100000
Primes Found   : 2199
-----------------------------

Total Primes Found : 9592
Total Execution    : XX ms (X.XX s)

============================================
  PARALLEL COMPUTING DEMONSTRATION COMPLETE
============================================
```

## GitHub Pages

The interactive website uses **4 Web Workers** to run the prime finder **live in your browser** — showing real-time progress bars, per-worker prime counts, and total execution time.

👉 https://mikaelahykuya.github.io/Parallel-Prime-Finder/

## License

Created for educational purposes — Parallel Computing course.
