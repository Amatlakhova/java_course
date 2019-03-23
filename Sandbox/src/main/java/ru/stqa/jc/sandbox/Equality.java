package ru.stqa.jc.sandbox;

public class Equality {
  public static void main(String[] args) {
    String s1 = "chrome 2.0";
    String s2 = "chrome " + Math.sqrt(4.0);

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}
