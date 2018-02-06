import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;

public class ImageLoader {

    private BufferedImage image;

    public BufferedImage loadimage(String path)
    {
        try {
            image = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


}
