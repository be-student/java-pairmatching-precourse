package pairmatching.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Search {

    private static final Pattern FORMAT = Pattern.compile(
            "^(백엔드|프론트엔드), 레벨[1-5], (자동차경주|로또|숫자야구게임|장바구니|결제|지하철노선도|성능개선|배포)$");
    private final Level level;
    private final Mission mission;
    private final Course course;

    public Search(String input) {
        validate(input);
        String[] token = input.split(", ");
        course = Course.getFromName(token[0]);
        level = Level.getFromName(token[1]);
        mission = Mission.getFromName(token[2]);
    }

    private void validate(String input) {
        if (FORMAT.matcher(input).matches()) {
            throw new IllegalArgumentException("형식을 다시 확인해 주세요");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Search)) {
            return false;
        }
        if (course != ((Search) o).course) {
            return false;
        }
        if (level != ((Search) o).level) {
            return false;
        }
        return mission == ((Search) o).mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, mission, course);
    }
}
