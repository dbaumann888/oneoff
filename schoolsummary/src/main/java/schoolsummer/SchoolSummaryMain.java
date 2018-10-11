package schoolsummer;

public class SchoolSummaryMain {
    private static final String SCHOOL_SUMMARY_FILE_NAME = "schoolsums.csv";
    public static void main(String[] argv) {
        SchoolSummer ss = new SchoolSummer(SCHOOL_SUMMARY_FILE_NAME);
        ss.sum();
        System.out.println(ss.toString());
    }
}
