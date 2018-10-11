package arty;

import java.awt.*;
import java.util.Random;

public class ArtyCanvas extends Component {
    private enum DrawMode {
        BLANK,
        ROSE,
        TREE;
    }

    private DrawMode mode;
    private final Dimension dimension;
    public ArtyCanvas(Dimension dimension) {
        this.dimension = dimension;
        this.mode = DrawMode.BLANK;
    }

    public Dimension getPreferredSize() {
        return this.dimension;
    }

    public void paint(Graphics g) {
        switch (this.mode) {
            case BLANK: drawBlank(g); break;
            case ROSE:  drawRose(g);  break;
            case TREE:  drawTree(g);  break;
        }
    }

    public void blank() { this.mode = DrawMode.BLANK; }
    public void rose()  { this.mode = DrawMode.ROSE;  }
    public void tree()  { this.mode = DrawMode.TREE;  }

    private void drawBlank(Graphics g) {
        int w = this.dimension.width, h = this.dimension.height;
        g.setColor(Color.CYAN);
        g.fillOval(50, 50, w - 100, h - 100);
    }

    private void drawRose(Graphics g) {
        Dimension center = new Dimension(this.dimension.width / 2, this.dimension.height / 3);
        drawStem(g, center, 400, 7, new Color(0, 180, 0));
        drawFourPetals(g, center, 95, 67, true, new Color(180, 0, 180));
        drawFourPetals(g, center, 72, 70, false, new Color(200, 0, 200));
        drawFourPetals(g, center, 50, 33, true, new Color(240, 0, 240));
        drawFourPetals(g, center, 50, 0, false, new Color(200, 0, 0));
    }

    private void drawStem(Graphics g, Dimension center, int length, int thickness, Color color) {
        g.setColor(color);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(center.width, center.height, center.width, center.height + length);
    }

    private void drawFourPetals(Graphics g, Dimension center, int radius, int shift, boolean diagonal, Color color) {
        g.setColor(color);
        if (diagonal) {
            g.fillOval(center.width - radius - shift, center.height - radius - shift, radius * 2, radius * 2);
            g.fillOval(center.width - radius - shift, center.height - radius + shift, radius * 2, radius * 2);
            g.fillOval(center.width - radius + shift, center.height - radius - shift, radius * 2, radius * 2);
            g.fillOval(center.width - radius + shift, center.height - radius + shift, radius * 2, radius * 2);
        } else {
            g.fillOval(center.width - radius - shift, center.height - radius, radius * 2, radius * 2);
            g.fillOval(center.width - radius, center.height - radius - shift, radius * 2, radius * 2);
            g.fillOval(center.width - radius + shift, center.height - radius, radius * 2, radius * 2);
            g.fillOval(center.width - radius, center.height - radius + shift, radius * 2, radius * 2);
        }
    }

    private void drawTree(Graphics g) {
        Dimension start = new Dimension(this.dimension.width / 2, this.dimension.height - 20);
        drawBranch(g, start, 150, Math.PI, 3, 11);
    }

    private void drawBranch(Graphics g, Dimension start, int length, double angle, int thickness, int depth) {
        if (depth == 0) {
            Random rand = new Random();
            int leafColorIndex = rand.nextInt(3);
            Color leafColor = leafColorIndex == 0 ? Color.RED : leafColorIndex == 1 ? Color.YELLOW : Color.ORANGE;
            g.setColor(leafColor);
            g.fillOval(start.width - 5, start.height - 5, 10, 10);
        } else {
            g.setColor(new Color(80, 75, 70));
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(thickness));
            int deltaX = (int)(Math.sin(angle) * length);
            int deltaY = (int)(Math.cos(angle) * length);
            Dimension end = new Dimension(start.width + deltaX, start.height + deltaY);
            g.drawLine(start.width, start.height, end.width, end.height);
            drawBranch(g, end, (int)(length * 0.85d), angle - (Math.PI / 6), thickness, depth - 1);
            drawBranch(g, end, (int)(length * 0.85d), angle + (Math.PI / 6), thickness, depth - 1);
        }
    }
}
