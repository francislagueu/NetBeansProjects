
package Linkedlist;


public class LinkedList {
    Node head;
    Node tail;
    int size = 0;
    
    public void insert(int data){
        Node node = new Node(data);
        if(tail==null){
            tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
        size++;
    }
    
    public Node delete(int data){
        
        Node nodeToReturn = null;
        if(size==0)
            return null;
        if(size==1){
            nodeToReturn = head;
            head= null;
            tail = null;
            size--;
            return nodeToReturn;
        }
        Node node = findNodeBefore(data);
        if(node.data == 0){
            head = head.next;
            size--;
        }else if(node != null){
            if(tail.data ==data){
                node.next = null;
                tail = node;
            }else{
                node.next = node.next.next;
            }
            size--;
        }
        return null;
    }
    
    public Node find(int data){
        if(head==null)
            return null;
        
        if(head.data == data)
            return head;
        
        Node node = head;
        while(node.next != null){
           node = node.next;
           if(node.data == data ){
               return node;
           }
        }
        return null;
    }
    public Node findNodeBefore(int data){
        
        if(head.data == data)
            return new Node();
        Node node = head;
        while(node.next !=null){
            if(node.next.data == data)
                return node;
            node = node.next;
            
        }
        
        return null;
    }
}
