package pairmatching.domain;

import java.util.List;

public class MatchingResultDto {

    private final List<String> result;

    public MatchingResultDto(List<String> result) {
        this.result = result;
    }

    public List<String> getResult() {
        return result;
    }
}
