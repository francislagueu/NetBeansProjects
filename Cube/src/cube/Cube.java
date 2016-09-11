package cube;

public class Cube {
	// Front, right, back, left, top, bottom faces.
	private char myFront, myRight, myBack, myLeft, myTop, myBottom;  			

	/**
	 * Construct a cube with the given colors for the faces.
	 * @param front color of front side of the cube
	 * @param right color of the right side of the cube
	 * @param back color of the back side of the cube
	 * @param left color of the left side of the cube
	 * @param top color of the top of the cube
	 * @param bottom color of the bottom of the cube
	 */
	public Cube(char front, char right, char back, char left, char top, char bottom) {
		myFront = front;
		myRight = right;
		myBack = back;
		myLeft = left;
		myTop = top;
		myBottom = bottom;
	}
			
	/**
	 * Rotate this cube 90 degrees around the top-bottom axis
	 */
	public void rotate() {
		char temp = myFront;
		myFront = myRight;
		myRight = myBack;
		myBack = myLeft;
		myLeft = temp;
	}
			
	/**
	 * Rotate this cube 90 degrees around the front-back axis
	 */
	public void tip() {
		char temp = myTop;
		myTop = myRight;
		myRight = myBottom;
		myBottom = myLeft;
		myLeft = temp;
	}
			
  /**
   * Flip this cube top to bottom around the front-back axis
   */
	public void flip() {
		tip();
		tip();
	}
			
	/**
	 * Convert this cube to a string representation.
	 */
	public String toString() {
	  return myFront + " " +  myRight + " " + myBack + " " + myLeft 
	 	   + "    " + myTop + " " + myBottom + "\n";
	}
			
	// Functions to return the colors of faces
	public char front() { return myFront; }
	public char left() { return myLeft; }
	public char back() { return myBack; }
	public char right() { return myRight; }
	public char top() { return myTop; }
	public char bottom() { return myBottom; }

}
