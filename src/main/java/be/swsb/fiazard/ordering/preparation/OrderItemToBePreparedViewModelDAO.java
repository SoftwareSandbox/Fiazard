package be.swsb.fiazard.ordering.preparation;

import be.swsb.fiazard.util.representation.FiazardJacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;

import static be.swsb.fiazard.ordering.preparation.OrderItemToBePreparedViewModel.ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME;

public class OrderItemToBePreparedViewModelDAO {

    private DB db;

    public OrderItemToBePreparedViewModelDAO(DB db) {
        this.db = db;
    }

    public List<OrderItemToBePreparedViewModel> find(LocalDateTime dateFrom, LocalDateTime dateUntil) {
        DBCursor<OrderItemToBePreparedViewModel> cursor = collection().find();
        where("deliveryDateTime", cursor::greaterThanEquals, dateFrom);
        where("deliveryDateTime", cursor::lessThanEquals, dateUntil);
        return Lists.newArrayList(cursor.iterator());
    }

    private void where(String fieldName, BiFunction<String, Object, DBCursor<OrderItemToBePreparedViewModel>> function, Object objectToBeUsedForFiltering) {
        if (objectToBeUsedForFiltering == null) {
            return;
        }

        function.apply(fieldName, objectToBeUsedForFiltering);
    }

    private JacksonDBCollection<OrderItemToBePreparedViewModel, String> collection() {
        ObjectMapper objectMapper = MongoJackModule.configure(new ObjectMapper());
        objectMapper.registerModule(FiazardJacksonModule.MODULE);
		return JacksonDBCollection.wrap(db.getCollection(ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME), OrderItemToBePreparedViewModel.class, String.class, objectMapper);
	}

}
