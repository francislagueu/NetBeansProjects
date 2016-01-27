/**
 * RBTreeTest.java
 * Code to exercise the RBTree class.
 * Uses a red-black tree with string tokens as keys and
 * integers as values.
 * 
 * @author Tom Cormen
 */
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class RBTreeTest {
  public static void main(String[] args) {
    RBTree<String,Integer> rbTree = null;
    Scanner input = new Scanner(System.in);
    char command;
    String key;
    Integer value;
    RBTree<String,Integer>.Node x = null;

    do {
      System.out.print("Command (? for help): ");
      command = input.next().charAt(0);

      switch (command) {
      case 'q':   // quit
        System.out.println("Bye");
        break;

      case 'c':   // create an empty tree
        rbTree = new RBTree<String,Integer>();
        break;

      case 'i':   // insert
        key = input.next();
        value = input.nextInt();
        rbTree.insert(key, value);
        break;

      case 'P':   // print
        if (rbTree.isEmpty())
          System.out.println("Tree is empty");
        else
          System.out.print(rbTree);
        break;

      case 'f':   // search
        key = input.next();
        x = (RBTree<String,Integer>.Node) rbTree.search(key);
        if (x == null)
          System.out.println("Not found");
        break;

      case 'r':   // remove
        if (x != null)
          x.remove();
        break;

      case 'e':   // example
        rbTree = new RBTree<String,Integer>();
        rbTree.insert("O", 15);
        rbTree.insert("E", 5);
        rbTree.insert("P", 16);
        rbTree.insert("C", 3);
        rbTree.insert("L", 12);
        rbTree.insert("T", 20);
        rbTree.insert("J", 10);
        rbTree.insert("M", 13);
        rbTree.insert("R", 18);
        rbTree.insert("W", 23);
        rbTree.insert("F", 6);
        rbTree.insert("G", 7);
        break;

      case 's':   // successor
        if (x != null) {
          RBTree<String,Integer>.Node y =
              (RBTree<String,Integer>.Node) x.successor();
          if (y == null)
            System.out.println("no successor");
          else
            System.out.println(y);        
        }
        break;

      case 'p':   // predecessor
        if (x != null) {
          RBTree<String,Integer>.Node y = 
              (RBTree<String,Integer>.Node) x.predecessor();
          if (y == null)
            System.out.println("no predecessor");
          else
            System.out.println(y);        
        }
        break;

      case 'n':   // minimum
        if (x != null)
          System.out.println(x.minimum());
        break;

      case 'x':   // maximum
        if (x != null)
          System.out.println(x.maximum());
        break;
        
      case 't':   // get root
        x = (RBTree<String,Integer>.Node) rbTree.getRoot();
        break;

      case '?':   // print commands
        System.out.println("c create\ni <s> <v> insert\nr remove\nf <s> find\nP print\ne example\ns successor\nr predecessor\nn minimum\nx maximum\nt get root\n?q quit\n? help\n");
        break;

      default:
        System.out.println("Huh?");
        break;
      }
      
      if (rbTree != null)
        rbTree.check();
    }
    while (command != 'q');
  }
}