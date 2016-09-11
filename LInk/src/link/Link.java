
package link;

public class Link {

    private Node head;
    public class Node{
        private Node next;
        private int data;
        
        public Node(){
            data = 0;
            next = null;
        }
        public Node(int data){
            this.data = data;
            this.next = null;
            
        }
    }
    
    public Node insert(int item){
        Node node = new Node(item);
        if(head ==null){
            head = node;
            return node;
        }
        else{
            Node curr = head;
            while(curr.next!=null)
                curr = curr.next;
            curr.next = node;
            return node;
        }
        
    }
    public Node delete(int item){
        if(head==null)
            return null;
        else if(item==head.data){
            head = head.next;
            return head;
        }else{
            Node curr = head;
            while(curr.next!=null&&item!=curr.next.data)
                curr = curr.next;
            if(item==curr.next.data){
                curr.next = curr.next.next;
                return curr;
            }
        }
        return null;    
    }
    
    public int find(int item){
        Node curr = head;
        while(curr!=null&&item!=curr.data)
            curr = curr.next;
        if(item==curr.data)
            return curr.data;
        else
            return -1;
    }
    public void print(){
       Node node = head;
        while(node!=null){
            System.out.println(node.data);
            node = node.next;
        }
            
    }
    public static void main(String[] args) {
        Link link = new Link();
        link.insert(4);
        link.insert(8);
        link.insert(3);
        link.insert(1);
        link.insert(9);
        
        link.print();
        
        link.delete(3);
         link.print();
         
         System.out.println();
         
         System.out.println(link.find(9));
    }
    
}
