package langs.cobol.framework;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Pic_9Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testWithoutScale() {
        Pic_9 value = new Pic_9(10);
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(0));
    }

    @Test
    public void testPlusScale() {
        Pic_9 value = new Pic_9(10, 2);
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(2));
    }

    @Test
    public void testMinusScale() {
        Pic_9 value = new Pic_9(10, -2);
        assertThat(value.length(), is(10));
        assertThat(value.scale(), is(-2));
    }

    @Test
    public void testCompare() {
        Pic_9 value = Pic_9.of(1000);
        assertTrue(value.eq(Pic_9.of(1000L)));
        assertTrue(value.gt(Pic_9.of("999.9")));
        assertTrue(value.lt(Pic_9.of("1000.1")));
    }

    @Test
    public void testAdd() {
        Pic_9 value = Pic_9.of(1000);
        value.add(200);
        assertThat(value.intValue(), is(1200));
    }

    @Test
    public void testSubtract() {
        Pic_9 value = Pic_9.of(1000);
        Pic_9.of(30).subtractFrom(value);
        assertThat(value.intValue(), is(970));
    }

    @Test
    public void testMultiply() {
        Pic_9 value = Pic_9.of(1000);
        Pic_9.of(4).multiplyBy(value);
        assertThat(value.intValue(), is(4000));
    }

    @Test
    public void testDivide() {
        Pic_9 quotient  = new Pic_9(10);
        Pic_9 remainder = new Pic_9(10);
        Pic_9.of(1000).divideBy(456, quotient, remainder);
        assertThat(quotient.intValue(), is(2));
        assertThat(remainder.intValue(), is(88));
    }

}
