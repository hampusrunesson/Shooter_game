import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * This class creates the player
 */
public class Player extends GameObject {

    Driver driver;
    Color color;
    private BufferedImage playerimage[];
    public int hp = 100;
    private String name;
    private Game game;
    private SpriteSheet tankImages;
    private int images = 4;
    private BufferedImage imageToShow;
    private int counter;
    private int updateFrequency = 100;
    private int playerNumber;
    private int coolDown;

    /**
     * The contructor
     * @param x
     * @param y
     * @param id
     * @param driver
     * @param color
     * @param tankImages
     * @param playerNumber
     * @param name
     * @param game
     */
    public Player(int x, int y, ID id, Driver driver, Color color, SpriteSheet tankImages, int playerNumber, String name, Game game, int coolDown)
    {
        super(x, y, id, tankImages, name, coolDown);
        this.coolDown = coolDown;
        this.driver = driver;
        this.color = color;
        this.name = name;
        this.game = game;
        this.tankImages =tankImages;
        this.playerNumber = playerNumber;



        playerimage = new BufferedImage[images];

        for(int i = 0; i < images; i++)
        {
            playerimage[i] = tankImages.grabImage(1 + i, 1+playerNumber,24,24);
        }
        imageToShow = playerimage[1];
    }

    public void tick()
    {

        incCoolDown();

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
        counter++;
        g.setColor(color);
        g.fillRect(x,y,24,24);

        if(vely != 0 || velx != 0) {
            if (velx > 0)
                imageToShow = playerimage[1];
            else if (velx < 0)
                imageToShow = playerimage[3];
            else if (vely > 0)
                imageToShow = playerimage[2];
            else
                imageToShow = playerimage[0];

        }

        g.drawImage(imageToShow, x, y, null);

        int px = 15 + 260*playerNumber;

        g.setColor(Color.gray);
        g.fillRect(px, 565, 200, 32);
        g.setColor(Color.green);
        g.fillRect(px, 565, hp*2, 32);
        g.setColor(Color.BLACK);
        g.drawRect(px, 565, 200, 32);

        g.setColor(Color.white);

        g.drawString(name, px, 560);


    }

    private BufferedImage changeImage(int Counter, BufferedImage image1, BufferedImage image2, BufferedImage lastShownImage)
    {
        if(Counter > updateFrequency )
        {
            if(image1 == lastShownImage)
                return image2;
            else
                return image1;
        }
        return lastShownImage;
    }



    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 24, 24);
    }

    }

