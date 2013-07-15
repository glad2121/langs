package langs.cobol.framework;

import java.nio.charset.Charset;

public class PicN extends ElementaryItem<PicN, String> {

    public static PicN of(String value) {
        return new PicN(0).value(value);
    }

    public PicN(int length) {
        super(length, "");
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
