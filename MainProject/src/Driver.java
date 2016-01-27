
public class Driver {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.insert(67);
		bst.insert(85);
		bst.insert(9);
		bst.insert(32);
		bst.insert(52);
		bst.insert(20);
		
		bst.print();
		bst.delete(9);
		bst.print();
		
		RedBlackTree<Integer> rbt = new RedBlackTree<>();
		rbt.insert(76);
		rbt.insert(9);
		rbt.insert(25);
		rbt.insert(10);
		rbt.insert(14);
		rbt.insert(7);
		rbt.insert(5);
		rbt.insert(95);
		rbt.insert(26);
		rbt.insert(85);
		
		rbt.Print();
	}

}
