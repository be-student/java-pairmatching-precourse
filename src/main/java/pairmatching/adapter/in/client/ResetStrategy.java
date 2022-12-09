package pairmatching.adapter.in.client;

import pairmatching.application.port.in.PairMatchingUseCase;

public class ResetStrategy extends PairMatchingStrategy {

    private static final String COMMAND = "3";

    @Override
    public void run(PairMatchingUseCase pairMatchingUseCase) {
        pairMatchingUseCase.reset();
        OutputView.printReset();
    }

    @Override
    public boolean isRightCommand(String command) {
        return COMMAND.equals(command);
    }
}
