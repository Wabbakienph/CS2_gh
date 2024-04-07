import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.awt.Color;
import java.util.ArrayList;


/**
 * Represents a Maze object
 */
public class Maze{

    private int numRows;
    private int numCols;

    private MazeGridLocation[][] maze;

    private MazeGridLocation startLoc;
    private MazeGridLocation goalLoc;

    /**
    * Creates a new Maze object which is used by MazeGUI and
    * MazeSolver.solveMaze()
    *
    * @param fName, the name of the maze .txt file
    * @throws FileNotFoundException if the given fName does not appear to exist
    */
    public Maze(String fName) throws FileNotFoundException{
        Scanner input = null;
        String line;
        char c;

        input = new Scanner(new File(fName));
        line = input.nextLine();

        numRows = Integer.valueOf(line.split(" ")[0]);
        numCols = Integer.valueOf(line.split(" ")[1]);

        maze = new MazeGridLocation[numRows][numCols];

        for(int i = 0; i < numRows; i++){
            line = input.nextLine();
            //System.out.println(line);
            for(int j = 0; j < numCols; j++){
                c = line.charAt(j);

                MazeGridLocation curLoc = new MazeGridLocation(i, j, c);
                maze[i][j] = curLoc;

                if(c == 'o'){
                    startLoc = (MazeGridLocation)curLoc;
                    curLoc.setBackgroundColor(Color.green);                 
                }

                if(c == '*'){
                    goalLoc = (MazeGridLocation)curLoc;
                    curLoc.setBackgroundColor(Color.red);                   
                }

                if(c == '#'){
                    curLoc.setBackgroundColor(Color.black);
                }
            }
        }
        
        input.close();
    }

    /**
    * @return int, the number of columns in this maze
    */
    public int getNumColumns(){
        return numCols;
    }

    /**
    * @return int, the number of rows in this maze
    */
    public int getNumRows(){
        return numRows;
    }

    /**
    * @return MazeGridLocation, the starting location in this maze
    */
    public MazeGridLocation getStartLocation(){
        return startLoc;
    }


    /**
    * @return MazeGridLocation, the goal location in this maze
    */
    public MazeGridLocation getGoalLocation(){
        return goalLoc;
    }

    /**
    * Access / read / get / retrieve a MazeGridLocation from this maze
    * @param i, specify the row index of the MazeGridLocation
    * @param j, specify the column index of the MazeGridLocation
    *
    * @return MazeGridLocation, the Location at position (i,j)
    */
    public MazeGridLocation getLocation(int i, int j){
        return maze[i][j];
    }


    /**
    * Query if the Location matching target has been visited
    *
    * @param target, the location to query.  This location must be logically equivalent to some location in the Maze (containing the same row and column index numbers)
    *
    * @return boolean, true if the matching location has been visited, false otherwise
    */
    public boolean isVisited(MazeGridLocation target){
        return (maze[target.getRow()][target.getColumn()]).visited ;
    }

    /**
    * Mark the location matching the provided target as visited
    *
    * @param target, the target the location to mark.  This location must be logically equivalent to some location in the Maze (containing the same row and column index numbers).  The location in the maze will be marked
    *
    */
    public void markVisited(MazeGridLocation target){
        (maze[target.getRow()][target.getColumn()]).visited = true;
    }

    /**
    * Get a list of locations neighboring the provided target location.
    *
    * @param target, the target location which is used to return neighbors of.  The method will return a list of neighbors from the maze surrounding the provided target which are (a) not walls and (b) exist in the confines of the maze
    *
    * @return ArrayList, The list of neighbors
    */ 
    public ArrayList<MazeGridLocation> getOpenNeighbors(MazeGridLocation target){
        // Small number of these, so it's not too big
        // of a deal.
        // Although we will be re-allocating (to add items)
        // we will do at MOST four re-allocations.

        ArrayList<MazeGridLocation> neighbors = new ArrayList<MazeGridLocation>(4);

        int r = target.getRow();
        int c = target.getColumn();


        // Could be a loop; whatever
        //up / NORTH
        carefulAdd(r-1, c, neighbors);

        // down / SOUTH
        carefulAdd(r+1, c, neighbors);

        // right / EAST
        carefulAdd(r, c+1, neighbors);

        // left / WEST
        carefulAdd(r, c-1, neighbors);

        return neighbors;

    }

    private void carefulAdd(int r, int c, ArrayList<MazeGridLocation> l){
        if(  (0 <= r && r < this.numRows) && (0 <= c && c < this.numCols) ){
            if(!maze[r][c].isWall()){
                l.add((MazeGridLocation)maze[r][c]);
            }
        }
    }

    /**
    * ToString method
    * @return String, the String representation of the maze
    */
    public String toString(){
        String s = "";
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){

                MazeGridLocation spot = maze[i][j];
                if(spot.isWall()){
                    s = s + "#";
                } else if(spot.equals(startLoc)){
                    s = s + "o";
                } else if(spot.equals(goalLoc)){
                    s = s + "*";
                } else {
                    s = s + ".";
                }
            }
            s = s + "\n";
        }
        return s;
    }
}