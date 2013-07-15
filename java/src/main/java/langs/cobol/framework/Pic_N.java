package langs.cobol.framework;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;

public class Pic_N extends ElementaryItem<Pic_N, String> {

    public static Pic_N of(String value) {
        return new Pic_N(0).value(value);
    }

    public Pic_N(int length) {
        super(length, "");
    }

    public void set(String line, int offset) {
        set(StringUtils.substring(line, offset, offset + length()));
    }

    @Override
    public int byteLength() {
        return length() * 2;
    }

    @Override
    public void readFrom(byte[] buf, int offset) {
        set(new String(buf, offset, byteLength(), encoding()));
    }

    @Override
    public byte[] toByteArray() {
        StringBuilder builder = new StringBuilder();
        if (value() != null) {
            builder.append(value());
        }
        int diff = builder.length() - length();
        for (; diff < 0; ++diff) {
            builder.append('　');
        }
        if (diff > 0) {
            builder.setLength(length());
        }
        return builder.toString().getBytes(encoding());
    }

    Charset encoding() {
        return Constants.WINDOWS_31J;
    }

}
