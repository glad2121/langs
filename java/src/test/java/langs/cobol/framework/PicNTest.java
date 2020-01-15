package langs.cobol.framework;

import static org.assertj.core.api.Assertions.*;

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
        assertThat(value.length()).isEqualTo(5);
        assertThat(value.byteLength()).isEqualTo(10);

        value.value("あいう");
        assertThat(value).hasToString("あいう");
        assertThat(toString(value.toByteArray())).isEqualTo("あいう　　");

        value.readFrom(toByteArray("xx１２３４５xx"), 2);
        assertThat(value).hasToString("１２３４５");
    }

    @Test
    public void testClone() {
        PicN value = new PicN(10).value("あいう");
        PicN clone = value.clone();
        assertThat(clone.value()).isSameAs(value.value());
        assertThat(clone.value()).isEqualTo("あいう");

        clone.set("かきく");
        assertThat(clone.value()).isEqualTo("かきく");
        assertThat(value.value()).isEqualTo("あいう");
    }

    String toString(byte[] bytes) {
        return new String(bytes, Constants.WINDOWS_31J);
    }

    byte[] toByteArray(String s) {
        return s.getBytes(Constants.WINDOWS_31J);
    }

}
