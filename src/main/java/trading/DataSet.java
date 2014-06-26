package trading;

import org.joda.time.LocalDate;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public interface DataSet {
    int nbColumns();
    LocalDate dateAt(int column);
    int intAt(int column);
    float floatAt(int column);
    boolean next();
}
