package langs.cobol.framework;

public class Pic_X extends Pic<Pic_X, String> {

    private static final long serialVersionUID = 1L;

    public Pic_X(int length) {
        super(length, "");
    }

    public void set(String line, int offset) {
        set(line.substring(offset, length()));
    }

}
