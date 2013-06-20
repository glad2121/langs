package langs.cobol.framework;

public class CobolException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CobolException() {
    }

    public CobolException(String message) {
        super(message);
    }

    public CobolException(Throwable cause) {
        super(cause);
    }

    public CobolException(String message, Throwable cause) {
        super(message, cause);
    }

}
