import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConfigMenu extends MouseAdapter{

        private int nPlayers;
        private Game game;
        public int players;
        private Scanner input = new Scanner(System.in);
        private ArrayList<String> names = new ArrayList<>();
        private int counter = 0;


    public ConfigMenu(Game game)
       {
           this.game = game;

       }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if(MouseOver(mx, my, 460, 500, 100, 50))
        {
            game.gamestate = STATE.Game;
            game.loadMap(game.map);
        }
    }


    private boolean MouseOver(int mx, int my, int x, int y, int width, int hight) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + hight) {
                return true;
            } else return false;
        } else return false;
    }

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

                    names.add(tempName);
                }
            }


        }

    public ArrayList<String> getNames() {
        return names;
    }
}
