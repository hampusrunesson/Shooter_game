import java.awt.*;

import javax.swing.*;


public class Window {

    JFrame frame;

    public Window(int width, int hight, String title, Component object)
    {
        frame = new JFrame(title);

        frame.setVisible(true);
        frame.setSize(width,hight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(object);
    }
}
