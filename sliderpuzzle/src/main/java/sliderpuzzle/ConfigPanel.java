package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConfigPanel extends JPanel {
    private final SliderPuzzleApplet applet;
    private final SliderPuzzleCanvas canvas;
    private final Dimension defaultDimension;

    public ConfigPanel(SliderPuzzleApplet applet, Dimension defaultDimension) {
        this.applet = applet;
        this.defaultDimension = defaultDimension;
        this.canvas = null;
    }

    void handleMouseClick(Point p) {
        applet.buildPuzzleUI(this.defaultDimension, PuzzleImage.GGBRIDGE);
    }

    public void buildUI() {
        JButton startButton = new JButton("start numbers");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                handleMouseClick(b.getMousePosition());
            }
        });
        add(startButton);

/*        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                panel.handleMouseClick(p);
            }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        */
    }
}
