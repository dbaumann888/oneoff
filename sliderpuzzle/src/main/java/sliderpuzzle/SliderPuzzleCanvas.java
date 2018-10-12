package sliderpuzzle;

import java.awt.*;

public class SliderPuzzleCanvas extends Component {

    private final Dimension dimension;
    public SliderPuzzleCanvas(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getPreferredSize() {
        return this.dimension;
    }

    public void paint(Graphics g) {
        drawBlank(g);
    }

    private void drawBlank(Graphics g) {
        int w = this.dimension.width, h = this.dimension.height;
        g.setColor(Color.CYAN);
        g.fillOval(50, 50, w - 100, h - 100);
    }
}
