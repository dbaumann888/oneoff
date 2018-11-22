package sliderpuzzle;

import javax.swing.*;
import java.awt.*;

public class SliderPuzzleApplet extends JApplet {
    private static final Dimension DEFAULT_PUZZLE_DIMENSION = new Dimension(6, 5);
    private ConfigPanel configPanel;
    private SliderPuzzlePanel sliderPuzzlePanel;

    public SliderPuzzleApplet() {}

    public void buildUI() {
        buildConfigUI();
    }

    public void buildConfigUI() {
        if (this.sliderPuzzlePanel != null) {
            remove(this.sliderPuzzlePanel);
        }
        if (this.configPanel == null) {
            this.configPanel = new ConfigPanel(this, DEFAULT_PUZZLE_DIMENSION);
            this.configPanel.buildUI();
        }
        add(this.configPanel);
        invalidate();
        repaint();
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
        this.sliderPuzzlePanel = new SliderPuzzlePanel(this, gameDim, image);
        add(this.sliderPuzzlePanel);
        sliderPuzzlePanel.buildUI();
        invalidate();
        repaint();
    }
}
