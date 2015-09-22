package be.swsb.fiazard.ddd;

import static be.swsb.fiazard.ddd.AggregateIdTestBuilder.aggregateId;

public class DomainEventTestBuilder {

    private DomainEventTestBuilder(){
    }

    public static DumbaEventTestBuilder aDumbaEvent(){
        return new DumbaEventTestBuilder();
    }

    public static DumboEventTestBuilder aDumboEvent(){
        return new DumboEventTestBuilder();
    }

    public static class DumbaEventTestBuilder extends DomainEventTestBuilder{

        private DumbaDomainEvent dumbaEvent;

        public DumbaDomainEvent build() {
            return dumbaEvent;
        }

        private DumbaEventTestBuilder(){
            dumbaEvent = new DumbaDomainEvent();
        }

        public DumbaEventTestBuilder withDumbaString(String dumbaString) {
            dumbaEvent.dumbaString = dumbaString;
            return this;
        }

        public DumbaEventTestBuilder withAggregateId(String id) {
            dumbaEvent.aggregateId = aggregateId(id);
            return this;
        }

        public DumbaEventTestBuilder withAggregateId(AggregateId id) {
            dumbaEvent.aggregateId = id;
            return this;
        }

        public DumbaEventTestBuilder withVersion(int version) {
            dumbaEvent.version = version;
            return this;
        }

    }

    public static class DumbaDomainEvent implements DomainEvent{

        private AggregateId aggregateId;
        private int version;

        private String dumbaString;

        public String getDumbaString() {
            return dumbaString;
        }

        @Override
        public AggregateId getAggregateId() {
            return aggregateId;
        }

        @Override
        public int getVersion() {
            return version;
        }
    }

    public static class DumboEventTestBuilder extends DomainEventTestBuilder{

        private DumboDomainEvent dumboEvent;

        private DumboEventTestBuilder(){
            dumboEvent = new DumboDomainEvent();
        }

        public DumboDomainEvent build() {
            return dumboEvent;
        }

        public DumboEventTestBuilder withDumboString(String dumboString) {
            dumboEvent.dumboString = dumboString;
            return this;
        }

        public DumboEventTestBuilder withAggregateId(String id) {
            dumboEvent.aggregateId = aggregateId(id);
            return this;
        }

        public DumboEventTestBuilder withAggregateId(AggregateId id) {
            dumboEvent.aggregateId = id;
            return this;
        }

        public DumboEventTestBuilder withVersion(int version) {
            dumboEvent.version = version;
            return this;
        }

    }

    public static class DumboDomainEvent implements DomainEvent{

        private AggregateId aggregateId;
        private int version;

        private String dumboString;

        public String getDumboString() {
            return dumboString;
        }

        @Override
        public AggregateId getAggregateId() {
            return aggregateId;
        }

        @Override
        public int getVersion() {
            return version;
        }
    }
}