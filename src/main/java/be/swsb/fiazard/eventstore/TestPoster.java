package be.swsb.fiazard.eventstore;

public class TestPoster {
    public static void main(String[] args) {
        AtomPoster poster = new AtomPoster(null);
        poster.post(new AtomEvent("testEvent", "hellooooooo1231546486464"));
    }
}
