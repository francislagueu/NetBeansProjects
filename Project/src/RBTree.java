

@SuppressWarnings("unchecked")
public class RBTree<K extends Comparable<K>, V> extends BST<K,V> {
    private int BLACK = 1;
    private int RED = 0;
  public class Node extends BST<K,V>.Node {
    protected boolean isBlack;
    int color;
    
    public Node(K key, V value) {
      super(key, value);
      //isBlack = false;
      color = RED;
    }
   
    protected boolean isBlack() {
      return isBlack;
    }
    
    protected boolean isRed() {
      return !isBlack;
    }
   
    protected void blacken() {
      isBlack = true;
    }
   
    protected void redden() {
      isBlack = false;
    }
   
    protected void leftRotate() {
      Node x = this;
      BST<K,V>.Node y = x.right;
      x.right = y.left;
      if (y.left != sentinel)
        y.left.parent = x;
      y.parent = x.parent;
      if (x.parent == sentinel)
        root = y;
      else if (x == x.parent.left)
        x.parent.left = y;
      else
        x.parent.right = y;
      y.left = x;
      x.parent = y;
    }
    protected void rightRotate() {
      Node y = this;
      BST<K,V>.Node x = y.left;
      y.left = x.right;
      if (x.right != sentinel)
        x.right.parent = y;
      x.parent = y.parent;
      if (y.parent == sentinel)
        root = x;
      else if (y == y.parent.right)
        y.parent.right = x;
      else
        x.parent.left = x;
      x.right = y;
      y.parent = x;
    }
    
    // Fix the possible violation of the red-black properties
    // created by the insert method.  This node, z, which is red, could
    // have a red parent, or z could be root, which must be black.
    // Push the possible violation up toward the root, until it
    // reaches the root, which can just be made black.
    protected void rbInsertFixup() {
      Node z = this;
      
      while (((RBTree<K,V>.Node) z.parent).color==RED) {
        if (z.parent == z.parent.parent.left) {
          // z's parent is a left child.
          Node y = (RBTree<K,V>.Node) z.parent.parent.right;
          if (y.color == RED) {
            // Case 1: z's uncle y is red.
//            System.out.println("rbInsertFixup: Case 1 left");
            ((RBTree<K,V>.Node) z.parent).color = BLACK;
           // y.blacken();
            y.color = BLACK;
            ((RBTree<K,V>.Node) z.parent.parent).color = RED;
            z = (RBTree<K,V>.Node) z.parent.parent;
          }
          else {
            if (z == z.parent.right) {
              // Case 2: z's uncle y is black and z is a right child.
//              System.out.println("rbInsertFixup: Case 2 left");
              z = (RBTree<K,V>.Node) z.parent;
              z.leftRotate();
            }
            // Case 3: z's uncle y is black and z is a left child.
//            System.out.println("rbInsertFixup: Case 3 left");
            //((RBTree<K,V>.Node) z.parent).blacken();
            ((RBTree<K,V>.Node) z.parent).color = BLACK;
            //((RBTree<K,V>.Node) z.parent.parent).redden();
            ((RBTree<K,V>.Node) z.parent.parent).color = RED;
            ((RBTree<K,V>.Node) z.parent.parent).rightRotate();
          }
        }
        else {
          // z's parent is a right child.  Do the same as when z's
          // parent is a left child, but exchange "left" and "right."
          Node y = (RBTree<K,V>.Node) z.parent.parent.left;
          if (y.color==RED) {
            // Case 1: z's uncle y is red.
//            System.out.println("rbInsertFixup: Case 1 right");
            //((RBTree<K,V>.Node) z.parent).blacken();
            ((RBTree<K,V>.Node) z.parent).color = BLACK;
            //y.blacken();
            y.color = BLACK;
            ((RBTree<K,V>.Node) z.parent.parent).redden();
            ((RBTree<K,V>.Node) z.parent.parent).color = RED;
            z = (RBTree<K,V>.Node) z.parent.parent;
          }
          else {
            if (z == z.parent.left) {
              // Case 2: z's uncle y is black and z is a left child.
//              System.out.println("rbInsertFixup: Case 2 right");
              z = (RBTree<K,V>.Node) z.parent;
              z.rightRotate();
            }
            // Case 3: z's uncle y is black and z is a right child.
//            System.out.println("rbInsertFixup: Case 3 right");
            //((RBTree<K,V>.Node) z.parent).blacken();
            ((RBTree<K,V>.Node) z.parent).color=BLACK;
            //((RBTree<K,V>.Node) z.parent.parent).redden();
            ((RBTree<K,V>.Node) z.parent.parent).color=RED;
            ((RBTree<K,V>.Node) z.parent.parent).leftRotate();
          }
        }
      }

      // The root is always black.
      //((RBTree<K,V>.Node) root).blacken();
      ((RBTree<K,V>.Node) root).color = BLACK;
    }

