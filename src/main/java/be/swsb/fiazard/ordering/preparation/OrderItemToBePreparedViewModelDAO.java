package be.swsb.fiazard.ordering.preparation;

import static be.swsb.fiazard.ordering.preparation.OrderItemToBePreparedViewModel.ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiFunction;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.DB;

public class OrderItemToBePreparedViewModelDAO {

    private DB db;
	private ObjectMapper objectMapper;

    public OrderItemToBePreparedViewModelDAO(DB db, ObjectMapper objectMapper) {
        this.db = db;
		this.objectMapper = objectMapper;
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
		return JacksonDBCollection.wrap(db.getCollection(ORDER_ITEM_TO_BEPREPARED_COLLECTION_NAME), OrderItemToBePreparedViewModel.class, String.class, objectMapper);
	}

}
