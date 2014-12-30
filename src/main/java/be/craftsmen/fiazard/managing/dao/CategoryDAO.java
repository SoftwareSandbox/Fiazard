package be.craftsmen.fiazard.managing.dao;

import be.craftsmen.fiazard.managing.representation.category.CategoryR;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.Mongo;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import java.util.List;

/**
 * Created by sch3lp on 30/12/14.
 */
public class CategoryDAO {


    public CategoryDAO(){
    }

    public List<CategoryR> findAll(){
        List<CategoryR> result = Lists.newArrayList();
//        JacksonDBCollection<CategoryR, String> categories = JacksonDBCollection.wrap(db.getCollection("category"), CategoryR.class, String.class);
//        DBCursor<CategoryR> cursor = categories.find();

        return result;
    }
}
