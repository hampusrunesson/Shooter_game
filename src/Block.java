import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 * This class creates all the blocks that will surround the map and
 * also the obstacles on the map, the players cannot go through these
 * The class is of type GameObject
 */
public class Block extends GameObject {

    private BufferedImage blockimage;

    /**
     *
     * @param x is the horizontal position to create the block on
     * @param y is the vertical position to create the block on
     * @param id is the ID of the object, which makes it possible to detect what type of GameObject it is
     * @param spriteS is the spritesheet that we load the image from
     */
    public Block(int x, int y, ID id, SpriteSheet spriteS) {
        super(x, y, id, spriteS, null);

        blockimage = spriteS.grabImage(5, 2, 20, 20);
    }

    /**
     * tick method, doesn't to anything here
     */
    public void tick() {}

    /**
     * Draws the block
     * @param g is the graphics
     */
    public void render(Graphics g)
    {

        g.drawImage(blockimage, x, y, null);
    }

    /**
     *
     * @return the bounds of the block
     */
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 16, 16);
    }

}
