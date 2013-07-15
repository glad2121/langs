package langs.cobol.framework;

public class CobolUtils {

    public static void display(Object... values) {
        StringBuilder builder = new StringBuilder();
        for (Object value : values) {
            builder.append(value);
        }
        System.out.println(builder);
    }

    public static void stopRun() {
        System.exit(0);
    }

}
