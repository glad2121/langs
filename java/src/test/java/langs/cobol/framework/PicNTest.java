package langs.cobol.framework;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PicNTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCore() {
        PicN value = new PicN(5);
        assertThat(value.length(), is(5));
        assertThat(value.byteLength(), is(10));
        
        value.value("あいう");
        assertThat(value.toString(), is("あいう"));
        assertThat(toString(value.toByteArray()), is("あいう　　"));
        
        value.readFrom(toByteArray("xx１２３４５xx"), 2);
        assertThat(value.toString(), is("１２３４５"));
    }

    @Test
    public void testClone() {
        PicN value = new PicN(10).value("あいう");
        PicN clone = value.clone();
        assertThat(clone.value(), sameInstance(value.value()));
        assertThat(clone.value(), is("あいう"));
        
        clone.set("かきく");
        assertThat(clone.value(), is("かきく"));
        assertThat(value.value(), is("あいう"));
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.WINDOWS_31J);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.WINDOWS_31J);
    }

}
