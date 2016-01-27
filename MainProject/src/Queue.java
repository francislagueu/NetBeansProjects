
public class Queue<T> {
	RedBlackTree<T>.RBNode front, back;
	RedBlackTree<T> rbt = new RedBlackTree<T>();
	public Queue(){
	}
	
	public void enQueue(Comparable<T> item){
		rbt.insert(item);
	}
	
	public Comparable<T> deQueue(){
		 
		return null;
		
	}

	public Comparable<T> peek(){
		return rbt.root.data;
	}
	
	public boolean isEmpty(){
		return front == null;
	}
}
