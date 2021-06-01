package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvApplication {

    public static void main(String[] args) throws IOException {
        //File csv1 = new File("D:/csv/csv1.csv");
        //File csv2 = new File("D:/csv/csv2.csv");

        /*InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);

        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }*/

        String line = "ID,Name,Start,End\n"+
                "16,Abraham Lincoln,1861,1865\n\n"+
                "ID,Name,Start,End\n" +
                "15,James Buchanan,1857,1861";

        String[] lineSplit = line.split("\n\n");

        String csv1=lineSplit[0];
        String csv2=lineSplit[1];

        List<String> csv1Headers = CsvParser.getHeadersFromACsv(csv1);
        //csv1Headers.forEach(h -> System.out.print(h + " "));
        //System.out.println();

        List<String> csv2Headers = CsvParser.getHeadersFromACsv(csv2);
        //csv2Headers.forEach(h -> System.out.print(h + " "));
        //System.out.println();

        List<String> allCsvHeaders = new ArrayList<>();
        allCsvHeaders.addAll(csv1Headers);
        allCsvHeaders.addAll(csv2Headers);
        //allCsvHeaders.forEach(h -> System.out.print(h + " "));
        //System.out.println();

        Set<String> uniqueHeaders = new LinkedHashSet<>(allCsvHeaders);
        //uniqueHeaders.forEach(h -> System.out.print(h + " "));
        //System.out.println();

        List<CsvVo> csv1Records = CsvParser.getRecodrsFromACsv(csv1, csv1Headers);
        List<CsvVo> csv2Records = CsvParser.getRecodrsFromACsv(csv2, csv2Headers);

        List<CsvVo> allCsvRecords = new ArrayList<>();
        allCsvRecords.addAll(csv1Records);
        allCsvRecords.addAll(csv2Records);

        //CsvParser.writeToCsv(new File("D:/csv/csv.csv"), uniqueHeaders, allCsvRecords);
        CsvParser.writeToCsv(uniqueHeaders, allCsvRecords);

    }

}