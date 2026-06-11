"""
main.py

Parallel Prime Number Finder using multiprocessing.

Splits the range 1-100000 across 4 worker processes to
demonstrate parallel computing concepts:
  - Workload decomposition
  - Concurrent execution via separate OS processes
  - Synchronisation via Queue / Process.join()
  - Result aggregation

Usage:
    python main.py
"""

import multiprocessing as mp
import time
from prime_finder import find_primes_in_range

# --- Configuration ---
MAX_NUMBER = 100000      # Upper bound of the search range
PROCESS_COUNT = 4        # Number of parallel worker processes


def main():
    print("=" * 44)
    print("  Parallel Prime Number Finder")
    print(f"  Range: 1 to {MAX_NUMBER}")
    print(f"  Processes: {PROCESS_COUNT}")
    print("=" * 44)

    # Divide the workload evenly
    range_size = MAX_NUMBER // PROCESS_COUNT

    # Shared queue to collect results from worker processes
    result_queue = mp.Queue()
    processes = []

    # --- Phase 1: Create worker processes ---
    for i in range(PROCESS_COUNT):
        process_id = i + 1
        start = (i * range_size) + 1
        end = MAX_NUMBER if i == PROCESS_COUNT - 1 else (i + 1) * range_size

        p = mp.Process(
            target=find_primes_in_range,
            args=(process_id, start, end, result_queue),
        )
        processes.append(p)

    # --- Phase 2: Start all processes ---
    start_time = time.time()

    for p in processes:
        p.start()

    # --- Phase 3: Wait for all processes to finish ---
    for p in processes:
        p.join()

    end_time = time.time()
    total_time_ms = (end_time - start_time) * 1000

    # --- Phase 4: Collect results from the queue ---
    results = []
    for _ in processes:
        results.append(result_queue.get())

    # Sort by process_id so output order is consistent
    results.sort(key=lambda r: r[0])

    # --- Phase 5: Display results ---
    print("\n" + "=" * 44)
    print("  RESULTS")
    print("=" * 44)

    total_primes = 0
    for process_id, start, end, primes in results:
        print(f"\nProcess ID     : {process_id}")
        print(f"Range          : {start} - {end}")
        print(f"Primes Found   : {len(primes)}")
        print("-" * 29)
        total_primes += len(primes)

    print(f"\nTotal Primes Found : {total_primes}")
    print(f"Total Execution    : {total_time_ms:.0f} ms ({total_time_ms / 1000:.3f} s)")

    print("\n" + "=" * 44)
    print("  PARALLEL COMPUTING DEMONSTRATION COMPLETE")
    print("=" * 44)


# Required guard for multiprocessing on Windows
if __name__ == "__main__":
    mp.freeze_support()
    main()
