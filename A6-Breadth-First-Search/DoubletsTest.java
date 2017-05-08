import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class DoubletsTest {


   @Test public void distanceTest1() throws FileNotFoundException { 
      String str1 = "Meow";
      String str2 = "Woof";
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      Assert.assertEquals(3, doublets.getHammingDistance(str1, str2));
   }
   
   @Test public void distanceTest2() throws FileNotFoundException { 
      String str1 = "Meow";
      String str2 = "Woofs";
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      Assert.assertEquals(-1, doublets.getHammingDistance(str1, str2));
   }
   
   @Test public void distanceTest3() throws FileNotFoundException { 
      String str1 = "Meow";
      String str2 = "meOw";
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      Assert.assertEquals(0, doublets.getHammingDistance(str1, str2));
   }
   
   
   
   
   @Test public void neighborsTest1() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String str = "meow";
      List<String> expected = new ArrayList<String>();
      expected.add("MEOU");
      Assert.assertEquals(expected, doublets.getNeighbors(str));
   }
   
   @Test public void neighborsTest2() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      String str = "azdgsdaf";
      List<String> expected = new ArrayList<String>();
      Assert.assertEquals(expected, doublets.getNeighbors(str));
   }
   
   
   
   
   @Test public void wordLadderTest1() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      List<String> sequence = new ArrayList<String>();
      sequence.add("CaT");
      sequence.add("hAt");
      sequence.add("hut");
      Assert.assertEquals(true, doublets.isWordLadder(sequence));
   }
   
   @Test public void wordLadderTest2() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      List<String> sequence = new ArrayList<String>();
      Assert.assertEquals(false, doublets.isWordLadder(sequence));
   }
   
   @Test public void wordLadderTest3() throws FileNotFoundException {
      WordLadderGame doublets = new Doublets(new FileInputStream(new File("sowpods.txt")));
      List<String> sequence = new ArrayList<String>();
      sequence.add("CaT");
      sequence.add("hAt");
      sequence.add("but");
      Assert.assertEquals(false, doublets.isWordLadder(sequence));
   }
}
