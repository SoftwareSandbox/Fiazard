package be.craftsmen.fiazard.managing.resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

import be.craftsmen.fiazard.common.Id;
import be.craftsmen.fiazard.common.error.ErrorR;
import be.craftsmen.fiazard.managing.representation.category.CategoryR;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = CategoryResourceV1.CATEGORY_BASE_URI, description = "Operations about categories")
@Path(CategoryResourceV1.CATEGORY_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResourceV1 {

    public static final String CATEGORY_BASE_URI = "/v1/categories";
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

    @GET
    @Path("/{categoryId}")
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = CategoryR[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message="Unauthorized"),
            @ApiResponse(code = 404, message="Not resource with that id was found")
    })
    public Response getById(@PathParam("categoryId") String categoryId) {
        Optional<CategoryR> optional = categories.stream()
                .filter(p -> Id.fromString(p.getId()).equals(Id.fromString(categoryId)))
                .findFirst();
        if (!optional.isPresent()){
            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
        return Response.ok(
                optional.get(),
                MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
