import java.util.Arrays;
/**
* The Selector Class shows useful functionalities of arrays.
* @Author Sloan Kiechel
*/

public final class Selector {

   private Selector() { }

/**
* The min method selects the minimum value from a given array.
* Throws an IllegalArgumentException if the array is null
* or has zero length.
* @param a the array
*/
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int min = a[0];
      for (int i = 1; i < a.length; i ++) {
         if (a[i] < min) {
            min = a[i];
         }
      }
      return min;
   }
   
/**
* The max method selects the maximum value from a given array.
* Throws an IllegalArgumentException if the array is null
* or has zero length.
* @param a the array
*/   
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      } 
      int max = a[0];
      for (int i = 1; i < a.length; i++) {
         if (a[i] > max) {
            max = a[i];
         }
      }
      return max;
   }
   
/**
* The kmin method selects the kth minimum value from a given array.
* Throws an IllegalArgumentException if the array is null,
* has zero length, or there is no kth minimum value.
* @param a the array
* @param k the kth value to search for
*/   
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      //copies array so as to not change original array
      int[] b = new int[a.length];
      for (int i = 0; i < a.length; i ++) {
         b[i] = a[i];
      }
      //Sorts array
      Arrays.sort(b);
      int distinct = 1;
      int temp = b[0];
      int kmin = 0;
      if (k == 1) {
         kmin = b[0];
         return kmin;
      }
      //Checks array for distinct elements, if distinct = k, that is kmin.
      for (int i = 1; i < a.length; i++) {
         if (b[i] != temp) {
            distinct++;
            if (distinct == k) {
               kmin = b[i];
            }
         }
         temp = b[i];
      }
      //If k is bigger than distanct, there is no kmin.
      if (k > distinct) {
         throw new IllegalArgumentException();
      }
      return kmin;
   }

/**
* The kmax method selects the kth maximum value from a given array.
* Throws an IllegalArgumentException if the array is null,
* has zero length, or there is no kth maximum value.
* @param a the array
* @param k the kth value to search for
*/    
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
         throw new IllegalArgumentException();
      }
      //copies array so as to not change original array
      int[] b = new int[a.length];
      for (int i = 0; i < a.length; i ++) {
         b[i] = a[i];
      }
      //Sorts array
      Arrays.sort(b);
      int distinct = 1;
      int temp = b[b.length - 1];
      int kmax = 0;
      if (k == 1) {
         kmax = b[b.length - 1];
         return kmax;
      }
      //Checks array for distinct elements, if distinct = k, that is kmin.
      for (int i = b.length - 1; i >= 0; i--) {
         if (b[i] != temp) {
            distinct++;
            if (distinct == k) {
               kmax = b[i];
            }
         }
         temp = b[i];
      }
      //If k is bigger than distanct, there is no kmin.
      if (k > distinct) {
         throw new IllegalArgumentException();
      }      
      return kmax;
   }

/**
* The range method returns an array of all values betwen low and high from
* the original given array.
* Throws an IllegalArgumentException if the array is null or
* has zero length.
* @param a the given array
* @param low the low value
* @param high the high value
*/   
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      //Goes through to find how many are in the range, since the size of an array
      //cannot be changed.
      int size = 0;
      for (int i = 0; i < a.length; i ++) {
         if (a[i] >= low && a[i] <= high) {
            size++;
         }
      }
      //Creates range array, returns it immediately if size = 0.
      int[] range = new int[size];
      if (size == 0) {
         return range;
      }
      //If size !=0, fills in range array and then returns it.
      int j = 0;
      for (int i = 0; i < a.length; i++) {
         if (a[i] >= low && a[i] <= high) {
            range[j] = a[i];
            j++;
         }
      }
      return range;
   }

/**
* The ceiling method returns the smallest value in a given array that
* is greater than or equal to a given key value.
* Throws an IllegalArgumentException if the array is null or
* has zero length, or if there is no qualifying ceiling value.
* @param a the given array
* @param key the given value
*/   
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int ceiling = 0;
      boolean found = false; //Tels if first possible ceiling found
      for (int i = 0; i < a.length; i ++) {
      //If no first ceiling, only compare to key.
         if (!found && a[i] >= key) {
            ceiling = a[i];
            found = true;
         }
         //If already found a ceiling, compare to key and previous ceiling.
         else if (a[i] >= key && a[i] <= ceiling) {
            ceiling = a[i];
         }
      }
      //If never found a ceiling value, throw an exception.
      if (!found) {
         throw new IllegalArgumentException();
      }
      return ceiling;
   }

/**
* The floor method returns the largest value in a given array that
* is less than or equal to a given key value.
* Throws an IllegalArgumentException if the array is null or
* has zero length, or if there is no qualifying ceiling value.
* @param a the given array
* @param key the given value
*/      
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      int floor = 0;
      boolean found = false; //Tels if first possible floor found
      for (int i = 0; i < a.length; i ++) {
      //If no first floor, only compare to key.
         if (!found && a[i] <= key) {
            floor = a[i];
            found = true;
         }
         //If already found a floor, compare to key and previous floor.
         else if (a[i] <= key && a[i] >= floor) {
            floor = a[i];
         }
      }
      //If never found a floor value, throw an exception.
      if (!found) {
         throw new IllegalArgumentException();
      }
      return floor;
   }
}