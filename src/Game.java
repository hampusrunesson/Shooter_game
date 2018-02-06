import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game extends Canvas implements Runnable
{


    private static final long serialVersionUID = 1L;

    private boolean running = false;
    private Thread thread;
    private Driver driver;
    private SpriteSheet spriteS;
    private Menu menu;
    public ConfigMenu cfg;
    private ScoreUpdater scoreBoard;
    public HashMap<String,Integer> killResults = new HashMap<>();
    public HashMap<String,Integer> deathResults = new HashMap<>();


    public BufferedImage map = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public boolean decider = false;
    private ArrayList<String> names;
    public int playersLeft;

    private int reloadTime = 25, t1 = 25, t2 = 25, t3 = 25, t4 = 25;



    public STATE gamestate = STATE.Menu;

    public Game()
    {

        new Window(1010, 620, "GAME OF DEEEEACKS", this);
        start();
        driver = new Driver();
        ImageLoader loader = new ImageLoader();
        this.addKeyListener(new KeyInput(driver, spriteS, this, reloadTime));
        menu = new Menu(this, driver, loader);
        this.addMouseListener(menu);
        cfg = new ConfigMenu(this);
        this.addMouseListener(cfg);


        sprite_sheet = loader.loadimage("map/qwert.png");

        spriteS = new SpriteSheet(sprite_sheet);
        floor = spriteS.grabImage(4, 2, 20, 20);

        //Detta kanske inte behövs????
        if(gamestate == STATE.Game)
        {
            loadMap(map);
        }



    }

    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop()
    {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1 )
            {
                tick();
                delta--;
            }
            if(running)

                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;

            }
        }
        stop();
    }

    public void tick()
    {
        driver.tick();

        if(gamestate == STATE.Game)
        {

            if(t1 <= reloadTime) {
                t1++;
            }
            if(t2 <= reloadTime)
                t2++;
            if(t3 <= reloadTime)
                t3++;
            if(t4 <= reloadTime)
                t4++;

            if(playersLeft == 1)
            {
                playersLeft -= 1;

                scoreBoard = new ScoreUpdater(this,killResults,deathResults);
            }
        }
        else if(gamestate == STATE.Menu)
        {
             menu.tick();
        }
        else if(gamestate == STATE.ConfigMenu)
        {

        }
    }

    public void render()
    {


            BufferStrategy bs = this.getBufferStrategy();
            if (bs == null) {
                this.createBufferStrategy(3);
                return;
            }

            Graphics g = bs.getDrawGraphics();

            g.setColor(Color.black);
            g.fillRect(0, 0, 1010, 620);

            if (gamestate == STATE.Game) {
                //allt här mellan ritas ut


                for (int xx = 0; xx < 32 * 32; xx += 20) {
                    for (int yy = 0; yy < 32 * 16; yy += 20) {
                        g.drawImage(floor, xx, yy, null);
                    }
                }

            }

            else if (gamestate == STATE.Menu) {
                menu.render(g);

            }

            else if (gamestate == STATE.ConfigMenu) {
                cfg.render(g);
            }
            //
            driver.render(g);

            g.dispose();
            bs.show();


    }

    //loading map

    public void loadMap(BufferedImage image)
    {
        names = cfg.getNames();
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < w; xx++)
        {
            for(int yy = 0; yy < h; yy++)
            {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                int white = (pixel) & 0xff;
                int yellow = (pixel >> 16) & 0xff;


                if(red == 249)
                    driver.addObject(new Block(xx*16, yy*16, ID.Block, spriteS));

                if(blue == 249) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player1, driver, Color.blue, spriteS, 0, names.get(0), this));
                    killResults.put(names.get(0), 0);
                    deathResults.put(names.get(0), 0);


                }

                if(green == 222) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player2, driver, Color.green, spriteS, 1, names.get(1), this));
                    killResults.put(names.get(1), 0);
                    deathResults.put(names.get(1), 0);


                }

                if(white == 255) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player3, driver, Color.white, spriteS, 2, names.get(2), this));
                    killResults.put(names.get(2), 0);
                    deathResults.put(names.get(2), 0);


                }

                if(yellow == 252) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player4, driver, Color.yellow, spriteS, 3, names.get(3), this));
                    killResults.put(names.get(3), 0);
                    deathResults.put(names.get(3), 0);


                }

            }
        }
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT4() {
        return t4;
    }

    public void setT4(int t4) {
        this.t4 = t4;
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
