package langs.cobol.framework;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PicXTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCore() {
        PicX value = new PicX(10);
        assertThat(value.length(), is(10));
        assertThat(value.byteLength(), is(10));
        
        value.value("abc");
        assertThat(value.toString(), is("abc"));
        assertThat(toString(value.toByteArray()), is("abc       "));
        
        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value.toString(), is("1234567890"));
    }

    @Test
    public void testClone() {
        PicX value = new PicX(10).value("abc");
        PicX clone = value.clone();
        assertThat(clone.value(), sameInstance(value.value()));
        assertThat(clone.value(), is("abc"));
        
        clone.set("def");
        assertThat(clone.value(), is("def"));
        assertThat(value.value(), is("abc"));
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.ISO_8859_1);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.ISO_8859_1);
    }

}
