package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SliderPuzzleMain {
    private static final Dimension CANVAS_DIMENSION = new Dimension(1600, 900);
    private static final Dimension PUZZLE_DIMENSION = new Dimension(4, 4);

    public static void main(String[] argv) {
        JFrame frame = new JFrame("Slider Puzzle");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        SliderPuzzleApplet applet = new SliderPuzzleApplet(CANVAS_DIMENSION, PUZZLE_DIMENSION);
        applet.buildUI();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
