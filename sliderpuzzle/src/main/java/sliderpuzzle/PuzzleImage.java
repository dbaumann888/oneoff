package sliderpuzzle;

public enum PuzzleImage {
    NUMBERS(null),
    DONUT("krispykreme.jpg"),
    NYC("nyctilt.png"),
    TIME_SQUARE("timesquaretilt.jpg"),
    PARITY("parity.png");

    private String fileName;

    PuzzleImage(String fileNameWithoutPath) {
        this.fileName = "/images/" + fileNameWithoutPath;
    }

    public String getFileName() { return this.fileName; }
}
