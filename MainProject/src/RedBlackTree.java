
public class RedBlackTree<T> extends BinarySearchTree<T>{
	public final static int BLACK = 1;
	public final static int RED = 0;
	public class RBNode extends BinarySearchTree<T>.Node{
		public int color;
		RBNode(Comparable<T> item) {
			super(item);
			color = BLACK;
		}
		
                public Node insert(RBNode node, Comparable<T> item){
                    node = new RBNode(item);
                    super.insert(node, item);
                    node.insertFixUp();
                    return null;
                    
                }
		  public void rotateLeft() {
		      RBNode x = this;
		      BinarySearchTree<T>.Node y = x.right;
		      x.right = y.left;
		      if (y.left != null)
		        y.left.parent = x;
		      y.parent = x.parent;
		      if (x.parent == null)
		        root = y;
		      else if (x == x.parent.left)
		        x.parent.left = y;
		      else
		        x.parent.right = y;
		      y.left = x;
		      x.parent = y;
		    }
		
		  public void rotateRight() {
		      RBNode y = this;
		      BinarySearchTree<T>.Node x = y.left;
		      y.left = x.right;
		      if (x.right != null)
		        x.right.parent = y;
		      x.parent = y.parent;
		      if (y.parent == null)
		        root = x;
		      else if (y == y.parent.right)
		        y.parent.right = x;
		      else
		        x.parent.left = x;
		      x.right = y;
		      y.parent = x;
		    }
		  public void insertFixUp(){
			  RBNode node = this;
				//RBNode node = new RBNode(item);
				insertCase1(node);
			}
	}
	
	public void insertCase1(RBNode node){
		if(node.parent == null)
			node.color = BLACK;
		else
			insertCase2(node);
	}
	
	@SuppressWarnings("unchecked")
	public void insertCase2(RBNode node){
		if(((RedBlackTree<T>.RBNode)node.parent).color == BLACK)
			return;
		else
			insertCase3(node);
	}
	
	@SuppressWarnings("unchecked")
	public void insertCase3(RBNode node){
		RBNode temp = (RedBlackTree<T>.RBNode) Uncle(node);
		if(temp!=null&&temp.color!=BLACK){
			((RedBlackTree<T>.RBNode)node.parent).color=BLACK;
			temp.color = BLACK;
			RBNode grand = (RedBlackTree<T>.RBNode)grandParent(node);
			grand.color = RED;
			insertCase1(grand);
		}
		else
			insertCase4(node);
	}
	
	@SuppressWarnings("unchecked")
	public void insertCase4(RBNode node){
		RBNode grand = (RedBlackTree<T>.RBNode) grandParent(node);
		if(node==node.parent.right && node.parent==grand.left){
			((RedBlackTree<T>.RBNode)node.parent).rotateLeft();
			node = (RedBlackTree<T>.RBNode) node.left;
		}
		else if(node==node.parent.left&&node.parent==grand.right){
			((RedBlackTree<T>.RBNode)node.parent).rotateRight();
			node = (RedBlackTree<T>.RBNode)node.right;
		}
		insertCase5(node);
	}
	
	@SuppressWarnings("unchecked")
	public void insertCase5(RBNode node){
		RBNode grand = (RedBlackTree<T>.RBNode) grandParent(node);
		((RedBlackTree<T>.RBNode)node.parent).color = BLACK;
		grand.color = RED;
		if(node==node.parent.left)
			grand.rotateRight();
		else
			grand.rotateLeft();
	}
	
    /**
     *
     * @param item
     * @return
     */
//    @SuppressWarnings("unchecked")
//        @Override
//	public RBNode insert(Comparable<T> item){
//           RBNode node = (RBNode)super.insert(item);
//		node.left = null;
//		node.right=null;
//		((RBNode)node).color = RED;
//		if(node==root)
//			((RBNode)node).color = BLACK;
//		else
//			((RBNode)node).insertFixUp();
//		return (RBNode)node;
//	}
        @Override
	public RBNode insert(Comparable<T> item){
               RBNode node=new RBNode(item);
//            node = (RedBlackTree).root;
            node.insert(node, item);
            return null;
        }
	public void deleteCase1(RBNode node){
		//RBNode node = new RBNode(item);
		if(node.parent ==null)
			return;
		else
			deleteCase2(node);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCase2(RBNode node){
		RBNode sib =  (RedBlackTree<T>.RBNode)Sibling(node);
		if(node.color ==RED){
			((RedBlackTree<T>.RBNode)node.parent).color = RED;
			sib.color = BLACK;
			if(node==node.parent.left)
				((RedBlackTree<T>.RBNode)node.parent).rotateLeft();
			else
				((RedBlackTree<T>.RBNode)node.parent).rotateRight();
		}
		deleteCase3(node);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCase3(RBNode node){
		RBNode sib = (RedBlackTree<T>.RBNode)Sibling(node);
		if(((RedBlackTree<T>.RBNode)node.parent).color==BLACK&&sib.color==BLACK
				&&((RedBlackTree<T>.RBNode)sib.left).color==BLACK&&((RedBlackTree<T>.RBNode)sib.right).color==BLACK){
			sib.color = BLACK;
			deleteCase1((RedBlackTree<T>.RBNode)node.parent);
		}
		else
			deleteCase4(node);
		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCase4(RBNode node){
		 RBNode sib = (RedBlackTree<T>.RBNode)Sibling(node);
		 if(((RedBlackTree<T>.RBNode)node.parent).color !=BLACK&&((RedBlackTree<T>.RBNode)sib.left).color==BLACK
				 &&((RedBlackTree<T>.RBNode)sib.right).color==BLACK){
			 sib.color = RED;
			 ((RedBlackTree<T>.RBNode)node.parent).color = BLACK;
		 }
		 else
			 deleteCase5(node);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCase5(RBNode node){
		RBNode sib = (RedBlackTree<T>.RBNode)Sibling(node);
		if(node==node.parent.left&&sib.color==BLACK&&((RedBlackTree<T>.RBNode)sib.left).color!=BLACK
				&&((RedBlackTree<T>.RBNode)sib.right).color==BLACK){
			sib.color=RED;
			((RedBlackTree<T>.RBNode)sib.left).color = BLACK;
			sib.rotateRight();
		}
		else if(node==node.parent.right&&sib.color==BLACK&&((RedBlackTree<T>.RBNode)sib.right).color!=BLACK
				&&((RedBlackTree<T>.RBNode)sib.left).color==BLACK){
			sib.color=RED;
			((RedBlackTree<T>.RBNode)sib.right).color = BLACK;
			sib.rotateLeft();
		}
		deleteCase6(node);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCase6(RBNode node){
		RBNode sib = (RedBlackTree<T>.RBNode)Sibling(node);
		sib.color = ((RedBlackTree<T>.RBNode)node.parent).color;
		((RedBlackTree<T>.RBNode)node.parent).color = BLACK;
		if(node==node.parent.left){
			((RedBlackTree<T>.RBNode)sib.right).color=RED;
			((RedBlackTree<T>.RBNode)node.parent).rotateLeft();
		}
		else{
			((RedBlackTree<T>.RBNode)sib.left).color=RED;
		((RedBlackTree<T>.RBNode)node.parent).rotateRight();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public RBNode delete(Comparable<T> item){
		RBNode node =(RedBlackTree<T>.RBNode)super.delete(item);
		deleteCase1(node);
		return node;
	}
	
	public void Print(){
		super.print();
	}
}







