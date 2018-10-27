package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SliderPuzzleMain {
    private static final Dimension PUZZLE_DIMENSION = new Dimension(10, 7);

    public static void main(String[] argv) {
        JFrame frame = new JFrame("Slider Puzzle");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
        SliderPuzzleApplet applet = new SliderPuzzleApplet(PUZZLE_DIMENSION, PuzzleImage.NYC);
        applet.buildUI();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
