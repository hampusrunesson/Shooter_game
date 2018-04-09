import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;

/**
 * This class reads in the images
 */
public class ImageLoader {

    private BufferedImage image;

    /**
     * This method try to locate the image and retrieve it
     * @param path is the directory of the image
     * @return the image
     */
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
