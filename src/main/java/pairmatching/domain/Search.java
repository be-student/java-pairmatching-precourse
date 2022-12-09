package pairmatching.domain;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Search {

    private static final Pattern FORMAT = Pattern.compile(
            "^(백엔드|프론트엔드), 레벨[1-5], (자동차경주|로또|숫자야구게임|장바구니|결제|지하철노선도|성능개선|배포)$");

    private final Mission mission;
    private final Course course;

    public Search(String input) {
        validate(input);
        String[] token = input.split(", ");
        course = Course.getFromName(token[0]);
        mission = Mission.getFromInfo(token[2], token[1]);
    }

    public Search(Mission mission, Course course) {
        this.course = course;
        this.mission = mission;
    }

    private void validate(String input) {
        if (!FORMAT.matcher(input).matches()) {
            throw new IllegalArgumentException("형식을 다시 확인해 주세요");
        }
    }

    public List<Mission> getSameLevelMissionsAndCourses() {
        return Mission.getSameLevelMissions(mission);
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Search)) {
            return false;
        }
        if (course != ((Search) o).course) {
            return false;
        }
        return mission == ((Search) o).mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mission, course);
    }
}
