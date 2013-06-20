package langs.cobol.framework;

import java.io.Closeable;
import java.io.IOException;

public interface FileHandler extends Closeable {

    public String readLine() throws IOException;

}
