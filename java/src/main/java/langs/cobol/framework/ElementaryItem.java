package langs.cobol.framework;

public abstract class ElementaryItem
        <T extends ElementaryItem<T, V>, V extends Comparable<V>>
        extends DataItem<T>
        implements Comparable<ElementaryItem<?, V>> {

    private final int length;

    private V value;

    protected ElementaryItem(int length, V value) {
        this.length = length;
        this.value = value;
    }

    public int length() {
        return length;
    }

    public V value() {
        return value;
    }

    public T value(V value) {
        set(value);
        return self();
    }

    public void set(V value) {
        this.value = value;
    }

    public void set(ElementaryItem<?, V> other) {
        set(other.value());
    }

    /**
     * self = other.
     */
    public boolean eq(V other) {
        return compareTo(other) == 0;
    }

    /**
     * self = other.
     */
    public boolean eq(ElementaryItem<?, V> other) {
        return eq(other.value());
    }

    /**
     * self > other.
     */
    public boolean gt(V other) {
        return compareTo(other) > 0;
    }

    /**
     * self > other.
     */
    public boolean gt(ElementaryItem<?, V> other) {
        return gt(other.value());
    }

    /**
     * self >= other.
     */
    public boolean ge(V other) {
        return compareTo(other) >= 0;
    }

    /**
     * self >= other.
     */
    public boolean ge(ElementaryItem<?, V> other) {
        return ge(other.value());
    }

    /**
     * self < other.
     */
    public boolean lt(V other) {
        return compareTo(other) < 0;
    }

    /**
     * self < other.
     */
    public boolean lt(ElementaryItem<?, V> other) {
        return lt(other.value());
    }

    /**
     * self <= other.
     */
    public boolean le(V other) {
        return compareTo(other) <= 0;
    }

    /**
     * self <= other.
     */
    public boolean le(ElementaryItem<?, V> other) {
        return le(other.value());
    }

    public int compareTo(V other) {
        if (value == null) {
            if (other == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (other == null) {
                return -1;
            } else {
                return value.compareTo(other);
            }
        }
    }

    public int compareTo(ElementaryItem<?, V> other) {
        return compareTo(other.value());
    }

    /**
     * MOVE self TO target.
     */
    public void moveTo(ElementaryItem<?, V> target) {
        target.set(value());
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
