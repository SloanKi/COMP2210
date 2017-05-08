import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;


public class LineTest {

      /* Temporary Tests */ 
      /* Collinear Test 
   @Test public void collinearTest() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,4);
      Line line = new Line();
      line.add(a);
      line.add(b);
      boolean actual = line.isCollinear(c);
      assertEquals(true, actual);
   }
   
   @Test public void collinearTest2() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,3);
      Line line = new Line();
      line.add(a);
      line.add(b);
      boolean actual = line.isCollinear(c);
      assertEquals(false, actual);
   }
   
   @Test public void collinearTest3() {
      Point a = new Point(0,0);
      Point b = new Point(5,5);
      Point c = new Point(4,4);
      Line line = new Line();
      line.add(b);
      line.add(c);
      boolean actual = line.isCollinear(a);
      assertEquals(true, actual);
   } */
   
/*******************************************************/
/*********************ADD TESTS*************************/
/*******************************************************/

   //First test is an empty line
   @Test public void addTest1() {
      Point a = new Point(0,0);
      Line line = new Line();
      assertEquals(true, line.add(a)); //Checks if true/false works
      assertEquals("(0, 0)", line.toString()); //Checks if actually added
   }
   
   //Tests when it only has one item in line
   @Test public void addTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Line line = new Line();
      line.add(a);
      assertEquals(true, line.add(b)); //Checks if true/false works
      assertEquals("(0, 0) -> (1, 1)", line.toString()); //Checks if actually added
   }
   
   //Normal test, more than one item, are collinear.
   @Test public void addTest3() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(true, line.add(c)); //Checks if true/false works
      //Checks if actually added
      assertEquals("(0, 0) -> (1, 1) -> (2, 2)", line.toString()); 
   }
   
   //Tests if aren't collinear
   @Test public void addTest4() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,3);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(false, line.add(c)); //Checks if true/false works
      //Checks to be sure it did not add
      assertEquals("(0, 0) -> (1, 1)", line.toString()); 
   }
   
   //Tests if already in line
   @Test public void addTest5() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(1,1);
      Line line = new Line();
      line.add(a);
      line.add(b);
      assertEquals(false, line.add(c)); //Checks if true/false works
      //Checks to be sure it did not add
      assertEquals("(0, 0) -> (1, 1)", line.toString()); 
   }
   
/*******************************************************/
/*********************FIRST TESTS*************************/
/*******************************************************/
   
   //Tests if null if empty
   @Test public void firstTest1() {
      Line line = new Line();
      assertEquals(null, line.first());
   }
   
   //Tests it pulls first
   @Test public void firstTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(a, line.first());
   }
   
/*******************************************************/
/*********************LAST TESTS*************************/
/*******************************************************/
   
   //Tests if null if empty
   @Test public void lastTest1() {
      Line line = new Line();
      assertEquals(null, line.last());
   }
   
   //Tests it pulls first
   @Test public void lastTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(c, line.last());
   }   
   
/*******************************************************/
/*********************LENGTH TESTS*************************/
/*******************************************************/ 

   //Tests if 0 if empty
   @Test public void lengthTest1() {
      Line line = new Line();
      assertEquals(0, line.length());
   }
   
   //Tests it pulls first
   @Test public void lengthTest2() {
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Line line = new Line();
      line.add(a);
      line.add(b);
      line.add(c);
      assertEquals(3, line.length());
   }  
   
/*******************************************************/
/*********************COMPARETO TESTS*************************/
/*******************************************************/  

   //First < First
   @Test public void compareTest1() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Point d = new Point(3,3);
      line1.add(a);
      line1.add(b);
      line2.add(c);
      line2.add(d);
      assertEquals(-1, line1.compareTo(line2));
   }  
   
   //First > First
   @Test public void compareTest2() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      Point d = new Point(3,3);
      line1.add(a);
      line1.add(b);
      line2.add(c);
      line2.add(d);
      assertEquals(1, line2.compareTo(line1));
   }  
   
   //First == First, Last < Last
   @Test public void compareTest3() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      line1.add(a);
      line1.add(b);
      line2.add(a);
      line2.add(c);
      assertEquals(-1, line1.compareTo(line2));
   }  
   
   //First == First, Last > Last
   @Test public void compareTest4() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      Point b = new Point(1,1);
      Point c = new Point(2,2);
      line1.add(a);
      line1.add(b);
      line2.add(a);
      line2.add(c);
      assertEquals(1, line2.compareTo(line1));
   }  
   
   //Equal to eachother
   @Test public void compareTest5() {
      Line line1 = new Line();
      Line line2 = new Line();
      Point a = new Point(0,0);
      line1.add(a);
      line2.add(a);
      assertEquals(0, line1.compareTo(line2));
   }  
   
}
