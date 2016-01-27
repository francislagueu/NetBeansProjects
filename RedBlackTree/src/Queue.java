

/**
 *
 * @author Francis
 */
public class Queue {
    private RedBlackTree tree;
    
    public Queue(){
        tree = new RedBlackTree();
    }
    
    public boolean isEmpty(){
        return (tree.root == null);
    }
    
    public void enQueue(int item){
        tree.Insert(item);
    }
    
    public int deQueue(){
        if(isEmpty())
            return 0;
        else{
            int item = tree.get(1);
            tree.delete(item);
            return item;
        }
    }
    
    public int peek(){
        if(isEmpty())
            return 0;
        else
            return tree.get(1);
    }
}
