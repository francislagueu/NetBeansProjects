
public class BinarySearchTree<T> {
	
	public class Node{
		Comparable<T> data;
		Node left, right, parent;
		
		Node(Comparable<T> item){
			data = item;
			parent = left = right = null;
		}
		
		@SuppressWarnings("unchecked")
		public Node insert(Node node, Comparable<T> item){
                    if(item == node.data)
                            return null;
                    else{
                        Node temp = new Node(item);
                        int comp = item.compareTo((T)node.data);
                        if(comp < 0){
                            if(node.left==null){
                                node.left = temp;
                                return temp;
                            }
                            else
                                return insert(node.left,item);
                        }
                        else if(comp>0){
                            if(node.right==null){
                                    node.right = temp;
                            }
                            else
                                    return insert(node.right, item);
                        }
                    }

            return null;

		}
		
		@SuppressWarnings("unchecked")
		public Node delete(Node node, Comparable<T> item){
			//node= root;
			Node temp = new Node (item);
			
			int comp = item.compareTo((T)this.data);
			if(comp < 0){
				if(this.left !=null)
					return left.delete(this, temp.data);
				else
					return null;
				
			}else if(comp > 0){
				if(this.right!=null)
					return right.delete(this, temp.data);
				else
					return null;
			}else{
				if(this.left!=null&&this.right!=null){
					this.data = findMin(this.right);
					delete(this.right, this.data);
				}else if(node.left==this){
					node.left = (this.left!=null)? this.left : this.right;
				}else if(node.right==node){
					node.right = (this.left!=null)? this.left : this.right;
				}
				return node;
			}
		}
		public Comparable<T> findMin(Node node){
			Node current = node;
			while(current.left!=null)
				current =current.left;
			return current.data;
		}
		
	}
	
	protected Node root;
	
	BinarySearchTree(){
		root = null;
	}
	
	public Node insert(Comparable<T> item){
		if(root==null){
                    return root = new Node(item);
		}
		else
                    return root.insert(root, item);
	}
	
	public Node find(Node node, Comparable<T> item){
		Node current = node;
		@SuppressWarnings("unchecked")
		int comp = item.compareTo((T)current.data);
		while(current!=null){
			if(comp < 0)
				current = current.left;
			else if(comp > 0)
				current = current.right;
			else
				return current;
		}
		return null;
	}
	
	public Node find(Comparable<T> item){
		return find(root, item);
	}
	
	
	
	public Comparable<T> findMax(Node node){
		Node current = node;
		while(current.right!=null)
			current = current.right;
		return current.data;
	}
	
	public Node Successor(Node node){
		if(node.right!=null){
			Node current = node.right;
			while(current.left!=null)
				current = current.left;
			return current;
		}else{
			Node current = node;
			while(current.parent!=null&&current.parent.right==current)
				current = current.parent;
			return current.parent;
		}
	}
	
	public Node Predecessor(Node node){
		if(node.left!=null){
			Node current = node.left;
			while(current.right!=null)
				current = current.right;
			return current;
		}else{
			Node current = node;
			while(current.parent!=null&&current.parent.left==current)
				current = current.parent;
			return current.parent;
		}
	}
	
	public Node Sibling(Node node){
		if(node==node.parent.left)
			return node.parent.right;
		else
			return node.parent.left;
	}
	
	public Node grandParent(Node node){
		if((node!=null)&&(node.parent!=null))
			return node.parent.parent;
		else
			return null;
	}
	
	public Node Uncle(Node node){
		Node grand = grandParent(node);
		if(grand==null)
			return null;
		if(node.parent == grand.left)
			return grand.right;
		else
			return grand.left;
	}
	
	
	
	public Node delete(Comparable<T> item){
		if(root==null)
			return null;
		else{
			Node node = new Node(null);
			if(root.data == item){
				node.left = root;
				Node temp = root.delete(node, item);
				root = node.left;
				return temp;
			}
			else
				return root.delete(null, item);
			
		}
	}
	
	public void print(Node node){
		if(node==null)
			return;
		else{
			print(node.left);
			System.out.println(node.data);
			print(node.right);
		}
	}
	
	public void print(){
		System.out.println("Hello");
		print(root);
	}

	
}
