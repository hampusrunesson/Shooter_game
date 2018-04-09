import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *This class creates a menu and are launching the correct map,
 * depending how many players that are playing
 */
public class Menu extends MouseAdapter
{
    private Game game;
    private Driver driver;
    private ImageLoader loader;

    /**
     * The constructor
     *
     * @param game is the active game instance
     * @param driver is the active driver instance
     * @param loader is the class that loads the correct map
     */
    public Menu(Game game, Driver driver, ImageLoader loader)
    {
        this.game = game;
        this.driver = driver;
        this.loader = loader;

    }

    /**
     * This method checks if the mouse is pressed at a valid location
     * @param e is the MouseEvent
     */
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if(MouseOver(mx, my, 350, 100, 300, 84))
        {

            game.gamestate = STATE.ConfigMenu;
            game.map = loader.loadimage("map/map2pl.png");
            game.cfg.players = game.playersLeft = 2;


            //game.loadMap(game.map);
        }
        if(MouseOver(mx, my, 325, 250, 350, 84))
        {
            game.gamestate = STATE.ConfigMenu;
            game.map = loader.loadimage("map/map3pl.png");
            game.cfg.players = 3;

        }
        if(MouseOver(mx, my, 325, 400, 350, 84))
        {
            game.gamestate = STATE.ConfigMenu;
            game.map = loader.loadimage("map/map4pl.png");
            game.cfg.players = 4;
        }

    }

    /**
     * Not using this
     * @param e
     */
    public void mouseReleased(MouseEvent e) {}

    /**
     * The method checks if the mouse is pressed over a valid location
     *
     * @param mx is horizontal position of mouse pointer
     * @param my is vertical position of mouse pointer
     * @param x is a valid horizontal location to press on
     * @param y is a valid vertical location to press on
     * @param width is the width of the valid location
     * @param hight is the hight of the valid location
     * @return true or false whether the the location is valid or not
     */
    private boolean MouseOver(int mx, int my, int x, int y, int width, int hight)
    {
        if(mx > x && mx < x + width)
        {
            if(my > y && my < y + hight)
            {
                return true;
            }
            else return false;
        }
        else return false;
    }

    public void tick() {}

    /**
     * This method draws the menu
     * @param g is the graphics
     */
    public void render(Graphics g)
    {

        Font ft = new Font("Comic Sans MS", 1, 50);


        g.setFont(ft);
        g.setColor(Color.BLUE);
        g.drawString("Menu", 430, 80);

        g.setColor(Color.RED);

        g.drawRect(325, 100, 350, 84);
        g.drawString("2 PLAYERS", 350, 160);

        g.setColor(Color.RED);

        g.drawRect(325, 250, 350, 84);
        g.drawString("3 PLAYERS", 350, 310);

        g.setColor(Color.RED);

        g.drawRect(325, 400, 350, 84);
        g.drawString("4 PLAYERS", 350, 460);
    }
}
