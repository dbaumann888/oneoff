package sliderpuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConfigPanel extends JPanel {
    private final SliderPuzzleApplet applet;
    private final Dimension defaultDimension;
    private java.awt.List imageList;
    private final SliderPuzzleConfiguration configuration;

    public ConfigPanel(SliderPuzzleApplet applet, Dimension defaultDimension) {
        this.applet = applet;
        this.defaultDimension = defaultDimension;
        this.configuration = new SliderPuzzleConfiguration();
    }

    void start() {
        int index = this.imageList.getSelectedIndex();
        if (index < 0) {
            index = 0;
        }
        PuzzleImage image = this.configuration.getImages().get(index);
        applet.buildPuzzleUI(image.getDefaultDimension(), image);
    }

    public void buildUI() {
        this.imageList = buildImageList();
        add(this.imageList);

        JButton startButton = new JButton("start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        add(startButton);
    }

    private java.awt.List buildImageList() {
        List<PuzzleImage> images = this.configuration.getImages();
        java.awt.List imageList = new java.awt.List(20, false);
        for (PuzzleImage image : images) {
            imageList.add(image.getName());
        }
        return imageList;
    }
}
