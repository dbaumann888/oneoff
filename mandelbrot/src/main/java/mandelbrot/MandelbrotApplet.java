package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.DataBuffer;

public class MandelbrotApplet extends JApplet {
    private final MandelbrotSet mandelbrotSet;
    private final Dimension initialCanvasDimension;
    private MandelbrotCanvas canvas;

    public MandelbrotApplet(Dimension canvasDim, int initialBorder) {
        this.mandelbrotSet = new MandelbrotSet(0.0d, 0.0d, canvasDim.width - (2 * initialBorder), 1000);
        this.initialCanvasDimension = canvasDim;
        this.canvas = null;
    }

    private void zoom(double multiplier) {
        this.mandelbrotSet.zoom(multiplier);
    }

    private void render() {
        DataBuffer data = this.canvas.getPixelData();
        Dimension canvasDim = this.canvas.getDimension();
        final int granularity = this.mandelbrotSet.getGranularity();
        for (int x = 0; x < canvasDim.width; ++x) {
            for (int y = 0; y < canvasDim.height; ++y) {
                int divergenceRate = mandelbrotSet.getDivergenceRate(new Dimension(x, y));
                int colorInt = mapColor(divergenceRate, granularity);
                data.setElem((x * canvasDim.height) + y, colorInt);
            }
        }
    }

    private int mapColor(int divergenceRate, int granularity) {
        return (int)(divergenceRate * 255 / (double)granularity);
    }

    public void buildUI() {
        this.canvas = new MandelbrotCanvas(this.mandelbrotSet, this.initialCanvasDimension);
        this.render();
        add("North", this.canvas);

        final MandelbrotApplet applet = this;

        JButton zoomInButton = new JButton("+");
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                applet.zoom(0.9d);
                applet.render();
                applet.canvas.repaint();
            }
        });
        add("Center", zoomInButton);

        JButton zoomOutButton = new JButton("-");
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                applet.zoom(1.1d);
                applet.render();
                applet.canvas.repaint();
            }
        });
        add("South", zoomOutButton);
    }
}
