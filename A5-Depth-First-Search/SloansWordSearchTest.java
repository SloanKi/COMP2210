import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class SloansWordSearchTest {

/**************************************************************************/
/*****************************ISVALIDWORD**********************************/
/**************************************************************************/

 //Should throw IllegalArgumentException if word is null.
   @Test (expected = IllegalArgumentException.class)
    public void validWordTest1() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      game.isValidWord(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void validWordTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.isValidWord("Meow");
   }
   
   //normal tests
   @Test public void validWordTest3() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(true, game.isValidWord("lexicon"));
   }
   
   @Test public void validWordTest4() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(false, game.isValidWord("meow"));
   }
   
   @Test public void validWordTest5() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidWord("MeOw"));
   }
   
   
/**************************************************************************/
/*****************************ISVALIDPREFIX********************************/
/**************************************************************************/

//Should throw IllegalArgumentException if prefix is null.
   @Test (expected = IllegalArgumentException.class)
    public void validPrefixTest1() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      game.isValidPrefix(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void validPrefixTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.isValidPrefix("Meow");
   }
   
   //normal tests
   @Test public void validPrefixTest3() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words_tiny.txt");
      Assert.assertEquals(false, game.isValidPrefix("pre"));
   }
   
   @Test public void validPrefixTest4() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidPrefix("homo"));
   }
   
   @Test public void validPrefixTest5() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      Assert.assertEquals(true, game.isValidPrefix("HoMo"));
   }
   
/**********************************************************************/
/*************************BOARDCREATION********************************/
/**********************************************************************/


   //Checks to see if default board works.
   @Test public void boardTest1() {
      SloansWordSearch game = new SloansWordSearch();
      String expected = "E E C A \nA L E P \nH N B O \nQ T T Y ";
      Assert.assertEquals(expected, game.getBoard());
   }   
   
   //Tries loading in boards.
   //Should throw exception because null
   @Test (expected = IllegalArgumentException.class)
    public void boardTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.setBoard(null);
   }
   
   //Should throw exception because not square.
   @Test (expected = IllegalArgumentException.class)
    public void boardTest3() {
      SloansWordSearch game = new SloansWordSearch();
      String[] notSquare = new String[]{"A", "B", "C"};
      game.setBoard(notSquare);
   }
   
   //Regular board loading
   @Test public void boardTest4() {
      SloansWordSearch game = new SloansWordSearch();
      String[] board = new String[]{"A", "B", "C", "D",};
      String expected = "A B \nC D ";
      game.setBoard(board);
      Assert.assertEquals(expected, game.getBoard());
   }
   
   //Regular board loading
   @Test public void boardTest5() {
      SloansWordSearch game = new SloansWordSearch();
      String[] board = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I"};
      String expected = "A B C \nD E F \nG H I ";
      game.setBoard(board);
      Assert.assertEquals(expected, game.getBoard());
   }
   
   
/**********************************************************************/
/*************************ISONBOARD************************************/
/**********************************************************************/
   //Regular test
   @Test public void isOnBoardTest1() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      ArrayList<Integer> expected = new ArrayList<Integer>();
      expected.add(5); expected.add(6); expected.add(9); expected.add(13);
      Assert.assertEquals(expected, game.isOnBoard("lent")); 
   } 
   
   //Exception Tests
   @Test (expected = IllegalArgumentException.class)
    public void isOnBoardTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      game.isOnBoard(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void isOnBoardTest3() {
      SloansWordSearch game = new SloansWordSearch();
      game.isOnBoard("Meow");
   }
   
   //More regular
   @Test public void isOnBoardTest4() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      ArrayList<Integer> expected = new ArrayList<Integer>();
      Assert.assertEquals(expected, game.isOnBoard("meow")); 
   }
   
   
