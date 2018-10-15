package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MandelbrotMain {
    private static final Dimension CANVAS_DIMENSION = new Dimension(1600, 900);
    private static final int INITIAL_BORDER = 100;

    public static void main(String[] argv) {
        JFrame frame = new JFrame("Mandelbrot");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        MandelbrotApplet applet = new MandelbrotApplet(CANVAS_DIMENSION, INITIAL_BORDER);
        applet.buildUI();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
