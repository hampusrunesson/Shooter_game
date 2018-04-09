import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class creates the bullets that the players can shoot,
 * is of type GameObject
 */
public class Bullet extends GameObject
{

    private Driver driver;
    private String shooterName;

    /**
     *
     * @param x horizontal location of the bullet
     * @param y vertical location of the bullet
     * @param id is the ID of the object
     * @param driver the driver that loops through all the GameObjects
     * @param tempObject temporary copy of an GameObject
     * @param spriteS the spritesheet
     * @param tempvelx is the latest horizontal speed that the player had
     * @param tempvely is the  latest vertical speed that the player had
     * @param shooterName the name of the player that fired the bullet
     */
    public Bullet(int x, int y, ID id, Driver driver, GameObject tempObject, SpriteSheet spriteS, float tempvelx, float tempvely,  String shooterName) {
        super(x, y, id, spriteS, shooterName);
        this.driver = driver;
        this.shooterName = shooterName;



        velx = (float) (tempvelx*1.5);
        vely = (float) (tempvely*1.5);
    }

    /**
     * The tick method increases the position of the bullet
     * in the direction that it is traveling.
     * It also deletes the bullet if it collide with a wall or if the speed os zero
     */
    public void tick()
    {

        x += velx;
        y += vely;

        for(int i = 0; i < driver.object.size(); i++)
        {
            GameObject tempObject = driver.object.get(i);

            if(velx == 0 && vely == 0) driver.removeObject(this);

            if(tempObject.getID() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    driver.removeObject(this);
                }
            }
        }
    }

    /**
     * Draws out the bullet
     * @param g is the graphics
     */
    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 8, 8);
    }

    /**
     *
     * @return the bounds of the bullet
     */
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 8, 8 );
    }

    /**
     *
     * @return the name of the player that fired the bullet
     */
    public String getShooterName() {
        return shooterName;
    }
}
