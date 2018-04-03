import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends MouseAdapter
{
    private Game game;
    private Driver driver;
    private ImageLoader loader;


    public Menu(Game game, Driver driver, ImageLoader loader)
    {
        this.game = game;
        this.driver = driver;
        this.loader = loader;

    }

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
            game.map = loader.loadimage("map/map2pl.png");
            game.cfg.players = 3;

        }
        if(MouseOver(mx, my, 325, 400, 350, 84))
        {
            game.gamestate = STATE.ConfigMenu;
            game.map = loader.loadimage("map/map2pl.png");
            game.cfg.players = 4;
        }

    }

    public void mouseReleased(MouseEvent e)
    {

    }

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

    public void tick()
    {

    }

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
