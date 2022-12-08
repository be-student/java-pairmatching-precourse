package pairmatching.adapter.in.client;

public class OutputView {

    private OutputView() {
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR]" + errorMessage);
    }
}
