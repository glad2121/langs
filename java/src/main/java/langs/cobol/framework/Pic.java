package langs.cobol.framework;

import java.io.Serializable;

public abstract class Pic<T extends Pic<T, V>, V extends Comparable<V>>
        implements Comparable<Pic<T, V>>, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private final int length;

    private V value;

    public Pic(int length) {
        this.length = length;
    }

    public Pic(int length, V value) {
        this(length);
        this.value = value;
    }

    public int length() {
        return length;
    }

    public V get() {
        return value;
    }

    public void set(V value) {
        this.value = value;
    }

    public void set(Pic<T, V> other) {
        set(other.get());
    }

    public boolean equalTo(V other) {
        return compareTo(other) == 0;
    }

    public boolean equalTo(Pic<T, V> other) {
        return equalTo(other.get());
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

    public int compareTo(Pic<T, V> other) {
        return compareTo(other.get());
    }

    public void moveTo(Pic<T, V> other) {
        other.set(get());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CobolException(e);
        }
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
