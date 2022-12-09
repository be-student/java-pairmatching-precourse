package pairmatching.adapter.in.client;

import static pairmatching.util.Repeat.repeat;

import pairmatching.application.port.in.PairMatchingUseCase;

public class Client {

    private final PairMatchingUseCase pairMatchingUseCase;

    public Client(PairMatchingUseCase pairMatchingService) {
        pairMatchingUseCase = pairMatchingService;
    }

    public void run(PairMatchingStrategy exitStrategy) {
        while (true) {
            PairMatchingStrategy pairMatchingStrategy = repeat(InputView::askPairMatchingStrategy);
            if (pairMatchingStrategy.equals(exitStrategy)) {
                break;
            }
            pairMatchingStrategy.run(pairMatchingUseCase);
        }
    }
}
