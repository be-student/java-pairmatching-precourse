package pairmatching.util;

import java.util.function.Supplier;
import pairmatching.adapter.in.client.OutputView;

public class Repeat {

    private Repeat() {
    }

    public static <T> T repeat(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return repeat(input);
        }
    }

    public static void repeat(Runnable input) {
        try {
            input.run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            repeat(input);
        }
    }
}
