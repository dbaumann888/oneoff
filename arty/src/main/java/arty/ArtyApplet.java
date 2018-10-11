package arty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtyApplet extends JApplet {
    private final Dimension dimension;
    private final ArtyCanvas canvas;

    public ArtyApplet(Dimension dim) {
        this.dimension = dim;
        this.canvas = new ArtyCanvas(this.dimension);
    }

    public void buildUI() {
        final ArtyCanvas canvas = this.canvas;
        add("North", this.canvas);

        JButton roseButton = new JButton("rose");
        roseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                canvas.rose();
                canvas.repaint();
            }
        });
        add("Center", roseButton);

        JButton treeButton = new JButton("tree");
        treeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();
                canvas.tree();
                canvas.repaint();
            }
        });
        add("South", treeButton);
    }
}
