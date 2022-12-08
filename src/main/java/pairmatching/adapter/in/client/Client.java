package pairmatching.adapter.in.client;

import java.util.function.Supplier;
import pairmatching.application.port.in.PairMatchingUseCase;

public class Client {

    private final PairMatchingUseCase pairMatchingService;

    public Client(PairMatchingUseCase pairMatchingService) {
        this.pairMatchingService = pairMatchingService;
    }

    public void run() {
        while (true) {
            Function function = repeat(InputView::askFunction);
            if (function == Function.QUIT) {
                break;
            }
        }
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
