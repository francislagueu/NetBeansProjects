/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edwige
 */
public class RBT extends BST{
    enum color {BLACK , RED}
    
    public class RNode extends BST.Node {
        public color col;
        public RNode left, right, parent;
        
        public RNode(int data){
            super(data);
            parent=left = right = null;
            
            col = col.RED;
        }
        
        
    }
    
    public boolean insert(int item){
         super.insert(item);
        fixInsert();
        return true;
    }
    
    public void fixInsert(){
        
    }
    
    @Override
    public boolean find(int item){
        return super.find(item);
        
    }
    
    public boolean delete(int item){
       
       super.delete(item);
       fixDelete();
       return true;
    }
    
    public void fixDelete(){
        
    }
    
    public void rotateLeft(Node grand, Node parent, Node child){
       (RNOde) parent.right =(RNode) child.left;
    }
    
}
