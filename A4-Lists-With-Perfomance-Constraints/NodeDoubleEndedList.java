import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * DoubleEndedList.java. Uses nodes to create a double-ended list. 
 * Elements can be inserted and deleted from either end of the list, but
 * not from any other location.
 * 
 * @author   Sloan Kiechel
 * @version  2017-03-14
 */
public class NodeDoubleEndedList<T> implements DoubleEndedList<T> {
   private Node front;
   private int size;
   private Node end;
   
   /**
   * The Node class gives us a way to organize our list.
   */
   private class Node {
      private T element;
      private Node next;
      private Node prev;
     //A constructor 
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
     //Another constructor 
      public Node(T e, Node n) {
         element = e;
         next = n;
      }
      
      //A third constructor
      public Node(T e, Node n, Node p) {
         element = e;
         next = n;
         prev = p;
      }
      
      //Length
      public int length(Node n) {
         Node p = n;
         int len = 0;
         while (p != null) {
            len++;
            p = p.next;
         }
         return len;
      }
      
   }
   
   //Constructor
   public NodeDoubleEndedList() {
      front = null;
      size = 0;
      end = null;
   }

   /**
    * Returns the number of elements in this list.
    */
   public int size(){
      return size;
   }
 
   /**
    * Returns true if this list contains no elements, false otherwise.
    */
   public boolean isEmpty() {
      return size == 0;
   }
   
    /**
    * Adds element to the front of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addFirst(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      //If there is no front, this is the front and the end.
      if (front == null) {
         front = n;
         end = n;
      }
      //Else, make it the start and link accordingly
      else {
         front.prev = n;
         n.next = front;
         front = n;
      }
      size++;
      
   }
   
   /**
    * Adds element to the end of the list. If element is null,
    * this method throws an IllegalArgumentException.
    */
   public void addLast(T element) {
      if (element == null) {
         throw new IllegalArgumentException();
      }
      Node n = new Node(element);
      //If there is no front, this is the front and the end
      if (front == null) {
         front = n;
         end = front;
      }
      //Else, make it the end and link accordingly
      else {
         n.prev = end;
         end.next = n;
         end = n;
      }
      size++;
   }
      
   /**
    * Delete and return the element at the front of the list.
    * If the list is empty, this method returns null.
    */
   public T removeFirst() {
   //If empty, return null
      if (isEmpty())
      {
         return null;
      }
      T removed = front.element; //remember to return
      //If only 1, set front and end to null.
      if (size == 1) {
         front = null;
         end = null;
      }
      else {
         front = front.next;
         front.prev = null;
      }
      size--;
      return removed;
   }
   
   /**
    * Delete and return the element at the end of the list.
    * If the list is empty, this method returns null.
    */
   public T removeLast() {
      if (isEmpty()) {
         return null;
      }
      T removed = end.element; //remember to return
      //if only one, set front and end to null.
      if (size == 1) {
         front = null;
         end = null;
      }
      else {
         end = end.prev;
         end.next = null;
      }
      size--;
      return removed;
   }
   
   /**
    * Creates and returns an iterator over the elements of this list.
    */
   public Iterator<T> iterator() {
      return new LinkedIterator();
   }
   
   private class LinkedIterator implements Iterator<T> {
      private Node current = front;
      
      public boolean hasNext() {
         return current != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T result = current.element;
         current = current.next;
         return result;
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}