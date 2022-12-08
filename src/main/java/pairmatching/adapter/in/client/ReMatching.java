package pairmatching.adapter.in.client;

import java.util.Arrays;

public enum ReMatching {
    REMATCH("네"),
    PASS("아니오");

    private final String input;

    ReMatching(String input) {
        this.input = input;
    }

    public static ReMatching getFromInput(String input) {
        return Arrays.stream(ReMatching.values())
                .filter(it -> it.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 네, 아니오로만 가능합니다"));
    }
}
