package langs.cobol.framework;

import java.math.BigDecimal;

public class Pic_9 extends Pic<Pic_9, BigDecimal> {

    private static final long serialVersionUID = 1L;

    public Pic_9(int length) {
        super(length, BigDecimal.ZERO);
    }

    public Pic_9(int length, int value) {
        this(length, new BigDecimal(value));
    }

    public Pic_9(int length, long value) {
        this(length, new BigDecimal(value));
    }

    public Pic_9(int length, BigDecimal value) {
        super(length, value);
    }

    public int getInt() {
        return get().intValue();
    }

    public void set(int value) {
        set(new BigDecimal(value));
    }

    public void set(long value) {
        set(new BigDecimal(value));
    }

    public void set(String value) {
        set(new BigDecimal(value));
    }

    public void set(String line, int offset) {
        set(line.substring(offset, length()));
    }

    public Pic_9 add(int value) {
        return add(new BigDecimal(value));
    }

    public Pic_9 add(long value) {
        return add(new BigDecimal(value));
    }

    public Pic_9 add(BigDecimal value) {
        set(get().add(value));
        return this;
    }

    public Pic_9 add(String value) {
        return add(new BigDecimal(value));
    }

    public Pic_9 add(Pic_9 other) {
        return add(other.get());
    }

    public void divide(int by, Pic_9 giving, Pic_9 remainder) {
        divide(new Pic_9(length(), by), giving, remainder);
    }

    public void divide(Pic_9 by, Pic_9 giving, Pic_9 remainder) {
        giving.set(get().divideToIntegralValue(by.get()));
        remainder.set(get().remainder(by.get()));
    }

}
