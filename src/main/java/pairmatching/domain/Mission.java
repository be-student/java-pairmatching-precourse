package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR("자동차경주"),
    LOTTO("로또"),
    NUMBER("숫자야구게임"),
    BASKET("장바구니"),
    PURCHASE("결제"),
    SUBWAY("지하철노선도"),
    IMPROVE("성능개선"),
    DEPLOY("배포");

    private final String name;

    Mission(String name) {
        this.name = name;
    }

    public static Mission getFromName(String name) {
        return Arrays.stream(Mission.values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("미션 이름을 확인해주세요"));
    }
}
