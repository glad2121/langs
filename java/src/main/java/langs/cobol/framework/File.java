package langs.cobol.framework;

import java.io.IOException;
import java.nio.charset.Charset;

public class File {

    public static final String SUCCESS = "00";

    public static final String FAILURE = "99";

    public static final String AT_END = "99";

    private String assignTo;

    private Charset encoding = Charset.defaultCharset();

    private Organization organization;

    private Pic_X status;

    private FileHandler handler;

    public File(
            String assignTo, Organization organization, Pic_X status) {
        this.assignTo = assignTo;
        this.organization = organization;
        this.status = status;
    }

    public void openInput() {
        try {
            handler = createFileHandler();
            status.set(SUCCESS);
        } catch (IOException e) {
            status.set(FAILURE);
        }
    }

    FileHandler createFileHandler() throws IOException {
        if (organization == Organization.LINE_SEQUENTIAL) {
            return new LineSequentialInputFileHandler(assignTo, encoding);
        }
        throw new IllegalArgumentException("organization=" + organization);
    }

    public void read() {
        throw new UnsupportedOperationException();
    }

    public String readLine() {
        try {
            String line = handler.readLine();
            status.set(line != null ? SUCCESS : AT_END);
            return line;
        } catch (IOException e) {
            status.set(FAILURE);
            return null;
        }
    }

    public boolean atEnd() {
        return status.equalTo(AT_END);
    }

    public void close() {
        try {
            handler.close();
            status.set(SUCCESS);
        } catch (IOException e) {
            status.set(FAILURE);
        }
    }

    public enum Organization {

        LINE_SEQUENTIAL

    }

}
