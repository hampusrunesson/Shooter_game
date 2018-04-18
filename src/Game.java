import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class is the game itself, and runns the game, contains the game loop etc.
 */
public class Game extends Canvas implements Runnable
{


    private static final long serialVersionUID = 1L;


    private boolean running = false;
    private Thread thread;
    private Driver driver;
    private SpriteSheet spriteS;
    public ConfigMenu cfg;
    public String path;
    public Window window;
    public nameMenu menu;
    public HashMap<String,Integer> killResults = new HashMap<>();
    public HashMap<String,Integer> deathResults = new HashMap<>();


    public BufferedImage map = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public int playersLeft;

    private int reloadTime = 50;

    //PlayerImage
    private BufferedImage playerSprite;
    private SpriteSheet loadPlayer;
    private String tankPath = "map/tanks.png";
    private Color playerColor;

    /**
     * The constructor of the class, initializes the driver, creates the window
     * and starts the game.
     */
    public STATE gamestate = STATE.nrPlayersMenu;

    public Game() {

        window = new Window(1010, 620, "test", menu = new nameMenu(this));
        while (gamestate == STATE.nrPlayersMenu) {

        }

    }

    public void initializeGame() {

        if (gamestate == STATE.Game) {
            changePanel(cfg, this);
        }

        start();
        driver = new Driver();
        ImageLoader loader = new ImageLoader();
        this.addKeyListener(new KeyInput(driver, this, reloadTime, 3));

        sprite_sheet = loader.loadimage("map/qwert.png");
        spriteS = new SpriteSheet(sprite_sheet);
        floor = spriteS.grabImage(4, 2, 20, 20);
        map = loader.loadimage(path);

        //player images
        playerSprite = loader.loadimage(tankPath);
        loadPlayer = new SpriteSheet(playerSprite);
        playerColor = new Color(0,0,0,0);

        loadMap(map);
    }




    /**
     * Starts a new thread to run on
     */
    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Combine the thread with the main thread
     */
    private void stop()
    {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is the loop, which makes the game run with a certain amount of cycles per second
     */
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

    /**
     * Tick method of the game
     */
    public void tick()
    {
        driver.tick();

        if(gamestate == STATE.Game)
        {
            if(playersLeft == 1)
            {
                playersLeft -= 1;

                new ScoreUpdater(this,killResults,deathResults);
            }
        }
    }

    /**
     * Runs the different render methods depending on which state the game is in.
     */
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
            //
            driver.render(g);

            g.dispose();
            bs.show();


    }


    /**
     * This method draw blocks and creates the players depending on the colors
     * of the picture. This method use RGB to draw or create the right thing on the
     * right spot on the field.
     *
     * @param image is a drawn image of the map (PNG)
     */
    public void loadMap(BufferedImage image)
    {
       // names = cfg.getNames();
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
                int yellow = (pixel >> 8) & 0xff;

                if(red == 249)
                    driver.addObject(new Block(xx*16, yy*16, ID.Block, spriteS));

                if(blue == 249) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player1, driver, playerColor, loadPlayer, 0, cfg.getNames().get(0), this,0));
                    killResults.put(cfg.getNames().get(0), 0);
                    deathResults.put(cfg.getNames().get(0), 0);


                }

                if(green == 222) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player2, driver, playerColor, loadPlayer, 1, cfg.getNames().get(1), this,0));
                    killResults.put(cfg.getNames().get(1), 0);
                    deathResults.put(cfg.getNames().get(1), 0);


                }

                if(white == 255) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player3, driver, playerColor, loadPlayer, 2, cfg.getNames().get(2), this,0));
                    killResults.put(cfg.getNames().get(2), 0);
                    deathResults.put(cfg.getNames().get(2), 0);


                }

                if(yellow == 252) {
                    driver.addObject(new Player(xx * 16, yy * 16, ID.Player4, driver, playerColor, loadPlayer, 3, cfg.getNames().get(3), this,0));
                    killResults.put(cfg.getNames().get(2), 0);
                    deathResults.put(cfg.getNames().get(2), 0);



                }

            }
        }
    }

    public void changePanel(Component removePanel, Component addPanel)
    {
        window.frame.getContentPane().remove(removePanel);
        window.frame.add(addPanel);
        window.frame.revalidate();
        window.frame.repaint();
    }
    /**
     * The main method of the project which only creates the game.
     * @param args
     */

    public static void main(String[] args)
    {
        new Game();
        //new Window(1010, 620, "gsggsd", new ConfigMenu(), null);








    }


}
