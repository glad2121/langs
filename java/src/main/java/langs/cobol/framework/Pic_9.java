package langs.cobol.framework;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

/**
 * Numeric Item.
 */
public class Pic_9 extends NumericItem<Pic_9, BigDecimal> {

    private final int scale;

    public static Pic_9 of(int value) {
        return new Pic_9(0).value(value);
    }

    public static Pic_9 of(long value) {
        return new Pic_9(0).value(value);
    }

    public static Pic_9 of(BigDecimal value) {
        return new Pic_9(0).value(value);
    }

    public static Pic_9 of(String value) {
        return new Pic_9(0).value(value);
    }

    public static Pic_9 sum(Object... values) {
        return Pic_9.of(Constants.ZERO).addAll(values);
    }

    public Pic_9(int length) {
        this(length, 0);
    }

    public Pic_9(int length, int scale) {
        super(length, Constants.ZERO);
        this.scale = scale;
    }

    public int scale() {
        return scale;
    }

    public int intValue() {
        return value().intValueExact();
    }

    public long longValue() {
        return value().longValueExact();
    }

    public Pic_9 value(int value) {
        return value(decimal(value));
    }

    public Pic_9 value(long value) {
        return value(decimal(value));
    }

    public Pic_9 value(String value) {
        return value(decimal(value));
    }

    public void set(int value) {
        set(decimal(value));
    }

    public void set(long value) {
        set(decimal(value));
    }

    public void set(String value) {
        set(decimal(value));
    }

    public void set(String line, int offset) {
        set(StringUtils.substring(line, offset, offset + length()));
    }

    /**
     * self = other.
     */
    public boolean eq(int other) {
        return eq(decimal(other));
    }

    /**
     * self = other.
     */
    public boolean eq(long other) {
        return eq(decimal(other));
    }

    /**
     * self = other.
     */
    public boolean eq(String other) {
        return eq(decimal(other));
    }

    /**
     * self > other.
     */
    public boolean gt(int other) {
        return gt(decimal(other));
    }

    /**
     * self > other.
     */
    public boolean gt(long other) {
        return gt(decimal(other));
    }

    /**
     * self > other.
     */
    public boolean gt(String other) {
        return gt(decimal(other));
    }

    /**
     * self >= other.
     */
    public boolean ge(int other) {
        return ge(decimal(other));
    }

    /**
     * self >= other.
     */
    public boolean ge(long other) {
        return ge(decimal(other));
    }

    /**
     * self >= other.
     */
    public boolean ge(String other) {
        return ge(decimal(other));
    }

    /**
     * self < other.
     */
    public boolean lt(int other) {
        return lt(decimal(other));
    }

    /**
     * self < other.
     */
    public boolean lt(long other) {
        return lt(decimal(other));
    }

    /**
     * self < other.
     */
    public boolean lt(String other) {
        return lt(decimal(other));
    }

    /**
     * self <= other.
     */
    public boolean le(int other) {
        return le(decimal(other));
    }

    /**
     * self <= other.
     */
    public boolean le(long other) {
        return le(decimal(other));
    }

    /**
     * self <= other.
     */
    public boolean le(String other) {
        return le(decimal(other));
    }

    /**
     * ADD value TO self
     * (value + self -> self).
     */
    public Pic_9 add(int value) {
        return add(decimal(value));
    }

    /**
     * ADD value TO self
     * (value + self -> self).
     */
    public Pic_9 add(long value) {
        return add(decimal(value));
    }

    /**
     * ADD value TO self
     * (value + self -> self).
     */
    public Pic_9 add(BigDecimal value) {
        set(value.add(value()));
        return self();
    }

    /**
     * ADD value TO self
     * (value + self -> self).
     */
    public Pic_9 add(String value) {
        return add(decimal(value));
    }

    /**
     * ADD other TO self
     * (other + self -> self).
     */
    public Pic_9 add(Pic_9 other) {
        return add(other.value());
    }

    Pic_9 addAll(Object... values) {
        for (Object value : values) {
            add(decimal(value));
        }
        return self();
    }

    /**
     * ADD self TO to
     * (self + to -> to).
     */
    public void addTo(Pic_9... to) {
        for (Pic_9 each : to) {
            each.add(value());
        }
    }

    /**
     * ADD self TO to GIVING giving
     * (self + to -> giving).
     */
    public void addTo(BigDecimal to, Pic_9[] giving) {
        for (Pic_9 each : giving) {
            each.set(value().add(to));
        }
    }

