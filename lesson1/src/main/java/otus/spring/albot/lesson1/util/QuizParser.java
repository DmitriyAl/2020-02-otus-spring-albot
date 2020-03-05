package otus.spring.albot.lesson1.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import otus.spring.albot.lesson1.Main;
import otus.spring.albot.lesson1.model.QuestionType;
import otus.spring.albot.lesson1.model.ParsedLine;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dmitrii Albot
 */
public class QuizParser {
    private String fileName;
    private static final Logger LOG = Logger.getLogger(QuizParser.class);

    public QuizParser(String fileName) {
        this.fileName = fileName;
    }

    public List<ParsedLine> parse() {
        List<CSVRecord> records = getRecords();
        List<ParsedLine> lines = new LinkedList<>();
        for (CSVRecord record : records) {
            try {
                QuestionType type = QuestionType.valueOf(record.get(0));
                String question = record.get(1);
                String answer = record.get(2);
                int size = record.size();
                ParsedLine line;
                if (size > 3) {
                    List<String> choices = new LinkedList<>();
                    for (int i = 3; i < size; i++) {
                        choices.add(record.get(i));
                    }
                    line = new ParsedLine(type, question, answer, choices);
                } else {
                    line = new ParsedLine(type, question, answer);
                }
                lines.add(line);
            } catch (ArrayIndexOutOfBoundsException ex) {
                LOG.debug("Invalid CSV line with number " + record.getRecordNumber());
            }
        }
        return lines;
    }

    private List<CSVRecord> getRecords() {
        List<CSVRecord> records = null;
        try {
            CSVParser parser = CSVFormat.DEFAULT.parse(new InputStreamReader(Main.class.getResourceAsStream(fileName)));
            records = parser.getRecords();
        } catch (IOException e) {
            LOG.error("Problems with csv parser");
        }
        return records;
    }
}
