package trading;

import org.junit.Test;
import trading.util.Visitor;

import java.io.IOException;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class HistoricalQuoteCsvReaderTest {

    @Test
    public void raw() throws IOException {
        HistoricalQuoteCsvReader reader = new HistoricalQuoteCsvReader();
        reader.read(getClass().getResourceAsStream("/AAPL.csv"),
                new Visitor<HistoricalQuote>() {
                    @Override
                    public boolean visit(HistoricalQuote element) {
                        System.out.println("HistoricalQuoteCsvReaderTest.visit-> " + element);
                        return true;
                    }
                }
        );
    }
}
