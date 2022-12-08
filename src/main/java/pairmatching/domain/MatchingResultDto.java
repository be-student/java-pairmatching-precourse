package pairmatching.domain;

import java.util.List;

public class MatchingResultDto {

    private final List<List<String>> result;

    public MatchingResultDto(List<List<String>> result) {
        this.result = result;
    }

    public List<List<String>> getResult() {
        return result;
    }
}
