import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author   Sloan (ssk0006@auburn.edu)
 * @version  2017-02-18
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** 
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /** 
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>();
      Iterator<Point> itr = c.iterator();
      while(itr.hasNext()) {
         this.add(itr.next());
      }
   }
 
   /** 
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    */
   public boolean add(Point p) {
      if (line.contains(p)) {
         return false;
      }
      else if (line.isEmpty() || line.size() == 1) {
         line.add(p);
         return true;
      }
      else if (isCollinear(p)) {
         line.add(p);
         return true;
      }
      return false;
   }
   
   /** 
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    */
   public Point first() {
      if (line.isEmpty()) {
         return null;
      }
      return line.first();
   }
   
   /** 
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    */
   public Point last() {
      if (line.isEmpty()) {
         return null;
      }
      return line.last();
   }
   
   /** 
    * Returns the number of points in this line.
    */
   public int length() {
      return line.size();
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. All three properties of compareTo
    * as specified in the Comparable interface are met, and this implementation
    * is consistent with equals.
    */
   @Override
   public int compareTo(Line that) {
      if(line.isEmpty() && that.length() == 0) {
         return 0;
      }
      if (line.isEmpty()) {
         return -1;
      }
      else if (that.length() == 0) {
         return 1;
      }
      else if (line.first().compareTo(that.first()) == 1) {
         return 1;
      }
      else if (line.first().compareTo(that.first()) == -1) {
         return -1;
      }
      else if (line.last().compareTo(that.last()) == 1) {
         return 1;
      }
      else if (line.last().compareTo(that.last()) == -1) {
         return -1;
      }
      return 0;
   }

   /** 
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
      return (Iterator<Point>)line.iterator();
   }
   
   /**    
    * Return true if this line's first and last points are equal to the     
    * parameter's first and last points. Empty lines are equal to each other     
    * but are not equal to any non-empty line.    
    *     
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.    
    */    
   @Override    public boolean equals(Object obj) {       
      if (obj == null) {          
         return false;       
      }       
      if (obj == this) {          
         return true;       
      }       
      if (!(obj instanceof Line)) {          
         return false;       
      }      
      Line that = (Line) obj;       
      if ((this.length() == 0) && (that.length() == 0)) {          
         return true;       
      }       
      if ((this.length() == 0) && (that.length() != 0)) {          
         return false;     
      }       
      if ((this.length() != 0) && (that.length() == 0)) {          
         return false;   
      }       
      return (this.first().equals(that.first())) && (this.last().equals(that.last()));   
   } 
    
    /**  
    * Return a string representation of this line.    
    *    
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.    
    */    
   @Override    public String toString() {
   
      if (length() == 0) {          
         return "";       
      }       
      StringBuilder s = new StringBuilder();       
      for (Point p : line) {        
         s.append(p + " -> ");      
      }      
      s = s.delete(s.length() - 4, s.length());       
      return s.toString();    
   }
 
 
 /**
 * MY OWN ADDED THING TO CHECK IF COLLINEAR. 
 * If the slope between the first point and last point is the same
 * as the slope between the first and question point, it is collinear.
 */ 
   private boolean isCollinear(Point a) {
      Point start = line.first();
      Point end = line.last();
      if(start.slopeTo(end) == start.slopeTo(a)) {
         return true;
      }
      return false;
   }
 
}
