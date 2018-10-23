package sliderpuzzle;

import java.awt.*;

public class SliderPuzzleCanvas extends Component {

    private final Dimension dimension;
    private int[][] tiles;
    private boolean solved;

    public SliderPuzzleCanvas(Dimension dimension, int[][] tiles) {
        this.dimension = dimension;
        this.tiles = tiles;
        this.solved = false;
    }

    public Dimension getPreferredSize() {
        return this.dimension;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    public void paint(Graphics g) {
        for (int x = 0; x < tiles.length; ++x) {
            for (int y = 0; y < tiles[x].length; ++y) {
                drawTile((Graphics2D)g, x, y);
            }
        }
    }

    public void drawTile(Graphics2D g, int x, int y) {
        double tileWidth = ((double)getWidth()) / tiles.length;
        double tileHeight = ((double)getHeight()) / tiles[x].length;
        if (tiles[x][y] == 0) {
            g.setColor(Color.BLACK);
            g.fillRect((int)(tileWidth * x), (int)(tileHeight * y), (int)tileWidth, (int)tileHeight);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect((int)(tileWidth * x), (int)(tileHeight * y), (int)tileWidth, (int)tileHeight);
            g.setStroke(new BasicStroke(dimension.width/100));
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font( "SansSerif", Font.PLAIN, 32));
            g.drawRect((int)(tileWidth * x), (int)(tileHeight * y), (int)tileWidth, (int)tileHeight);
            g.drawString(Integer.toString(tiles[x][y]), (int)(tileWidth * x + tileWidth / 2), (int)(tileHeight * y + tileHeight / 2));
        }

        if (this.solved) {
            int maxX = this.tiles.length - 1, maxY = this.tiles[0].length - 1;
            g.setColor(Color.PINK);
            g.fillRect((int) (tileWidth * maxX), (int) (tileHeight * maxY), (int) tileWidth, (int) tileHeight);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("SansSerif", Font.PLAIN, 32));
            g.drawString("Solved", (int) (tileWidth * maxX + tileWidth / 2), (int) (tileHeight * maxY + tileHeight / 2));
        }
    }

    public Dimension computeTileLocation(Point p) {
        double tileWidth = ((double)getWidth()) / tiles.length;
        double tileHeight = ((double)getHeight()) / tiles[0].length;
        int x = (int)(p.x / tileWidth);
        int y = (int)(p.y / tileHeight);
        return new Dimension(x, y);
    }

    public void celebrate(boolean solved) {
        this.solved = solved;
    }
}
