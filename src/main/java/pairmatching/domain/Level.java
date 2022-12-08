package pairmatching.domain;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level getFromName(String input) {
        return Arrays.stream(Level.values())
                .filter(it -> it.name.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("레벨은 1,2,3,4,5까지 있습니다"));
    }
}