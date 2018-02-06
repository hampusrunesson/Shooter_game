import java.awt.*;

import javax.swing.*;


public class Window {

    JFrame frame;

    public Window(int width, int hight, String title, Game game)
    {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, hight));
        frame.setMaximumSize(new Dimension(width, hight));
        frame.setMinimumSize(new Dimension(width, hight));



        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
