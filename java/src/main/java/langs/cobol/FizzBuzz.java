package langs.cobol;

import langs.cobol.framework.Pic_9;
import langs.cobol.framework.Program;

public class FizzBuzz extends Program {

    // WORKING-STORAGE SECTION.
    final Pic_9 i  = new Pic_9(4);
    final Pic_9 q  = new Pic_9(4);
    final Pic_9 r3 = new Pic_9(4);
    final Pic_9 r5 = new Pic_9(4);

    public static void main(String[] args) {
        new FizzBuzz().execute();
    }

    public void execute() {
        for (i.set(1); i.getInt() <= 1000; i.add(1)) {
            i.divide(3, q, r3);
            i.divide(5, q, r5);
            if (r3.getInt() == 0) {
                if (r5.getInt() == 0) {
                    display("FizzBuzz");
                } else {
                    display("Fizz");
                }
            } else {
                if (r5.getInt() == 0) {
                    display("Buzz");
                } else {
                    showNumber();
                }
            }
        }
    }

    void showNumber() {
        display(i);
    }

}
