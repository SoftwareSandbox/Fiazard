package be.swsb.fiazard.catering;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.AggregateIdGenerator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SandwichToPrepareRepositoryIntegrationTest {

    private static final String ID1 = "id1";
    private static final String ID2 = "id2";
    private static final AggregateId ORDER_ID = new AggregateIdGenerator().generate();
    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private SandwichToPrepareRepository repository;

    @Before
    public void setUp() {
        repository = new SandwichToPrepareRepository(mongoDBRule.getDB());
    }

    @Test
    public void canRetrieveSandwichToPrepare() {
        SandwichToPrepare sandwich1 = persistSandwich(ID1, NAME1);
        SandwichToPrepare sandwich2 = persistSandwich(ID2, NAME2);

        List<SandwichToPrepare> sandwiches = repository.findAll();
        assertThat(sandwiches).containsOnly(sandwich1, sandwich2);
    }

    private SandwichToPrepare persistSandwich(String id, String naam) {
        SandwichToPrepare sandwich = new SandwichToPrepare(id, ORDER_ID, naam);
        mongoDBRule.persist(sandwich);
        return sandwich;
    }
}