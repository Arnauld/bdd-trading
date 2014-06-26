package trading;

import com.google.common.base.Function;
import org.joda.time.LocalDate;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class HistoricalQuote {
    private final LocalDate date;
    private final float open;
    private final float high;
    private final float low;
    private final float close;
    private final int volume;
    private final float adjClose;


    public HistoricalQuote(LocalDate date, float open, float high, float low, float close, int volume, float adjClose) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
    }

    public LocalDate date() {
        return date;
    }

    public float open() {
        return open;
    }

    public float high() {
        return high;
    }

    public float low() {
        return low;
    }

    public float close() {
        return close;
    }

    public int volume() {
        return volume;
    }

    public float adjClose() {
        return adjClose;
    }

    @Override
    public String toString() {
        return "HistoricalQuote{" +
                "date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", adjClose=" + adjClose +
                '}';
    }

    public static Function<? super HistoricalQuote, Double> fnAdjClose() {
        return new Function<HistoricalQuote, Double>() {
            @Override
            public Double apply(HistoricalQuote historicalQuote) {
                return (double)historicalQuote.adjClose();
            }
        };
    }
}
