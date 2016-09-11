package cube;


public class CubeTower {
  public static final int NUMCUBES = 4;		// Number of cubes to place
	private Cube [] myCubes;			          // Tower of cubes
	
	private BuriedCount buried;		// Keep track of buried faces for Insane3
		
	
	public CubeTower() {
		myCubes = new Cube[NUMCUBES];
		myCubes[0] = new Cube('R', 'R', 'R', 'G', 'W', 'B');
		myCubes[1] = new Cube('B', 'B', 'R', 'W', 'G', 'G');
		myCubes[2] = new Cube('R', 'R', 'W', 'B', 'W', 'G');
		myCubes[3] = new Cube('W', 'W', 'B', 'G', 'G', 'R');
		
		buried = new BuriedCount(myCubes);  // For Insane3
	}
		
	/**
	 * Rotate cube CubeNo 90 degrees around the top-bottom axis
	 * @param cubeNo the number of the cube to rotate
	 */
	public void rotate(int cubeNo) {
		myCubes[cubeNo-1].rotate();
	}
		
	/**
	 * Rotate cube CubeNo 90 degrees around the front-back axis
	 * @param cubeNo the number of the cube to rotate
	 */
		void tip(int cubeNo) {
			myCubes[cubeNo-1].tip();
		}
		
		/**
		 * Flip cube CubeNo top to bottom around the front-back axis
		 * @param cubeNo the number of the cube to rotate
		 */
		void flip(int cubeNo) {
			myCubes[cubeNo-1].flip();
		}
		
		/**
		 * Represents the cube stack as a string
		 */
		public String toString() {
			String returnString = "";
			
			for(int i = 0; i < NUMCUBES; i++)
				returnString += myCubes[i];
			
			return returnString;
		}
		
		/**
		 * Returns true if the cube placements are valid (no color duplications)
		 * @return true if the cube placements are valid
		 */
		public boolean isValid() {
			boolean OK = true;		// True while no duplications found
			
			for(int i = 1; i < NUMCUBES && OK; i++)
				for(int j = 0; j < i && OK; j++)
					OK = myCubes[i].front() != myCubes[j].front() &&
							 myCubes[i].left() != myCubes[j].left() &&
							 myCubes[i].back() != myCubes[j].back() &&
							 myCubes[i].right() != myCubes[j].right();
			
			return OK;
		}
		
		// The following code is used for Insane2 and Insane3
		
	  /**
	   * Returns true if the cubeNo placement is valid (no color duplications with earlier cubes)
	   * @param cubeNo the cube to be checked
	   * @return true if cube cubeNo has no conflicts with earlier placements
	   */
	  public boolean isValid(int cubeNo) {
			boolean OK = true;		// True while no duplications found
			
			for(int i = 0; i < cubeNo-1 && OK; i++)
					OK = myCubes[i].front() != myCubes[cubeNo-1].front() &&
							 myCubes[i].left() != myCubes[cubeNo-1].left() &&
							 myCubes[i].back() != myCubes[cubeNo-1].back() &&
							 myCubes[i].right() != myCubes[cubeNo-1].right();
			
			return OK;
		}
	  
	  // The following code is used for Insane3
	    
	  /**
	   * Have the cube placements so far have not buried too many of any color?
	   * @return true if the puzzle can possibly be completed
	   */
	  public boolean buriedOK()  {
	  	return buried.buriedOK();
	  }

	  /**
	   * Bury the top and bottom faces of cube cubeNo
	   * @param the index of the cube whose top and bottom faces will be buried
	   */
	  public void bury(int cubeNo) {
	  	buried.buryFace(myCubes[cubeNo-1].top());
	  	buried.buryFace(myCubes[cubeNo-1].bottom());
	  }
	  		
	  /** 
	   * Unbury the top and bottom faces of cube cubeNo
	   * @param the index of the cube whose top and bottom faces will be unburied
	   */
	  public void unbury(int cubeNo)  {
	  	buried.unburyFace(myCubes[cubeNo-1].top());
	  	buried.unburyFace(myCubes[cubeNo-1].bottom());
	  }
}
