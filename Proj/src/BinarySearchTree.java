/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edwige
 */
public class BinarySearchTree<T extends Comparable<T>> {
    
    public class Node{
        Node left, right, parent;
        T data;
        
        public Node(T item){
            this.data = item;
            parent = left = right = null;
        }
        
        public Node findMin(){
            Node node = this;
            while(node.left !=null)
                node =node.left;
            return node;
        }
        
        public Node findMax(){
            Node node= this;
            while(node.right !=null)
                node =node.right;
            return node;
        }
        
//        protected void subTree(Node node){
//            if(this.parent == null)
//                root = node;
//            else if(this==this.parent.left)
//                this.parent.left = node;
//            else
//                this.parent.right = node;
//            if(node!=null)
//                node.parent = this.parent;
//        }
       
         public String toString(){
           
             return " Item= " + data+ " Parent = "+(parent==null ? "null": parent.data)
                     +" left= " + (left==null? "null":left.data)+ " Right= "
                     +(right==null? "null":right.data);
             
         } 
       
    }
    
    protected Node root;
    
    public Node insert(Node node , T item){
            if(node == null)
             return node = new Node(item);
            else if(item.compareTo(node.data)< 0)
              return  insert(node.left, item);
            else if(item.compareTo(node.data)>0)
                return insert(node.right, item);
            else
                return null;
    }
    public void insert(T item){
        insert(root, item);
    }

     public Node delete(Node node, T item){
            if(node==null)
                return null;
            if(item.compareTo(node.data)<0)
                return delete(node.left, item);
            else if(item.compareTo(node.data)>0)
                return delete(node.right, item);
            else{
                Node temp;
                if(node==null)
                    return null;
                else if(node.right==null){
                    temp = node;
                    node = node.left;
                   return temp;
                }
                else if(node.left == null){
                    temp =node;
                    node=node.right;
                    return temp;
                }else{
                    Node prev = node;
                    temp = node.left;
                    while(temp.right!=null){
                        prev = temp;
                        temp = temp.right;
                    }
                    node.data = temp.data;
                    if(prev!=node)
                        prev.right = temp.left;
                    else
                        prev.left = temp.left;
                    return temp;
                }
            }
        }
    
        public Node find(Node node, T item){
            if(node==null)
                return null;
            if(node!=null){
                if(item.compareTo(node.data)<0)
                    return find(node.left, item);
                else if(item.compareTo(node.data)>0)
                    return find(node.right, item);
                if(item.compareTo(node.data)==0)
                    return node;
            }
            return null;
        }
        
        public void print(Node node){
            if(node!=null){
                print(node.left);
                System.out.println(node.data + "");
                print(node.right);
            }
        }
        
    public void delete(T item){
        delete(root, item);
    }
    
    public void find(T item){
        find(root, item);
    }
    
    public void print(){
        print(root);
    }
    public String Print(Node node){
        if(root==null)
            return "";
        else
            return Print(node.left) + " " + node.toString()+"\n" 
                    + Print(node.right);
    }
    public String toString(){
        if(root==null)
            return "";
        else
            return Print(root);
    }
    public int compareTo(T item){
        Node node = null;
        if(item.compareTo(node.data)==0)
            return 0;
        else if(item.compareTo(node.data)<0)
            return -1;
        else
            return 1;
    }
}
