package sliderpuzzle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SliderPuzzleConfiguration {
    List<PuzzleImage> images;

    public SliderPuzzleConfiguration() {
        this.images = readConfigurationFile();
    }

    public List<PuzzleImage> getImages() {
        return this.images;
    }

    List<PuzzleImage> readConfigurationFile() {
        List<PuzzleImage> fileImages = new LinkedList<>();
        InputStream is = SliderPuzzleConfiguration.class.getResourceAsStream("/configuration");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] lineParts = line.split(",");
                    String name = lineParts[0];
                    String filePath = lineParts[1];
                    if (filePath.startsWith("images")) {
                        filePath = "/" + filePath;
                    }
                    int defaultColumns = Integer.parseInt(lineParts[2]);
                    int defaultRows = Integer.parseInt(lineParts[3]);
                    PuzzleImage image = new PuzzleImage(name, filePath, new Dimension(defaultColumns, defaultRows));
                    fileImages.add(image);
                }
            }
        } catch (IOException ioe) {
            System.out.println("problem reading configuration file: " + ioe.getMessage());
        }

        return fileImages;
    }
}
