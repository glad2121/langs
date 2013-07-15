package langs.cobol.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Program extends CobolUtils {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected static void initialize(DataItem<?> item) {
        item.initialize();
    }

    protected static void call(Program program) {
        program.execute();
    }

    public abstract void execute();

}
