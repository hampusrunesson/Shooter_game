import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    private BufferedImage blockimage;

    public Block(int x, int y, ID id, SpriteSheet spriteS) {
        super(x, y, id, spriteS, null);

        blockimage = spriteS.grabImage(5, 2, 20, 20);
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {

        g.drawImage(blockimage, x, y, null);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 16, 16);
    }

}
