package cube;

public class BuriedCount {
  // Hold count of non-buried faces of each color
	private int red, blue, green, white;
	
	/**
	 * Construct an object that keeps track of the unburied faces of each color
	 */
	public BuriedCount(Cube [] cubes) {
		red = blue = green = white = 0;
		
		// Use unburyFace to add 1 to the appropriate color.  
		// Results in a count of the number of each color.
		for(Cube cube : cubes) {
			unburyFace(cube.front());
			unburyFace(cube.left());
			unburyFace(cube.back());
			unburyFace(cube.right());
			unburyFace(cube.top());
			unburyFace(cube.bottom());
		}
	}

	/**
	 * Have the cube placements so far have not buried too many of any color?
	 * @return true if the puzzle can possibly be completed
	 */
  public boolean buriedOK() {
  	return red >= 4 && blue >= 4 && green >= 4 && white >= 4;
  }
  
  /**
   * Bury a face of color c
   * @param c the character of the color to be buried
   */
  public void buryFace(char c)
  {
  	switch(c)
  	{ 
  	case 'R': red--; break;
  	case 'G': green--; break;
  	case 'B': blue--; break;
  	case 'W': white--; break;
  	default: throw  new IllegalArgumentException(
  			"Bad character '" + c + "' found in buryFace");
  	}
  }

  /**
   * Unbury a face of color c
   * @param c the character of the color to be unburied
   */
  public void unburyFace(char c)  {
  	switch(c)
  	{ 
  	case 'R': red++; break;
  	case 'G': green++; break;
  	case 'B': blue++; break;
  	case 'W': white++; break;
  	default: throw  new IllegalArgumentException(
  			"Bad character '" + c + "' found in unburyFace");
  	}
  }		
}
