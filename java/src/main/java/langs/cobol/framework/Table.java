package langs.cobol.framework;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Table<E extends DataItem<E>>
        extends GroupItem<Table<E>> {

    private E[] elements;

    public static <E extends DataItem<E>>
            Table<E> of(E... elements) {
        return new Table<E>(elements);
    }

    @SuppressWarnings("unchecked")
    public static <E extends DataItem<E>>
            Table<E> of(Class<E> elementType, int occurs) {
        return new Table<E>((E[]) Array.newInstance(elementType, occurs));
    }

    public Table(E... elements) {
        this.elements = elements;
    }

    public Table(Table<E> other) {
        E[] elements = other.elements.clone();
        for (int i = 0; i < elements.length; ++i) {
            elements[i] = elements[i].clone();
        }
        this.elements = elements;
    }

    @SuppressWarnings("unchecked")
    public Class<E> elementType() {
        return (Class<E>) elements.getClass().getComponentType();
    }

    public int occurs() {
        return elements.length;
    }

    public E[] get() {
        return elements;
    }

    public E get(int index) {
        final int index0 = index - 1;
        if (elements[index0] == null) {
            elements[index0] = createElement();
        }
        return elements[index0];
    }

    E createElement() {
        try {
            return elementType().newInstance();
        } catch (InstantiationException e) {
            throw new CobolException(e);
        } catch (IllegalAccessException e) {
            throw new CobolException(e);
        }
    }

    public void set(int index, E element) {
        elements[index - 1] = element;
    }

    public boolean atEnd(int index) {
        return occurs() < index;
    }

    @Override
    public int byteLength() {
        int result = 0;
        for (E element : elements) {
            result += element.byteLength();
        }
        return result;
    }

    @Override
    public int readFrom(InputStream in) {
        int count = 0;
        for (E element : elements) {
            int n = element.readFrom(in);
            if (n > 0) {
                count += n;
            }
            if (n < element.byteLength()) {
                if (count <= 0) {
                    return -1;
                }
                break;
            }
        }
        return count;
    }

    @Override
    public void writeTo(OutputStream out) {
        for (E element : elements) {
            element.writeTo(out);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    @Override
    public Table<E> clone() {
        return new Table<E>(this);
    }

}
