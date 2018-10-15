package mandelbrot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

public class MandelbrotCanvas extends Component {

    private Dimension dimension;
    private BufferedImage img;

    public MandelbrotCanvas(MandelbrotSet mandelbrotSet, Dimension dimension) {
        this.dimension = dimension;
        this.img = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_RGB);
    }

    public DataBuffer getPixelData() {
        return this.img.getRaster().getDataBuffer();
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public Dimension getPreferredSize() {
        return this.dimension;
    }

    public void paint(Graphics g) {
        g.drawImage(this.img, 0, 0, this);
    }
}
