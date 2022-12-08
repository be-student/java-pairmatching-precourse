package pairmatching.adapter.in.client;

import pairmatching.domain.MatchingResultDto;

public class OutputView {

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR]" + errorMessage);
    }

    public static void printResult(MatchingResultDto matchingResultDto) {
    }

    public static void printReset() {
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }
}
