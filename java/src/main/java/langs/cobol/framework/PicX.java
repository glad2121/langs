package langs.cobol.framework;

import java.nio.charset.Charset;

/**
 * Alphanumeric Item.
 */
public class PicX extends ElementaryItem<PicX, String> {

    public static PicX of(String value) {
        return new PicX(0).value(value);
    }

    public PicX(int length) {
        super(length, "");
    }

    @Override
    public int byteLength() {
        return length();
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
            builder.append(' ');
        }
        if (diff > 0) {
            builder.setLength(length());
        }
        return builder.toString().getBytes(encoding());
    }

    Charset encoding() {
        return Constants.ISO_8859_1;
    }

}
