package pairmatching.domain;

import java.util.regex.Pattern;

public class Search {

    private static final Pattern SEARCH = Pattern.compile(
            "^(백엔드|프론트엔드), 레벨[1-5], (자동차경주|로또|숫자야구게임|장바구니|결제|지하철노선도|성능개선|배포)$");
    private final Course course;
    private final Level level;
    private final Mission mission;

    public Search(String input) {
        validate(input);
        String[] token = input.split(", ");
        course = Course.getFromName(token[0]);
        level = Level.getFromName(token[1]);
        mission = Mission.getFromName(token[2]);
    }

    private void validate(String input) {
        if (SEARCH.matcher(input).matches()) {
            throw new IllegalArgumentException("형식을 다시 확인해 주세요");
        }
    }
}
