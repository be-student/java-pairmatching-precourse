package pairmatching.adapter.in.client;

import java.util.ArrayList;
import java.util.List;
import pairmatching.application.port.in.PairMatchingUseCase;

public abstract class PairMatchingStrategy {

    private static final List<PairMatchingStrategy> strategies = new ArrayList<>();

    public static PairMatchingStrategy getFromCommand(String command) {
        return strategies.stream()
                .filter(it -> it.isRightCommand(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다"));
    }

    public static void addStrategy(PairMatchingStrategy pairMatchingStrategy) {
        if (pairMatchingStrategy == null) {
            throw new IllegalStateException("null 은 들어올 수 없습니다");
        }
        strategies.add(pairMatchingStrategy);
    }

    public abstract void run(PairMatchingUseCase pairMatchingUseCase);

    public abstract boolean isRightCommand(String command);
}
