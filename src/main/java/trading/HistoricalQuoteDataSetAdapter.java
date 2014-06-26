package trading;

import org.joda.time.LocalDate;

import java.util.Iterator;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class HistoricalQuoteDataSetAdapter implements DataSet {

    public static final int DATE = 0;
    public static final int OPEN = 1;
    public static final int HIGH = 2;
    public static final int LOW = 3;
    public static final int CLOSE = 4;
    public static final int VOLUME = 5;
    public static final int ADJ_CLOSE = 6;

    private final Iterator<HistoricalQuote> quotes;
    private HistoricalQuote current;

    public HistoricalQuoteDataSetAdapter(Iterator<HistoricalQuote> quotes) {
        this.quotes = quotes;
    }

    @Override
    public int nbColumns() {
        return 7;
    }

    @Override
    public LocalDate dateAt(int column) {
        if(column!=DATE)
            throw new IllegalArgumentException("Column " + column + " is not a Date!");
        return current.date();
    }

    @Override
    public int intAt(int column) {
        switch (column) {
            case OPEN:
                return (int) current.open();
            case HIGH:
                return (int) current.high();
            case LOW:
                return (int) current.low();
            case CLOSE:
                return (int) current.close();
            case VOLUME:
                return (int) current.volume();
            case ADJ_CLOSE:
                return (int) current.adjClose();
        }
        throw new IllegalArgumentException("Column " + column + " cannot be viewed as an int!");
    }

    @Override
    public float floatAt(int column) {
        switch (column) {
            case OPEN:
                return current.open();
            case HIGH:
                return current.high();
            case LOW:
                return current.low();
            case CLOSE:
                return current.close();
            case VOLUME:
                return (float) current.volume();
            case ADJ_CLOSE:
                return current.adjClose();
        }
        throw new IllegalArgumentException("Column " + column + " cannot be viewed as a float!");
    }

    @Override
    public boolean next() {
        boolean next = quotes.hasNext();
        if(next)
            current = quotes.next();
        return next;
    }
}
