package langs.cobol.framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LineSequentialInputFileHandler implements FileHandler {

    static final Logger logger = LogManager.getLogger();

    private BufferedReader reader;

    public LineSequentialInputFileHandler(String assignTo)
            throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(assignTo), encoding()));
    }

    public byte[] read() throws IOException {
        String line = reader.readLine();
        if (logger.isDebugEnabled()) {
            logger.debug(line);
        }
        if (line == null) {
            return null;
        }
        return line.getBytes(encoding());
    }

    public void close() throws IOException {
        reader.close();
    }

    Charset encoding() {
        return Constants.ISO_8859_1;
    }

}
