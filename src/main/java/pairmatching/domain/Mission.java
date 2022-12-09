package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Mission {
    CAR("자동차경주", "레벨1"),
    LOTTO("로또", "레벨1"),
    NUMBER("숫자야구게임", "레벨1"),
    BASKET("장바구니", "레벨2"),
    PURCHASE("결제", "레벨2"),
    SUBWAY("지하철노선도", "레벨2"),
    IMPROVE("성능개선", "레벨4"),
    DEPLOY("배포", "레벨4");

    private final String name;
    private final String level;

    Mission(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public static Mission getFromInfo(String name, String level) {
        return Arrays.stream(Mission.values())
                .filter(it -> it.name.equals(name))
                .filter(it -> it.level.equals(level))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("미션 이름과 레벨을 확인해주세요"));
    }

    public static List<Mission> getSameLevelMissions(Mission other) {
        return Arrays.stream(Mission.values())
                .filter(it -> it.level.equals(other.level))
                .collect(Collectors.toList());
    }
}
