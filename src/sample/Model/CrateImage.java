package sample.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CrateImage {

    public static void saveImage(String filepath) {
        try {
            BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
            File imageFile = new File("image.png");
            ImageIO.write(image, "PNG", imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeImage(Matrix mat){

    }


}
