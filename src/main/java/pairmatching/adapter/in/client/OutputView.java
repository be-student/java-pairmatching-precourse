package pairmatching.adapter.in.client;

import java.util.List;
import pairmatching.domain.MatchingResultDto;

public class OutputView {

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR]" + errorMessage);
    }

    public static void printResult(MatchingResultDto matchingResultDto) {
        List<List<String>> result = matchingResultDto.getResult();
        result.forEach(OutputView::printOnePair);
    }

    private static void printOnePair(List<String> result) {
        System.out.println(String.join(" : ", result));
    }

    public static void printReset() {
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }
}