    /**
     * SUBTRACT value FROM self
     * (self - value -> self).
     */
    public Pic_9 subtract(BigDecimal value) {
        set(value().subtract(value));
        return self();
    }

    /**
     * SUBTRACT self FROM from
     * (from - self -> from).
     */
    public void subtractFrom(Pic_9... from) {
        for (Pic_9 each : from) {
            each.subtract(value());
        }
    }

    /**
     * SUBTRACT self FROM from GIVING giving
     * (from - self -> giving).
     */
    public void subtractFrom(BigDecimal from, Pic_9[] giving) {
        for (Pic_9 each : giving) {
            each.subtract(from.subtract(value()));
        }
    }

    /**
     * MULTIPLY value BY self
     * (value * self -> self).
     */
    public Pic_9 multiply(BigDecimal value) {
        set(value.multiply(value()));
        return self();
    }

    /**
     * MULTIPLY self BY by
     * (self * by -> by).
     */
    public void multiplyBy(Pic_9... by) {
        for (Pic_9 each : by) {
            each.multiply(value());
        }
    }

    /**
     * MULTIPLY self BY by GIVING giving
     * (self * by -> giving).
     */
    public void multiplyBy(BigDecimal by, Pic_9[] giving) {
        for (Pic_9 each : giving) {
            each.set(value().multiply(by));
        }
    }

    /**
     * DIVIDE value INTO self
     * (self / value -> self).
     */
    public Pic_9 divide(BigDecimal value) {
        set(value().divide(value));
        return self();
    }

    /**
     * DEVIDE self BY by GIVING giving REMAINDER remainder
     * (self / by -> giving, self % by -> remainder).
     */
    public void divideBy(int by, Pic_9 giving, Pic_9 remainder) {
        divideBy(decimal(by), giving, remainder);
    }

    /**
     * DEVIDE self BY by GIVING giving REMAINDER remainder
     * (self / by -> giving, self % by -> remainder).
     */
    public void divideBy(BigDecimal by, Pic_9 giving, Pic_9 remainder) {
        giving.set(value().divideToIntegralValue(by));
        remainder.set(value().remainder(by));
    }

    /**
     * DEVIDE self BY by GIVING giving REMAINDER remainder
     * (self / by -> giving, self % by -> remainder).
     */
    public void divideBy(Pic_9 by, Pic_9 giving, Pic_9 remainder) {
        divideBy(by.value(), giving, remainder);
    }

    @Override
    public void readFrom(byte[] buf, int offset) {
        set(new String(buf, offset, byteLength(), encoding()));
    }

    @Override
    public byte[] toByteArray() {
        StringBuilder builder = new StringBuilder();
        builder.append(toString());
        int diff = builder.length() - length();
        for (; diff < 0; ++diff) {
            builder.insert(0, '0');
        }
        if (diff > 0) {
            builder.delete(0, diff);
        }
        return builder.toString().getBytes(encoding());
    }

    @Override
    public String toString() {
        if (value() == null) {
            return null;
        }
        return value().toPlainString();
    }

    BigDecimal decimal(int value) {
        return new BigDecimal(value);
    }

    BigDecimal decimal(long value) {
        return new BigDecimal(value);
    }

    BigDecimal decimal(double value) {
        return new BigDecimal(value);
    }

    BigDecimal decimal(BigInteger value) {
        return new BigDecimal(value);
    }

    BigDecimal decimal(String value) {
        return new BigDecimal(value);
    }

    BigDecimal decimal(Object value) {
        if (value instanceof Pic_9) {
            return ((Pic_9) value).value();
        } else if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof BigInteger) {
            return decimal((BigInteger) value);
        } else if (value instanceof Double) {
            return decimal(((Double) value).doubleValue());
        } else if (value instanceof Float) {
            return decimal(((Float) value).doubleValue());
        } else if (value instanceof Long) {
            return decimal(((Long) value).longValue());
        } else if (value instanceof Number) {
            return decimal(((Number) value).intValue());
        } else if (value instanceof CharSequence) {
            return decimal(value.toString());
        } else if (value instanceof DataItem) {
            return decimal(value.toString());
        } else {
            throw new IllegalArgumentException(String.valueOf(value));
        }
    }

    Charset encoding() {
        return Constants.US_ASCII;
    }

    public static class Helper {

        public static Pic_9[] giving(Pic_9... giving) {
            return giving;
        }

    }

}
