package pairmatching.application.port.in;

public class ChooseFunctionCommand {

    private final String function;

    public ChooseFunctionCommand(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input에는 null이 올 수 없습니다");
        }
        function = input;
    }

    public String getFunction() {
        return function;
    }
}
