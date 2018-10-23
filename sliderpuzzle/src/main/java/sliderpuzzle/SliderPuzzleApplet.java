package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SliderPuzzleApplet extends JApplet {
    private final Dimension canvasDimension;
    private final Dimension gameDimension;
    private final SliderPuzzleCanvas canvas;
    private final TileSet tiles;

    public SliderPuzzleApplet(Dimension canvasDim, Dimension gameDim) {
        this.canvasDimension = canvasDim;
        this.gameDimension = gameDim;
        this.tiles = new TileSet(gameDim);
        this.canvas = new SliderPuzzleCanvas(this.canvasDimension, tiles.getArray());
    }

    void handleClick(Point p) {
        Dimension clickLocation = this.canvas.computeTileLocation(p);
        this.tiles.slide(clickLocation);
        this.canvas.setTiles(this.tiles.getArray());
        this.canvas.celebrate(this.tiles.isSolved());
        this.canvas.repaint();
    }

    public void buildUI() {
        add("North", this.canvas);

        final SliderPuzzleApplet applet = this;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                applet.handleClick(p);
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }
}
