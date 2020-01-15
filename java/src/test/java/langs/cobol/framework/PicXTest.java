package langs.cobol.framework;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(value.length()).isEqualTo(10);
        assertThat(value.byteLength()).isEqualTo(10);

        value.value("abc");
        assertThat(value).hasToString("abc");
        assertThat(toString(value.toByteArray())).isEqualTo("abc       ");

        value.readFrom(toByteArray("xx1234567890xx"), 2);
        assertThat(value).hasToString("1234567890");
    }

    @Test
    public void testClone() {
        PicX value = new PicX(10).value("abc");
        PicX clone = value.clone();
        assertThat(clone.value()).isSameAs(value.value());
        assertThat(clone.value()).isEqualTo("abc");

        clone.set("def");
        assertThat(clone.value()).isEqualTo("def");
        assertThat(value.value()).isEqualTo("abc");
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.ISO_8859_1);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.ISO_8859_1);
    }

}
