# Parallel Prime Number Finder using Multiprocessing

[![Run Parallel Prime Finder](https://github.com/MikaelaHykuya/Parallel-Prime-Finder/actions/workflows/run.yml/badge.svg)](https://github.com/MikaelaHykuya/Parallel-Prime-Finder/actions/workflows/run.yml)

A Python-based parallel computing project that demonstrates multiprocessing by splitting the task of finding prime numbers from 1 to 100,000 across 4 worker processes.  
**See it run live** → [GitHub Actions Tab](https://github.com/MikaelaHykuya/Parallel-Prime-Finder/actions/workflows/run.yml)

## Project Description

This program finds all prime numbers in the range 1–100,000 by dividing the workload among 4 OS-level processes using Python's `multiprocessing` module. Each worker handles a distinct sub-range, and results are collected via a shared `Queue`. Execution time is measured to demonstrate the speedup from parallel processing.

## Objectives

- Implement **multiprocessing** in Python using `Process`, `Queue`, and `join()`.
- Practice **workload decomposition** — splitting a large task into independent chunks.
- Measure **execution time** to observe parallel speedup.
- Learn **inter-process communication** via `Queue`.
- Produce a well-documented project suitable for a university Parallel Computing course.

## Parallel Computing Concept

Parallel computing breaks a large problem into smaller independent tasks that run simultaneously on multiple CPU cores.

| Concept | Implementation |
|---------|---------------|
| **Decomposition** | Range 1–100,000 split into 4 equal sub-ranges |
| **Assignment** | Each sub-range → one `Process` |
| **Concurrency** | Processes execute in parallel on separate cores |
| **Synchronisation** | `Process.join()` blocks until all workers finish |
| **Communication** | `Queue` transfers results from workers to main |
| **Aggregation** | Results are sorted by process ID and displayed |

> **Note:** Python's Global Interpreter Lock (GIL) limits `threading` for CPU-bound tasks, so this project uses `multiprocessing` (separate OS processes) for true parallelism.

## Program Workflow

```
 ┌─────────────────────────────────────────────┐
 │                  main.py                    │
 │  Spawn 4 Processes, each with a sub-range   │
 └─────┬─────────┬─────────┬─────────┬─────────┘
       │         │         │         │
   Process 1  Process 2  Process 3  Process 4
   (1-25000)  (25001-    (50001-    (75001-
               50000)     75000)     100000)
       │         │         │         │
       └─────────┴─────────┴─────────┘
                    │
               ┌────┴────┐
               │  join() │  ← synchronisation
               └────┬────┘
                    │
               ┌────┴────┐
               │ Queue   │
               │ collect │
               └────┬────┘
                    │
               ┌────┴────┐
               │ Display │
               │ Results │
               └─────────┘
```

1. **main.py** divides 1–100,000 into 4 equal parts.
2. 4 `Process` objects are created, each running `find_primes_in_range()`.
3. All processes start simultaneously — the OS schedules them across cores.
4. **main.py** calls `.join()` on each process — this is the synchronisation barrier.
5. Results are pulled from the shared `Queue`, sorted, and displayed.
6. Total execution time is printed.

## Folder Structure

```
Parallel-Prime-Finder/
│
├── src/
│   ├── main.py           # Entry point – creates processes, measures time
│   └── prime_finder.py   # is_prime() + worker function
│
├── docs/
│   └── index.md          # GitHub Pages documentation
│
├── screenshots/          # Place your screenshots here
│
├── .gitignore
├── requirements.txt
└── README.md
```

## How to Run

### Prerequisites

- **Python 3.8+** installed on your system.
- No external packages required (stdlib only).

### Execution

```bash
cd Parallel-Prime-Finder
python src/main.py
```

## Expected Output

```
============================================
  Parallel Prime Number Finder
  Range: 1 to 100000
  Processes: 4
============================================

============================================
  RESULTS
============================================

Process ID     : 1
Range          : 1 - 25000
Primes Found   : 2762
-----------------------------

Process ID     : 2
Range          : 25001 - 50000
Primes Found   : 2371
-----------------------------

Process ID     : 3
Range          : 50001 - 75000
Primes Found   : 2260
-----------------------------

Process ID     : 4
Range          : 75001 - 100000
Primes Found   : 2199
-----------------------------

Total Primes Found : 9592
Total Execution    : XX ms (X.XX s)

============================================
  PARALLEL COMPUTING DEMONSTRATION COMPLETE
============================================
```

> **Note:** Execution time varies by CPU, core count, and system load.

## Screenshots

| Step | Screenshot |
|------|-----------|
| Execution | `screenshots/execution.png` |

## License

Created for educational purposes — Parallel Computing course.
