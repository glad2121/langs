package langs.cobol.framework;

import static org.assertj.core.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Pic9Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * PIC S9(10).
     */
    @Test
    public void testWithoutScale() {
        Pic9 value = new Pic9(10);
        assertThat(value.length()).isEqualTo(10);
        assertThat(value.scale()).isEqualTo(0);
        assertThat(value.byteLength()).isEqualTo(10);

        value.value("123.45");
        assertThat(value).hasToString("123");
        assertThat(toString(value.toByteArray())).isEqualTo("0000000123");

        value.value("-123.45");
        assertThat(value).hasToString("-123");
        assertThat(toString(value.toByteArray())).isEqualTo("000000012s");

        value.value("123456789012");
        assertThat(value).hasToString("123456789012");
        assertThat(toString(value.toByteArray())).isEqualTo("3456789012");

        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value).hasToString("1234567890");
    }

    /**
     * PIC S9(8)V9(2).
     */
    @Test
    public void testPlusScale() {
        Pic9 value = new Pic9(10, 2);
        assertThat(value.length()).isEqualTo(10);
        assertThat(value.scale()).isEqualTo(2);
        assertThat(value.byteLength()).isEqualTo(10);

        value.value("123.45");
        assertThat(value).hasToString("123.45");
        assertThat(toString(value.toByteArray())).isEqualTo("0000012345");

        value.value("-123.45");
        assertThat(value).hasToString("-123.45");
        assertThat(toString(value.toByteArray())).isEqualTo("000001234u");

        value.value("123456789012");
        assertThat(value).hasToString("123456789012.00");
        assertThat(toString(value.toByteArray())).isEqualTo("5678901200");

        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value).hasToString("12345678.90");
    }

    /**
     * PIC S9(10)VP(2).
     */
    @Test
    public void testMinusScale() {
        Pic9 value = new Pic9(10, -2);
        assertThat(value.length()).isEqualTo(10);
        assertThat(value.scale()).isEqualTo(-2);
        assertThat(value.byteLength()).isEqualTo(10);

        value.value("123.45");
        assertThat(value).hasToString("100");
        assertThat(toString(value.toByteArray())).isEqualTo("0000000001");

        value.value("-123.45");
        assertThat(value).hasToString("-100");
        assertThat(toString(value.toByteArray())).isEqualTo("000000000q");

        value.value("123456789012");
        assertThat(value).hasToString("123456789000");
        assertThat(toString(value.toByteArray())).isEqualTo("1234567890");

        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value).hasToString("123456789000");
    }

    @Test
    public void testClone() {
        Pic9 value = new Pic9(10, 2).value("123.45");
        Pic9 clone = value.clone();
        assertThat(clone.value()).isSameAs(value.value());
        assertThat(clone).hasToString("123.45");

        clone.set("234.56");
        assertThat(clone).hasToString("234.56");
        assertThat(value).hasToString("123.45");
    }

    @Test
    public void testCompare() {
        Pic9 value = Pic9.of(1000);
        assertThat(value.eq(Pic9.of("999.9"))).isFalse();
        assertThat(value.eq(Pic9.of(1000))).isTrue();
        assertThat(value.eq(Pic9.of("1000.1"))).isFalse();

        assertThat(value.gt(Pic9.of("999.9"))).isTrue();
        assertThat(value.gt(Pic9.of(1000))).isFalse();

        assertThat(value.ge(Pic9.of("999.9"))).isTrue();
        assertThat(value.ge(Pic9.of(1000))).isTrue();
        assertThat(value.ge(Pic9.of("1000.1"))).isFalse();

        assertThat(value.lt(Pic9.of(1000))).isFalse();
        assertThat(value.lt(Pic9.of("1000.1"))).isTrue();

        assertThat(value.le(Pic9.of("999.9"))).isFalse();
        assertThat(value.le(Pic9.of(1000))).isTrue();
        assertThat(value.le(Pic9.of("1000.1"))).isTrue();
    }

    @Test
    public void testAdd() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(200).addTo(value);
        assertThat(value.intValue()).isEqualTo(1200);
    }

    @Test
    public void testSubtract() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(30).subtractFrom(value);
        assertThat(value.intValue()).isEqualTo(970);
    }

    @Test
    public void testMultiply() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(4).multiplyBy(value);
        assertThat(value.intValue()).isEqualTo(4000);
    }

    @Test
    public void testDivide() {
        Pic9 quotient  = new Pic9(10);
        Pic9 remainder = new Pic9(10);
        Pic9.of(1000).divideBy(456, quotient, remainder);
        assertThat(quotient.intValue()).isEqualTo(2);
        assertThat(remainder.intValue()).isEqualTo(88);
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.US_ASCII);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.US_ASCII);
    }

}
