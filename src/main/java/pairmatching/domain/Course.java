package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static Course getFromName(String name) {
        return Arrays.stream(Course.values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("코스 이름이 잘못되었습니다"));
    }
}