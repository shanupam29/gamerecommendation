package com.assignment.game.recommendation.util;

import com.assignment.game.recommendation.entity.Customer;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {"id", "recStatus", "game1", "game2", "game3", "game4", "game5", "game6", "game7", "game8", "game9", "game10"};

    private static final String ID = "id";
    private static final String REC_STATUS = "recStatus";
    private static final String GAME1 = "game1";
    private static final String GAME2 = "game2";
    private static final String GAME3 = "game3";
    private static final String GAME4 = "game4";
    private static final String GAME5 = "game5";
    private static final String GAME6 = "game6";
    private static final String GAME7 = "game7";
    private static final String GAME8 = "game8";
    private static final String GAME9 = "game9";
    private static final String GAME10 = "game10";

    /**
     * Utility method to parse the contents of the csv file and map to the
     * entity class for persistence.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Customer> parseContents(final MultipartFile file) throws IOException {

        FileReader fileReader = null;

        CSVParser csvFileParser = null;

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();

        List<Customer> customers = new ArrayList<>();
        try {
            fileReader = new FileReader(multipartToFile(file));

            csvFileParser = new CSVParser(fileReader, csvFileFormat);

            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            csvRecords.forEach(record -> {
                customers.add(new Customer(Long.parseLong(record.get(ID)),
                        Boolean.parseBoolean(record.get(REC_STATUS)), record.get(GAME1),
                        record.get(GAME2), record.get(GAME3), record.get(GAME4),
                        record.get(GAME5), record.get(GAME6),
                        record.get(GAME7), record.get(GAME8),
                        record.get(GAME9), record.get(GAME10)));
            });
        } finally {
            fileReader.close();
        }
        return customers;
    }

    private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        if (convFile.exists()) {
            convFile.delete();
        }
        multipart.transferTo(convFile);
        return convFile;
    }
}
