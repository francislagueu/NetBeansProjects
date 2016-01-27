
import java.util.Comparator;



/**
 *
 * @author edwige
 */
public class BST {

    

   
    public class Node {
        //public  Comparator<int> comp;
        private int data;
        private Node left, right;
        
        public Node(int item){
            this.data = item;
            left = null;
            right=null;
        }
        
        public boolean insert(int item){
            //int res = item.compareinto(this.data);
            if(item/*.equals(*/==this.data)
                return false;
            else if(item<this.data)
                if(left == null){
                    left = new Node(item);
                    return true;
                }else{
                    return left.insert(item);
                }
            else if(item >this.data){
                if(right == null){
                    right = new Node(item);
                    return true;
                }
                else
                    return right.insert(item);
            }
            return false;
        }
        
        public boolean find(int item){
            //int res = item.compareinto(this.data);
            if(item/*.equals(*/==this.data)
                return true;
            else if(item<this.data){
                if(left==null)
                    return false;
                else
                    return left.find(item);
            }
            else if(item>this.data){
                if(right == null)
                    return false;
                else
                    right.find(item);
            }
            return false;
        }
        
//        public int compare(int P, int Q) {
//            if(comp==null)
//                return ((Comparator<int>)P).compareint(Q);
//            else
//                return comp.compare(P, Q);
//        }
        
        public boolean delete(int item, Node parent){
            //int res = item.compareinto(this.data);
            if(item < this.data){
                if(left!=null)
                    return left.delete(item, this);
                else
                    return false;
            }
            else if(item>this.data){
                if(right!=null)
                    return false;
                else
                    return right.delete(item, this);
            }
            else{
                if(left!=null&&right!=null){
                    this.data = right.findMin();
                    right.delete(this.data, this);
                }
                else if(parent.left ==  this)
                    parent.left = (left!=null)?left:right;
                else if(parent.right == this)
                    parent.right = (left!=null)?left:right;
            }
            return true;
            
        }
        
        public int findMin(){
            if(left == null)
                return data;
            else
                return left.findMin();
        }
        
        public int findMax(){
            if(right==null)
                return data;
            else
                return right.findMax();
        }
        
//        public String toString(){
//            return "Data= "+this.data + "\n";
//        }

        
    }
    
    private Node root;
    public BST(){
        root = null;
    }
    
    public boolean insert(int item){
        if(root == null){
            root = new Node(item);
            return true;
        }
        else
            return root.insert(item);
    }
    
    public boolean find(int item){
        if(root==null)
            return false;
        else
            return root.find(item);
    }
    
    public boolean delete(int item){
        if(root==null)
            return false;
        else{
            if(root.data==item){
                Node node = new Node(0);
                node.left = root;
                boolean answer = root.delete(item, node);
                root = node.left;
                return answer;
            }
            else
                return root.delete(item, null);
        }
    }
    
    public void print(Node node){
//        if(node==null)
//            return "";
//        else
//            return print(node.left) + " " + print(node.right); 
        if(node.left != null)
            print(node.left);
        System.out.println(""+ node.data);
        if(node.right!= null)
            print(node.right);
    }
    
    public void Print(){
        print(root);
    }
    
//    public String toString(){
//        if(root==null)
//            return "empty";
//        //else
//            return print(root);
//    }
}
