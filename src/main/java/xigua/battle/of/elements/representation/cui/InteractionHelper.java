package xigua.battle.of.elements.representation.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractionHelper {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void printEmptyAndLine(String text) {
        System.out.println();
        System.out.println(text);
    }

    public static void println(String text) {
        System.out.println(text);
    }

    public static int readInteger(String text, int min, int max) {
        return readInteger(text, min, max, "");
    }

    public static int readInteger(String prompt, int min, int max, String errorMsg) {
        int integer;

        do {
            System.out.print(prompt);

            try {
                integer = Integer.parseInt(in.readLine());
            } catch (NumberFormatException | IOException e) {
                throw new RuntimeException("An error occurs while reading integer.", e);
            }

            if (integer < min || integer > max) {
                System.out.println(errorMsg);
            }
        } while (integer < min || integer > max);

        return integer;
    }
}
