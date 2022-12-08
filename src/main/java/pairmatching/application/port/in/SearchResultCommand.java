package pairmatching.application.port.in;

public class SearchResultCommand {

    private final String function;

    public SearchResultCommand(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input에는 null이 올 수 없습니다");
        }
        function = input;
    }

    public String getFunction() {
        return function;
    }
}
