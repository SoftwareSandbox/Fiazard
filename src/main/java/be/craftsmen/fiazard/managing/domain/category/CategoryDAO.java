package be.craftsmen.fiazard.managing.domain.category;

import java.util.ArrayList;
import java.util.List;

import be.craftsmen.fiazard.managing.domain.product.Product;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import org.mongojack.JacksonDBCollection;

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
