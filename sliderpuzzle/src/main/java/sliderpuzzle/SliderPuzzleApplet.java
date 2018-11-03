package sliderpuzzle;

import javax.swing.*;
import java.awt.*;

public class SliderPuzzleApplet extends JApplet {
    private final ConfigPanel configPanel;
    private SliderPuzzlePanel sliderPuzzlePanel;

    public SliderPuzzleApplet(Dimension defaultGameDim, PuzzleImage image) {
        this.configPanel = new ConfigPanel(this, defaultGameDim);
    }

    public void buildUI() {
        buildConfigUI();
    }

    public void buildConfigUI() {
        add(this.configPanel);
        this.configPanel.buildUI();
    }

    @Override
    public Dimension getPreferredSize() {
        if (this.sliderPuzzlePanel != null) {
            return this.sliderPuzzlePanel.getPreferredSize();
        } else {
            return super.getPreferredSize();
        }
    }

    @Override
    public Dimension getSize() {
        if (this.sliderPuzzlePanel != null) {
            return this.sliderPuzzlePanel.getSize();
        } else {
            return super.getSize();
        }
    }

    public void buildPuzzleUI(Dimension gameDim, PuzzleImage image) {
        remove(this.configPanel);
        this.sliderPuzzlePanel = new SliderPuzzlePanel(gameDim, image);
        add(this.sliderPuzzlePanel);
        sliderPuzzlePanel.buildUI();
        invalidate();
        repaint();
    }
}
