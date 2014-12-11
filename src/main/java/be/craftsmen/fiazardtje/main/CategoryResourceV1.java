package be.craftsmen.fiazardtje.main;

import be.craftsmen.fiazardtje.common.Id;
import be.craftsmen.fiazardtje.representation.category.CategoryR;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceV1 {

    private static final List<CategoryR> categories;

    static {
        CategoryR ham = new CategoryR(Id.random(), "Ham");
        CategoryR kaas = new CategoryR(Id.random(), "Kaas");
        categories = Lists.newArrayList(ham, kaas);
    }

    @GET
    @Timed
    public Response getAll(){
        return Response.ok(categories, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
