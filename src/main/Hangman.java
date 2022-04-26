package main;

import java.util.Scanner; // Import the Scanner class
import java.util.ArrayList; // Import the ArrayList class
import java.io.File; // Import the File class
import java.io.FileNotFoundException;

public class Hangman {

  public static String replay() {
      Scanner replayObj = new Scanner(System.in); // Create a Scanner object
      System.out.println("\nPlay again?");
      String replayYN = replayObj.nextLine(); // Read user input
      replayYN = replayYN.toUpperCase();
      return replayYN;
  }

  public static void main(String[] args) {

    String b0 = "    ======||\n    |     ||\n          ||\n          ||\n          ||\n          ||\n============";
    String b1 = "    ======||\n    |     ||\n    O     ||\n          ||\n          ||\n          ||\n============";
    String b2 = "    ======||\n    |     ||\n    O     ||\n    |     ||\n          ||\n          ||\n============";
    String b3 = "    ======||\n    |     ||\n    O     ||\n   /|     ||\n          ||\n          ||\n============";
    String b4 = "    ======||\n    |     ||\n    O     ||\n   /|\\    ||\n          ||\n          ||\n============";
    String b5 = "    ======||\n    |     ||\n    O     ||\n   /|\\    ||\n   /      ||\n          ||\n============";
    String b6 = "    ======||\n    |     ||\n    O     ||\n   /|\\    ||\n   / \\    ||\n          ||\n============";

    System.out.println("\nWelcome to Hangman!\n"); // Welcome message

    ArrayList<String> wordList = new ArrayList<String>();

//    String abc = replay();

    try {
      File wordBank = new File("wordbank.txt"); // Specify the file name
      Scanner myReader = new Scanner(wordBank);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
	data = data.replaceAll("\\s","");
        wordList.add(data);
      } // Closes while
      myReader.close();
    } // Closes try

    catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    } // Closes catch

    int wordListLength = wordList.size();
    int ranNum = (int)(Math.random() * wordListLength);
    String word = wordList.get(ranNum); // Game word
    word = word.toUpperCase();
    char[] wordCheckChar = word.toCharArray();
    int wordLength = word.length();
   
    ArrayList<String> incorrectGuess = new ArrayList<String>(); // ArrayList used to store incorrect guesses.
    ArrayList<String> wordCheck = new ArrayList<String>(); // ArrayList used to check guessed letters.
    
    for (int i = 0; i < wordLength; i++) { // Adds letters as a String to check against player's input.
      String a = Character.toString(wordCheckChar[i]);
      wordCheck.add(a);
    } // Close for

    ArrayList<String> wordDisplay = new ArrayList<String>(); // Create an ArrayList object to store guessed letters
    for (int i = 0; i < wordLength; i++) {
      wordDisplay.add("-");
    } // Close for

    int replayStatus = 0;  // Replay status. If status is set to 1, the game will end.
    int bodyParts = 6; // Number of body parts left
    int count; // Counter used to check if there are any correct guesses
    int winCondition = 2; // Used to check win condition. 0 is a win, 1 is a loss, 2 is undecided

    while (winCondition == 2) {  // Primary game loop
      System.out.println("---------------------------------------------------------------------------------\n");
      System.out.println("Incorrect guesses:\n" + incorrectGuess);

      if (bodyParts == 6) {
	System.out.println(b0);
      } // Closes if
      if (bodyParts == 5) {
	System.out.println(b1);
      } // Closes if
      if (bodyParts == 4) {
	System.out.println(b2);
      } // Closes if
      if (bodyParts == 3) {
	System.out.println(b3);
      } // Closes if
      if (bodyParts == 2) {
	System.out.println(b4);
      } // Closes if
      if (bodyParts == 1){
	System.out.println(b5);
      } // Closes if

      System.out.println("You have " + bodyParts + " body parts left.\n\n");
      System.out.println("There are " + wordLength + " letters in the word.\n" + wordDisplay + "\n***Note: Word may be plural***");

      Scanner Obj1 = new Scanner(System.in); // Create a Scanner object
      System.out.println("\nGuess a letter or the word. Lower or upper case is fine.");

      String guess = Obj1.nextLine(); // Read user input
      guess = guess.toUpperCase(); // Player's guess. Can be a word or a letter.
      System.out.println("You guessed " + guess + "\n");

      if (guess.equals(word)) {  // Player wins only if they guess the correct word.
        System.out.println("Correct! You win!");
        winCondition = 1;
      } // Closes if

      else {  // Game loops as long as the player has body parts and has not guessed the correct word.
        count = 0;
        for (int i = 0; i < wordLength; i++) { // Adds correct letter guesses to the word display. Adds to the counter for correct letter guesses.
          if ((wordCheck.get(i)).equals(guess)) {
            wordDisplay.set(i, guess);
            count += 1;
          } // Closes if
        } // Closes for

        if (count == 0) {  // A count greater than 1 indicates a correct letter guess.
          bodyParts -= 1;
	  incorrectGuess.add(guess);
        } // Closes if

      if (bodyParts == 0) {  // Checks to see if the player has body parts remaining and change winCondition status.
        winCondition = 1;
      }  // Closes if

      if (winCondition == 1) {  // Checks to see if the player has triggered the losing winCondition.
	System.out.println("---------------------------------------------------------------------------------\n\n" + b6);
        System.out.println("You lost!\nYour word was " + word);

/*        String replayQ = replay(); // Replay system would require rework of earlier code. Perhaps another time.
	  if (replayQ.equals("Y")) {
	    winCondition = 2;
	  } // Closes if
*/ 
      } // Closes if
      } // Closes else
    } // Closes while
  } // Closes main
} // Closes Hangman