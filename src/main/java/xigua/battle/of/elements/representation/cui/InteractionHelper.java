package xigua.battle.of.elements.representation.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractionHelper {
    private static final int DISPLAY_DELAY_TIME = 618;
    private static final BufferedReader STDIN = new BufferedReader(new InputStreamReader(System.in));

    public static void printEmptyAndLine(String text) {
        System.out.println();
        printLine(text);
    }

    public static void printLine(String text) {
        System.out.print(text);
        try {
            STDIN.readLine();
        } catch (IOException e) {
            throw new RuntimeException("An error occurs while reading enter key.", e);
        }
    }

    public static int readInteger(String text, int min, int max) {
        return readInteger(text, min, max, "");
    }

    public static int readInteger(String prompt, int min, int max, String errorMsg) {
        int integer;

        do {
            System.out.print(prompt);

            try {
                integer = Integer.parseInt(STDIN.readLine());
            } catch (NumberFormatException | IOException e) {
                integer = Integer.MAX_VALUE;
            }

            if (integer < min || integer > max) {
                System.out.println(errorMsg);
            }
        } while (integer < min || integer > max);

        return integer;
    }
}
