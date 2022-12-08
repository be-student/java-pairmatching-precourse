package pairmatching.application.port.in;

import pairmatching.domain.MatchingResultDto;

public interface PairMatchingUseCase {

    boolean alreadyExist();

    void matching();

    MatchingResultDto matchingResult();

    void reset();
}
