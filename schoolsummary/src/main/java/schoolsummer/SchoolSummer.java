package schoolsummer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SchoolSummer {
    private final String sumFileName;
    private Map<String, Integer> stateSums = new HashMap<>();
    private Map<String, Integer> citySums = new HashMap<>();
    private Map<String, Integer> schoolSums = new HashMap<>();

    SchoolSummer(String fileName) {
        this.sumFileName = fileName;
    }

    void sum() {
        InputStream inputStream = getClass().getResourceAsStream("/" + this.sumFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            System.err.println("Exception reading summary file: " + e);
        }
    }

    private void processLine(String line) {
        String[] pieces = line.split(",");
        String state = pieces[0];
        String city = pieces[1];
        String school = pieces[2];
        String grade = pieces[3];
        Integer numKids = Integer.parseInt(pieces[4]);

        addSum(this.stateSums, state, numKids);
        addSum(this.citySums, city, numKids);
        addSum(this.schoolSums, school, numKids);
    }

    private void addSum(Map<String, Integer> sumMap, String name, final Integer numKids) {
        Integer oldSum = sumMap.get(name);
        Integer newSum = (oldSum == null) ? numKids : oldSum + numKids;
        sumMap.put(name, newSum);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("states: ");
        sb.append(this.stateSums.toString());
        sb.append("\ncities: ");
        sb.append(this.citySums.toString());
        sb.append("\nschools: ");
        sb.append(this.schoolSums.toString());
        return sb.toString();
    }
}
