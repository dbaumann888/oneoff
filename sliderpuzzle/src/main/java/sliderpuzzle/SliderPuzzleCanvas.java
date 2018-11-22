package sliderpuzzle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SliderPuzzleCanvas extends Component {

    private static final Dimension NUMBERS_DIMENSION = new Dimension(900, 900);

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

    private int getColumns() { return this.tiles.length; }
    private int getRows() { return this.tiles[0].length; }
    private int getTileWidth() { return this.dimension.width / getColumns(); }
    private int getTileHeight() { return this.dimension.height / getRows(); }

    public void setPuzzleImage(PuzzleImage puzzleImage) {
        this.puzzleImage = puzzleImage;
/*        if (puzzleImage == PuzzleImage.NUMBERS) {
            this.image = null;
            this.dimension = NUMBERS_DIMENSION;
        } else { */
        InputStream is;
        try {
            String filePath = this.puzzleImage.getFilePath();

            if (filePath.startsWith("/images/")) {
                is = SliderPuzzleCanvas.class.getResourceAsStream(filePath);
            } else {
                URL urlObject = new URL(filePath);
                URLConnection urlConnection = urlObject.openConnection();
                is = urlConnection.getInputStream();
            }
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int tileWidth = this.image.getWidth() / getColumns();
        int tileHeight = this.image.getHeight() / getRows();
        this.dimension = new Dimension(tileWidth * getColumns(),tileHeight * getRows());
        this.subImages = buildSubImages(this.image, this.tiles);
    }

    private BufferedImage[] buildSubImages(BufferedImage image, int[][] tiles) {
        int xMax = getColumns(), yMax = getRows(), tileWidth = getTileWidth(), tileHeight = getTileHeight();
        BufferedImage[] bufferedImages = new BufferedImage[xMax * yMax];
        int i = 0;
        for (int y = 0; y < yMax; ++y) {
            for (int x = 0; x < xMax; ++x) {
                int pX = x * tileWidth, pY = y * tileHeight;
                bufferedImages[i] = image.getSubimage(pX, pY, tileWidth, tileHeight);
                i++;
            }
        }
        return bufferedImages;
    }

    @Override
    public Dimension getPreferredSize() {
        return this.dimension;
    }

    @Override
    public Dimension getSize() {
        return this.dimension;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    public void paint(Graphics g) {
        for (int x = 0; x < getColumns(); ++x) {
            for (int y = 0; y < getRows(); ++y) {
                drawTile((Graphics2D)g, x, y);
            }
        }
    }

    public Dimension computeTileLocation(Point p) {
        return new Dimension(p.x / getTileWidth(), p.y / getTileHeight());
    }

    public void celebrate(boolean solved) {
        this.solved = solved;
    }


    private void drawTile(Graphics2D g, int x, int y) {
/*        if (this.puzzleImage == PuzzleImage.NUMBERS) {
            drawNumberTile(g, x, y);
        } else {*/
            drawImageTile(g, x, y);
//        }
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
        int tileWidth = getTileWidth(), tileHeight = getTileHeight();
        if ((tiles[x][y] == 0) && !this.solved) {
            g.setColor(Color.BLACK);
            g.fillRect(tileWidth * x, tileHeight * y, tileWidth, tileHeight);
        } else {
            int tileNumber = this.tiles[x][y];
            if (tileNumber == 0) {
                tileNumber = getColumns() * getRows();
            }
            BufferedImage img = this.subImages[tileNumber - 1];
            g.drawImage(img, tileWidth * x, tileHeight * y, null);
        }
    }
}
