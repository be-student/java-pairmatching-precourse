package pairmatching.adapter.in.client;

public final class QuitStrategy extends PairMatchingStrategy {

    private static final String COMMAND = "Q";

    @Override
    public void run() {
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
