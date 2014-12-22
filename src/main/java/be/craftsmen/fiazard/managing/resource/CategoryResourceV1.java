package be.craftsmen.fiazard.managing.resource;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.managing.representation.category.CategoryR;
import be.craftsmen.fiazard.common.error.ErrorR;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = CategoryResourceV1.CATEGORY_BASE_URI, description = "Operations about categories")
@Path(CategoryResourceV1.CATEGORY_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceV1 {

    public static final String CATEGORY_BASE_URI = "/v1/category";
    private static final List<CategoryR> categories;
    public static CategoryR ham = new CategoryR(Id.random(), "Ham");
    public static CategoryR cheese = new CategoryR(Id.random(), "Cheese");

    static {
        categories = Lists.newArrayList(ham, cheese);
    }


    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = CategoryR[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message="Unauthorized")
    })
    public Response getAll(){
        return Response.ok(categories, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
