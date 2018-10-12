package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SliderPuzzleApplet extends JApplet {
    private final Dimension canvasDimension;
    private final Dimension gameDimension;
    private final SliderPuzzleCanvas canvas;

    public SliderPuzzleApplet(Dimension canvasDim, Dimension gameDim) {
        this.canvasDimension = canvasDim;
        this.gameDimension = gameDim;
        this.canvas = new SliderPuzzleCanvas(this.canvasDimension);
    }

    public void buildUI() {
        final SliderPuzzleCanvas canvas = this.canvas;
        add("North", this.canvas);

        JButton roseButton = new JButton("rose");
        roseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                canvas.repaint();
            }
        });
        add("Center", roseButton);

        JButton treeButton = new JButton("tree");
        treeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                canvas.repaint();
            }
        });
        add("South", treeButton);
    }
}
