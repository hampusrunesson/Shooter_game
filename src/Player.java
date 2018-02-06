import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Player extends GameObject {

    Driver driver;
    Color color;
    private BufferedImage playerimage[] = new BufferedImage[3];
    public int hp = 100;
    private int numberofplayers;
    private String name;
    private Game game;



    public Player(int x, int y, ID id, Driver driver, Color color, SpriteSheet spriteS, int numberofplayers, String name, Game game)
    {
        super(x, y, id, spriteS, name);
        this.driver = driver;
        this.color = color;
        this.numberofplayers = numberofplayers;
        this.name = name;
        this.game = game;


        playerimage[0] = spriteS.grabImage(7, 1, 24, 24);
        playerimage[1] = spriteS.grabImage(1, 1, 24, 24);
        playerimage[2] = spriteS.grabImage(1, 1, 24, 24);

    }

    public void tick()
    {
        /*if(hp == 0)
        {
            //gamefeed


            driver.removeObject(this);
        }*/

        if(velx != 0 || vely != 0)
        {
            setTempvelx(velx);
            setTempvely(vely);
        }

        x += velx;
        y += vely;

        collision();



    }

    private void collision()
    {
        for(int i = 0; i < driver.object.size(); i++)
        {
            GameObject tempObject = driver.object.get(i);

            if(tempObject.getID() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    x += velx * -1;
                    y += vely * -1;
                }
            }

            if(tempObject.getID() == ID.Bullet)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    hp = hp - 20;
                    driver.removeObject(tempObject);

                    if(hp <= 0)
                    {
                        game.killResults.put(tempObject.getShooterName(), game.killResults.get(tempObject.getShooterName())+1);
                        game.deathResults.put(name, game.deathResults.get(name) + 1);
                        game.playersLeft -= 1;
                        driver.removeObject(this);
                    }
                }
            }


        }
    }

    public void render(Graphics g)
    {
        //player rendering
        //g.drawImage(playerimage[0], x, y, null);
        g.fillRect(x,y,20,20);

        int px = 15 + 275*numberofplayers;

        g.setColor(Color.gray);
        g.fillRect(px, 565, 200, 32);
        g.setColor(Color.green);
        g.fillRect(px, 565, hp*2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(px, 565, 200, 32);

        g.setColor(Color.white);

        g.drawString(name, px, 560);


    }



    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 24, 24);
    }


}
