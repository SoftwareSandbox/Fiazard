package be.swsb.fiazard.catering;

import be.swsb.fiazard.common.mongo.MongoDBRule;
import be.swsb.fiazard.ddd.AggregateId;
import be.swsb.fiazard.ddd.AggregateIdGenerator;
import be.swsb.fiazard.ordering.domain.category.Category;
import be.swsb.fiazard.ordering.domain.category.CategoryDAO;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KlaarTeMakenBroodjeRepositoryIntegrationTest {

    private static final String ID1 = "id1";
    private static final String ID2 = "id2";
    private static final AggregateId BESTELLING_ID = new AggregateIdGenerator().generate();
    private static final String NAAM1 = "naam1";
    private static final String NAAM2 = "naam2";

    @Rule
    public MongoDBRule mongoDBRule = MongoDBRule.create();

    private KlaarTeMakenBroodjeRepository repository;

    @Before
    public void setUp() {
        repository = new KlaarTeMakenBroodjeRepository(mongoDBRule.getDB());
    }

    @Test
    public void kanKlaarTeMakenBroodjeOphalen() {
        KlaarTeMakenBroodje broodje1 = persistKlaarTeMakenBroodje(ID1, NAAM1);
        KlaarTeMakenBroodje broodje2 = persistKlaarTeMakenBroodje(ID2, NAAM2);

        List<KlaarTeMakenBroodje> broodjes = repository.findAll();
        assertThat(broodjes).containsOnly(broodje1, broodje2);
    }

    private KlaarTeMakenBroodje klaarTeMakenBroodjeById(List<KlaarTeMakenBroodje> broodjes, String id1) {
        return broodjes.stream().filter(broodje -> id1.equals(broodje.getId())).findFirst().get();
    }

    private KlaarTeMakenBroodje persistKlaarTeMakenBroodje(String id, String naam) {
        KlaarTeMakenBroodje broodje = new KlaarTeMakenBroodje(id, BESTELLING_ID, naam);
        mongoDBRule.persist(broodje);
        return broodje;
    }
}