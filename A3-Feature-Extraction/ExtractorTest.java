import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;


public class ExtractorTest {


   // 2 lines no garbage
   @Test public void fastTest() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(1, 2),
         new Point(2, 3),
         new Point(3, 4),
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(2, numLines);
   }
   
   // 1 8-point collinear line
   @Test public void fastTest2() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         new Point(7, 7),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, fastLines.first());
   }
   
   // all garbage
   @Test public void fastTest3() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(2, 2),
         new Point(5, 3),
         new Point(8, 4),
         new Point(7, 0), 
         new Point(20, 1),
         new Point(18, 2),
         new Point(13, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
    // empty
   @Test public void fastTest4() {
      Point[] p = new Point[0];
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      int numLines = fastLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
   // intersecting lines
   @Test public void fastTest5() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         //
         new Point(0, 1), 
         new Point(1, 1),
         new Point(2, 1),
         new Point(3, 1),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, fastLines.first());
   }
   
      /*************************************************************************/
/****************************getLinesBrute Tests******************************/
/*************************************************************************/
      // 2 lines no garbage
   @Test public void bruteTest() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(1, 2),
         new Point(2, 3),
         new Point(3, 4),
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(2, numLines);
   }
   

   // all garbage
   @Test public void bruteTest3() {
      Point[] p = new Point[] {
         // 
         new Point(0, 1), 
         new Point(2, 2),
         new Point(5, 3),
         new Point(8, 4),
         new Point(7, 0), 
         new Point(20, 1),
         new Point(18, 2),
         new Point(13, 3),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
    // empty
   @Test public void bruteTest4() {
      Point[] p = new Point[0];
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      int numLines = bruteLines.size();
   
      Assert.assertEquals(0, numLines);
   }
   
   // intersecting lines
   @Test public void bruteTest5() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         new Point(1, 1),
         new Point(2, 2),
         new Point(3, 3),
         //
         new Point(0, 1), 
         new Point(1, 1),
         new Point(2, 1),
         new Point(3, 1),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> bruteLines = test.getLinesBrute();
      
      Line testL = new Line(Arrays.asList(p));
      
   
      Assert.assertEquals(testL, bruteLines.first());
   }
   
      /*************************************************************************/
/****************************MORE getLinesFast Tests******************************/
/*************************************************************************/

         
    // 1 8-point collinear line - Garbage in back and front
   @Test public void fastTest8() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         // new Point(1, 1),
         // new Point(2, 2),
         new Point(0, 1),
         new Point(0, 2),
         new Point(1, 0),
         new Point(2, 0),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         new Point(7, 7),
         new Point(8, 8),
         new Point(9, 9),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
  // 1 4-point collinear line - Garbage in front
   @Test public void fastTest9() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         //
         new Point(0, 1),
         new Point(0, 2),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
     // 1 4-point collinear line - Garbage in front
   @Test public void fastTest17() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         //
         new Point(0, 1),
         new Point(0, 2),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
         
    // 1 4-point collinear line - Garbage in back and front
   @Test public void fastTest10() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         // new Point(1, 1),
         // new Point(2, 2),
         new Point(0, 1),
         new Point(0, 2),
         new Point(1, 0),
         new Point(2, 0),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
    // 1 3-point collinear line - Garbage in front
   @Test public void fastTest11() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         //
         new Point(0, 2),
         new Point(3, 3),
         new Point(4, 4), 
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(0, fastLines.size());
   }
   
     // 1 3-point collinear line - Garbage in front
   @Test public void fastTest12() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         //
         new Point(0, 2),
         new Point(3, 3),
         new Point(4, 4), 
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(0, fastLines.size());
   }
   
         
    // 1 3-point collinear line - Garbage in back and front
   @Test public void fastTest13() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         // new Point(1, 1),
         // new Point(2, 2),
         new Point(0, 2),
         new Point(2, 0),
         new Point(3, 3),
         new Point(4, 4), 
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(0, fastLines.size());
   }

    // 1 5-point collinear line - Garbage in back
   @Test public void fastTest14() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         // new Point(1, 1),
         // new Point(2, 2),
         new Point(1, 0),
         new Point(2, 0),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
  // 1 5-point collinear line - Garbage in front
   @Test public void fastTest15() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         //
         new Point(0, 1),
         new Point(0, 2),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
   
         
    // 1 5-point collinear line - Garbage in back and front
   @Test public void fastTest16() {
      Point[] p = new Point[] {
         //
         new Point(0, 0), 
         // new Point(1, 1),
         // new Point(2, 2),
         new Point(0, 1),
         new Point(0, 2),
         new Point(1, 0),
         new Point(2, 0),
         new Point(3, 3),
         new Point(4, 4), 
         new Point(5, 5),
         new Point(6, 6),
         };
      Extractor test = new Extractor(Arrays.asList(p));
      SortedSet<Line> fastLines = test.getLinesFast();
      
   
      Assert.assertEquals(1, fastLines.size());
   }
}
