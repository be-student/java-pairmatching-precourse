package pairmatching.adapter.in.client;

import static pairmatching.util.Repeat.repeat;

import pairmatching.application.port.in.PairMatchingUseCase;
import pairmatching.application.port.in.SearchResultCommand;

public class Client {

    private final PairMatchingUseCase pairMatchingUseCase;

    public Client(PairMatchingUseCase pairMatchingService) {
        pairMatchingUseCase = pairMatchingService;
    }

    public void run() {
        while (true) {
            Function function = repeat(InputView::askFunction);
            if (function == Function.QUIT) {
                break;
            }
            if (function == Function.SEARCH) {
                repeat(this::search);
            }
            if (function == Function.RESET) {
                reset();
            }
            if (function == Function.MATCHING) {
                repeat(this::matching);
            }
        }
    }

    private void search() {
        SearchResultCommand searchResultCommand = InputView.askSearch();
        OutputView.printResult(pairMatchingUseCase.matchingResult(searchResultCommand));
    }

    private void reset() {
        pairMatchingUseCase.reset();
        OutputView.printReset();
    }

    private void matching() {
        SearchResultCommand searchResultCommand = InputView.askSearch();
        if (pairMatchingUseCase.alreadyExist(searchResultCommand)) {
            ReMatching reMatching = askReMatching();
            if (reMatching == ReMatching.PASS) {
                return;
            }
        }
        pairMatchingUseCase.matching(searchResultCommand);
        OutputView.printResult(pairMatchingUseCase.matchingResult(searchResultCommand));
    }

    private ReMatching askReMatching() {
        return repeat(InputView::askReMatching);
    }
}
