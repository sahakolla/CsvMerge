package csv;

import csv.CsvVo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CsvParser {

    public static List<CsvVo> getRecodrsFromACsv(String file, List<String> keys) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(file));
        List<CsvVo> records = new ArrayList<>();
        boolean isHeader = true;

        String line = null;
        while ((line = br.readLine()) != null) {
            if (isHeader) {// first line is header
                isHeader = false;
                continue;
            }
            CsvVo record = new CsvVo(file);
            String[] lineSplit = line.split(",");
            for (int i = 0; i < lineSplit.length; i++) {
                record.put(keys.get(i), lineSplit[i]);
            }
            records.add(record);
        }

        br.close();

        return records;
    }

    public static List<String> getHeadersFromACsv(String file) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(file));
        List<String> headers = null;

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(",");
            headers = new ArrayList<>(Arrays.asList(lineSplit));
            break;
        }

        br.close();

        return headers;
    }

    public static void writeToCsv(final Set<String> headers, final List<CsvVo> records)
            throws IOException {

        StringWriter csvWriter = new StringWriter();
        //FileWriter csvWriter = new FileWriter(file);

        // write headers
        String sep = "";
        String[] headersArr = headers.toArray(new String[headers.size()]);
        for (String header : headersArr) {
            csvWriter.append(sep);
            csvWriter.append(header);
            sep = ",";
        }

        csvWriter.append("\n");

        // write records at each line
        for (CsvVo record : records) {
            sep = "";
            for (int i = 0; i < headersArr.length; i++) {
                csvWriter.append(sep);
                csvWriter.append(record.get(headersArr[i]));
                sep = ",";
            }
            csvWriter.append("\n");
        }

        System.out.println("Data after merging csv files:\n" + csvWriter);

        csvWriter.flush();
        csvWriter.close();
    }
}