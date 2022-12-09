package pairmatching.adapter.in.client;

import static pairmatching.util.Repeat.repeat;

import pairmatching.application.port.in.PairMatchingUseCase;
import pairmatching.application.port.in.SearchResultCommand;

public class SearchStrategy extends PairMatchingStrategy {

    private static final String COMMAND = "2";

    @Override
    public void run(PairMatchingUseCase pairMatchingUseCase) {
        repeat(() -> search(pairMatchingUseCase));
    }

    private void search(PairMatchingUseCase pairMatchingUseCase) {
        SearchResultCommand searchResultCommand = InputView.askSearch();
        OutputView.printResult(pairMatchingUseCase.matchingResult(searchResultCommand));
    }

    @Override
    public boolean isRightCommand(String command) {
        return COMMAND.equals(command);
    }
}
