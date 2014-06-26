package trading.util;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public interface Visitor<T> {
    boolean visit(T element);
}
