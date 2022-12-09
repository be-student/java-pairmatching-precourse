package pairmatching.application.port.in;

import pairmatching.domain.MatchingResultDto;

public interface PairMatchingUseCase {

    boolean alreadyExist(SearchResultCommand searchResultCommand);

    void matching(SearchResultCommand searchResultCommand);

    MatchingResultDto matchingResult(SearchResultCommand searchResultCommand);

    void reset();
}
