package langs.cobol;

import langs.cobol.framework.Pic9;
import langs.cobol.framework.Program;

public class FizzBuzz extends Program {

    // DATA DIVISION.
    // WORKING-STORAGE SECTION.
    final Pic9 i  = new Pic9(4);
    final Pic9 q  = new Pic9(4);
    final Pic9 r3 = new Pic9(4);
    final Pic9 r5 = new Pic9(4);

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
