package pairmatching;

import pairmatching.adapter.in.client.Client;
import pairmatching.application.service.PairMatchingService;

public class Application {

    public static void main(String[] args) {
        Client client = new Client(new PairMatchingService());
        client.run();
    }
}
