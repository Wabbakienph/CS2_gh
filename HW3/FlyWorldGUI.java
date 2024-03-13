import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;

/**
 * Handles all the work to setup the GUI<br>
 * and dealing with keystrokes, specifically the arrows<br>
 *
 * DO NOT modify any code in this file
 *
 */
public class FlyWorldGUI extends JPanel
{
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;

    public static final int ATHOME = 0;
    public static final int EATEN = 1;
    public static final int NOACTION =-1;

    private GridBagConstraints gbc;

    private FlyWorld world;

    private Action moveNorth = new AbstractAction("NORTH")
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        updateWorld(NORTH);
    }
    };

    private Action moveSouth = new AbstractAction("SOUTH")
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        updateWorld(SOUTH);
    }
    };
    
    private Action moveEast = new AbstractAction("EAST")
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        updateWorld(EAST);
    }
    };

    private Action moveWest = new AbstractAction("WEST")
    {
    @Override
    public void actionPerformed(ActionEvent e)
    {
        updateWorld(WEST);
    }
    };

    /**
     * Initializes some GUI pieces like keystroke handling<br>
     * and calls FlyWorld to create the world based on a given<br>
     * file
     *
     * @param fileName the file containing the description of the grid
     */
    public FlyWorldGUI(String fileName)
    {
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "NORTH");
    getActionMap().put("NORTH", moveNorth);

    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "SOUTH");
    getActionMap().put("SOUTH", moveSouth);

    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "WEST");
    getActionMap().put("WEST", moveWest);

    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "EAST");
    getActionMap().put("EAST", moveEast);

    world = new FlyWorld(fileName);
    gbc = new GridBagConstraints();
    setLayout(new GridBagLayout());

    for (int row = 0; row < world.getNumRows(); row++)
    {
        for (int col = 0; col <  world.getNumCols(); col++)
        {
        gbc.gridx = col;
        gbc.gridy = row;
        add(world.getLocation(row, col), gbc);
        }
    }
    }

    /**
     * Called when one of the arrow keys is pressed<br>
     * Moves the fly and checks it's status<br>
     * Then moves all predators and checks fly's<br>
     * status again.
     *
     * @param direction the direction to try to move the fly
     */
    public void updateWorld(int direction)
    {
    int result = world.moveFly(direction);
    if (result == ATHOME)
    {
        flyGotHome();
        System.exit(0);
    }

    if (result == EATEN)
    {
        flyGotEaten();
        System.exit(0);
    }

    if (world.movePredators())
    {
        flyGotEaten();
        System.exit(0);
    }

    // This is necessary in case the student's
    // code takes a long time for one reason or another
    this.repaint(); 
    }
    
    private void flyGotHome()
    {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
    JOptionPane.showMessageDialog(frame,
                      "Yeah, Mosca made it home safely",
                      "Hooray",
                      JOptionPane.PLAIN_MESSAGE);
    }

    private void flyGotEaten()
    {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
    JOptionPane.showMessageDialog(frame,
                      "Oh no, Mosca got eaten",
                      "Too bad. Try again",
                      JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String [] args)
    {
    JFrame mainFrame = new JFrame("Fly Away Home");
    FlyWorldGUI w = new FlyWorldGUI(args[0]);

    mainFrame.getContentPane().add("Center", w);
    w.setFocusable(true);
    w.requestFocusInWindow();

    mainFrame.setSize(600, 600);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setVisible(true);
    }
}
