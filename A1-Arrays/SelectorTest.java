import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


public class SelectorTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

/*************************TESTS FOR MIN***********************************/
   /**
   * Tests for invalid cases, a = null
   */
   @Test (expected = IllegalArgumentException.class)
    public void minIllegalTest1() {
      int[] a = null;
      Selector.min(a);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void minIllegalTest2() {
      int[] a = new int[0];
      Selector.min(a);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void minNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(2, Selector.min(a));
   }
   
   @Test public void minNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(1, Selector.min(a));
   }
   
   @Test public void minNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(4, Selector.min(a));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void minDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      Assert.assertEquals(2, Selector.min(a));
   }
   
   /**
   * Tests Boundaries
   */
   @Test public void minBoundary1(){
      int[] a = {1};
      Assert.assertEquals(1, Selector.min(a));
   }
   
   @Test public void minBoundary2() {
      int[] a = {2, 1};
      Assert.assertEquals(1, Selector.min(a));
   }
   
   @Test public void minBoundary3() {
      int[] a = {1, 2};
      Assert.assertEquals(1, Selector.min(a));
   }
   
/*************************TESTS FOR MAX***********************************/
   /**
   * Tests for invalid cases, a = null
   */
   @Test (expected = IllegalArgumentException.class)
    public void maxIllegalTest1() {
      int[] a = null;
      Selector.max(a);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void maxIllegalTest2() {
      int[] a = new int[0];
      Selector.max(a);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void maxNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      Assert.assertEquals(8, Selector.max(a));
   }
   
   @Test public void maxNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      Assert.assertEquals(9, Selector.max(a));
   }
   
   @Test public void maxNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      Assert.assertEquals(8, Selector.max(a));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void maxDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      Assert.assertEquals(8, Selector.max(a));
   }
   
   /**
   * Tests Boundaries
   */
   @Test public void maxBoundary1(){
      int[] a = {1};
      Assert.assertEquals(1, Selector.max(a));
   }
   
   @Test public void maxBoundary2() {
      int[] a = {2, 1};
      Assert.assertEquals(2, Selector.max(a));
   }
   
   @Test public void maxBoundary3() {
      int[] a = {1, 2};
      Assert.assertEquals(2, Selector.max(a));
   }
   
/*************************TESTS FOR KMIN***********************************/
   /**
   * Tests invalid Cases, a = null
   */
   @Test (expected = IllegalArgumentException.class)
    public void kminIllegalTest1() {
      int[] a = null;
      Selector.kmin(a, 3);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void kminIllegalTest2() {
      int[] a = new int[0];
      Selector.kmin(a, 3);
   }
   
   /**
   * Tests for invalid cases, k < 1
   */
   @Test (expected = IllegalArgumentException.class)
   public void kminIllegalTest3() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 0;
      Selector.kmin(a, k);
   }
   
   /**
   * Tests for invalid cases, k > a.length
   */
   @Test (expected = IllegalArgumentException.class)
   public void kminIllegalTest4() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 6;
      Selector.kmin(a, k);
   }
   
   /**
   * Tests for invalid cases, k > distinct values
   */
   @Test (expected = IllegalArgumentException.class)
   public void kminIllegalTest5() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int k = 6;
      Selector.kmin(a, k);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void kminNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 1;
      Assert.assertEquals(2, Selector.kmin(a, k));
   }
   
   @Test public void kminNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      int k = 3;
      Assert.assertEquals(5, Selector.kmin(a, k));
   }
   
   @Test public void kminNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      int k = 5;
      Assert.assertEquals(8, Selector.kmin(a, k));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void kminDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int k = 3;
      Assert.assertEquals(4, Selector.kmin(a, k));
   }
   
   /**
   * Tests Boundaries
   */
   @Test public void kminBoundary1(){
      int[] a = {1};
      int k = 1;
      Assert.assertEquals(1, Selector.kmin(a, k));
   }
   
   @Test public void kminBoundary2() {
      int[] a = {2, 1};
      int k = 2;
      Assert.assertEquals(2, Selector.kmin(a, k));
   }
   
   @Test public void kminBoundary3() {
      int[] a = {1, 2};
      int k = 1;
      Assert.assertEquals(1, Selector.kmin(a, k));
   }
   
/*************************TESTS FOR KMAX***********************************/
   /**
   * Tests invalid Cases, a = null
   */
   @Test (expected = IllegalArgumentException.class)
    public void kmaxIllegalTest1() {
      int[] a = null;
      Selector.kmax(a, 3);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void kmaxIllegalTest2() {
      int[] a = new int[0];
      Selector.kmax(a, 3);
   }
   
   /**
   * Tests for invalid cases, k < 1
   */
   @Test (expected = IllegalArgumentException.class)
   public void kmaxIllegalTest3() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 0;
      Selector.kmax(a, k);
   }
   
   /**
   * Tests for invalid cases, k > a.length
   */
   @Test (expected = IllegalArgumentException.class)
   public void kmaxIllegalTest4() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 6;
      Selector.kmax(a, k);
   }
   
   /**
   * Tests for invalid cases, k > distinct values
   */
   @Test (expected = IllegalArgumentException.class)
   public void kmaxIllegalTest5() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int k = 6;
      Selector.kmax(a, k);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void kmaxNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int k = 1;
      Assert.assertEquals(8, Selector.kmax(a, k));
   }
   
   @Test public void kmaxNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      int k = 3;
      Assert.assertEquals(5, Selector.kmax(a, k));
   }
   
   @Test public void kmaxNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      int k = 5;
      Assert.assertEquals(4, Selector.kmax(a, k));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void kmaxDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int k = 3;
      Assert.assertEquals(4, Selector.kmax(a, k));
   }
   
   /**
   * Tests Boundaries
   */
   @Test public void kmaxBoundary1(){
      int[] a = {1};
      int k = 1;
      Assert.assertEquals(1, Selector.kmax(a, k));
   }
   
   @Test public void kmaxBoundary2() {
      int[] a = {2, 1};
      int k = 2;
      Assert.assertEquals(1, Selector.kmax(a, k));
   }
   
   @Test public void kmaxBoundary3() {
      int[] a = {1, 2};
      int k = 1;
      Assert.assertEquals(2, Selector.kmax(a, k));
   }
   
