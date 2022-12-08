package pairmatching.application.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class PairMatchingService {

    private List<Crew> crew;
    private List<Crew> shuffledCrew;

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
        List<Crew> names = new ArrayList<>();
        while (true) {
            String name = br.readLine();
            if (name == null) {
                break;
            }
            names.add(new Crew(Course.getFromName(course), name));
        }
    }
}
