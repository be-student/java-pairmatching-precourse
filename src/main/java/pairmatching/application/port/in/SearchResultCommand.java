package pairmatching.application.port.in;

public class SearchResultCommand {

    private final String search;

    public SearchResultCommand(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input에는 null이 올 수 없습니다");
        }
        search = input;
    }

    public String getSearch() {
        return search;
    }
}
