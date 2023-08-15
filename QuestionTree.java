//Clayton Johnson
//CS 145 20 Questions lab
//8/6/2023
//Interactive binary-tree based guessing game called 20 questions
import java.util.*;
import java.io.*;

public class QuestionTree {
   
   private Scanner input;
   private QuestionNode treeRoot;
   private UserInterface ui;
   private int gamesPlayed;
   private int gamesWon;
   
   //Constructor to initialize the question tree
   public QuestionTree(UserInterface ui) {
      if (ui == null) {
         throw new IllegalArgumentException();
      }
      treeRoot = new QuestionNode("A:computer");
      input = new Scanner(System.in);
      this.ui = ui;
      gamesPlayed = 0;
      gamesWon = 0;
   }
   
   //Loads a new question tree input by the user
   public void load(Scanner input) {
      if (input == null) {
         throw new IllegalArgumentException();
      }
      this.treeRoot = this.loadHelper(input);
   }
   
   //Helper method for loading the question tree(recursively)
   private QuestionNode loadHelper(Scanner input) {    
      String data = input.nextLine();
      QuestionNode current = new QuestionNode(data);
      //If node is a question
      if (data.startsWith("Q:")) {
         //Recursively loads subtrees
         current.yesNode = loadHelper(input);
         current.noNode = loadHelper(input);
      }
      return current;
   }
   
   //Saves question tree to output file
   public void save(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException();
      }
      saveHelper(output, treeRoot);
   }
   
   //Helper method to save the tree(recursively)
   private void saveHelper(PrintStream output, QuestionNode treeRoot) {
      //Checks if node is a leaf
      if (treeRoot.yesNode == null && treeRoot.noNode == null){
            output.println(treeRoot.data);         
        } else {
            //Recursively save question and subtrees
            output.println(treeRoot.data);
            this.saveHelper(output, treeRoot.yesNode);
            this.saveHelper(output, treeRoot.noNode);
        } 
   }
   
   //Returns total number of games played
   public int totalGames() {
      return gamesPlayed;
   }
   
   //Returns total number of games won by the computer
   public int gamesWon() {
      return gamesWon;
   }
   
   //Plays one game of 20 questions
   public void play() {
      gamesPlayed++;
      this.treeRoot = playGame(this.treeRoot);
   }
   
   //Helper method to play a game
   private QuestionNode playGame(QuestionNode current) {
      //If a leaf is reached, ask the user if this is the answer
      if (current.yesNode == null && current.noNode == null) {
         ui.println("Would your object happen to be " + current.data.substring(2) + "? ");
         if (ui.nextBoolean()) {
            ui.println("I win!");
            gamesWon++;
         } else { //gets users object and new question
            ui.print("I lose. What is your object? ");
            QuestionNode answer = new QuestionNode("A:" + ui.nextLine());
            ui.print("Type a yes/no question to distinguish your item from ");
            ui.println(current.data.substring(2) + ": ");
            String question = ui.nextLine();
            ui.println("And what is the answer for your object? "); 
            if (ui.nextBoolean()) {
               current = new QuestionNode("Q:" + question, answer, current); 
            } else {
               current = new QuestionNode("Q:" + question, current, answer); 
            }   
         }
      //If its a question node, ask the question and go down the tree
      } else {
         ui.println(current.data.substring(2));
         if (ui.nextBoolean()) {
            current.yesNode = playGame(current.yesNode);
         } else {
            current.noNode = playGame(current.noNode); 
         }   
      } 
      return current;
   }
}
   
