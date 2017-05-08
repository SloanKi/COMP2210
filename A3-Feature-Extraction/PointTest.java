import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PointTest {

/*************************************************************************/
/****************************COMPARETO TESTS******************************/
/*************************************************************************/
   @Test public void pointCompareTest1() {
      Point a = new Point (0, 0);
      Point b = new Point (0, 1);
      assertEquals(-1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest2() {
      Point a = new Point (1, 0);
      Point b = new Point (0, 0);
      assertEquals(1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest3() {
      Point a = new Point (0, 0);
      Point b = new Point (1, 0);
      assertEquals(-1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest4() {
      Point a = new Point (0, 1);
      Point b = new Point (0, 0);
      assertEquals(1, a.compareTo(b));
   }
   
   @Test public void pointCompareTest5() {
      Point a = new Point (0, 0);
      Point b = new Point (0, 0);
      assertEquals(0, a.compareTo(b));
   }
   
/*************************************************************************/
/****************************SLOPETO TESTS******************************/
/*************************************************************************/
   @Test public void slopeToTest1() {
      Point a = new Point(1, 1);
      Point b = new Point(2, 2);
      double expected = 1;
      double actual = a.slopeTo(b);
      assertEquals(expected, actual, 0);
   }
    
    // slopeTo test
   @Test public void slopeToTest2() {
      Point a = new Point(2, 3);
      Point b = new Point(5, 4);
      double expected = 0.33;
      double actual = a.slopeTo(b);
      assertEquals(expected, actual, .1);
   }
    
    // slopeTo test horizontal line
   @Test public void slopeToTest3() {
      Point a = new Point(2, 3);
      Point b = new Point(2, 4);
      double expected = Double.POSITIVE_INFINITY;
      double actual = a.slopeTo(b);
      assertEquals(expected, actual, .1);
   }
    
   //slopeTo test vertical line
   @Test public void slopeToTest4() {
      Point a = new Point(2, 3);
      Point b = new Point(3, 3);
      double expected = 0.0;
      double actual = a.slopeTo(b);
      assertEquals(expected, actual, .1);
   }
    
    //slopeTo test same points
   @Test public void slopeToTest5() {
      Point a = new Point(2, 3);
      Point b = new Point(2, 3);
      double expected = Double.NEGATIVE_INFINITY;
      double actual = a.slopeTo(b);
      assertEquals(expected, actual, .1);
   }
/*************************************************************************/
/****************************COMPAREPOINTSBYSLOPE TESTS*******************/
/*************************************************************************/      
   @Test public void compareSlopeTest1() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(1, actual);
   }
   
   @Test public void compareSlopeTest2() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      int actual = a.slopeOrder.compare(c, b);
      assertEquals(-1, actual);
   }
   
   @Test public void compareSlopeTest3() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(5,5);
      int actual = a.slopeOrder.compare(b, c);
      assertEquals(0, actual);
   }
}
