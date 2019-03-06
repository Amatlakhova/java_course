package ru.stqa.jc.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 5);
    System.out.println("Distance between points = " + distance(p1, p2));

    Point p3 = new Point(6, 9);
    Point p4 = new Point(1.4, 2.8);
    System.out.println("Distance between points = " + p3.distance(p4));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }


}