/*************************TESTS FOR RANGE***********************************/
   /**
   * Tests invalid Cases, a = null
   */
   @Test (expected = IllegalArgumentException.class)
    public void rangeIllegalTest1() {
      int[] a = null;
      int low = 0;
      int high = 0;
      Selector.range(a, low, high);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void rangeIllegalTest2() {
      int[] a = new int[0];
      int low = 0;
      int high = 0;
      Selector.range(a, low, high);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void rangeNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int low = 1;
      int high = 5;
      int[] expected = {2, 3, 4};
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }
   
   @Test public void rangeNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      int low = 3;
      int high = 5;
      int[] expected = {5, 3};
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }
   
   @Test public void rangeNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      int low = 4;
      int high = 8;
      int[] expected = {8, 7, 6, 5, 4};
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void rangeDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int low = 3;
      int high = 7;
      int[] expected = {7, 3, 3, 4};
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }

   /**
   * Tests for nothing in range
   */
   @Test public void rangeSpecial() {
      int[] a = {1, 2, 3, 4};
      int low = 5;
      int high = 10;
      int[] expected = new int[0];
      Assert.assertArrayEquals(expected, Selector.range(a, low, high));
   }
   
/*************************TESTS FOR CEILING***********************************/
   /**
   * Tests for invalid cases, a = null.
   */
   @Test (expected = IllegalArgumentException.class)
    public void ceilingIllegalTest1() {
      int[] a = null;
      int key = 0;
      Selector.ceiling(a, key);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingIllegalTest2() {
      int[] a = new int[0];
      int key = 0;
      Selector.ceiling(a, key);
   }
   
   /**
   * Tests for invalid cases, no valid ceiling value.
   */
   @Test (expected = IllegalArgumentException.class)
   public void ceilingIllegalTest3() {
      int[] a = {1, 2, 3, 4};
      int key = 5;
      Selector.ceiling(a, key);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void ceilingNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int key = 1;
      Assert.assertEquals(2, Selector.ceiling(a, key));
   }
   
   @Test public void ceilingNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      int key = 7; 
      Assert.assertEquals(7, Selector.ceiling(a, key));
   }
   
   @Test public void ceilingNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      int key = 0;
      Assert.assertEquals(4, Selector.ceiling(a, key));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void ceilingDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int key = 5;
      Assert.assertEquals(7, Selector.ceiling(a, key));
   }
   
   /**
   * Tests for boundaries
   */
   @Test public void ceilingBoundaries1() {
      int[] a = {1};
      int key = 0;
      Assert.assertEquals(1, Selector.ceiling(a, key));
   }
   
   @Test public void ceilingBoundaries2() {
      int[] a = {2, 1};
      int key = 1;
      Assert.assertEquals(1, Selector.ceiling(a, key));
   }
   
/*************************TESTS FOR FLOOR***********************************/
   /**
   * Tests for invalid cases, a = null.
   */
   @Test (expected = IllegalArgumentException.class)
    public void floorIllegalTest1() {
      int[] a = null;
      int key = 0;
      Selector.floor(a, key);
   }
   
   /**
   * Tests for invalid cases, a.length = 0
   */
   @Test (expected = IllegalArgumentException.class)
   public void floorIllegalTest2() {
      int[] a = new int[0];
      int key = 0;
      Selector.floor(a, key);
   }
   
   /**
   * Tests for invalid cases, no valid ceiling value.
   */
   @Test (expected = IllegalArgumentException.class)
   public void floorIllegalTest3() {
      int[] a = {1, 2, 3, 4};
      int key = 0;
      Selector.floor(a, key);
   }
   
   /**
   * Tests for normal cases
   */
   @Test public void floorNormal1() {
      int[] a = {2, 8, 7, 3, 4};
      int key = 6;
      Assert.assertEquals(4, Selector.floor(a, key));
   }
   
   @Test public void floorNormal2() {
      int[] a = {5, 9, 1, 7, 3};
      int key = 1; 
      Assert.assertEquals(1, Selector.floor(a, key));
   }
   
   @Test public void floorNormal3() {
      int[] a = {8, 7, 6, 5, 4};
      int key = 9;
      Assert.assertEquals(8, Selector.floor(a, key));
   }
   
  /**
  * Tests for what happens with doubles
  */ 
   @Test public void floorDoubles() {
      int[] a = {2, 8, 8, 7, 3, 3, 4};
      int key = 5;
      Assert.assertEquals(4, Selector.floor(a, key));
   }
   
   /**
   * Tests for boundaries
   */
   @Test public void floorBoundaries1() {
      int[] a = {1};
      int key = 2;
      Assert.assertEquals(1, Selector.floor(a, key));
   }
   
   @Test public void floorBoundaries2() {
      int[] a = {1, 2};
      int key = 2;
      Assert.assertEquals(2, Selector.floor(a, key));
   }
}
