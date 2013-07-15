package langs.cobol.framework;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class File {

    public static final String SUCCESS = "00";
    public static final String FAILURE = "99";
    public static final String AT_END  = "99";

    static final Logger logger = LoggerFactory.getLogger(File.class);

    private DataItem<?> record;

    private String assignTo;

    private Organization organization;

    private Pic_X status;

    private FileHandler handler;

    public File(DataItem<?> record) {
        this.record = record;
    }

    public File assignTo(String assignTo) {
        this.assignTo = assignTo;
        return this;
    }

    public File assignTo(Pic_X assignTo) {
        return assignTo(assignTo.value());
    }

    public File orginizationIs(Organization organization) {
        this.organization = organization;
        return this;
    }

    public File fileStatusIs(Pic_X status) {
        this.status = status;
        return this;
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
            return new LineSequentialInputFileHandler(assignTo);
        }
        throw new IllegalArgumentException("organization=" + organization);
    }

    public void read() {
        try {
            byte[] buf = handler.read();
            if (buf != null) {
                record.readFrom(buf, 0);
                status.set(SUCCESS);
            } else {
                status.set(AT_END);
            }
        } catch (IOException e) {
            status.set(FAILURE);
        }
    }

    public boolean atEnd() {
        return status.eq(AT_END);
    }

    public void close() {
        try {
            handler.close();
            status.set(SUCCESS);
        } catch (IOException e) {
            status.set(FAILURE);
        }
    }

}
