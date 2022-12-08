package pairmatching.adapter.in.client;

import java.util.function.Supplier;
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
                search();
            }
            if (function == Function.RESET) {
                reset();
            }
            if (function == Function.MATCHING) {
                matching();
            }
        }
    }

    private void search() {
        SearchResultCommand searchResultCommand = repeat(InputView::askSearch);
        OutputView.printResult(pairMatchingUseCase.matchingResult(searchResultCommand));
    }

    private void reset() {
        pairMatchingUseCase.reset();
        OutputView.printReset();
    }

    private void matching() {
        SearchResultCommand searchResultCommand = repeat(InputView::askSearch);
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

    private void repeat(Runnable input) {
        try {
            input.run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            input.run();
        }
    }

    private <T> T repeat(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return input.get();
        }
    }
}
