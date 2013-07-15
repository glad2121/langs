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
    public void test() {
        PicN value = new PicN(5);
        assertThat(value.length(), is(5));
        
        value.value("あいう");
        assertThat(value.toString(), is("あいう"));
        assertThat(toString(value.toByteArray()), is("あいう　　"));
        
        value.readFrom(toByteArray("xx１２３４５xx"), 2);
        assertThat(value.toString(), is("１２３４５"));
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.WINDOWS_31J);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.WINDOWS_31J);
    }

}
