package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SliderPuzzlePanel extends JPanel {
    private final SliderPuzzleApplet applet;
    private final SliderPuzzleCanvas canvas;
    private final TileSet tiles;

    public SliderPuzzlePanel(SliderPuzzleApplet applet, Dimension gameDim, PuzzleImage image) {
        this.applet = applet;
        this.tiles = new TileSet(gameDim);
        this.tiles.randomize();
        this.canvas = new SliderPuzzleCanvas(tiles.getArray(), image);
    }

    void handleMouseClick(Point p) {
        Dimension clickLocation = this.canvas.computeTileLocation(p);
        if (this.tiles.isSolved()) {
            this.applet.buildConfigUI();
        } else {
            this.tiles.slide(clickLocation, false);
            this.canvas.setTiles(this.tiles.getArray());
            boolean solvedAfter = this.tiles.isSolved();
            if (solvedAfter) {
                this.canvas.celebrate(solvedAfter);
            }
        }
        this.canvas.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return this.canvas.getPreferredSize();
    }

    @Override
    public Dimension getSize() {
        return this.canvas.getSize();
    }

    public void buildUI() {
        add(this.canvas);

        final SliderPuzzlePanel panel = this;

        addMouseListener(new MouseListener() {
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
    }
}
