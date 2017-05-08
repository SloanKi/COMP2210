import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Collections;

public class SelectorTest {
   
   /** Collections used in the various examples. */
   static Collection<Integer> c1 = Arrays.<Integer>asList(new Integer[]{2,8,7,3,4});
   static Collection<Integer> c2 = Arrays.<Integer>asList(new Integer[]{5,9,1,7,3});
   static Collection<Integer> c3 = Arrays.<Integer>asList(new Integer[]{8,7,6,5,4});


   /**
    * Defines a total order on integers as ascending natural order.
    */
   static Comparator<Integer> ascendingInteger =
      new Comparator<Integer>() {
         public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
         }
      };


   /**
    * Defines a total order on integers as descending natural order.
    * This is the reverse of ascendingInteger above.
    */
   static Comparator<Integer> descendingInteger =
      new Comparator<Integer>() {
         public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
         }
      };


   /**
    * Defines an exmple composite "data" value with two fields.
    */
   static class Data {
      String  stringVal;
      Integer integerVal;
   
      public Data(String sval, Integer ival) {
         stringVal = sval;
         integerVal = ival;
      }
   
      @Override
      /**
       * Returns a string representation of this Data.
       * @return a formatted string with s and i values
       */
      public String toString() {
         return "(" + stringVal + ", " + integerVal + ")";
      }
   
      @Override
      /**
       * Returns true if the provided object is
       * equal to this Data, false otherwise.
       */
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         }
         if (obj == null) {
            return false;
         }
         if (obj.getClass() != this.getClass()) {
            return false;
         }
         Data that = (Data) obj;
         return (this.stringVal.equals(that.stringVal))
            && (this.integerVal.equals(that.integerVal));
      }
   }


   /** An array of Data used in various examples. */
   static Collection<Data> c4 = Arrays.<Data>asList(new Data[]{
      new Data("A",5), new Data("B", 4), new Data("C", 3), new Data("D", 2), new Data("E", 1)});


   /**
    * Defines a total order on Data as ascending natural order of
    * the String field s.
    */
   static Comparator<Data> ascendingStringData =
      new Comparator<Data>() {
         public int compare(Data d1, Data d2) {
            return d1.stringVal.compareTo(d2.stringVal);
         }
      };


   /**
    * Defines a total order on Data as ascending natural order of
    * the Integer field i.
    */
   static Comparator<Data> ascendingIntegerData =
      new Comparator<Data>() {
         public int compare(Data d1, Data d2) {
            return d1.integerVal.compareTo(d2.integerVal);
         }
      };


   /**
    * Returns a string representation of the given collection.
    * @param  c the provided collection
    * @return   a formatted string with each element of the collection
    */
   static String asString(Collection c) {
      StringBuilder s = new StringBuilder();
      s.append("[");
      for (Object o : c) {
         s.append(o.toString() + ",");
      }
      s.delete(s.length() - 1, s.length());
      s.append("]");
      return s.toString();
   }
