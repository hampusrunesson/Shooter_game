import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class nameMenu extends JPanel{

    private Game game;

    public nameMenu(Game game) {

        this.game = game;
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                int mx = e.getX();
                int my = e.getY();

                if (MouseOver(mx, my, 350, 100, 300, 84)) {

                    game.path = "map/map2pl.png";
                    game.gamestate = STATE.ConfigMenu;
                    game.changePanel(game.menu, game.cfg = new ConfigMenu(2, game));



                }
                if (MouseOver(mx, my, 325, 250, 350, 84)) {

                    game.path = "map/map3pl.png";
                    game.gamestate = STATE.ConfigMenu;
                    game.changePanel(game.menu, game.cfg = new ConfigMenu(3, game));

                }
                if (MouseOver(mx, my, 325, 400, 350, 84)) {

                    game.path = "map/map4pl.png";
                    game.gamestate = STATE.ConfigMenu;
                    game.changePanel(game.menu, game.cfg = new ConfigMenu(4, game));

                }

            }

            /**
             * Not using this
             *
             * @param e
             */
            public void mouseReleased(MouseEvent e) {
            }
        });
    }

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
        private boolean MouseOver ( int mx, int my, int x, int y, int width, int hight)
        {
            if (mx > x && mx < x + width) {
                if (my > y && my < y + hight) {
                    return true;
                } else return false;
            } else return false;
        }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

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
