package langs.cobol.framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LineSequentialInputFileHandler implements FileHandler {

    private BufferedReader reader;

    public LineSequentialInputFileHandler(String assignTo, Charset encoding)
            throws FileNotFoundException {
        this.reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(assignTo), encoding));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
    }

}
