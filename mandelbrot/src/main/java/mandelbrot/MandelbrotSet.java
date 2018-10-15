package mandelbrot;

import java.awt.*;

public class MandelbrotSet {
    private double xCenter;
    private double yCenter;
    private double zoom;
    private final int granularity;

    public MandelbrotSet(double initialX, double initialY, int initialWidth, int granularity) {
        this.zoom = 3.5d / initialWidth; // mandelbrot x goes from -2.5 to 1.0 -- put left edge at left border
        this.xCenter = initialX - 0.75d;
        this.yCenter = initialY;
        this.granularity = granularity;
    }

    public int getDivergenceRate(Dimension pixel) {
        double x0 = (pixel.width * this.zoom) - this.xCenter;
        double y0 = (pixel.height * this.zoom) - this.yCenter;

        double x = 0.0d, y = 0.0d;
        int i = 0;
        for (; ((x*x) + (y*y) >= 2*2) && (i < this.granularity); ++i) {
            double newX = (x * x) - (y * y) + x0;
            y = 2 * x * y + y0;
            x = newX;
        }
        return i;
    }

    public void zoom(double multiplier) {
        this.zoom *= multiplier;
    }

    public int getGranularity() {
        return this.granularity;
    }
}
