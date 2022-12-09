package pairmatching.adapter.in.client;

import pairmatching.application.port.in.PairMatchingUseCase;

public final class QuitStrategy extends PairMatchingStrategy {

    private static final String COMMAND = "Q";

    @Override
    public void run(PairMatchingUseCase pairMatchingUseCase) {
        //Do Nothing And Only Exit
    }

    @Override
    public boolean isRightCommand(String command) {
        return COMMAND.equals(command);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof QuitStrategy;
    }
}
