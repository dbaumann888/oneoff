package arty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ArtyMain {
    private static final Dimension CANVAS_DIMENSION = new Dimension(1600, 900);

    public static void main(String[] argv) {
        JFrame frame = new JFrame("Arty");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        ArtyApplet applet = new ArtyApplet(CANVAS_DIMENSION);
        applet.buildUI();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
