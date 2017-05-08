import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;
/**
 * ArrayRandomizedList.java. 
 * Uses arrays to create a list with order defined as "random
 * order." The order described by a radomized list is "random" in the sense
 * that the element accessed by either the sample or remove method is selected
 * uniformly at random from the current elements in the list. In addition, an
 * iterator on a randomized list will sequentially access each element in some
 * uniformly random sequence. Simultaneous iterators on the same randomized
 * list are independent of each other. That is, they will with high probability
 * have different iteration sequences.
 * 
 * @author   Sloan Kiechel (ssk0006@auburn.edu)
 * @version  2017-03-14
 */
public class ArrayRandomizedList<T> implements RandomizedList<T>{
   private T[] elements;
   private int size;
   private static final int DEFAULT_CAPACITY = 5;
   /**
   * Default constructor
   */
   public ArrayRandomizedList() {
      this(DEFAULT_CAPACITY);
   }
   
   /**
   * Constructor
   */
   @SuppressWarnings("unchecked") //Gets rid of warning
   public ArrayRandomizedList(int capacity) { 
      elements = (T[]) new Object[capacity];
      size = 0;
   }
   
    /**
    * Returns the number of elements in this list.
    */
   public int size() {
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }
    
   /**
    * Adds the specified element to this list. If the element is null, this
    * method throws an IllegalArgumentException.
    */
   public void add(T element){ 
      if (element == null)
      {
         throw new IllegalArgumentException();
      }
      //Resizes array if full
      if (size == elements.length)
      {
         resize(elements.length*2);
      }
      elements[size] = element;
      size++;
   }
  
   /**
    * Selects and removes an element selected uniformly at random from the
    * elements currently in the list. If the list is empty this method returns
    * null.
    */
   public T remove() {
      if (this.isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int value = rand.nextInt(size); //Choose random number
      T removed = elements[value]; //Remember removed element to return
      elements[value] = null; // Remove element
      //Now, if value isn't the last item, switch the last value with this value
      if (value != (size-1)) { //(Size - 1) = last index
         elements[value] = elements[size-1];
         elements[size-1] = null;
      }
      size --; //Now that items have been switched, minus size.
      //if array is less than 25% full, reduce array capacity to half.
      if (size > 0 && size < elements.length / 4) {
         resize(elements.length/2);
      }
      return removed;
   }
   
   /**
    * Selects but does not remove an element selected uniformly at random from
    * the elements currently in the list. If the list is empty this method
    * return null.
    */
   public T sample() {
      if (this.isEmpty()) {
         return null;
      }
      Random rand = new Random();
      int value = rand.nextInt(size); //Choose random number
      return elements[value];
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   public Iterator<T> iterator() {
      return new ArrayIterator(elements, size);
   }
   
   /**
   * Resizes an array
   */   
   private void resize(int capacity) {
      T[] a = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i ++) {
         a[i] = elements[i];
      }
      elements = a;
   }
   
  /** 
   * Nested class that makes an iterator.
   */
   public class ArrayIterator<T> implements Iterator<T> {
      private T[] items; //The array of elements iterated over
      private int length; //The number of elements in the array
      /**
      * Constructor
      */
      public ArrayIterator(T[] elements, int sizeIn) {
         items = elements;
         length = sizeIn;
      }
      
      /**
      * Checks to see if there is a next element
      */
      public boolean hasNext() {
         return (length > 0); //As long as length > 0, still has a next, since next removes size.
      }
      
      /**
      * Remove(), for our purposes throws UnsupportedOperationException (since we
      * already have a remove function in the List).
      */
      public void remove() {
         throw new UnsupportedOperationException();
      }
      
      /**
      * Returns the next item in the list, or throws a NoSuchElementException if 
      * there are no more elements.
      */
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         Random rand = new Random();
         int value = rand.nextInt(length); //Choose random number from remaining elements.
         T next = items[value]; //Remember removed element to return
      //Now, if value isn't the last item, switch with last
         if (value != (length-1)) { //(Size - 1) = last index
            items[value] = items[length-1];
            items[length-1] = next;
         }
         length--;
         return next;
      }
   }
}
