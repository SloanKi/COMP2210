import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;






/**
 * Creates a word search game based on definitions from WordSearchGame.
 *
 * @author Sloan Kiechel (ssk0006@auburn.edu)
 * @version 2017-03-27
 * 
 */
 
public class SloansWordSearch implements WordSearchGame {
   private TreeSet<String> lexicon; //List of words
   private String[][] board; //Game board
   private static final int MAX_NEIGHBORS = 8;
   private int width;
   private int height;
   private boolean[][] visited; // Keeps track of visited positions
   private ArrayList<Integer> path; //Keeps track of path for isOnBoard
   private String wordSoFar; //Keeps track of word being built
   private SortedSet<String> allWords;
   private ArrayList<Position> path2; //Path for all words. Like x,y better than row-major.
   
   /**
   * Constructor. Initializes default board.
   */
   public SloansWordSearch() {
      lexicon = null;
      //Creates default board.
      board = new String[4][4];
      board[0][0] = "E"; 
      board[0][1] = "E"; 
      board[0][2] = "C"; 
      board[0][3] = "A"; 
      board[1][0] = "A"; 
      board[1][1] = "L"; 
      board[1][2] = "E"; 
      board[1][3] = "P"; 
      board[2][0] = "H"; 
      board[2][1] = "N"; 
      board[2][2] = "B"; 
      board[2][3] = "O"; 
      board[3][0] = "Q"; 
      board[3][1] = "T"; 
      board[3][2] = "T"; 
      board[3][3] = "Y";    
      width = board.length;
      height = board[0].length;
      markAllUnvisited(); //Creats visited board and marks as unvisited
   }
       
