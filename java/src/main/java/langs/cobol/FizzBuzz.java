package langs.cobol;

import langs.cobol.framework.Pic_9;
import langs.cobol.framework.Program;

public class FizzBuzz extends Program {

    // DATA DIVISION.
    // WORKING-STORAGE SECTION.
    final Pic_9 i  = new Pic_9(4);
    final Pic_9 q  = new Pic_9(4);
    final Pic_9 r3 = new Pic_9(4);
    final Pic_9 r5 = new Pic_9(4);

    public static void main(String[] args) {
        new FizzBuzz().execute();
    }

    // PROCEDURE DIVISION.
    public void execute() {
        for (i.set(1); !i.gt(100); i.add(1)) {
            i.divideBy(3, q, r3);
            i.divideBy(5, q, r5);
            if (r3.eq(0)) {
                if (r5.eq(0)) {
                    display("FizzBuzz");
                } else {
                    display("Fizz");
                }
            } else {
                if (r5.eq(0)) {
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