/****************************************************************************/   
/*******************************MIN TESTS************************************/
/****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void minInvalid1() {
      Collection invalid = null;
      Selector.<Integer>min(invalid, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void minInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>min(c1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void minInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>min(empty, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void minNormal1() {
      Integer actual = Selector.<Integer>min(c1, ascendingInteger);
      Integer expected = 2;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal2() {
      Integer actual = Selector.<Integer>min(c2, descendingInteger);
      Integer expected = 9;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal3() {
      Integer actual = Selector.<Integer>min(c3, ascendingInteger);
      Integer expected = 4;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal4() {
      Data actual = Selector.<Data>min(c4, ascendingStringData);
      Data expected = new Data("A", 5);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void minNormal5() {
      Data actual = Selector.<Data>min(c4, ascendingIntegerData);
      Data expected = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
/****************************************************************************/   
/*******************************MAX TESTS************************************/
/****************************************************************************/
   @Test (expected = IllegalArgumentException.class)
    public void maxInvalid1() {
      Collection invalid = null;
      Selector.<Integer>max(invalid, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void maxInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>max(c1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void maxInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>max(empty, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void maxNormal1() {
      Integer actual = Selector.<Integer>max(c1, ascendingInteger);
      Integer expected = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal2() {
      Integer actual = Selector.<Integer>max(c2, descendingInteger);
      Integer expected = 1;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal3() {
      Integer actual = Selector.<Integer>max(c3, ascendingInteger);
      Integer expected = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal4() {
      Data actual = Selector.<Data>max(c4, ascendingStringData);
      Data expected = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void maxNormal5() {
      Data actual = Selector.<Data>max(c4, ascendingIntegerData);
      Data expected = new Data("A", 5);
      Assert.assertEquals(expected, actual);
   }
   
/****************************************************************************/   
/*******************************KMIN TESTS************************************/
/****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void kminInvalid1() {
      Collection invalid = null;
      Selector.<Integer>kmin(invalid, 1, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void kminInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>kmin(c1, 2, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kminInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>kmin(empty, 2, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kminInvalid4() {
      Selector.<Integer>kmin(c1, 6, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kminInvalid5() {
      Selector.<Integer>kmin(c1, 0, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void kminNormal1() {
      Integer actual = Selector.<Integer>kmin(c1, 1, ascendingInteger);
      Integer expected = 2;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kminNormal2() {
      Integer actual = Selector.<Integer>kmin(c2, 2, descendingInteger);
      Integer expected = 7;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kminNormal3() {
      Integer actual = Selector.<Integer>kmin(c3, 3, ascendingInteger);
      Integer expected = 6;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kminNormal4() {
      Data actual = Selector.<Data>kmin(c4, 4, ascendingStringData);
      Data expected = new Data("D", 2);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kminNormal5() {
      Data actual = Selector.<Data>kmin(c4, 2, ascendingIntegerData);
      Data expected = new Data("D", 2);
      Assert.assertEquals(expected, actual);
   }
   
   //Tests for doubles
   @Test public void kminDoubles1() {
      List<Integer> d = Arrays.asList(1, 1, 1, 1, 2, 3, 4, 5);
      Integer actual = Selector.<Integer>kmin(d, 3, ascendingInteger);
      Integer expected = 3;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kminDoubles2() {
      List<Data> d = Arrays.asList(new Data("A",1), new Data("A",1), new Data("B", 2), new Data("C",3));
      Data actual = Selector.<Data>kmin(d, 2, ascendingStringData);
      Data expected = new Data("B", 2);
      Assert.assertEquals(expected, actual);
   }
   
/*****************************************************************************/   
/*******************************KMAX TESTS************************************/
/****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void kmaxInvalid1() {
      Collection invalid = null;
      Selector.<Integer>kmax(invalid, 1, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void kmaxInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>kmax(c1, 2, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kmaxInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>kmax(empty, 2, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kmaxInvalid4() {
      Selector.<Integer>kmax(c1, 6, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void kmaxInvalid5() {
      Selector.<Integer>kmax(c1, 0, ascendingInteger);
   }
   
   //Tests normal Cases
   @Test public void kmaxNormal1() {
      Integer actual = Selector.<Integer>kmax(c1, 1, ascendingInteger);
      Integer expected = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kmaxNormal2() {
      Integer actual = Selector.<Integer>kmax(c2, 2, descendingInteger);
      Integer expected = 3;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kmaxNormal3() {
      Integer actual = Selector.<Integer>kmax(c3, 3, ascendingInteger);
      Integer expected = 6;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kmaxNormal4() {
      Data actual = Selector.<Data>kmax(c4, 4, ascendingStringData);
      Data expected = new Data("B", 4);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kmaxNormal5() {
      Data actual = Selector.<Data>kmax(c4, 2, ascendingIntegerData);
      Data expected = new Data("B", 4);
      Assert.assertEquals(expected, actual);
   }
   
   //Tests for doubles
   @Test public void kmaxDoubles1() {
      List<Integer> d = Arrays.asList(1, 1, 1, 1, 2, 3, 4, 5);
      Integer actual = Selector.<Integer>kmax(d, 3, ascendingInteger);
      Integer expected = 3;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void kmaxDoubles2() {
      List<Data> d = Arrays.asList(new Data("A",1), new Data("A",1), new Data("B", 2), new Data("C",3), new Data("C",3));
      Data actual = Selector.<Data>kmax(d, 2, ascendingStringData);
      Data expected = new Data("B", 2);
      Assert.assertEquals(expected, actual);
   }   
   
/*****************************************************************************/   
/*******************************RANGE TESTS************************************/
/*****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void rangeInvalid1() {
      Collection invalid = null;
      Selector.<Integer>range(invalid, 1, 1, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void rangeInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>range(c1, 2, 1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void rangeInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>range(empty, 2, 1, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void rangeInvalid4() {
      Selector.<Integer>range(c1, 9, 12, ascendingInteger);
   }

   //Tests normal cases
   @Test public void rangeNormal1()
   {
      Collection<Integer> actual = Selector.<Integer>range(c1, 1, 5, ascendingInteger);
      List<Integer> expected = Arrays.asList(2, 3, 4);
      Assert.assertEquals(expected, actual);
   }   
   
   @Test public void rangeNormal2() {
      Collection<Integer> actual = Selector.<Integer>range(c2, 3, 5, ascendingInteger);
      List<Integer> expected = Arrays.asList(5, 3);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void rangeNormal3() {
      Collection<Integer> actual = Selector.<Integer>range(c2, 5, 3, descendingInteger);
      List<Integer> expected = Arrays.asList(5, 3);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void rangeNormal4() {
      Collection<Integer> actual = Selector.<Integer>range(c3, 4, 8, ascendingInteger);
      List<Integer> expected = Arrays.asList(8, 7, 6, 5, 4);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void rangeNormal5() {
      Data low = new Data("B", 3);
      Data high = new Data("C", 5);
      Collection<Data> actual = Selector.<Data>range(c4, low, high, ascendingStringData);
      List<Data> expected = Arrays.asList(new Data("B", 4), new Data("C", 3));
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void rangeNormal6() {
      Data low = new Data("F", 4);
      Data high = new Data("G", 7);
      Collection<Data> actual = Selector.<Data>range(c4, low, high, ascendingIntegerData);
      List<Data> expected = Arrays.asList(new Data("A", 5), new Data("B", 4));
      Assert.assertEquals(expected, actual);
   }
   
/*****************************************************************************/   
/*******************************CEILING TESTS************************************/
/*****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void ceilingInvalid1() {
      Collection invalid = null;
      Selector.<Integer>ceiling(invalid, 1, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void ceilingInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>ceiling(c1, 1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void ceilingInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>ceiling(empty, 1, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void ceilingInvalid4() {
      Selector.<Integer>ceiling(c1, 9, ascendingInteger);
   }
   
   //tests for normal cases
   @Test public void ceilingNormal1() {
      Integer expected = Selector.<Integer>ceiling(c1, 1, ascendingInteger); 
      Integer actual = 2;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ceilingNormal2() {
      Integer expected = Selector.<Integer>ceiling(c2, 7, descendingInteger); 
      Integer actual = 7;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ceilingNormal3() {
      Integer expected = Selector.<Integer>ceiling(c3, 0, ascendingInteger); 
      Integer actual = 4;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ceilingNormal4() {
      Data expected = Selector.<Data>ceiling(c4, new Data("B", 9), ascendingStringData); 
      Data actual = new Data("B", 4);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void ceilingNormal5() {
      Data expected = Selector.<Data>ceiling(c4, new Data("F", 0), ascendingIntegerData); 
      Data actual = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
   
/*****************************************************************************/   
/*******************************FLOOR TESTS************************************/
/*****************************************************************************/
   //tests for invalid cases
   @Test (expected = IllegalArgumentException.class)
    public void floorInvalid1() {
      Collection invalid = null;
      Selector.<Integer>floor(invalid, 1, ascendingInteger);
   }
   
   @Test (expected = IllegalArgumentException.class)
    public void floorInvalid2() {
      Comparator invalid = null;
      Selector.<Integer>floor(c1, 1, invalid);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void floorInvalid3() {
      List empty = Collections.emptyList();
      Selector.<Integer>floor(empty, 1, ascendingInteger);
   }
   
   @Test (expected = NoSuchElementException.class)
   public void floorInvalid4() {
      Selector.<Integer>floor(c1, 1, ascendingInteger);
   }
   
   //tests for normal cases
   @Test public void floorNormal1() {
      Integer expected = Selector.<Integer>floor(c1, 6, ascendingInteger); 
      Integer actual = 4;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void floorNormal2() {
      Integer expected = Selector.<Integer>floor(c2, 1, descendingInteger); 
      Integer actual = 1;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void floorNormal3() {
      Integer expected = Selector.<Integer>floor(c3, 9, ascendingInteger); 
      Integer actual = 8;
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void floorNormal4() {
      Data expected = Selector.<Data>floor(c4, new Data("F", 0), ascendingStringData); 
      Data actual = new Data("E", 1);
      Assert.assertEquals(expected, actual);
   }
   
   @Test public void floorNormal5() {
      Data expected = Selector.<Data>floor(c4, new Data("B", 9), ascendingIntegerData); 
      Data actual = new Data("A", 5);
      Assert.assertEquals(expected, actual);
   }
}
