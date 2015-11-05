package be.swsb.fiazard.ordering.preparation;

import be.swsb.fiazard.util.representation.FiazardJacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import java.util.List;

import static be.swsb.fiazard.ordering.preparation.OrderItemToBePreparedViewModel.ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME;

public class OrderItemToBePreparedViewModelDAO {

    private DB db;

    public OrderItemToBePreparedViewModelDAO(DB db) {
        this.db = db;
    }

    public List<OrderItemToBePreparedViewModel> findAll() {
        return Lists.newArrayList(collection().find().iterator());
    }

	private JacksonDBCollection<OrderItemToBePreparedViewModel, String> collection() {
        ObjectMapper objectMapper = MongoJackModule.configure(new ObjectMapper());
        objectMapper.registerModule(FiazardJacksonModule.MODULE);
		return JacksonDBCollection.wrap(db.getCollection(ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME), OrderItemToBePreparedViewModel.class, String.class, objectMapper);
	}

}
