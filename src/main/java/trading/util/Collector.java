package trading.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arnauld Loyer (@aloyer)
 */
public class Collector<T> implements Visitor<T> {
    private final List<T> collected;

    public Collector() {
        this(new ArrayList<T>());
    }

    public Collector(List<T> collected) {
        this.collected = collected;
    }

    @Override
    public boolean visit(T element) {
        collected.add(element);
        return true;
    }

    public List<T> getCollected() {
        return collected;
    }
}
