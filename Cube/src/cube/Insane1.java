package cube;

public class Insane1 {
  private static int numPlacements;
  
	/**
	 * Performs the backtracking by placing cube number "cubeNum" in all possible
	 *   positions. 
	 * @param cubes the stack of cubes to place in all positions
	 * @param cubeNo the cube currently being placed
	 */
	public static void PlaceCube(CubeTower cubes, int cubeNo) {
    numPlacements++;  // Count another cube placed
    
		if (cubeNo > CubeTower.NUMCUBES) {	// All cubes placed?
			if (cubes.isValid())	    			  // Found a solution?
				System.out.println(cubes);
		}
		else {
			for (int tips = 1; tips <= 3; tips++)	{	 // For each of three top-bottom pairs

				if (cubeNo == 1)			// First cube has 3 non-symetric positions
					PlaceCube(cubes, cubeNo+1);
				else  {								// Other cubes have 24 possible positions
					for (int rot = 1; rot <= 4; rot++) {	// For each of 4 rotation positions
						PlaceCube(cubes, cubeNo+1);
						cubes.rotate(cubeNo);
					}

					cubes.flip(cubeNo);

					for (int rot = 1; rot <= 4; rot++) {	// For each of 4 rotation positions
						PlaceCube(cubes, cubeNo+1);
						cubes.rotate(cubeNo);
					}

					cubes.flip(cubeNo);				// Get back to original position
				}

				cubes.rotate(cubeNo);
				cubes.tip(cubeNo);					// Get a different top-bottom pair
			}
		}
	}


	public static void main(String [] args) {
		CubeTower cubes = new CubeTower();	// Stack of cubes to place

    numPlacements = 0;							// Count nodes examined

		PlaceCube(cubes, 1);
		
		System.out.println("Number of cube placements: " + numPlacements);
	}
}

		

		

		
