/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;


public class BinarySearchTree implements Comparable<Object> {

     private Node root;
     
     
    

    public int compareTo(Object data ) {
        if(data == this.data)
            return 0;
        else
            return 1;
   }

    private class Node {
        private Object data;
        private Node left, right;
        private int count;
        public Node(Object data, int count) {
            this.data = data;
            this.count = count;
        }
    }
    
    public BinarySearchTree(){
        
    }
    public boolean isEmpty(){
        return size()==0;
    }
    
    public int size(){
        return size(root);
    }
    
    private int size(Node node){
        if(node == null)
            return 0;
        else
            return node.count;
    } 
    
    private Object find(Node node){
        if(node == null)
            return null;
        int result = compareTo(node.data);
        if(result < 0)
            return find(node.left);
        else if(result > 0)
            return find(node.right);
        else
            return node.data;
    }
    
    public static void main(String[] args) {
       
    }
}
