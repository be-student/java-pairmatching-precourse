package pairmatching.adapter.in.client;

import java.util.Arrays;

public enum Function {
    MATCHING("1"),
    SEARCH("2"),
    RESET("3"),
    QUIT("Q");

    private final String function;

    Function(String function) {
        this.function = function;
    }

    public static Function getFromFunctionInput(String input) {
        return Arrays.stream(Function.values())
                .filter(it -> it.function.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 1,2,3,Q만 가능합니다"));
    }
}
