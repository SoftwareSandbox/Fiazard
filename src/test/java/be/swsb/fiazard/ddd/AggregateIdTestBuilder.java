package be.swsb.fiazard.ddd;

public class AggregateIdTestBuilder {

    private String id;

    public AggregateId build() {
        return new AggregateId(id);
    }

    public static AggregateId aggregateId(String id) {
        return new AggregateIdTestBuilder().withId(id).build();
    }

    public static AggregateId randomId() {
        return AggregateId.generate();
    }

    private AggregateIdTestBuilder() {
    }

    public AggregateIdTestBuilder withId(String id) {
        this.id = id;
        return this;
    }
}