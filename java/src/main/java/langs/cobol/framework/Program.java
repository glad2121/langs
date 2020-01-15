package langs.cobol.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Program extends CobolUtils {

    protected final Logger logger = LogManager.getLogger();

    protected static void initialize(DataItem<?> item) {
        item.initialize();
    }

    protected static void call(Program program) {
        program.execute();
    }

    public abstract void execute();

}
