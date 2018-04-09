import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the menu where the players enter there names
 */
public class ConfigMenu extends MouseAdapter{

        private int nPlayers;
        private Game game;
        public int players;
        private Scanner input = new Scanner(System.in);
        private ArrayList<String> names = new ArrayList<>();
        private int counter = 0;

    /**
     * The constructor of the class
     * @param game is the current game
     */
    public ConfigMenu(Game game)
       {
           this.game = game;

       }

    /**
     * Locate the mouse on the frame and checks if the player want to continue
     * @param e is the event (if the mouse is pressed)
     */
    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if(MouseOver(mx, my, 460, 500, 100, 50))
        {
            if (names.size() == players)
            {
                game.gamestate = STATE.Game;
                game.loadMap(game.map);
            }
        }
    }

    /**
     *
     * @param mx the current horizontal location of the mouse pointer
     * @param my the current vertical location of the mouse pointer
     * @param x the horizontal location where something can be pressed with the mouse pointer
     * @param y the vertical location where something can be pressed with the mouse pointer
     * @param width the width of the object that can be pressed
     * @param hight the hight of the object that can be pressed
     * @return true if the mouse is over a location that can be pressed, else it returns false
     */
    private boolean MouseOver(int mx, int my, int x, int y, int width, int hight) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + hight) {
                return true;
            } else return false;
        } else return false;
    }

    /**
     * Draws the menu
     * @param g is the graphics
     */
        public void render(Graphics g) {
            Font ft = new Font("Comic Sans MS", 1, 50);
            Font ft2 = new Font("Comic Sans MS", 1, 32);


            g.setFont(ft);
            g.setColor(Color.BLUE);
            g.drawString("Configurations", 330, 80);

            //Button
            g.setFont(ft2);
            g.setColor(Color.RED);
            g.drawRect(460,500,100,50);
            g.drawString("PLAY!",467,535);




            for (int i = 0; i < players; i++) {
                g.setColor(Color.RED);
                g.drawString("Player " + (i + 1) + " ID:", 100, (i * 75) + 200);


                if(names.size() > i)
                {
                    g.setColor(Color.BLUE);
                    g.drawString(names.get(i), 320, (i * 75) + 200);

                }
                counter ++;
            }

            if(counter == 2*players) {
                for (int x = 0; x < players; x++) {

                    System.out.println("Player " + (x + 1) + " type in your username :");
                    String tempName = input.nextLine();
                    System.out.println(tempName);
                    names.add(tempName);
                }
            }


        }

    public ArrayList<String> getNames() {
        return names;
    }
}
