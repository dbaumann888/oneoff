package sliderpuzzle;

import java.awt.*;
import java.util.Random;

public class TileSet {

    private static final int BLANK = 0;
    private static final int RANDOM_SHUFFLES = 50000;
    private final Dimension dimension;
    private final int[][] tiles;

    TileSet(Dimension dimension) {
        this.dimension = dimension;
        this.tiles = new int[dimension.width][dimension.height];
        int tileNum = 1;
        for (int y = 0; y < dimension.height; ++y) {
            for (int x = 0; x < dimension.width; ++x) {
                if ((x == dimension.width - 1) && (y == dimension.height - 1)) {
                    this.tiles[x][y] = BLANK;
                } else {
                    this.tiles[x][y] = tileNum;
                    tileNum++;
                }
            }
        }
    }

    int[][] getArray() {
        return this.tiles;
    }

    void slide(Dimension location, boolean inRandomMode) {
        Dimension blank = findBlank();
        if (location.equals(blank) && isSolved() && !inRandomMode) {
            randomize();
        } else {
            swapSeriesOfTiles(blank.width, blank.height, location.width, location.height);
        }
    }

    Dimension findBlank() {
        int x = 0, y = 0;
        out:
        for (x = 0; x < this.dimension.width; ++x) {
            for (y = 0; y < this.dimension.height; ++y) {
                if (this.tiles[x][y] == BLANK) {
                    break out;
                }
            }
        }
        return new Dimension(x, y);
    }

    private void swapSeriesOfTiles(int xBlank, int yBlank, int x2, int y2) {
        if (((xBlank != x2) && (yBlank != y2)) || ((xBlank == x2) && (yBlank == y2))) {
            return;
        }

        if (xBlank == x2) {
            // slide vertical
            if (yBlank < y2) {
                for (int y = yBlank; y < y2; ++y) {
                    swap2Tiles(x2, y, x2, y + 1);
                }
            } else {
                for (int y = yBlank; y > y2; --y) {
                    swap2Tiles(x2, y, x2, y - 1);
                }
            }
        } else {
            // slide horizontal
            if (xBlank < x2) {
                for (int x = xBlank; x < x2; ++x) {
                    swap2Tiles(x, y2, x + 1, y2);
                }
            } else {
                for (int x = xBlank; x > x2; --x) {
                    swap2Tiles(x, y2, x - 1, y2);
                }
            }
        }
    }

    private void swap2Tiles(int x1, int y1, int x2, int y2) {
        int temp = this.tiles[x1][y1];
        this.tiles[x1][y1] = this.tiles[x2][y2];
        this.tiles[x2][y2] = temp;
    }

    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < RANDOM_SHUFFLES; ++i) {
            int randomTile = random.nextInt(this.dimension.width * this.dimension.height);
            Dimension location = new Dimension(randomTile % this.dimension.width, randomTile / this.dimension.width);
            slide(location, true);
        }
    }

    public boolean isSolved() {
        boolean solved = true;
        int i = 1;
        for (int y = 0; solved && y < this.dimension.height; ++y) {
            for (int x = 0; solved && x < this.dimension.width && !((x == this.dimension.width - 1) && (y == this.dimension.height - 1)); ++x) {
                if (this.tiles[x][y] != i) {
                    solved = false;
                } else {
                    ++i;
                }
            }
        }
        return solved;
    }
}
