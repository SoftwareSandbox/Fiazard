package be.swsb.fiazard.eventstore;

import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.DomainEventTestBuilder;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static be.swsb.fiazard.ddd.AggregateIdTestBuilder.aggregateId;
import static be.swsb.fiazard.ddd.DomainEventTestBuilder.aDumbaEvent;
import static be.swsb.fiazard.ddd.DomainEventTestBuilder.aDumboEvent;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class EventStoreImplTest {

    @InjectMocks
    private EventStoreImpl eventStore;

    @Mock
    private AtomPoster atomPosterMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    //TODO sch3lp: maybe end-to-end test with a live EventStore is better? But how to provision on Travis?
    @Test
    public void store_PostsDomainEventsAsPayloadToTheFiazardStreamWithTheirEventType() throws Exception {
        AggregateId aggregateId = aggregateId("1");
        DomainEventTestBuilder.DumbaDomainEvent dumba = aDumbaEvent().withAggregateId(aggregateId).withDumbaString("dumba").withVersion(0).build();
        DomainEventTestBuilder.DumboDomainEvent dumbo = aDumboEvent().withAggregateId(aggregateId).withDumboString("dumbo").withVersion(1).build();

        eventStore.store(aggregateId, Lists.newArrayList(dumba, dumbo), 1);

        verify(atomPosterMock, times(1)).post(refEq(new AtomEvent("DumbaDomainEvent", dumba), "uuid"));
        verify(atomPosterMock, times(1)).post(refEq(new AtomEvent("DumboDomainEvent", dumbo), "uuid"));
    }
}