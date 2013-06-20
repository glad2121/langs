package langs.cobol.framework;

import java.util.Arrays;

public class Table<E> {

    private E[] values;

    public static <E> Table<E> create(E... values) {
        return new Table<E>(values);
    }

    public Table(E... values) {
        this.values = values;
    }

    public int occurs() {
        return values.length;
    }

    public E[] get() {
        return values;
    }

    public E get(int index) {
        final int index0 = index - 1;
        if (values[index0] == null) {
            values[index0] = createElement();
        }
        return values[index0];
    }

    @SuppressWarnings("unchecked")
    E createElement() {
        try {
            return (E) values.getClass().getComponentType().newInstance();
        } catch (InstantiationException e) {
            throw new CobolException(e);
        } catch (IllegalAccessException e) {
            throw new CobolException(e);
        }
    }

    public void set(int index, E value) {
        values[index - 1] = value;
    }

    public boolean atEnd(int index) {
        return occurs() < index;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

}
