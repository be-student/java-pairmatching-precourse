package pairmatching.application.port.in;

import pairmatching.domain.MatchingResultDto;

public interface PairMatchingUseCase {

    boolean alreadyExist(SearchResultCommand searchResultCommand);

    void matching();

    MatchingResultDto matchingResult(SearchResultCommand searchResultCommand);

    void reset();
}
