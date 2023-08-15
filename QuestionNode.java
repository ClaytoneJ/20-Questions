//Clayton Johnson
//CS 145 20 questions lab
//8/6/23
//This creates the binary search tree to be used in the QuestionTree class
public class QuestionNode {

   public String data;
   public QuestionNode yesNode;  
   public QuestionNode noNode;

   //Leaf constructor
   public QuestionNode(String data) {
      this(data,null, null); 
   }
   
   //Branch constructor
   public QuestionNode(String data, QuestionNode yesNode, QuestionNode noNode) {
      this.data = data;
      this.yesNode = yesNode;
      this.noNode = noNode; 
   }
}