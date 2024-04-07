import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents a single grid location (i.e., one row/column position)
 * Within a 2D grid in a Graphical User Interface (GUI)
 */
public class MazeGridLocation extends JPanel
{
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    protected Color backgroundColor;
    
    protected int row;
    protected int col;
    protected char character;

    public boolean visited = false;

    /**
     * Creates a new grid position at a specific location within the grid
     * with a white background color
     *
     * @param r, the row
     * @param c, the column
     * @param newCharacter, the character at this location in the maze
     */
    public MazeGridLocation(int r, int c, char newCharacter)
    {
    setBorder(new LineBorder(Color.BLACK));

    row = r;
    col = c;
    character = newCharacter;

    backgroundColor = Color.WHITE;
    setBackground(backgroundColor);
    }

    /**
     * @return int
     */
    public int getRow()
    {
    return row;
    }

    /**
     * @return int
     */
    public int getColumn()
    {
    return col;
    }


    /**
    *
    * @return char, the character at this location
    */
    public boolean isWall(){
        return this.character == '#';
    }


    /**
     * Changes the background color of this location
     *
     * @param color, the color to make this square in the GUI
     */
    public void setBackgroundColor(Color color)
    {
    backgroundColor = color;
    setBackground(backgroundColor);
    repaint();
    }

    /**
     * Simple String representation of this location's row and column
     *
     * @return String as (row, col)
     */
    @Override
    public String toString()
    {
    return "(" + row + ", " + col +")";
    }

    /**
     * Determines if two (presumably MazeGridLocations) are same 
     * That is same row and column.
     *
     * @param o, some other Object (presumably a MazeGridLocation) to compare with
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o)
    {
    if (o == null)
        return false;
    if (o == this)
        return true;
    if (!(o instanceof MazeGridLocation))
        return false;

    MazeGridLocation other = (MazeGridLocation)o;
    return row == other.row && col == other.col;
    }
        
    /**
     * This method is used to help ensure that the size
     * of the grid location is always a very specific size<br>
     * YOU DO NOT NEED TO WORRY ABOUT THIS METHOD OR CALL IT EVER
     */
    @Override
    public Dimension getPreferredSize()
    {
    return new Dimension(WIDTH, HEIGHT);
    }
    
}
