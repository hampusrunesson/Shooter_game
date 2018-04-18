import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates the menu where the players enter there names
 */
public class ConfigMenu extends JPanel{

        public int players;
        private ArrayList<JTextField> fields = new ArrayList<>();
        private ArrayList<String> names = new ArrayList<>();

        public JTextField t1;
        private JButton button;
        private Game game;

        public ConfigMenu(int players, Game game)
        {
            this.players = players;
            this.game = game;
            initTextFields();
        }


        public void initTextFields()
        {
            setLayout(null);
            setBounds(0,0,1010,620);
            setBackground(Color.BLACK);
            Font ft = new Font("Comic Sans MS", 1, 32);

            for(int i = 0; i < players; i++)
            {
                t1 = new JTextField();
                t1.setFont(ft);

                t1.setBounds(300,200 + (i*75),200,50);
                t1.setBackground(Color.BLACK);
                t1.setForeground(Color.BLUE);
                t1.setBorder(BorderFactory.createLineBorder(Color.RED,2));
                add(t1);
                fields.add(t1);
            }
                button = new JButton("PLAY!");
                button.setBounds(405,500, 150,50);
                button.setFont(ft);
                button.setBackground(Color.BLUE);
                add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(JTextField f: fields)
                        {
                            if(f.getText().equals(""))
                            {
                                names.add("Player "+ ((fields.indexOf(f)+1)) % 10);
                                continue;
                            }
                            names.add(f.getText());
                            System.out.println(f.getText());
                        }
                        game.gamestate = STATE.Game;
                        game.initializeGame();
                    }
                });





        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Font ft = new Font("Comic Sans MS", 1, 50);
            Font ft2 = new Font("Comic Sans MS", 1, 32);


            g.setFont(ft);
            g.setColor(Color.BLUE);
            g.drawString("Configurations", 330, 80);

            //Button
            g.setFont(ft2);

            for(int i = 0; i < players; i++)
            {
                g.drawString("Player " + (i+1) + " ID:", 60, 240 + (i*75));
            }
        }

    public ArrayList<String> getNames() {
        return names;
    }
}
