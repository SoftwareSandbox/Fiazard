package be.craftsmen.fiazard.managing.dao;

import java.util.List;

import be.craftsmen.fiazard.managing.representation.category.CategoryR;

import com.google.common.collect.Lists;

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
