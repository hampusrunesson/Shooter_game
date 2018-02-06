import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject
{

    private Driver driver;
    private String shooterName;

    public Bullet(int x, int y, ID id, Driver driver, GameObject tempObject, SpriteSheet spriteS, float tempvelx, float tempvely,  String shooterName) {
        super(x, y, id, spriteS, shooterName);
        this.driver = driver;
        this.shooterName = shooterName;



        velx = (float) (tempvelx*1.5);
        vely = (float) (tempvely*1.5);
    }

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

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 8, 8);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 8, 8 );
    }

    public String getShooterName() {
        return shooterName;
    }
}
