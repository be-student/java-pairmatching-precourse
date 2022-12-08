package pairmatching.application.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.application.port.in.PairMatchingUseCase;
import pairmatching.application.port.in.SearchResultCommand;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.MatchingResultDto;

public class PairMatchingService implements PairMatchingUseCase {

    private List<Crew> crew = new ArrayList<>();


    public PairMatchingService() {
        init();
    }

    private void init() {
        try {
            BufferedReader frontendReader = new BufferedReader(
                    new FileReader("src/main/resources/frontend-crew.md"));
            BufferedReader backendReader = new BufferedReader(
                    new FileReader("src/main/resources/backend-crew.md"));
            readFileByLine(frontendReader, "프론트엔드");
            readFileByLine(backendReader, "백엔드");
        } catch (IOException e) {
            throw new IllegalStateException("파일이 없습니다");
        }
    }

    private void readFileByLine(BufferedReader br, String course) throws IOException {
        while (true) {
            String name = br.readLine();
            if (name == null) {
                break;
            }
            crew.add(new Crew(Course.getFromName(course), name));
        }
    }

    @Override
    public boolean alreadyExist() {
        return false;
    }

    @Override
    public void matching() {

    }

    @Override
    public MatchingResultDto matchingResult(SearchResultCommand searchResultCommand) {
        String search = searchResultCommand.getSearch();
        return null;
    }


    @Override
    public void reset() {

    }
}
