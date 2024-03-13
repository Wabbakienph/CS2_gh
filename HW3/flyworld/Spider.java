import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class Spider {
    protected static final String imgFile = "spider.png";

    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    public Spider(GridLocation loc, FlyWorld fw) {
        location = loc;
        world = fw;

        try {
            image = ImageIO.read(new File(imgFile));
        } catch (IOException ioe) {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setSpider(this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public GridLocation getLocation() {
        return location;
    }

    public boolean isPredator() {
        return true;
    }

    public String toString() {
        return "Spider in world: " + world + " at location (" + location.getRow() + ", " + location.getCol() + ")";
    }
    
    public GridLocation[] generateLegalMoves() {
        // get the spider's location 
        GridLocation curr = this.getLocation();
        int srow = curr.getRow();
        int scol = curr.getCol();
        
        // get Mosca's current location
        GridLocation moscaLoc = world.getFlyLocation();
        int moscaRow = moscaLoc.getRow();
        int moscaCol = moscaLoc.getCol();

        // Calculate the direction towards the fly
        int deltaRow = Integer.compare(moscaRow, srow); 
        int deltaCol = Integer.compare(moscaCol, scol);

        GridLocation[] legalMoves = new GridLocation[2]; // At most, two legal moves towards the fly
        int count = 0;

        // Add legal moves towards the fly
        if (deltaRow != 0 && world.isValidLoc(srow + deltaRow, scol)) {
            legalMoves[count++] = world.getLocation(srow + deltaRow, scol);
        }
        if (deltaCol != 0 && world.isValidLoc(srow, scol + deltaCol)) {
            legalMoves[count++] = world.getLocation(srow, scol + deltaCol);
        }

        // Trim the array to remove null elements
        return Arrays.copyOf(legalMoves, count);
    }

    public void update() {
        GridLocation[] legalMoves = generateLegalMoves();
        Random rand = new Random();
        int x = rand.nextInt((legalMoves.length));

        if (!legalMoves[x].hasPredator()) {
            // Remove the frog from its current location
            GridLocation curr = this.getLocation();
            curr.removeSpider();

            location = legalMoves[x];
            location.setSpider(this);
        }
    }
    

    public boolean eatsFly() {
        GridLocation moscaLoc = world.getFlyLocation();
        return location.equals(moscaLoc); // check if mosca and spider are on the same square
    }
}