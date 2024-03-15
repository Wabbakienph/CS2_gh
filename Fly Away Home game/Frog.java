import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;



/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog
{
    protected static final String imgFile = "frog.png";

    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
        location = loc; // a single grid location = world[i][j] in FlyWorld.java 
        world = fw;     // a FlyWorld instance

        try
        {
            image = ImageIO.read(new File(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setFrog(this); 
    }

    /**
     * @return BufferedImage the image of the frog
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation the location of the frog
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always true
     */
    public boolean isPredator()
    {
    return true;
    }

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Frog in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }

    /**
     * Generates a list of <strong>ALL</strong> possible legal moves<br>
     * for a frog.<br>
     * You should select all possible grid locations from<br>
     * the <strong>world</strong> based on the following restrictions<br>
     * Frogs can move one space in any of the four cardinal directions but<br>
     * 1. Can not move off the grid<br>
     * 2. Can not move onto a square that already has frog on it<br>
     * GridLocation has a method to help you determine if there is a frog<br>
     * on a location or not.<br>
     *
     * @return GridLocation[] a collection of legal grid locations from<br>
     * the <strong>world</strong> that the frog can move to
     */
    public GridLocation[] generateLegalMoves() {
        GridLocation curr = this.getLocation();
        int row = curr.getRow();
        int col = curr.getCol();

        // Generate an array of directions a frog can go {row,col}
        int[][] directions = {
            {-1,0}, // north
            {1,0}, // south
            {0,1}, // east
            {0,-1} // west
        };

        GridLocation[] legalMoves = new GridLocation[4];
        int count = 0; // counter for legalMoves array

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];            

            // check if the frog moves off the grid
            if (world.isValidLoc(newRow, newCol)) {
                GridLocation newLoc = world.getLocation(newRow, newCol);

                // Check if the and if not add location to
                if (!newLoc.hasPredator()) {
                    legalMoves[count++] = newLoc;
                }
            }
        }

        // Trim the legalMoves array to remove null elements (if any)
        legalMoves = Arrays.copyOf(legalMoves, count);
        
        return legalMoves; // THIS LINE IS JUST SO CODE COMPILES
    }

    /**
     * This method updates the frog's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the frog's location to the chosen updated location.
     */
    public void update() {   
        GridLocation[] legals = this.generateLegalMoves();

        if (legals.length > 0) {
            Random rand = new Random();
            int x = rand.nextInt((legals.length));

            // Remove the frog from its current location
            GridLocation curr = this.getLocation();
            curr.removeFrog();

            location = legals[x];
            location.setFrog(this);
        } else {
            System.out.println("No legal moves.");}
        }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        GridLocation flyNow = world.getFlyLocation();
        GridLocation frogNow = this.getLocation();

        // Check if the frog is on the same location as the fly
        if (frogNow.equals(flyNow)) {
            return true;
        }

        // Check if the frog is one space away from the fly in any cardinal direction
        int frogRow = frogNow.getRow();
        int frogCol = frogNow.getCol();
        int flyRow = flyNow.getRow();
        int flyCol = flyNow.getCol();

        if ((Math.abs(frogRow - flyRow) == 1 && frogCol == flyCol) ||   // Check north/south
            (Math.abs(frogCol - flyCol) == 1 && frogRow == flyRow)) {   // Check east/west
            return true;
        }

        return false; // THIS LINE IS JUST SO CODE COMPILES
    } 
    
    public static void main(String[] args) {
        System.out.println();
    }
}
