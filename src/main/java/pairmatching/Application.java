package pairmatching;

import pairmatching.adapter.in.client.Client;
import pairmatching.adapter.in.client.MatchingStrategy;
import pairmatching.adapter.in.client.PairMatchingStrategy;
import pairmatching.adapter.in.client.QuitStrategy;
import pairmatching.adapter.in.client.ResetStrategy;
import pairmatching.adapter.in.client.SearchStrategy;
import pairmatching.application.service.PairMatchingService;

public class Application {

    public static void main(String[] args) {
        initPairMatchingStrategy();
        Client client = new Client(new PairMatchingService());
        client.run(new QuitStrategy());
    }

    private static void initPairMatchingStrategy() {
        PairMatchingStrategy quitStrategy = new QuitStrategy();
        PairMatchingStrategy.addStrategy(quitStrategy);

        PairMatchingStrategy searchStrategy = new SearchStrategy();
        PairMatchingStrategy.addStrategy(searchStrategy);

        PairMatchingStrategy resetStrategy = new ResetStrategy();
        PairMatchingStrategy.addStrategy(resetStrategy);

        PairMatchingStrategy matchingStrategy = new MatchingStrategy();
        PairMatchingStrategy.addStrategy(matchingStrategy);
    }
}
