
public class BinarySearchTree {
	public Node root;
	
	BinarySearchTree(){
		root = null;
	}
	
	public boolean insert(int item){
		if(root == null){
			root = new Node(item);
			return true;
		}else
			return root.insert(item);
	}
	
	public boolean find(int item){
		if(root == null)
			return false;
		else
			return root.find(item);
	}
        
        
	
	public boolean delete(int item){
		if(root==null)
			return false;
		else{
			if(root.data == item){
				Node node = new Node(0);
				node = root;
				boolean result = root.delete(item, node);
				root = node.left;
				return result;
			}else
				return root.delete(item, null);
		}
	}
	
	public class Node{
		int data;
		Node left;
		Node right;
		
		/*Node(){
			data = 0;
			left = null;
			right = null;
		}*/
		public boolean insert(int item) {
			// TODO Auto-generated method stub
			if(item == this.data)
				return false;
			else if(item < this.data){
				if(left==null){
					left = new Node(item);
					return true;
				}else
					return left.insert(item);
			}else if(item > this.data){
				if(right==null){
					right = new Node(item);
					return true;
				}else
					return right.insert(item);
			}
			return false;
		}
		
		public boolean find(int item){
			if(item == this.data)
				return true;
			else if(item > this.data){
				if(right == null)
					return false;
				else
					return right.find(item);
			}
			else if(item < this.data){
				if(left == null)
					return false;
				else
					return left.find(item);
			}
			return false;
		}
		public int findMin(){
			if(left==null)
				return data;
			else
				return left.findMin();
			
		}
		public int findMax(){
			if(right==null)
				return data;
			else
				return right.findMin();
			
		}
                
                
		
		public boolean delete(int item, Node parent){
			if(item < this.data){
				if(left!=null)
					return left.delete(item, this);
				else
					return false;
			}
			else if(item > this.data){
				if(right!=null)
					return right.delete(item, this);
				else
					return false;
			}else{
				if(left!=null&&right!=null){
					this.data = right.findMin();
					right.delete(this.data, this);
				}else if(parent.left==this){
					parent.left = (left!=null) ? left : right;
				}else if(parent.right==this){
					parent.right = (left!=null) ? left : right;
				}
				return true;
			}
		}
		
		Node(int item){
			data = item;
			left = null;
			right = null;
		}
	}
        
}