/**********************************************************************/
/*************************ALLVALIDWORDS********************************/
/**********************************************************************/   
   //Regular Test
   @Test public void allValidWordsTest1() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> expected = new TreeSet<String>();
      expected.add("ALEPOT"); expected.add("BENTHAL"); expected.add("PELEAN"); expected.add("TOECAP");
      Assert.assertEquals(expected, game.getAllValidWords(6)); 
   }
   
   //Exception Tests
   @Test (expected = IllegalArgumentException.class)
    public void allValidWordsTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      game.getAllValidWords(0);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void allValidWordsTest3() {
      SloansWordSearch game = new SloansWordSearch();
      game.getAllValidWords(2);
   }

   //More Regular
   @Test public void allValidWordsTest4() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> expected = new TreeSet<String>();
      Assert.assertEquals(expected, game.getAllValidWords(10));
   }
   
   //Big board from Vocareum because having issues
   @Test public void allValidWordsTest5() {
      SloansWordSearch game = new SloansWordSearch();
      String[] board = board = new String[]{"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B","G","U","P","Y","H","R","R","X","R","E","F","H","D","H","T","K","X","K","O","Z","F","W","Y","H","Y","T","C","H","M","V","P","R","T","A","K","N","E","S","I","B","T",
         "M","V","Y","Q","E","U","O","E","F","A","K","J","C","W","I","K","I","U","K","T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G","D","P","P","P","G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K","H","K","G","B",
         "B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z","R","G","A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T","J","K","J","F","C","I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C","V","P",
         "P","D","S","Q","E","W","O","C","R","L","V","L","P","T","A","T","N","O","R","M","W","K","O","D","O","U","O","V","F","M","H","V","V","S","I","X","Z","L","O","T","Z","L","B","R","G","F","Q","P","A","Y","P","D","L","B","K","S","N","C","H","O","P","Y","K","H",
         "C","R","R","I","C","S","B","J","X","R","F","I","Y","R","H","B","Z","I","P","C","K","I","N","O","E","C","C","U","C","P","I","J","R","E","Y","E","Z","U","R","R","M","F","S","M","R","N","J","I","B","T","Q","O","C","V","R","O","T","X","H","C","R","W","S",
         "A","V","T","N","U","I","O","W","X","C","O","R","X","Q","A","S","A","S","S","E","M","B","L","Y","O","Z","F","P","L","S","C","I","T","L","U","M","O","N","I","T","O","R","J","W","I","N","L","L","L","E","L","J","R","R","E","M","M","O","B","D","X","I","J","D",
         "S","R","L","C","H","S","H","Y","U","L","P","M","O","U","S","E","C","B","I","I","U","I",};
      game.setBoard(board);
      game.loadLexicon("words_medium.txt");
      SortedSet<String> expected = new TreeSet<String>();
      expected.add("ASSESSMENT"); expected.add("CHURCHGOER"); expected.add("INGREDIENT"); expected.add("INTERFACES"); expected.add("ISOMERASES"); expected.add("RETRIEVING"); expected.add("SCAMMONIES"); expected.add("SUBNETWORK");
      Assert.assertEquals(expected, game.getAllValidWords(10)); 
   }
   
/**********************************************************************/
/*************************GETSCOREFORWORDS*****************************/
/**********************************************************************/ 
   //Exceptions
   @Test (expected = IllegalArgumentException.class)
    public void getScoreTest1() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("OWL.txt");
      game.getScoreForWords(new TreeSet<String>(), 0);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void getScoreTest2() {
      SloansWordSearch game = new SloansWordSearch();
      game.getScoreForWords(new TreeSet<String>(), 3);
   }
   
   //Regular tests
   //First two assume that your getAllValidWords is working. I'll also make
   //Some for those who don't have those working yet.
   @Test public void getScoreTest3() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = game.getAllValidWords(6);
      int expected = 5;
      Assert.assertEquals(expected, game.getScoreForWords(words, 6));
   }
   
   @Test public void getScoreTest4() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = game.getAllValidWords(10); //Returns an empty list
      int expected = 0;
      Assert.assertEquals(expected, game.getScoreForWords(words, 10));
   }
   
   //Tests without using getAllValidWords
   @Test public void getScoreTest5() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = new TreeSet<String>();
      words.add("CLEAN"); words.add("ToeCap"); words.add("Thane"); words.add("neele");
      words.add("leant");
      int expected = 6;
      Assert.assertEquals(expected, game.getScoreForWords(words, 5));
   }
   
   @Test public void getScoreTest6() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = new TreeSet<String>();
      words.add("CLEAN"); words.add("ToeCap"); words.add("Thane"); words.add("neele");
      words.add("leant");
      int expected = 1;
      Assert.assertEquals(expected, game.getScoreForWords(words, 6));
   }
   
   @Test public void getScoreTest7() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = new TreeSet<String>();
      words.add("CLEAN"); words.add("ToeCap"); words.add("Thane"); words.add("neele");
      words.add("leant");
      int expected = 0;
      Assert.assertEquals(expected, game.getScoreForWords(words, 7));
   }
   
   @Test public void getScoreTest8() {
      SloansWordSearch game = new SloansWordSearch();
      game.loadLexicon("words.txt");
      SortedSet<String> words = new TreeSet<String>();
      words.add("CLEAN"); words.add("ToeCap"); words.add("Thane"); words.add("neele");
      words.add("leant");
      int expected = 11;
      Assert.assertEquals(expected, game.getScoreForWords(words, 4));
   }
}
