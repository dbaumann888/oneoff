package sliderpuzzle;

import java.awt.*;

public class PuzzleImage {
    private String name;
    private String filePath;
    private Dimension defaultDimension;

    PuzzleImage(String name, String filePath, Dimension defaultDimension) {
        this.name = name;
        this.filePath = filePath;
        this.defaultDimension = defaultDimension;
    }

    public String getName() { return this.name; }
    public String getFilePath() { return this.filePath; }
    public Dimension getDefaultDimension() { return this.defaultDimension; }
}
