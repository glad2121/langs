package langs.cobol.framework;

public class Pic_N extends Pic<Pic_N, String> {

    private static final long serialVersionUID = 1L;

    public Pic_N(int length) {
        super(length, "");
    }

    public void set(String line, int offset) {
        set(line.substring(offset, length()));
    }

}
