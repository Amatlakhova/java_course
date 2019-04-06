package ru.stqa.jc.sandbox;

public class Primes {

  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i += 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeWhile(int n) {
    int i = 2;
    while (1 < n && n % i != 0) {
      i++;
    }
    return i == n;
  }

  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i += 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(long n) {
    int m = (int) Math.sqrt(n);
    for (long i = 2; i < n / 2; i += 1) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
