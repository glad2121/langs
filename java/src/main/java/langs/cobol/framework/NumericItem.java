package langs.cobol.framework;

public abstract class NumericItem
        <T extends NumericItem<T, V>, V extends Comparable<V>>
        extends ElementaryItem<T, V> {

    protected NumericItem(int length, V value) {
        super(length, value);
    }

}
