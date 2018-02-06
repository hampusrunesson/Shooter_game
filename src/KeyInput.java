import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class KeyInput extends KeyAdapter {

    private Driver driver;
    private SpriteSheet spriteS;
    private int reloadTime;
    private Game game;


    public KeyInput(Driver driver, SpriteSheet spriteS, Game game, int reloadTime)
    {
        this.driver = driver;
        this.spriteS = spriteS;
        this.reloadTime = reloadTime;
        this.game = game;

    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();



        for(int i = 0; i < driver.object.size(); i++)
        {

            GameObject tempObject = driver.object.get(i);

            if(tempObject.getID() == ID.Player1)
            {//key event for player 1


                if(key == KeyEvent.VK_W) tempObject.setVely(-3);
                if(key == KeyEvent.VK_S) tempObject.setVely(3);
                if(key == KeyEvent.VK_D) tempObject.setVelx(3);
                if(key == KeyEvent.VK_A) tempObject.setVelx(-3);
                if(key == KeyEvent.VK_SPACE && game.getT1() > reloadTime)
                {
                    game.setT1(0);

                    int bx = 5;
                    int by = 5;
                    if(tempObject.getTempvelx() > 0) bx = 30;
                    if(tempObject.getTempvelx() < 0) bx = -15;
                    if(tempObject.getTempvely() > 0) by = 30;
                    if(tempObject.getTempvely() < 0) by = -15;

                    driver.addObject(new Bullet(tempObject.getX() + bx, tempObject.getY() + by, ID.Bullet, driver, tempObject, spriteS, tempObject.getTempvelx(), tempObject.getTempvely(), tempObject.getShooterName()));
                }
            }

            if(tempObject.getID() == ID.Player2)
            {//key event for player 1
                if(key == KeyEvent.VK_UP) tempObject.setVely(-3);
                if(key == KeyEvent.VK_DOWN) tempObject.setVely(3);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelx(3);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelx(-3);

                if(key == KeyEvent.VK_NUMPAD0 && game.getT2() > reloadTime)
                {
                    game.setT2(0);

                    int bx = 5;
                    int by = 5;
                    if(tempObject.getTempvelx() > 0) bx = 30;
                    if(tempObject.getTempvelx() < 0) bx = -15;
                    if(tempObject.getTempvely() > 0) by = 30;
                    if(tempObject.getTempvely() < 0) by = -15;
                    driver.addObject(new Bullet(tempObject.getX() + bx, tempObject.getY() + by, ID.Bullet, driver, tempObject, spriteS, tempObject.getTempvelx(), tempObject.getTempvely(), tempObject.getShooterName()));
                }
            }

            if(tempObject.getID() == ID.Player3)
            {//key event for player 1
                if(key == KeyEvent.VK_T) tempObject.setVely(-3);
                if(key == KeyEvent.VK_G) tempObject.setVely(3);
                if(key == KeyEvent.VK_H) tempObject.setVelx(3);
                if(key == KeyEvent.VK_F) tempObject.setVelx(-3);
                if(key == KeyEvent.VK_V && game.getT3() > reloadTime)
                {
                    game.setT3(0);

                    int bx = 5;
                    int by = 5;
                    if(tempObject.getTempvelx() > 0) bx = 30;
                    if(tempObject.getTempvelx() < 0) bx = -15;
                    if(tempObject.getTempvely() > 0) by = 30;
                    if(tempObject.getTempvely() < 0) by = -15;

                    driver.addObject(new Bullet(tempObject.getX() + bx, tempObject.getY() + by, ID.Bullet, driver, tempObject, spriteS, tempObject.getTempvelx(), tempObject.getTempvely(),tempObject.getShooterName()));
                }
            }

            if(tempObject.getID() == ID.Player4)
            {//key event for player 1
                if(key == KeyEvent.VK_I) tempObject.setVely(-3);
                if(key == KeyEvent.VK_K) tempObject.setVely(3);
                if(key == KeyEvent.VK_L) tempObject.setVelx(3);
                if(key == KeyEvent.VK_J) tempObject.setVelx(-3);
                if(key == KeyEvent.VK_N && game.getT4() > reloadTime)
                {
                    game.setT4(0);

                    int bx = 5;
                    int by = 5;
                    if(tempObject.getTempvelx() > 0) bx = 30;
                    if(tempObject.getTempvelx() < 0) bx = -15;
                    if(tempObject.getTempvely() > 0) by = 30;
                    if(tempObject.getTempvely() < 0) by = -15;

                    driver.addObject(new Bullet(tempObject.getX() + bx, tempObject.getY() + by, ID.Bullet, driver, tempObject, spriteS, tempObject.getTempvelx(), tempObject.getTempvely(), tempObject.getShooterName()));
                }
            }

        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int i = 0; i < driver.object.size(); i++)
        {
            GameObject tempObject = driver.object.get(i);

            if(tempObject.getID() == ID.Player1)
            {//key event for player 1
                if(key == KeyEvent.VK_W) tempObject.setVely(0);
                if(key == KeyEvent.VK_S) tempObject.setVely(0);
                if(key == KeyEvent.VK_D) tempObject.setVelx(0);
                if(key == KeyEvent.VK_A) tempObject.setVelx(0);
            }
            if(tempObject.getID() == ID.Player2)
            {//key event for player 2
                if(key == KeyEvent.VK_UP) tempObject.setVely(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVely(0);
                if(key == KeyEvent.VK_RIGHT) tempObject.setVelx(0);
                if(key == KeyEvent.VK_LEFT) tempObject.setVelx(0);
            }
            if(tempObject.getID() == ID.Player3)
            {
                if(key == KeyEvent.VK_T) tempObject.setVely(0);
                if(key == KeyEvent.VK_G) tempObject.setVely(0);
                if(key == KeyEvent.VK_H) tempObject.setVelx(0);
                if(key == KeyEvent.VK_F) tempObject.setVelx(0);
            }
            if(tempObject.getID() == ID.Player4)
            {//key event for player 1
                if(key == KeyEvent.VK_I) tempObject.setVely(0);
                if(key == KeyEvent.VK_K) tempObject.setVely(0);
                if(key == KeyEvent.VK_L) tempObject.setVelx(0);
                if(key == KeyEvent.VK_J) tempObject.setVelx(0);
            }
        }

    }
}
