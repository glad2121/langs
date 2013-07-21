package langs.cobol.framework;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(0));
        assertThat(value.byteLength(), is(10));
        
        value.value("123.45");
        assertThat(value.toString(), is("123"));
        assertThat(toString(value.toByteArray()), is("0000000123"));
        
        value.value("-123.45");
        assertThat(value.toString(), is("-123"));
        assertThat(toString(value.toByteArray()), is("000000012s"));
        
        value.value("123456789012");
        assertThat(value.toString(), is("123456789012"));
        assertThat(toString(value.toByteArray()), is("3456789012"));
        
        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value.toString(), is("1234567890"));
    }

    /**
     * PIC S9(8)V9(2).
     */
    @Test
    public void testPlusScale() {
        Pic9 value = new Pic9(10, 2);
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(2));
        assertThat(value.byteLength(), is(10));
        
        value.value("123.45");
        assertThat(value.toString(), is("123.45"));
        assertThat(toString(value.toByteArray()), is("0000012345"));
        
        value.value("-123.45");
        assertThat(value.toString(), is("-123.45"));
        assertThat(toString(value.toByteArray()), is("000001234u"));
        
        value.value("123456789012");
        assertThat(value.toString(), is("123456789012.00"));
        assertThat(toString(value.toByteArray()), is("5678901200"));
        
        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value.toString(), is("12345678.90"));
    }

    /**
     * PIC S9(10)VP(2).
     */
    @Test
    public void testMinusScale() {
        Pic9 value = new Pic9(10, -2);
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(-2));
        assertThat(value.byteLength(), is(10));
        
        value.value("123.45");
        assertThat(value.toString(), is("100"));
        assertThat(toString(value.toByteArray()), is("0000000001"));
        
        value.value("-123.45");
        assertThat(value.toString(), is("-100"));
        assertThat(toString(value.toByteArray()), is("000000000q"));
        
        value.value("123456789012");
        assertThat(value.toString(), is("123456789000"));
        assertThat(toString(value.toByteArray()), is("1234567890"));
        
        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value.toString(), is("123456789000"));
    }

    @Test
    public void testClone() {
        Pic9 value = new Pic9(10, 2).value("123.45");
        Pic9 clone = value.clone();
        assertThat(clone.value(), sameInstance(value.value()));
        assertThat(clone.toString(), is("123.45"));
        
        clone.set("234.56");
        assertThat(clone.toString(), is("234.56"));
        assertThat(value.toString(), is("123.45"));
    }

    @Test
    public void testCompare() {
        Pic9 value = Pic9.of(1000);
        assertFalse(value.eq(Pic9.of("999.9")));
        assertTrue (value.eq(Pic9.of(1000)));
        assertFalse(value.eq(Pic9.of("1000.1")));
        
        assertTrue (value.gt(Pic9.of("999.9")));
        assertFalse(value.gt(Pic9.of(1000)));
        
        assertTrue (value.ge(Pic9.of("999.9")));
        assertTrue (value.ge(Pic9.of(1000)));
        assertFalse(value.ge(Pic9.of("1000.1")));
        
        assertFalse(value.lt(Pic9.of(1000)));
        assertTrue (value.lt(Pic9.of("1000.1")));
        
        assertFalse(value.le(Pic9.of("999.9")));
        assertTrue (value.le(Pic9.of(1000)));
        assertTrue (value.le(Pic9.of("1000.1")));
    }

    @Test
    public void testAdd() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(200).addTo(value);
        assertThat(value.intValue(), is(1200));
    }

    @Test
    public void testSubtract() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(30).subtractFrom(value);
        assertThat(value.intValue(), is(970));
    }

    @Test
    public void testMultiply() {
        Pic9 value = Pic9.of(1000);
        Pic9.of(4).multiplyBy(value);
        assertThat(value.intValue(), is(4000));
    }

    @Test
    public void testDivide() {
        Pic9 quotient  = new Pic9(10);
        Pic9 remainder = new Pic9(10);
        Pic9.of(1000).divideBy(456, quotient, remainder);
        assertThat(quotient.intValue(), is(2));
        assertThat(remainder.intValue(), is(88));
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.US_ASCII);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.US_ASCII);
    }

}
