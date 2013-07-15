package langs.cobol.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class DataItem<T extends DataItem<T>>
        implements Cloneable {

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public void initialize() {
    }

    public void moveTo(DataItem<?> other) {
        other.readFrom(toByteArray(), 0);
    }

    public int byteLength() {
        throw new UnsupportedOperationException();
    }

    public void readFrom(InputStream in) {
        try {
            byte[] buf = new byte[byteLength()];
            in.read(buf);
            readFrom(buf, 0);
        } catch (IOException e) {
            throw new CobolException(e);
        }
    }

    public void readFrom(byte[] buf, int offset) {
        InputStream in = new ByteArrayInputStream(buf);
        in.mark(offset);
        readFrom(in);
    }

    public void writeTo(OutputStream out) {
        try {
            out.write(toByteArray());
        } catch (IOException e) {
            throw new CobolException(e);
        }
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeTo(out);
        return out.toByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CobolException(e);
        }
    }

}
