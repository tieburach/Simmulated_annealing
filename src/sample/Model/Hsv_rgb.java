package sample.Model;

import java.awt.*;

public class Hsv_rgb {

    public static Color hsv2rgb(double hue) {
        double sat = 1;
        double val = 255;
        double red = 0, grn = 0, blu = 0;
        double i, f, p, q, t;

        hue /= 60;
        i = Math.floor(hue);
        f = hue - i;
        p = val * (1 - sat);
        q = val * (1 - (sat * f));
        t = val * (1 - (sat * (1 - f)));
        if (i == 0) {
            red = val;
            grn = t;
            blu = p;
        } else if (i == 1) {
            red = q;
            grn = val;
            blu = p;
        } else if (i == 2) {
            red = p;
            grn = val;
            blu = t;
        } else if (i == 3) {
            red = p;
            grn = q;
            blu = val;
        } else if (i == 4) {
            red = t;
            grn = p;
            blu = val;
        } else if (i == 5) {
            red = val;
            grn = p;
            blu = q;
        }
        return new Color ((int)red, (int)grn, (int)blu);
    }

    public static double[] rgb2hsv(double red, double grn, double blu) {
        double hue, sat, val;
        double x, f, i;
        double result[] = new double[3];

        x = Math.min(Math.min(red, grn), blu);
        val = Math.max(Math.max(red, grn), blu);
        if (x == val) {
            hue = 0;
            sat = 0;
        } else {
            f = (red == x) ? grn - blu : ((grn == x) ? blu - red : red - grn);
            i = (red == x) ? 3 : ((grn == x) ? 5 : 1);
            hue = ((i - f / (val - x)) * 60) % 360;
            sat = ((val - x) / val);
        }
        result[0] = hue;
        result[1] = sat;
        result[2] = val;
        return result;
    }
}
