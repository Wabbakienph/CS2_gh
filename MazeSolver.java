import java.util.ArrayList;

class MazeSolver{
	private Agenda agenda;
	// private ArrayList<MazeGridLocation> visited;
	private ArrayList<MazeGridLocation> path;

	public MazeSolver(Agenda a){
		this.agenda = a;
		// this.visited = new ArrayList<MazeGridLocation>();
		this.path = new ArrayList<MazeGridLocation>();
	}

	public ArrayList<MazeGridLocation> solveMaze(Maze m, MazeGUI mg){	
		if (agenda.isEmpty()) {
			MazeGridLocation start = m.getStartLocation();
			MazeGridLocation goal = m.getGoalLocation();
			m.markVisited(start);
			mg.visitLoc(start);
			MazeGridLocation currLoc = start;

			// A new 2D to assign parent location (currLoc) to neighboring locations
			MazeGridLocation[][] parentMap = new MazeGridLocation[m.getNumRows()][m.getNumColumns()];
		// PART 1: DETERMINE SOLVABILITY 
			while (!currLoc.equals(goal)) {
				ArrayList<MazeGridLocation> neighbors = getNeighbors(m, currLoc);
				
				for (MazeGridLocation neighbor : neighbors){
					if (!m.isVisited(neighbor) && !agenda.containsLocation(neighbor)) {
						agenda.addLocation(neighbor);
						mg.addLocToAgenda(neighbor);
						parentMap[neighbor.getRow()][neighbor.getColumn()] = m.getLocation(currLoc.row, currLoc.col);
					}
				}
				

				if (!agenda.isEmpty()) {
					m.markVisited(currLoc);
					mg.visitLoc(currLoc);
					mg.pause(200);
					currLoc = agenda.removeLocation();
					m.markVisited(currLoc); // Mark the current location as visited
					mg.visitLoc(currLoc);
				} else { // no PASSABLE location insight = crash/no path found
					System.out.println("No path found.");
					return new ArrayList<MazeGridLocation>();
				}
			}
			m.markVisited(goal);
			mg.visitLoc(goal);
		
		// PART 2: DRAWING AN ACTUAL SOLUTION
			/*
			 * Reconstructing the SOLUTION PATH by
			 * backtracking from the goal and adding the current node to the top of the path
			 */
			while (currLoc != null && !currLoc.equals(start)) {
				path.add(0, currLoc);
				mg.addLocToPath(currLoc);
				// mg.pause(200);

				MazeGridLocation parent = parentMap[currLoc.row][currLoc.col];
				// parents could be null!
				if (parent != null) {
					currLoc = parent;		
				}
			}
			path.add(0, start); // Add the start location to the path
		}

		return path;
	}

	/*
	 * Helper function to generate neighboring locations of a specified spot.
	 */
	public ArrayList<MazeGridLocation> getNeighbors(Maze m, MazeGridLocation target) {
		ArrayList<MazeGridLocation> neighbors = m.getOpenNeighbors(target);

		// A new ArrL to store every 
		ArrayList<MazeGridLocation> neighborsToAdd = new ArrayList<>();

		for (MazeGridLocation neighbor : neighbors) {
			if (!m.isVisited(neighbor)) {
				neighborsToAdd.add(neighbor);
			}
		}

		return neighborsToAdd;
	}
}