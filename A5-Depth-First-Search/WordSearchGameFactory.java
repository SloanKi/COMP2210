
/**
 * Provides a factory method for creating word search games. 
 *
 * @author Sloan Kiechel
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-03-23
 */
public class WordSearchGameFactory {

   /**
    * Returns an instance of a class that implements the WordSearchGame
    * interface.
    */
   public static WordSearchGame createGame() {
      // You must return an instance of your solution class here instead of null.
      return new SloansWordSearch();
   }
}