    // Same as transplant from the BST class, except that we make v
    // have the same parent as this node, even if v is the sentinel.
    protected void transplant(Node v) {
      super.transplant(v);
      v.parent = this.parent;   // even if v is the sentinel
    }
    
    /**
     * Remove this node from a red-black tree.
     * Guaranteed to remove this node, and not some other node.
     */
    public void remove() {
      Node z = this;
      Node y = z;   // y is the node either removed or moved within the tree
      boolean yOrigWasBlack = (y.color == BLACK);//isBlack();  // need to know whether y was black
      Node x;       // x is the node that will move into y's original position
      if (z.left == sentinel) {       // no left child?
        x = (RBTree<K,V>.Node) z.right;
        z.transplant(x);              // replace z by its right child
      }
      else if (z.right == sentinel) { // no right child?
        x = (RBTree<K,V>.Node) z.left;
        z.transplant(x);              // replace z by its left child
      }
      else {
        // Node z has two children.  Its successor y is in its right subtree
        // and has no left child.
        y = (RBTree<K,V>.Node) z.right.minimum();
        yOrigWasBlack = (y.color==BLACK);//.isBlack();
        x = (RBTree<K,V>.Node) y.right;

        // Splice y out of its current location, and have it replace z.
        if (y.parent == z)
          x.parent = y;
        else {
          // If y is not z's right child, replace y as a child of its parent by
          // y's right child and turn z's right child into y's right child.
          y.transplant(x);
          y.right = z.right;
          y.right.parent = y;
        }

        // Regardless of whether we found that y was z's right child, replace z as
        // a child of its parent by y and replace y's left child by z's left child.
        z.transplant(y);
        y.left = z.left;
        y.left.parent = y;

        // Give y the same color as z.
        if (z.color ==BLACK)//.isBlack())
          y.color=BLACK;//.blacken();
        else
          y.color=RED;//redden();
      }

      // If we removed a black node, then must fix up the tree because
      // black-heights are now incorrect.
      if (yOrigWasBlack)
        x.rbRemoveFixup();
    }

    // Fix the possible violation of the red-black properties caused
    // by removing a black node. This node has moved into the position of
    // the node that was removed or moved. If the removed node was black,
    // three problems could arise. If it was the root and a red child became
    // the root, now the root is red. If both this node and its parent are red, we have
    // two red nodes in a row. And moving a node y within the tree causes any simple
    // path that had contained y to have one fewer black node.
    // Consider this node, x, now occupying y's original position, as having an
    // extra black.  Restore the red-black properties by pushing the extra
    // black up in the tree until one of the following happens:
    //    x is a red-and-black node, and we color x singly black;
    //    x is the root, and we just remove the extra black; or
    //    having rotated and recolored, we exit the loop.
    protected void rbRemoveFixup() {
      Node x = this;
      Node w = null;

      while (x != root && x.isBlack()) {
          if (x == x.parent.left) {
            w = (RBTree<K,V>.Node) x.parent.right;
            if (w.isRed()) {
              // Case 1: x's sibling w is red.
//              System.out.println("rbRemoveFixup: Case 1 left");
              w.blacken();
              ((RBTree<K,V>.Node) x.parent).redden();
              ((RBTree<K,V>.Node) x.parent).leftRotate();
              w = (RBTree<K,V>.Node) x.parent.right;
            }
            if (((RBTree<K,V>.Node) w.left).isBlack() &&
                ((RBTree<K,V>.Node) w.right).isBlack()) {
              // Case 2: x's sibling w is black, and both of w's children are black.
//              System.out.println("rbRemoveFixup: Case 2 left");
              w.redden();
              x = (RBTree<K,V>.Node) x.parent;
            }
            else {
              if (((RBTree<K,V>.Node) w.right).isBlack()) {
                // Case 3: x's sibling w is black, w's left child is red,
                // and w's right child is black.
//                System.out.println("rbRemoveFixup: Case 3 left");
                ((RBTree<K,V>.Node) w.left).blacken();
                w.redden();
                w.rightRotate();
                w = (RBTree<K,V>.Node) x.parent.right;
              }
              // Case 4: x's sibling w is black, and w's right child is red.
//              System.out.println("rbRemoveFixup: Case 4 left");
              if (((RBTree<K,V>.Node) x.parent).isBlack())
                w.blacken();
              else
                w.redden();
              ((RBTree<K,V>.Node) x.parent).blacken();
              ((RBTree<K,V>.Node) w.right).blacken();
              ((RBTree<K,V>.Node) x.parent).leftRotate();
              x = (RBTree<K,V>.Node) root;
            }
          }
          else {
            w = (RBTree<K,V>.Node) x.parent.left;
            if (w.isRed()) {
              // Case 1: x's sibling w is red.
//              System.out.println("rbRemoveFixup: Case 1 right");
              w.blacken();
              ((RBTree<K,V>.Node) x.parent).redden();
              ((RBTree<K,V>.Node) x.parent).rightRotate();
              w = (RBTree<K,V>.Node) x.parent.left;
            }
            if (((RBTree<K,V>.Node) w.right).isBlack() &&
                ((RBTree<K,V>.Node) w.left).isBlack()) {
              // Case 2: x's sibling w is black, and both of w's children are black.
//              System.out.println("rbRemoveFixup: Case 2 right");
              w.redden();
              x = (RBTree<K,V>.Node) x.parent;
            }
            else {
              if (((RBTree<K,V>.Node) w.left).isBlack()) {
                // Case 3: x's sibling w is black, w's right child is red,
                // and w's left child is black.
//                System.out.println("rbRemoveFixup: Case 3 right");
                ((RBTree<K,V>.Node) w.right).blacken();
                w.redden();
                w.leftRotate();
                w = (RBTree<K,V>.Node) x.parent.left;
              }
              // Case 4: x's sibling w is black, and w's left child is red.
//              System.out.println("rbRemoveFixup: Case 4 right");
              if (((RBTree<K,V>.Node) x.parent).isBlack())
                w.blacken();
              else
                w.redden();
              ((RBTree<K,V>.Node) x.parent).blacken();
              ((RBTree<K,V>.Node) w.left).blacken();
              ((RBTree<K,V>.Node) x.parent).rightRotate();
              x = (RBTree<K,V>.Node) root;
            }
          }
        }

      x.blacken();
    }

