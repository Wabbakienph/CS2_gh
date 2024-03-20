import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents a single grid location (i.e., one row/column position)
 * Within a 2D grid in a Graphical User Interface (GUI)
 */
public class GridLocation extends JPanel
{
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    
    private int row;
    private int col;

    private Color backgroundColor;

    private Fly fly;
    private Frog frog;
    private Spider spider;

    /**
     * Creates a new GridLocation at a specific location within the grid (r = row, c = column)
     * with a white background color.
     *
     * @param r the row
     * @param c the column
     */
    public GridLocation(int r, int c)
    {
    setBorder(new LineBorder(Color.BLACK));

    row = r;
    col = c;

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
    public int getCol()
    {
    return col;
    }

    /**
     * Changes the background color of this location
     *
     * @param color The color to set this square to (e.g., Color.GREEN)
     */
    public void setBackgroundColor(Color color)
    {
    backgroundColor = color;
    setBackground(backgroundColor);
    repaint();
    }

     /**
     * Adds a fly to this location
     *
     * @param f the Fly to be added
     */
    public void setFly(Fly f)
    {
    fly = f;;
    repaint();
    }

    /**
     * Adds a Frog to this location
     *
     * @param f the frog to be added
     */
    public void setFrog(Frog f)
    {
    frog = f;
    repaint();
    }

    /**
     * Adds a Spider to this location
     *
     * @param f the spider to be added
     */
    public void setSpider(Spider f)
    {
        spider = f;
        repaint();
    }

    /**
     * Removes Fly from this location
     */
    public void removeFly()
    {
    fly = null;
    repaint();
    }
    
    /**
     * Removes Frog from this location
     */
    public void removeFrog()
    {
    frog = null;
    repaint();
    }

    /**
     * Removes Spider from this location
     */
    public void removeSpider()
    {
        spider = null;
        repaint();
    }

    /**
     * Returns true if location contains a
     * predator, false otherwise.<br>
     * Useful because can't have two predators in same spot
     *
     * @return boolean
     */
    public boolean hasPredator()
    {
    return frog != null || spider != null;
    }
 
    /**
     * Determines if two locations represent the same position<br>
     *
     * @param o another object which may or may not be a GridLocation
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
    if (!(o instanceof GridLocation))
        return false;

    GridLocation other = (GridLocation)o;

    return row == other.row && col == other.col;
    }

    /**
     * Simple string representation of a GridLocation<br>
     * May be useful for debugging purposes
     *
     * @return String
     */
    @Override
    public String toString()
    {
    return "(" + row + ", " + col +")";
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

    /**
     * This method actually draws the picture in
     * the location if there is one<br>
     * YOU NEVER NEED TO CALL THIS METHOD
     *
     * @param g a Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics g)
    {
    super.paintComponent(g);
    
    if (fly != null)
        g.drawImage(fly.getImage(), 0, 0, null);
    if (frog != null)
        g.drawImage(frog.getImage(), 0, 0, null);
    if (spider != null) // Added to draw spider if present
        g.drawImage(spider.getImage(), 0, 0, null);
    }
}
