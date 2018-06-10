package sample.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeatMapColors {

    private static Color getColorForValue(double value) {
        float zakres = (float)Parameters.getStartingMaximum() - Parameters.getStopCriteria();
        float proportion = 240 / zakres;
        float hue = (zakres - ((float)value - (float)Parameters.getStopCriteria()))* proportion;
        return Hsv_rgb.hsv2rgb(hue);
    }

    public static BufferedImage createColorScaleImage() {
        BufferedImage bufferedImage = new BufferedImage(Matrix.getRows(), Matrix.getColumns(), BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < Matrix.getRows(); x++) {
            for (int y = 0; y < Matrix.getColumns(); y++) {
                double value = Matrix.getCellTemperature(x, y);
                Color color = getColorForValue(value);
                bufferedImage.setRGB(y,x, color.getRGB());
            }
        }
        return bufferedImage;
    }

    public static void saveImage(BufferedImage bufferedImage, String filepath) {
        File imageFile = new File(filepath);
        try {
            ImageIO.write(bufferedImage, "PNG", imageFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
