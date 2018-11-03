package sliderpuzzle;

public enum PuzzleImage {
    NUMBERS(null),
    ARROWS("arrows.jpeg"),
    DONUT("donutsprinkles.jpg"),
    GGBRIDGE("ggbridge.jpg"),
    STEEL_MILL("steelmill.jpg"),
    TRAINS("traindepot.jpg");

    private String fileName;

    PuzzleImage(String fileNameWithoutPath) {
        this.fileName = "/images/" + fileNameWithoutPath;
    }

    public String getFileName() { return this.fileName; }
}
