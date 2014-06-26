package trading;

import au.com.bytecode.opencsv.CSVReader;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import trading.util.Visitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class HistoricalQuoteCsvReader {


    private final DateTimeFormatter dateFormatter;

    public HistoricalQuoteCsvReader() {
        dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    }

    public void read(InputStream inputStream, Visitor<HistoricalQuote> visitor) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
        checkFirstLine(reader.readNext());

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            LocalDate date = dateFormatter.parseLocalDate(nextLine[0]);
            float open = Float.parseFloat(nextLine[1]);
            float high = Float.parseFloat(nextLine[2]);
            float low = Float.parseFloat(nextLine[3]);
            float close = Float.parseFloat(nextLine[4]);
            int volume = Integer.parseInt(nextLine[5]);
            float adjClose = Float.parseFloat(nextLine[6]);

            boolean doContinue = visitor.visit(new HistoricalQuote(date, open, high, low, close, volume, adjClose));
            if(!doContinue)
                return;
        }
    }

    private void checkFirstLine(String[] columns) {
        if(columns==null)
            throw new IllegalArgumentException("Invalid input file: no headers");

        if(!"Date".equalsIgnoreCase(columns[0]))
            throw new IllegalArgumentException("Invalid input file: Expecting Date");
    }
}
