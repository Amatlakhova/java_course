package ru.stqa.jc.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {

    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 3);
    Assert.assertEquals(p1.distance(p2), 2.0);

    p1 = new Point(2, 6);
    p2 = new Point(2, 3);
    Assert.assertEquals(p1.distance(p2), 3.0);

    p1 = new Point(-4, -3);
    p2 = new Point(2, -3);
    Assert.assertEquals(p1.distance(p2), 6.0);

    p1 = new Point(1.5, 7);
    p2 = new Point(1.5, 2.5);
    Assert.assertEquals(p1.distance(p2), 4.5);

    p1 = new Point(0, 0);
    p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);

  }
}
