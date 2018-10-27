package sliderpuzzle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SliderPuzzleCanvas extends Component {

    private static final Dimension NUMBERS_DIMENSION = new Dimension(1200, 1200);

    private Dimension dimension;
    private int[][] tiles;
    private boolean solved;
    private PuzzleImage puzzleImage;
    private BufferedImage image;
    private BufferedImage subImages[];

    public SliderPuzzleCanvas(int[][] tiles, PuzzleImage puzzleImage) {
        this.tiles = tiles;
        this.solved = false;
        setPuzzleImage(puzzleImage);
    }

    public void setPuzzleImage(PuzzleImage puzzleImage) {
        this.puzzleImage = puzzleImage;
        if (puzzleImage == PuzzleImage.NUMBERS) {
            this.image = null;
            this.dimension = NUMBERS_DIMENSION;
        } else {
            try {
                this.image = ImageIO.read(SliderPuzzleCanvas.class.getResourceAsStream(this.puzzleImage.getFileName()));
                this.dimension = new Dimension(this.image.getWidth(), this.image.getHeight());
                this.subImages = buildSubImages(this.image, this.tiles);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage[] buildSubImages(BufferedImage image, int[][] tiles) {
        int xMax = tiles.length, yMax = tiles[0].length;
        BufferedImage[] bufferedImages = new BufferedImage[xMax * yMax];
        int width = this.image.getWidth(), height = this.image.getHeight();
        int i = 0;
        int xImage = 0, yImage = 0;
        int xImage2, yImage2;
        for (int y = 0; y < tiles[0].length; ++y) {
            yImage2 = Math.min((int)((double)(height) * (double)(y + 1) / yMax), height);
            for (int x = 0; x < tiles.length; ++x) {
                xImage2 = Math.min((int)((double)(width) * (double)(x + 1) / xMax), width);
                bufferedImages[i] = image.getSubimage(xImage, yImage, xImage2 - xImage, yImage2 - yImage);
                xImage = xImage2;
                i++;
            }
            yImage = yImage2;
            xImage = 0;
        }
        return bufferedImages;
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


    private void drawTile(Graphics2D g, int x, int y) {
        if (this.puzzleImage == PuzzleImage.NUMBERS) {
            drawNumberTile(g, x, y);
        } else {
            drawImageTile(g, x, y);
        }
    }

    private void drawNumberTile(Graphics2D g, int x, int y) {
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

    private void drawImageTile(Graphics2D g, int x, int y) {
        double tileWidth = ((double)getWidth()) / tiles.length;
        double tileHeight = ((double)getHeight()) / tiles[x].length;
        if ((tiles[x][y] == 0) && !this.solved) {
            g.setColor(Color.BLACK);
            g.fillRect((int)(tileWidth * x), (int)(tileHeight * y), (int)tileWidth, (int)tileHeight);
        } else {
            int tileNumber = this.tiles[x][y];
            if (tileNumber == 0) tileNumber = tiles.length * tiles[0].length;
            BufferedImage img = this.subImages[tileNumber - 1];
            g.drawImage(img, (int)(tileWidth * x), (int)(tileHeight * y), null);
        }
    }
}
