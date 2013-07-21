package langs.cobol.framework;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

public class JavaUtils {

    public static String toString(
            byte[] buf, int offset, int length, Charset encoding) {
        int n = Math.min(buf.length - offset, length);
        return new String(buf, offset, n, encoding);
    }

    public static byte[] read(InputStream in, int length)
            throws IOException {
        byte[] buf = new byte[length];
        int n = in.read(buf);
        if (n < 0) {
            return null;
        }
        if (n < length) {
            return Arrays.copyOf(buf, length);
        }
        return buf;
    }

}
