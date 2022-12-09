package pairmatching.application.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import pairmatching.application.port.in.PairMatchingUseCase;
import pairmatching.application.port.in.SearchResultCommand;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.MatchingResultDto;
import pairmatching.domain.Mission;
import pairmatching.domain.Search;

public class PairMatchingService implements PairMatchingUseCase {

    private final List<String> frontendCrewName = new ArrayList<>();
    private final List<String> backendCrewName = new ArrayList<>();
    private Map<Search, List<List<Crew>>> matchedResult = new HashMap<>();


    public PairMatchingService() {
        init();
    }

    private void init() {
        try {
            BufferedReader frontendReader = new BufferedReader(
                    new FileReader("src/main/resources/frontend-crew.md"));
            BufferedReader backendReader = new BufferedReader(
                    new FileReader("src/main/resources/backend-crew.md"));
            readFileByLine(frontendReader, frontendCrewName);
            readFileByLine(backendReader, backendCrewName);
        } catch (IOException e) {
            throw new IllegalStateException("파일이 없습니다");
        }
    }

    private void readFileByLine(BufferedReader br, List<String> crewNames) throws IOException {
        while (true) {
            String name = br.readLine();
            if (name == null) {
                break;
            }
            crewNames.add(name);
        }
    }

    @Override
    public boolean alreadyExist(SearchResultCommand searchResultCommand) {
        Search search = new Search(searchResultCommand.getSearch());
        return matchedResult.getOrDefault(search, null) != null;
    }

    @Override
    public void matching(SearchResultCommand searchResultCommand) {
        Search search = new Search(searchResultCommand.getSearch());
        List<Crew> shuffledCrew;
        Course course = search.getCourse();
        if (course.equals(Course.BACKEND)) {
            shuffledCrew = fromCourseAndNames(Randoms.shuffle(backendCrewName), course);
        } else {
            shuffledCrew = fromCourseAndNames(Randoms.shuffle(frontendCrewName), course);
        }
        List<List<Crew>> matchedCrew = matchingCrews(shuffledCrew, search);
        matchedResult.put(search, matchedCrew);
    }

    private List<Crew> fromCourseAndNames(List<String> crewNames, Course course) {
        return crewNames.stream()
                .map(name -> new Crew(course, name))
                .collect(Collectors.toList());
    }

    private List<List<Crew>> matchingCrews(List<Crew> shuffledCrew, Search search) {
        for (int i = 0; i < 3; i++) {
            List<List<Crew>> matched = tokenize(shuffledCrew);
            if (isOkay(matched, search)) {
                return matched;
            }
        }
        throw new IllegalArgumentException("매칭할 수 없습니다");
    }


    private List<List<Crew>> tokenize(List<Crew> shuffled) {
        List<List<Crew>> matched = new ArrayList<>();
        int twoPairLength = shuffled.size() - 2 - shuffled.size() % 2;
        for (int i = 0; i < twoPairLength; i += 2) {
            List<Crew> twoPair = new ArrayList<>();
            twoPair.add(shuffled.get(i));
            twoPair.add(shuffled.get(i + 1));
            matched.add(twoPair);
        }
        List<Crew> lastPair = new ArrayList<>();
        for (int i = twoPairLength; i < shuffled.size(); i++) {
            lastPair.add(shuffled.get(i));
        }
        matched.add(lastPair);
        return matched;
    }

    private boolean isOkay(List<List<Crew>> matched, Search search) {
        List<Mission> missions = search.getSameLevelMissionsAndCourses();
        List<Search> searches = missions
                .stream()
                .map(it -> new Search(it, search.getCourse()))
                .collect(Collectors.toList());
        return searches.stream()
                .map(matchedResult::get)
                .filter(Objects::nonNull)
                .noneMatch(it -> containsSamePair(it, matched));
    }

    private boolean containsSamePair(List<List<Crew>> currentMatching, List<List<Crew>> matchedResult) {
        return currentMatching.stream()
                .anyMatch(pair -> pairRemainsSameResult(pair, matchedResult));
    }

    private boolean pairRemainsSameResult(List<Crew> crews, List<List<Crew>> matchedResult) {
        return matchedResult.stream()
                .anyMatch(
                        alreadyMatched -> crews.stream()
                                .filter(alreadyMatched::contains)
                                .count() >= 2
                );
    }


    @Override
    public MatchingResultDto matchingResult(SearchResultCommand searchResultCommand) {
        Search search = new Search(searchResultCommand.getSearch());
        if (matchedResult.get(search) == null) {
            throw new IllegalArgumentException("매칭 이력이 없습니다.");
        }
        List<List<String>> result = matchedResult.get(search)
                .stream()
                .map(this::getCrewsName)
                .collect(Collectors.toList());
        return new MatchingResultDto(result);
    }

    private List<String> getCrewsName(List<Crew> crews) {
        return crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void reset() {
        matchedResult = new HashMap<>();
    }
}