    /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      lexicon = new TreeSet<String>(); 
   //Checks if file is real, and reads it in.
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         Scanner scan = new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (scan.hasNext()) {
            String str = scan.next();
            str = str.toUpperCase();
            lexicon.add(str); //Adds it to the lexicon
            scan.nextLine();
         }
      }
      //If can't find file, throw exception.
      catch (java.io.FileNotFoundException e) {
         throw new IllegalArgumentException();
      } 
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
     //If letterArray null, throw exception.
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      int n = (int)Math.sqrt(letterArray.length);
      //Throw exception if not square.
      if ((n * n) != letterArray.length) {
         throw new IllegalArgumentException();
      }
      //Put array into 2D array.
      board = new String[n][n];
      width = n;
      height = n;
      int index = 0;
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j++) {
            board[i][j] = letterArray[index];
            index++;
         }
      }
      markAllUnvisited(); //Creats visited board and marks as unvisited
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
      String strBoard = "";
      for (int i = 0; i < height; i ++) {
         if (i > 0) {
            strBoard += "\n";
         }
         for (int j = 0; j < width; j++) {
            strBoard += board[i][j] + " ";
         }
      }
      return strBoard;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      path2 = new ArrayList<Position>(); //Keeps track of path
      allWords = new TreeSet<String>();
      wordSoFar = "";
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
            wordSoFar = board[i][j];
            if (isValidWord(wordSoFar) && wordSoFar.length() >= minimumWordLength) {
               allWords.add(wordSoFar);
            }
            if (isValidPrefix(wordSoFar)) {
               Position temp = new Position(i,j);
               path2.add(temp);
               //Starts DFS of Board
               dfs2(i, j, minimumWordLength); 
               //When fails, removes last part from temp.
               path2.remove(temp);
            }
         }
      }
      return allWords;
   }
   
   private void dfs2(int x, int y, int min) {
      Position start = new Position(x, y);
      markAllUnvisited(); //Marks everything unvisited
      markPathVisited(); //Marks path of current word visited
      for (Position p : start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (isValidPrefix(wordSoFar + board[p.x][p.y])) {
               wordSoFar += board[p.x][p.y];
               path2.add(p);
               if (isValidWord(wordSoFar) && wordSoFar.length() >= min) {
                  allWords.add(wordSoFar);
               }
               dfs2(p.x, p.y, min);
               //Backtracking, remove last part added to wordSoFar.
               path2.remove(p);
               int endIndex = wordSoFar.length() - board[p.x][p.y].length();
               wordSoFar = wordSoFar.substring(0, endIndex);
            }
         }
      }
      markAllUnvisited(); //Marks everything unvisited.
      markPathVisited(); //Marks path of current word visited.
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      int score = 0;
      Iterator<String> itr = words.iterator();
      while (itr.hasNext()) {
         String word = itr.next();
         //if word is atleast min length, is in lexicon, and is on the board
         if (word.length() >= minimumWordLength && isValidWord(word)
             && !isOnBoard(word).isEmpty()) {
            //Adds one for meeting min and one for each extra character.
            score += (word.length() - minimumWordLength) + 1;
         }
      }
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      //Checks if in lexicon
      wordToCheck = wordToCheck.toUpperCase();
      return lexicon.contains(wordToCheck);
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      prefixToCheck = prefixToCheck.toUpperCase();
      //Checks if in lexicon
      String word = lexicon.ceiling(prefixToCheck);
      if (word != null) {
         return word.startsWith(prefixToCheck);
      }
      return false;
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      if (lexicon == null) {
         throw new IllegalStateException();
      }
      path2 = new ArrayList<Position>();
      wordToCheck = wordToCheck.toUpperCase();
      wordSoFar = "";
      path = new ArrayList<Integer>();
   //Finds the starting position
      for (int i = 0; i < height; i++) {
         for (int j = 0; j < width; j ++) {
         //If first spot is whole word, add position to list and return.
            if (wordToCheck.equals(board[i][j])) {
               path.add(i * width + j); //Adds row-major position
               return path;
            }
            if (wordToCheck.startsWith(board[i][j])) {
               Position pos = new Position(i, j);
               path2.add(pos); //Adds regular position
               wordSoFar = board[i][j]; //Adds to wordSoFar
               dfs(i, j, wordToCheck); //Starts the Search
               //If search fails, removes from path.
               if (!wordToCheck.equals(wordSoFar)) {
                  path2.remove(pos);
               }
               else {
               //Adds row-major position
                  for (Position p: path2) {
                     path.add((p.x * width) + p.y);
                  } 
                  return path;
               }
            }
         }
      }
      return path;
   }
   
   /**
   * Depth-First Search for isOnBoard.
   * @param x the x value
   * @param y the y value
   * @param wordToCheck the word to check for.
   */
   private void dfs(int x, int y, String wordToCheck) {
      Position start = new Position(x, y);
      markAllUnvisited(); //Marks everything unvisited
      markPathVisited(); //Marks path of current word visited
      for (Position p: start.neighbors()) {
         if (!isVisited(p)) {
            visit(p);
            if (wordToCheck.startsWith(wordSoFar + board[p.x][p.y])) {
               wordSoFar += board[p.x][p.y]; //Adds string on to wordSoFar.
               path2.add(p);
               dfs(p.x, p.y, wordToCheck);
               if (wordToCheck.equals(wordSoFar)) {
                  return;
               }
               else {
                  path2.remove(p);
               //Removes last added part of word, since we are backtracking.
                  int endIndex = wordSoFar.length() - board[p.x][p.y].length();
                  wordSoFar = wordSoFar.substring(0, endIndex);
               }
            }
         }
      }
      markAllUnvisited(); //Marks everything unvisited
      markPathVisited(); //Marks path of current word visited
   }

   /**
   * Marks all positions unvisited.
   */
   private void markAllUnvisited() {
      visited = new boolean[width][height];
      for (boolean[] row : visited) {
         Arrays.fill(row, false);
      }
   }
   
   /**
   * Marks path as visited.
   */
   private void markPathVisited() {
      for (int i = 0; i < path2.size(); i ++) {
         visit(path2.get(i));
      }
   }
   

   ///////////////////////////////////////////
   // Position class and associated methods //
   ///////////////////////////////////////////

   /**
    * Models an (x,y) position in the grid.
    */
   private class Position {
      int x;
      int y;
   
      /** Constructs a Position with coordinates (x,y). */
      public Position(int x, int y) {
         this.x = x;
         this.y = y;
      }
   
      /** Returns a string representation of this Position. */
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   
      /** Returns all the neighbors of this Position. */
      public Position[] neighbors() {
         Position[] nbrs = new Position[MAX_NEIGHBORS];
         int count = 0;
         Position p;
         // generate all eight neighbor positions
         // add to return value if valid
         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               if (!((i == 0) && (j == 0))) {
                  p = new Position(x + i, y + j);
                  if (isValid(p)) {
                     nbrs[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(nbrs, count);
      }
   }

   /**
    * checks if a position is valid.
    * @param p the position
    */
   private boolean isValid(Position p) {
      return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < height);
   }

   /**
    * Checks if a position has been visited.
    * @param p the position
    */
   private boolean isVisited(Position p) {
      return visited[p.x][p.y];
   }

   /**
    * Mark this valid position as having been visited.
    */
   private void visit(Position p) {
      visited[p.x][p.y] = true;
   }

}