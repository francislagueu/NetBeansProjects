
@SuppressWarnings("null")
public class RedBlackTree extends BinarySearchTree{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	public RedBlackTree(){
		head = new RBNode(0);
		head.left = head.right = nullNode;
	}
	
	public void Insert(int item){
            if(head==null){
                insert(item);
                head.color =BLACK;
            }else{
                RBNode node = new RBNode(item);
                insert(node.data);
                if(node.left.color==RED&&node.right.color==RED)
                    color(node);
                if(node.right.color==RED&&node.left.color!=RED)
                    node = rotateLeft(node);
                if(node.left.color==RED && node.left.left.color ==RED)
                    node = rotateRight(node);
            }
//		current = parent = grand = head;
//		//nullNode.data = item;
//		insert(item);
//		while(item !=current.data){
//			great = grand;
//			grand = parent; 
//			parent =current;
//			current = (item < current.data) ? current.left : current.right;
//			if(current.left.color==RED && current.right.color==RED)
//				flip(item);
//			
//		}
//		
	}
	
//	private void flip(int item){
//		current.color = RED;
//		current.left.color =BLACK;
//		current.right.color = RED;
//		
//		if(parent.color == RED){
//			grand.color = RED;
//			if((item < grand.data)!= (item < parent.data))
//				parent = rotate(item, grand);
//			current = rotate(item, great);
//			current.color = BLACK;
//		}
//		head.right.color = BLACK;
//	}
        public void color(RBNode node){
            node.color = !node.color;
            node.left.color = !node.left.color;
            node.right.color = !node.right.color;
        }
	private void Print(RBNode node){
		if(node!=nullNode){
			Print(node.left);
			System.out.println(node.data);
			Print(node.right);
		}
	}
        /*
        call insert of the binary search tree
        color the current node to red
        
        case 1 : if the node parent is black
            continue;
        case 2 : if the node parent is red
            
        
        */
        
        private RBNode fix(RBNode node){
            if(node.right.color==RED)
                node = rotateLeft(node);
            if(node.left.color==RED && node.left.left.color==RED)
                node = rotateRight(node);
            if(node.left.color==RED&&node.right.color==RED)
                color(node);
            return node;
        }
	public void deleteMIn(){
            delete(head.data);
            head.color = BLACK;
        }
	public  void Print(){
		Print(head);
	}

    public int get(int item) {
        return findMin();
    }
    public int findMin(){
	if(root.left==null)
            return root.data;
	else
            return root.left.findMin();
    }	
	
	private static class RBNode{
		RBNode left, right;
		int data;
		boolean color;
	
	public RBNode(int item){
		this.data = item;
		left = null;
		right = null;
	}
	
	
	public RBNode(int item, RBNode lt, RBNode rt){
		this.data = item;
		this.left = lt;
		this.right = rt;
		this.color =RED;
	}
	
	}
	
	private  RBNode head;
	private static RBNode nullNode;
	private static RBNode current;
	private static RBNode parent;
	private static RBNode grand;
	private static RBNode great;
	
	static {
		nullNode = new RBNode(0);
		nullNode.left = nullNode.right = nullNode;
	}
	private static RBNode rotateLeft(RBNode node){
		RBNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
                temp.color = node.color;
                node.color = RED;
		return temp;
	}
	private static RBNode rotateRight(RBNode node){
		RBNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
                temp.color = node.color;
                node.color = RED;
		return temp;
	}
	private RBNode rotate(int item, RBNode parent){
		if(item < parent.data)
			return parent.left = (item < parent.left.data) ?
					rotateLeft(parent.left) : rotateRight(parent.left);
		else 
			return parent.right = (item < parent.right.data) ?
					rotateLeft(parent.right) : rotateRight(parent.right);
						
	}

}
