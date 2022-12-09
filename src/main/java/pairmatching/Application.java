package pairmatching;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class Application {
    public static void main(String[] args) {
        try {
            List<Crew> crews = new ArrayList<>();
            crews.add(new Crew(Course.BACKEND, "test"));
            crews.add(new Crew(Course.BACKEND, "hello"));
            List<Crew> temp = Randoms.shuffle(crews);
            System.out.println(temp.get(0).getClass());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