    /**
     * @return the String representation of this Node
     */
    public String toString() {
      return super.toString() + ", color = " + (isBlack ? "black" : "red");
    }
  }
  
  /**
   * Constructor for the RBTree class.
   * Makes a sentinel that is its own parent and children, colored black.
   */
  public RBTree() {
    sentinel = getNewNode(null, null);
    sentinel.parent = sentinel;
    sentinel.left = sentinel;
    sentinel.right = sentinel;
    ((RBTree<K,V>.Node) sentinel).blacken();
    root = sentinel;
  }
  
  // Check the black-height of this RBTree and that it does not
  // contain two consecutive red nodes.
  public void check() {
    if (root != sentinel) {
      checkBlackHeight((RBTree<K,V>.Node) root);
      checkReds((RBTree<K,V>.Node) root);
    }
  }

  // Print an error message when a check fails.
  private void complain(String s, Node x) {
    System.err.println("Check error: " + s + " at " + x.toString());
  }

  // Check that x and its parent are not both red.
  private void checkReds(Node x) {
    if (x != sentinel) {
      if (x.parent != sentinel && x.isRed() &&
          ((RBTree<K,V>.Node) x.parent).isRed())
        complain("two consecutive red nodes", x);
      
      checkReds((RBTree<K,V>.Node) x.left);
      checkReds((RBTree<K,V>.Node) x.right);
    }
  }
  
  // Return the black-height of node x.  If its subtrees do not have
  // the same black-height, call attention to it.
  private int checkBlackHeight(Node x) {
    if (x == sentinel)
      return 0;
    else {
      int leftBlackHeight = checkBlackHeight((RBTree<K,V>.Node) x.left) +
          (((RBTree<K,V>.Node) x.left).isBlack() ? 1 : 0);
      int rightBlackHeight = checkBlackHeight((RBTree<K,V>.Node) x.right) +
          (((RBTree<K,V>.Node) x.right).isBlack() ? 1 : 0);
      if (leftBlackHeight != rightBlackHeight)
          complain("blackheight error", x);

      return leftBlackHeight;
    }
  }
    
  /**
   * Create a new node and insert it into the RBTree.
   * 
   * @param key key of the new node
   * @param value value in the new node
   * @return a reference to the new node
   */
  public Node insert(K key, V value) {
    Node z = (RBTree<K,V>.Node) super.insert(key, value);
    z.left = sentinel;
    z.right = sentinel;
    z.redden();
    z.rbInsertFixup();
    return z;
  }
  
  // Overrides the getNewNode method from the superclass.
  protected Node getNewNode(K key, V value) {
    return new Node(key, value);
  }
}