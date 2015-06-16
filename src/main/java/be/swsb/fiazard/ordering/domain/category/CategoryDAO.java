package be.swsb.fiazard.ordering.domain.category;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.JacksonDBCollection;

import be.swsb.fiazard.ordering.domain.product.Product;

import com.google.common.collect.Lists;
import com.mongodb.DB;

public class CategoryDAO {

    private DB db;

    public CategoryDAO(DB db){
        this.db = db;
    }

    public List<Category> findAll(){
        ArrayList<Category> categories = Lists.newArrayList();
        JacksonDBCollection<Product, String> products = JacksonDBCollection.wrap(db.getCollection("products"), Product.class, String.class);
        products.find().forEach((product) -> {
            categories.add(product.getCategory());
        });
        return categories;
    }
}
