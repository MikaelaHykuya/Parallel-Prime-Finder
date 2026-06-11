"""
prime_finder.py

Prime number detection logic used by worker processes.
Contains an optimized is_prime() function and a worker
function that processes a sub-range and sends results via Queue.
"""

import math


def is_prime(n: int) -> bool:
    """
    Determine if a number is prime.

    Optimizations:
      - Numbers < 2 are not prime.
      - 2 is the only even prime.
      - Check divisors only up to sqrt(n), skipping evens.
    """
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    # Only check odd divisors up to the square root
    limit = int(math.isqrt(n))
    for i in range(3, limit + 1, 2):
        if n % i == 0:
            return False
    return True


def find_primes_in_range(process_id: int, start: int, end: int, result_queue):
    """
    Worker function executed by each child process.

    Iterates over [start, end], collects primes, and puts
    the result tuple into the queue for the main process to collect.

    Args:
        process_id:   Unique identifier for this worker
        start:        Start of the range (inclusive)
        end:          End of the range (inclusive)
        result_queue: multiprocessing.Queue to send results back
    """
    primes = []
    for num in range(start, end + 1):
        if is_prime(num):
            primes.append(num)

    # Send results back to the main process
    result_queue.put((process_id, start, end, primes))
