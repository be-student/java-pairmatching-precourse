package pairmatching.adapter.in.client;

import static pairmatching.util.Repeat.repeat;

import pairmatching.application.port.in.PairMatchingUseCase;
import pairmatching.application.port.in.SearchResultCommand;

public class MatchingStrategy extends PairMatchingStrategy {

    private static final String COMMAND = "1";

    @Override
    public void run(PairMatchingUseCase pairMatchingUseCase) {
        repeat(() -> matching(pairMatchingUseCase));
    }

    private void matching(PairMatchingUseCase pairMatchingUseCase) {
        SearchResultCommand searchResultCommand = InputView.askSearch();
        if (pairMatchingUseCase.alreadyExist(searchResultCommand)) {
            ReMatching reMatching = repeat(InputView::askReMatching);
            if (reMatching == ReMatching.PASS) {
                return;
            }
        }
        pairMatchingUseCase.matching(searchResultCommand);
        OutputView.printResult(pairMatchingUseCase.matchingResult(searchResultCommand));
    }

    @Override
    public boolean isRightCommand(String command) {
        return COMMAND.equals(command);
    }
}
